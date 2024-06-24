package com.kongyu.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2024/6/22
 */
public class ListUtils {

    /**
     * <h2>横向分段</h2>
     * <p>将一个集合按特定的长度将其分割成多个subList，每个subList的长度为size。</p>
     * <p>集合越大，分割成subList的数量越多，每个subList的长度不变。</p>
     * <p style="color: #FF4800"><small><b>最后由余数组成的subList，它的长度将会小于size。</b></small></p>
     * <br/>
     * <h3>用途：</h3>
     * <p>将列表按固定大小进行分段，每个段的大小尽量相等，最后一个段可能会比其他段小。</p>
     * <br/>
     * <h3>应用场景：</h3>
     * <ul>
     *     <li><b>批量处理：</b>需要将一个大的数据列表分成若干个较小的子列表进行批量处理时，例如分页显示或批量操作（增删改）。</li>
     *     <li><b>并行处理：</b>分段后的子列表可以并行处理，提高效率。</li>
     *     <li><b>分组操作：</b>将数据按固定大小进行分组，例如每N个元素一组进行某些统计或计算。</li>
     * </ul>
     * <br/>
     *
     * @param size 每个subList的长度
     * @param list 被分割的集合
     * @param <T>  T
     * @return 分割后的集合
     */
    public static <T> List<List<T>> transverseSegmented(int size, List<T> list) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty");
        }

        return IntStream.range(0, (list.size() + size - 1) / size)
                .mapToObj(i -> list.subList(i * size, Math.min((i + 1) * size, list.size())))
                .collect(Collectors.toList());
    }

    /**
     * <h2>纵向分段</h2>
     * <p>将一个集合按特定的数量将其分割成多个subList, 分割的subList的数量为size。</p>
     * <p>集合越大，分割的subList的长度越长，分割subList的数量不变。</p>
     * <p style="color: #FF4800"><small><b>如果集合的长度小于size，那么subList的数量小于size，每个subList的长度为1。</b></small></p>
     * <br/>
     * <h3>用途：</h3>
     * <p>将列表按段数进行分段，每个段的大小尽量相等，剩余元素会均匀分布到前面的段中。</p>
     * <br/>
     * <h3>应用场景：</h3>
     * <ul>
     *     <li><b>任务均分：</b>需要将一个列表平均分成若干个部分，每个部分的元素数量尽量相等，用于任务均分或负载均衡。</li>
     *     <li><b>资源分配：</b>将资源按均分策略分配到若干个子资源集合中，确保每个子集合的资源量尽可能均匀。</li>
     *     <li><b>并行处理：</b>分段后的子列表可以并行处理，提高效率。</li>
     *     <li><b>数据分块：</b>将数据分成多个块，每个块大小接近，用于数据存储或传输。</li>
     * </ul>
     * <br/>
     *
     * @param size 每个subList的长度
     * @param list 被分割的集合
     * @param <T>  T
     * @return 分割后的集合
     */
    public static <T> List<List<T>> longitudinalSegmented(int size, List<T> list) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty");
        }

        int length = list.size();
        int quotient = length / size;
        int remainder = length % size;

        if (size >= length) {
            return IntStream.range(0, length)
                    .mapToObj(i -> list.subList(i, i + 1))
                    .collect(Collectors.toList());
        }

        List<List<T>> segments = new ArrayList<>();
        AtomicInteger increment = new AtomicInteger(0);

        IntStream.range(0, size).forEach(i -> {
            int start = i * quotient + increment.get();
            int end = start + quotient;
            if (remainder > i) {
                increment.incrementAndGet();
                end++;
            }
            segments.add(list.subList(start, end));
        });

        return segments;
    }

}

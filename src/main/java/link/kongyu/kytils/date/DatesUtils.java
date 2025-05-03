package link.kongyu.kytils.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DatesUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 获取指定日期范围内的所有日期
     *
     * @param start start
     * @param end   end
     * @return 日期字符串
     */
    public static List<String> getDates(String start, String end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("参数 start 和 end 必须同时存在");
        }

        try {
            return getDates(LocalDate.parse(start, DATE_FORMATTER), LocalDate.parse(end, DATE_FORMATTER));
        }
        catch (DateTimeParseException e) {
            throw new IllegalArgumentException("参数 start 和 end 必须是有效的日期格式", e);
        }
    }

    /**
     * 获取指定日期范围内的所有日期
     *
     * @param startDate start
     * @param endDate   end
     * @return 日期字符串
     */
    public static List<String> getDates(LocalDate startDate, LocalDate endDate) {
        List<String> dates = new ArrayList<>();
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("参数 start 日期不能晚于 end 日期");
        }

        long elapsed = ChronoUnit.DAYS.between(startDate, endDate);
        for (int i = 0; i <= elapsed; i++) {
            dates.add(startDate.plusDays(i).format(DATE_FORMATTER));
        }
        return dates;
    }

    /**
     * 获取过去指定天数内的所有日期
     *
     * @param days 天数
     * @return 日期字符串
     */
    public static List<String> getPastDates(int days) {
        return getDatesByDays(LocalDate.now(), days, true);
    }

    /**
     * 获取未来指定天数内的所有日期
     *
     * @param days 天数
     * @return 日期字符串
     */
    public static List<String> getFutureDates(int days) {
        return getDatesByDays(LocalDate.now(), days, false);
    }

    /**
     * 获取指定日期范围内的所有日期
     *
     * @param startDate 开始日期
     * @param days      天数
     * @param reverse   是否倒序
     * @return 日期字符串
     */
    public static List<String> getDatesByDays(LocalDate startDate, int days, boolean reverse) {
        if (days < 1) { return Collections.emptyList(); }
        return IntStream.range(0, days)
                        .mapToObj(i -> startDate.plusDays(reverse ? -i : i).format(DATE_FORMATTER))
                        .collect(Collectors.toList());
    }

    /**
     * 获取某个月的所有日期
     *
     * @param year  年份
     * @param month 月份
     * @return 日期字符串
     */
    public static List<String> getDatesOfMonth(int year, int month) {
        LocalDate firstDay = LocalDate.of(year, month, 1);
        LocalDate lastDay = firstDay.withDayOfMonth(firstDay.lengthOfMonth());
        return getDates(firstDay, lastDay);
    }
}

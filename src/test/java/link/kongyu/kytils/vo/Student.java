package link.kongyu.kytils.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends People {
    private String clsNo;

}

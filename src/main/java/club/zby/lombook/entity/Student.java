package club.zby.lombook.entity;

import club.zby.lombook.converter.CustomStringStringConverter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @Author: 赵博雅
 * @Date: 2020/4/24 16:20
 */

/**
 * @Data：作用于类上，是以下注解的集合：
 * {
 *      @ToString
 *      @EqualsAndHashCode: 作用于类，覆盖默认的equals和hashCode
 *      @Getter
 *      @Setter
 *      @RequiredArgsConstructor: 生成包含final和@NonNull注解的成员变量的构造器
 * }
 * @NonNull：主要作用于成员变量和参数中，标识不能为空，否则抛出空指针异常
 * @Log：作用于类上，生成日志变量
 * @AllArgsConstructor: 全参构造器
 * @NoArgsConstructor: 无参构造
 * @Builder: 作用于类上，将类转变为建造者模式
 *
 *
 *
 */
@Component
@AllArgsConstructor //全参构造器
@NoArgsConstructor  //无参构造
@Builder            //作用于类上，将类转变为建造者模式
public @Data class Student {

    @ExcelProperty(value = "姓名",index = 0)
    @NonNull private String name;

    @ExcelProperty(value = "年龄")
    private Integer age;

    @ExcelProperty(value = "性别")
    private String gender;

    @ExcelProperty(value = "身高")
    private String height;

    @ExcelProperty(value = "体重")
    private String weight;
    /**
     * 自定义转换器（String to String）
     */
    @ExcelProperty(value = "专业",converter = CustomStringStringConverter.class)
    private String major;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "日期")
    private String update;

}

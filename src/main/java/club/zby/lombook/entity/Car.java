package club.zby.lombook.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author: 赵博雅
 * @Date: 2020/4/27 15:38
 */
@Component
@AllArgsConstructor //全参构造器
@NoArgsConstructor  //无参构造
@Builder            //作用于类上，将类转变为建造者模式
public @Data class Car {

    @ExcelProperty({"第一列","品牌"})
    private String brand;   //品牌

    @ExcelProperty({"第一列","类型"})
    private String type;   //类型

    @ExcelProperty({"第二列","排量"})
    private String displacement;    //排量



}

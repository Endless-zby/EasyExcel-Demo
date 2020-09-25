package club.zby.lombook.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 赵博雅
 * @date 2020/8/13 10:49
 */
@AllArgsConstructor //全参构造器
@NoArgsConstructor  //无参构造
@Builder            //作用于类上，将类转变为建造者模式
public @Data class Lottery {

    @ExcelProperty(value = "id")
    private String id;   //uid

    @ExcelProperty(value = "phone")
    private String phone;   //手机号

    @ExcelProperty(value = "抽奖次数")
    private String num;    //抽奖次数


}

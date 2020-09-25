package club.zby.lombook.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 赵博雅
 * @date 2020/8/19 17:07
 */
@AllArgsConstructor //全参构造器
@NoArgsConstructor  //无参构造
@Builder
public @Data class LotteryPlus {

    @ExcelProperty(value = "user_id")
    private String uid;   //uid

    @ExcelProperty(value = "number")
    private String number;   //

}

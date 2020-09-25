package club.zby.lombook.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 赵博雅
 * @date 2020/9/22 16:04
 */


@Component
@AllArgsConstructor //全参构造器
@NoArgsConstructor  //无参构造
@Builder            //作用于类上，将类转变为建造者模式
public @Data class Res {

    @ExcelProperty(value = "CLIENT_ID")
    private String text;

}

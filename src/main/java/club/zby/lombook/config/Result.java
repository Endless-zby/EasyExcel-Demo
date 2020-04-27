package club.zby.lombook.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author: 赵博雅
 * @Date: 2020/4/27 19:00
 */
@Component
@AllArgsConstructor //全参构造器
@NoArgsConstructor  //无参构造
@Builder            //作用于类上，将类转变为建造者模式
public @Data class Result {

    private Object data;

    private int flag;

}

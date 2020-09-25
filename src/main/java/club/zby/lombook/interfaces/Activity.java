package club.zby.lombook.interfaces;

/**
 * @author 赵博雅
 * @date 2020/9/25 14:07
 */

import java.lang.annotation.*;

/**定义活动id*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Activity {

    /**
     * 活动id
     * @return
     */
    String id() default " ";

    /**
     * 操作数据库名
     * @return
     */
    String dataBase() default "wealth-application;";

}

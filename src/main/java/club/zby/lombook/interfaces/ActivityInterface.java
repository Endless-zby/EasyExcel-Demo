package club.zby.lombook.interfaces;

import club.zby.lombook.lotteryserver.main;
import org.apache.poi.ss.formula.functions.T;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;

/**
 * @author 赵博雅
 * @date 2020/9/25 14:13
 */
public class ActivityInterface {

    private Class<T> CLASS;

    public ActivityInterface(Class<T> Class){
        this.CLASS = Class;
    }

    public static String getActivity() throws NoSuchMethodException {
        Activity method = getMethod();
        assert method != null;
        return method.id();
    }

    public static String getDataBase() throws NoSuchMethodException {
        Activity method = getMethod();
        assert method != null;
        return "use " + method.dataBase() + ";\n";
    }
    public static Activity getMethod() throws NoSuchMethodException {
        Method dealLotteryExcel = main.class.getDeclaredMethod("main", String[].class);
        boolean annotationPresent = dealLotteryExcel.isAnnotationPresent(Activity.class);
        if(annotationPresent){
            return dealLotteryExcel.getAnnotation(Activity.class);
        }
        return null;
    }

}

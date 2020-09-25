package club.zby.lombook.interfaces;

import club.zby.lombook.lotteryserver.main;
import org.apache.poi.ss.formula.functions.T;

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
        Method dealLotteryExcel = getMethod();
        boolean annotationPresent = dealLotteryExcel.isAnnotationPresent(Activity.class);
        if(annotationPresent){
            Activity annotation = dealLotteryExcel.getAnnotation(Activity.class);
            return annotation.id();
        }
        return "";
    }

    public static String getDataBase() throws NoSuchMethodException {
        Method dealLotteryExcel = getMethod();
        boolean annotationPresent = dealLotteryExcel.isAnnotationPresent(Activity.class);
        if(annotationPresent){
            Activity annotation = dealLotteryExcel.getAnnotation(Activity.class);
            return "use " + annotation.dataBase() + ";\n";
        }
        return "use \n";
    }
    public static Method getMethod() throws NoSuchMethodException {
        return main.class.getDeclaredMethod("main", String[].class);
    }

}

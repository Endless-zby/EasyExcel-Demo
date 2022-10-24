package club.zby.lombook.lotteryserver;

import club.zby.lombook.entity.Res;
import com.alibaba.excel.EasyExcel;
import com.google.common.base.CaseFormat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author 赵博雅
 * @date 2020/9/22 16:06
 */
public class mains {

    public static void main(String[] args) throws FileNotFoundException {
//        ArrayList<Res> res = new ArrayList<>();
//        EasyExcel.read(new FileInputStream("D:\\20200922.xlsx"), Res.class,new ResListener(res)).doReadAll();
//        EasyExcel.write("D:\\20200922Plus.xlsx").sheet().doWrite(res);

        String to = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "mfund_year_rate");
        System.out.println(to);
    }
}

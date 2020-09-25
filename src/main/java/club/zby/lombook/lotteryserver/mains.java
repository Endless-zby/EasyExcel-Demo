package club.zby.lombook.lotteryserver;

import club.zby.lombook.entity.Res;
import com.alibaba.excel.EasyExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author 赵博雅
 * @date 2020/9/22 16:06
 */
public class mains {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Res> res = new ArrayList<>();
        EasyExcel.read(new FileInputStream("D:\\20200922.xlsx"), Res.class,new ResListener(res)).doReadAll();
        EasyExcel.write("D:\\20200922Plus.xlsx").sheet().doWrite(res);
    }
}

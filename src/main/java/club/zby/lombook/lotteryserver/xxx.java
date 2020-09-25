package club.zby.lombook.lotteryserver;

import club.zby.lombook.entity.Lottery;
import club.zby.lombook.entity.LotteryPlus;
import com.alibaba.excel.EasyExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * @author 赵博雅
 * @date 2020/8/19 17:06
 */
public class xxx {

    static String sql = "UPDATE active_person_award set amount = amount + {1} where `uid` = {2};";

    public static void main(String[] args) throws IOException {
        ArrayList<LotteryPlus> lotteryPluses = new ArrayList<>();
        EasyExcel.read(new FileInputStream("D:\\lotteryplus.xlsx"), LotteryPlus.class,new LotteryPlusListener(lotteryPluses)).doReadAll();
        FileChannel outFileChannel = FileChannel.open(Paths.get("D:\\outPlus.txt"), WRITE,CREATE);

        for (int i = 0; i < lotteryPluses.size(); i++) {
            String result = sql;
            String replace = result.replace("{1}", lotteryPluses.get(i).getNumber())
                    .replace("{2}", lotteryPluses.get(i).getUid());

            ByteBuffer buffers = ByteBuffer.allocate(replace.getBytes().length);
            buffers.put(replace.getBytes());
            buffers.flip();
            outFileChannel.write(buffers);
        }
        outFileChannel.close();
        System.out.println("处理sql数量：" + lotteryPluses.size());
    }
}

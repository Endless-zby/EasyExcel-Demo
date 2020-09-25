package club.zby.lombook.lotteryserver;

import club.zby.lombook.entity.Lottery;
import club.zby.lombook.interfaces.Activity;
import club.zby.lombook.interfaces.ActivityInterface;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;

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
 * @date 2020/8/13 10:56
 */
public class main {

    public static final String sql = "INSERT INTO `active_person_award` (`uid`, `mobile`, `amount`, `employ_sum`, `friend_trade_count`, `is_win`, `prize`, `source`, `activity_id`, `friend_id`, `create_at`, `update_at`) VALUES\n";
    public static final String value = "(用户编码, 手机号码, 抽奖次数, 0, 0, 0, 0, 0, 活动编码, NULL, 创建时间, 更新时间)";


    @Activity(id = "96",dataBase = "wealth-application")
    public static void main(String[] args) throws IOException, NoSuchMethodException {
        ArrayList<Lottery> lotteries = new ArrayList<>();
        EasyExcel.read(new FileInputStream("D:\\lottery.xlsx"), Lottery.class,new LotteryListener(lotteries)).doReadAll();
        FileChannel outFileChannel = FileChannel.open(Paths.get("D:\\out.txt"), WRITE,CREATE);

        ByteBuffer dataBase = ByteBuffer.allocate(ActivityInterface.getDataBase().length());
        dataBase.put(ActivityInterface.getDataBase().getBytes());
        dataBase.flip();
        outFileChannel.write(dataBase);

        ByteBuffer buffer = ByteBuffer.allocate(sql.length());
        buffer.put(sql.getBytes());
        buffer.flip();
        outFileChannel.write(buffer);

        for (int i = 0; i < lotteries.size(); i++) {
            String result = value;
            String replace = result.replace("用户编码", lotteries.get(i).getId()).replace("手机号码", lotteries.get(i).getPhone())
                    .replace("活动编码", ActivityInterface.getActivity())
                    .replace("抽奖次数", lotteries.get(i).getNum()).replace("创建时间", String.valueOf(System.currentTimeMillis()))
                    .replace("更新时间", String.valueOf(System.currentTimeMillis()));
            if(i < lotteries.size() - 1){
                replace = replace + ",\n";
            }else {
                replace = replace + ";";
            }

            ByteBuffer buffers = ByteBuffer.allocate(replace.getBytes().length);
            buffers.put(replace.getBytes());
            buffers.flip();
            outFileChannel.write(buffers);
        }
        outFileChannel.close();
        System.out.println("处理sql数量：" + lotteries.size());

    }

}

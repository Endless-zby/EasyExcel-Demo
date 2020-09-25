package club.zby.lombook.service;

import club.zby.lombook.entity.Lottery;
import club.zby.lombook.interfaces.Activity;
import club.zby.lombook.lotteryserver.LotteryListener;
import com.alibaba.excel.EasyExcel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * @author 赵博雅
 * @date 2020/8/14 16:59
 */
@Service
public class LotteryService {

    public static final String CREATE = "CREATE";
    public static final String UPDATE = "UPDATE";

    public static final String DATABASE = "use wealth-application;\n";

    public static final String sql = "INSERT INTO `active_person_award` (`uid`, `mobile`, `amount`, `employ_sum`, `friend_trade_count`, `is_win`, `prize`, `source`, `activity_id`, `friend_id`, `create_at`, `update_at`) VALUES\n";
    public static final String value = "(用户编码, 手机号码, 抽奖次数, 0, 0, 0, 0, 0, 活动编码, NULL, 创建时间, 更新时间)";


    static String update = "UPDATE active_person_award set amount = amount + {1} where `uid` = {2};\n";


    public void dealLotteryExcel(MultipartFile file,String activity,String type,String url,String name) throws IOException {

        ArrayList<Lottery> lotteries = new ArrayList<>();
        EasyExcel.read(file.getInputStream(), Lottery.class,new LotteryListener(lotteries)).doReadAll();
        FileChannel outFileChannel = FileChannel.open(Paths.get(url + name), StandardOpenOption.WRITE, StandardOpenOption.CREATE);


        if(CREATE.equals(type)){
            ByteBuffer dataBase = ByteBuffer.allocate(DATABASE.length());
            dataBase.put(DATABASE.getBytes());
            dataBase.flip();
            outFileChannel.write(dataBase);
            ByteBuffer buffer = ByteBuffer.allocate(sql.length());
            buffer.put(sql.getBytes());
            buffer.flip();
            outFileChannel.write(buffer);
            for (int i = 0; i < lotteries.size(); i++) {
                String result = value;
                String replace = result.replace("用户编码", lotteries.get(i).getId()).replace("手机号码", lotteries.get(i).getPhone())
                        .replace("活动编码",activity)
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
        }
        if(UPDATE.equals(type)){
            ByteBuffer buffer = ByteBuffer.allocate(DATABASE.length());
            buffer.put(DATABASE.getBytes());
            buffer.flip();
            outFileChannel.write(buffer);
            for (Lottery lo : lotteries) {
                String result = update;
                String replace = result.replace("{1}",lo.getNum())
                        .replace("{2}",lo.getId());

                ByteBuffer buffers = ByteBuffer.allocate(replace.getBytes().length);
                buffers.put(replace.getBytes());
                buffers.flip();
                outFileChannel.write(buffers);
            }
        }

        outFileChannel.close();
        System.out.println("处理sql数量：" + lotteries.size());
    }


}

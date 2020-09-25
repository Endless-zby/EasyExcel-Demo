package club.zby.lombook.lotteryserver;

import club.zby.lombook.entity.Lottery;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵博雅
 * @date 2020/8/13 11:36
 */

public class LotteryListener extends AnalysisEventListener<Lottery> {

    List<Lottery> lotteries = new ArrayList<>();

    public LotteryListener(List<Lottery> lotteries){
        this.lotteries = lotteries;
    }

    @Override
    public void invoke(Lottery lottery, AnalysisContext analysisContext) {
        if(!lotteries.contains(lottery)){
            lotteries.add(lottery);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("所有数据解析完成！");
    }
}

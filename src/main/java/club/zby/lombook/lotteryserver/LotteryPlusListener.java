package club.zby.lombook.lotteryserver;

import club.zby.lombook.entity.Lottery;
import club.zby.lombook.entity.LotteryPlus;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵博雅
 * @date 2020/8/19 17:10
 */
public class LotteryPlusListener extends AnalysisEventListener<LotteryPlus> {
    List<LotteryPlus> lotteryPluses = new ArrayList<>();

    public LotteryPlusListener(List<LotteryPlus> lotteryPluses){
        this.lotteryPluses = lotteryPluses;
    }

    @Override
    public void invoke(LotteryPlus lotteryPlus, AnalysisContext analysisContext) {
        lotteryPluses.add(lotteryPlus);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("所有数据解析完成！");
    }
}

package club.zby.lombook.lotteryserver;

import club.zby.lombook.entity.Lottery;
import club.zby.lombook.entity.Res;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵博雅
 * @date 2020/9/22 16:07
 */
public class ResListener extends AnalysisEventListener<Res> {


    private static final String[] test = {"880000008", "880019097", "880059653", "880020640", "880053948", "880026185", "880026881", "880027992", "880107260",
            "880123409", "880136920", "880160054", "880263126", "880298911", "880312515","880355948", "880646447", "880781631", "880995795", "881200981", "881210923", "881228568", "881272248", "881296952", "881306795", "881310515", "881336761", "881403191", "881434394",
            "881449574", "881459242", "881517045", "881562474"};


    List<Res> lotteries = new ArrayList<>();

    public ResListener(List<Res> lotteries){
        this.lotteries = lotteries;
    }

    @Override
    public void invoke(Res res, AnalysisContext analysisContext) {
        Boolean flag = false;
        for (String str: test) {
            if(str.equals(res.getText())){
                flag = true;
              break;
            }
        }
        if(!flag){
            lotteries.add(res);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("所有数据解析完成！");
    }
}

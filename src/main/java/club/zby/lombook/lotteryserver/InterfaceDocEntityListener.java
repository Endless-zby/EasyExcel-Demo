package club.zby.lombook.lotteryserver;

import club.zby.lombook.entity.InterfaceDocEntity;
import club.zby.lombook.entity.Lottery;
import club.zby.lombook.entity.OutDoc;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class InterfaceDocEntityListener extends AnalysisEventListener<InterfaceDocEntity> {

    TreeMap<String, OutDoc> outDoc;

    ArrayList<InterfaceDocEntity> funInterfaceDocList = new ArrayList<>();

    String funName = null;

    String funId = null;

    public InterfaceDocEntityListener(TreeMap<String, OutDoc> outDoc) {
        this.outDoc = outDoc;
    }

    @Override
    public void invoke(InterfaceDocEntity interfaceDocEntity, AnalysisContext analysisContext) {

        if(null == interfaceDocEntity){
            return;
        }

        if(StringUtils.isEmpty(interfaceDocEntity.getFunId())){
            if(!StringUtils.isEmpty(interfaceDocEntity.getParameter())){
                String to = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, interfaceDocEntity.getParameter());
                interfaceDocEntity.setParameter(to);
            }
            funInterfaceDocList.add(interfaceDocEntity);
            return;
        }

        if (isNumeric(interfaceDocEntity.getFunId())) {
            funId = interfaceDocEntity.getFunId();
            funName = interfaceDocEntity.getFunName();
            String to = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, interfaceDocEntity.getParameter());
            interfaceDocEntity.setParameter(to);
            funInterfaceDocList.add(interfaceDocEntity);
        }
        if ("功能编号".equals(interfaceDocEntity.getFunId())) {
            OutDoc build = OutDoc.builder().funId(funId).funName(funName).interfaceDocEntities(funInterfaceDocList).build();
            outDoc.put(funId,build);
            // 清除
            funInterfaceDocList = new ArrayList<>();
            funName = null;
            funId = null;
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        System.out.println("所有数据解析完成！");
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}

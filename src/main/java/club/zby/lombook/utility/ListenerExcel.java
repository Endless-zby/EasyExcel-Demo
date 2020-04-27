package club.zby.lombook.utility;

import club.zby.lombook.dao.StudentDao;
import club.zby.lombook.entity.Student;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2020/4/26 18:57
 */
public class ListenerExcel extends AnalysisEventListener<Student> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerExcel.class);

    //批次保存最大值
    private static final int BATCH_COUNT = 2;

    List<Student> list = new ArrayList<>();

    private StudentDao studentDao;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param studentDao
     */
    public ListenerExcel(StudentDao studentDao,List<Student> list) {
        this.studentDao = studentDao;
        this.list = list;
    }
    public ListenerExcel(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as invoke
     * @param context
     */
    @Override
    public void invoke(Student data, AnalysisContext context) {
        Integer rowNum = context.getCurrentRowNum();
        if(rowNum == 0 || rowNum == null){
            return;
        }
        LOGGER.info("解析到一条数据:{},位于第{}行", JSON.toJSONString(data),rowNum);

        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM

//        if (list.size() >= BATCH_COUNT) {
//            saveData();
//            // 存储完成清理 list
//            list.clear();
//        }

    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }
    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        studentDao.saveAll(list);
        LOGGER.info("存储数据库成功！");
    }

    public List<Student> getResult(){
        return list;
    }

}

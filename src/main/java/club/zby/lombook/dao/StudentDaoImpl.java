package club.zby.lombook.dao;

import club.zby.lombook.entity.Student;
import lombok.extern.java.Log;

import java.util.List;


/**
 * @Author: 赵博雅
 * @Date: 2020/4/26 19:56
 */
@Log
public class StudentDaoImpl implements StudentDao {
    @Override
    public void saveAll(List<Student> student) {

        log.info("数据写入中。。。");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

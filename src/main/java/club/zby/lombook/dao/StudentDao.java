package club.zby.lombook.dao;


import club.zby.lombook.entity.Student;

import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2020/4/26 19:04
 */

public interface StudentDao {

    void saveAll(List<Student> student);

}

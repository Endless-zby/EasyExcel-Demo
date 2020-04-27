package club.zby.lombook.main;

import club.zby.lombook.dao.StudentDao;
import club.zby.lombook.dao.StudentDaoImpl;
import club.zby.lombook.entity.Student;
import club.zby.lombook.utility.ListenerExcel;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import lombok.extern.java.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.DataFormatException;


/**
 * @Author: 赵博雅
 * @Date: 2020/4/24 16:55
 */
@Log
public class main {

    public static void main(String[] args) throws FileNotFoundException {
        Student student = new Student();
        student.setName("赵博雅");
        student.setAge(23);
        student.setGender("man");
        student.setHeight("176");
        student.setWeight("120");
        System.out.println(student.toString());
        log.info("日志打印。。。。。。");

        System.out.println(log.getName());
        Student build = Student.builder()
                .name("张三")
                .age(22)
                .gender("man")
                .height("170")
                .weight("120")
                .build();
        System.out.println(build);


        StudentDao studentdao = new StudentDaoImpl();
        ArrayList<Student> students = new ArrayList<>();

        ExcelReaderBuilder read =
                EasyExcel.read(
                        new FileInputStream("E:\\studentTest.xlsx"),
                        Student.class,
                        new ListenerExcel(studentdao,students));
        read.doReadAll();
        System.out.println(students.size());
    }
}

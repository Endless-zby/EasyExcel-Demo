package club.zby.lombook;

import club.zby.lombook.dao.StudentDao;
import club.zby.lombook.dao.StudentDaoImpl;
import club.zby.lombook.entity.Student;
import club.zby.lombook.utility.ListenerExcel;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class LombookApplicationTests {

    @Test
    void contextLoads() throws FileNotFoundException {

        //数据库 -> 数据持久化
        StudentDao studentdao = new StudentDaoImpl();

        ExcelReaderBuilder read =
                EasyExcel.read(
                        new FileInputStream("E:\\studentTest.xlsx"),
                        Student.class,
                        new ListenerExcel(studentdao));
        read.doReadAll();
    }
}

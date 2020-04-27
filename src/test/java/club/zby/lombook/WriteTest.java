package club.zby.lombook;


import club.zby.lombook.entity.Car;
import club.zby.lombook.service.DataTemplate;
import club.zby.lombook.service.serviceImpl.DataTemplateImpl;
import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: 赵博雅
 * @Date: 2020/4/27 10:34
 */
@SpringBootTest
public class WriteTest {

    @Test
    void contextLoads() {

        String fileName = "E:\\" + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        //生成测试数据的模板
        DataTemplate dataTemplate = new DataTemplateImpl();

        EasyExcel.write(fileName, Car.class)
                .sheet("模板")
                .doWrite(dataTemplate.createCar());
    }
}

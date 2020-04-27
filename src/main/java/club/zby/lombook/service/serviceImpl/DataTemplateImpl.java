package club.zby.lombook.service.serviceImpl;

import club.zby.lombook.entity.Car;
import club.zby.lombook.entity.Student;
import club.zby.lombook.service.DataTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2020/4/27 11:05
 */
public class DataTemplateImpl implements DataTemplate {
    @Override
    public List<Student> createStudent() {

        List<Student> students = new ArrayList<>();

        Student build = Student.builder()
                .name("张三")
                .age(22)
                .gender("man")
                .height("170")
                .weight("120")
                .major("计算机科学与技术")
                .update("2020-04-27 11:17:50")
                .build();

        students.add(build);
        return students;
    }

    @Override
    public List<Car> createCar() {
        List<Car> cars = new ArrayList<>();

        Car car = Car.builder()
                .brand("哈弗H6")
                .type("SUV")
                .displacement("1.5")
                .build();

        cars.add(car);
        return cars;
    }
}

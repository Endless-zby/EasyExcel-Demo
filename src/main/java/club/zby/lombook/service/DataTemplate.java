package club.zby.lombook.service;

import club.zby.lombook.entity.Car;
import club.zby.lombook.entity.Student;

import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2020/4/27 11:03
 */
public interface DataTemplate {

    List<Student> createStudent();

    List<Car> createCar();


}

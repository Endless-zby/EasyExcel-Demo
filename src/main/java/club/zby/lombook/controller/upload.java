package club.zby.lombook.controller;

import club.zby.lombook.config.Result;
import club.zby.lombook.dao.StudentDaoImpl;
import club.zby.lombook.entity.Car;
import club.zby.lombook.entity.Student;
import club.zby.lombook.utility.ListenerExcel;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 赵博雅
 * @Date: 2020/4/27 16:41
 */
@Api(value = "EasyExcel_Demo")
@RestController("EasyExcel")
public class upload {


    @ApiOperation(value = "解析模板Excel",notes = "解析Student的模板Excel")
    @ApiResponse(code = 400,message = "请上传模板文件",response = String.class)
    @PostMapping("upload")
    @ResponseBody
    public Result upload(MultipartFile file) throws IOException {
        //接收监听器中解析的所有数据
        ArrayList<Student> students = new ArrayList<>();
        EasyExcel.read(file.getInputStream(), Student.class, new ListenerExcel(new StudentDaoImpl(),students)).doReadAll();
        return new Result(students,200);
    }

    @ApiOperation(value = "下载模板Excel",notes = "下载Student的模板Excel")
    @ApiResponse(code = 400,message = "下载模板文件",response = String.class)
    @PostMapping("download")
    @ResponseBody
    public void  download(@RequestBody List<Car> car, HttpServletResponse response) throws IOException {
        //接收监听器中解析的所有数据
        //使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), Car.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(car);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

}

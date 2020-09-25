package club.zby.lombook.controller;

import club.zby.lombook.config.FileUtil;
import club.zby.lombook.config.Result;
import club.zby.lombook.dao.StudentDaoImpl;
import club.zby.lombook.entity.Car;
import club.zby.lombook.entity.Student;
import club.zby.lombook.service.LotteryService;
import club.zby.lombook.utility.ListenerExcel;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
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

    @Autowired
    private LotteryService lotteryService;



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

    @ApiOperation(value = "上传待导入数据",notes = "下载out.txt")
    @ApiResponse(code = 400,message = "上传待导入数据",response = String.class)
    @PostMapping("download")
    @ResponseBody
    public void  download(HttpServletResponse response, MultipartFile file, @NotNull  @RequestParam("activity") String activity, @NotNull @RequestParam("type") String type) throws IOException {

        String url = "D:\\";
        String name = "out.txt";
        lotteryService.dealLotteryExcel(file,activity,type,url,name);
        FileUtil.downloadFile(response,name,url);
    }

}

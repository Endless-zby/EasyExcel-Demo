package club.zby.lombook.lotteryserver;

import club.zby.lombook.entity.InterfaceDocEntity;
import club.zby.lombook.entity.Lottery;
import club.zby.lombook.entity.OutDoc;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class interfaceMain {


    public static void main(String[] args) throws FileNotFoundException {

        TreeMap<String, OutDoc> outDoc = new TreeMap<>();



        ExcelReader build = EasyExcel.read(new FileInputStream("D:\\杭州接口文档\\intf_V2.1.91_test.xlsx"), InterfaceDocEntity.class, new InterfaceDocEntityListener(outDoc)).build();
        List<ReadSheet> readSheets = build.excelExecutor().sheetList();
        System.out.println(JSON.toJSONString(readSheets));

        ReadSheet readSheet = EasyExcel.readSheet(13).build();

        build.read(readSheet);

        System.out.println(JSON.toJSONString(outDoc));

    }


}

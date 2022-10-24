package club.zby.lombook.lotteryserver;

import club.zby.lombook.entity.EntityFactory;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class interfaceMain {


    public static void main(String[] args) throws IOException {

        TreeMap<String, OutDoc> outDoc = new TreeMap<>();



        ExcelReader build = EasyExcel.read(new FileInputStream("D:\\贵州股交接口文档\\555555.xlsx"), InterfaceDocEntity.class, new InterfaceDocEntityListener(outDoc)).build();
        List<ReadSheet> readSheets = build.excelExecutor().sheetList();
        System.out.println(JSON.toJSONString(readSheets));

        ReadSheet readSheet = EasyExcel.readSheet("股交官网接口").build();

        build.read(readSheet);

        System.out.println(JSON.toJSONString(outDoc));

        OutDoc outDoc1 = outDoc.get("600");
        EntityFactory entityFactory = new EntityFactory(outDoc1,"D:\\Code Myself\\EasyExcel-Demo\\src\\main\\java\\club\\zby\\lombook\\tmp\\");
        String funIn = entityFactory.createFunIn();
        System.out.println(funIn  + "\n\n\n");

        String funOut = entityFactory.createFunOut();
        System.out.println(funOut);

    }


}

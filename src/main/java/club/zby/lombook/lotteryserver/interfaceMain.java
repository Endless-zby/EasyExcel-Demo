package club.zby.lombook.lotteryserver;

import club.zby.lombook.entity.EntityFactory;
import club.zby.lombook.entity.InterfaceDocEntity;
import club.zby.lombook.entity.OutDoc;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSON;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class interfaceMain {


    public static void main(String[] args) throws IOException {

        TreeMap<String, OutDoc> outDoc = new TreeMap<>();

        ExcelReader build = EasyExcel.read(new FileInputStream("D:\\杭州接口文档\\intf_V2.1.91_test.xlsx"), InterfaceDocEntity.class, new InterfaceDocEntityListener(outDoc)).build();
        List<ReadSheet> readSheets = build.excelExecutor().sheetList();
        System.out.println(JSON.toJSONString(readSheets));

        ReadSheet readSheet = EasyExcel.readSheet("股交官网接口").build();

        build.read(readSheet);

        System.out.println(JSON.toJSONString(outDoc));

        OutDoc outDoc1 = outDoc.get("600");
        EntityFactory entityFactory = new EntityFactory(outDoc1,"D:\\Myself\\EasyExcel-Demo\\src\\main\\java\\club\\zby\\lombook\\tmp\\");
        String funIn = entityFactory.createFunIn();
        System.out.println(funIn  + "\n\n\n");

        String funOut = entityFactory.createFunOut();
        System.out.println(funOut);

    }


}

package club.zby.lombook.entity;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class EntityFactory {

    private static OutDoc outDoc;

    private static String filePathName;

    private static String className;

    private static String suffix = ".java";

    public EntityFactory(OutDoc outDoc, String filePathName) {
        EntityFactory.outDoc = outDoc;
        EntityFactory.filePathName = filePathName;
    }

    public EntityFactory(OutDoc outDoc) {
        EntityFactory.outDoc = outDoc;
    }

    public String createFunIn() throws IOException {
        List<InterfaceDocEntity> interfaceDocEntities = outDoc.getInterfaceDocEntities();
        if(CollectionUtils.isEmpty(interfaceDocEntities)){
            return null;
        }
        String content = createParameter(outDoc, InterfaceDocEntity.IN_PUT);
        if(!StringUtils.isEmpty(filePathName)){
            createFile(filePathName + className + suffix, content);
        }
        return content;
    }

    public String createFunOut() throws IOException {
        List<InterfaceDocEntity> interfaceDocEntities = outDoc.getInterfaceDocEntities();
        if(CollectionUtils.isEmpty(interfaceDocEntities)){
            return null;
        }

        String content = createParameter(outDoc, InterfaceDocEntity.OUT_PUT);
        if(!StringUtils.isEmpty(filePathName)){
            createFile(filePathName + className + suffix, content);
        }
        return content;
    }

    public String createParameter(InterfaceDocEntity interfaceDocEntity){

        String annotation = String.format(InterfaceDocEntity.ANNOTATION,interfaceDocEntity.getParameterName());

        String parameterType = DataTypeEnum.valueOf(interfaceDocEntity.getParameterType().substring(0,1)).getType();

        StringBuilder parameters = new StringBuilder(annotation);
        parameters.append(InterfaceDocEntity.ENTER);
        parameters.append(String.format(InterfaceDocEntity.PARAMETER_MODEL,InterfaceDocEntity.PRIVATE,parameterType,interfaceDocEntity.getParameter()));
        parameters.append(InterfaceDocEntity.ENTER);

        return parameters.toString();
    }

    public String createParameter(OutDoc outDoc, String inOutPut){
        List<InterfaceDocEntity> interfaceDocEntitys = outDoc.getInterfaceDocEntities().stream().filter(doc -> inOutPut.equals(doc.getIo())).collect(Collectors.toList());

        StringBuilder content = new StringBuilder();
        content.append(String.format(InterfaceDocEntity.AUTHOR,outDoc.getFunName(),"开发者姓名"));
        content.append(InterfaceDocEntity.GETTER_ANNOTATION);
        content.append(InterfaceDocEntity.SETTER_ANNOTATION);

        StringBuilder parameters = new StringBuilder();

        for (InterfaceDocEntity interfaceDocEntity : interfaceDocEntitys) {
            String parameter = createParameter(interfaceDocEntity);
            parameters.append(parameter);
        }
        String common = inOutPut.equals(InterfaceDocEntity.IN_PUT) ? InterfaceDocEntity.COMMON_IN : InterfaceDocEntity.COMMON_OUT;
        className = inOutPut.equals(InterfaceDocEntity.IN_PUT) ? InterfaceDocEntity.IN_PUT_CLASS_NAME : InterfaceDocEntity.OUT_PUT_CLASS_NAME;



        content.append(String.format(InterfaceDocEntity.CODE_CONTENT,String.format(className,outDoc.getFunId()),common, "1L", parameters));

        return content.toString();
    }


   public static void createFile(String filePathName, String content) throws IOException {
        Path path = Paths.get(filePathName);
        //使用newBufferedWriter创建文件并写入文件
        //使用try无需手动关闭
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(content);
        }
//        //追加写模式
//        try(BufferedWriter writer = Files.newBufferedWriter(path,StandardCharsets.UTF_8, StandardOpenOption.APPEND)){
//            writer.append("追加写");
//        }
    }

}

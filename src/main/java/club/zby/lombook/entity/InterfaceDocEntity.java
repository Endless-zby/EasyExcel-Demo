package club.zby.lombook.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterfaceDocEntity {

    public static final String IN_PUT = "I";
    public static final String OUT_PUT = "O";
    public static final String ANNOTATION =
            "    /**\n" +
            "     * %s\n" +
            "     */";

    public static final String PARAMETER_MODEL = "    %s %s %s;";

    public static final String ENTER = "\n";

    public static final String PRIVATE = "private";

    public static final String IN_PUT_CLASS_NAME = "Fun%sRequestIn";

    public static final String OUT_PUT_CLASS_NAME = "Fun%sResponseOut";

    public static final String DATA_ANNOTATION = "@Data\n";

    public static final String COMMON_IN = "CommonIn";

    public static final String COMMON_OUT = "CommonOut";

    public static final String CODE_CONTENT = "public class %s extends %s {\n" +
            "    private static final long serialVersionUID = %s;\n" +
            "\n" +
            "%s\n" +
            "}";

    public static final String AUTHOR = "/**\n" +
            " * %s\n" +
            " *\n" +
            " * @author %s\n" +
            " */\n";


    @ExcelProperty(value = "功能编号")
    private String funId;

    @ExcelProperty(value = "功能名称")
    private String funName;

    @ExcelProperty(value = "输入输出")
    private String io;

    @ExcelProperty(value = "参数字段")
    private String parameter;

    @ExcelProperty(value = "参数类型")
    private String parameterType;

    @ExcelProperty(value = "参数名称")
    private String parameterName;

    @ExcelProperty(value = "功能类型")
    private String funType;

    @ExcelProperty(value = "是否结果集")
    private String isList;

    @ExcelProperty(value = "是否为空")
    private String isNull = "N";

    @ExcelProperty(value = "验证模式")
    private String authenticationMode;




}

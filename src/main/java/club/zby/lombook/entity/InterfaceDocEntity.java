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
    private String isNull;

    @ExcelProperty(value = "验证模式")
    private String authenticationMode;


}

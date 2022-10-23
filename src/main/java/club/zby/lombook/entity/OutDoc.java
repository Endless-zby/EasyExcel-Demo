package club.zby.lombook.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OutDoc {

    private String funId;

    private String funName;

    private List<InterfaceDocEntity> interfaceDocEntities;

}

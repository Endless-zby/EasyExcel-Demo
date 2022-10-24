package club.zby.lombook.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutDoc {

    private String funId;

    private String funName;

    private List<InterfaceDocEntity> interfaceDocEntities;

}

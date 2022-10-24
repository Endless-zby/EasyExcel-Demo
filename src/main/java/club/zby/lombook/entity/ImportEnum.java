package club.zby.lombook.entity;

import java.util.Arrays;

public enum ImportEnum {

    BigDecimal("BigDecimal","import java.math.BigDecimal;\n"),
    Data("Data","import lombok.Data;\n");


    private String key;
    private String value;

    ImportEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        ImportEnum[] values = ImportEnum.values();
        System.out.println(Arrays.toString(values));
    }
}

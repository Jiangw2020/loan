package jw.srb.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DictEnum {

    EDUCATION("education", "学历"),
    INDUSTRY("industry", "从事行业"),
    INCOME("income", "收入来源"),
    RETURN_SOURCE("returnSource", "还款来源"),
    RELATION("relation", "联系人关系"),

    RETURN_METHOD("returnMethod", "还款方式"),
    MONEY_USER("moneyUse", "资金用途"),
    ;

    private String dictCode;
    private String msg;
}

package jw.srb.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LendStatusEnum {

    CHECK(0, "待发布"),
    INVEST_RUN(1, "募资中"),
    PAY_RUN(2, "还款中"),
    PAY_OK(3, "已结清"),
    FINISH(4, "结标"),
    CANCEL(-1, "已撤标"),
//    OVERDUE(-1, "逾期催收中"),
//    BAD_BILL(-2, "坏账"),
    ;

    private Integer status;
    private String msg;


    public static String getMsgByStatus(int status) {
        LendStatusEnum arrObj[] = LendStatusEnum.values();
        for (LendStatusEnum obj : arrObj) {
            if (status == obj.getStatus().intValue()) {
                return obj.getMsg();
            }
        }
        return "";
    }
}

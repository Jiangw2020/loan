package jw.srb.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class LendNoUtils {

    public static String getNo() {

        LocalDateTime time=LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String strDate = dtf.format(time);

        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }

        return strDate + result;
    }

    public static String getLendNo() {

        return "LEND" + getNo();
    }

    public static String getLendItemNo() {

        return "INVEST" + getNo();
    }

    public static String getLoanNo() {

        return "LOAN" + getNo();
    }

    public static String getReturnNo() {
        return "RETURN" + getNo();
    }


    public static Object getWithdrawNo() {
        return "WITHDRAW" + getNo();
    }


    public static String getReturnItemNo() {
        return "RETURNITEM" + getNo();
    }


    public static String getChargeNo() {

        return "CHARGE" + getNo();
    }

    /**
     * 获取交易编码
     */
    public static String getTransNo() {
        return "TRANS" + getNo();
    }

}
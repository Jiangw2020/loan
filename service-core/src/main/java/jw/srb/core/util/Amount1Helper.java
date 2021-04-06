package jw.srb.core.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 等额本息工具类
 * 校验网址：http://www.xjumc.com/
 * 等额本息是指一种贷款的还款方式，是在还款期内，每月偿还同等数额的贷款(包括本金和利息)，和等额本金是不一样的概念，虽然刚开始还款时每月还款额可能会低于等额本金还款方式，但是最终所还利息会高于等额本金还款方式，该方式经常被银行使用。
 *
 * 每月还款数额计算公式如下：
 * 每月还款额=贷款本金×[月利率×(1+月利率) ^ 还款月数]÷{[(1+月利率) ^ 还款月数]-1}
 */
public class Amount1Helper {

    /**
     * 每月还款利息
     * @param invest 总借款额（贷款本金）
     * @param yearRate 年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还利息
     */
    public static Map<Integer, BigDecimal> getPerMonthInterest(BigDecimal invest, BigDecimal yearRate, int totalMonth) {
        Map<Integer, BigDecimal> map = new HashMap();
        //月利息
        double monthRate = yearRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_DOWN).doubleValue();
        BigDecimal monthInterest;
        for (int i = 1; i < totalMonth + 1; i++) {
            BigDecimal multiply = invest.multiply(new BigDecimal(monthRate));
            BigDecimal sub  = new BigDecimal(Math.pow(1 + monthRate, totalMonth)).subtract(new BigDecimal(Math.pow(1 + monthRate, i-1)));
            monthInterest = multiply.multiply(sub).divide(new BigDecimal(Math.pow(1 + monthRate, totalMonth) - 1), 8, BigDecimal.ROUND_DOWN);
            monthInterest = monthInterest.setScale(2, BigDecimal.ROUND_DOWN);
            map.put(i, monthInterest);
        }
        return map;
    }

    /**
     * 每月还款本金
     * @param invest 总借款额（贷款本金）
     * @param yearRate 年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还本金
     */
    public static Map<Integer, BigDecimal> getPerMonthPrincipal(BigDecimal invest, BigDecimal yearRate, int totalMonth) {
        double monthRate = yearRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_DOWN).doubleValue();
        BigDecimal monthIncome = invest.multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalMonth)))
                .divide(new BigDecimal(Math.pow(1 + monthRate, totalMonth) - 1), 8, BigDecimal.ROUND_DOWN);
        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, yearRate, totalMonth);
        Map<Integer, BigDecimal> mapPrincipal = new HashMap();

        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
            mapPrincipal.put(entry.getKey(), monthIncome.subtract(entry.getValue()));
        }
        return mapPrincipal;
    }

    /**
     * 总利息
     * @param invest 总借款额（贷款本金）
     * @param yearRate 年利率
     * @param totalMonth 还款总月数
     * @return 总利息
     */
    public static BigDecimal getInterestCount(BigDecimal invest, BigDecimal yearRate, int totalMonth) {
        BigDecimal count = new BigDecimal(0);
        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, yearRate, totalMonth);

        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
            count = count.add(entry.getValue());
        }
        return count;
    }

    public static void main(String[] args) {
        BigDecimal invest = new BigDecimal("12000"); // 本金
        int month = 12;
        BigDecimal yearRate = new BigDecimal("0.12"); // 年利率
        Map mapInterest = getPerMonthInterest(invest, yearRate, month);
        System.out.println("等额本息---每月还款利息：" + mapInterest);
        Map mapPrincipal = getPerMonthPrincipal(invest, yearRate, month);
        System.out.println("等额本息---每月还款本金：" + mapPrincipal);
        BigDecimal count = getInterestCount(invest, yearRate, month);
        System.out.println("等额本息---总利息：" + count);
    }
}

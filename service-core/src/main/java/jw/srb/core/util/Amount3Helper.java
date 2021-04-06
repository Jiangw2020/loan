package jw.srb.core.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 按月付息到期还本工具类
 */
public class Amount3Helper {

    /**
     * 每月还款利息
     * 按月付息，到期还本-计算获取还款方式为按月付息，到期还本的每月偿还利息
     * 公式：每月应还利息=总借款额*年利率÷还款月数
     *
     * @param invest 总借款额（贷款本金）
     * @param yearRate 年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还利息
     */
    public static Map getPerMonthInterest(BigDecimal invest, BigDecimal yearRate, int totalMonth) {
        Map<Integer, BigDecimal> map = new HashMap<>();
        //每月偿还利息
        BigDecimal monthIncome = invest.multiply(yearRate).divide(new BigDecimal(12), 8, BigDecimal.ROUND_DOWN);
        for(int i=1; i<=totalMonth; i++) {
            map.put(i, monthIncome);
        }
        return map;
    }

    /**
     * 每月偿还本金
     * @param invest 总借款额（贷款本金）
     * @param yearRate 年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还本金
     */
    public static Map<Integer, BigDecimal> getPerMonthPrincipal(BigDecimal invest, BigDecimal yearRate, int totalMonth) {
        Map<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();
        // 每月利息
        for (int i = 1; i <= totalMonth; i++) {
            if(i == totalMonth){
                map.put(i, invest);
            } else {
                map.put(i, new BigDecimal("0"));
            }
        }
        return map;
    }

    /**
     * 总利息
     * @param invest 总借款额（贷款本金）
     * @param yearRate 年利率
     * @param totalMonth 还款总月数
     * @return 总利息
     */
    public static BigDecimal getInterestCount(BigDecimal invest, BigDecimal yearRate, int totalMonth) {
        //每月偿还利息
        BigDecimal count = invest.multiply(yearRate).divide(new BigDecimal(12), 2, BigDecimal.ROUND_DOWN);

        return count.multiply(new BigDecimal(totalMonth));
    }

    public static void main(String[] args) {
        BigDecimal invest = new BigDecimal("10000"); // 本金
        int month = 12;
        BigDecimal yearRate = new BigDecimal("0.12"); // 年利率

        Map getPerMonthPrincipalInterest = getPerMonthPrincipal(invest, yearRate, month);
        System.out.println("按月付息到期还本---每月偿还本金：" + getPerMonthPrincipalInterest);
        Map mapInterest = getPerMonthInterest(invest, yearRate, month);
        System.out.println("按月付息到期还本---每月偿还利息：" + mapInterest);
        BigDecimal count = getInterestCount(invest, yearRate, month);
        System.out.println("按月付息到期还本---总利息：" + count);
    }
}
package jw.srb.core.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * 等额本金工具类
 * 校验网址：http://www.xjumc.com/
 * 等额本金是指一种贷款的还款方式，是在还款期内把贷款数总额等分，每月偿还同等数额的本金和剩余贷款在该月所产生的利息，这样由于每月的还款本金额固定，
 *  * 而利息越来越少，借款人起初还款压力较大，但是随时间的推移每月还款数也越来越少。
 */
public class Amount2Helper {

    /**
     * 每月本息
     * @param invest 总借款额（贷款本金）
     * @param yearRate 年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还利息
     */
    public static Map<Integer, BigDecimal> getPerMonthPrincipalInterest(BigDecimal invest, BigDecimal yearRate, int totalMonth) {
        Map<Integer, BigDecimal> map = new HashMap();
        // 每月本金
        BigDecimal monthPri = invest.divide(new BigDecimal(totalMonth), 8, BigDecimal.ROUND_DOWN);
        // 获取月利率
        double monthRate = yearRate.divide(new BigDecimal(12), 8, BigDecimal.ROUND_DOWN).doubleValue();
        monthRate = new BigDecimal(monthRate).setScale(8, BigDecimal.ROUND_DOWN).doubleValue();
        for (int i = 1; i <= totalMonth; i++) {
            double monthRes = monthPri.doubleValue() + (invest.doubleValue() - monthPri.doubleValue() * (i - 1)) * monthRate;
            monthRes = new BigDecimal(monthRes).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            map.put(i, new BigDecimal(monthRes));
        }
        return map;
    }

    /**
     * 每月还款利息
     * @param invest 总借款额（贷款本金）
     * @param yearRate 年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还本金
     * @return
     */
    public static Map<Integer, BigDecimal> getPerMonthInterest(BigDecimal invest, BigDecimal yearRate, int totalMonth) {
        Map<Integer, BigDecimal> inMap = new HashMap();
        BigDecimal principal = invest.divide(new BigDecimal(totalMonth), 8, BigDecimal.ROUND_DOWN);
        Map<Integer, BigDecimal> map = getPerMonthPrincipalInterest(invest, yearRate, totalMonth);
        for (Map.Entry<Integer, BigDecimal> entry : map.entrySet()) {
            BigDecimal principalBigDecimal = principal;
            BigDecimal principalInterestBigDecimal = new BigDecimal(entry.getValue().toString());
            BigDecimal interestBigDecimal = principalInterestBigDecimal.subtract(principalBigDecimal);
            interestBigDecimal = interestBigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
            inMap.put(entry.getKey(), interestBigDecimal);
        }
        return inMap;
    }

    /**
     * 每月还款本金
     * @param invest 总借款额（贷款本金）
     * @param yearRate 年利率
     * @param totalMonth 还款总月数
     * @return 总利息
     */
    public static Map<Integer, BigDecimal> getPerMonthPrincipal(BigDecimal invest, BigDecimal yearRate, int totalMonth) {
        Map<Integer, BigDecimal> map = new HashMap<>();
        BigDecimal monthIncome = invest.divide(new BigDecimal(totalMonth), 8, BigDecimal.ROUND_DOWN);
        for(int i=1; i<=totalMonth; i++) {
            map.put(i, monthIncome);
        }
        return map;
    }

    /**
     * 总利息
     * @param invest
     * @param yearRate
     * @param totalMonth
     * @return
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

        Map benjin = getPerMonthPrincipal(invest, yearRate, month);
        System.out.println("等额本金---每月本金:" + benjin);
        Map mapInterest = getPerMonthInterest(invest, yearRate, month);
        System.out.println("等额本金---每月利息:" + mapInterest);
        BigDecimal count = getInterestCount(invest, yearRate, month);
        System.out.println("等额本金---总利息：" + count);
    }
}
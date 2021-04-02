package com.heepay.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="登录对象", description="登录对象")
public class InvestNotifyVo {

    /**
     * 结果编码：
     * 0001=投标成功
     * E100=商户授权错误
     * E101=签名错误
     * E103=时间戳无效
     * E104=参数不全或不合法
     * E105=投标失败。该错误记录了投资单
     * E106=数据验证失败
     * U999=未知错误
     */
    private String resultCode;
    //投资结果描述
    private String resultMsg;
    //商户订单号
    private String agentBillNo;
    //项目编号
    private String agentProjectCode;
    //标类型。0-满标，1-流转标。该接口固定返回0
    private String projectType;
    //投资人绑定协议号
    private String voteBindCode;
    //投资金额
    private String voteAmt;
    //商户奖励投资人的金额。
    //与商户传入的vote_prize_amt一致
    private String votePrizeAmt;
    //商户手续费
    private String voteFeeAmt;
    //投标备注
    private String voteNote;
    //通知时间。从1970-01-01 00:00:00算起的毫秒数。绑定错误不返回。
    private String timestamp;
    //签名
    private String sign;

}

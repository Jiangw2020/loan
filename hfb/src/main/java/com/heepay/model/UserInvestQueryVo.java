package com.heepay.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "UserInvest")
public class UserInvestQueryVo {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "投资人绑定协议号")
	private String voteBindCode;

	@ApiModelProperty(value = "借款人绑定协议号")
	private String benefitBindCode;

	@ApiModelProperty(value = "项目编号")
	private String agentProjectCode;

	@ApiModelProperty(value = "项目名称")
	private String agentProjectName;

	@ApiModelProperty(value = "商户订单号")
	private String agentBillNo;

	@ApiModelProperty(value = "投资金额")
	private String voteAmt;

	@ApiModelProperty(value = "投资奖励金额")
	private String votePrizeAmt;

	@ApiModelProperty(value = "商户手续费")
	private String voteFeeAmt;

	@ApiModelProperty(value = "项目总金额")
	private String projectAmt;

}


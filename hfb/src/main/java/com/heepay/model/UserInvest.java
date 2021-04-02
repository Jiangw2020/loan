package com.heepay.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * UserInvest
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "UserInvest")
@TableName("user_invest")
public class UserInvest extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "投资人绑定协议号")
	@TableField("vote_bind_code")
	private String voteBindCode;

	@ApiModelProperty(value = "借款人绑定协议号")
	@TableField("benefit_bind_code")
	private String benefitBindCode;

	@ApiModelProperty(value = "项目编号")
	@TableField("agent_project_code")
	private String agentProjectCode;

	@ApiModelProperty(value = "项目名称")
	@TableField("agent_project_name")
	private String agentProjectName;

	@ApiModelProperty(value = "商户订单号")
	@TableField("agent_bill_no")
	private String agentBillNo;

	@ApiModelProperty(value = "投资金额")
	@TableField("vote_amt")
	private String voteAmt;

	@ApiModelProperty(value = "投资奖励金额")
	@TableField("vote_prize_amt")
	private String votePrizeAmt;

	@ApiModelProperty(value = "商户手续费")
	@TableField("vote_fee_amt")
	private String voteFeeAmt;

	@ApiModelProperty(value = "项目总金额")
	@TableField("project_amt")
	private String projectAmt;

	@ApiModelProperty(value = "投资备注")
	@TableField("note")
	private String note;

	@ApiModelProperty(value = "状态")
	@TableField("status")
	private Integer status;


	@TableField(exist = false)
	private String resultCode;
	@TableField(exist = false)
	private String resultMsg;
}


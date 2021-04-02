package com.heepay.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * UserItemReturn
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "UserItemReturn")
@TableName("user_item_return")
public class UserItemReturn extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "还款id")
	@TableField("user_return_id")
	private Long userReturnId;

	@ApiModelProperty(value = "还款项目编号")
	@TableField("agent_project_code")
	private String agentProjectCode;

	@ApiModelProperty(value = "投资单号")
	@TableField("vote_bill_no")
	private String voteBillNo;

	@ApiModelProperty(value = "收款人（投资人）")
	@TableField("to_bind_code")
	private String toBindCode;

	@ApiModelProperty(value = "还款金额")
	@TableField("transit_amt")
	private String transitAmt;

	@ApiModelProperty(value = "还款本金")
	@TableField("base_amt")
	private String baseAmt;

	@ApiModelProperty(value = "还款利息")
	@TableField("benifit_amt")
	private String benifitAmt;

	@ApiModelProperty(value = "商户手续费")
	@TableField("fee_amt")
	private String feeAmt;

	@ApiModelProperty(value = "备注")
	@TableField("note")
	private String note;

	@ApiModelProperty(value = "状态")
	@TableField("status")
	private Integer status;

}


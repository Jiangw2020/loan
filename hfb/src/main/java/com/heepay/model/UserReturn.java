package com.heepay.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * UserReturn
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "UserReturn")
@TableName("user_return")
public class UserReturn extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "商户商品名称")
	@TableField("agent_goods_name")
	private String agentGoodsName;

	@ApiModelProperty(value = "批次号")
	@TableField("agent_batch_no")
	private String agentBatchNo;

	@ApiModelProperty(value = "还款人绑定协议号")
	@TableField("from_bind_code")
	private String fromBindCode;

	@ApiModelProperty(value = "还款总额")
	@TableField("total_amt")
	private String totalAmt;

	@ApiModelProperty(value = "商户手续费")
	@TableField("vote_fee_amt")
	private String voteFeeAmt;

	@ApiModelProperty(value = "还款明细数据")
	@TableField("data")
	private String data;

	@ApiModelProperty(value = "备注")
	@TableField("note")
	private String note;

	@ApiModelProperty(value = "状态")
	@TableField("status")
	private Integer status;

}


//
//
package com.heepay.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * UserBind
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "UserBind")
@TableName("user_bind")
public class UserBind extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "商户id")
	@TableField("agent_id")
	private Integer agentId;

	@ApiModelProperty(value = "商户的用户id")
	@TableField("agent_user_id")
	private String agentUserId;

	@ApiModelProperty(value = "用户姓名")
	@TableField("personal_name")
	private String personalName;

	@ApiModelProperty(value = "手机号")
	@TableField("mobile")
	private String mobile;

	@ApiModelProperty(value = "身份证号")
	@TableField("id_card")
	private String idCard;

	@ApiModelProperty(value = "银行卡号")
	@TableField("bank_no")
	private String bankNo;

	@ApiModelProperty(value = "email")
	@TableField("email")
	private String email;

	@ApiModelProperty(value = "returnUrl")
	@TableField("return_url")
	private String returnUrl;

	@ApiModelProperty(value = "notifyUrl")
	@TableField("notify_url")
	private String notifyUrl;

	@ApiModelProperty(value = "timestamp")
	@TableField("timestamp")
	private Long timestamp;

	@ApiModelProperty(value = "绑定的汇付宝id")
	@TableField("bind_code")
	private String bindCode;

	@ApiModelProperty(value = "支付密码")
	@TableField("pay_passwd")
	private String payPasswd;

	@ApiModelProperty(value = "状态")
	@TableField("status")
	private Integer status;

}


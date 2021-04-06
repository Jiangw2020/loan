package jw.srb.core.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 借款信息表
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BorrowInfo对象", description="借款信息表")
public class BorrowInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "借款用户id")
    private Long userId;

    @ApiModelProperty(value = "借款金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "借款期限")
    private Integer period;

    @ApiModelProperty(value = "年化利率")
    private BigDecimal borrowYearRate;

    @ApiModelProperty(value = "还款方式 1-等额本息 2-等额本金 3-每月还息一次还本 4-一次还本")
    private Integer returnMethod;

    @ApiModelProperty(value = "资金用途")
    private Integer moneyUse;

    @ApiModelProperty(value = "状态（0：未提交，1：审核中， 2：审核通过， -1：审核不通过）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除(1:已删除，0:未删除)")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    //扩展字段
    @ApiModelProperty(value = "姓名")
    @TableField(exist = false)
    private String name;

    @ApiModelProperty(value = "手机")
    @TableField(exist = false)
    private String mobile;

    @ApiModelProperty(value = "其他参数")
    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();
}

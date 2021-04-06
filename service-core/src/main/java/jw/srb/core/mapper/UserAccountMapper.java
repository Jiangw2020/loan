package jw.srb.core.mapper;

import jw.srb.core.pojo.entity.UserAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;

/**
 * <p>
 * 用户账户 Mapper 接口
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {

    void updateAccount(String investBindCode, BigDecimal bigDecimal, BigDecimal negate);
}

package jw.srb.core.service;

import jw.srb.core.pojo.entity.UserAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 用户账户 服务类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface UserAccountService extends IService<UserAccount> {


    String commitCharge(BigDecimal chargeAmt, Long userId);

    String notify(Map<String, Object> paramMap);

    BigDecimal getAccount(Long userId);

    String commitWithdraw(BigDecimal fetchAmt, Long userId);

    void notifyWithdraw(Map<String, Object> paramMap);
}

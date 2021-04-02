package com.heepay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heepay.mapper.UserAccountMapper;
import com.heepay.model.UserAccount;
import com.heepay.service.UserAccountService;
import com.heepay.util.BigDecimalUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

	@Resource
	private UserAccountMapper userAccountMapper;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void recharge(Map<String, Object> paramMap) {
		String bindCode = (String)paramMap.get("bindCode");
		String chargeAmt = (String)paramMap.get("chargeAmt");

		UserAccount userAccount = userAccountMapper.selectOne(new QueryWrapper<UserAccount>().eq("user_code", bindCode));
		if(null == userAccount) {
			userAccount =  new UserAccount();
			userAccount.setUserCode(bindCode);
			userAccount.setAmount(chargeAmt);
			userAccount.setFreezeAmount("0");
			userAccountMapper.insert(userAccount);
		} else {
			String amount = BigDecimalUtil.add(userAccount.getAmount(), chargeAmt);
			userAccount.setAmount(amount);
			userAccountMapper.updateById(userAccount);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void withdraw(Map<String, Object> paramMap) {
		String bindCode = (String)paramMap.get("bindCode");
		String fetchAmt = (String)paramMap.get("fetchAmt");

		UserAccount userAccount = userAccountMapper.selectOne(new QueryWrapper<UserAccount>().eq("user_code", bindCode));
		String amount = BigDecimalUtil.subtract(userAccount.getAmount(), fetchAmt);
		userAccount.setAmount(amount);
		userAccountMapper.updateById(userAccount);
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean lockAccount(String userCode, String lockAmt) {
		UserAccount userAccount = this.getByUserCode(userCode);
		String amount = userAccount.getAmount();
		if(Double.parseDouble(amount) < Double.parseDouble(lockAmt)) {
			return false;
		}

		amount = BigDecimalUtil.subtract(amount, lockAmt);
		String freezeAmount = BigDecimalUtil.add(userAccount.getFreezeAmount(), lockAmt);
		userAccount.setAmount(amount);
		userAccount.setFreezeAmount(freezeAmount);
		this.updateById(userAccount);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean unLockAccount(String userCode, String unLockAmt) {
		UserAccount userAccount = this.getByUserCode(userCode);
		String freezeAmount = userAccount.getFreezeAmount();
		if(Double.parseDouble(freezeAmount) < Double.parseDouble(unLockAmt)) {
			return false;
		}

		freezeAmount = BigDecimalUtil.subtract(userAccount.getFreezeAmount(), unLockAmt);
		userAccount.setFreezeAmount(freezeAmount);
		this.updateById(userAccount);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean transferAccounts(String userCode, String amt) {
		UserAccount userAccount = this.getByUserCode(userCode);
		String amount = userAccount.getAmount();

		amount = BigDecimalUtil.add(amount, amt);
		userAccount.setAmount(amount);
		this.updateById(userAccount);
		return false;
	}

	public String getAmount(String userCode) {
		UserAccount userAccount = this.getByUserCode(userCode);
		String amount = userAccount.getAmount();
		return amount;
	}

	public UserAccount getByUserCode(String userCode) {
		return userAccountMapper.selectOne(new QueryWrapper<UserAccount>().eq("user_code", userCode));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean dealAccount(String userCode, String amount, String freezeAmount) {
		UserAccount userAccount = this.getByUserCode(userCode);
		String amount1 = BigDecimalUtil.add(userAccount.getAmount(), amount);
		String freezeAmount1 = BigDecimalUtil.add(userAccount.getFreezeAmount(), freezeAmount);
		userAccount.setAmount(amount1);
		userAccount.setFreezeAmount(freezeAmount1);
		this.updateById(userAccount);
		return false;
	}

}

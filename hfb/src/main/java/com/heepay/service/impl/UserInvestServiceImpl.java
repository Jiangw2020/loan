package com.heepay.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heepay.mapper.UserInvestMapper;
import com.heepay.model.UserInvest;
import com.heepay.model.UserInvestQueryVo;
import com.heepay.service.UserAccountService;
import com.heepay.service.UserInvestService;
import com.heepay.util.HfbException;
import com.heepay.util.ResultCodeEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class UserInvestServiceImpl extends ServiceImpl<UserInvestMapper, UserInvest> implements UserInvestService {

	@Resource
	private UserInvestMapper userInvestMapper;

	@Resource
	private UserAccountService userAccountService;

	@Override
	public IPage<UserInvest> selectPage(Page<UserInvest> pageParam, UserInvestQueryVo userInvestQueryVo) {

		return userInvestMapper.selectPage(pageParam, userInvestQueryVo);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public synchronized UserInvest invest(Map<String, Object> paramMap) {
		UserInvest userInvest = JSONObject.parseObject(JSONObject.toJSONString(paramMap),UserInvest.class);

		Integer count = userInvestMapper.selectCount(new QueryWrapper<UserInvest>().eq("agent_bill_no", userInvest.getAgentBillNo()));
		if(count.intValue() > 0) {
			throw new HfbException(ResultCodeEnum.REPEAT_ERROR);
		}

		//项目投资的总金额
		String voteAmtTotal = userInvestMapper.selectSumVoteAmtByAgentProjectCode(userInvest.getAgentProjectCode());
		voteAmtTotal = StringUtils.isEmpty(voteAmtTotal) ? "0" : voteAmtTotal;
		String curVoteAmtTotal = new BigDecimal(voteAmtTotal).add(new BigDecimal(userInvest.getVoteAmt())).toString();
		if(Double.parseDouble(curVoteAmtTotal) > Double.parseDouble(userInvest.getProjectAmt())) {
			throw new HfbException(ResultCodeEnum.INVEST_AMMOUNT_MORE_ERROR);
		}

		UserInvest userInvest1 = userInvestMapper.selectByAgentProjectCode(userInvest.getAgentProjectCode());
		if(null != userInvest1 && Double.parseDouble(userInvest.getProjectAmt()) != Double.parseDouble(userInvest1.getProjectAmt())) {
			throw new HfbException(ResultCodeEnum.PROJECT_AMMOUNT_ERROR);
		}

		this.save(userInvest);

		userInvest.setResultMsg("成功");
		userInvest.setResultCode("0001");

		userAccountService.lockAccount(userInvest.getVoteBindCode(), userInvest.getVoteAmt());
		return userInvest;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String makeLoan(Map<String, Object> paramMap) {
		String agentProjectCode = (String)paramMap.get("agentProjectCode");

		//项目投资的总金额
		String voteAmtTotal = userInvestMapper.selectSumVoteAmtByAgentProjectCode(agentProjectCode);

		//确保接口调用的幂等性：校验投资信息的状态，如果状态已修改，则直接返回，不进行后续的转账操作
		Integer status = userInvestMapper.selectStatusByAgentProjectCode(agentProjectCode);
		if(null != status && status.intValue() == 1) {
			return voteAmtTotal;
		}

		//借款人绑定协议号
		String benefitBindCode = "";
		List<UserInvest> userInvestList = userInvestMapper.selectList(new QueryWrapper<UserInvest>().eq("agent_project_code", agentProjectCode));
		for(UserInvest userInvest : userInvestList) {
			//投资人绑定协议号
			String voteBindCode = userInvest.getVoteBindCode();
			//借款人绑定协议号
			benefitBindCode = userInvest.getBenefitBindCode();
			//投资金额
			String voteAmt = userInvest.getVoteAmt();

			//解锁投资人金额
			userAccountService.unLockAccount(voteBindCode, voteAmt);
		}

		//给借款人转账：
		//从paramMap中获取平台服务费，然后从转账金额中减去平台的服务费
		BigDecimal mchFee = new BigDecimal((String)paramMap.get("mchFee"));
		BigDecimal total = new BigDecimal(voteAmtTotal).subtract(mchFee);
		userAccountService.transferAccounts(benefitBindCode, total.toString());

		//更新状态
		UserInvest uptUserInvest = new UserInvest();
		uptUserInvest.setStatus(1);
		userInvestMapper.update(uptUserInvest, new QueryWrapper<UserInvest>().eq("agent_project_code", agentProjectCode));

		return total.toString();
	}

	@Override
	public String cancelLend(Map<String, Object> paramMap) {
		String agentProjectCode = (String)paramMap.get("agentProjectCode");

		//项目投资的总金额
		String voteAmtTotal = userInvestMapper.selectSumVoteAmtByAgentProjectCode(agentProjectCode);
		Integer status = userInvestMapper.selectStatusByAgentProjectCode(agentProjectCode);
		if(null != status && status.intValue() == -1) {
			return voteAmtTotal;
		}

		//借款人绑定协议号
		String benefitBindCode = "";
		List<UserInvest> userInvestList = userInvestMapper.selectList(new QueryWrapper<UserInvest>().eq("agent_project_code", agentProjectCode));
		for(UserInvest userInvest : userInvestList) {
			//投资人绑定协议号
			String voteBindCode = userInvest.getVoteBindCode();
			//借款人绑定协议号
			benefitBindCode = userInvest.getBenefitBindCode();
			//投资金额
			String voteAmt = userInvest.getVoteAmt();

			//返回投资人金额
			userAccountService.dealAccount(voteBindCode, voteAmt, "-"+voteAmt);
		}
		return voteAmtTotal;
	}
}

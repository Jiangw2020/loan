package com.heepay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heepay.model.UserInvest;
import com.heepay.model.UserInvestQueryVo;;import java.util.Map;

public interface UserInvestService extends IService<UserInvest> {

	/**
	 * 讲师分页列表
	 * @param userInvestQueryVo
	 * @return
	 */
	IPage<UserInvest> selectPage(Page<UserInvest> pageParam, UserInvestQueryVo userInvestQueryVo);

	UserInvest invest(Map<String, Object> paramMap);

	String makeLoan(Map<String, Object> paramMap);

	String cancelLend(Map<String, Object> paramMap);

}

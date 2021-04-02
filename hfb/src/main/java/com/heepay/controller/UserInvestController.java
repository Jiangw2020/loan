package com.heepay.controller;

import com.heepay.model.NotifyVo;
import com.heepay.model.UserInvest;
import com.heepay.service.UserAccountService;
import com.heepay.service.UserBindService;
import com.heepay.service.UserInvestService;
import com.heepay.task.ScheduledTask;
import com.heepay.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author qy
 *
 */
@CrossOrigin
@Controller
@RequestMapping("/userInvest")
@Slf4j
public class UserInvestController {

	@Resource
	private UserInvestService userInvestService;

	@Resource
	private UserBindService userBindService;

	@Resource
	private UserAccountService userAccountService;

	@Resource
	private ThreadPoolExecutor threadPoolExecutor;

	/**
	 * 投标
	 * @param request
	 * @return
	 */
	@PostMapping("/AgreeUserVoteProject")
	public String  AgreeUserVoteProject(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());
		SignUtil.isSignEquals(paramMap);

		model.addAttribute("paramMap", paramMap);
		model.addAttribute("userBind", userBindService.getByBindCode((String)paramMap.get("voteBindCode")));
		model.addAttribute("userAccount", userAccountService.getByUserCode((String)paramMap.get("voteBindCode")));
		return "userInvest/index";
	}

	/**
	 * 投标
	 * @param request
	 * @return
	 */
	@PostMapping("/invest")
	public String invest(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());

		userBindService.checkPassword((String)paramMap.get("voteBindCode"), request.getParameter("payPasswd"));

		UserInvest userInvest = userInvestService.invest(paramMap);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("resultCode","0001");
		resultMap.put("resultMsg","充值成功");
		resultMap.put("agentBillNo",userInvest.getAgentBillNo());
		resultMap.put("agentProjectCode",userInvest.getAgentProjectCode());
		resultMap.put("projectType",0);
		resultMap.put("voteBindCode",userInvest.getVoteBindCode());
		resultMap.put("voteAmt", new BigDecimal(userInvest.getVoteAmt()));
		resultMap.put("votePrizeAmt",new BigDecimal("0"));
		resultMap.put("voteFeeAmt",new BigDecimal("0"));
		resultMap.put("voteNote","");
		resultMap.put("timestamp",new Date().getTime());
		resultMap.put("sign",SignUtil.getSign(resultMap));

		//异步通知
		ScheduledTask.queue.offer(new NotifyVo((String)paramMap.get("notifyUrl"), resultMap));

		//同步跳转
		//response.sendRedirect(userBind.getReturnUrl());
		model.addAttribute("returnUrl", paramMap.get("returnUrl"));
		return "userInvest/success";
	}

	/**
	 * 放款
	 * @param request
	 * @return
	 */
	@PostMapping("/AgreeAccountLendProject")
	@ResponseBody
	public Map<String, Object>  AgreeAccountLendProject(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());
		SignUtil.isSignEquals(paramMap);
		String voteAmtTotal = userInvestService.makeLoan(paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("resultCode","0000");
		resultMap.put("resultMsg","放款成功");
		resultMap.put("agentProjectCode",paramMap.get("agentProjectCode"));
		resultMap.put("agentBillNo",paramMap.get("agentBillNo"));
		resultMap.put("voteAmt",voteAmtTotal);
		resultMap.put("mchFee",paramMap.get("mchFee"));
		resultMap.put("hyFee","0");

		return resultMap;
	}

	/**
	 * 撤标
	 * @param request
	 * @return
	 */
	@PostMapping("/CancelProject")
	@ResponseBody
	public Map<String, Object>  CancelProject(Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());
		SignUtil.isSignEquals(paramMap);
		String voteAmtTotal = userInvestService.cancelLend(paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("resultCode","0000");
		resultMap.put("resultMsg","撤标成功");
		resultMap.put("agentProjectCode",paramMap.get("agentProjectCode"));
		resultMap.put("voteAmt",voteAmtTotal);
		return resultMap;
	}
}


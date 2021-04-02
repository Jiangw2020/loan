package com.heepay.controller;

import com.heepay.model.NotifyVo;
import com.heepay.service.UserAccountService;
import com.heepay.service.UserBindService;
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
@RequestMapping("/userAccount")
@Slf4j
public class UserAccountController {

	@Resource
	private UserAccountService userAccountService;

	@Resource
	private UserBindService userBindService;

	@Resource
	private ThreadPoolExecutor threadPoolExecutor;

	/**
	 * 充值
	 * @param request
	 * @return
	 */
	@PostMapping("/AgreeBankCharge")
	public String  bind(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());
		SignUtil.isSignEquals(paramMap);

		model.addAttribute("paramMap", paramMap);
		model.addAttribute("userBind", userBindService.getByBindCode((String)paramMap.get("bindCode")));
		return "userAccount/index";
	}

	@PostMapping("/recharge")
	public String recharge(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());

		userBindService.checkPassword((String)paramMap.get("bindCode"), request.getParameter("payPasswd"));

		userAccountService.recharge(paramMap);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("resultCode","0001");
		resultMap.put("resultMsg","充值成功");
		resultMap.put("agentBillNo",paramMap.get("agentBillNo"));
		resultMap.put("bindCode",paramMap.get("bindCode"));
		resultMap.put("chargeAmt", new BigDecimal((String)paramMap.get("chargeAmt")));
		resultMap.put("mchFee",new BigDecimal("0"));
		resultMap.put("hyFee",new BigDecimal("0"));
		resultMap.put("timestamp",new Date().getTime());
		resultMap.put("sign",SignUtil.getSign(resultMap));

		//异步通知
		//threadPoolExecutor.submit(new NotifyThread((String)paramMap.get("notifyUrl"), paramMap));
		ScheduledTask.queue.offer(new NotifyVo((String)paramMap.get("notifyUrl"), resultMap));

		//同步跳转
		//response.sendRedirect(userBind.getReturnUrl());
		model.addAttribute("returnUrl", paramMap.get("returnUrl"));
		return "userAccount/success";
	}

	/**
	 * 提现
	 * @param request
	 * @return
	 */
	@PostMapping("/CashBankManager")
	public String  CashBankManager(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());
		SignUtil.isSignEquals(paramMap);

		model.addAttribute("paramMap", paramMap);
		model.addAttribute("userBind", userBindService.getByBindCode((String)paramMap.get("bindCode")));
		return "withdraw/index";
	}

	@PostMapping("/withdraw")
	public String withdraw(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());

		userBindService.checkPassword((String)paramMap.get("bindCode"), request.getParameter("payPasswd"));

		userAccountService.withdraw(paramMap);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("resultCode","0001");
		resultMap.put("resultMsg","充值成功");
		resultMap.put("agentBillNo",paramMap.get("agentBillNo"));
		resultMap.put("bindCode",paramMap.get("bindCode"));
		resultMap.put("fetchAmt", new BigDecimal((String)paramMap.get("fetchAmt")));
		resultMap.put("mchFee",new BigDecimal("0"));
		resultMap.put("timestamp",new Date().getTime());
		resultMap.put("sign",SignUtil.getSign(resultMap));

		//异步通知
		//threadPoolExecutor.submit(new NotifyThread((String)paramMap.get("notifyUrl"), paramMap));
		ScheduledTask.queue.offer(new NotifyVo((String)paramMap.get("notifyUrl"), resultMap));

		//同步跳转
		//response.sendRedirect(userBind.getReturnUrl());
		model.addAttribute("returnUrl", paramMap.get("returnUrl"));
		return "withdraw/success";
	}
}


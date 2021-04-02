package com.heepay.controller;

import com.heepay.model.NotifyVo;
import com.heepay.service.LendReturnService;
import com.heepay.service.UserAccountService;
import com.heepay.service.UserBindService;
import com.heepay.task.ScheduledTask;
import com.heepay.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author qy
 *
 */
@CrossOrigin
@Controller
@RequestMapping("/lendReturn")
@Slf4j
public class LendReturnController {

	@Resource
	private LendReturnService borrowReturnService;

	@Resource
	private UserBindService userBindService;

	@Resource
	private UserAccountService userAccountService;

	/**
	 * 还款
	 * @param request
	 * @return
	 */
	@PostMapping("/AgreeUserRepayment")
	public String  AgreeUserVoteProject(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());
		SignUtil.isSignEquals(paramMap);

		model.addAttribute("paramMap", paramMap);
		model.addAttribute("userBind", userBindService.getByBindCode((String)paramMap.get("fromBindCode")));
		model.addAttribute("userAccount", userAccountService.getByUserCode((String)paramMap.get("fromBindCode")));
		return "lendReturn/index";
	}

	/**
	 * 还款
	 * @param request
	 * @return
	 */
	@PostMapping("/returnCommit")
	public String returnCommit(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());

		userBindService.checkPassword((String)paramMap.get("fromBindCode"), request.getParameter("payPasswd"));

		Map<String, Object> resultParam = borrowReturnService.returnCommit(paramMap);

		//异步通知
		//threadPoolExecutor.submit(new NotifyThread((String)paramMap.get("notifyUrl"), resultParam));
		ScheduledTask.queue.offer(new NotifyVo((String)paramMap.get("notifyUrl"), resultParam));

		//同步跳转
		//response.sendRedirect(userBind.getReturnUrl());
		model.addAttribute("returnUrl", paramMap.get("returnUrl"));
		return "lendReturn/success";
	}
}


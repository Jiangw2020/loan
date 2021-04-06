package jw.srb.core.controller.api;


import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jw.srb.base.util.JwtUtils;
import jw.srb.common.result.R;
import jw.srb.core.hfb.RequestHelper;
import jw.srb.core.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 用户账户 前端控制器
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
@Api(tags = "会员账户")
@RestController
@RequestMapping("/api/core/userAccount")
@Slf4j
public class UserAccountController {

    @Resource
    private UserAccountService userAccountService;

    @ApiOperation("充值")
    @PostMapping("/auth/commitCharge/{chargeAmt}")
    public R commitCharge(
            @ApiParam(value = "充值金额", required = true)
            @PathVariable BigDecimal chargeAmt, HttpServletRequest request) {

        //获取当前登录用户的id
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        //组装表单字符串，用于远程提交数据
        String formStr = userAccountService.commitCharge(chargeAmt, userId);
        return R.ok().data("formStr", formStr);
    }

    @ApiOperation(value = "用户充值异步回调")
    @PostMapping("/notify")
    public String notify(HttpServletRequest request) {
        Map<String, Object> paramMap = RequestHelper.switchMap(request.getParameterMap());
        log.info("用户充值异步回调：" + JSON.toJSONString(paramMap));

        //验签
        if(RequestHelper.isSignEquals(paramMap)){

            //判断业务是否成功
            if("0001".equals(paramMap.get("resultCode"))){
                //同步账户数据
                return userAccountService.notify(paramMap);
            }else{
                return "success";
            }

        }else{
            return "fail";
        }
    }

    @ApiOperation("查询账户余额")
    @GetMapping("/auth/getAccount")
    public R getAccount(HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        BigDecimal account = userAccountService.getAccount(userId);
        return R.ok().data("account", account);
    }

    @ApiOperation("用户提现")
    @PostMapping("/auth/commitWithdraw/{fetchAmt}")
    public R commitWithdraw(
            @ApiParam(value = "金额", required = true)
            @PathVariable BigDecimal fetchAmt, HttpServletRequest request) {

        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        String formStr = userAccountService.commitWithdraw(fetchAmt, userId);
        return R.ok().data("formStr", formStr);
    }

    @ApiOperation("用户提现异步回调")
    @PostMapping("/notifyWithdraw")
    public String notifyWithdraw(HttpServletRequest request) {
        Map<String, Object> paramMap = RequestHelper.switchMap(request.getParameterMap());
        log.info("提现异步回调：" + JSON.toJSONString(paramMap));

        //校验签名
        if(RequestHelper.isSignEquals(paramMap)) {
            //提现成功交易
            if("0001".equals(paramMap.get("resultCode"))) {
                userAccountService.notifyWithdraw(paramMap);
            } else {
                log.info("提现异步回调充值失败：" + JSON.toJSONString(paramMap));
                return "fail";
            }
        } else {
            log.info("提现异步回调签名错误：" + JSON.toJSONString(paramMap));
            return "fail";
        }
        return "success";
    }

}



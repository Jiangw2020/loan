package jw.srb.core.controller.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jw.srb.common.result.R;
import jw.srb.core.pojo.entity.Lend;
import jw.srb.core.service.LendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的准备表 前端控制器
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
@Api(tags = "标的")
@RestController
@RequestMapping("/api/core/lend")
@Slf4j
public class LendController {

    @Resource
    private LendService lendService;

    @ApiOperation("获取标的列表")
    @GetMapping("/list")
    public R list() {
        List<Lend> lendList = lendService.selectList();
        return R.ok().data("lendList", lendList);
    }

    @ApiOperation("获取标的信息")
    @GetMapping("/show/{id}")
    public R show(
            @ApiParam(value = "标的id", required = true)
            @PathVariable Long id) {
        Map<String, Object> lendDetail = lendService.getLendDetail(id);
        return R.ok().data("lendDetail", lendDetail);
    }

    @ApiOperation("计算投资收益")
    @GetMapping("/getInterestCount/{invest}/{yearRate}/{totalmonth}/{returnMethod}")
    public R getInterestCount(
            @ApiParam(value = "投资金额", required = true)
            @PathVariable("invest") BigDecimal invest,

            @ApiParam(value = "年化收益", required = true)
            @PathVariable("yearRate")BigDecimal yearRate,

            @ApiParam(value = "期数", required = true)
            @PathVariable("totalmonth")Integer totalmonth,

            @ApiParam(value = "还款方式", required = true)
            @PathVariable("returnMethod")Integer returnMethod) {

        BigDecimal  interestCount = lendService.getInterestCount(invest, yearRate, totalmonth, returnMethod);
        return R.ok().data("interestCount", interestCount);
    }
}


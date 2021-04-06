package jw.srb.core.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jw.srb.common.result.R;
import jw.srb.core.pojo.entity.BorrowInfo;
import jw.srb.core.pojo.vo.BorrowInfoApprovalVO;
import jw.srb.core.service.BorrowInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "借款管理")
@RestController
@RequestMapping("/admin/core/borrowInfo")
@Slf4j
public class AdminBorrowInfoController {

    @Resource
    private BorrowInfoService borrowInfoService;

    @ApiOperation("借款信息列表")
    @GetMapping("/list")
    public R list() {
        List<BorrowInfo> borrowInfoList = borrowInfoService.selectList();
        return R.ok().data("list", borrowInfoList);
    }

    @ApiOperation("借款信息详情")
    @GetMapping("/show/{id}")
    public R show(
            @ApiParam(value = "借款信息id", required = true)
            @PathVariable Long id){

        Map<String, Object> borrowInfoDetail = borrowInfoService.getBorrowInfoDetail(id);
        return R.ok().data("borrowInfoDetail", borrowInfoDetail);
    }

    @ApiOperation("审批借款信息")
    @PostMapping("/approval")
    public R approval(@RequestBody BorrowInfoApprovalVO borrowInfoApprovalVO) {

        borrowInfoService.approval(borrowInfoApprovalVO);
        return R.ok().message("审批完成");
    }
}
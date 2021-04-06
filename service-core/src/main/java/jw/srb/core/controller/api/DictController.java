package jw.srb.core.controller.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jw.srb.common.result.R;
import jw.srb.core.pojo.entity.Dict;
import jw.srb.core.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
@Api(tags = "数据字典")
@RestController
@RequestMapping("/api/core/dict")
@Slf4j
public class DictController {

    @Resource
    private DictService dictService;

    @ApiOperation("根据dictCode获取下级节点")
    @GetMapping("/findByDictCode/{dictCode}")
    public R findByDictCode(
            @ApiParam(value ="节点编码", required = true)
            @PathVariable String dictCode){

        List<Dict> list = dictService.findByDictCode(dictCode);
        return R.ok().data("dictList", list);
    }
}


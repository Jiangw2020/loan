package jw.srb.core.mapper;

import jw.srb.core.pojo.dto.ExcelDictDTO;
import jw.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(@Param("list") List<ExcelDictDTO> list);
}

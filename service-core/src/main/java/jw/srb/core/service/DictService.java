package jw.srb.core.service;

import jw.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface DictService extends IService<Dict> {

    void importData(InputStream inputStream);

    List listDictData();

    List<Dict> listByParentId(Long parentId);
}

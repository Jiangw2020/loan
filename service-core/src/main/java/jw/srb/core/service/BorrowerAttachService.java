package jw.srb.core.service;

import jw.srb.core.pojo.entity.BorrowerAttach;
import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.vo.BorrowerAttachVO;

import java.util.List;

/**
 * <p>
 * 借款人上传资源表 服务类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface BorrowerAttachService extends IService<BorrowerAttach> {

    List<BorrowerAttachVO> selectBorrowerAttachVOList(Long id);
}

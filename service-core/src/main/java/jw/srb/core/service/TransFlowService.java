package jw.srb.core.service;

import jw.srb.core.pojo.bo.TransFlowBO;
import jw.srb.core.pojo.entity.TransFlow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 交易流水表 服务类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface TransFlowService extends IService<TransFlow> {

    void saveTransFlow(TransFlowBO investTransFlowBO);
}

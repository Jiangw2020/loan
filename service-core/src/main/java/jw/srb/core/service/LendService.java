package jw.srb.core.service;

import jw.srb.core.pojo.entity.BorrowInfo;
import jw.srb.core.pojo.entity.Lend;
import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.vo.BorrowInfoApprovalVO;

/**
 * <p>
 * 标的准备表 服务类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface LendService extends IService<Lend> {

    void createLend(BorrowInfoApprovalVO borrowInfoApprovalVO, BorrowInfo borrowInfo);
}

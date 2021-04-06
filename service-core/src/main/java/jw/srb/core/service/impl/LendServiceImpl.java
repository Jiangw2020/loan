package jw.srb.core.service.impl;

import jw.srb.core.pojo.entity.BorrowInfo;
import jw.srb.core.pojo.entity.Lend;
import jw.srb.core.mapper.LendMapper;
import jw.srb.core.pojo.vo.BorrowInfoApprovalVO;
import jw.srb.core.service.LendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标的准备表 服务实现类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
@Service
public class LendServiceImpl extends ServiceImpl<LendMapper, Lend> implements LendService {

    @Override
    public void createLend(BorrowInfoApprovalVO borrowInfoApprovalVO, BorrowInfo borrowInfo) {

    }
}

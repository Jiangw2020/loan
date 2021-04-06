package jw.srb.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jw.srb.core.mapper.UserInfoMapper;
import jw.srb.core.pojo.bo.TransFlowBO;
import jw.srb.core.pojo.entity.TransFlow;
import jw.srb.core.mapper.TransFlowMapper;
import jw.srb.core.pojo.entity.UserInfo;
import jw.srb.core.service.TransFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 交易流水表 服务实现类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
@Service
public class TransFlowServiceImpl extends ServiceImpl<TransFlowMapper, TransFlow> implements TransFlowService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public void saveTransFlow(TransFlowBO transFlowBO) {

        String bindCode = transFlowBO.getBindCode();
        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.eq("bind_code", bindCode);
        UserInfo userInfo = userInfoMapper.selectOne(userInfoQueryWrapper);

        TransFlow transFlow = new TransFlow();
        transFlow.setTransAmount(transFlowBO.getAmount());
        transFlow.setMemo(transFlowBO.getMemo());
        transFlow.setTransTypeName(transFlowBO.getTransTypeEnum().getTransTypeName());
        transFlow.setTransType(transFlowBO.getTransTypeEnum().getTransType());
        transFlow.setTransNo(transFlowBO.getAgentBillNo());//流水号
        transFlow.setUserId(userInfo.getId());
        transFlow.setUserName(userInfo.getName());
        baseMapper.insert(transFlow);
    }
    @Override
    public boolean isSaveTransFlow(String agentBillNo) {
        QueryWrapper<TransFlow> transFlowQueryWrapper = new QueryWrapper<>();
        transFlowQueryWrapper.eq("trans_no", agentBillNo);
        Integer count = baseMapper.selectCount(transFlowQueryWrapper);
        return count > 0;
    }
}

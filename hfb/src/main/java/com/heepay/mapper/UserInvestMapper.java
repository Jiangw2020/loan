package com.heepay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heepay.model.UserInvest;
import com.heepay.model.UserInvestQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInvestMapper extends BaseMapper<UserInvest> {

    IPage<UserInvest> selectPage(Page<UserInvest> page, @Param("vo") UserInvestQueryVo userInvestQueryVo);

    /**
     * 根据项目编号获取投资总金额
     * @param agentProjectCode
     * @return
     */
    String selectSumVoteAmtByAgentProjectCode(@Param("agentProjectCode") String agentProjectCode);

    UserInvest selectByAgentProjectCode(@Param("agentProjectCode") String agentProjectCode);

    Integer selectStatusByAgentProjectCode(@Param("agentProjectCode") String agentProjectCode);
}

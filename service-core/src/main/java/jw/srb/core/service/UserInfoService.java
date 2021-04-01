package jw.srb.core.service;

import jw.srb.core.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.vo.LoginVO;
import jw.srb.core.pojo.vo.RegisterVO;
import jw.srb.core.pojo.vo.UserInfoVO;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface UserInfoService extends IService<UserInfo> {

    void register(RegisterVO registerVO);

    UserInfoVO login(LoginVO loginVO, String ip);

    boolean checkMobile(String mobile);
}

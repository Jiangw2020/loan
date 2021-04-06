package jw.srb.core.service;

import jw.srb.core.pojo.entity.UserBind;
import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.vo.UserBindVO;

import java.util.Map;

/**
 * <p>
 * 用户绑定表 服务类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface UserBindService extends IService<UserBind> {

    String commitBindUser(UserBindVO userBindVO, Long userId);

    void notify(Map<String, Object> paramMap);

    String getBindCodeByUserId(Long userId);
}

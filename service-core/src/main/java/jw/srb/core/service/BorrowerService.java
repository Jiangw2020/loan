package jw.srb.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jw.srb.core.pojo.entity.Borrower;
import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.vo.BorrowerApprovalVO;
import jw.srb.core.pojo.vo.BorrowerDetailVO;
import jw.srb.core.pojo.vo.BorrowerVO;

/**
 * <p>
 * 借款人 服务类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface BorrowerService extends IService<Borrower> {

    void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId);

    Integer getStatusByUserId(Long userId);

    IPage<Borrower> listPage(Page<Borrower> pageParam, String keyword);

    BorrowerDetailVO getBorrowerDetailVOById(Long id);

    void approval(BorrowerApprovalVO borrowerApprovalVO);
}

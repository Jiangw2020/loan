package jw.srb.core.hfb;

/**
 * 汇付宝常量定义
 */
public class HfbConst {

    //给商户分配的唯一标识
    public static final String AGENT_ID = "999888";
    //签名key
    public static final String SIGN_KEY = "9876543210";


    /**
     * 用户绑定
     */
    //用户绑定汇付宝平台url地址
    public static final String USERBIND_URL = "http://localhost:9999/userBind/BindAgreeUserV2";
    //用户绑定异步回调
    public static final String USERBIND_NOTIFY_URL = "http://localhost/api/core/userBind/notify";
    //用户绑定同步回调
    public static final String USERBIND_RETURN_URL = "http://localhost:3000/user";

    /**
     * 充值
     */
    //充值汇付宝平台url地址
    public static final String RECHARGE_URL = "http://localhost:9999/userAccount/AgreeBankCharge";
    //充值异步回调
    public static final String RECHARGE_NOTIFY_URL = "http://localhost/api/core/userAccount/notify";
    //充值同步回调
    public static final String RECHARGE_RETURN_URL = "http://localhost:3000/user";

    /**
     * 投标
     */
    //充值汇付宝平台url地址
    public static final String INVEST_URL = "http://localhost:9999/userInvest/AgreeUserVoteProject";
    //充值异步回调
    public static final String INVEST_NOTIFY_URL = "http://localhost/api/core/lendItem/notify";
    //充值同步回调
    public static final String INVEST_RETURN_URL = "http://localhost:3000/user";

    /**
     * 放款
     */
    public static final String MAKE_LOAD_URL = "http://localhost:9999/userInvest/AgreeAccountLendProject";

    /**
     * 提现
     */
    //充值汇付宝平台url地址
    public static final String WITHDRAW_URL = "http://localhost:9999/userAccount/CashBankManager";
    //充值异步回调
    public static final String WITHDRAW_NOTIFY_URL = "http://localhost/api/core/userAccount/notifyWithdraw";
    //充值同步回调
    public static final String WITHDRAW_RETURN_URL = "http://localhost:3000/user";

    /**
     * 还款扣款
     */
    //充值汇付宝平台url地址
    public static final String BORROW_RETURN_URL = "http://localhost:9999/lendReturn/AgreeUserRepayment";
    //充值异步回调
    public static final String BORROW_RETURN_NOTIFY_URL = "http://localhost/api/core/lendReturn/notifyUrl";
    //充值同步回调
    public static final String BORROW_RETURN_RETURN_URL = "http://localhost:3000/user";

}

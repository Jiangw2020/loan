<template>
  <!--信息详细-->
  <div class="item-detail wrap">
    <div class="breadcrumbs">
      <a href="/">首页</a>&gt; <a href="/lend">散标投资列表</a>&gt;
      <span class="cur">项目详情</span>
    </div>
    <div class="item-detail-head clearfix" data-target="sideMenu">
      <div class="hd">
        <i class="icon icon-xin"></i>
        <h2 style="width:70%">{{ lend.title }}</h2>
      </div>

      <!-- 标的信息开始 -->
      <div class="bd clearfix">
        <div class="data" style="width: auto;">
          <ul>
            <li>
              <span class="f16">借款金额</span><br />
              <span class="f30 c-333">{{ lend.amount }}</span>
              元
            </li>
            <li class="relative">
              <span class="f16">年利率</span><br />
              <span class="f30 c-orange">{{ lend.lendYearRate * 100 }}% </span>
            </li>
            <li>
              <span class="f16">借款期限</span><br />
              <span class="f30 c-333">{{ lend.period }}</span>
              个月
            </li>
            <li><span class="c-888">借款编号：</span>{{ lend.lendNo }}</li>
            <li>
              <span class="c-888">借款时间：</span>
              {{ lend.lendStartDate }}至{{ lend.lendEndDate }}
            </li>
            <li>
              <span class="c-888">还款方式：</span>{{ lend.param.returnMethod }}
            </li>
            <li class="colspan" style="line-height: 60px;">
              <span class="c-888 fl">投标进度：</span>
              <div class="progress-bar fl" style="margin-top:26px;">
                <span
                  :style="
                    'width:' + (lend.investAmount / lend.amount) * 100 + '%'
                  "
                ></span>
              </div>
              <span class="c-orange">
                {{ (lend.investAmount / lend.amount) * 100 }}%
              </span>
              <span>
                已有{{ lend.investNum }}人投资{{ lend.investAmount }}元
              </span>
            </li>
          </ul>
        </div>
      </div>
      <!-- 标的信息开始 -->

      <!-- 投资表单开始 -->
      <div v-if="userType === 1 && lend.status === 1" class="bd clearfix">
        <div class="data" style="width: auto;">
          <el-form :inline="true" class="demo-form-inline">
            <el-form-item label="投资金额">
              <el-input
                v-model="invest.investAmount"
                :disabled="lend.status != 1"
                @blur="getInterestCount()"
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
            <el-form-item label="您将获得收益">
              <span class="c-orange">{{ interestCount }}</span>
              元
            </el-form-item>
            <el-form-item>
              <el-checkbox v-model="agree">同意</el-checkbox>
              <span class="orange">
                <a href="#">《出借协议》</a>
              </span>
              <el-button
                type="warning"
                @click="commitInvest"
                :disabled="!agree"
              >
                立即投资
              </el-button>
            </el-form-item>
          </el-form>
          <p>
            您的账户余额 <span class="c-orange">{{ account }}</span> 元，
            <a href="/user/recharge" class="c-888">马上充值</a>
          </p>
        </div>
      </div>
      <!-- 投资表单结束 -->
    </div>

    <!-- 投资记录 -->
    <div class="item-detail-body clearfix mrt30 ui-tab">
      <div class="ui-tab-nav hd">
        <ul>
          <li class="nav_li active">
            <a href="javascript:;">投资记录</a>
          </li>
        </ul>
      </div>
      <div class="bd">
        <div class="ui-tab-item active" style="display: block;">
          <div class="repayment-list">
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
              <tbody>
                <tr>
                  <th>投标人</th>
                  <th>投标金额</th>
                  <th>投标时间</th>
                </tr>
              </tbody>
              <tbody id="repayment_content">
                <tr v-for="lendItem in lendItemList" :key="lendItem.id">
                  <td>{{ lendItem.investName }}</td>
                  <td>
                    <span class="c-orange">￥{{ lendItem.investAmount }}</span>
                  </td>
                  <td>{{ lendItem.investTime }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- 还款计划 -->
    <div class="item-detail-body clearfix mrt30 ui-tab">
      <div class="ui-tab-nav hd">
        <ul>
          <li class="nav_li active">
            <a href="javascript:;">还款计划</a>
          </li>
        </ul>
      </div>
      <div class="bd">
        <div class="ui-tab-item active" style="display: block;">
          <div class="repayment-list">
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
              <thead>
                <tr>
                  <th>还款期数</th>
                  <th>还款日期</th>
                  <th>应还本金(元)</th>
                  <th>应还利息(元)</th>
                  <th>状态</th>
                  <th>是否逾期</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody id="repayment_content">
                <tr v-for="lendReturn in lendReturnList" :key="lendReturn.id">
                  <td>{{ lendReturn.currentPeriod }}</td>
                  <td>{{ lendReturn.returnDate }}</td>
                  <td class="c-orange">￥{{ lendReturn.principal }}</td>
                  <td class="c-orange">￥{{ lendReturn.interest }}</td>
                  <td>{{ lendReturn.status === 0 ? '未还款' : '已还款' }}</td>
                  <td>
                    <span v-if="lendReturn.overdue">
                      是（逾期金额：{{ lendReturn.overdueTotal }}元）
                    </span>
                    <span v-else>否</span>
                  </td>
                  <td>
                    <a href="javascript:" @click="commitReturn(lendReturn.id)">
                      {{ lendReturn.status === 0 ? '还款' : '' }}
                    </a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- 回款计划 -->
    <div v-if="userType === 1" class="item-detail-body clearfix mrt30 ui-tab">
      <div class="ui-tab-nav hd">
        <ul>
          <li class="nav_li active">
            <a href="javascript:;">回款计划</a>
          </li>
        </ul>
      </div>
      <div class="bd">
        <div class="ui-tab-item active" style="display: block;">
          <div class="repayment-list">
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
              <thead>
                <tr>
                  <th>期数</th>
                  <th>本金(元)</th>
                  <th>利息(元)</th>
                  <th>本息(元)</th>
                  <th>计划回款日期</th>
                  <th>实际回款日期</th>
                  <th>状态</th>
                  <th>是否逾期</th>
                </tr>
              </thead>
              <tbody id="repayment_content">
                <tr
                  v-for="lendItemReturn in lendItemReturnList"
                  :key="lendItemReturn.id"
                >
                  <td>{{ lendItemReturn.currentPeriod }}</td>
                  <td class="c-orange">￥{{ lendItemReturn.principal }}</td>
                  <td class="c-orange">￥{{ lendItemReturn.interest }}</td>
                  <td class="c-orange">￥{{ lendItemReturn.total }}</td>
                  <td>{{ lendItemReturn.returnDate }}</td>
                  <td>{{ lendItemReturn.realReturnTime }}</td>
                  <td>
                    {{ lendItemReturn.status === 0 ? '未还款' : '已还款' }}
                  </td>
                  <td>
                    <span v-if="lendItemReturn.overdue">
                      是（逾期金额：{{ lendReturn.overdueTotal }}元）
                    </span>
                    <span v-else>否</span>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- 借款和借款人信息 -->
    <div class="item-detail-body clearfix mrt30 ui-tab">
      <div class="ui-tab-nav hd">
        <ul>
          <li class="nav_li active">
            <a href="javascript:;">借款信息</a>
          </li>
        </ul>
      </div>
      <div class="bd">
        <div class="ui-tab-item active" style="display: block;">
          <div class="borrow-info" style="width:auto;">
            <dl class="item">
              <dt>
                <h3>项目介绍</h3>
              </dt>
              <dd>
                <div class="text">
                  <p class="MsoNormal" style="margin-left:0cm;text-indent:0cm;">
                    {{ lend.lendInfo }}
                  </p>
                </div>
              </dd>
            </dl>

            <dl class="item">
              <dt>
                <h3>借款人信息</h3>
              </dt>
              <dd>
                <div class="text">
                  <p class="MsoNormal" style="margin-left:0cm;text-indent:0cm;">
                    姓名：{{ borrower.name }} <br />
                    手机号码：{{ borrower.mobile }} <br />
                    身份认证：已认证 <br />
                    注册时间：{{ borrower.createTime }} <br />
                  </p>
                </div>
              </dd>
            </dl>

            <dl class="item">
              <dt>
                <h3>审核信息</h3>
              </dt>
              <dd>
                <div class="verify clearfix" style="width: auto;">
                  <ul>
                    <li>
                      <i class="icon icon-4"></i><br />
                      身份证
                    </li>
                    <li>
                      <i class="icon icon-5"></i><br />
                      户口本
                    </li>
                    <li>
                      <i class="icon icon-6"></i><br />
                      结婚证
                    </li>
                    <li>
                      <i class="icon icon-7"></i><br />
                      工作证明
                    </li>
                    <li>
                      <i class="icon icon-8"></i><br />
                      工资卡流水
                    </li>
                    <li>
                      <i class="icon icon-9"></i><br />
                      收入证明
                    </li>
                    <li>
                      <i class="icon icon-10"></i><br />
                      征信报告
                    </li>
                    <li>
                      <i class="icon icon-11"></i><br />
                      亲属调查
                    </li>
                    <li>
                      <i class="icon icon-19"></i><br />
                      行驶证
                    </li>
                    <li>
                      <i class="icon icon-20"></i><br />
                      车辆登记证
                    </li>
                    <li>
                      <i class="icon icon-21"></i><br />
                      车辆登记发票
                    </li>
                    <li>
                      <i class="icon icon-22"></i><br />
                      车辆交强险
                    </li>
                    <li>
                      <i class="icon icon-23"></i><br />
                      车辆商业保险
                    </li>
                    <li>
                      <i class="icon icon-24"></i><br />
                      车辆评估认证
                    </li>
                  </ul>
                </div>
              </dd>
            </dl>
            <dl class="item">
              <dt>
                <h3>风控步骤</h3>
              </dt>
              <dd>
                <div class="text">
                  <p class="MsoNormal" style="margin-left:0cm;text-indent:0cm;">
                    调查：风控部对借款人各项信息进行了全面的电话征信，一切资料真实可靠。<span
                    ></span>
                  </p>
                  <p class="MsoNormal" style="margin-left:0cm;text-indent:0cm;">
                    抵押物：全款长安福特福克斯汽车，车牌号：川<span>AYY***</span>，新车购买于<span>2013</span>年，裸车价<span>14</span>万，评估价<span>5</span>万。
                  </p>
                  <p class="MsoNormal" style="margin-left:0cm;text-indent:0cm;">
                    权证：汽车已入库、已办理相关手续等。
                  </p>
                  <p class="MsoNormal" style="margin-left:0cm;text-indent:0cm;">
                    担保：质押物担保。
                  </p>
                  <p class="MsoNormal" style="margin-left:0cm;text-indent:0cm;">
                    结论：此客户为老客户，上笔贷款<span>4</span>万元，标的号为<span>20200745682</span>，已结清，现因资金周转，再次申请贷款。借款人居住稳定，收入来源可靠，经风控综合评估，同意放款<span>4</span>万。
                  </p>
                  <p class="MsoNormal" style="margin-left:0cm;text-indent:0cm;">
                    保障：借款逾期<span>48</span>小时内，易贷风险准备金先行垫付。
                  </p>
                </div>
                <div class="step clearfix">
                  <ul>
                    <li><i class="icon icon-1"></i>资料审核</li>
                    <li><i class="icon icon-2"></i>实地调查</li>
                    <li><i class="icon icon-3"></i>资产评估</li>
                    <li class="no"><i class="icon icon-4"></i>发布借款</li>
                  </ul>
                </div>
              </dd>
            </dl>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '~/assets/css/index.css'
import '~/assets/css/detail.css'
import cookie from 'js-cookie'

export default {
  async asyncData({ $axios, params }) {
    let lendId = params.id
    let response = await $axios.$get('/api/core/lend/show/' + lendId)
    //投资记录
    let responseLendItemList = await $axios.$get(
      '/api/core/lendItem/list/' + lendId
    )
    //还款计划
    let responseLendReturnList = await $axios.$get(
      '/api/core/lendReturn/list/' + lendId
    )

    return {
      lend: response.data.lendDetail.lend, //标的详情
      borrower: response.data.lendDetail.borrower, //借款人信息
      lendItemList: responseLendItemList.data.list, //投资记录
      lendReturnList: responseLendReturnList.data.list, //还款计划
    }
  },

  data() {
    return {
      account: 0, //账户余额
      agree: false, //是否同意协议
      invest: {
        lendId: 0, //标的id
        investAmount: 100, //投资金额
      },
      interestCount: 0, //将获得收益
      userType: 0, //用户类型
      lendItemReturnList: [], //回款计划
    }
  },

  //此时方法在客户端的浏览器中执行，可以获取到cookie
  mounted() {
    //查询账户余额
    this.fetchAccount()

    //判断登录人的用户类型
    this.fetchUserType()

    //回款计划
    this.fetchLendItemReturnList()
  },

  methods: {
    //查询账户余额
    fetchAccount() {
      let userInfo = cookie.get('userInfo')
      if (userInfo) {
        this.$axios
          .$get('/api/core/userAccount/auth/getAccount')
          .then((response) => {
            this.account = response.data.account
          })
      }
    },

    //获取登录人的用户类型
    fetchUserType() {
      let userInfo = cookie.get('userInfo')
      if (userInfo) {
        userInfo = JSON.parse(userInfo)
        this.userType = userInfo.userType
      }
    },

    //计算收益
    getInterestCount() {
      this.$axios
        .$get(
          `/api/core/lend/getInterestCount/${this.invest.investAmount}/${this.lend.lendYearRate}/${this.lend.period}/${this.lend.returnMethod}`
        )
        .then((response) => {
          this.interestCount = response.data.interestCount
        })
    },

    //投资
    commitInvest() {
      //校验用户是否登录
      let userInfo = cookie.get('userInfo')
      // console.log(typeof userInfo)
      // console.log(!userInfo) //true
      if (!userInfo) {
        console.log('跳转到登录页面')
        window.location.href = '/login'
        return
      }

      //校验当前用户是否是投资人
      let userInfoObj = JSON.parse(userInfo)
      if (userInfoObj.userType == 2) {
        //借款人
        this.$message.error('借款人无法投资')
        return
      }

      //判断标的是否超卖：标的已投金额 + 本次投资金额 > 标的总金额
      if (
        this.lend.investAmount + Number(this.invest.investAmount) >
        this.lend.amount
      ) {
        this.$message.error('标的可投资金额不足')
        return
      }

      //是否是100的整数倍
      // console.log(this.invest.investAmount)
      // console.log(Number(this.invest.investAmount))
      // console.log(typeof Number(this.invest.investAmount))
      // return
      if (
        Number(this.invest.investAmount) === 0 ||
        this.invest.investAmount % this.lend.lowestAmount != 0
      ) {
        this.$message.error(`投资金额必须是${this.lend.lowestAmount}的整数倍`)
        return
      }

      //余额的判断
      if (this.invest.investAmount > this.account) {
        this.$message.error('余额不足，请充值')
        return
      }

      //数据提交
      this.$alert(
        '<div style="size: 18px;color: red;">您即将前往汇付宝确认标的</div>',
        '前往汇付宝资金托管平台',
        {
          dangerouslyUseHTMLString: true,
          confirmButtonText: '立即前往',
          callback: (action) => {
            console.log('action', action)
            if (action === 'confirm') {
              this.invest.lendId = this.lend.id
              this.$axios
                .$post('/api/core/lendItem/auth/commitInvest', this.invest)
                .then((response) => {
                  // console.log(response.data.formStr)
                  // debugger
                  document.write(response.data.formStr)
                })
            }
          },
        }
      )
    },

    //回款计划
    fetchLendItemReturnList() {
      this.$axios
        .$get('/api/core/lendItemReturn/list/' + this.$route.params.id)
        .then((response) => {
          this.lendItemReturnList = response.data.list
        })
    },

    commitReturn(lendReturnId) {
      this.$alert(
        '<div style="size: 18px;color: red;">您即将前往汇付宝确认还款</div>',
        '前往汇付宝资金托管平台',
        {
          dangerouslyUseHTMLString: true,
          confirmButtonText: '立即前往',
          callback: (action) => {
            if (action === 'confirm') {
              this.$axios
                .$post('/api/core/lendReturn/auth/commitReturn/' + lendReturnId)
                .then((response) => {
                  document.write(response.data.formStr)
                })
            }
          },
        }
      )
    },
  },
}
</script>

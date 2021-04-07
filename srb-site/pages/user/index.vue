<template>
  <div class="personal-main">
    <div class="pmain-profile">
      <div class="pmain-welcome">
        <span class="fr">上次登录时间： {{ userIndexVO.lastLoginTime }} </span>
      </div>
      <div class="pmain-user">
        <div class="user-head">
          <span class="head-img">
            <span>
              <img
                :src="userIndexVO.headImg"
                style="width:88px;height:88px;z-index:0;"
              />
              <i class="headframe" style="z-index:0;"></i>
            </span>
          </span>
        </div>
        <div class="user-info">
          <ul>
            <li>
              用户名<span>{{ userIndexVO.name }}</span>
              <NuxtLink
                v-if="
                  userIndexVO.userType === 2 && userIndexVO.bindStatus === 1
                "
                to="/user/borrower"
              >
                立即借款
              </NuxtLink>
            </li>
            <li v-if="userIndexVO.bindStatus !== 1">
              您还未开通第三方支付账户，请
              <NuxtLink to="/user/bind">立即开通</NuxtLink>
              以确保您的正常使用和资金安全。
            </li>
          </ul>
        </div>
      </div>

      <div v-if="userIndexVO.bindStatus === 1" class="pmain-money">
        <ul>
          <li class="none">
            <span>
              <em>账户余额</em>
              <i class="markicon"></i>
            </span>
            <span class="truemoney">
              <i class="f26 fb">{{ userIndexVO.amount }}</i> 元
            </span>
          </li>
          <li>
            <span>
              <em>冻结金额</em>
              <i class="markicon"></i>
            </span>
            <span class="truemoney">
              <i class="f26 fb">{{ userIndexVO.freezeAmount }}</i
              >元
            </span>
          </li>
          <li>
            <NuxtLink to="/user/recharge">
              <el-button size="mini" type="danger">充值</el-button>
            </NuxtLink>
            <NuxtLink to="/user/withdraw">
              <el-button size="mini" type="success">提现</el-button>
            </NuxtLink>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userIndexVO: {},
    }
  },

  created() {
    this.fetchUserData()
  },

  methods: {
    fetchUserData() {
      this.$axios
        .$get('/api/core/userInfo/auth/getIndexUserInfo')
        .then((response) => {
          this.userIndexVO = response.data.userIndexVO
        })
    },
  },
}
</script>

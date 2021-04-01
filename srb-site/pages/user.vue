<template>
  <!--个人中心-->
  <div class="wrapper wbgcolor">
    <div class="w1200 personal">
      <div class="credit-ad">
        <img src="~/assets/images/clist1.jpg" width="1200" height="96" />
      </div>
      <div class="personal-left">
        <InvestorNav v-if="userType === 1" />
        <BorrowerNav v-if="userType === 2" />
      </div>
      <NuxtChild />
      <div class="clear"></div>
    </div>
  </div>
</template>

<script>
import '~/assets/css/user.css'
import InvestorNav from '~/components/InvestorNav'
import BorrowerNav from '~/components/BorrowerNav'
import cookie from 'js-cookie'

export default {
  components: {
    InvestorNav,
    BorrowerNav,
  },

  data() {
    return {
      userType: 0,
    }
  },

  mounted() {
    let userInfo = cookie.get('userInfo')
    if (userInfo) {
      userInfo = JSON.parse(userInfo)
      this.userType = userInfo.userType
    }
  },
}
</script>

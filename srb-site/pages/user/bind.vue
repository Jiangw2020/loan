<template>
  <div class="personal-main">
    <div class="personal-pay">
      <h3><i>开通第三方账户</i></h3>
      <div class="pay-notice">
        <p>
          请开通汇付宝存管账户以便于您正常理财
        </p>
      </div>
      <div class="pay-form">
        <ul>
          <li>
            <label>真实姓名</label>
            <input
              v-model="userBind.name"
              type="text"
              class="pay-txt"
              maxlength="16"
              placeholder="您的真实姓名"
            />
          </li>
          <li>
            <label>身份证号</label>
            <input
              v-model="userBind.idCard"
              type="text"
              class="pay-txt"
              maxlength="18"
              placeholder="您的身份证号"
            />
            <div id="idCardErrorDiv">
              <p style="margin-top:10px;">
                身份证信息认证后将不可修改，请您仔细填写
              </p>
            </div>
          </li>
          <li>
            <label>绑定银行</label>
            <input
              v-model="userBind.bankType"
              type="text"
              class="pay-txt"
              placeholder="银行名称"
            />
          </li>
          <li>
            <label>银行卡号</label>
            <input
              v-model="userBind.bankNo"
              type="text"
              class="pay-txt"
              placeholder="本人持有的银行卡"
            />
          </li>
          <li>
            <label>预留手机</label>
            <input
              v-model="userBind.mobile"
              type="text"
              class="pay-txt"
              placeholder="银行卡预留手机号"
            />
          </li>
          <li>
            <label>&nbsp;</label>
            <input v-model="agree" type="checkbox" />
            我已阅读并同意
            <a href="#" class="c-orange" target="_blank">
              《汇付宝托管账户协议》
            </a>
          </li>
          <li>
            <label>&nbsp;</label>
            <el-button :disabled="!agree" @click="commitBind()" type="primary">
              开户
            </el-button>
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
      agree: false,
      userBind: {},
    }
  },

  methods: {
    commitBind() {
      this.$alert(
        '<div style="size: 18px;color: red;">您即将前往汇付宝绑定账号</div>',
        '前往汇付宝资金托管平台',
        {
          dangerouslyUseHTMLString: true,
          confirmButtonText: '立即前往',
          callback: (action) => {
            //TODO 提交用户信息
            console.log('action', action)
            if (action === 'confirm') {
              this.$axios
                .$post('/api/core/userBind/auth/bind', this.userBind)
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

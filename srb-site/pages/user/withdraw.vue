<template>
  <div class="personal-main">
    <div class="personal-deposit">
      <h3><i>提现</i></h3>

      <div
        class="deposit-form"
        style="margin-top:0px;border-top:0px none;width:auto;height:240px;"
      >
        <h6>填写提现金额</h6>
        <ul>
          <li>
            <span class="deposit-formleft">提现金额</span>
            <span class="deposit-formright">
              <input
                v-model="fetchAmt"
                type="text"
                class="deposite-txt"
                maxlength="10"
              />
              元
            </span>
          </li>
          <li>
            <span class="deposit-formleft">提现费用</span>
            <em id="txfy" class="markicon fl"></em>
            <span class="deposit-formright deposit-formright1">
              <i> <label id="form:fee"> 0.00</label> </i>元
            </span>
          </li>
          <li>
            <input
              type="submit"
              value="马上提现"
              class="btn-depositok"
              @click="commitWithdraw()"
            />
          </li>
        </ul>
      </div>
      <div class="deposit-tip" style="height: 110px;line-height: 24px;">
        <b>温馨提示：</b><br />
        1、为了您的资金安全，您的账户资金由第三方汇付宝进行托管。<br />
        2、充值前请注意您的银行卡充值限额，以免造成不便。<br />
        3、为了您的资金安全，建议充值前进行实名认证。<br />
        4、如果充值遇到任何问题，请联系客服：4006-000-000。
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      fetchAmt: 0,
    }
  },

  methods: {
    commitWithdraw() {
      this.$alert(
        '<div style="size: 18px;color: red;">您即将前往汇付宝提现</div>',
        '前往汇付宝资金托管平台',
        {
          dangerouslyUseHTMLString: true,
          confirmButtonText: '立即前往',
          callback: (action) => {
            if (action === 'confirm') {
              //提现
              this.$axios
                .$post(
                  '/api/core/userAccount/auth/commitWithdraw/' + this.fetchAmt
                )
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

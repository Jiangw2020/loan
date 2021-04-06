<template>
  <div class="personal-main">
    <div class="personal-pay">
      <h3><i>借款申请</i></h3>

      <el-steps :active="active" style="margin: 40px">
        <el-step title="提交借款信息"></el-step>
        <el-step title="审核"></el-step>
        <el-step title="等待审核结果"></el-step>
      </el-steps>

      <div v-if="active === 0" class="user-borrower">
        <el-form label-width="120px">
          <el-form-item label="借款金额">
            <el-col :span="6">
              <el-input v-model="borrowInfo.amount" />
            </el-col>
            <el-col :span="6">
              &nbsp;&nbsp;您最多可借款{{ borrowAmount }}元
            </el-col>
          </el-form-item>

          <el-form-item label="期数">
            <el-select v-model="borrowInfo.period">
              <el-option :value="1" label="1个月" />
              <el-option :value="3" label="3个月" />
              <el-option :value="6" label="6个月" />
              <el-option :value="9" label="9个月" />
              <el-option :value="12" label="12个月" />
              <el-option :value="24" label="24个月" />
            </el-select>
          </el-form-item>

          <el-form-item label="还款方式">
            <el-select v-model="borrowInfo.returnMethod">
              <el-option
                v-for="item in returnMethodList"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="资金用途">
            <el-select v-model="borrowInfo.moneyUse">
              <el-option
                v-for="item in moneyUseList"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="年利率">
            <el-col :span="6">
              <el-input v-model="borrowInfo.borrowYearRate">
                <template slot="append">%</template>
              </el-input>
            </el-col>
            <el-col :span="8">
              &nbsp;&nbsp;年利率越高，借款越容易成功。
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              :disabled="submitBtnDisabled"
              @click="save"
            >
              提交
            </el-button>
          </el-form-item>
        </el-form>
        <el-alert
          title="您提供的任何信息尚融宝都承诺予以保护，不会挪作他用。"
          type="warning"
          :closable="false"
        >
        </el-alert>
      </div>

      <div v-if="active === 1">
        <div style="margin-top:40px;">
          <el-alert
            title="您的借款申请已成功提交，请耐心等待"
            type="warning"
            show-icon
            :closable="false"
          >
            我们将在10分钟内完成审核，审核时间为周一至周五8:00至20:00。
          </el-alert>
        </div>
      </div>

      <div v-if="active === 2">
        <div style="margin-top:40px;">
          <el-alert
            v-if="borrowInfoStatus === 2"
            title="您的借款申请审批已通过"
            type="success"
            show-icon
            :closable="false"
          >
          </el-alert>

          <el-alert
            v-if="borrowInfoStatus === -1"
            title="您的借款申请审批未通过"
            type="error"
            show-icon
            :closable="false"
          >
          </el-alert>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      active: null, //步骤
      borrowInfoStatus: null, //审批状态
      //借款申请
      borrowInfo: {
        borrowYearRate: '12',
      },
      borrowAmount: 0, //借款额度
      submitBtnDisabled: false,
      returnMethodList: [], //还款方式列表
      moneyUseList: [], //资金用途列表
    }
  },

  watch: {
    'borrowInfo.amount'(value) {
      if (value > this.borrowAmount) {
        let _this = this
        this.$alert('您的借款额度不足', {
          type: 'error',
          callback() {
            _this.borrowInfo.amount = _this.borrowAmount
          },
        })
      }
    },
  },

  created() {
    this.getBorrowInfoStatus()
  },

  methods: {
    getBorrowInfoStatus() {
      this.$axios
        .$get('/api/core/borrowInfo/auth/getBorrowInfoStatus')
        .then((response) => {
          this.borrowInfoStatus = response.data.borrowInfoStatus
          if (this.borrowInfoStatus === 0) {
            //未认证
            this.active = 0

            //获取借款额度
            this.getBorrowAmount()

            //初始化下拉列表
            this.initSelected()
          } else if (this.borrowInfoStatus === 1) {
            //审批中
            this.active = 1
          } else if (this.borrowInfoStatus === 2) {
            //审批成功
            this.active = 2
          } else if (this.borrowInfoStatus === -1) {
            //审批失败
            this.active = 2
          }
        })
    },

    //初始化下拉列表的数据
    initSelected() {
      //还款方式列表
      this.$axios
        .$get('/api/core/dict/findByDictCode/returnMethod')
        .then((response) => {
          this.returnMethodList = response.data.dictList
        })

      //资金用途列表
      this.$axios
        .$get('/api/core/dict/findByDictCode/moneyUse')
        .then((response) => {
          this.moneyUseList = response.data.dictList
        })
    },

    getBorrowAmount() {
      this.$axios
        .$get('/api/core/borrowInfo/auth/getBorrowAmount')
        .then((response) => {
          this.borrowAmount = response.data.borrowAmount
        })
    },

    //提交借款申请
    save() {
      this.$axios
        .$post('/api/core/borrowInfo/auth/save', this.borrowInfo)
        .then((response) => {
          this.active = 1
        })
    },
  },
}
</script>

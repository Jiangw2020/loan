<template>
  <div class="app-container">
    <h4>借款信息</h4>
    <table
      class="table table-striped table-condenseda table-bordered"
      width="100%"
    >
      <tbody>
        <tr>
          <th width="15%">借款金额</th>
          <td width="35%">{{ borrowInfoDetail.borrowInfo.amount }}元</td>
          <th width="15%">借款期限</th>
          <td width="35%">{{ borrowInfoDetail.borrowInfo.period }}个月</td>
        </tr>
        <tr>
          <th>年化利率</th>
          <td>{{ borrowInfoDetail.borrowInfo.borrowYearRate * 100 }}%</td>
          <th>还款方式</th>
          <td>{{ borrowInfoDetail.borrowInfo.param.returnMethod }}</td>
        </tr>

        <tr>
          <th>资金用途</th>
          <td>{{ borrowInfoDetail.borrowInfo.param.moneyUse }}</td>
          <th>状态</th>
          <td>{{ borrowInfoDetail.borrowInfo.param.status }}</td>
        </tr>
        <tr>
          <th>创建时间</th>
          <td>{{ borrowInfoDetail.borrowInfo.createTime }}</td>
          <th></th>
          <td></td>
        </tr>
      </tbody>
    </table>

    <h4>借款人信息</h4>
    <table
      class="table table-striped table-condenseda table-bordered"
      width="100%"
    >
      <tbody>
        <tr>
          <th width="15%">借款人</th>
          <td width="35%">
            <b>{{ borrowInfoDetail.borrower.name }}</b>
          </td>
          <th width="15%">手机</th>
          <td width="35%">{{ borrowInfoDetail.borrower.mobile }}</td>
        </tr>
        <tr>
          <th>身份证</th>
          <td>{{ borrowInfoDetail.borrower.idCard }}</td>
          <th>性别</th>
          <td>{{ borrowInfoDetail.borrower.sex }}</td>
        </tr>
        <tr>
          <th>年龄</th>
          <td>{{ borrowInfoDetail.borrower.age }}</td>
          <th>是否结婚</th>
          <td>{{ borrowInfoDetail.borrower.marry }}</td>
        </tr>
        <tr>
          <th>学历</th>
          <td>{{ borrowInfoDetail.borrower.education }}</td>
          <th>行业</th>
          <td>{{ borrowInfoDetail.borrower.industry }}</td>
        </tr>
        <tr>
          <th>月收入</th>
          <td>{{ borrowInfoDetail.borrower.income }}</td>
          <th>还款来源</th>
          <td>{{ borrowInfoDetail.borrower.returnSource }}</td>
        </tr>
        <tr>
          <th>创建时间</th>
          <td>{{ borrowInfoDetail.borrower.createTime }}</td>
          <th>状态</th>
          <td>{{ borrowInfoDetail.borrower.status }}</td>
        </tr>
      </tbody>
    </table>

    <el-row style="text-align:center;margin-top: 40px;">
      <el-button @click="back">
        返回
      </el-button>
    </el-row>
  </div>
</template>

<script>
import borrowInfoApi from '@/api/core/borrow-info'
import '@/styles/show.css'

export default {
  data() {
    return {
      borrowInfoDetail: {
        borrowInfo: {
          param: {}
        },
        borrower: {}
      }
    }
  },

  created() {
    if (this.$route.params.id) {
      this.fetchDataById()
    }
  },

  methods: {
    fetchDataById() {
      borrowInfoApi.show(this.$route.params.id).then(response => {
        this.borrowInfoDetail = response.data.borrowInfoDetail
      })
    },

    back() {
      this.$router.push('/core/borrower/info-list')
    }
  }
}
</script>

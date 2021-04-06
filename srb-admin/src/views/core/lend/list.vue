<template>
  <div class="app-container">
    <!-- 列表 -->
    <el-table :data="list" stripe>
      <el-table-column type="index" label="序号" width="60" align="center" />
      <el-table-column prop="lendNo" label="标的编号" width="160" />
      <el-table-column prop="amount" label="标的金额" />
      <el-table-column prop="period" label="投资期数" />
      <el-table-column label="年化利率">
        <template slot-scope="scope">
          {{ scope.row.lendYearRate * 100 }}%
        </template>
      </el-table-column>
      <el-table-column prop="investAmount" label="已投金额" />
      <el-table-column prop="investNum" label="投资人数" />
      <el-table-column prop="publishDate" label="发布时间" width="150" />
      <el-table-column prop="lendStartDate" label="开始日期" />
      <el-table-column prop="lendEndDate" label="结束日期" />
      <el-table-column prop="param.returnMethod" label="还款方式" />
      <el-table-column prop="param.status" label="状态" />

      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="mini">
            <router-link :to="'/core/lend/detail/' + scope.row.id">
              查看
            </router-link>
          </el-button>

          <el-button
            v-if="scope.row.status == 1"
            type="warning"
            size="mini"
            @click="makeLoan(scope.row.id)"
          >
            放款
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import lendApi from '@/api/core/lend'

export default {
  data() {
    return {
      list: null // 列表
    }
  },

  created() {
    this.fetchData()
  },

  methods: {
    // 加载列表数据
    fetchData() {
      lendApi.getList().then(response => {
        this.list = response.data.list
      })
    },

    makeLoan(id) {
      this.$confirm('确定放款吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          return lendApi.makeLoan(id)
        })
        .then(response => {
          //放款成功则重新获取数据列表
          this.fetchData()
          this.$message({
            type: 'success',
            message: response.message
          })
        })
        .catch(error => {
          console.log('取消', error)
          if (error === 'cancel') {
            this.$message({
              type: 'info',
              message: '已取消放款'
            })
          }
        })
    }
  }
}
</script>

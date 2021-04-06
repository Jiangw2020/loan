<template>
  <div class="personal-main">
    <div class="personal-pay">
      <h3><i>借款人信息认证</i></h3>

      <el-steps :active="active" style="margin: 40px">
        <el-step title="填写借款人信息"></el-step>
        <el-step title="提交平台审核"></el-step>
        <el-step title="等待认证结果"></el-step>
      </el-steps>

      <div v-if="active === 0" class="user-borrower">
        <h6>个人基本信息</h6>
        <el-form label-width="120px">
          <el-form-item label="年龄">
            <el-col :span="5">
              <el-input v-model="borrower.age" />
            </el-col>
          </el-form-item>

          <el-form-item label="性别">
            <el-select v-model="borrower.sex">
              <el-option :value="1" :label="'男'" />
              <el-option :value="0" :label="'女'" />
            </el-select>
          </el-form-item>
          <el-form-item label="婚否">
            <el-select v-model="borrower.marry">
              <el-option :value="true" :label="'是'" />
              <el-option :value="false" :label="'否'" />
            </el-select>
          </el-form-item>
          <el-form-item label="学历">
            <el-select v-model="borrower.education">
              <el-option
                v-for="item in educationList"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="行业">
            <el-select v-model="borrower.industry">
              <el-option
                v-for="item in industryList"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="月收入">
            <el-select v-model="borrower.income">
              <el-option
                v-for="item in incomeList"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="还款来源">
            <el-select v-model="borrower.returnSource">
              <el-option
                v-for="item in returnSourceList"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
        </el-form>

        <h6>联系人信息</h6>
        <el-form label-width="120px">
          <el-form-item label="联系人姓名">
            <el-col :span="5">
              <el-input v-model="borrower.contactsName" />
            </el-col>
          </el-form-item>
          <el-form-item label="联系人手机">
            <el-col :span="5">
              <el-input v-model="borrower.contactsMobile" />
            </el-col>
          </el-form-item>
          <el-form-item label="联系人关系">
            <el-select v-model="borrower.contactsRelation">
              <el-option
                v-for="item in contactsRelationList"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
        </el-form>

        <h6>身份认证信息</h6>
        <el-form label-width="120px">
          <el-form-item label="身份证人像面">
            <el-upload
              :on-success="onUploadSuccessIdCard1"
              :on-remove="onUploadRemove"
              :multiple="false"
              :action="uploadUrl"
              :data="{ module: 'idCard1' }"
              :limit="1"
              list-type="picture-card"
            >
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="身份证国徽面">
            <el-upload
              :on-success="onUploadSuccessIdCard2"
              :on-remove="onUploadRemove"
              :multiple="false"
              :action="uploadUrl"
              :data="{ module: 'idCard2' }"
              :limit="1"
              list-type="picture-card"
            >
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
        </el-form>

        <h6>其他信息</h6>
        <el-form label-width="120px">
          <el-form-item label="房产信息">
            <el-upload
              :on-success="onUploadSuccessHouse"
              :on-remove="onUploadRemove"
              :multiple="false"
              :action="uploadUrl"
              :data="{ module: 'house' }"
              list-type="picture-card"
            >
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="车辆信息">
            <el-upload
              :on-success="onUploadSuccessCar"
              :on-remove="onUploadRemove"
              :multiple="false"
              :action="uploadUrl"
              :data="{ module: 'car' }"
              list-type="picture-card"
            >
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
        </el-form>

        <el-form label-width="120px">
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
      </div>

      <div v-if="active === 1">
        <div style="margin-top:40px;">
          <el-alert
            title="您的认证申请已成功提交，请耐心等待"
            type="warning"
            show-icon
            :closable="false"
          >
            我们将在2小时内完成审核，审核时间为周一至周五8:00至20:00。
          </el-alert>
        </div>
      </div>

      <div v-if="active === 2">
        <div style="margin-top:40px;">
          <el-alert
            v-if="borrowerStatus === 2"
            title="您的认证审批已通过"
            type="success"
            show-icon
            :closable="false"
          >
          </el-alert>
          <NuxtLink to="/user/apply" v-if="borrowerStatus === 2">
            <el-button style="margin-top:20px;" type="success">
              我要借款
            </el-button>
          </NuxtLink>

          <el-alert
            v-if="borrowerStatus === -1"
            title="您的认证审批未通过"
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
    let BASE_API = process.env.BASE_API

    return {
      active: null, //步骤
      borrowerStatus: null,
      submitBtnDisabled: false,
      //借款人信息
      borrower: {
        borrowerAttachList: [], //附件列表
      },
      educationList: [], //学历列表
      industryList: [], //行业列表
      incomeList: [], //月收入列表
      returnSourceList: [], //还款来源列表
      contactsRelationList: [], //联系人关系
      uploadUrl: BASE_API + '/api/oss/file/upload', //文件上传地址
    }
  },

  created() {
    this.getUserInfo()
  },

  methods: {
    getUserInfo() {
      this.$axios
        .$get('/api/core/borrower/auth/getBorrowerStatus')
        .then((response) => {
          this.borrowerStatus = response.data.borrowerStatus

          if (this.borrowerStatus === 0) {
            //未认证
            this.active = 0
            this.initSelected()
          } else if (this.borrowerStatus === 1) {
            //认证中
            this.active = 1
            // } else if (this.borrowerStatus === 2) {
            //   this.active = 2
            // } else if (this.borrowerStatus === -1) {
            //   this.active = 2
            // }
          } else {
            this.active = 2
          }
        })
    },

    initSelected() {
      //学历列表
      this.$axios
        .$get('/api/core/dict/findByDictCode/education')
        .then((response) => {
          this.educationList = response.data.dictList
        })

      //行业列表
      this.$axios
        .$get('/api/core/dict/findByDictCode/industry')
        .then((response) => {
          this.industryList = response.data.dictList
        })

      //收入列表
      this.$axios
        .$get('/api/core/dict/findByDictCode/income')
        .then((response) => {
          this.incomeList = response.data.dictList
        })

      //还款来源列表
      this.$axios
        .$get('/api/core/dict/findByDictCode/returnSource')
        .then((response) => {
          this.returnSourceList = response.data.dictList
        })

      //联系人关系列表
      this.$axios
        .$get('/api/core/dict/findByDictCode/relation')
        .then((response) => {
          this.contactsRelationList = response.data.dictList
        })
    },

    save() {
      this.submitBtnDisabled = true
      this.$axios
        .$post('/api/core/borrower/auth/save', this.borrower)
        .then((response) => {
          this.active = 1
        })
    },

    onUploadSuccessIdCard1(response, file) {
      this.onUploadSuccess(response, file, 'idCard1')
    },

    onUploadSuccessIdCard2(response, file) {
      this.onUploadSuccess(response, file, 'idCard2')
    },

    onUploadSuccessHouse(response, file) {
      this.onUploadSuccess(response, file, 'house')
    },

    onUploadSuccessCar(response, file) {
      this.onUploadSuccess(response, file, 'car')
    },

    onUploadSuccess(response, file, type) {
      if (response.code === 0) {
        //业务成功，填充borrower.borrowerAttachList列表
        this.borrower.borrowerAttachList.push({
          imageName: file.name,
          imageUrl: response.data.url,
          imageType: type,
        })
      } else {
        //业务失败
        this.$message.error(response.message)
      }
    },

    onUploadRemove(file, fileList) {
      console.log('onUploadRemove file', file)
      console.log('onUploadRemove fileList', fileList)
      //调用远程文件删除接口
      this.$axios
        .$delete('/api/oss/file/remove?url=' + file.response.data.url)
        .then((response) => {
          console.log('远程删除文件成功')
          //从this.borrower.borrowerAttachList列表中删除对象
          this.borrower.borrowerAttachList = this.borrower.borrowerAttachList.filter(
            function(item) {
              return item.imageUrl != file.response.data.url
            }
          )
        })
    },
  },
}
</script>

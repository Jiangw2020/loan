import request from '@/utils/request'

export default {
  getList(lendId) {
    return request({
      url: `/admin/core/lendReturn/list/` + lendId,
      method: 'get'
    })
  }
}

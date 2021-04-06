import request from '@/utils/request'

export default {
  getList(lendId) {
    return request({
      url: `/admin/core/lendItem/list/` + lendId,
      method: 'get'
    })
  }
}

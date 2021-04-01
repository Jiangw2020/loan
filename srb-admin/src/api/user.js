import request from '@/utils/request'

export function login(data) {
  return request({
    baseURL: '/dev-api',
    url: '/vue-admin-template/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    baseURL: '/dev-api',
    url: '/vue-admin-template/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    baseURL: '/dev-api',
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}

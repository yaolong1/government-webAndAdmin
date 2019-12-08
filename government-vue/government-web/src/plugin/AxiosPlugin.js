require('es6-promise').polyfill();
import axios from 'axios';
import router from '../router'

// 创建axios实例
// 这里export de原因是方便组件外使用

export const Axios = axios.create({
  baseURL: 'http://47.94.81.96:8088',
  timeout: 5000
});




//POST传参序列化(添加请求拦截器)

// 在发送请求之前做某件事
Axios.interceptors.request.use(config =>{

  if( localStorage.TokenInfo){
    config.headers.Authorization = 'Bearer'+ localStorage.TokenInfo;
  }
  return config;

  // 设置以 form 表单的形式提交参数，如果以 JSON 的形式提交表单，可忽略
  if (config.method === 'post'){

    const formData = new FormData();
    Object.keys(config.data).forEach(key => formData.append(key, config.data[key]));
    config.data = formData
  }

  // // 下面会说在什么时候存储 token
  // if (localStorage.token) {
  //   config.headers.Authorization = 'JWT ' + localStorage.token
  // }
  // return config



},error => {
  alert("错误的传参",'fail')
  return Promise.reject(error)
});


//返回状态判断（添加响应拦截器）

Axios.interceptors.response.use(res =>{

  // 状态码不对跳转登录页
  if(res.data.status === 1001){
    router.push({
      //跳转的登录页后，登录成功返回上一个页面 在登录页中，判断如果登录成功，写上这句话 this.$router.go(-1)
      path: '/login',
      // 状态码不对跳转登录页的时候需要传一个域名在登录页判断
      //为防止好友分享的登录页面
      query:{redirect:location.hostname}
      /**
       * 这样在登录页就拿到了上个页面的location.hostname了。然后在登录页判断一下
       * 在登录处理页面写上
       *
       * 域名相同代表是在同一个项目中，不同就是别人分享的
       *  if（this.$route.query.redirect == location.hostname）{
       *    this.$router.go(-1)
       *  }else{
       *    this.$router.push({path:"/主页"})
       *  }
       */
    })
  }

  return res;
  //对响应数据做的事情
  if(!res.data.success){
    alert(res.status);
    return Promise.reject(res)
  }

  return res;
},error =>{

  if(error.response.status === 401 || error.response.status === 403) {
    // 401 说明 token 验证失败
    // 可以直接跳转到登录页面，重新登录获取 token
    // location.href = '/login'

    router.push({
      path:'/login',
      query:{redirect:location.hostname}
    })

  } else if (error.response.status === 500) {
    // 服务器错误
    // do something
    return Promise.reject(error.response.data)
  }
  // 返回 response 里的错误信息
  return Promise.reject(error.response.data)
});

// 将 Axios 实例添加到Vue的原型对象上
export default {
  install(Vue){
    Object.defineProperty(Vue.prototype,'$MyAxios',{value:Axios})
  }
}

import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/news/Home'
import Dynamic from '@/views/news/Dynamic'
import DynamicDetails from "@/views/news/DynamicDetails"
import Markdown from "@/component/Markdown"
import Login from "@/views/Login"
import Main from "@/views/management/Main"
import ArticleList from "@/component/ArticleList"
import UserManagementList from "@/component/UserManagementList"
import UserAdd from "@/component/UserAdd"

Vue.use(Router)


export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/Home'
    }
    ,
    {
      path: '/Home',
      name: 'Home',
      component: Home
    },
    {
      path: '/Dynamic/:info_card_id',
      name: 'Dynamic',
      component: Dynamic
    },
    {
      path: '/DynamicDetails/:infoId',
      name: 'DynamicDetails',
      component: DynamicDetails
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/Main',
      name: 'Main',
      component: Main,
      children: [
        {
          path: '/admin/Markdown',
          name: '/admin/Markdown',
          component: Markdown
        },
        {
          path: '/admin/ArticleList',
          name: '/admin/ArticleList',
          component: ArticleList
        }, {
          path: '/user/UserManagementList',
          name: '/user/UserManagementList',
          component: UserManagementList
        }, {
          path: '/user/UserAdd',
          name: '/user/UserAdd',
          component: UserAdd
        }
      ]
    }
  ]
})

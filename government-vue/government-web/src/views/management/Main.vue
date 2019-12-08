<template>
  <el-container style="height: 900px; border: 2px solid #ada7a2;">
    <el-aside width="200px" style="background-color: #545c64">
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
        <el-submenu index="1" style="text-align: left">
          <template slot="title">
            <i class="el-icon-notebook-1"></i>
            <span>文章管理</span>
          </template>
            <el-menu-item index="1-1" style="text-align: left"><router-link to="/admin/Markdown"><i class="el-icon-edit-outline" style="color: #999999;"></i><span style="color: white">写文章</span></router-link></el-menu-item>
            <el-menu-item index="1-2" style="text-align: left"><router-link to="/admin/ArticleList"><i class="el-icon-tickets" style="color: #999999;"></i><span style="color: white">文章列表</span></router-link></el-menu-item>
        </el-submenu>
        <el-submenu index="2" style="text-align: left">
          <template slot="title">
            <i class="el-icon-user"></i>
            <span>用户管理</span>
          </template>
          <el-menu-item index="2-1" style="text-align: left"><router-link to="/user/UserAdd"><i class="el-icon-circle-plus-outline" style="color: #999999;"></i><span style="color: white">添加用户</span></router-link></el-menu-item>
          <el-menu-item index="2-2" style="text-align: left"><router-link to="/user/UserManagementList"><i class="el-icon-s-custom" style="color: #999999;"></i><span style="color: white">用户列表</span></router-link></el-menu-item>
        </el-submenu>

      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown @command="handleCommand">
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="Logout">退出登录</el-dropdown-item>
            <el-dropdown-item command="b">个人中心</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span class="user">{{this.sessionId}}</span>
      </el-header>

      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  export default {
    name: "Main",
    sessionId: '',
    data() {
      return{

      }
    },
    methods: {
        handleCommand(command){

            if(command === 'Logout'){
                this.$MyAxios({
                    method:'get',
                    url: '/logouts'
                }).then(res=>{
                    if(res.data.code===200){
                        localStorage.removeItem("user")
                        localStorage.removeItem("TokenInfo")
                        this.$message.success("退出成功")
                        this.$router.go(0)
                    }
                }).catch(error =>{
                    // alert(error)
                    console.log(error)
                })
            }
        }
    },
      created() {
         let sessionId = localStorage.getItem("user")
          this.sessionId = sessionId;
      }
  }
</script>

<style scoped>
  .el-header {
    background-color: #949fb2;
    color: white;
    line-height: 60px;
  }

  .el-aside {
    color: #ffffff;
  }

  .user {
    color: white;
  }
</style>

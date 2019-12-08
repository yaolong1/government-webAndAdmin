<template>
  <div class="login-container">
    <el-form :model="ruleForm2" :rules="rules2"
             status-icon
             ref="ruleForm2"
             label-position="left"
             label-width="0px"
             class="demo-ruleForm login-page">
      <h3 class="title">系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text"
                  v-model="ruleForm2.userName"
                  auto-complete="off"
                  placeholder="用户名"
        >
          <i slot="suffix" class="el-input__icon el-icon-user-solid"></i>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password"
                  v-model="ruleForm2.password"
                  auto-complete="off"
                  placeholder="密码"
        >
          <i slot="suffix" class="el-input__icon el-icon-lock"></i>
        </el-input>
      </el-form-item>
      <el-checkbox
        v-model="checked"
        class="rememberme"
      >记住密码
      </el-checkbox>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="handleSubmit" :loading="logining">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

  import axios from 'axios'
  import qs from "qs"

  export default {
    data() {
      return {
        logining: false,
        ruleForm2: {
          userName: '',
          password: '',
        },
        rules2: {
          userName: [{required: true, message: '请输入你的用户名', trigger: 'blur'}],
          password: [{required: true, message: '请输入你的密码', trigger: 'blur'}]
        },
        checked: false
      }
    },
    methods: {
      handleSubmit() {
        let api = 'http://localhost:8081/oauth/token';
        let data = {
          grant_type: "password",
          username: this.ruleForm2.userName,
          password: this.ruleForm2.password,
          client_id: "client",
          client_secret: "secret"
        };
        console.log(data);
        this.$axios({
          method: 'post',
          url: api,
          data: qs.stringify(data),
        }).then(response =>{
          if(response.data.code===200){
            console.log(response);
            localStorage.setItem('TokenInfo', response.data.access_token);
            localStorage.setItem('user', this.ruleForm2.userName);
            console.log(response.data.access_token);
            console.log(localStorage.user);
            this.$router.push({path: '/admin/ArticleList'});
          }else {
              this.$message.error('用户名或密码错误');
          }
        }).catch(e =>{
            console.log(e)
            this.$message.error('用户名或密码错误');
        })
      }
    }
  };
</script>

<style scoped>
  .login-container {
    width: 100%;
    height: 100%;
  }

  .login-page {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  label.el-checkbox.rememberme {
    margin: 0px 0px 15px;
    text-align: left;
    float: left;

  }
</style>

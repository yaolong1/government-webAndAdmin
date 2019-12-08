<template>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
           class="demo-ruleForm login-page">
    <h3 class="title" v-if="show==='1'">添加用户</h3>
    <h3 class="title" v-else>修改用户</h3>

    <el-form-item label="用户名" prop="userName">
      <el-input v-model="ruleForm.userName">
        <i slot="suffix" class="el-input__icon el-icon-user-solid"></i>
      </el-input>
    </el-form-item>

    <el-form-item label="旧密码" prop="oldPassword" v-if="show==='2'">
      <el-input type="password" v-model="ruleForm.oldPassword" autocomplete="off">
        <i slot="suffix" class="el-input__icon el-icon-lock"></i>
      </el-input>
    </el-form-item>

    <el-form-item label="新密码" prop="password" v-if="show==='2'">
      <el-input type="password" v-model="ruleForm.password" autocomplete="off">
        <i slot="suffix" class="el-input__icon el-icon-lock"></i>
      </el-input>
    </el-form-item>

    <el-form-item label="密码" prop="password" v-if="show==='1'">
      <el-input type="password" v-model="ruleForm.password" autocomplete="off">
        <i slot="suffix" class="el-input__icon el-icon-lock"></i>
      </el-input>
    </el-form-item>

    <el-form-item label="确认密码" prop="checkPassword" v-if="show==='1'">
      <el-input type="password" v-model="ruleForm.checkPassword" autocomplete="off">
        <i slot="suffix" class="el-input__icon el-icon-lock"></i>
      </el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="back_()" >返回</el-button>
      <el-button type="primary" @click="addForm()" v-if="show==='1'">提交</el-button>
      <el-button type="primary" @click="eqPasswordUpdate()" v-else>修改</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>

  </el-form>
</template>

<script>

    export default {
        name: "UserAdd",
        data() {
            var checkUsername = (rule, value, callback) => {
                if (value === '') {
                    return callback(new Error('用户名不能为空'));
                }
                callback();
            };
            var validatePassword = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPassword !== '') {
                        this.$refs.ruleForm.validateField('checkPassword');
                    }
                    callback();
                }
            };
            var validatePassword2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('输入的密码不一致'));
                } else {
                    callback();
                }
            };
            return {
                ruleForm: {
                    password: '',
                    checkPassword: '',
                    userName: '',
                    oldPassword1: '',
                    oldPassword: '',
                    id:''
                },
                rules: {
                    password: [
                        {validator: validatePassword, trigger: 'blur'}
                    ],
                    checkPassword: [
                        {validator: validatePassword2, trigger: 'blur'}
                    ],
                    userName: [
                        {validator: checkUsername, trigger: 'blur'}
                    ],
                },
                show: '1',
            };
        },
        methods: {
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            /**
             * 获取要更新的内容参数并赋值
             */
            getUpdateUserInfo() {
                this.ruleForm.userName = this.$route.params.userName;
                this.ruleForm.id = this.$route.params.id;
                console.log(this.ruleForm);
                if (this.$route.params.show === '2' && this.$route.params.show != null) {
                    this.show = this.$route.params.show;
                    console.log(this.ruleForm);
                }
            },
            /**
             * 添加用户
             */
            addForm() {
                let api = "/user/addUser";

                this.$MyAxios({
                    method: 'put',
                    url: api,
                    data: this.ruleForm
                }).then(response => {
                    if (response.data.code === 200) {
                        this.$message({
                            message: '添加用户成功！',
                            type: 'success',
                        });
                    } else {
                        if (response.data.code === 500) {
                            this.$message({
                                message: response.data.msg,
                                type: 'warning'
                            });
                        } else {
                            this.$message.error('用户名重复添加用户失败，或网络异常');
                        }

                    }
                });
                console.log(this.ruleForm)
            },
            /**
             * 更新操作
             */
            updateUser() {
              let api = '/user/updateUserInfo';
                  this.$MyAxios({
                    method: 'put',
                    url: api,
                    data: this.ruleForm
                  }).then(response => {
                    if (response.data.code === 200) {
                      console.log(response);
                      this.$message({
                        message: '用户修改成功！',
                        type: 'success',
                      });
                    } else {
                      console.log(response.data.code);
                      this.$message.error('用户修改失败请重新修改');
                    }
                  }).catch(e => {
                    console.log(e)
                  })
            },
          eqPassword2() {
            let api = '/user/eqPassword';
            let data = {
              userName: this.ruleForm.userName,
              password: this.ruleForm.password,
              id: this.ruleForm.id,
            };
            this.$MyAxios({
              method: 'post',
              url: api,
              data: data
            }).then(response => {
              console.log("旧密码与新密码比较"+response.data.boolean)
              if(!response.data.boolean){
                this.updateUser()
              }else {
                this.$message.error('新密码不能和旧密码相同')
              }

            })
          },
          eqPasswordUpdate() {
                let api = '/user/eqPassword';
                let data = {
                  userName: this.ruleForm.userName,
                  password: this.ruleForm.oldPassword,
                  id: this.ruleForm.id,
                };
                this.$MyAxios({
                    method: 'post',
                    url: api,
                    data: data
                }).then(response => {
                  console.log("旧密码与旧密码比较"+response.data.boolean)
                 if(response.data.boolean){
                   this.eqPassword2()
                 }else {
                   this.$message.error('旧密码错误，请重新输入')
                 }

                })
            },
            back_() {
                this.$router.push({
                        name: '/user/UserManagementList'
                    }
                )
            },
        },
        created() {
          this.getUpdateUserInfo()
        }
    }
</script>

<style scoped>
  .login-page {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    text-align: center;
  }
</style>

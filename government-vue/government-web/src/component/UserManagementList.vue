<template>
  <div>
    <el-table
      :data="tableData.filter(data => !search
    || data.username.toLowerCase().includes(search.toLowerCase())
    ||data.username.toLowerCase().includes(search.toLowerCase()))"
      ref="multipleTable"

      tooltip-effect="dark"
      @selection-change="handleSelectionChange"
      style="width: 100%;height: 700px">
      <el-table-column type="selection" width="45"></el-table-column>
      <el-table-column label="id" prop="id"></el-table-column>
      <el-table-column label="用户名" prop="userName" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="密码" prop="password" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-input v-model="search" size="mini" placeholder="输入关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">
            Edit
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      class="page-1"
      background
      @current-change="handleCurrentChange"
      :current-page.sync="pageInfo.current"
      :page-size="pageInfo.size"
      layout="prev, pager, next"
      :total="pageInfo.total">
    </el-pagination>
    <div class="position">
      <div style="float: left">
        <el-button @click="delList()"size="small"type="danger"  icon="el-icon-delete" plain>批量删除</el-button>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "UserManagementList",
    data() {
      return {
        tableData: [{
          id: 1,
          userName: 'admin',
          password: '12345'
        }, {
          id: 2,
          userName: 'admin',
          password: '12345'
        }, {
          id: 3,
          userName: 'admin',
          password: '12345'
        }, {
          id: 4,
          userName: 'admin',
          password: '12345'
        }],
        search: '',
        deleteIdList: [],
        multipleSelection: [],
        state: false,
        pageInfo: {
          current: 1,
          size: 12,
          total: 0,
        }
      }
    },
    methods: {
      /**
       * 编辑跳转更新
       * @param index
       * @param row
       */
      handleEdit(index, row) {
        this.$router.push({
          name: '/user/UserAdd',
          params: {
            id: row.id,
            userName: row.userName,
            password: row.password,
            show: '2'
          }
        });

        console.log(index, row);
      },
      /**
       * 删除用户
       * @param index
       * @param row
       */
      handleDelete(index, row) {
        let id = row.id;
        let api = `/user/delUserInfoById/`+id;
        this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$MyAxios({
            methods: 'get',
            url: api,
          }).then(response =>{
            if(response.data.code === 200){
              this.$message({
                type: 'success',
                message: '删除成功!',
              });
              this.showUserList(1,this.pageInfo.size)
            }else {
              this.$message({
                type: 'error',
                message: '删除失败'
              });
            }
          });

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },

      /**
       * 选中状态
       * @param val
       */
      handleSelectionChange(val) {
        this.multipleSelection = val;
        val.forEach((data,index) =>{
          this.deleteIdList[index] = data.id
        });
        console.log(this.deleteIdList)
      },

      /**
       * 批量删除请求
       *
       **/
      delList(){
        let api = '/user/delUserInfoList';
        if(this.deleteIdList.length>0){
          this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
              this.$MyAxios({
              method: 'post',
              url: api,
              data: this.deleteIdList
            }).then(response =>{
              if(response.data.code === 200){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                this.showUserList(1,this.pageInfo.size)
              }else {
                this.$message({
                  type: 'error',
                  message: '删除失败'
                });
              }
              this.$refs.multipleTable.clearSelection();    //操作完成清除勾选框
            });

          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });

        }else {
          this.$message({
            type: 'error',
            message: '请选择再删除'
          });
        }
      },

      /**
       * 显示文章列表
       */
      showUserList(current,size){
        this.tableData = [];
        var api = "/user/getArticleInfoListPage/" + current + "/" + size;
          this.$MyAxios.get(api).then(response => {
          this.tableData = response.data.data.records;

          this.pageInfo.total = response.data.total;
          if(response.data.code===200){
            // window.reload()

          }else {
            console.log(response.data.code)
          }
          console.log(response)
        })
      },

      /**
       * 分页生效
       * @param val
       */
      handleCurrentChange(val) {
        //当前页
        this.pageInfo.current = val;
        this.showUserList(val,this.pageInfo.size)
        //请求数据
      }
    },
    created() {
      this.showUserList(1,this.pageInfo.size)
    }
  }
</script>

<style scoped>
  .position{
    text-align: end;
  }
</style>

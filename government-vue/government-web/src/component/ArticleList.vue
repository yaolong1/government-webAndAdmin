<template>
  <div>
    <el-table
      :data="tableData.filter(data => !search
      || data.infoId.toLowerCase().includes(search.toLowerCase())
      || data.infoTitle.toLowerCase().includes(search.toLowerCase())
      || data.infoContent.toLowerCase().includes(search.toLowerCase())
      || data.cardTitleType.toLowerCase().includes(search.toLowerCase()))"
      ref="multipleTable"
      tooltip-effect="dark"
      @selection-change="handleSelectionChange"
      style="width: 100%;height: 700px">
      <el-table-column type="selection" width="45"></el-table-column>
      <el-table-column label="id" prop="infoId"></el-table-column>
      <el-table-column label="发布时间" prop="infoDate" :formatter="formData"></el-table-column>
      <el-table-column label="文章内容" prop="infoContent" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="文章标题" prop="infoTitle" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="文章类型" prop="cardTitleType"></el-table-column>
      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-input v-model="search" size="mini" placeholder="输入关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">
            编辑
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">
            删除
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
  import moment from 'moment'
  export default {
    name: "ArticleLists",
    data() {
      return {
        tableData: [{
          infoId: '1',
          infoDate: '2019-05-04',
          infoTitle: '上海市普陀区金沙江路 ',
          infoContent: '我的他',
          cardTitleType: '行政',
          infoCardId: '1'
        }, {
          infoId: '2',
          infoDate: '2019-05-04',
          infoTitle: '上海市普陀区金沙江路 ',
          infoContent: '我的他',
          cardTitleType: '行政',
          infoCardId: '1'
        }, {
          infoId: '3',
          infoDate: '2019-05-04',
          infoTitle: '上海市普陀区金沙江路 ',
          infoContent: '我的他',
          cardTitleType: '行政',
          infoCardId: '1'
        }, {
          infoId: '4',
          infoDate: '2016-05-04',
          infoTitle: '上海市普陀区金沙江路 ',
          infoContent: '我的他',
          cardTitleType: '行政',
          infoCardId: '1'
        }],
        search: '',
        deleteIdList: [],
        multipleSelection: [],
        state: false,
        pageInfo: {
          current: 1,
          size: 12,
          total: 0,
        },
      }
    },
    methods: {
      /**
       *  编辑信息
       *
      **/
      handleEdit(index, row) {

        this.$router.push({
          name: '/admin/Markdown',
          params: {
            infoCardId: row.infoCardId,
            infoContent: row.infoContent,
            infoDate: row.infoDate,
            infoTitle: row.infoTitle,
            infoId: row.infoId,
            show: '2'
          }
        });

        console.log(index, row);
      },
      /**
       * 删除单条信息
       *
       **/
      handleDelete(index, row) {
        let infoId = row.infoId;
        let api = `/admin/delArticleInfoById/`+infoId;
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
                          this.showList(1,this.pageInfo.size)
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
       * 状态选中
       *
       **/
      handleSelectionChange(val) {
        this.multipleSelection = val
        val.forEach((data,index) =>{
          this.deleteIdList[index] = data.infoId
        });
        console.log(this.deleteIdList)
      },
      /**
       * 批量删除请求
       *
       **/
      delList(){
          let api = '/admin/delArticleInfoList';
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
                    this.showList(1,this.pageInfo.size)
                  }else {
                      this.$message({
                          type: 'error',
                          message: '删除失败'
                      });
                  }
               this.$refs.multipleTable.clearSelection();            //操作完成清除勾选框
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
       showList(current,size){
        this.tableData = [];
        var api = "/admin/getArticleListPage/" + current + "/" + size;
        this.$MyAxios.get(api).then(response => {
          this.tableData = response.data.mainCard.records;
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
       * 格式化时间
       */
       formData(row,column){
         var newdate = row[column.property]
        if(newdate === undefined){return ''};
        return moment(newdate).format("YYYY-MM-DD HH:mm:ss")
      },
      /**
       * 分页生效
       * @param val
       */
      handleCurrentChange(val) {
        //当前页
        this.pageInfo.current = val;
        this.showList(val,this.pageInfo.size)
        //请求数据

      }
    },
    created() {
      this.showList(1,this.pageInfo.size)
    }
  }
</script>

<style scoped>
  .position{
    text-align: end;
  }
</style>

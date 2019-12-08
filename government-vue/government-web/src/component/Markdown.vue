<template>
  <div class="app-container">
    <el-form :label-position="'left'" :model="markdownForm" :rules="rules" label-width="80px">
      <el-form-item label="标 题" prop="title" size="small">
        <el-input v-model="markdownForm.infoTitle"></el-input>
      </el-form-item>
      <span style="text-align: center;margin-bottom: 20px ">
         <el-tag>文章类型</el-tag>
       </span>
      <br><br>
      <el-form-item  prop="radio" size="small">
        <el-radio-group v-model = markdownForm.infoCardId size="medium">
          <el-radio-button label=1>乡镇动态</el-radio-button>
          <el-radio-button label=2>政务公开</el-radio-button>
          <el-radio-button label=3>政策法规</el-radio-button>
          <el-radio-button label=4>招商引资</el-radio-button>
          <el-radio-button label=5>美丽奋发</el-radio-button>
        </el-radio-group>
      </el-form-item>
    </el-form>


    <mavon-editor class="editor" ref="md" @save="save" @imgAdd="imgAdd" @imgDel="imgDel"
                  v-model="markdownForm.infoContent"
                  :codeStyle="markdown.codeStyle" :toolbars="markdown.toolbars"/>
    <br>
    <div>
      <el-button type="primary" style="float: right;" @click="release" v-show="show==='1'" >发布</el-button>
      <el-button type="primary" style="float: left;" @click="back_" v-show="show==='2'" >返回</el-button>
      <el-button type="primary" style="float: right;" @click="update" v-show="show==='2'" >更新</el-button>
    </div>
  </div>
</template>

<script>

  import axios from 'axios'

  export default {
    name: "Markdown",
    data() {
      return {
        markdown: {
          codeStyle: 'atom-one-dark',
          toolbars: {
            bold: true, // 粗体
            italic: true, // 斜体
            header: true, // 标题
            underline: true, // 下划线
            strikethrough: true, // 中划线
            mark: true, // 标记
            superscript: true, // 上角标
            subscript: true, // 下角标
            quote: true, // 引用
            ol: true, // 有序列表
            ul: true, // 无序列表
            link: true, // 链接
            imagelink: true, // 图片链接
            code: true, // code
            table: true, // 表格
            fullscreen: true, // 全屏编辑
            readmodel: true, // 沉浸式阅读
            htmlcode: true, // 展示html源码
            help: true, // 帮助
            /* 1.3.5 */
            undo: true, // 上一步
            redo: true, // 下一步
            trash: true, // 清空
            save: true, // 保存（触发events中的save事件）
            /* 1.4.2 */
            navigation: true, // 导航目录
            /* 2.1.8 */
            alignleft: true, // 左对齐
            aligncenter: true, // 居中
            alignright: true, // 右对齐
            /* 2.2.1 */
            subfield: true, // 单双栏模式
            preview: false, // 预览
          }
        },
        markdownForm: {
          infoTitle: ' ',
          infoCardId: 1,
          infoContent: '',
          infoDate:'',
          infoContentHtml: '',
          infoId: '',
        },
        rules: {
          title: [
            {required: true, message: '请检查标题是否正确', trigger: 'blur'},
            {min: 1, max: 80, message: '长度在1到80个字符', trigger: 'blur'}
          ]
        },
        show: '1'
      }
    },
    created() {
      this.getArticle()
      this.getUpdateInfo()

    },
    mounted() {
      this.timer = setInterval(this.intervalSave, 2 * 60 * 1000);
      this.markdownForm.infoContentHtml = this.$refs.md.d_render;
      console.log(this.markdownForm.infoContentHtml)
    },
    beforeDestroy() {
      clearInterval(this.timer);

    },
    methods: {
      getArticle() {  //获取文章内容

      },
      save() {  //保存文章内容

      },
      intervalSave() { //自动保存

      },
      imgAdd(pos, file) { //添加图片，pos为位置

      },
      imgDel(pos, url) { //删除图片，并不是修改就会触发，仅支持工具栏操作

      },
      release(){ //发布文章
        let date = new  Date().getTime();
        this.markdownForm.infoDate = date;

        this.markdownForm.infoContentHtml = this.$refs.md.d_render;
          let api = "/saveCardInfo";

          this.$MyAxios({
          method: 'post',
          url: api,
          data: this.markdownForm
        }).then(response =>{
          if (response.data.code===200){
            this.$message({
              message: '文章发布成功！',
              type: 'success',
            });
          } else {
            this.$message.error('文章发布失败请重新发布');
            console.log(response)
          }
          console.log(response)
        });
        console.log(this.markdownForm)
      },
      /**
       * 获取要更新的内容参数并赋值
       */
      getUpdateInfo(){
        this.markdownForm.infoContent = this.$route.params.infoContent;
        this.markdownForm.infoCardId = this.$route.params.infoCardId;
        this.markdownForm.infoTitle = this.$route.params.infoTitle;
        this.markdownForm.infoDate = this.$route.params.infoDate;
        this.markdownForm.infoId = this.$route.params.infoId;
        if(this.$route.params.show==='2'&&this.$route.params.show!=null){
          this.show = this.$route.params.show;
        }
      },
      /**
       * 修改/更新 文章
       */
      update(){
        let api = '/admin/updateArticleInfo';
          this.$MyAxios({
          method: 'put',
          url: api,
          data: this.markdownForm
        }).then(response =>{
          if (response.data.code===200){
            this.$message({
              message: '文章更新成功！',
              type: 'success',
            });
          } else {
            console.log(response.data.code)
            this.$message.error('文章更新失败请重新更新');
          }
        }).catch(e=>{
          console.log(e)
        })
      },


      back_(){
        this.$router.push({
          name: '/admin/ArticleList'
          }
        )
      }
    },
    watch:{
      $route(to,from){
      }
    }
  }
</script>

<style scoped lang="scss">

  .v-note-panel.shadow {
    min-height: 440px; //设置编辑框最小高度
  }

  .editor {
    height: 600px;
  }

  .input {
    width: 380px;
  }

  .radio1 {
  }
</style>

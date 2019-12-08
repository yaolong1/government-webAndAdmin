<template>
  <div>
    <div id="container">
      <div id="header">
        <div id="left" class="header1"><img src="https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E4%B8%AD%E5%9B%BD%E5%A5%8B%E5%8F%91%E4%BA%BA%E6%B0%91%E6%94%BF%E5%BA%9C-titil.png" class="img-utils"></div>
        <div id="right" class="header2">
          <div class="search">
            <el-input placeholder="搜索" class="input-with-select">
            </el-input>
            <el-button slot="append" class="my-button" icon="el-icon-search"></el-button>
          </div>
        </div>
      </div>

      <div class="model1">
        <div class="nav">
          <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            router
            mode="horizontal"
            @select="handleSelect"
            @open="handleOpen"
            @close="handleClose"
            background-color="#B91C1C"
            text-color="#fff"
            active-text-color="#ffffff"
          >
            <el-menu-item :index="nva.home">网站首页</el-menu-item>
            <el-submenu index="2">
              <template slot="title">奋发概况</template>
              <el-menu-item index="2-1">政民互动</el-menu-item>
              <el-menu-item index="2-2">调查征集</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">
                <router-link to="/Dynamic/5" style="color: #fff8f8">党务公开</router-link>
              </template>
              <el-menu-item index="3-1">信息公开</el-menu-item>
            </el-submenu>
            <el-submenu index="4">
              <template slot="title">
                <router-link to="/Dynamic/2" style="color: #fff8f8">政务公开</router-link>
              </template>
              <el-menu-item index="4-1">政务文件</el-menu-item>
              <el-menu-item index="4-2">建设发展</el-menu-item>
            </el-submenu>
            <el-menu-item><router-link to="/Dynamic/6" style="color: #fff8f8">通知公告</router-link></el-menu-item>
            <el-submenu index="6">
              <template slot="title">
                <router-link to="/Dynamic/3" style="color: #fff8f8">政策法规</router-link>
              </template>
              <el-menu-item index="6-1">最新政策</el-menu-item>
              <el-menu-item index="6-2">法规意识</el-menu-item>
            </el-submenu>
            <el-submenu index="7">
              <template slot="title">
                <router-link to="/Dynamic/1" style="color: #fff8f8">乡镇动态</router-link>
              </template>
              <el-menu-item index="7-1">城镇动态</el-menu-item>
              <el-menu-item index="7-2">民生服务</el-menu-item>
            </el-submenu>
            <el-menu-item >
              <router-link to="/Dynamic/4" style="color: #fff8f8">招商引资</router-link>
            </el-menu-item>
            <el-menu-item ><router-link to="/Dynamic/7" style="color: #fff8f8">便民服务</router-link></el-menu-item>
          </el-menu>
        </div>
        <div class="navImg">

          <img src="https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E8%BD%AE%E6%92%AD2.jpg" class="banner_img1"/>
        </div>

        <div class="dynamic-card">

          <el-card class="box-card interval" v-for="(item,index) in cardData" :key="index">
            <div class="clearfix2" slot="header">
              <span><router-link :to="{name: 'DynamicDetails', params: {infoId:item.infoId}}" style="color: #100707;">{{item.infoTitle}}</router-link></span>
            </div>
            <div class="text item">
              <div class="contents">
               <article v-html="item.infoContentHtml" class="doc"></article>
              </div>
              <br>
              <div>
                <span class="list-date2">{{format(item.infoDate)}}</span>
              </div>
            </div>
          </el-card>
          <el-pagination
            class="page-1"
            background
            @current-change="handleCurrentChange"
            :current-page.sync="pageInfo.current"
            :page-size="pageInfo.size"
            layout="prev, pager, next"
            :total="pageInfo.total">
          </el-pagination>
        </div>

        <div class="footerNav">
          <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#B91C1C"
            text-color="#fff"
            active-text-color="#ffffff"
          >
            <el-menu-item index="1" target="_self">奋发概况</el-menu-item>
            <el-menu-item index="2"><a href="#" target="_self">政策法规</a></el-menu-item>
            <el-menu-item index="3"><a href="#" target="_self">乡镇动态</a></el-menu-item>
            <el-menu-item index="4"><a href="#" target="_self">招商引资</a></el-menu-item>
          </el-menu>
        </div>
        <div class="footer"><br>Copyright © 2019 - 2019 殷跃龙 | <span style="color: #B91C1C">渝ICP备-19015782号</span></div>
      </div>
      <br>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: "Dynamic",
    data() {
      return {
        nva: {
          home: '/Home',
          dynamic: '/Dynamic'
        }
        ,
        formInline: {
          user: '',
          region: ''
        },
        activeIndex: '1',
        activeIndex2: '1',
        cardData: [],
        pageInfo: {
          infoCardId: 1,
          current: 1,
          size: 4,
          total: 0,
        },

      }
    },
    mounted() {
      // this.getCardTitleList(1)
    },
    created: function () {
      //这里定义这个方法，vue实例之后运行到这里就调用这个函数
      this.getCardInfoListPage(this.$route.params.info_card_id, this.pageInfo.current, this.pageInfo.size)

    },
    watch:{

      $route(to,from){
        this.getCardInfoListPage(this.$route.params.info_card_id, this.pageInfo.current, this.pageInfo.size)
      }
    },
    methods:
      {
      handleCurrentChange(val) {
          //当前页
          this.pageInfo.current = val;
          // alert(this.page);
          //请求数据
          this.getCardInfoListPage(this.$route.params.info_card_id, val, this.pageInfo.size)
        },


      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      getCardInfoListPage(infoCardId, current, size) {
        this.cardData = []
        var api = "/getCardInfoListById/" + infoCardId + "/" + current + "/" + size;
        this.$MyAxios.get(api).then(response => {
          this.cardData = response.data.data.records;
          this.pageInfo.total = response.data.total;
          if(response.data.code===200){
            // window.reload()

          }else {
            console.log(response.data.code)
          }
          console.log(response)
        })

      },
      //格式化时间戳
      format(s) {
        const
          date = new Date(s),
          y = date.getFullYear(),
          m = date.getMonth() + 1,
          d = date.getDate() - 1
        return `${y}-${m < 10 ? '0' + m : m}-${d < 10 ? '0' + d : d}`
      },
    },
  }

</script>

<style scoped>


</style>

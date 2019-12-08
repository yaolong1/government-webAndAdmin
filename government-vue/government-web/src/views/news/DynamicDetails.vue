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
      <div class="model3 ">
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
            <el-menu-item index="5">通知公告</el-menu-item>
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
            <el-menu-item index="8">
              <router-link to="/Dynamic/4" style="color: #fff8f8">招商引资</router-link>
            </el-menu-item>
            <el-menu-item index="9"><a href="#">便民服务</a></el-menu-item>
          </el-menu>
        </div>
        <div class="Img">
          <img src="https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E8%BD%AE%E6%92%AD2.jpg" class="banner_img1"/>
        </div>
      </div>

      <div style="width: 100%;margin-top: 20px">
        <div class="dynamic-details-content">
          <el-card class="box-card interval">
            <div class="clearfix2" slot="header">
              <span style="color: #B91C1C;font-size: 25px; float: left"> {{DynamicDetailsInfo.infoTitle}}</span><br><br>
              <span class="list-date2">{{format(DynamicDetailsInfo.infoDate)}}</span>
            </div>
            <div class="text item">
              <div class="contents contents2">
                <article v-html="DynamicDetailsInfo.infoContentHtml" style="text-align: left"></article>
                <br><br><br><br><br><br><br><br><br><br><br><br><br>
              </div>
            </div>
          </el-card>
        </div>

        <div class="dynamic-details-right">
          <el-card class="box-card interval">
            <div style="float: top;width: 100%">
              <el-input placeholder="搜索" style="width: 205px">
              </el-input>
              <el-button slot="append" class="my-button2" icon="el-icon-search"></el-button>
            </div>

            <div class="dynamic-details-card">
              <span class="dynamic-details-card1"></span>
              <span class="dynamic-details-card2"></span>
            </div>
            <div class="dynamic-details-card-1">
              <span class="dynamic-details-card1"></span>
              <span class="dynamic-details-card2"></span>
            </div>
          </el-card>
        </div>
      </div>
      <br>
      <div class="footerNav2">
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
      <div class="footer" style="float: left"><br>Copyright © 2019 - 2019 殷跃龙 | <span style="color: #B91C1C">渝ICP备-19015782号</span>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: "DynamicDetails",
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
        DynamicDetailsInfo: {
          infoTitle: '2018年度城乡居民基本医疗保险参保缴费9月开始',
          infoDate: 1521847095000,
          infoContentHtml: '<p>根据《关于做好2018年度城乡居民基本医疗保险工作的通知》（梅市人社〔2017〕64号）精神，2018年度城乡居民基本医疗保险参保缴费工作于9月开始。</p>\n' +
            '\n' +
            '<p>一、参保缴费时间</p>\n' +
            '\n' +
            '<p>2017年9月1日至11月30日。</p>\n' +
            '\n' +
            '<p>二、参保缴费标准</p>\n' +
            '\n' +
            '<p>2018年度城乡居民医保个人缴费标准为每人每年180元。</p>\n' +
            '\n' +
            '<p>三、参保缴费办法</p>\n' +
            '\n' +
            '<p>首次参保人员应先到所属参保地城乡居民医保中心办理参保登记（幼中小学就读的外市户籍学生，需提供学校就读证明），再到社保部门委托的邮储银行进行缴费。连续参保人员按以下办法办理缴费。</p>\n' +
            '\n' +
            '<p>（一）农村居民缴费。农村居民以户为单位参保缴费，村委会统一收缴医保费，并建立参保登记花名册。镇（街道）做好参保人员的报盘和缴费工作。</p>\n' +
            '\n' +
            '<p>（二）城镇居民缴费。城镇居民持本人户口簿或身份证或上一年度缴费单，到邮政储蓄银行网点办理缴费。同时，也可通过手机银行、网银在线缴费、代扣代缴等快捷渠道办理参保、续保手续。</p>\n' +
            '\n' +
            '<p>（三）在校学生缴费。全日制大学、中职技校的学生，由学校统一收缴、造册，报学校所在地镇（街道）城乡居民医保中心进行报盘核定，核定无误后，学校持缴费通知单到邮政储蓄银行办理缴费手续。</p>\n' +
            '\n' +
            '<p>（四）特殊人群缴费。特困供养对象、享受当地最低生活保障家庭成员、建档立卡贫困人员按照《关于印发&lt;梅州市困难群众医疗救助实施细则>的通知》(梅市民字〔2017〕35号)规定，其个人缴费部分给予全额资助。重度残疾人员、农村纯生二女结扎的夫妇双方及其年龄在14周岁以内的女孩，其个人缴费由县级政府统筹安排给予全额补助。</p>\n' +
            '\n' +
            '<p>四、医保待遇调整</p>\n' +
            '\n' +
            '<p>2018年度城乡居民医保待遇视国家、省、市有关政策进行调整。</p>'
        }
      }
    },
    mounted() {
      // this.getCardTitleList(1)
    },
    created: function () {
      //这里定义这个方法，vue实例之后运行到这里就调用这个函数
      this.getCardTitleList2();
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      //获取卡片信息
      getCardTitleList2() {
        var api = "/getCardInfoById/" + this.$route.params.infoId;
        this.$MyAxios.get(api).then(response => {
          console.log(response);
          this.DynamicDetailsInfo = response.data.DynamicDetailsInfo
          console.log(this.DynamicDetailsInfo)

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
    }
  }

</script>

<style scoped>


</style>

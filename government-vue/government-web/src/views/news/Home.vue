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
            mode="horizontal"
            router
            @select="handleSelect"
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
        <div class="navImg">
          <template class="d_jump1">
            <el-carousel :interval="5000" arrow="always" class="d_jump">
              <el-carousel-item v-for="item in imgList" :key="item.id">
                <el-row>
                  <el-col :span="24"><img :src="item.idView" class="banner_img1"/></el-col>
                </el-row>
              </el-carousel-item>
            </el-carousel>
          </template>
        </div>
      </div>
      <div class="inform">
        <span class="advice">通知公告</span>
        <span class="info1"></span>
        <span class="info2"></span>
        <span class="info3 "><a href="#"><i class="el-icon-share"></i></a> </span>
      </div>
      <div class="middle">
        <div class="content">
          <div class="img">
            <template class="d_jump1">
              <el-carousel :interval="5000" arrow="always" class="d_jump">
                <el-carousel-item v-for="item in imgList2" :key="item.id">
                  <el-row>
                    <el-col :span="24"><img :src="item.idView" class="banner_img"/></el-col>
                  </el-row>
                </el-carousel-item>
              </el-carousel>
            </template>
          </div>
          <div class="card">
            <div class="card-top">
              <div class="tab tab1 ">
                <el-card class="box-card">
                  <div class="clearfix">
                    <span style="color: #B91C1C">l</span>
                    <span>乡镇动态</span>
                    <hr style="background-color:#B91C1C;height: 1px;width:100%;border: none;">
                  </div>
                  <div v-for="(item,index) in mainCard1" v-if='index<=3' class="text item">

                    <div style="width: 100%;height: auto"><a href="#" class="list-content1">{{item.infoTitle }}</a> <span
                      class="list-date">{{format(item.infoDate)}}</span></div>

                  </div>
                  <el-button plain size="small" style="color: #B91C1C;float: right;">
                    <router-link to="/Dynamic/1" style="color: #B91C1C">更多</router-link>
                  </el-button>
                </el-card>
              </div>
              <div class="tab tab2">
                <el-card class="box-card">
                  <div class="clearfix">
                    <span style="color: #B91C1C">l</span>
                    <span>政务公开</span>
                    <hr style="background-color:#B91C1C;height: 1px;width:100%;border: none;">
                  </div>
                  <div v-for="(item,index) in mainCard2" v-if='index<=3' class="text item">

                    <a href="#" class="list-content1">{{item.infoTitle }}</a> <span class="list-date">{{format(item.infoDate)}}</span>

                  </div>
                  <el-button plain size="small" style="color: #B91C1C;float: right;">
                    <router-link to="/Dynamic/2" style="color: #B91C1C">更多</router-link>
                  </el-button>
                </el-card>
              </div>
            </div>
            <div class="card-top">
              <div class="tab tab3">
                <el-card class="box-card">
                  <div class="clearfix">
                    <span style="color: #B91C1C">l</span>
                    <span>政策法规</span>
                    <hr style="background-color:#B91C1C;height: 1px;width:100%;border: none;">
                  </div>
                  <div v-for="(item,index) in mainCard3" v-if='index<=6' class="text item">

                    <a href="#" class="list-content1">{{item.infoTitle }}</a> <span class="list-date">{{format(item.infoDate)}}</span>

                  </div>
                  <el-button plain size="small" style="color: #B91C1C;float: right;">
                    <router-link to="/Dynamic/3" style="color: #B91C1C">更多</router-link>
                  </el-button>
                </el-card>
              </div>
              <div class="tab tab4">
                <el-card class="box-card">
                  <div class="clearfix">
                    <span style="color: #B91C1C">l</span>
                    <span>招商引资</span>
                    <hr style="background-color:#B91C1C;height: 1px;width:100%;border: none;">
                  </div>
                  <div v-for="(item,index) in mainCard4" v-if='index<=6' class="text item">

                    <a href="#" class="list-content1">{{item.infoTitle }}</a> <span class="list-date">{{format(item.infoDate)}}</span>

                  </div>
                  <el-button plain size="small" style="color: #B91C1C;float: right;">
                    <router-link to="/Dynamic/4" style="color: #B91C1C">更多</router-link>
                  </el-button>
                </el-card>
              </div>
            </div>
          </div>
        </div>

        <div class="sider">
          <div class="sider1">
            <div class="sider1-up">
              <img src="https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E4%B8%AD%E5%9B%BD%E6%A2%A6.jpg" class="img-utils">
            </div>
            <div class="sider1-up">
              <div v-for="o in 3" :key="o" class="text item">
                <a href="#" style="color: white;font-size: 10px">> {{'列表内容 ' + o }}</a>
                <hr>
              </div>
            </div>
          </div>
          <div class="sider2"><img src="../../assets/images/网上办事.jpg"
                                   style="width: 100%;height: 100% ;border-radius:5px 5px 5px 5px;"></div>
          <div class="sider3">
            <el-card class="box-card">
              <div class="clearfix">
                <span style="color: #B91C1C">l</span>
                <span>美丽奋发</span>
                <hr style="background-color:#B91C1C;height: 1px;width:100%;border: none;">
              </div>
              <div class="body-small">
                <el-tabs tab-position="left">
                  <el-tab-pane label="和谐社会">
                    <div class="tab-img">
                      <span><img src="https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E5%92%8C%E8%B0%90%E7%A4%BE%E4%BC%9A%E5%9B%BE.jpg" class="img-utils"></span>
                      <el-button plain size="small" style="color: #B91C1C">查看详情</el-button>

                    </div>


                  </el-tab-pane>
                  <el-tab-pane label="乡镇动态">
                    <div class="tab-img">
                      <span><img src="https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E4%B9%A1%E9%95%87%E5%8A%A8%E6%80%81%E5%9B%BE.jpg" class="img-utils"></span>
                      <el-button plain size="small" style="color: #B91C1C">查看详情</el-button>
                    </div>

                  </el-tab-pane>
                  <el-tab-pane label="廉政文化">
                    <div class="tab-img">
                      <span><img src="https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E5%BB%89%E6%94%BF%E6%96%87%E5%8C%96%E5%9B%BE.jpg" class="img-utils"></span>
                      <el-button plain size="small" style="color: #B91C1C">查看详情</el-button>
                    </div>
                  </el-tab-pane>
                  <el-tab-pane label="美丽奋发">
                    <div class="tab-img">
                      <span><img src="https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E7%BE%8E%E4%B8%BD%E5%A5%8B%E5%8F%91%E5%9B%BE.jpg" class="img-utils"></span>
                      <el-button plain size="small" style="color: #B91C1C">查看详情</el-button>
                    </div>
                  </el-tab-pane>
                </el-tabs>
              </div>
            </el-card>

          </div>
        </div>
      </div>
      <br>
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
  </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "Home",
        data() {
            return {
                nva: {
                    home: '/Home',
                    dynamic: '/Dynamic'
                },
                formInline: {
                    user: '',
                    region: ''
                },
                activeIndex: '1',
                activeIndex2: '1',
                // 图片需要引入, 否则无法显示
                imgList: [
                    {id: 0, idView: ('https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E8%BD%AE%E6%92%AD1.jpg')},
                    {id: 1, name: '详情', idView: ('https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E8%BD%AE%E6%92%AD2.jpg')},
                ],
                imgList2: [
                    {id: 0, idView: ('https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E8%BD%AE%E6%92%AD%E5%B0%8F1.jpg')},
                    {id: 1, idView: ('https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E8%BD%AE%E6%92%AD%E5%B0%8F2.jpg')},
                    {id: 3, idView: ('https://yaolong.oss-cn-beijing.aliyuncs.com/government/%E8%BD%AE%E6%92%AD%E5%B0%8F3.jpg')},
                ],
                mainCard1: [],
                mainCard2: [],
                mainCard3: [],
                mainCard4: [],

            }
        },
        mounted() {
            // this.getCardTitleList(1)
        },
        created: function () {
            this.getCardTitleList1(1);//这里定义这个方法，vue实例之后运行到这里就调用这个函数
            this.getCardTitleList2(2);
            this.getCardTitleList3(3);
            this.getCardTitleList4(4);
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },

            //获取卡片信息
            getCardTitleList1(index) {
                var api = "/get/ByCardId/" + index;
              this.$MyAxios.get(api).then(response => {
                    console.log(response);
                    this.mainCard1 = response.data.mainCard
                    console.log(this.mainCard1)

                })
            },
            getCardTitleList2(index) {
                var api = "/get/ByCardId/" + index;
              this.$MyAxios.get(api).then(response => {
                    console.log(response);
                    this.mainCard2 = response.data.mainCard
                    console.log(this.mainCard2)

                })
            },
            getCardTitleList3(index) {
                var api = "/get/ByCardId/" + index;
              this.$MyAxios.get(api).then(response => {
                    console.log(response);
                    this.mainCard3 = response.data.mainCard
                    console.log(this.mainCard3)

                })
            },
            getCardTitleList4(index) {
                var api = "/get/ByCardId/" + index;
                this.$MyAxios.get(api).then(response => {
                    console.log(response);
                    this.mainCard4 = response.data.mainCard
                    console.log(this.mainCard4)

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

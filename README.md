


# 政府网站模板 

- **后台 SpringBoot + SpringSecurity oAuth2（密码模式）+JWT + Mybatis-Plus + redis** 
 
- **前端 vue.js + element-ui**

## government-vue文件 运行准备（前端）


---
- Node.js安装

    - 下载地址：[nodeJs官网下载](https://nodejs.org/en/)
    
    - 安装完nodejs之后,打开cmd终端输入一下命令安装webpack
    
   ~~~
   npm install webpack -g
   ~~~
 
   - 安装 Node.js 淘宝镜像加速器（cnpm）
   ~~~
   npm install cnpm -g
   
   # 或使用如下语句解决 npm 速度慢的问题
   npm install --registry=https://registry.npm.taobao.org

---
- 安装vue
 
   - 安装 vue-cli
   ~~~
   cnpm install vue-cli -g
   ~~~
   
   - 测试是否安装成功
   ~~~
   vue list
   ~~~
   ![](img/vue-list.png)
   {% asset_img vue-list.png %}
- 上述环境安装完成后在government-web目录下用管理员模式的命令行工具运行一下命令：
~~~
npm i node-sass --sass_binary_site=https://npm.taobao.org/mirrors/node-sass/

npm install

npm install element-ui --sava 安装element-ui

~~~
- 下载成功后在government-web目录cmd下运行
~~~
npm run dev
~~~
 ![](img/run-dev.png)
{% asset_img run-dev.png %}

直接访问http://localhost:8080

如下：

 ![](img/Home.png)
{% asset_img Home.png %}

- 功能介绍(图片不是动态的 暂时还没有写)
    
    - 发布的文章简单展示如下：
    
      ![](img/简单展示.png)
      {% asset_img 简单展示.png %}
    
    - 点击更多进入单个文章类别的详情页面（文章类别：乡镇动态 政务公开，政策法规，招商引资。。具体发布怎样类别的文章到后台设置发布类型就行了）
    
        详情界面如下：
        ![](img/detailstype.png)
        {% asset_img detailstype.png %}
        
    - 点击标题会进入文章的详情页面
          详情界面如下：
          ![](img/details.png)
          {% asset_img details.png %}
    其他类型的大同小异不贴不来了
    
    - 后台界面 （由于还没有连接后台，在后面连接后台之后再展示）



---
## government-admin文件 运行准备（后台）

在数据库导入sql目录下得.sql文件

更改 application.yml的mysql数据连接配置和redis缓存连接配置

配置好之后运行GovernmentAdminApplication.java

或者在`target/`直接运行`java -jar government-admin-0.0.1-SNAPSHOT.jar` 这里不用以上连接配置，直接是连接我的数据库


前端一直保持运行状态 没有运行运行即可;


---

后端界面地址默认要登录不登录进不去

地址：http://localhost:8080/login
账号：admin 密码：123

登录成功如下图：

   ![](img/mainlist.png)
   % asset_img mainlist.png %}

- 功能介绍：
  
  **文章管理：**
  
   - 模糊搜索
    
   - 查看文章
    ![](img/mainlist.png)
      % asset_img mainlist.png %}
   - 修改文章
    ![](img/edit.png)
     % asset_img edit.png %}
   - 批量删除
      ![](img/deletlist.png)
      % asset_img deletlist.png %}
      ![](img/suceese.png)
      % asset_img suceese.png %}
   - 发布文章（用Markdown）
    ![](img/release.png)
    % asset_img release.png %}
    ![](img/a.png)
    % asset_img a.png %}
    
   - 删除文章 
   
   
   **用户管理：**(都差不多不粘贴了)
    
   - 查看用户
    ![](img/userlist.png)
    % asset_img userlist.png %}
   
   - 添加用户
 
   - 修改用户 
    
   - 删除用户  
     
   - 模糊搜索
    

  **退出登录**
    
  ![](img/havetoken.png)
  % asset_img havetoken.png %}
  ---
  退出：
  ![](img/logout.png)
  % asset_img logout.png %}
  ---
  退出成功显示权限不足：
  ![](img/Noauthentication.png)
  % asset_img  Noauthentication.png %}
 



<font color=red>终于写完了好累！！！！！！！！ </font>      
    
# 实训项目在线演示地址：
  
  后台：[后台演示](47.94.81.96:8089/Login)
  
  账号：admin 密码：123
  
  前台：[前端演示](yaolong.top:8089/Home)
  
   
    
[代码地址](https://github.com/yaolong1/government-webAndAdmin)   

# 部署
 
 [部署教程地址](http://yaolong.top/2019/12/08/docker%E9%83%A8%E7%BD%B2vue%E9%A1%B9%E7%9B%AE/)
    
    
   


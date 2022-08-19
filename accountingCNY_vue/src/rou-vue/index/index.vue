<template>
  <!-- 首页布局 -->
  <el-container class="home">
    <!-- 头部区域 -->
    <el-header class="top">
      <!-- 设置，账单的路由导航 -->
      <div class="t_top">
        <router-link to="/setUp">
          <Tools style="width: 20px; color: #fff" />
        </router-link>
        <router-link to="/bill">
          <Histogram style="width: 20px; color: #fff" />
        </router-link>
      </div>
      <!-- 首页收入 支出展示 -->
      <div class="t_bot">
        <div class="income text-white">
          <span>本月支出（￥）</span>
          <h1>0</h1>
        </div>
        <p>|</p>
        <div class="spend text-white">
          <span>本月收入（￥）</span>
          <h1>0</h1>
        </div>
      </div>
    </el-header>
    <!-- 首页账单详情区域 -->
    <el-main class="bot">
      <!-- 首页账单详情展示区域 -->
      <div class="ins">
        <p>本月未开始记账<br />点击加号，开始记账</p>
      </div>
      <!-- 首页账单类别添加区域 -->
      <div class="add">
        <router-link to="/add">
          <el-row>
            <el-button type="danger" color="#9776FB" round>
              <Plus style="color: #fff; width: 20px" />
            </el-button>
          </el-row>
        </router-link>
      </div>
    </el-main>
  </el-container>
</template>
<script setup lang="ts">
import { Tools, Histogram, Plus } from "@element-plus/icons-vue";
</script>
<style scoped>
.bot {
  display: flex;
  flex-direction: column;
}
.bot .ins {
  flex: 9;
  display: flex;
  justify-content: center;
  align-items: center;
}
.bot .ins p {
  color: #c1cfde;
  font-weight: 100;
  line-height: 40px;
  text-indent: 1em;
}
.bot .add {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
.home {
  display: flex;
  flex-direction: column;
  height: 100vh;
}
.top {
  background-color: #7797ff;
  /* height: 210px; */
  flex: 3;
}
.t_bot .income h1,
.t_bot .spend h1 {
  text-align: center;
}
.bot {
  /* background-color: #3d3e52; */
  flex: 7;
}
.t_top {
  display: flex;
  justify-content: space-between;
  margin: 10px 10px;
}
.t_bot {
  display: flex;
  justify-content: space-around;
  align-items: center;
  height: 200px;
}
.t_bot p {
  margin-top: 35px;
  color: #c1cfde;
}
</style>

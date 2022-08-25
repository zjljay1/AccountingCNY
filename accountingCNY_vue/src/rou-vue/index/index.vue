<!-- eslint-disable vue/no-template-shadow -->
<!-- eslint-disable vue/no-use-v-if-with-v-for -->
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
          <h1>{{ spend }}</h1>
        </div>
        <p>|</p>
        <div class="spend text-white">
          <span>本月收入（￥）</span>
          <h1>{{ income }}</h1>
        </div>
      </div>
    </el-header>
    <!-- 首页账单详情区域 -->
    <el-main class="bot">
      <!-- 首页账单详情展示区域 -->
      <div class="ins">
        <p v-if="flag">本月未开始记账<br />点击加号，开始记账</p>
        <!-- <button @click="zs">点击</button> -->
        <div v-for="(value, index) in bill" :key="index">
          <el-timeline>
            <el-timeline-item
              center
              :timestamp="value.amount_time"
              placement="top"
            >
              <el-card
                :body-style="{
                  display: 'flex',
                  justifyContent: 'space-between',
                }"
              >
                <div class="flex flex-col">
                  <div v-for="(val, index) in sto.svgData" :key="index">
                    <h1
                      v-if="sto.svgData[index].id == value.category_id"
                      class="flex"
                    >
                      <img
                        :src="'http://127.0.0.1:5173/src' + val.url"
                        class="h-10"
                      />
                      {{ val.name }}
                    </h1>
                  </div>
                  <p class="text-gray-400">{{ value.note }}</p>
                </div>
                <h1>{{ value.amount }}</h1>
              </el-card>
            </el-timeline-item>
          </el-timeline>
        </div>
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
import { getAll } from "@/axios/index";
import { ref, reactive, computed } from "vue";
import svg from "@/stores/svg";
const userId = Number(localStorage.getItem("userId"));
let flag = ref(false);
const sto = svg();
let bill = ref([]);
let income = ref(0);
let spend = ref(0);
getAll(userId).then((res) => {
  // 这玩意是一个reactive类型的对象，直接赋值会覆盖掉reactive的东西
  bill.value = res.data.data;
  for (let i = 0; i < bill.value.length; i++) {
    if (bill.value[i].amount > 0) {
      income.value += bill.value[i].amount;
    } else {
      spend.value += bill.value[i].amount;
    }
  }
  // flag.value = false;
  console.log(bill.value);
});
</script>

<!-- <style>
.el-card__body {
  display: flex;
  justify-content: space-between;
}
</style> -->

<style scoped>
.bot {
  display: flex;
  flex-direction: column;
}
.bot .ins {
  flex: 9;
  display: flex;
  flex-direction: column;
}
.bot .ins p {
  color: #646d77;
  font-weight: 100;
  line-height: 40px;
  text-indent: 1em;
}
.bot .add {
  position: fixed;
  bottom: 0%;
  left: 50%;
}
.home {
  display: flex;
  flex-direction: column;
  height: 100vh;
}
.top {
  /* background-color: #7797ff; */
  background-image: linear-gradient(to right, #5284c0, #67c2b3);
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

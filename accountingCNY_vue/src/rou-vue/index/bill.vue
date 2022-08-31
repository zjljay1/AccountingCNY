<template>
  <!-- 头部区域 -->
  <div class="flex justify-between">
    <el-icon class="m-3" @click="atavisn"><CloseBold /></el-icon>
    <div><h2>统计</h2></div>
    <div>
      <el-date-picker
        v-model="month"
        type="month"
        format="YY-MM"
        :editable="false"
        :clearable="false"
        class="wii"
      />
    </div>
  </div>
  <!-- 表区域 -->
  <div
    id="myChart"
    ref="myChart"
    class="flex justify-center items-center h-96 w-full"
  ></div>
  <div class="ins">
    <p v-if="flag" class="flex justify-center">
      本月未开始记账<br />点击加号，开始记账
    </p>
    <div v-for="(value, index) in bill" :key="index">
      <el-timeline>
        <el-timeline-item center :timestamp="value.amount_time" placement="top">
          <el-card
            :body-style="{
              display: 'flex',
              justifyContent: 'space-between',
            }"
            class="h-26"
          >
            <div class="flex flex-col">
              <div v-for="(val, key) in sto.svgData" :key="key">
                <h2
                  v-if="sto.svgData[key].id == value.category_id"
                  class="flex h-6"
                >
                  <img
                    :src="'http://127.0.0.1:5173/src' + val.url"
                    class="h-8"
                  />
                  {{ val.name }}
                </h2>
              </div>
              <p class="text-gray-400 mt-2">{{ value.note }}</p>
            </div>
            <h1>{{ value.amount }}</h1>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, watch } from "vue";
import * as echarts from "echarts";
import { CloseBold } from "@element-plus/icons-vue";
import router from "@/router/index";
import { ref, reactive } from "vue";
import svg from "@/stores/svg";
import stores from "@/stores/index";
import { getAll, deleteId, alters } from "@/axios/index";

const userId = Number(localStorage.getItem("userId")); //用户id
let option;
let flag = ref(true); //转换
const sto = svg(); //pinia数据
let bill = reactive([]); //渲染数据
let inMouth = []; //当月每日收入
let spMouth = []; //当月每日支出
let store = stores(); //pinia
let month = ref(new Date()); //月份
for (let i = 0; i < 32; i++) {
  inMouth[i] = 0;
  spMouth[i] = 0;
}
let chartDom;
let myChart;
//查询接口
const again = () => {
  getAll(userId).then((res) => {
    if (res.data.data != null) {
      let moth = month.value.getMonth() + 1;
      let shu = 0;
      for (let i = 0; i < res.data.data.length; i++) {
        let d = new Date(res.data.data[i].amount_time);
        let dM = d.getDate() - 1;
        let dMo = d.getMonth() + 1;
        if (moth == dMo && res.data.data[i].amount < 0) {
          spMouth[dM] += -res.data.data[i].amount;
        } else if (moth == dMo && res.data.data[i].amount > 0) {
          inMouth[dM] += res.data.data[i].amount;
        }
        if (moth == dMo) {
          bill.push(res.data.data[i]);
          bill[shu].amount_time =
            d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
          shu++;
        }
      }
    }
    if (bill == null) {
      flag.value = true;
    } else {
      flag.value = false;
    }
    myChart.setOption({
      xAxis: {
        axisTick: {
          alignWithLabel: true,
        },
      },
      yAxis: {},
      series: [
        {
          // 根据名字对应到相应的系列
          name: "支出",
          // barWidth: "60%",
          data: spMouth,
        },
        {
          // 根据名字对应到相应的系列
          name: "收入",
          data: inMouth,
        },
      ],
    });
  });
};
//Mounted生命周期 在里面进行表的异步渲染
onMounted(() => {
  chartDom = document.getElementById("myChart");
  myChart = echarts.init(chartDom);
  option = {
    tooltip: {
      trigger: "axis",
      axisPointer: {
        // 坐标轴指示器，坐标轴触发有效
        type: "shadow", // 默认为直线，可选为：'line' | 'shadow'
      },
    },
    legend: {
      show: true,
      selected: {
        支出: true,
        收入: true,
      },
      data: [
        {
          name: "支出",
          icon: "circle",
        },
        {
          name: "收入",
          icon: "circle",
        },
      ],
    },
    xAxis: {
      type: "category",
      data: [
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
        21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
      ],
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        name: "收入",
        type: "bar",
        data: [],
      },
      {
        name: "支出",
        type: "bar",
        data: [],
      },
    ],
  };
  again();
  option && myChart.setOption(option);
});
//监视月份的变化
watch(month, (newVal, oldVal) => {
  console.log(newVal, oldVal);
  for (let i = 0; i < 32; i++) {
    inMouth[i] = 0;
    spMouth[i] = 0;
  }
  bill.length = 0;
  again();
});
//返回首页
const atavisn = () => {
  router.push({
    name: "index",
  });
};
</script>
<style>
.wii {
  width: 5rem !important;
}
</style>

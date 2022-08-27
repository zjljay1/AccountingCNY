<template>
  <!-- 收入 -->
  <div class="grid grid-flow-col grid-cols-4 h-36 overflow-auto">
    <div
      v-for="(value, index) in incomeData"
      :key="index"
      class="m-4 flex flex-col items-center"
      @click="changeMode(value.name, value.id)"
    >
      <div>
        <span v-if="value.name == mode" class="flex h-3 w-3 absolute left-12">
          <span
            class="animate-ping absolute inline-flex h-full w-full rounded-full bg-purple-400 opacity-75"
          ></span>
          <span
            class="relative inline-flex rounded-full h-3 w-3 bg-purple-500"
          ></span>
        </span>
        <img :src="'http://127.0.0.1:5173/src' + value.url" class="h-10" />
      </div>
      <p class="text-sm">{{ value.name }}</p>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Tools, Histogram, Plus } from "@element-plus/icons-vue";
import { ref } from "vue";
import storeincome from "@/stores/index";
import svg from "@/stores/svg";
import { getCateAll } from "@/axios/index";
let incomeSvg = ref([]); //接口接受数据
let incomeData = ref([]); //页面渲染数据
let mode = ref("");
let store = storeincome(); //pinia状态数据
let svgid = svg(); //pinia状态数据
//查询接口
getCateAll().then((res) => {
  incomeSvg.value = res.data.data;
  for (let i = 0; i < incomeSvg.value.length; i++) {
    if (incomeSvg.value[i].sort == 1) {
      incomeData.value.push(incomeSvg.value[i]);
    }
  }
  for (let k = 0; k < incomeData.value.length; k++) {
    for (let t = 0; t < svgid.svgData.length; t++) {
      if (incomeData.value[k].svgid == svgid.svgData[t].svgid) {
        incomeData.value[k].url = svgid.svgData[t].url;
      }
    }
  }
});
//点击事件
const changeMode = (itme: any, id: any) => {
  mode.value = itme;
  store.category.income = itme;
  store.category.cate_id = id;
  store.category.sort = 1;
};
</script>

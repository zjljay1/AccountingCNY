<template>
  <!-- 支出 -->
  <div class="grid grid-cols-4 gap-3 h-36 overflow-auto">
    <div
      v-for="(val, index) in spendData"
      :key="index"
      class="flex flex-col items-center relative"
      @click="changeMode(val.name, val.id)"
    >
      <span v-if="val.name == mode" class="flex h-3 w-3 absolute left-4">
        <span
          class="animate-ping absolute inline-flex h-full w-full rounded-full bg-purple-400 opacity-75"
        ></span>
        <span
          class="relative inline-flex rounded-full h-3 w-3 bg-purple-500"
        ></span>
      </span>
      <img :src="'http://127.0.0.1:5173/src' + val.url" class="h-10" />
      <p class="text-sm">{{ val.name }}</p>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Tools, Histogram, Plus } from "@element-plus/icons-vue";
import { ref, reactive } from "vue";
import storebill from "@/stores/index";
import svg from "@/stores/svg";
import { getCateAll } from "@/axios/index";
let spendSvg = ref([]); //接口接受数据
let spendData = ref([]); //页面渲染数据
let mode = ref("");
let stoer = storebill(); //pinia状态数据
let svgid = svg(); //pinia状态数据
//查询接口
getCateAll().then((res) => {
  spendSvg.value = res.data.data;
  for (let i = 0; i < spendSvg.value.length; i++) {
    if (spendSvg.value[i].sort == 0) {
      spendData.value.push(spendSvg.value[i]);
    }
  }
  for (let k = 0; k < spendData.value.length; k++) {
    for (let t = 0; t < svgid.svgData.length; t++) {
      if (spendData.value[k].svgid == svgid.svgData[t].svgid) {
        spendData.value[k].url = svgid.svgData[t].url;
      }
    }
  }
});
//点击事件
const changeMode = (itme: any, id) => {
  mode.value = itme;
  stoer.category.spend = itme;
  stoer.category.cate_id = id;
  stoer.category.sort = 0;
  console.log(itme, id);
};
</script>

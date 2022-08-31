<template>
  <!-- 支出 -->
  <div class="grid grid-cols-4 gap-3 h-36 overflow-auto">
    <div
      v-for="(val, index) in spendData"
      :key="index"
      class="flex flex-col items-center relative"
      @click="changeMode(val.name, val.svgid)"
    >
      <div>
        <span v-if="val.svgid == mode" class="flex h-3 w-3 absolute left-12">
          <span
            class="animate-ping absolute inline-flex h-full w-full rounded-full bg-purple-400 opacity-75"
          ></span>
          <span
            class="relative inline-flex rounded-full h-3 w-3 bg-purple-500"
          ></span>
        </span>
        <img :src="'http://127.0.0.1:5173/src' + val.url" class="h-10" />
      </div>
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
import { acceptHMRUpdate } from "pinia";
let spendSvg = ref([]); //接口接受数据
let spendData = ref([]); //页面渲染数据
let mode = ref();
let stoer = storebill(); //pinia状态数据
let svgid = svg(); //pinia状态数据
mode.value = stoer.amount.category_id;
//查询接口
const againGet = () => {
  getCateAll().then((res) => {
    spendSvg.value = res.data.data;
    spendData.value = [];
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
};
againGet();
//点击事件
const changeMode = (itme: any, svgid) => {
  mode.value = svgid;
  stoer.category.spend = itme;
  stoer.category.cate_id = svgid;
  stoer.category.sort = 0;
  console.log(itme, svgid);
};
</script>

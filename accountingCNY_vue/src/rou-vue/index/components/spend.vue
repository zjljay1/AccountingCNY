<template>
  <!-- 支出 -->
  <div class="grid grid-cols-4 gap-3 h-52 overflow-auto">
    <div
      v-for="(val, index) in spendSvg"
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
import { ref } from "vue";
import storebill from "@/stores/index";
const spendSvg = [
  {
    url: "/img/spend-svg/repast.svg",
    name: "餐饮",
    id: 1,
  },
  {
    url: "/img/spend-svg/colthes.svg",
    name: "衣服",
    id: 4,
  },
  {
    url: "/img/spend-svg/fall-ill.svg",
    name: "医疗",
    id: 5,
  },
  {
    url: "/img/spend-svg/graze.svg",
    name: "零食",
    id: 6,
  },
  {
    url: "/img/spend-svg/keep.svg",
    name: "健身",
    id: 8,
  },
  {
    url: "/img/spend-svg/phone.svg",
    name: "电子产品",
    id: 3,
  },
  {
    url: "/img/spend-svg/supply.svg",
    name: "日用",
    id: 2,
  },
  {
    url: "/img/spend-svg/gift.svg",
    name: "礼物",
    id: 7,
  },
];
let mode = ref("");
let stoer = storebill();
const changeMode = (itme: any, id) => {
  mode.value = itme;
  stoer.category.spend = itme;
  stoer.category.cate_id = id;
  stoer.category.sort = 0;
};
</script>

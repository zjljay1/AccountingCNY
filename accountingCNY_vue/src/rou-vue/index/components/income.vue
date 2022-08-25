<template>
  <!-- 收入 -->
  <div class="grid grid-flow-col grid-cols-4">
    <div
      v-for="(value, index) in incomeSvg"
      :key="index"
      class="m-4 flex flex-col items-center"
      @click="changeMode(value.name, value.id)"
    >
      <span v-if="value.name == mode" class="flex h-3 w-3 relative left-4">
        <span
          class="animate-ping absolute inline-flex h-full w-full rounded-full bg-purple-400 opacity-75"
        ></span>
        <span
          class="relative inline-flex rounded-full h-3 w-3 bg-purple-500"
        ></span>
      </span>
      <img :src="'http://127.0.0.1:5173/src' + value.url" class="h-10" />
      <p class="text-sm">{{ value.name }}</p>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Tools, Histogram, Plus } from "@element-plus/icons-vue";
import { ref } from "vue";
import storeincome from "@/stores/index";
import { it } from "element-plus/es/locale";
let val = ref(null);
const incomeSvg = [
  {
    url: "/img/income-svg/salary.svg",
    name: "工资",
    id: 9,
  },
  {
    url: "/img/income-svg/moonlighting.svg",
    name: "兼职",
    id: 10,
  },
];
let mode = ref("");
let store = storeincome();
const changeMode = (itme: any, id: any) => {
  mode.value = itme;
  store.category.income = itme;
  store.category.cate_id = id;
  store.category.sort = 1;
};
</script>

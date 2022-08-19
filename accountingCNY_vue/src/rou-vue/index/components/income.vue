<template>
  <!-- 收入 -->
  <div class="flex justify-around flex-wrap items-baseline">
    <div @click="a">
      <img src="../../../assets/img/income-svg/salary.svg" />
      <p ref="val">工资</p>
    </div>
    <div @click="a">
      <img src="../../../assets/img/income-svg/moonlighting.svg" />
      <p ref="val">兼职</p>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Tools, Histogram, Plus } from "@element-plus/icons-vue";
import { ref } from "vue";
let val = ref(null);
const a = () => {
  console.log(123, val.value.innerText);
};
</script>
<style scoped>
img {
  height: 70px;
}
p {
  text-align: center;
}
</style>

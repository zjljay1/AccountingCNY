<template>
  <!-- 头部 -->
  <div class="flex justify-between">
    <el-icon class="m-3" @click="atavisn"><CloseBold /></el-icon>
    <h2>小猪存钱罐</h2>
    <div></div>
  </div>
  <!-- 存钱 -->
</template>
<script setup lang="ts">
import { CloseBold } from "@element-plus/icons-vue";
import router from "@/router";
const atavisn = () => {
  router.push({
    name: "index",
  });
};
</script>

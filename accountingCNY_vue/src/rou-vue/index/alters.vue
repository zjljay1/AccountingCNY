<template>
  <div>
    <!-- add头部区域 -->
    <div class="flex justify-between">
      <el-icon class="m-3" @click="atavisn"><CloseBold /></el-icon>
      <h2>修改</h2>
      <div></div>
    </div>
    <!-- 支出收入区域 -->
    <div>
      <el-tabs v-model="flag" type="border-card" stretch class="flex flex-col">
        <el-tab-pane label="支出" name="first">
          <spend_alter />
        </el-tab-pane>
        <el-tab-pane label="收入" name="second">
          <income_alter />
        </el-tab-pane>
      </el-tabs>
    </div>
    <!-- 账单信息区域 -->
    <div>
      <news_alter />
    </div>
  </div>
</template>
<script setup lang="ts">
import { Plus, Setting } from "@element-plus/icons-vue";
import { CloseBold } from "@element-plus/icons-vue";
import { reactive, ref } from "vue";
import income_alter from "./components/income_alter.vue";
import spend_alter from "./components/spend-alters.vue";
import router from "@/router";
import news_alter from "./components/news_alter.vue";
import stores from "@/stores/index";
let flag = ref("");
let sto = stores();
if (sto.amount.amount < 0) {
  flag.value = "first";
} else {
  flag.value = "second";
}
const atavisn = () => {
  router.push({
    name: "index",
  });
};
</script>
<style scoped></style>

<template>
  <div>
    <!-- add头部区域 -->
    <div class="flex justify-between">
      <el-icon class="m-3" @click="atavisn"><CloseBold /></el-icon>
      <h2>记一笔</h2>
      <div>
        <el-row>
          <el-button type="danger" color="#fff" round>
            <Plus style="color: #111; width: 20px" />
          </el-button>
        </el-row>
      </div>
    </div>
    <!-- 支出收入区域 -->
    <div>
      <el-tabs type="border-card" stretch class="flex flex-col">
        <el-tab-pane label="支出" name="first">
          <spend />
        </el-tab-pane>
        <el-tab-pane label="收入" name="second">
          <income />
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Plus } from "@element-plus/icons-vue";
import { CloseBold } from "@element-plus/icons-vue";
import { reactive } from "vue";
import income from "./components/income.vue";
import spend from "./components/spend.vue";
import router from "@/router";
const atavisn = () => {
  router.push({
    name: "index",
  });
};
</script>
<style scoped></style>

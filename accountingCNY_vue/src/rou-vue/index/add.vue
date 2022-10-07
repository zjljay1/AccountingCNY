<template>
  <div>
    <!-- add头部区域 -->
    <div class="flex justify-between">
      <el-icon class="m-3" @click="atavisn"><CloseBold /></el-icon>
      <h2>记一笔</h2>
      <div>
        <el-row>
          <el-button type="danger" color="#fff" round>
            <router-link to="/sort">
              <img src="@/assets/img/waii/piyyg.svg" class="w-10 h-10" />
            </router-link>
          </el-button>
        </el-row>
      </div>
    </div>
    <!-- 支出收入区域 -->
    <div>
      <el-tabs v-model="first" type="border-card" stretch class="flex flex-col">
        <el-tab-pane label="支出" name="first">
          <spend />
        </el-tab-pane>
        <el-tab-pane label="收入" name="second">
          <income />
        </el-tab-pane>
      </el-tabs>
    </div>
    <!-- 账单信息区域 -->
    <div>
      <news />
    </div>
  </div>
</template>
<script setup lang="ts">
import { Plus, Setting } from "@element-plus/icons-vue";
import { CloseBold } from "@element-plus/icons-vue";
import { reactive, ref } from "vue";
import income from "./components/income.vue";
import spend from "./components/spend.vue";
import router from "@/router";
import news from "./components/news.vue";
const first = ref("first");
const atavisn = () => {
  router.push({
    name: "index",
  });
};
</script>
<style scoped></style>

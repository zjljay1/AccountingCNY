<template>
  <div>
    <div class="flex justify-between">
      <el-icon class="m-3" @click="atavisn"><CloseBold /></el-icon>
      <h2>分类管理</h2>
    </div>
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
    <div class="add_sort">
      <router-link to="/add">
        <el-row>
          <el-button type="danger" color="#9776FB" round>
            <Plus style="color: #fff; width: 20px" />
          </el-button>
        </el-row>
      </router-link>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Tools, Histogram, Plus, Delete } from "@element-plus/icons-vue";
import { CloseBold } from "@element-plus/icons-vue";
import router from "@/router";
import { ref } from "vue";
const first = ref("first");
const atavisn = () => {
  router.push({
    name: "add",
  });
};
</script>
<style scoped>
.add_sort {
  position: fixed;
  bottom: 0%;
  left: 50%;
}
</style>

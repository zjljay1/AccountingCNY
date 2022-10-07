<template>
  <div>
    <el-container>
      <el-header
        ><div class="flex justify-between">
          <el-icon class="m-3" @click="atavisn"><CloseBold /></el-icon>
          <div><h2>个人设置</h2></div>
          <div></div></div
      ></el-header>
      <el-main
        ><el-form :model="form" label-width="120px">
          <el-form-item label="名称">
            <el-input v-model="form.name" placeholder="请输入您的名称" />
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" placeholder="请输入您的密码">
            </el-input>
          </el-form-item>
          <el-form-item label="手机号码">
            <el-input v-model="form.phone" placeholder="请输入您的手机号码">
            </el-input>
          </el-form-item>
          <el-form-item label="生日">
            <el-col :span="11">
              <el-date-picker
                v-model="form.birthday"
                type="date"
                placeholder="请选择日期"
                style="width: 100%"
              />
            </el-col>
          </el-form-item>
          <el-form-item label="个人简介">
            <el-input v-model="form.summary" type="textarea" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">确定修改</el-button>
            <el-button type="primary" @click="outSubmit">退出账号</el-button>
          </el-form-item>
        </el-form></el-main
      >
    </el-container>
  </div>
</template>
<script setup lang="ts">
import { ref, reactive } from "vue";
import router from "@/router/index";
import { CloseBold } from "@element-plus/icons-vue";
import { updateuser, getuserall } from "@/axios/index";
import { ElMessage } from "element-plus";

//返回首页
const atavisn = () => {
  router.push({
    name: "index",
  });
};
const id = localStorage.getItem("userId");
const form = reactive({
  id: id,
  name: "",
  password: "",
  phone: "",
  birthday: "",
  summary: "",
});
getuserall(id).then((res) => {
  form.name = res.data.data.name;
  form.password = res.data.data.password;
  form.phone = res.data.data.phone;
  form.birthday = res.data.data.birthday;
  form.summary = res.data.data.summary;
});
const open = () => {
  ElMessage({
    message: "退出成功",
    type: "success",
  });
};
const onSubmit = () => {
  updateuser(form).then((res) => {
    console.log(res);
  });
};
const outSubmit = () => {
  localStorage.removeItem("name");
  localStorage.removeItem("userId");
  localStorage.removeItem("token");
  open();
  router.push({
    name: "login",
  });
};
</script>

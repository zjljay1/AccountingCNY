<template>
  <div class="login">
    <div class="title"><h2>小猪记账本</h2></div>
    <el-form
      ref="ruleFormRef"
      :model="ruleForm"
      status-icon
      :rules="rules"
      class="demo-ruleForm"
    >
      <el-form-item label="" prop="name">
        <el-input
          v-model="ruleForm.name"
          type="text"
          autocomplete="off"
          placeholder="请输入用户名"
          clearable
          :prefix-icon="User"
        />
      </el-form-item>
      <el-form-item label="" prop="pass">
        <el-input
          v-model="ruleForm.pass"
          type="password"
          autocomplete="off"
          placeholder="请输入密码"
          clearable
          show-password
          :prefix-icon="GoodsFilled"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" class="btn" @click="submitForm(ruleFormRef)"
          >登录</el-button
        >
      </el-form-item>
      <el-form-item>
        <el-button type="default" class="btn" @click="submitForm_zc()"
          >注册</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>
<script lang="ts" setup>
import { reactive, ref } from "vue";
import type { FormInstance } from "element-plus";
import { User, GoodsFilled } from "@element-plus/icons-vue";
import { login, userId } from "../../axios/index";
import router from "@/router";
import { ElMessage } from "element-plus";
const ruleFormRef = ref<FormInstance>();

const validatePass = (rule: any, value: any, callback: any) => {
  console.log(rule);
  if (value === "") {
    callback(new Error("请输入密码"));
  } else {
    if (ruleForm.name !== "") {
      if (!ruleFormRef.value) return;
      ruleFormRef.value.validateField("checkPass", () => null);
    }
    callback();
  }
};
const validatePass2 = (rule: any, value: any, callback: any) => {
  console.log(rule);
  if (value === "") {
    callback(new Error("请输入用户名"));
  } else {
    callback();
  }
};

const ruleForm = reactive({
  pass: "",
  name: "",
});

const rules = reactive({
  pass: [{ validator: validatePass, trigger: "blur" }],
  name: [{ validator: validatePass2, trigger: "blur" }],
});

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      console.log("submit!");
      const da = { name: ruleForm.name, password: ruleForm.pass };
      login(da)
        .then((res) => {
          console.log("登录");
          if (res.data.code < 300) {
            localStorage.setItem("name", da.name);
            userId(da.name).then((res) => {
              localStorage.setItem("userId", res.data.data);
              open2();
              router.push({
                //编程式路由
                name: "index",
              });
            });
          }
        })
        .catch((error) => {
          console.log(error);
          open4();
        });
    } else {
      console.log("error submit!");
      return false;
    }
  });
};
// 注册
const submitForm_zc = () => {
  router.push({
    name: "regsiter",
  });
};
const open2 = () => {
  ElMessage({
    message: "登录成功",
    type: "success",
  });
};

const open4 = () => {
  ElMessage.error("登录失败");
  ruleForm.name = "";
  ruleForm.pass = "";
};
</script>
<style scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height: 100vh;
  background-image: url("@/assets/img/login-img/login.svg");
}
.title {
  margin-bottom: 30px;
}
.demo-ruleForm {
  width: 50%;
}
.btn {
  width: 100%;
}
</style>

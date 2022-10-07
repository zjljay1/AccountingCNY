<template>
  <div class="reg">
    <div class="title"><h2>小猪记账本</h2></div>
    <el-form
      ref="ruleFormRef"
      :model="ruleForm"
      status-icon
      :rules="rules"
      class="demo-ruleForm"
    >
      <el-form-item prop="name">
        <el-input
          v-model="ruleForm.name"
          type="text"
          autocomplete="off"
          placeholder="请输入用户名"
          clearable
          :prefix-icon="User"
        />
      </el-form-item>
      <el-form-item prop="pass">
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
      <el-form-item prop="checkPass">
        <el-input
          v-model="ruleForm.checkPass"
          type="passwrod"
          placeholder="请再次输入密码"
          clearable
          show-password
          :prefix-icon="GoodsFilled"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" class="btn" @click="submitForm(ruleFormRef)"
          >注册</el-button
        >
      </el-form-item>
      <el-form-item>
        <el-button type="default" class="btn" @click="submitForm_zc()"
          >登录</el-button
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
import { register } from "@/axios/index";

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
    callback(new Error("请再次输入密码"));
  } else if (value !== ruleForm.pass) {
    callback(new Error("两个输入不匹配!"));
  } else {
    callback();
  }
};
const valiName = (rule: any, value: any, callback: any) => {
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
  checkPass: "",
});

const rules = reactive({
  pass: [{ validator: validatePass, trigger: "blur" }],
  name: [{ validator: valiName, trigger: "blur" }],
  checkPass: [{ validator: validatePass2, trigger: "blur" }],
});

let alt = ref(false);
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      console.log("submit!");
      const data = {
        name: ruleForm.name,
        password: ruleForm.pass,
      };
      console.log(data);
      register(data).then((res) => {
        console.log(res.data);
        open2();
      });
    } else {
      console.log("error submit!");
      return false;
    }
  });
};
// 返回登录
const submitForm_zc = () => {
  router.push({
    name: "login",
  });
};
const open2 = () => {
  ElMessage({
    message: "注册成功",
    type: "success",
  });
};
</script>
<style scoped>
.reg {
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

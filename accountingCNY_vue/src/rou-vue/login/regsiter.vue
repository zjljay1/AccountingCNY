<template>
  <el-alert
    v-if="alt"
    type="success"
    description="注册成功,请返回登录页 登录"
    show-icon
    center
    :closable="false"
  />
  <div class="flex justify-center items-center h-screen">
    <div class="form">
      <h1>REGSITER</h1>
      <div class="user">
        <img src="../../assets/img/login-img/username.png" />
        <input v-model="name" type="text" placeholder="Username" />
      </div>
      <div class="pass">
        <img src="../../assets/img/login-img/password.png" />
        <input v-model="pws" type="text" placeholder="Password" />
      </div>
      <div class="pass">
        <img src="../../assets/img/login-img/手机.png" />
        <input v-model="phone" type="text" placeholder="phone" />
      </div>
      <div class="enter">
        <button @click="reigster">注册</button>
      </div>
      <div class="chek">
        <router-link to="/"><button>登录账号</button></router-link>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { register } from "@/axios/index";
import { ref } from "vue";
import router from "@/router";

let name = ref();
let pws = ref();
let phone = ref();
let alt = ref(false);
const reigster = () => {
  const data = {
    name: name.value,
    password: pws.value,
    phone: phone.value,
  };
  console.log(data);
  register(data).then((res) => {
    console.log(res.data);
    alt.value = true;
    setTimeout(() => {
      alt.value = false;
    }, 2000);
  });
};
</script>
<style scoped>
img {
  height: 25px;
  width: 40px;
}
.form {
  height: 400px;
  width: 400px;
  background-color: #ffffff;
  border-radius: 30px;
  /* box-shadow: 10px 10px 5px #9c9b99; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.form h1 {
  margin-bottom: 70px;
  font-size: 50px;
  color: #000000;
}
.user,
.pass {
  display: flex;
  align-items: center;
}
.user img,
.pass img {
  padding-right: 20px;
}

.enter,
.user,
.pass {
  margin-top: 15px;
}
.enter button {
  height: 30px;
  width: 100px;
  background-color: #edf6f5;
  border-style: none;
  cursor: pointer;
}

.user input,
.pass input {
  border-top-style: none;
  border-right-style: none;
  border-bottom-style: solid;
  border-left-style: none;
  border-color: #e7e7e7;
  outline: none;
}

input::-webkit-input-placeholder {
  color: #cacfce;
  margin-left: 10px;
}

.chek button {
  color: #d7c19e;
  margin-top: 15px;
  border: 0px;
}
</style>

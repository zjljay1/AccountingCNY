<template>
  <div class="login">
    <div class="form">
      <h1>LOGIN</h1>
      <div class="user">
        <img src="../../assets/img/login-img/username.png" />
        <input v-model="data.name" type="text" placeholder="Username" />
      </div>
      <div class="pass">
        <img src="../../assets/img/login-img/password.png" />
        <input v-model="data.pws" type="text" placeholder="Password" />
      </div>
      <div class="enter">
        <button @click="fa.dpush">登录</button>
      </div>
      <div class="chek">
        <router-link to="/regsiter"><button>注册账号</button></router-link>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { login, userId } from "../../axios/index";
import { reactive } from "vue";
import router from "@/router";
import { ElMessage } from "element-plus";

const data = reactive({ name: "", pws: "" });
const fa = reactive({
  //登录接口
  dpush() {
    const da = { name: data.name, password: data.pws };
    login(da)
      .then((res) => {
        if (res.data.code < 300) {
          localStorage.setItem("name", data.name);
          userId(data.name).then((res) => {
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
  },
});

const open2 = () => {
  ElMessage({
    message: "登录成功",
    type: "success",
  });
};

const open4 = () => {
  ElMessage.error("登录失败");
};
</script>

<style scoped>
img {
  height: 25px;
  width: 40px;
}
.form {
  height: 100vh;
  /* width: 400px; */
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

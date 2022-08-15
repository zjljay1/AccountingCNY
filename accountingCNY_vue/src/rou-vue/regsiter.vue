<template>
  <div class="reg">
    <div class="form">
      <h1>REGSITER</h1>
      <div class="user">
        <img src="../assets/img/username.png" />
        <input id="name" type="text" placeholder="Username" />
      </div>
      <div class="pass">
        <img src="../assets/img/password.png" />
        <input id="pws" type="text" placeholder="Password" />
      </div>
      <div class="pass">
        <img src="../assets/img/手机.png" />
        <input id="pws" type="text" placeholder="phone" />
      </div>
      <div class="enter">
        <button>注册</button>
      </div>
      <div class="chek">
        <router-link to="/"><button>登录账号</button></router-link>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts"></script>
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
  box-shadow: 10px 10px 5px #9c9b99;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.form h1 {
  margin-bottom: 70px;
  font-size: 50px;
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

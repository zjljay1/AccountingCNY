<template>
  <div class="">
    <div class="flex justify-center">
      <div class="">
        <el-date-picker
          v-model="date"
          type="datetime"
          format="MM/DD hh"
          :editable="false"
          :clearable="false"
          class="ass"
        />
      </div>
      <div class="">
        <el-input v-model="note" placeholder="备注" :clearable="false" />
      </div>
    </div>
    <div class="flex justify-center mt-9">
      <el-input
        v-model="amount"
        placeholder="输入金额"
        :clearable="false"
        class="acc"
      />
      <el-button type="primary" @click="push">确定</el-button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import bill from "@/stores/index";
import { addbill, alters } from "@/axios/index";
import router from "@/router/index";
const note = ref(""); //备注
let amount = ref(""); //金额
let store = bill();
let date = ref(new Date(store.amount.amount_time)); //时;
if (store.amount.amount < 0) {
  amount.value = -store.amount.amount;
} else {
  amount.value = store.amount.amount;
}
note.value = store.amount.note;
const push = () => {
  if (store.category.sort == 0) {
    amount.value = amount.value * -1;
  }
  const data = {
    id: store.amount.id,
    amount: amount.value,
    amount_time: date.value,
    category_id: store.category.cate_id,
    user_id: Number(localStorage.getItem("userId")),
    note: note.value,
  };
  alters(data).then((res) => {
    console.log(res.data);
    router.push({
      name: "index",
    });
  });
};
</script>
<style>
.ass {
  width: 100px !important;
}
.acc {
  width: 200px !important;
}
</style>

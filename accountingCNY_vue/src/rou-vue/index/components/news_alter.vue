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
const note = ref(""); //备注
let amount = ref(""); //金额
let date = ref(new Date()); //时;
let store = bill();
if (store.amount.amount < 0) {
  amount.value = -store.amount.amount;
} else {
  amount.value = store.amount.amount;
}
note.value = store.amount.note;
date.value = store.amount.amount_time;
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
  console.log(data);
  alters(data)
    .then((res) => {
      console.log(res.data);
    })
    .catch((error) => {
      console.log(error.data);
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

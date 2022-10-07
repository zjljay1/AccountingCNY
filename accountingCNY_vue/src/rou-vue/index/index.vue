<template>
  <!-- 首页布局 -->
  <el-container class="home">
    <!-- 头部区域 -->
    <el-header class="top">
      <!-- 设置，账单的路由导航 -->
      <div class="t_top">
        <router-link to="/setUp">
          <Tools style="width: 20px; color: #fff" />
        </router-link>
        <router-link to="/bill">
          <Histogram style="width: 20px; color: #fff" />
        </router-link>
      </div>
      <!-- 首页收入 支出展示 -->
      <div class="t_bot">
        <div class="income text-white">
          <span>本月支出（￥）</span>
          <h1>{{ spend }}</h1>
        </div>
        <p>|</p>
        <div class="spend text-white">
          <span>本月收入（￥）</span>
          <h1>{{ income }}</h1>
        </div>
      </div>
    </el-header>
    <!-- 首页账单详情区域 -->
    <el-main class="bot">
      <!-- 首页账单详情展示区域 -->
      <div class="ins">
        <p v-if="flag" class="flex justify-center">
          本月未开始记账<br />点击加号，开始记账
        </p>
        <div v-for="(value, index) in bill" :key="index">
          <el-timeline>
            <el-timeline-item
              center
              :timestamp="value.amount_time"
              placement="top"
            >
              <el-popconfirm
                confirm-button-text="修改"
                cancel-button-text="删除"
                icon-color="#626AEF"
                title="请进行你的操作"
                cancel-button-type="primary"
                @confirm="alerts(value)"
                @cancel="Deletes(value.id)"
              >
                <template #reference>
                  <el-card
                    :body-style="{
                      display: 'flex',
                      justifyContent: 'space-between',
                    }"
                    class="h-26"
                  >
                    <div class="flex flex-col">
                      <div v-for="(val, key) in sto.svgData" :key="key">
                        <h2
                          v-if="sto.svgData[key].svgid == value.category_id"
                          class="flex h-6"
                        >
                          <img
                            :src="'http://127.0.0.1:5173/src' + val.url"
                            class="h-8"
                          />
                          {{ val.name }}
                        </h2>
                      </div>
                      <p class="text-gray-400">{{ value.note }}</p>
                    </div>
                    <h1>{{ value.amount }}</h1>
                  </el-card>
                </template>
              </el-popconfirm>
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>
      <!-- 首页账单类别添加区域 -->
      <div class="add">
        <router-link to="/add">
          <el-row>
            <el-button type="danger" color="#9776FB" round>
              <Plus style="color: #fff; width: 20px" />
            </el-button>
          </el-row>
        </router-link>
      </div>
      <div class="pig">
        <img
          src="@/assets/img/waii/piyyg.svg"
          class="h-14 w-14"
          @click="pigyg = true"
        />
      </div>
      <el-dialog v-model="pigyg" title="小猪存钱罐" width="80%">
        <el-input
          v-model="num"
          type="Number"
          placeholder="请输入金额"
        ></el-input>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="pigyg = false">取消</el-button>
            <el-button type="primary" @click="defin">确定</el-button>
          </div>
        </template>
      </el-dialog>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { Tools, Histogram, Plus, Delete } from "@element-plus/icons-vue";
import { getAll, deleteId } from "@/axios/index";
import { ref, reactive, computed } from "vue";
import svg from "@/stores/svg";
import router from "@/router";
import stores from "@/stores/index";
const userId = Number(localStorage.getItem("userId")); //用户id
let flag = ref(true); //转换
const sto = svg(); //pinia数据
let bill = reactive([]); //渲染数据
let income = ref(0); //收入
let spend = ref(0); //支出
let store = stores();
let datetime = ref(new Date());
//渲染接口
const againGet = () => {
  console.log("渲染");
  getAll(userId).then((res) => {
    if (res.data.data != null) {
      income.value = 0;
      spend.value = 0;
      bill.length = 0;
      for (let a = 0; a < res.data.data.length; a++) {
        let d = new Date(res.data.data[a].amount_time);
        let dMo = d.getMonth();
        if (datetime.value.getMonth() == dMo) {
          bill.push(res.data.data[a]);
        }
      }
      bill.sort((a, b) => {
        return (
          new Date(b.amount_time).getTime() - new Date(a.amount_time).getTime()
        );
      });

      if (bill == null) {
        flag.value = true;
      } else {
        flag.value = false;
        for (let i = 0; i < bill.length; i++) {
          let d = new Date(bill[i].amount_time);
          bill[i].amount_time =
            d.getFullYear() +
            "-" +
            (d.getMonth() + 1) +
            "-" +
            d.getDate() +
            " " +
            d.getHours() +
            ":" +
            d.getMinutes();
          if (bill[i].amount > 0) {
            income.value += bill[i].amount;
          } else {
            spend.value += bill[i].amount;
          }
        }
      }
    }
  });
};
againGet();
//删除接口
const Deletes = (id: Number) => {
  deleteId(id).then((res) => {
    console.log(res);
    for (let q = 0; q < bill.length; q++) {
      if (bill[q].id == id) {
        if (bill[q].amount > 0) {
          income.value -= bill[q].amount;
        } else {
          spend.value -= bill[q].amount;
        }
        bill.splice(q, 1);
      }
    }
    if (bill.length == 0) {
      flag.value = true;
    } else {
      flag.value = false;
    }
  });
};
//修改接口
const alerts = (data: any) => {
  store.amount = data;
  if (data.amount < 0) {
    store.category.sort = 0;
  } else {
    store.category.sort = 1;
  }
  router.push({
    name: "alters",
  });
};
//存钱罐
let pigyg = ref(false);
let num = ref();
const defin = () => {
  console.log(12);
};
</script>
<style scoped>
.bot {
  display: flex;
  flex-direction: column;
}
.bot .ins {
  flex: 9;
  display: flex;
  flex-direction: column;
}
.bot .ins p {
  color: #646d77;
  font-weight: 100;
  line-height: 40px;
  text-indent: 1em;
}
.bot .add {
  position: fixed;
  bottom: 0%;
  left: 50%;
}
.home {
  display: flex;
  flex-direction: column;
  height: 100vh;
}
.top {
  /* background-color: #7797ff; */
  background-image: linear-gradient(to right, #5284c0, #67c2b3);
  /* height: 210px; */
  flex: 3;
}
.t_bot .income h1,
.t_bot .spend h1 {
  text-align: center;
}
.bot {
  /* background-color: #3d3e52; */
  flex: 7;
}
.t_top {
  display: flex;
  justify-content: space-between;
  margin: 10px 10px;
}
.t_bot {
  display: flex;
  justify-content: space-around;
  align-items: center;
  height: 200px;
}
.t_bot p {
  margin-top: 35px;
  color: #c1cfde;
}
</style>

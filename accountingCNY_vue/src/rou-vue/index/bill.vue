<template>
  <!-- 头部区域 -->
  <div class="flex justify-between">
    <el-icon class="m-3" @click="atavisn"><CloseBold /></el-icon>
    <h2>统计</h2>
    <div></div>
  </div>
  <!-- 表区域 -->
  <div
    id="myChart"
    ref="myChart"
    class="flex justify-center items-center h-96 w-full"
  ></div>
  <div class="ins">
    <p v-if="flag" class="flex justify-center">
      本月未开始记账<br />点击加号，开始记账
    </p>
    <div
      v-for="(value, index) in bill"
      :key="index"
      @touchstart.prevent="pet"
      @touchend.prevent="pct(value)"
    >
      <el-timeline>
        <el-timeline-item center :timestamp="value.amount_time" placement="top">
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
                  v-if="sto.svgData[key].id == value.category_id"
                  class="flex h-6"
                >
                  <img
                    :src="'http://127.0.0.1:5173/src' + val.url"
                    class="h-8"
                  />
                  {{ val.name }}
                </h2>
              </div>
              <p class="text-gray-400 mt-2">{{ value.note }}</p>
            </div>
            <h1>{{ value.amount }}</h1>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>

  <el-dialog v-model="revele" title="修改或删除类型" :width="formLabelWidth">
    <el-form :model="form">
      <el-form-item label="请输入修改的类型名称">
        <el-input v-model="ADname.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="请选择类型图片">
        <div
          v-for="(value, index) in svgid.svgData"
          :key="index"
          class="m-4 flex flex-col items-center"
          @click="ade(value.id)"
        >
          <div>
            <span
              v-if="value.id == modee"
              class="flex h-3 w-3 absolute left-12"
            >
              <span
                class="animate-ping absolute inline-flex h-full w-full rounded-full bg-purple-400 opacity-75"
              ></span>
              <span
                class="relative inline-flex rounded-full h-3 w-3 bg-purple-500"
              ></span>
            </span>
            <img :src="'http://127.0.0.1:5173/src' + value.url" class="h-10" />
          </div>
        </div>
      </el-form-item>
    </el-form>
    <!-- 删除 修改按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button type="danger" @click="nested = true">删除</el-button>
        <el-button type="success" @click="Alter">修改</el-button>
      </span>
    </template>
    <el-dialog v-model="nested" width="50%" append-to-body>
      <p>确定删除{{ ADname.name }}的数据吗？</p>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="success" @click="Detele">确定删除</el-button>
          <el-button type="default" @click="nested = false">取消删除</el-button>
        </span>
      </template>
    </el-dialog>
  </el-dialog>
</template>

<script setup lang="ts">
import { onMounted } from "vue";
import * as echarts from "echarts";
import { CloseBold } from "@element-plus/icons-vue";
import router from "@/router/index";
import { ref, reactive } from "vue";
import svg from "@/stores/svg";
import stores from "@/stores/index";
import { getAll, deleteId, alters } from "@/axios/index";

const userId = Number(localStorage.getItem("userId")); //用户id
let option;
let flag = ref(true); //转换
const sto = svg(); //pinia数据
let bill = reactive([]); //渲染数据
let income = ref([]); //收入
let spend = ref([]); //支出
let store = stores(); //pinia
let date = ref([]);
let svgid = svg();
let myChart: any;
//查询接口
const again = () => {
  getAll(userId).then((res) => {
    console.log(option.legend.selected);
    if (res.data.data != null) {
      bill.length = 0;
      for (let i = 0; i < res.data.data.length; i++) {
        if (res.data.data[i].amount < 0) {
          spend.value[i] = -res.data.data[i].amount;
        } else {
          income.value[i] = res.data.data[i].amount;
        }
        bill.push(res.data.data[i]);
        let d = new Date(res.data.data[i].amount_time);
        bill[i].amount_time =
          d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
        date.value[i] = d.getMonth() + 1 + "-" + d.getDate();
      }
    }
    if (bill == null) {
      flag.value = true;
    } else {
      flag.value = false;
    }
    myChart.setOption({
      xAxis: {
        data: date.value,
      },
      yAxis: {},
      series: [
        {
          // 根据名字对应到相应的系列
          name: "支出",
          data: spend.value,
        },
        {
          // 根据名字对应到相应的系列
          name: "收入",
          data: income.value,
        },
      ],
    });
  });
};
//Mounted生命周期 在里面进行表的异步渲染
onMounted(() => {
  const chartDom = document.getElementById("myChart");
  myChart = echarts.init(chartDom);

  option = {
    tooltip: {
      trigger: "axis",
      axisPointer: {
        // 坐标轴指示器，坐标轴触发有效
        type: "shadow", // 默认为直线，可选为：'line' | 'shadow'
      },
    },
    legend: {
      show: true,
      // formatter: (name) => {
      //   console.log(name, option.legend.selected);
      // },
      selected: {
        支出: true,
        收入: true,
      },
      data: [
        {
          name: "支出",
          icon: "circle",
        },
        {
          name: "收入",
          icon: "circle",
        },
      ],
    },
    xAxis: {
      data: [],
    },
    yAxis: {},
    series: [
      {
        name: "收入",
        type: "bar",
        data: [],
      },
      {
        name: "支出",
        type: "bar",
        data: [],
      },
    ],
  };
  getAll(userId).then((res) => {
    console.log(option.legend.selected);
    if (res.data.data != null) {
      for (let i = 0; i < res.data.data.length; i++) {
        if (res.data.data[i].amount < 0) {
          spend.value[i] = -res.data.data[i].amount;
        } else {
          income.value[i] = res.data.data[i].amount;
        }
        bill.push(res.data.data[i]);
        let d = new Date(res.data.data[i].amount_time);
        bill[i].amount_time =
          d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
        date.value[i] = d.getMonth() + 1 + "-" + d.getDate();
      }
    }
    if (bill == null) {
      flag.value = true;
    } else {
      flag.value = false;
    }
    myChart.setOption({
      xAxis: {
        data: date.value,
      },
      yAxis: {},
      series: [
        {
          // 根据名字对应到相应的系列
          name: "支出",
          data: spend.value,
        },
        {
          // 根据名字对应到相应的系列
          name: "收入",
          data: income.value,
        },
      ],
    });
  });
  option && myChart.setOption(option);
});
//返回首页
const atavisn = () => {
  router.push({
    name: "index",
  });
};
//删除和修改\
const formLabelWidth = "80%"; //窗口总宽度
const form = reactive({
  name: "",
});
let dd: Date;
let de: Date;
let revele = ref(false);
let alterData = ref();
let nested = ref(false);
let ADname = reactive({
  name: "",
});
let modee = ref();
const ade = (id: any) => {
  modee.value = id;
};
//按下事件
const pet = () => {
  dd = new Date();
};
//松开事件
const pct = (data: any) => {
  de = new Date();
  alterData.value = data;
  ADname.name = data.name;
  modee.value = data.svgid;
  console.log(data);
  const ddTime = dd.getTime();
  const deTime = de.getTime();
  if (deTime - ddTime > 200) {
    revele.value = true;
  }
};
//修改事件
const Alter = () => {
  revele.value = false;
  if (ADname.name != "") {
    alterData.value.name = ADname.name;
  }
  if (modee.value != null) {
    alterData.value.svgid = modee.value;
  }
  alters(alterData.value).then((res) => {
    console.log(res);
    again();
  });
};
//删除事件
const Detele = () => {
  let deleteid = alterData.value.id;
  revele.value = false;
  nested.value = false;
  //删除
  deleteId(deleteid).then((res) => {
    console.log(res);
    again();
  });
};
</script>

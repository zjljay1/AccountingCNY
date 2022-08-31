<template>
  <!-- 收入 -->
  <div class="grid grid-cols-4 gap-3 h-96 overflow-auto">
    <div
      v-for="(value, index) in incomeData"
      :key="index"
      class="m-4 flex flex-col items-center"
      @touchstart.prevent="pet"
      @touchend.prevent="pct(value)"
    >
      <div>
        <span v-if="value.svgid == mode" class="flex h-3 w-3 absolute left-12">
          <span
            class="animate-ping absolute inline-flex h-full w-full rounded-full bg-purple-400 opacity-75"
          ></span>
          <span
            class="relative inline-flex rounded-full h-3 w-3 bg-purple-500"
          ></span>
        </span>
        <img :src="'http://127.0.0.1:5173/src' + value.url" class="h-10" />
      </div>
      <p class="text-sm">{{ value.name }}</p>
    </div>
    <!-- 添加类型 -->
    <div
      class="flex flex-col items-center mt-4"
      @click="dialogFormVisible = true"
    >
      <img src="@/img/add.svg" class="h-10" />
      <p>添加类型</p>
    </div>
    <!-- 排序 -->
    <div class="flex flex-col items-center mt-4" @click="sortF = true">
      <img src="@/img/sort.svg" class="h-10" />
      <p>排序</p>
    </div>
  </div>
  <!-- 添加按钮展示 -->
  <el-dialog
    v-model="dialogFormVisible"
    title="添加收入类型"
    :width="formLabelWidth"
  >
    <el-form :model="form">
      <el-form-item label="请输入类型名称">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="请选择类型图片">
        <div
          v-for="(value, index) in svgid.svgData"
          :key="index"
          class="m-4 flex flex-col items-center"
          @click="changeMode(value.name, value.id)"
        >
          <div>
            <span v-if="value.id == mode" class="flex h-3 w-3 absolute left-12">
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
    <!-- 确定取消按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="add">确定</el-button>
      </span>
    </template>
  </el-dialog>
  <!-- 修改and删除 -->
  <el-dialog v-model="revele" title="修改或删除类型" :width="formLabelWidth">
    <el-form :model="form">
      <el-form-item label="请输入修改的类型名称" :label-width="nameWidhttow">
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
        <el-button type="danger" @click="innerVisible = true">删除</el-button>
        <el-button type="success" @click="Alter">修改</el-button>
      </span>
    </template>
    <el-dialog v-model="innerVisible" width="50%" append-to-body>
      <p>将删除{{ ADname.name }}类别的数据</p>
      <template #footer>
        <el-button type="success" @click="Detele">确定删除</el-button>
        <el-button type="default" @click="innerVisible = false"
          >取消删除</el-button
        ></template
      ></el-dialog
    >
  </el-dialog>

  <!-- 排序窗口 -->
  <el-dialog v-model="sortF" :width="formLabelWidth">
    <el-form :model="form">
      <el-col :xs="25" :sm="8" :md="8" :lg="8">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>长按进行排序</span>
            </div>
          </template>
          <!-- 单列拖拽 -->
          <draggable
            v-model="incomeData"
            item-key="name"
            chosen-class="chosen"
            force-fallback="true"
            animation="300"
            @change="vote"
          >
            <template #item="{ element }">
              <div class="item-single">{{ element.name }}</div>
            </template>
          </draggable>
        </el-card>
      </el-col></el-form
    >
    <!-- 确定取消按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="sortF = false">取消</el-button>
        <el-button type="primary" @click="sorts(incomeData)">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup lang="ts">
import { Tools, Histogram, Plus } from "@element-plus/icons-vue";
import { ref, reactive } from "vue";
import storeincome from "@/stores/index";
import svg from "@/stores/svg";
import {
  getCateAll,
  addCate,
  DeleteCate,
  alterCate,
  sort,
} from "@/axios/index";
import draggable from "vuedraggable";
let incomeSvg = ref([]); //接口接受数据
let incomeData = ref([]); //页面渲染数据
let mode = ref();
let store = storeincome(); //pinia状态数据
let svgid = svg(); //pinia状态数据
//查询接口
const againGet = () => {
  getCateAll().then((res) => {
    incomeSvg.value = res.data.data;
    incomeData.value = [];
    for (let i = 0; i < incomeSvg.value.length; i++) {
      if (incomeSvg.value[i].sort == 1) {
        incomeData.value.push(incomeSvg.value[i]);
      }
    }
    for (let k = 0; k < incomeData.value.length; k++) {
      for (let t = 0; t < svgid.svgData.length; t++) {
        if (incomeData.value[k].svgid == svgid.svgData[t].svgid) {
          incomeData.value[k].url = svgid.svgData[t].url;
        }
      }
    }
    incomeData.value.sort((a, b) => {
      return a.sequencing - b.sequencing;
    });
  });
};
againGet();
//点击事件
const changeMode = (itme: any, id: any) => {
  mode.value = id;
  store.category.income = itme;
  store.category.cate_id = id;
  store.category.sort = 1;
  console.log(itme, id);
};
//添加类型
const dialogFormVisible = ref(false); //窗口是否展示
const formLabelWidth = "80%"; //窗口总宽度
const form = reactive({
  name: "",
});
//添加事件
const add = () => {
  dialogFormVisible.value = false;
  const dates = {
    name: form.name,
    sort: 1,
    svgid: mode.value,
    user_id: Number(localStorage.getItem("userId")),
    sequencing: -1,
  };
  addCate(dates).then((res) => {
    console.log(res.data);
    againGet();
  });
};

//删除和修改
let dd: Date;
let de: Date;
let revele = ref(false);
const nameWidhttow = "160px";
let alterData = ref();
const innerVisible = ref(false);
let ADname = reactive({
  name: "",
});
let modee = ref();
const ade = (id: any) => {
  console.log(id);
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
  } else {
    changeMode(data.name, data.svgid);
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
  alterCate(alterData.value).then((res) => {
    console.log(res);
    againGet();
  });
};
//删除事件
const Detele = () => {
  let deleteid = alterData.value.id;
  revele.value = false;
  innerVisible.value = false;
  //删除
  DeleteCate(deleteid).then((res) => {
    console.log(res);
    againGet();
  });
};

//排序
const sortF = ref(false);
const vote = () => {};
const sorts = (data) => {
  sortF.value = false;
  let size = 0;
  console.log(incomeData.value);
  for (let k = 0; k < incomeSvg.value.length; k++) {
    if (incomeSvg.value[k].sort == 1) {
      size++;
    }
  }
  for (let i = 0; i < size; i++) {
    data[i].sequencing = i;
    console.log(data[i]);
    sort(data[i]).then((res) => {
      console.log(res);
    });
  }
};
</script>
<style>
.item-single {
  font-size: 1.5em;
  height: 57px;
  text-align: center;
  line-height: 55px;
  border: 1px solid #e5e4e9;
  cursor: move;
}
</style>

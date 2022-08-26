import { defineStore } from "pinia";

const indexStore = defineStore("index", {
  state: () => {
    return {
      category: {
        spend: "", //支出的类别名称
        income: "", //输入的类别名称
        cate_id: Number, //类别表id 后端根据这个id进行添加操作
        sort: -1, // 当前点击的账单类别属性 0表示支出 1表示收入
      },
      user: {
        id: 0, //用户id
        name: String, //用户名称
      },
      index: {},
    };
  },
  getters: {},
  actions: {},
});

export default indexStore;

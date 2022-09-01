import { defineStore } from "pinia";

const indexStore = defineStore("index", {
  state: () => {
    return {
      category: {
        spend: "", //支出的类别名称
        income: "", //输入的类别名称
        cate_id: 0, //类别表id 后端根据这个id进行添加操作
        sort: -1, // 当前点击的账单类别属性 0表示支出 1表示收入
      },
      user: {
        id: 0, //用户id
        name: "", //用户名称
      },
      index: {},
      amount: {
        amount: 0, //金额
        amount_time: Object, //时间
        category_id: 0, //类型id
        id: 0, //账单id
        note: "", //备注
      },
    };
  },
  getters: {},
  actions: {},
});

export default indexStore;

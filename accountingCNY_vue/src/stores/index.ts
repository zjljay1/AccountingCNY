import { defineStore } from "pinia";

const indexStore = defineStore("index", {
  state: () => {
    return {
      category: {
        spend: "",
        income: "",
        cate_id: Number,
        sort: -1,
      },
      user: {
        id: 0,
        name: String,
      },
      index: {},
    };
  },
  getters: {},
  actions: {},
});

export default indexStore;

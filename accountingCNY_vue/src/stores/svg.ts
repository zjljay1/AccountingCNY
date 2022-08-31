import { defineStore } from "pinia";

const svg = defineStore("svg", {
  state: () => {
    return {
      svgData: [
        {
          url: "/img/spend-svg/repast.svg", //svg的url路径
          name: "餐饮", //类别名称
          id: 1, //数据id
          sort: 0, //0表示支出 1表示收入
          svgid: 1, //svg的id 可以根据这个id获取svg的url路径
        },
        {
          url: "/img/spend-svg/colthes.svg",
          name: "衣服",
          id: 4,
          sort: 0,
          svgid: 4,
        },
        {
          url: "/img/spend-svg/fall-ill.svg",
          name: "医疗",
          id: 5,
          sort: 0,
          svgid: 5,
        },
        {
          url: "/img/spend-svg/graze.svg",
          name: "零食",
          id: 6,
          sort: 0,
          svgid: 6,
        },
        {
          url: "/img/spend-svg/keep.svg",
          name: "健身",
          id: 8,
          sort: 0,
          svgid: 8,
        },
        {
          url: "/img/spend-svg/phone.svg",
          name: "电子产品",
          id: 3,
          sort: 0,
          svgid: 3,
        },
        {
          url: "/img/spend-svg/supply.svg",
          name: "日用",
          id: 2,
          sort: 0,
          svgid: 2,
        },
        {
          url: "/img/spend-svg/gift.svg",
          name: "礼物",
          id: 7,
          sort: 0,
          svgid: 7,
        },
        {
          url: "/img/income-svg/salary.svg",
          name: "薪资",
          id: 9,
          sort: 1,
          svgid: 9,
        },
        {
          url: "/img/income-svg/moonlighting.svg",
          name: "兼职",
          id: 10,
          sort: 1,
          svgid: 10,
        },
        {
          url: "/img/spend-svg/pet.svg",
          name: "宠物",
          id: 11,
          sort: 0,
          svgid: 11,
        },
        {
          url: "/img/spend-svg/trip.svg",
          name: "出行",
          id: 12,
          sort: 0,
          svgid: 12,
        },
      ],
    };
  },
  getters: {},
  actions: {},
});

export default svg;

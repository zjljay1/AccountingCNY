import { defineStore } from "pinia";

const svg = defineStore("svg", {
  state: () => {
    return {
      svgData: [
        {
          url: "/img/spend-svg/repast.svg",
          name: "餐饮",
          id: 1,
        },
        {
          url: "/img/spend-svg/colthes.svg",
          name: "衣服",
          id: 4,
        },
        {
          url: "/img/spend-svg/fall-ill.svg",
          name: "医疗",
          id: 5,
        },
        {
          url: "/img/spend-svg/graze.svg",
          name: "零食",
          id: 6,
        },
        {
          url: "/img/spend-svg/keep.svg",
          name: "健身",
          id: 8,
        },
        {
          url: "/img/spend-svg/phone.svg",
          name: "电子产品",
          id: 3,
        },
        {
          url: "/img/spend-svg/supply.svg",
          name: "日用",
          id: 2,
        },
        {
          url: "/img/spend-svg/gift.svg",
          name: "礼物",
          id: 7,
        },
        {
          url: "/img/income-svg/salary.svg",
          name: "工资",
          id: 9,
        },
        {
          url: "/img/income-svg/moonlighting.svg",
          name: "兼职",
          id: 10,
        },
        {
          url: "/img/spend-svg/pet.svg",
          name: "宠物",
          id: 11,
        },
        {
          url: "/img/spend-svg/trip.svg",
          name: "出行",
          id: 12,
        },
      ],
    };
  },
  getters: {},
  actions: {},
});

export default svg;

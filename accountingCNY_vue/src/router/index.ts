/* eslint-disable no-undef */
import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/login",
      name: "login",
      component: () => import("../rou-vue/login/login.vue"),
      meta: {
        hideBreadcrumb: true,
        sign: true,
        title: "登录",
      },
    },
    { path: "/", redirect: "/login" },
    {
      path: "/regsiter",
      name: "regsiter",
      component: () => import("../rou-vue/login/regsiter.vue"),
      meta: {
        hideBreadcrumb: true,
        sign: true,
        title: "注册",
      },
    },
    {
      path: "/index",
      name: "index",
      meta: {
        sign: true,
        title: "首页",
      },
      component: () => import("../rou-vue/index/index.vue"),
    },
    {
      path: "/bill",
      name: "bill",
      meta: {
        sign: true,
        title: "报表",
      },
      component: () => import("../rou-vue/index/bill.vue"),
    },
    {
      path: "/setUp",
      name: "setUp",
      meta: {
        sign: true,
        title: "设置",
      },
      component: () => import("../rou-vue/index/set-up.vue"),
    },
    {
      path: "/add",
      name: "add",
      meta: {
        sign: true,
        title: "添加账单",
      },
      component: () => import("../rou-vue/index/add.vue"),
      children: [],
    },
    {
      path: "/sort",
      name: "sort",
      meta: {
        sign: true,
        title: "存钱罐",
      },
      component: () => import("../rou-vue/index/sort.vue"),
    },
    {
      path: "/alters",
      name: "alters",
      meta: {
        sign: true,
        title: "修改",
      },
      component: () => import("../rou-vue/index/alters.vue"),
    },
    {
      path: "/loes",
      name: "loes",
      meta: {
        sign: true,
        title: "404",
      },
      component: () => import("../rou-vue/lose/404.vue"),
    },
    {
      path: "/piggy",
      name: "piggy",
      meta: {
        sing: true,
        title: "小猪",
      },
      component: () => import("../rou-vue/index/piggy.vue"),
    },
  ],
});

//路由守卫 登录
router.beforeEach((_to, _from, _next) => {
  const token = localStorage.getItem("token");
  if (token != null) {
    if (_to.meta.hideBreadcrumb) {
      router.push({
        name: "index",
      });
    } else {
      _next();
    }
  } else {
    if (_to.meta.hideBreadcrumb == null) {
      console.log("请先登录");
      router.push({
        name: "login",
      });
    } else {
      _next();
    }
  }
});

router.beforeEach((to) => {
  document.title = to.meta.title;
});
export default router;

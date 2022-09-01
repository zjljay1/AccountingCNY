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
      },
    },
    { path: "/", redirect: "/login" },
    {
      path: "/regsiter",
      name: "regsiter",
      component: () => import("../rou-vue/login/regsiter.vue"),
      meta: {
        hideBreadcrumb: true,
      },
    },
    {
      path: "/index",
      name: "index",
      component: () => import("../rou-vue/index/index.vue"),
    },
    {
      path: "/bill",
      name: "bill",
      component: () => import("../rou-vue/index/bill.vue"),
    },
    {
      path: "/setUp",
      name: "setUp",
      component: () => import("../rou-vue/index/set-up.vue"),
    },
    {
      path: "/add",
      name: "add",
      component: () => import("../rou-vue/index/add.vue"),
      children: [],
    },
    {
      path: "/sort",
      name: "sort",
      component: () => import("../rou-vue/index/sort.vue"),
    },
    {
      path: "/alters",
      name: "alters",
      component: () => import("../rou-vue/index/alters.vue"),
    },
  ],
});

//路由守卫 登录
router.beforeEach((_to, _from, _next) => {
  let id = localStorage.getItem("userId");
  if (!_to.meta.hideBreadcrumb) {
    if (
      localStorage.getItem("name") != null &&
      localStorage.getItem("name") != ""
    ) {
      _next();
    } else {
      console.log("tankey已过期,请重新登录");
      router.push({
        name: "login",
      });
    }
  } else {
    _next();
  }
  if (_to.meta.hideBreadcrumb && localStorage.getItem("name") != null) {
    if (localStorage.getItem("name") != "") {
      console.log("你已登录,无需重新登录");
      router.push({
        name: "index",
      });
    }
  }
});

export default router;

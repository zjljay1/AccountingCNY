import { createRouter, createWebHistory } from "vue-router";
import login from "../rou-vue/login.vue";
import regsiter from "../rou-vue/regsiter.vue";
import index from "../rou-vue/index.vue";
// import { login } from "../axios/index";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "login",
      component: login,
      meta: {
        hideBreadcrumb: true,
      },
    },
    {
      path: "/regsiter",
      name: "regsiter",
      component: regsiter,
      meta: {
        hideBreadcrumb: true,
      },
    },
    {
      path: "/index",
      name: "index",
      component: index,
    },
  ],
});

//路由守卫 登录
router.beforeEach((_to, _from, _next) => {
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
    if (localStorage.getItem("name") == "admin") {
      console.log("你已登录,无需重新登录");
      router.push({
        name: "index",
      });
    }
  }
});
export default router;

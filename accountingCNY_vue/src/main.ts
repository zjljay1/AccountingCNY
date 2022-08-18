import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

// 引入tailwind
import "./assets/base.css";
// import "element-plus/lib/theme-chalk/index.css";

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.mount("#app");

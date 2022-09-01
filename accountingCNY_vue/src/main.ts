import { createApp } from "vue";
import { createPinia } from "pinia";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import App from "./App.vue";
import router from "./router";

// 引入tailwind
import "./assets/base.css";
// import "../element-plus/theme-chalk/index.css";
// import "../node_modules/element-plus/lib/theme-chalk/index.css";
// D:\project\AccountingCNY\accountingCNY_vue\node_modules\vite\client.d.ts
// import "../node_modules/vite/client";
const app = createApp(App);
app.use(ElementPlus);
app.use(createPinia());
app.use(router);
app.mount("#app");

import {createApp} from 'vue';
import App from './App.vue'
import 'vant/lib/index.css';
import * as VueRouter from 'vue-router'
import vant from "vant";
import routes from "./config/route.js";


const router = VueRouter.createRouter({
    history:VueRouter.createWebHashHistory(),//路径中添加#，避免浏览器找不到路径
    routes
})


const app = createApp(App)
app.use(router)
app.use(vant);
app.mount('#app')

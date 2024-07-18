import {createApp} from 'vue';
import App from './App.vue'
import 'vant/lib/index.css';
import {
    Button, Icon, NavBar, Row, Tabbar, TabbarItem
} from "vant";


const app = createApp(App)
app.use(Button);
app.use(NavBar);
app.use(Row);
app.use(Tabbar);
app.use(TabbarItem);
app.use(Icon);
app.mount('#app')

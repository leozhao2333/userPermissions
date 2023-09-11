import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
//element-ui
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'
import '@element-plus/icons-vue'
import '@/assets/css/global.css'
const app = createApp(App)
app.config.globalProperties.$axios = axios
axios.defaults.baseURL = "http://localhost:9999" //后端地址
// axios.defaults.baseURL = "http://101.43.115.68:9999" //后端地址
axios.interceptors.request.use(config => {
        var token = sessionStorage.getItem('token')
        if(token != null){
            config.headers['token'] = token
        }
        return config
    }
)
app.use(store).use(router).use(ElementPlus).mount('#app')
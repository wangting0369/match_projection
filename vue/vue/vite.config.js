import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'


// https://vitejs.dev/config/
export default defineConfig({
  server: {
    host: '0.0.0.0', // 默认是 localhost
    port: 5713, // 自定义端口
    strictPort: false, // 设为 true 时若端口已被占用则会直接退出，而不是尝试下一个可用端口
    open: false, // 启动后是否浏览器自动打开
    hmr: true, // 为开发服务启用热更新，默认是不启用热更新的
    // proxy: { // 本地开发环境通过代理实现跨域，生产环境使用 nginx 转发
    //   '/api': {
    //     target: 'http://127.0.0.1:8006', // 后端服务实际地址
    //     changeOrigin: true,
    //     rewrite: path => path.replace(/^\/api/, '')
    //   }
    // }
  },
  plugins: [vue()],
})

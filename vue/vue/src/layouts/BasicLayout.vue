<template>
  <van-nav-bar
      title="标题"
      left-text="返回"
      right-text="按钮"
      @click-left="onClickLeft"
      @click-right="onClickRight"
  >
    <template #right>
      <van-icon name="search" size="18" />
    </template>
  </van-nav-bar>

  <div id="content">
    <router-view></router-view>
  </div>

  <van-tabbar route>
    <van-tabbar-item to="/" icon="home-o" >主页</van-tabbar-item>
    <van-tabbar-item to="/team" icon="friends-o">队伍</van-tabbar-item>
    <van-tabbar-item to="/user" icon="setting-o">个人</van-tabbar-item>
  </van-tabbar>

</template>



<script setup>
import {ref} from "vue";
import {showToast} from "vant";
import {useRouter} from "vue-router";

//组合式api，不能使用router
const router = useRouter()
const onClickLeft = () => {
  router.push('/')
};
const onClickRight = () => {
  router.push('/search')
};

const active = ref("index");
const onChange = (index) => showToast(`标签_${index}`)

</script>



<style scoped>

</style>
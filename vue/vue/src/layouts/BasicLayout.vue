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

  <div>
    <template v-if="active === 'index'">
      <Index/>
    </template>
    <template v-else-if="active === 'team'">
      <Team/>
    </template>
  </div>

  <van-tabbar v-model="active" @change="onChange">
    <van-tabbar-item icon="home-o" name="index">主页</van-tabbar-item>
    <van-tabbar-item icon="friends-o" name="team">队伍</van-tabbar-item>
    <van-tabbar-item icon="setting-o" name="user">个人</van-tabbar-item>
  </van-tabbar>

</template>



<script setup>
import {ref} from "vue";
import {showToast} from "vant";
import Index from "../pages/Index.vue";
import Team from "../pages/Team.vue";

const onClickLeft = () => alert("左");
const onClickRight = () => alert("右");

const active = ref("index");
const onChange = (index) => showToast(`标签_${index}`)

</script>



<style scoped>

</style>
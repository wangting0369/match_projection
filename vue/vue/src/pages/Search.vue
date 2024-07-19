<template>
  <van-search
      v-model="search_text"
      shape="round"
      background='#5C93C7'
      placeholder="请输入搜索关键词"
      show-action
      @search="onSearch"
      @cancel="onCancel"
  />
  <van-divider :style="{ color: '#516172', borderColor: '#516172', padding: '0 16px' }">
    已选择的标签
  </van-divider>
  <div v-if="activeIds.length === 0">
    暂无选择
  </div>
  <div v-else>
<!--    el-col中设置的了style样式，会导致gutter间距样式不起作用，建议直接加class-->
    <van-row gutter="20" style="padding: 0 16px" >
      <van-col>
        <van-tag v-for="tagid in activeIds" closeable size="medium" type="primary" @close="closeTag(tagid)">{{tagid}}</van-tag>
      </van-col>
    </van-row>
  </div>
  <van-divider :style="{ color: '#516172', borderColor: '#516172', padding: '0 16px' }">
    请选择分类标签
  </van-divider>


  <van-tree-select
    v-model:active-id="activeIds"
    v-model:main-active-index="activeIndex"
    :items="tagList"
  />

</template>


<script setup>
import {showToast} from "vant";
import {ref} from "vue";

const search_text = ref('');
const activeIds = ref([]);
const activeIndex = ref(0);
const tagOriList = [
  {
    text:"性别",
    children:[
      { text: "男", id: "男"},
      { text: "女", id: "女"}
    ],
  },
  {
    text: "年级",
    children: [
      {text: "大一", id: "大一"}
    ]
  }
]
let tagList = ref(tagOriList);
/**
 * 搜索框可以回车搜索，检索标签
 * @param val
 */
const onSearch = () => {
  tagList.value = tagOriList.map(parentTag =>{
    const tempParent = {...parentTag}
    const parentChildren = [...parentTag.children]
    tempParent.children= parentChildren.filter(item => {
      return item.id.includes(search_text.value)
    })
    return tempParent
  })
};
const onCancel = () => {
  search_text.value =''
  tagList.value = tagOriList
}
const closeTag = (val) =>{
  activeIds.value = activeIds.value.filter(item => {  //把不等于tagid的都保留下来
    return item !== val
  })
}

</script>


<style scoped>

</style>
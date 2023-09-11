<template>
  <div style="height: 50px; line-height: 50px ;border-bottom: 1px solid #ccc; display: flex">
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: blue">{{this.oidname}} {{userName}}</div>
    <div style="flex: 1"></div>
    <div style="width: 100px">
      <el-button type="submit" @click.prevent="layout">登出</el-button>
    </div>
  </div>
</template>

<script>

export default {
  data(){
    return{
      oidname:localStorage.getItem('oidname'),
      userName:localStorage.getItem('userName'),
    }
  },
  methods:{
    layout: async function(){
      this.$router.replace('/login');//如果输入的名字以及密码正确路由跳转至个人页面
    }
  }
}
</script>
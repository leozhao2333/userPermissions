<template>
  <div>
    <el-menu
        style="width: 200px; min-height: 100vh"
        :default-active="$route.path"
        router
        class="el-menu-vertical-demo">
      <el-menu-item index="/user">
        <template #title>用户管理</template>
      </el-menu-item>
      <el-menu-item index="/userGroup">
        <template #title>用户组管理</template>
      </el-menu-item>
      <el-menu-item index="/file">
        <template #title>文件管理</template>
      </el-menu-item>
      <el-menu-item index="/fileGroup">
        <template #title>文件组管理</template>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>

export default {
  name: "Aside",
  data(){
    return{
    };
  },
  methods:{
  },
}
</script>
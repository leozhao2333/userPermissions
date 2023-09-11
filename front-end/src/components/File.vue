<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="getFileList">查询</el-button>
    </div>
    <el-table :data="FileVoList" border style="width: 100%">
      <el-table-column prop="fileName" label="文件名" />
      <el-table-column prop="fileType" label="文件类型" />
      <el-table-column prop="createByName" label="创建人" />
      <el-table-column fixed="right" label="权限" width="180">
        <template #default="scope">
          <el-button type="primary" @click="add(scope.row)">新增</el-button>
          <el-dialog v-model="addtable" width="30%">
            <el-form :model="form" label-width="120px">
              <el-form-item label="用户">
                <el-select v-model="username" class="m-2" @click="getUserList">
                  <el-option
                      v-for="item in userList"
                      :key="item.id"
                      :label="item.userName"
                      :value="item.id"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="读写权限">
                <div class="mb-2 flex items-center text-sm">
                  <el-radio-group v-model="radio1" class="ml-4">
                    <el-radio label="0" size="large">可读</el-radio>
                    <el-radio label="1" size="large">不可读</el-radio>
                  </el-radio-group>
                </div>
              </el-form-item>
              <el-form-item label="管理权限">
                <div class="mb-2 flex items-center text-sm">
                  <el-radio-group v-model="radio2" class="ml-4">
                    <el-radio label="0" size="large">管理</el-radio>
                    <el-radio label="1" size="large">不可管理</el-radio>
                  </el-radio-group>
                </div>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="editFileUserRole(form)">确认</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>
          <el-button type="primary" @click="getUserRoleByFile(scope.row.id)">查看</el-button>
          <el-dialog v-model="dialogTableVisible" width="30%">
            <el-table :data="fileUserRoleVoList">
              <el-table-column property="userIdName" label="用户名" />
              <el-table-column property="roleReadName" label="读写权限" />
              <el-table-column property="roleManageName" label="管理权限" />
              <el-table-column fixed="right" label="操作" width="180">
              <template #default="scope">
                <el-popconfirm
                    width="220"
                    confirm-button-text="确认"
                    cancel-button-text="取消"
                    :icon="InfoFilled"
                    title="确定删除吗?"
                    @confirm="deleteFileUserRole(scope.row)"
                >
                  <template #reference>
                    <el-button type="text">删除</el-button>
                  </template>
                </el-popconfirm>
              </template>
              </el-table-column>
            </el-table>
          </el-dialog>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data(){
    return{
      addtable:false,
      dialogTableVisible:false,
      username:"",
      search:"",
      FileVoList:[],
      fileUserRoleVoList:[],
      userList:[],
      form:[],
      //单选
      radio1:'',
      radio2:'',
    };
  },
  methods:{
    add:async function(row){
      this.username=''
      this.radio1=''
      this.radio2=''
      this.form=row
      let res = await this.$axios({
        method:"post",
        url:"/common/file/getFileWithRole",
        data:{
          "id":row.id
        },
      });
      let roleManage = res.data.data.roleManage;
      if (roleManage==0){
        this.addtable = true
      }
    },

    getUserList:async function(){
      let res = await this.$axios({
        method:"post",
        url:"/common/user/getUserList",
        data:{
        },
      });
      this.userList =  res.data.data
    },

    getFileList:async function() {
      let res = await this.$axios({
        method:"post",
        url:"/common/file/getFileList",
        data:{
          "fileName":this.search
        },
      });
      console.log(res.data.data)
      this.FileVoList =  res.data.data
    },

    getUserRoleByFile:async function(fileId) {
      let res = await this.$axios({
        method:"post",
        url:"/common/file-user-role/getUserRoleByFile",
        data:{
          "fileId":fileId
        },
      });
      this.fileUserRoleVoList =  res.data.data
      this.dialogTableVisible = true
    },

    editFileUserRole:async function(file) {
      this.addtable = false
      if (file.id!=''){
        let res = await this.$axios({
          method:"post",
          url:"/common/file-user-role/editFileUserRole",
          data:{
            "fileId":file.id,
            "userId":this.username,
            "roleRead":this.radio1,
            "roleManage":this.radio2
          },
        });
        this.fileUserRoleVoList =  res.data.data
        this.addtable=false
        this.form=''
      }
    },

    deleteFileUserRole:async function(row) {
      let res = await this.$axios({
        method:"post",
        url:"/common/file/getFileWithRole",
        data:{
          "id":row.id
        },
      });
      let roleManage = res.data.data.roleManage;
      if (roleManage==0){
        await this.$axios({
          method:"post",
          url:"/common/file-user-role/deleteFileUserRole",
          data:{
            "id":row.id
          },
        });
        this.getUserRoleByFile(row.fileId)
      }
    },
  }
}
</script>

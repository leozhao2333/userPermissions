<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="getUserList">查询</el-button>
      <el-button type="primary" style="margin-left: 5px" @click="add">新增</el-button>
      <el-dialog v-model="adduser" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="登录名称">
          <el-input v-model="loginName" placeholder="请输入登录名称" style="width: 40%" clearable></el-input>
          </el-form-item>
          <el-form-item label="用户名称">
          <el-input v-model="userName" placeholder="请输入用户名称" style="width: 40%" clearable></el-input>
          </el-form-item>
          <el-form-item label="用户密码">
          <el-input v-model="userPass" placeholder="请输入用户密码" style="width: 40%" clearable></el-input>
          </el-form-item>
          <el-form-item label="电子邮箱">
          <el-input v-model="email" placeholder="请输入电子邮箱" style="width: 40%" clearable></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
          <el-input v-model="phone" placeholder="请输入电话号码" style="width: 40%" clearable></el-input>
          </el-form-item>
          <el-form-item label="管理">
          <div class="mb-2 flex items-center text-sm">
            <el-radio-group v-model="radio1" class="ml-4">
              <el-radio label="1" size="large">是</el-radio>
              <el-radio label="0" size="large">否</el-radio>
            </el-radio-group>
          </div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="cteateUser">确认</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
    <el-table :data="userVoList" border style="width: 100%">
      <el-table-column prop="loginName" label="登录名称" />
      <el-table-column prop="userName" label="用户姓名" />
      <el-table-column prop="userPass" label="用户密码" />
      <el-table-column prop="phone" label="电话号码" />
      <el-table-column prop="email" label="电子邮箱" />
      <el-table-column prop="ifManage" label="是否是管理" />
      <el-table-column fixed="right" label="管理" width="180">
        <template #default="scope">
          <el-button type="primary" @click="edit(scope.row)">编辑</el-button>
          <el-dialog v-model="edituser" width="30%">
            <el-form :model="form" label-width="120px">
              <el-form-item label="登录名称">
                <el-input v-model="loginName" placeholder="请输入登录名称" style="width: 40%" clearable></el-input>
              </el-form-item>
              <el-form-item label="用户名称">
                <el-input v-model="userName" placeholder="请输入用户名称" style="width: 40%" clearable></el-input>
              </el-form-item>
              <el-form-item label="用户密码">
                <el-input v-model="userPass" placeholder="请输入用户密码" style="width: 40%" clearable></el-input>
              </el-form-item>
              <el-form-item label="电子邮箱">
                <el-input v-model="email" placeholder="请输入电子邮箱" style="width: 40%" clearable></el-input>
              </el-form-item>
              <el-form-item label="电话号码">
                <el-input v-model="phone" placeholder="请输入电话号码" style="width: 40%" clearable></el-input>
              </el-form-item>
              <el-form-item label="管理">
                <div class="mb-2 flex items-center text-sm">
                  <el-radio-group v-model="radio1" class="ml-4">
                    <el-radio label="1" size="large">是</el-radio>
                    <el-radio label="0" size="large">否</el-radio>
                  </el-radio-group>
                </div>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="editUser">确认</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>
          <el-popconfirm
              width="220"
              confirm-button-text="确认"
              cancel-button-text="取消"
              :icon="InfoFilled"
              title="确定删除吗?"
              @confirm="deleteUser(scope.row)"
          >
            <template #reference>
              <el-button type="primary">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data(){
    return{
      adduser:false,
      edituser:false,
      dialogTableVisible:false,
      id:"",
      loginName:"",
      userName:"",
      userPass:"",
      email:"",
      phone:"",
      search:"",
      userVoList:[],
      //单选
      radio1:'',
    };
  },
  methods:{
    add:async function(){
      this.loginName=''
      this.userName=''
      this.userPass=''
      this.email=''
      this.phone=''
      this.radio1=''
      this.adduser = true
    },
    cteateUser:async function() {
      if (this.loginName!=''){
        await this.$axios({
          method:"post",
          url:"/common/user/cteateUser",
          data:{
            "loginName":this.loginName,
            "userName":this.userName,
            "userPass":this.userPass,
            "phone":this.phone,
            "email":this.email,
            "ifManage":this.radio1,
          },
        });
      }
      this.adduser=false;
      this.getUserList()
    },

    edit:async function(userVo){
      this.id=userVo.id
      this.loginName=userVo.loginName
      this.userName=userVo.userName
      this.userPass=userVo.userPass
      this.email=userVo.email
      this.phone=userVo.phone
      this.radio1=userVo.ifManage
      this.edituser = true
    },
    editUser:async function() {
      this.edituser = false
      await this.$axios({
        method:"post",
        url:"/common/user/editUser",
        data:{
          "id":this.id,
          "loginName":this.loginName,
          "userName":this.userName,
          "userPass":this.userPass,
          "phone":this.phone,
          "email":this.email,
          "ifManage":this.radio1,
        },
      });
      this.getUserList()
    },

    getUserList:async function() {
      let res = await this.$axios({
        method:"post",
        url:"/common/user/getUserList",
        data:{
          "userName":this.search
        },
      });
      this.userVoList =  res.data.data
    },

    deleteUser:async function(row) {
      await this.$axios({
        method:"post",
        url:"/common/user/deleteUser",
        data:{
          "id":row.id
        },
      });
      this.getUserList()
    },
  }
}
</script>

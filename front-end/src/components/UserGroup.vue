<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="getList">查询</el-button>
      <el-button type="primary" style="margin-left: 5px" @click="add">新增</el-button>
      <el-dialog v-model="addusergroup" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="分组名称">
          <el-input v-model="groupName" placeholder="请输入分组名称" style="width: 40%" clearable></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
              <span class="dialog-footer">
                <el-button type="primary" @click="addUserGroup">确认</el-button>
              </span>
        </template>
      </el-dialog>
    </div>
    <el-table :data="userGroupVoList" border style="width: 100%">
      <el-table-column prop="groupName" label="分组名称" />
      <el-table-column prop="createByName" label="创建人" />
      <el-table-column prop="createTime" label="创建时间" />
      <el-table-column fixed="right" label="管理" width="220">
        <template #default="scope">
          <el-button type="primary" @click="edit(scope.row)">编辑</el-button>
          <el-dialog v-model="edittable" width="30%">
            <el-form :model="form" label-width="120px">
              <el-form-item label="分组名称">
                <el-input v-model="groupName"  style="width: 40%" clearable></el-input>
              </el-form-item>
            </el-form>
            <template #footer>
              <span class="dialog-footer">
                <el-button type="primary" @click="editusergroup">确认</el-button>
              </span>
            </template>
          </el-dialog>
          <el-button type="primary" @click="getUgUserList(scope.row.id)">查看</el-button>
          <el-dialog v-model="usertable" width="30%">
            <el-button type="primary" @click="adduser">新增</el-button>
            <el-dialog v-model="newusertable" width="30%">
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
                <el-form-item>
                  <el-button type="primary" @click="editUgUser">确认</el-button>
                </el-form-item>
              </el-form>
            </el-dialog>
            <el-table :data="ugUserList">
              <el-table-column property="uidName" label="用户姓名" />
              <el-table-column fixed="right" label="操作" width="180">
                <template #default="scope">
                  <el-popconfirm
                      width="220"
                      confirm-button-text="确认"
                      cancel-button-text="取消"
                      :icon="InfoFilled"
                      title="确定删除吗?"
                      @confirm="deleteUgUser(scope.row)"
                  >
                    <template #reference>
                      <el-button type="text">删除</el-button>
                    </template>
                  </el-popconfirm>
                </template>
              </el-table-column>
            </el-table>
          </el-dialog>
          <el-popconfirm
              width="220"
              confirm-button-text="确认"
              cancel-button-text="取消"
              :icon="InfoFilled"
              title="确定删除吗?"
              @confirm="deleteUserGroup(scope.row)"
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
      addusergroup:false,
      edittable:false,
      usertable:false,
      dialogTableVisible:false,
      newusertable:false,
      id:"",
      groupName:"",
      username:"",
      search:"",
      ugid:'',
      userGroupVoList:[],
      ugUserList:[],
      userList:[],
    };
  },
  methods:{
    add:async function(){
      this.groupName=''
      this.addusergroup = true
    },
    adduser:async function(){
      this.newusertable = true
    },

    addUserGroup:async function() {
      await this.$axios({
        method:"post",
        url:"/common/user-group/editUserGroup",
        data:{
          "groupName":this.groupName
        },
      });
      this.addusergroup = false
      this.getList()
    },

    edit:async function(usergroup){
      this.edittable = true
      this.id = usergroup.id
      this.groupName=usergroup.groupName
    },

    getUgUserList:async function(ugid){
      this.usertable=true;
      this.ugid=ugid
      let res = await this.$axios({
        method:"post",
        url:"/common/ug-user/getUgUserList",
        data:{
          "ugid":ugid
        },
      });
      this.ugUserList =  res.data.data
    },

    editUgUser:async function(){
      this.newusertable = false
      if (this.username!=''){
        await this.$axios({
          method:"post",
          url:"/common/ug-user/editUgUser",
          data:{
            "ugid":this.ugid,
            "uid":this.username
          },
        });
        this.getUgUserList(this.ugid)
      }
    },

    deleteUgUser:async function(uguser) {
      await this.$axios({
        method:"post",
        url:"/common/ug-user/deleteUgUser",
        data:{
          "id":uguser.id
        },
      });
      this.getUgUserList(uguser.ugid)
    },

    getUserList:async function(){
      let res = await this.$axios({
        method:"post",
        url:"/common/user/getUserList",
        data:{
          "userName":this.search
        },
      });
      this.userList =  res.data.data
    },

    getList:async function() {
      let res = await this.$axios({
        method:"post",
        url:"/common/user-group/getList",
        data:{
          "groupName":this.search
        },
      });
      this.userGroupVoList =  res.data.data
    },

    editusergroup:async function() {
      this.edittable = false
      await this.$axios({
        method:"post",
        url:"/common/user-group/editUserGroup",
        data:{
          "id":this.id,
          "groupName":this.groupName
        },
      });
      this.getList()
    },

    deleteUserGroup:async function(row) {
      await this.$axios({
        method:"post",
        url:"/common/user-group/deleteUserGroup",
        data:{
          "id":row.id
        },
      });
      this.getList()
    },
  }
}
</script>

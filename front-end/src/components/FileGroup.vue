<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="getList">查询</el-button>
      <el-button type="primary" style="margin-left: 5px" @click="addfg">新增文件组</el-button>
      <el-dialog v-model="addfgtable" width="30%">
        <el-form :model="fg" label-width="120px">
          <el-form-item label="文件组名称">
            <el-input v-model="fileGroupName" placeholder="请输入分组名称" style="width: 40%" clearable></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
              <span class="dialog-footer">
                <el-button type="primary" @click="addUserGroup">确认</el-button>
              </span>
        </template>
      </el-dialog>
    </div>
    <el-table :data="fileGroupVoList" border style="width: 100%">
      <el-table-column prop="fileGroupName" label="文件组名" />
      <el-table-column prop="createByName" label="创建人" />
      <el-table-column fixed="right" label="文件" width="100">
        <template #default="scope">
          <el-button type="primary" @click="getfilelist(scope.row)">查看</el-button>
          <el-dialog v-model="filetable" width="30%" >
            <el-button type="primary" @click="addfgfile">新增</el-button>
            <el-dialog v-model="newfiletable" width="30%">
              <el-form :model="form" label-width="120px">
                <el-form-item label="文件">
                  <el-select v-model="filename" class="m-2" >
                    <el-option
                        v-for="item in filelist"
                        :key="item.id"
                        :label="item.fileName"
                        :value="item.id"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="editfgfile">确认</el-button>
                </el-form-item>
              </el-form>
            </el-dialog>
            <el-table :data="fgfilelist">
              <el-table-column property="fileName" label="文件名" />
              <el-table-column fixed="right" label="操作" width="180">
                <template #default="scope">
                  <el-popconfirm
                      width="220"
                      confirm-button-text="确认"
                      cancel-button-text="取消"
                      :icon="InfoFilled"
                      title="确定删除吗?"
                      @confirm="deletefgfile(scope.row)"
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
      <el-table-column fixed="right" label="权限" width="220">
        <template #default="scope">
          <el-button type="primary" @click="add(scope.row)">新增</el-button>
          <el-dialog v-model="addtable" width="30%">
            <el-form :model="form" label-width="40%">
              <el-form-item label="用户组">
                <el-select v-model="username" class="m-2" @click="getUserGroupList">
                  <el-option
                      v-for="item in userGroupVoList"
                      :key="item.id"
                      :label="item.groupName"
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
                <el-button type="primary" @click="addFgUgRole(form)">确认</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>
          <el-button type="primary" @click="getUgRoleByFg(scope.row.id)">查看</el-button>
          <el-dialog v-model="dialogTableVisible" width="30%">
            <el-table :data="fgUgRoleVoList">
              <el-table-column property="ugIdName" label="用户组名" />
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
                      @confirm="deleteFgUgRole(scope.row)"
                  >
                    <template #reference>
                      <el-button type="primary">删除</el-button>
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
              @confirm="deleteFileGroup(scope.row)"
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
      filetable:false,
      newfiletable:false,
      addfgtable:false,
      addtable:false,
      dialogTableVisible:false,
      filename:'',
      fg:'',
      filegroup:[],
      fileGroupName:'',
      username:"",
      search:"",
      fileGroupVoList:[],
      fgUgRoleVoList:[],
      userGroupVoList:[],
      form:[],
      fgfilelist:[],
      filelist:[],
      //单选
      radio1:'',
      radio2:'',
      fgid:''
    };
  },
  methods:{
    getfilelist:async function(row){
      this.filegroup = row
      let res = await this.$axios({
        method:"post",
        url:"/common/file/getFileList",
        data:{
          "fgId":row.id
        },
      });
      this.fgfilelist= res.data.data
      this.filetable = true
    },

    addfgfile:async function(){

      this.newfiletable = true
      let res = await this.$axios({
        method:"post",
        url:"/common/file/getFileList",
        data:{
        },
      });
      this.filelist =  res.data.data
    },

    editfgfile:async function(){
      await this.$axios({
        method:"post",
        url:"/common/file/updateFile",
        data:{
          "id":this.filename,
          "fgId":this.filegroup.id
        },
      });
      this.newfiletable = false
      this.getfilelist(this.filegroup)
    },

    deletefgfile:async function(file){
      await this.$axios({
        method:"post",
        url:"/common/file/editFileNull",
        data:{
          "id":file.id,
        },
      });
      this.getfilelist(this.filegroup)
    },

    add:async function(row){
      this.username=''
      this.radio1=''
      this.radio2=''
      this.form=row
      let res =await this.$axios({
        method:"post",
        url:"/common/file-group/getRole",
        data:{
          "id":row.id
        },
      });
      let managerole =res.data.data.roleManage
      console.log(managerole)
      if (managerole==0){
        this.addtable = true
      }
    },

    addfg:async function(){
      this.addfgtable = true
    },

    deleteFileGroup:async function(fg){
      let res =await this.$axios({
        method:"post",
        url:"/common/file-group/getRole",
        data:{
          "id":fg.id
        },
      });
      let managerole =res.data.data.roleManage
      console.log(managerole)
      if (managerole==0){
        await this.$axios({
          method:"post",
          url:"/common/file-group/deleteFileGroup",
          data:{
            "id":fg.id
          },
        });
        this.getList()
      }
    },

    addUserGroup:async function(){
      this.addfgtable = false
      if (this.fileGroupName!=''){
        await this.$axios({
          method:"post",
          url:"/common/file-group/editFileGroup",
          data:{
            "fileGroupName":this.fileGroupName
          },
        });
        this.getList()
      }
    },

    getUserGroupList:async function(){
      let res = await this.$axios({
        method:"post",
        url:"/common/user-group/getList",
        data:{
        },
      });
      this.userGroupVoList =  res.data.data
    },

    getList:async function() {
      let res = await this.$axios({
        method:"post",
        url:"/common/file-group/getList",
        data:{
          "fileGroupName":this.search
        },
      });
      this.fileGroupVoList =  res.data.data
    },

    getUgRoleByFg:async function(fileId) {
      let res = await this.$axios({
        method:"post",
        url:"/common/fg-ug-role/getUgRoleByFg",
        data:{
          "fgId":fileId
        },
      });
      this.fgUgRoleVoList =  res.data.data
      this.dialogTableVisible = true
    },

    addFgUgRole:async function(fg) {
      this.addtable = false
      if (fg.id!=''){
        await this.$axios({
          method:"post",
          url:"/common/fg-ug-role/editFgUgRole",
          data:{
            "fgId":fg.id,
            "ugId":this.username,
            "roleRead":this.radio1,
            "roleManage":this.radio2
          },
        });
        this.addtable = false
        this.getList
      }
    },

    editFgUgRole:async function(fileId) {
      this.addtable = false
      let res = await this.$axios({
        method:"post",
        url:"/common/fg-ug-role/editFgUgRole",
        data:{
          "fgId":fileId,
          "ugId":this.username,
          "roleRead":this.radio1,
          "roleManage":this.radio2
        },
      });
      this.fgUgRoleVoList =  res.data.data
      this.addtable=false
    },

    deleteFgUgRole:async function(row) {
      await this.$axios({
        method:"post",
        url:"/common/fg-ug-role/deleteFgUgRole",
        data:{
          "id":row.id
        },
      });
      this.getUgRoleByFg(row.fgId)
    },

  }
}
</script>

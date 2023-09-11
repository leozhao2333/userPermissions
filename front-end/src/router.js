import {createRouter,createWebHashHistory} from "vue-router";
import Layout from "@/layout/Layout";
import User from "@/components/User";
import UserGroup from "@/components/UserGroup";
import File from "@/components/File";
import FileGroup from "@/components/FileGroup";

const routes = [
    {
        path:'/',
        name:'Layout',
        component:Layout,
        redirect:"/user",
        children:[
            {
              path: "user",
              name: "User",
              component:User
            },
            {
              path: "userGroup",
              name: "UserGroup",
              component:UserGroup
            },
            {
                path: "file",
                name: "File",
                component:File
            },
            {
                path: "fileGroup",
                name: "FileGroup",
                component:FileGroup
            }
        ]
    },
    {
        path: "/login",
        name: "Login",
        component:() =>import("@/components/Login")
    }
];

const router = createRouter( {
    history: createWebHashHistory(process.env.BASE_URL),
    routes
});

router.beforeEach((to,from,next)=>
{
    //登录可以直接进入,而主页面需要分情况
    if(to.path=='/login')
    {
        next();
    }
    else
    {
        if(from.path=="/login")//从登录页面可以直接通过登录进入主页面
        {
            next();
        }
        else{
            if (!sessionStorage.getItem('token')){
                next('/login');
            }else {
                next();
            }
        }
    }
})
export default router;

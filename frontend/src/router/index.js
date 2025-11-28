import { createRouter, createWebHistory } from "vue-router";

import LoginView from "../views/LoginView.vue";
import RegisterView from "../views/RegisterView.vue";
import ProfileView from "../views/ProfileView.vue";
import ProfileEditView from "../views/ProfileEditView.vue";
import HelloDemoView from "../views/HelloDemoView.vue";
import MainLayout from "../layouts/MainLayout.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    // 👉 首頁你如果想直接進 hello，可以改成這樣：
    { path: "/", redirect: "/hello-demo" },

    // 公開頁面（不需要登入）
    { path: "/login", component: LoginView },
    { path: "/register", component: RegisterView },

    // 有 layout 的頁面
    {
      path: "/",
      component: MainLayout,
      children: [
        // Hello 功能 demo：不需要登入，但會套 Layout
        { path: "hello-demo", component: HelloDemoView },

        // 下列「個人資料相關」才需要登入
        { path: "profile", component: ProfileView },
        { path: "profile/edit", component: ProfileEditView },
      ],
    },
  ],
});

// 🔐 路由守衛
router.beforeEach((to, from, next) => {
  // 不需要登入的路徑
  const publicPaths = ["/login", "/register", "/hello-demo"];

  const token = localStorage.getItem("token");

  if (!publicPaths.includes(to.path) && !token) {
    // 想去需要登入的頁面，但沒 token → 踢回 login
    return next("/login");
  }

  if (publicPaths.includes(to.path) && token && to.path === "/login") {
    // 已登入還去 login → 導回 profile（或 hello-demo 看你喜歡）
    return next("/profile");
  }

  next();
});

export default router;

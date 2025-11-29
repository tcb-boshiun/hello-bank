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
    { path: "/", redirect: "/hello-demo" },
    { path: "/login", component: LoginView },
    { path: "/register", component: RegisterView },
    {
      path: "/",
      component: MainLayout,
      children: [
        { path: "hello-demo", component: HelloDemoView },
        { path: "profile", component: ProfileView },
        { path: "profile/edit", component: ProfileEditView }
      ]
    }
  ]
});

// Public pages that don't require login
const publicPaths = ["/login", "/register", "/hello-demo"];

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem("token");

  if (!publicPaths.includes(to.path) && !token) {
    return next("/login");
  }

  if (to.path === "/login" && token) {
    return next("/profile");
  }

  next();
});

export default router;

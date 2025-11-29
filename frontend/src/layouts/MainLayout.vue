<template>
  <div class="layout-root">
    <header class="header">
      <div class="header-title">
        <h1 style="font-size:18px; margin:0;">Hello Bank</h1>
        <span class="header-title-badge">Demo</span>
      </div>
      <div class="header-right">
        <span>Hi, {{ userName || "尊敬的客人" }}</span>
        <button
          v-if="isLogin"
          class="btn secondary"
          @click="logout"
        >
          登出
        </button>
        <button
          v-else
          class="btn secondary"
          @click="goLogin"
        >
          登入
        </button>
      </div>
    </header>

    <div class="body">
      <nav class="sidebar">
        <div class="nav-section-title">主要功能</div>
        <router-link to="/hello-demo" class="nav-link">Hello Demo</router-link>
        <div class="nav-section-title">會員</div>
        <router-link to="/profile" class="nav-link">會員中心</router-link>
      </nav>

      <main class="content">
        <router-view />
      </main>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import http from "../api/http";

const router = useRouter();
const userAct = ref("");
const userName = ref("");

const isLogin = computed(() => !!localStorage.getItem("token"));

function syncUserFromStorage() {
  userAct.value = localStorage.getItem("userAct") || "";
  userName.value = localStorage.getItem("userName") || "";
}

onMounted(() => {
  syncUserFromStorage();
});

async function logout() {
  try {
    await http.post("/api/auth/logout");
  } catch (e) {
    console.error(e);
  }
  localStorage.removeItem("token");
  localStorage.removeItem("userAct");
  localStorage.removeItem("userName");
  userAct.value = "";
  userName.value = "";
  router.push("/login");
}

function goLogin() {
  router.push("/login");
}
</script>

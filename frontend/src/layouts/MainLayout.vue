<template>
  <div class="layout-root">
    <header class="header">
      <div><h1>Hello Bank</h1></div>
      <div class="header-right">
        <span v-if="userAct">Hi, {{ userAct }}</span>
        <button @click="logout">登出</button>
      </div>
    </header>

    <div class="body">
      <nav class="sidebar">
        <router-link to="/profile">個人資料</router-link>
        <router-link to="/profile/edit">修改資料</router-link>
        <router-link to="/hello-demo">Hello 功能測試</router-link>
      </nav>

      <main class="content">
        <router-view />
      </main>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import http from "../api/http";

const router = useRouter();
const userAct = ref("");

onMounted(() => {
  userAct.value = localStorage.getItem("userAct") || "";
});

async function logout() {
  try {
    await http.post("/api/auth/logout");
  } catch {}
  localStorage.removeItem("token");
  localStorage.removeItem("userAct");
  router.push("/login");
}
</script>

<style scoped>
.layout-root { display: flex; flex-direction: column; height: 100vh; }
.header { height: 56px; background:#1e40af; color:white; display:flex; justify-content:space-between; padding:0 16px; align-items:center;}
.body { flex:1; display:flex; }
.sidebar { width:180px; background:#eee; display:flex; flex-direction:column; padding:8px;}
.content { flex:1; padding:16px;}
</style>

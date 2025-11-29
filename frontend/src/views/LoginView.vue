<template>
  <div class="auth-page">
    <div class="auth-card">
      <div class="auth-card-header">
        <div>
          <div class="auth-title">登入 Hello Bank</div>
          <div class="auth-subtitle">使用會員帳號登入或以訪客身分體驗系統</div>
        </div>
        <span class="chip">Demo</span>
      </div>

      <div class="auth-form-row">
        <label class="auth-label">使用者帳號</label>
        <input
          v-model="userAct"
          class="input"
          placeholder="請輸入帳號"
        />
      </div>

      <div class="auth-form-row">
        <label class="auth-label">使用者密碼</label>
        <input
          v-model="userPwd"
          type="password"
          class="input"
          placeholder="請輸入密碼"
        />
      </div>

      <div class="auth-actions">
        <button class="btn" @click="doLogin" :disabled="loading">
          {{ loading ? "登入中..." : "登入" }}
        </button>
        <button class="btn secondary" @click="goWithoutLogin">
          先逛逛（不登入）
        </button>
        <button class="btn secondary" @click="goRegister">
          註冊
        </button>
      </div>

      <p v-if="error" style="color:#dc2626; font-size:13px; margin-top:8px;">
        {{ error }}
      </p>

      <div class="auth-footer">
        Demo 環境僅為教學使用，不會儲存真實個人資料。
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { login } from "../api/auth";

const router = useRouter();
const userAct = ref("");
const userPwd = ref("");
const error = ref("");
const loading = ref(false);

async function doLogin() {
  if (!userAct.value || !userPwd.value) {
    error.value = "請輸入帳號與密碼";
    return;
  }
  loading.value = true;
  error.value = "";
  try {
    const res = await login(userAct.value, userPwd.value);
    localStorage.setItem("token", res.data.token);
    localStorage.setItem("userAct", res.data.userAct);
    localStorage.setItem("userName", res.data.userName || res.data.userAct);
    await router.push("/hello-demo");
  } catch (e) {
    console.error(e);
    error.value = "登入失敗，請確認帳號密碼";
  } finally {
    loading.value = false;
  }
}

function goWithoutLogin() {
  localStorage.removeItem("token");
  localStorage.removeItem("userAct");
  localStorage.removeItem("userName");
  router.push("/hello-demo");
}

function goRegister() {
  router.push("/register");
}
</script>

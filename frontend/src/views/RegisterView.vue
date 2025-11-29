<template>
  <div class="auth-page">
    <div class="auth-card">
      <div class="auth-card-header">
        <div>
          <div class="auth-title">註冊帳號</div>
          <div class="auth-subtitle">建立您的 Hello Bank 會員帳號</div>
        </div>
      </div>

      <div class="auth-form-row">
        <label class="auth-label">帳號</label>
        <input
          v-model="form.userAct"
          class="input"
          placeholder="請輸入登入帳號"
        />
      </div>

      <div class="auth-form-row">
        <label class="auth-label">密碼</label>
        <input
          v-model="form.userPwd"
          type="password"
          class="input"
          placeholder="請輸入登入密碼"
        />
      </div>

      <div class="auth-form-row">
        <label class="auth-label">姓名</label>
        <input
          v-model="form.userName"
          class="input"
          placeholder="請輸入姓名"
        />
      </div>

      <div class="auth-form-row">
        <label class="auth-label">Email</label>
        <input
          v-model="form.userMail"
          class="input"
          placeholder="name@example.com"
        />
      </div>

      <div class="auth-actions">
        <button class="btn" @click="submit" :disabled="loading">
          {{ loading ? "送出中..." : "註冊" }}
        </button>
        <button class="btn secondary" type="button" @click="goLogin">
          返回登入
        </button>
      </div>

      <p v-if="error" style="color:#dc2626; font-size:13px; margin-top:8px;">
        {{ error }}
      </p>
      <p v-if="success" style="color:#16a34a; font-size:13px; margin-top:8px;">
        註冊成功，請返回登入。
      </p>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { register } from "../api/auth";

const router = useRouter();
const form = reactive({
  userAct: "",
  userPwd: "",
  userName: "",
  userMail: ""
});
const loading = ref(false);
const error = ref("");
const success = ref(false);

async function submit() {
  error.value = "";
  success.value = false;

  if (!form.userAct || !form.userPwd || !form.userName) {
    error.value = "請至少填寫帳號、密碼與姓名";
    return;
  }

  loading.value = true;
  try {
    await register(form);
    success.value = true;
  } catch (e) {
    console.error(e);
    error.value = "註冊失敗，帳號可能已存在";
  } finally {
    loading.value = false;
  }
}

function goLogin() {
  router.push("/login");
}
</script>

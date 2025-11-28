<template>
  <div>
    <h2>登入</h2>
    <input v-model="userAct" placeholder="帳號" />
    <input v-model="userPwd" type="password" placeholder="密碼" />
    <button @click="doLogin">登入</button>
    <p v-if="error">{{ error }}</p>
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

async function doLogin() {
  try {
    const res = await login(userAct.value, userPwd.value);
    localStorage.setItem("token", res.data.token);
    localStorage.setItem("userAct", res.data.userAct);
    router.push("/profile");
  } catch {
    error.value = "登入失敗";
  }
}
</script>

<template>
  <div>
    <h2>註冊</h2>
    <input v-model="form.userId" placeholder="身分證" />
    <input v-model="form.userName" placeholder="姓名" />
    <input v-model="form.userAct" placeholder="帳號" />
    <input v-model="form.userPwd" type="password" placeholder="密碼" />
    <input v-model="form.userPhone" placeholder="手機" />
    <input v-model="form.userMail" placeholder="Email" />
    <input v-model="form.userAddress" placeholder="地址" />
    <button @click="doRegister">註冊</button>
    <p v-if="error">{{ error }}</p>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { register } from "../api/auth";
import { useRouter } from "vue-router";

const router = useRouter();
const error = ref("");
const form = ref({
  userId:"",
  userName:"",
  userAct:"",
  userPwd:"",
  userPhone:"",
  userMail:"",
  userAddress:""
});

async function doRegister() {
  try {
    const res = await register(form.value);
    localStorage.setItem("token", res.data.token);
    localStorage.setItem("userAct", res.data.userAct);
    router.push("/profile");
  } catch {
    error.value = "註冊失敗";
  }
}
</script>

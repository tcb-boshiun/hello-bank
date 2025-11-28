<template>
  <div>
    <h2>使用者資料</h2>
    <div v-if="user">
      <p>身分證：{{ user.userId }}</p>
      <p>姓名：{{ user.userName }}</p>
      <p>帳號：{{ user.userAct }}</p>
      <p>手機：{{ user.userPhone }}</p>
      <p>Email：{{ user.userMail }}</p>
      <p>地址：{{ user.userAddress }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { getUserInfo } from "../api/user";

const user = ref(null);

onMounted(async () => {
  const userAct = localStorage.getItem("userAct");
  const res = await getUserInfo(userAct);
  user.value = res.data;
});
</script>

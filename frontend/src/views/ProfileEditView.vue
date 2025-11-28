<template>
  <div>
    <h2>修改資料</h2>

    <div v-if="form">
      <input v-model="form.userName" placeholder="姓名" />
      <input v-model="form.userPhone" placeholder="手機" />
      <input v-model="form.userMail" placeholder="Email" />
      <input v-model="form.userAddress" placeholder="地址" />
      <input v-model="newPwd" type="password" placeholder="新密碼(可不填)" />
      <button @click="doUpdate">更新</button>
      <p v-if="msg">{{ msg }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { getUserInfo, updateUserInfo } from "../api/user";

const form = ref(null);
const newPwd = ref("");
const msg = ref("");

onMounted(async () => {
  const userAct = localStorage.getItem("userAct");
  const res = await getUserInfo(userAct);
  form.value = res.data;
});

async function doUpdate() {
  const userAct = localStorage.getItem("userAct");
  await updateUserInfo(userAct, {
    userName: form.value.userName,
    userPhone: form.value.userPhone,
    userMail: form.value.userMail,
    userAddress: form.value.userAddress,
    newUserPwd: newPwd.value
  });
  msg.value = "更新成功";
}
</script>

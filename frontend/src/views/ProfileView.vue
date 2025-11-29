<template>
  <div class="card">
    <h2>使用者資料</h2>
    <div class="card-subtitle">
      檢視目前登入使用者的基本資訊。
    </div>

    <p v-if="loading">載入中...</p>
    <p v-if="error" style="color:#dc2626;">{{ error }}</p>

    <template v-if="user">
      <div class="field-row">
        <span class="field-label">身分證</span>
        <span class="field-value">{{ user.userId }}</span>
      </div>
      <div class="field-row">
        <span class="field-label">姓名</span>
        <span class="field-value">{{ user.userName }}</span>
      </div>
      <div class="field-row">
        <span class="field-label">帳號</span>
        <span class="field-value">{{ user.userAct }}</span>
      </div>
      <div class="field-row">
        <span class="field-label">手機</span>
        <span class="field-value">{{ user.userPhone }}</span>
      </div>
      <div class="field-row">
        <span class="field-label">Email</span>
        <span class="field-value">{{ user.userMail }}</span>
      </div>
      <div class="field-row">
        <span class="field-label">地址</span>
        <span class="field-value">{{ user.userAddress }}</span>
      </div>

      <div style="margin-top:16px;">
        <button class="btn secondary" @click="goEdit">編輯資料</button>
      </div>
    </template>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { getUserInfo } from "../api/user";

const router = useRouter();
const user = ref(null);
const loading = ref(true);
const error = ref("");

onMounted(async () => {
  const userAct = localStorage.getItem("userAct");
  if (!userAct) {
    return router.push("/login");
  }

  try {
    const res = await getUserInfo(userAct);
    if (!res.data) {
      error.value = "找不到使用者資料";
    } else {
      user.value = res.data;
    }
  } catch (e) {
    console.error(e);
    error.value = "讀取資料失敗";
  } finally {
    loading.value = false;
  }
});

function goEdit() {
  router.push("/profile/edit");
}
</script>

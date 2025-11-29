<template>
  <div class="card">
    <h2>編輯使用者資料</h2>
    <div class="card-subtitle">
      更新您的聯絡方式與基本資料。
    </div>

    <p v-if="loading">載入中...</p>
    <p v-if="error" style="color:#dc2626;">{{ error }}</p>

    <form v-if="!loading && !error" @submit.prevent="save">
      <div class="field-row">
        <span class="field-label">身分證</span>
        <span class="field-value">{{ userId }}</span>
      </div>
      <div class="field-row">
        <span class="field-label">帳號</span>
        <span class="field-value">{{ userAct }}</span>
      </div>

      <div class="field-row">
        <span class="field-label">姓名</span>
        <input v-model="userName" class="input" />
      </div>
      <div class="field-row">
        <span class="field-label">手機</span>
        <input v-model="userPhone" class="input" />
      </div>
      <div class="field-row">
        <span class="field-label">Email</span>
        <input v-model="userMail" class="input" />
      </div>
      <div class="field-row">
        <span class="field-label">地址</span>
        <input v-model="userAddress" class="input" />
      </div>

      <div style="margin-top:16px; display:flex; gap:8px;">
        <button class="btn" type="submit" :disabled="saving">
          {{ saving ? "儲存中..." : "儲存" }}
        </button>
        <button class="btn secondary" type="button" @click="goBack">
          取消
        </button>
      </div>
      <p v-if="success" style="color:#16a34a; margin-top:8px;">儲存成功</p>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { getUserInfo, updateUserInfo } from "../api/user";

const router = useRouter();

const loading = ref(true);
const saving = ref(false);
const error = ref("");
const success = ref(false);

const userId = ref("");
const userAct = ref("");
const userName = ref("");
const userPhone = ref("");
const userMail = ref("");
const userAddress = ref("");

onMounted(async () => {
  const act = localStorage.getItem("userAct");
  if (!act) {
    return router.push("/login");
  }

  try {
    const res = await getUserInfo(act);
    const data = res.data;
    if (!data) {
      error.value = "找不到使用者資料";
    } else {
      userId.value = data.userId;
      userAct.value = data.userAct;
      userName.value = data.userName;
      userPhone.value = data.userPhone;
      userMail.value = data.userMail;
      userAddress.value = data.userAddress;
    }
  } catch (e) {
    console.error(e);
    error.value = "載入資料失敗";
  } finally {
    loading.value = false;
  }
});

async function save() {
  saving.value = true;
  error.value = "";
  success.value = false;
  try {
    const payload = {
      userId: userId.value,
      userAct: userAct.value,
      userName: userName.value,
      userPhone: userPhone.value,
      userMail: userMail.value,
      userAddress: userAddress.value
    };
    await updateUserInfo(payload);
    localStorage.setItem("userName", userName.value);
    success.value = true;
    router.push("/profile");
  } catch (e) {
    console.error(e);
    error.value = "儲存失敗";
  } finally {
    saving.value = false;
  }
}

function goBack() {
  router.push("/profile");
}
</script>

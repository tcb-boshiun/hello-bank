<template>
  <div class="card">
    <h2>Hello Demo</h2>
    <div class="card-subtitle">
      測試與後端 API 的連線，並顯示回應結果。
    </div>

    <section style="margin-bottom:16px;">
      <h3 style="margin:0 0 8px; font-size:15px;">Hello API</h3>
      <div style="display:flex; gap:8px; align-items:center; margin-bottom:8px;">
        <button class="btn" @click="callHello" :disabled="helloLoading">
          {{ helloLoading ? "呼叫中..." : "呼叫 /api/hello" }}
        </button>
        <span v-if="helloError" style="color:#dc2626; font-size:13px;">{{ helloError }}</span>
      </div>
      <div v-if="hello"
           style="font-family:monospace; font-size:13px; padding:8px 10px; border-radius:8px; background:#f3f4f6;">
        {{ hello }}
      </div>
    </section>

    <section>
      <h3 style="margin:12px 0 8px; font-size:15px;">Echo API</h3>
      <div style="display:flex; gap:8px; margin-bottom:8px; align-items:center; flex-wrap:wrap;">
        <input
            v-model="text"
            class="input"
            style="max-width:260px;"
            placeholder="輸入任意文字"
        />
        <button class="btn secondary" @click="callEcho" :disabled="echoLoading">
          {{ echoLoading ? "送出中..." : "送出到 /api/echo" }}
        </button>

        <button class="btn secondary" @click="show" :disabled="echoLoading">
          {{ echoLoading ? "送出中..." : "show" }}
        </button>

        <button class="btn secondary" @click="hide" :disabled="echoLoading">
          {{ echoLoading ? "送出中..." : "hide" }}
        </button>
      </div>
      <div v-if="echo"
           style="font-family:monospace; font-size:13px; padding:8px 10px; border-radius:8px; background:#ecfeff;">
        {{ echo }}
      </div>
      <span v-if="echoError" style="color:#dc2626; font-size:13px;">{{ echoError }}</span>
    </section>
  </div>
</template>

<script setup>
import {ref} from "vue";
import http from "../api/http";

const hello = ref("");
const text = ref("");
const echo = ref("");
const helloLoading = ref(false);
const echoLoading = ref(false);
const helloError = ref("");
const echoError = ref("");

async function callHello() {
  helloLoading.value = true;
  helloError.value = "";
  try {
    const res = await http.get("/api/hello");
    hello.value = res.data;
  } catch (e) {
    console.error(e);
    helloError.value = "呼叫失敗";
  } finally {
    helloLoading.value = false;
  }
}

async function callEcho() {
  if (!text.value) {
    echoError.value = "請先輸入文字";
    return;
  }
  echoLoading.value = true;
  echoError.value = "";
  try {
    const res = await http.post("/api/echo/v2", text.value, {
      headers: {"Content-Type": "text/plain"}
    });
    echo.value = res.data;
  } catch (e) {
    console.error(e);
    echoError.value = "呼叫失敗";
  } finally {
    echoLoading.value = false;
  }
}

async function show() {
  if (!text.value) {
    echoError.value = "請先輸入文字";
    return;
  }
  echoLoading.value = true;
  echoError.value = "";
  try {
    const res = await http.post("/api/echo/v2", text.value, {
      headers: {"Content-Type": "text/plain"}
    });
    echo.value = res.data;
  } catch (e) {
    console.error(e);
    echoError.value = "呼叫失敗";
  } finally {
    echoLoading.value = false;
  }
}

async function hide() {
  if (!text.value) {
    echoError.value = "請先輸入文字";
    return;
  }
  echoLoading.value = true;
  echoError.value = "";
  try {
    const res = await http.post("/api/echo", text.value, {
      headers: {"Content-Type": "text/plain"}
    });
    echo.value = res.data;
  } catch (e) {
    console.error(e);
    echoError.value = "呼叫失敗";
  } finally {
    echoLoading.value = false;
  }
  echo.value = "*".repeat(echo.value.length);
}
</script>

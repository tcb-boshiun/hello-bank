<template>
  <div class="page">
    <header class="header">
      <h1>Hello Bank</h1>
      <p class="subtitle">簡易前端 demo（連線到你的 Spring Boot 後端）</p>
    </header>

    <main class="content">
      <section class="card">
        <h2>測試後端連線</h2>
        <p>按下按鈕呼叫後端 API：<code>GET /api/hello</code></p>

        <button :disabled="loading" @click="callBackend">
          {{ loading ? '呼叫中…' : '呼叫 /api/hello' }}
        </button>

        <div class="result" v-if="error || responseText">
          <h3>回應結果</h3>
          <p v-if="error" class="error">{{ error }}</p>
          <pre v-else>{{ responseText }}</pre>
        </div>
      </section>

      <section class="card">
        <h2>傳送簡單訊息（POST /api/echo）</h2>
        <p>如果你有實作 <code>POST /api/echo</code>，可以在這裡測試；沒有也沒關係，可以之後再改 URL。</p>

        <textarea
          v-model="message"
          placeholder="輸入要傳給後端的訊息，例如：Hello Bank!"
        />
        <button :disabled="loadingEcho" @click="callEcho">
          {{ loadingEcho ? '傳送中…' : '呼叫 /api/echo' }}
        </button>

        <div class="result" v-if="echoResponse || echoError">
          <h3>回應結果</h3>
          <p v-if="echoError" class="error">{{ echoError }}</p>
          <pre v-else>{{ echoResponse }}</pre>
        </div>
      </section>
    </main>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const loading = ref(false)
const responseText = ref('')
const error = ref('')

const message = ref('')
const loadingEcho = ref(false)
const echoResponse = ref('')
const echoError = ref('')

async function callBackend() {
  loading.value = true
  error.value = ''
  responseText.value = ''
  try {
    const res = await fetch('/api/hello')
    if (!res.ok) {
      throw new Error(`HTTP ${res.status}`)
    }

    // 嘗試用 text 讀，若是 JSON 也會顯示字串內容
    const text = await res.text()
    responseText.value = text
  } catch (e) {
    error.value = '呼叫 /api/hello 失敗：' + e.message
  } finally {
    loading.value = false
  }
}

async function callEcho() {
  loadingEcho.value = true
  echoError.value = ''
  echoResponse.value = ''
  try {
    const res = await fetch('/api/echo', {
      method: 'POST',
      headers: {
        'Content-Type': 'text/plain;charset=utf-8'
      },
      body: message.value
    })

    if (!res.ok) {
      throw new Error(`HTTP ${res.status}`)
    }

    const text = await res.text()
    echoResponse.value = text
  } catch (e) {
    echoError.value = '呼叫 /api/echo 失敗：' + e.message
  } finally {
    loadingEcho.value = false
  }
}
</script>

<style>
body {
  margin: 0;
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
}

.page {
  min-height: 100vh;
  background: #f3f4f6;
  color: #111827;
}

.header {
  padding: 20px 24px;
  background: #0f172a;
  color: #f9fafb;
}

.header h1 {
  margin: 0;
  font-size: 24px;
}

.subtitle {
  margin: 4px 0 0;
  font-size: 14px;
  opacity: 0.85;
}

.content {
  padding: 24px;
  display: grid;
  gap: 16px;
}

.card {
  background: #ffffff;
  border-radius: 10px;
  padding: 16px 20px;
  box-shadow: 0 2px 6px rgba(15, 23, 42, 0.08);
}

.card h2 {
  margin-top: 0;
  font-size: 18px;
}

button {
  margin-top: 8px;
  padding: 8px 16px;
  border-radius: 6px;
  border: none;
  background: #2563eb;
  color: #f9fafb;
  font-size: 14px;
  cursor: pointer;
}

button:disabled {
  opacity: 0.6;
  cursor: default;
}

textarea {
  width: 100%;
  min-height: 80px;
  margin-top: 8px;
  padding: 8px;
  border-radius: 6px;
  border: 1px solid #d1d5db;
  font-family: inherit;
  font-size: 14px;
}

.result {
  margin-top: 12px;
  padding: 8px;
  background: #f9fafb;
  border-radius: 6px;
  border: 1px solid #e5e7eb;
}

pre {
  margin: 0;
  white-space: pre-wrap;
  word-break: break-all;
  font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, 'Liberation Mono', 'Courier New', monospace;
  font-size: 13px;
}

.error {
  color: #b91c1c;
  font-weight: 500;
}
</style>


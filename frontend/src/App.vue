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
            v-model="messageEcho1"
            placeholder="輸入要傳給後端的訊息，例如：Hello Bank!"
        />
        <button :disabled="loadingEcho1" @click="callEcho1">
          {{ loadingEcho1 ? '傳送中…' : '呼叫 /api/echo' }}
        </button>

        <div class="result" v-if="echoResponse1 || echoError1">
          <h3>回應結果</h3>
          <p v-if="echoError1" class="error">{{ echoError1 }}</p>
          <pre v-else>{{ echoResponse1 }}</pre>
        </div>
      </section>

      <section class="card">
        <h2>傳送簡單訊息（POST /api/echo2）</h2>
        <p>如果你有實作 <code>POST /api/echo2</code>，可以在這裡測試；沒有也沒關係，可以之後再改 URL。</p>

        <textarea
            v-model="messageEcho2"
            placeholder="輸入要傳給後端的訊息，例如：Hi Bank!"
        />
        <button :disabled="loadingEcho2" @click="callEcho2">
          {{ loadingEcho2 ? '傳送中…' : '呼叫 /api/echo2' }}
        </button>

        <div class="result" v-if="echoResponse2 || echoError2">
          <h3>回應結果</h3>
          <p v-if="echoError2" class="error">{{ echoError2 }}</p>
          <pre v-else>{{ echoResponse2 }}</pre>
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

// 第一個 echo 區塊狀態 (/api/echo)
const messageEcho1 = ref('')
const loadingEcho1 = ref(false)
const echoResponse1 = ref('')
const echoError1 = ref('')

// 第二個 echo 區塊狀態 (/api/echo2)
const messageEcho2 = ref('')
const loadingEcho2 = ref(false)
const echoResponse2 = ref('')
const echoError2 = ref('')

async function callBackend() {
  loading.value = true
  error.value = ''
  responseText.value = ''
  try {
    const res = await fetch('/api/hello')
    if (!res.ok) {
      throw new Error(`HTTP ${res.status}`)
    }
    const text = await res.text()
    responseText.value = text
  } catch (e) {
    error.value = '呼叫 /api/hello 失敗：' + (e instanceof Error ? e.message : String(e))
  } finally {
    loading.value = false
  }
}

async function callEcho1() {
  loadingEcho1.value = true
  echoError1.value = ''
  echoResponse1.value = ''
  try {
    const res = await fetch('/api/echo', {
      method: 'POST',
      headers: {
        'Content-Type': 'text/plain;charset=utf-8'
      },
      body: messageEcho1.value
    })

    if (!res.ok) {
      throw new Error(`HTTP ${res.status}`)
    }

    const text = await res.text()
    echoResponse1.value = text
  } catch (e) {
    echoError1.value = '呼叫 /api/echo 失敗：' + (e instanceof Error ? e.message : String(e))
  } finally {
    loadingEcho1.value = false
  }
}

async function callEcho2() {
  loadingEcho2.value = true
  echoError2.value = ''
  echoResponse2.value = ''
  try {
    const res = await fetch('/api/echo2', {
      method: 'POST',
      headers: {
        'Content-Type': 'text/plain;charset=utf-8'
      },
      body: messageEcho2.value
    })

    if (!res.ok) {
      throw new Error(`HTTP ${res.status}`)
    }

    const text = await res.text()
    echoResponse2.value = text
  } catch (e) {
    echoError2.value = '呼叫 /api/echo2 失敗：' + (e instanceof Error ? e.message : String(e))
  } finally {
    loadingEcho2.value = false
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

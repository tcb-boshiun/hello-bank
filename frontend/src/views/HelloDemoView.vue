<template>
  <div>
    <h2>Hello</h2>
    <div>
      <button @click="callHello">Hello</button>
      <p v-if="hello">{{ hello }}</p>
      <br><br>
    </div>
    <div>
      <button @click="callEcho">Echo</button>
      <input v-model="text1" placeholder="輸入文字"/>
      <p v-if="echo1">{{ echo1 }}</p>
      <br><br>
    </div>
    <div>
      <button @click="callEchoV2">Echo_v2</button>
      <input v-model="text2" placeholder="輸入文字"/>
      <p v-if="echo2">{{ echo2 }}</p>
      <br><br>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import {getHello, echo, echoV2} from "../api/hello";

const hello = ref("");
const text1 = ref("");
const echo1 = ref("");
const text2 = ref("");
const echo2 = ref("");

async function callHello() {
  const res = await getHello();
  hello.value = res.data;
}

async function callEcho() {
  const res = await echo(text1.value);
  echo1.value = res.data;
}

async function callEchoV2() {
  const res = await echoV2(text2.value);
  echo2.value = res.data;
}
</script>

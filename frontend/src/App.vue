<template>
  <div id="mainBody">
    <input id="input" v-model="inputStr" @keyup.enter="handleClick" />
    <button @click="handleClick">排序</button>
    <p>{{ result.join(' ') }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const inputStr = ref('')
const result = ref([])

const handleClick = async() => {
  result.value=[];

  const parts = inputStr.value.trim().split(/\s+/)
  const nums = parts.map(Number).filter(x => !isNaN(x))

  const response = await fetch("http://localhost:8080/sort", {
    method: "POST",
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(nums)
  })
  result.value = await response.json();

}

</script>

<style scoped>
#mainBody {
  text-align: center;
  margin-top: 50px;
}
</style>
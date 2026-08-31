<template>
  <div id="mainBody">
    <input id="input" v-model="inputStr" @keyup.enter="handleClick" placeholder="输入若干整数，用空格分隔" />
    <button id="button" @click="handleClick">排序</button>

    <div v-if="steps.length > 0" class="steps-container">
      <p>归并排序过程：</p>
      <div v-for="(step, index) in steps" :key="index" class="step">
        <span class="step-index">第 {{ index + 1 }} 步：</span>
        <span>{{ step.join(' ') }}</span>
      </div>
    </div>

    <p v-if="final.length>0">最终结果：{{ final }}</p>


    <p v-if="errorMsg" style="color:red">{{ errorMsg }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const inputStr = ref('')
const steps = ref([])
const errorMsg = ref('')
const final = ref([])

const handleClick = async () => {
  steps.value = []
  errorMsg.value = ''

  const parts = inputStr.value.trim().split(/\s+/)
  const nums = parts.map(Number).filter(x => !isNaN(x))

  if (nums.length === 0) {
    errorMsg.value = '请输入至少一个有效整数'
    return
  }

  try {
    const response = await fetch('http://localhost:8080/sort', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(nums)
    })
    if (!response.ok) throw new Error('请求失败')
    steps.value = await response.json()
    final.value = steps.value[steps.value.length - 1] || []
  } catch (err) {
    errorMsg.value = '排序出错：' + err.message
  }
}
</script>

<style scoped>
#mainBody {
  text-align: center;
  margin-top: 50px;
}

#input {
  margin: 10px;
  height: 30px;
  width: 500px;
}

#button {
  height: 30px;
}

.steps-container {
  margin-top: 24px;
}

.step {
  margin: 8px 0;
  font-size: 16px;
  background: #f8f9fa;
  padding: 6px 12px;
  border-radius: 4px;
  display: block;
}

.step-index {
  font-weight: bold;
  margin-right: 10px;
  color: #42b883;
}
</style>
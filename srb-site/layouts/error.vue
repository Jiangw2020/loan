<template>
  <div>
    <p>错误页面：</p>
    <p>错误信息 -</p>
    {{ error }}
  </div>
</template>

<script>
export default {
  props: ['error'],
}
</script>

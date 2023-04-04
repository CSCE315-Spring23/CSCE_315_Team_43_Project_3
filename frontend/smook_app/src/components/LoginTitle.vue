<script setup>
defineProps({
  msg: {
    type: String,
    required: true
  }
})
</script>

<template>
  <div class="greetings">
    <h1 class="green">{{ msg }}</h1>
    <img alt="Vue logo" class="logo" src="@/assets/smookLogo.svg" width="125" height="125" />
  </div>
</template>

<style scoped>
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  top: -10px;
}

h3 {
  font-size: 1.2rem;
}
img {
  display: block;
  margin-left: auto;
  margin-right: auto;
}
.greetings h1,
.greetings h3 {
  text-align: center;
}
/* .greetings {
} */
/* @media (min-width: 1024px) {
  .greetings h1,
  .greetings h3 {
    text-align: left;
  }
} */
</style>

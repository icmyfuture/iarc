<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Dark Souls</h2>
    <div>
      <button class="btn btn-sm" @click="gotoNext">下一步</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    gotoNext: function () {
      let me = this
      let user = {'name': 'ivan dark souls iii'}
      me.$router.push({name: 'next', params: user})
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>

<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>NIOH</h2>
    <div>
      <button class="btn btn-sm" @click="getAjaxResult">ajax test</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Next',
  data () {
    return {
      msg: this.$route.params.name
    }
  },
  methods: {
    getAjaxResult: function () {
      let me = this
      me.$ajax('/user/getUser/ivan', function (res) {
        alert(JSON.stringify(res))
      }, function (res) {
        alert(res)
      })
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

require('es6-promise').polyfill()
require('isomorphic-fetch')

export default{
  fget (url, success, failure) {
    var me = this
    if (me.$emit) {
      me.$emit('BusyEvent', true)
    }
    fetch(window.$config.APIHost + url, {
      method: 'get',
      mode: 'cors',
      header: {'Access-Control-Allow-Origin': '*'}
    }).then(function (res) {
      if (me.$emit) {
        me.$emit('BusyEvent', false)
      }
      if (res.ok) {
        res.json().then(function (json) {
          success && typeof (success) === 'function' && success(json)
        })
      } else {
        res.text().then(function (error) {
          failure && typeof (failure) === 'function' && failure(error)
        })
      }
    }).catch(function (res) {
      if (me.$emit) {
        me.$emit('BusyEvent', false)
      }
    })
  },
  fpost (url, data, success, failure) {
    var me = this
    if (me.$emit) {
      me.$emit('BusyEvent', true)
    }
    alert(1)
    fetch(window.$config.APIHost + url,
      {
        method: 'post',
        credentials: 'include',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
      }).then(function (res) {
      if (me.$emit) {
        me.$emit('BusyEvent', false)
      }
      if (res.ok) {
        res.json().then(function (json) {
          success && typeof (success) === 'function' && success(json)
        })
      } else {
        res.text().then(function (error) {
          failure && typeof (failure) === 'function' && failure(error)
        })
      }
    }).catch(function (res) {
      if (me.$emit) {
        me.$emit('BusyEvent', false)
      }
    })
  }
}

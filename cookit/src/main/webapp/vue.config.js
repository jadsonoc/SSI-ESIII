const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

module.exports = {
  devServer: {
    port: 8083,
    proxy: 'http://localhost:8082',
    proxy: 'http://localhost:8082/userManager/login'
  } }
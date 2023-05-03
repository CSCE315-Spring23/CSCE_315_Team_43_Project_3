module.exports = {
    // https://cli.vuejs.org/config/#devserver-proxy
    devServer: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'https://smook-app.uc.r.appspot.com/api',
                ws: true,
                secure: false, 
                changeOrigin: true
            }
        }
    }
}
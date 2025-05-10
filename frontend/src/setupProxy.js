const {createProxyMiddleware} = require('http-proxy-middleware');
module.exports = function (app) {
	app.use(
		createProxyMiddleware(['/v1', '/users'], {
			target: 'http://127.0.0.1:8080',
			changeOrigin: true
		})
	)
};
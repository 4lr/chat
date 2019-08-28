// @ts-ignore
const proxy = require('http-proxy-middleware');

const config = {
    target: 'http://localhost:8080'
};

module.exports = (app) => {

    app.use(
        proxy('/api', {
            ...config,
        }),
    );

    app.use(
        proxy('/ws', {
            ...config,
            ws: true,
            followRedirects: true,
        }),
    );

};

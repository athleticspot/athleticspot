const webpack = require('webpack');
const writeFilePlugin = require('write-file-webpack-plugin');
const webpackMerge = require('webpack-merge');
const BrowserSyncPlugin = require('browser-sync-webpack-plugin');
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const execSync = require('child_process').execSync;
const fs = require('fs');
const path = require('path');

const commonConfig = require('./webpack.common.js');

const ENV = 'prod';

const ddlPath = './target/www/vendor.json';

if (!fs.existsSync(ddlPath)) {
    execSync('webpack --config webpack/webpack.vendor.js');
}

module.exports = webpackMerge(commonConfig({env: ENV}), {
    devtool: 'source-map',
    output: {
        path: path.resolve('./target/www'),
        filename: 'app/[name].bundle.js',
        chunkFilename: 'app/[id].chunk.js'
    },
    plugins: [
        new ExtractTextPlugin('styles.css'),
        new webpack.NoEmitOnErrorsPlugin(),
        new webpack.NamedModulesPlugin(),
        new writeFilePlugin(),
        new webpack.WatchIgnorePlugin([
            path.resolve('./src/test'),
        ])
    ]
});

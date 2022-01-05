const path = require("path");
const webpack = require("webpack");

const HtmlWebPackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');

module.exports = {
    mode: 'development',
    entry: './src/main.js',
    output: {
        path: path.resolve(__dirname, 'www'),
        filename: (chunkData) => {
            return chunkData.chunk.name === 'service-worker'
                ? '[name].js'
                : '[name].[chunkhash:8].js';
        },
        publicPath: '',
    },
    devtool: "inline-source-map",
    devServer: {
        // static: {
        //     directory: path.join(__dirname, 'public'),
        //   },
        // compress: true,
        port: 8800,
        // hot:true,
        // host:"localhost",
        // port: 8800,
    },
    resolve: {
        alias: {
            // vue$: 'vue/dist/vue.esm.js'
            // 'vue$': 'vue/dist/vue.common.js',
            'vue$': 'vue/dist/vue.esm.js'
        },
        extensions: ['.ts', '.js', '.json', '.jsx', '.css'],
        modules: ['node_modules'],
    },
    optimization: {
        minimize: false,
        splitChunks: {},
        concatenateModules: true, // false with ace
    },
    module: {
        rules: [
            {
                test: /\.vue?$/,
                loader: "vue-loader",
                options: { esModule: true }
            },
            {
                test:  /\.s?[ac]ss$/,
                use: [
                    'vue-style-loader',
                    'css-loader',
                    {
                        loader: 'sass-loader',
                        // Requires sass-loader@^7.0.0
                        options: {
                          implementation: require('sass'),
                        },
                        // Requires >= sass-loader@^8.0.0
                        options: {
                          implementation: require('sass'),
                          sassOptions: {
                          },
                        },
                      },
                ]
            },
        ]
    },
    plugins: [
        new webpack.LoaderOptionsPlugin({ minimize: true }),
        new webpack.EnvironmentPlugin({ NODE_ENV: 'development', DEBUG: false }),
        new CleanWebpackPlugin({ cleanAfterEveryBuildPatterns: ['./www'] }),
        new HtmlWebPackPlugin({ template: './src/index.html', filename: './index.html' }),
        new CopyWebpackPlugin({
            patterns: [
                { from: './assets', to: './assets' },
                { from: './fonts/**/*.{otf,woff,woff2,json,ttf}', to: '.' },
                // { from: './externals/kakao.min.js', to: './js' },
            ],
        }),
        new VueLoaderPlugin(),
        new webpack.HotModuleReplacementPlugin()
    ],
};
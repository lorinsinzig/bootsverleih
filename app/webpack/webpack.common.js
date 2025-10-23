const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {
    entry: {
        'main': './app/typescripts/Loader.ts'
    },
    output: {
        path: path.resolve('./public/javascripts'),
        publicPath: '/assets/javascripts',
        filename: '[name].js'
    },
    resolve: {
        extensions: ['.ts', '.js', '.scss']
    },
    module: {
        rules: [
            {
                test: /\.(sa|sc|c)ss$/,
                exclude: /node_modules/,
                use: [
                    'raw-loader',
                    {
                        loader: MiniCssExtractPlugin.loader,
                    },
                    'css-loader',
                    'sass-loader'
                ]
            },

            {
                test: /\.ts$/,
                use: [{
                    loader: 'ts-loader',
                    options: {
                        configFile: path.resolve('./tsconfig.json')
                    }
                }]
            }
        ]
    },
    plugins: [
        new MiniCssExtractPlugin({
            filename: "[name].css"
            // filename: isDevelopment ? '[name].css' : '[name].[hash].css',
            // chunkFilename: isDevelopment ? '[id].css' : '[id].[hash].css'
        })
    ],
};
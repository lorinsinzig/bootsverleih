const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {
    entry: {
        'main': './app/typescripts/Loader.ts',
        'styles': './app/stylesheets/main.scss'
    },
    output: {
        path: path.resolve(__dirname, '../../public'),
        publicPath: '/assets/',
        filename: 'javascripts/[name].js'
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
                    MiniCssExtractPlugin.loader,
                    {
                        loader: 'css-loader',
                        options: {
                            url: false
                        }
                    },
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
            filename: 'stylesheets/[name].css'
        })
    ],
};
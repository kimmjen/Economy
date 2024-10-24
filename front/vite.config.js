import {defineConfig} from 'vite'
import path from 'path';
import react from '@vitejs/plugin-react'

console.log(path.resolve(__dirname))
// https://vitejs.dev/config/
export default defineConfig({
    plugins: [react()],
    resolve: {
        alias: {
            '@': path.resolve(__dirname, './src'), // '@'를 'src'로 별칭 설정
        },
    },
    build: {
        outDir: '../backend/src/main/resources/static',
        target: 'esnext',
        modulePreload: false,
        minify: false,
        manifest: true,
        cssCodeSplit: false,
        assetsInlineLimit: 8096,
        rollupOptions: {
            output: {
                format: 'esm',
                minifyInternalExports: false,
            },
        },
    },
    server: {
        proxy: {
            '/**': {
                target: 'http://localhost:8080',
                changeOrigin: true,
            }
        }
    }
})

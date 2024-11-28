import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'node:path'
import autoprefixer from 'autoprefixer'
import tailwindcss from 'tailwindcss'
import { viteStaticCopy } from 'vite-plugin-static-copy'

export default defineConfig(() => {
  return {
    base: '/NEIN-PMS',
    build: {
      // outDir: 'build',
      outDir: 'NEIN-PMS',
    },
    css: {
      postcss: {
        plugins: [
          tailwindcss({}), // tailwind css import into vite app
          autoprefixer({}), // add options if needed
        ],
      },
    },
    esbuild: {
      loader: 'jsx',
      include: /src\/.*\.jsx?$/,
      exclude: [],
    },
    optimizeDeps: {
      force: true,
      esbuildOptions: {
        loader: {
          '.js': 'jsx',
        },
      },
    },
    plugins: [
      react(),
      viteStaticCopy({
        targets: [
          {
            src: 'WEB-INF/**/*', // Path to your WEB-INF directory
            dest: 'WEB-INF', // Destination folder inside the build output
          },
        ],
      }),
    ],
    resolve: {
      alias: [
        {
          find: 'src/',
          replacement: `${path.resolve(__dirname, 'src')}/`,
        },
      ],
      extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.scss'],
    },
    server: {
      port: 3000,
      // host: '10.206.73.59',
      proxy: {

      },
    },
  }
})

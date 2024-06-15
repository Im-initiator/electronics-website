/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  theme: {
    extend: {
      height: {
        screenLayout: "calc(100vh - 106px)",
      },
    },
  },
  plugins: [],
}


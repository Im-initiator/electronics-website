/** @type {import('next').NextConfig} */

const nextConfig = {
    cssModules: true,
    cssLoaderOptions: {
        importLoaders: 1,
        localIdentName: "[]",
    },
};

export default nextConfig;

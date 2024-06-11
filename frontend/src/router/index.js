import { createRouter, createWebHistory } from 'vue-router';
import ClientLayout from '@/views/layouts/ClientLayout.vue';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: ClientLayout
        },
        {
            path: '/admin'
        },
        {
            path: '/auth',
            children: [
                {
                    path: 'login'
                },
                {
                    path: 'register'
                }
            ]
        }
    ]
});

export default router;

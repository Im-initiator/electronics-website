import { createRouter, createWebHistory } from 'vue-router';
import ClientLayout from '@/views/layouts/ClientLayout.vue';
import AuthLayout from '@/views/layouts/AuthLayout.vue';

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
            component: AuthLayout,
            children: [
                {
                    path: 'login',
                    component: () => import('@/views/LoginPage.vue')
                },
                {
                    path: 'register'
                }
            ]
        }
    ]
});

export default router;

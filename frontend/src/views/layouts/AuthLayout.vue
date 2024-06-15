<template>
    <header-component></header-component>
    <div class="h-screenLayout w-screen bg-sky-300 flex justify-center items-center">
        <router-view></router-view>
    </div>
</template>

<script setup>
import HeaderComponent from '@/components/HeaderComponent.vue';
</script>
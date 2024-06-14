<script setup>
import MenuIcon from '@/assets/icons/menu.svg';
import CrossIcon from '@/assets/icons/cross.svg';
import CartIcon from '@/assets/icons/cart.svg';
import UserIcon from '@/assets/icons/user.svg';
import SearchIcon from '@/assets/icons/search.svg';
import FilterIcon from '@/assets/icons/filter.svg';

import { ref } from 'vue';
const isMenuOpen = ref(false);
const setMenuStatus = (value) => isMenuOpen.value = value;

const isSearching = ref(false);
const setSearchingStatus = () => isSearching.value = !isSearching.value;

</script>

<template>
    <header class="w-screen px-4 py-2 bg-white">
        <div class="flex items-center justify-between max-w-[1000px] mx-auto">
            <div class="flex items-center gap-2">
                <div class="w-8 h-8 md:hidden" @click="setMenuStatus(true)">
                    <MenuIcon class="w-full h-auto"/>
                </div>
                <div class="w-40 h-auto">
                    <img class="w-full h-auto" src="/Horizontal_Logo.png" alt="logo" />
                </div>
            </div>
            <nav class="hidden md:flex gap-6">
                <router-link to="">Khuyến mãi</router-link>
                <router-link to="">Điện thoại</router-link>
                <router-link to="">Laptop</router-link>
                <router-link to="">Phụ kiện</router-link>
                <router-link to="">Cửa hàng</router-link>
                <router-link to="">Bài viết</router-link>
            </nav>
            <div class="flex items-center gap-4">
                <router-link to=""><CartIcon class="w-6 h-6"/></router-link>
                <router-link to=""><UserIcon class="w-6 h-6"/></router-link>
                <SearchIcon class="w-6 h-6 cursor-pointer" @click="setSearchingStatus"/>
            </div>
            <nav class="fixed h-screen w-screen text-2xl font-medium flex flex-col gap-4 top-0 left-0 px-8 py-16 bg-white transition-transform duration-500 ease-in-out md:hidden z-20" :class="isMenuOpen ? 'translate-x-0' : '-translate-x-full'">
                <CrossIcon class="absolute top-8 right-16 w-6 h-6" @click="setMenuStatus(false)"/>
                <router-link to="">Khuyến mãi</router-link>
                <router-link to="">Điện thoại</router-link>
                <router-link to="">Laptop</router-link>
                <router-link to="">Phụ kiện</router-link>
                <router-link to="">Cửa hàng</router-link>
                <router-link to="">Bài viết</router-link>
            </nav>
        </div>
        <div v-if="isSearching" class="flex items-center gap-2 overflow-hidden max-w-[1000px] mx-auto">
            <div class="basis-full flex items-center gap-2 rounded-full border border-gray-500 px-4 py-2 text-md">
                <SearchIcon class="w-4 h-4"/>
                <input type="text" placeholder="Search" class="outline-none text-sm selection:bg-gray-300"/>
            </div>
            <div class="flex text-xs items-end basis-11">
                <FilterIcon class="w-6 h-6"/>
                <div>Lọc</div>
            </div>
        </div>
    </header>
</template>
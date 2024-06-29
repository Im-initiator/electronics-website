<script setup>
import { ref } from 'vue';
import axios from 'axios';

const isUsernameFocused = ref(false);
const username = ref('');
const setUsername = (e) => {
    username.value = e.target.value;
}

const isPasswordFocused = ref(false);
const password = ref('');
const setPassword = (e) => {
    password.value = e.target.value;
}

const handleLogin = async () => {
    try {
        const res = await axios.post('http://localhost:8080/login', {
            username: username.value,
            password: password.value
        });

        const { data } = res;
        if (data.success) {
            alert('Login successful');
        } else {
            alert('Login failed');
        }
        
    } catch (error) {
        console.error(error);
    }
}
</script>

<template>
    <form class="px-8 py-16 bg-white rounded-md text-sky-800 text-lg drop-shadow-md" @submit.prevent="handleLogin">
        <h1 class="text-4xl font-bold uppercase">Welcome back</h1>
        <h1 class="text-xl font-semibold">Log in to continue</h1>
        <div class="relative border border-sky-700 rounded-md mt-8">
            <label class="absolute -top-6 left-4 transition-transform duration-200 ease-in-out" :class="isUsernameFocused  || username !== '' ? 'translate-y-0' : 'translate-y-8'" for="username">Username</label>
            <input required @change="setUsername" class="bg-transparent px-4 py-2 outline-none md:min-w-[400px]" type="text" placeholder="Username" id="username" name="username" @focus="isUsernameFocused = true" @blur="isUsernameFocused = false"/>
        </div>
        <div class="relative border border-sky-700 rounded-md mt-8">
            <label class="absolute -top-6 left-4 transition-transform duration-200 ease-in-out" :class="isPasswordFocused  || password !== '' ? 'translate-y-0' : 'translate-y-8'" for="password">Password</label>
            <input required @change="setPassword" class="bg-transparent px-4 py-2 outline-none md:min-w-[400px]" type="password" placeholder="Password" id="password" name="password" @focus="isPasswordFocused = true" @blur="isPasswordFocused = false"/>
        </div>
        <div class="mt-4">
            <a href="#" class="text-sky-700">Forgot password?</a>
        </div>
        <div class="flex justify-end mt-4">
            <button class="px-4 py-2 rounded-md bg-sky-700 text-white" type="submit">Login</button>
        </div>
    </form>
</template>
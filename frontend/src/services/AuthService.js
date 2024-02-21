import axios from "axios";

const API_URL = 'http://localhost:9001/api/users/';

const AuthService = {
    register: async function (userData) {
        const response = await axios.post(API_URL + 'register', userData);
        return response.data;
    },

    login: async function (username, password) {
        const response = await axios.post(API_URL + 'authenticate', { username, password });
        return response.data;
    },

    logout: function () {
        // Implement logout logic if needed
    },

    updateProfile: async function (userData) {
        const response = await axios.post(API_URL + 'profile/update', userData);
        return response.data;
    },

    getUserProfile: async function (userId) {
        const response = await axios.get(API_URL + 'profile/' + userId);
        return response.data;
    }
};

export default AuthService;

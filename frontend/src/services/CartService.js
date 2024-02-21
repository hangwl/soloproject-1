import axios from 'axios';

const API_URL = 'http://localhost:9001/api/carts';

const CartService = {

    getCartItems: async function (userId) {
        const response = await axios.get(`${API_URL}/user/${userId}`);
        return response.data;
    },

    addToCart: async function (userId, cartItemDto) {
        return axios.post(`${API_URL}/user/${userId}/add`, cartItemDto);
    },

    removeFromCart: async function (userId, cartItemId) {
        return axios.delete(`${API_URL}/user/${userId}/item/${cartItemId}`);
    },

    clearCart: async function (userId) {
        return axios.post(`${API_URL}/user/${userId}/clear`);
    }

};

export default CartService;

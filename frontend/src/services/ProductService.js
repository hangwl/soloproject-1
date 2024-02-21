import axios from 'axios';

const API_URL = 'http://localhost:9001/api/products/';

const ProductService = {
    fetchAllProducts: async function () {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            console.error("Error fetching products:", error);
            throw error;
        }
    }
};

// Export the named object
export default ProductService;
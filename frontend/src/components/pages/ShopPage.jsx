import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import '../../styles/ShopPage.css';
import CartService from '../../services/CartService';
import ProductService from '../../services/ProductService';

const ShopPage = ({ user }) => {
    const [products, setProducts] = useState([]);
    const [quantities, setQuantities] = useState({}); // Track quantities for each product
    const [showPopup, setShowPopup] = useState(false);


    useEffect(() => {
        ProductService.fetchAllProducts()
            .then(data => {
                setProducts(data);
                // Initialize quantities and total prices for each product
                const initialQuantities = {};
                data.forEach(product => {
                    initialQuantities[product.productId] = 1; // Default quantity set to 1
                });
                setQuantities(initialQuantities);
            })
            .catch(error => console.error("Failed to fetch products:", error));
    }, []);

    const handleQuantityChange = (productId, value) => {
        setQuantities({ ...quantities, [productId]: value });
    };

    const handleAddToCart = (productId) => {
        if (user && user.userId) {
            const quantity = quantities[productId]; // Get the selected quantity

            const cartItemDto = {
                productId: productId,
                quantity: quantity
            };

            CartService.addToCart(user.userId, cartItemDto)
                .then(() => {
                    console.log('Product added to cart');
                    setShowPopup(true);
                })
                .catch(error => {
                    console.error('Failed to add product to cart:', error);
                });
        } else {
            console.log('User ID is not available');
        }
    };

    const Popup = ({ onClose }) => {
        return (
            <div className="popup">
                <div className="popup-content">
                    <p>Product added to cart!</p>
                    <button onClick={onClose}>Close</button>
                </div>
            </div>
        );
    };

    return (
        <div>
            <h1>Shop</h1>
            {user && <Link to="/cart">View Cart</Link>}
            <div className="shop-container">
                {products.map(product => (
                    <div key={product.productId} className="product-card">
                        <h2>{product.name}</h2>
                        <p>{product.description}</p>
                        <p>${product.price}</p>
                        <div className="cart-actions">
                            <input
                                type="number"
                                min="1"
                                value={quantities[product.productId]}
                                onChange={(e) => handleQuantityChange(product.productId, parseInt(e.target.value, 10))}
                            />
                            <button onClick={() => handleAddToCart(product.productId)}>Add to Cart</button>
                        </div>
                    </div>
                ))}
            </div>
            {showPopup && <Popup onClose={() => setShowPopup(false)} />}
        </div>
    );
};

export default ShopPage;
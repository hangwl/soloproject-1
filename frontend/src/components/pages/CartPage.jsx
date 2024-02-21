import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import CartService from '../../services/CartService';
import '../../styles/CartPage.css';

const CartPage = ({ user }) => {
    const [cartItems, setCartItems] = useState([]);
    const [responseMessage, setResponseMessage] = useState('');

    useEffect(() => {
        if (user && user.userId) {
            CartService.getCartItems(user.userId)
                .then(data => setCartItems(data.cartItems))
                .catch(error => {
                    console.error("Failed to fetch cart items:", error);
                    setResponseMessage('Failed to load cart items');
                });
        }
    }, [user]);

    const calculateItemTotal = (price, quantity) => {
        return (price * quantity).toFixed(2);
    };

    const calculateCartTotal = () => {
        return cartItems.reduce((total, item) => total + item.productPrice * item.quantity, 0).toFixed(2);
    };

    const handleRemoveItem = (cartItemId) => {
        if (user && user.userId) {
            CartService.removeFromCart(user.userId, cartItemId)
                .then(() => {
                    setCartItems(cartItems.filter(item => item.cartItemId !== cartItemId));
                    // Optionally: Show a success message or update total amount
                })
                .catch(error => console.error('Failed to remove item:', error));
        }
    };

    const handleClearCart = () => {
        if (user && user.userId) {
            CartService.clearCart(user.userId)
                .then(() => {
                    setCartItems([]);
                    setResponseMessage('Cart cleared successfully');
                })
                .catch(error => {
                    console.error("Failed to clear cart:", error);
                    setResponseMessage('Failed to clear cart');
                });
        }
    };

    const handleCheckout = () => {
        console.log('Proceeding to checkout...');
        // Add logic to handle checkout process
    };

    return (
        <div className="cart-page">
            <h1>Your Cart</h1>
            {cartItems.length === 0 ? (
                <p>Your cart is empty.</p>
            ) : (
                cartItems.map(item => (
                    <div key={item.cartItemId} className="cart-item">
                        <p>Product: {item.productName}</p>
                        <p>Price: ${item.productPrice}</p>
                        <p>Quantity: {item.quantity}</p>
                        <p>Total: ${calculateItemTotal(item.productPrice, item.quantity)}</p>
                        <button onClick={() => handleRemoveItem(item.cartItemId)}>Remove</button>
                    </div>
                ))
            )}
            <div className="cart-footer">
                <h2>Total Cost: ${calculateCartTotal()}</h2>
                {cartItems.length > 0 && (
                    <>
                        <button onClick={handleClearCart} className="clear-cart-button">Clear Cart</button>
                        <button onClick={handleCheckout} className="checkout-button">Checkout</button>
                    </>
                )}
                <div><Link to="/shop" className="continue-shopping-link">Continue Shopping</Link></div>
            </div>
            {responseMessage && <p className="response-message">{responseMessage}</p>}
        </div>
    );
};

export default CartPage;

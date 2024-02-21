import React from 'react';
import { Link } from 'react-router-dom';

const UserHomePage = ({ user }) => {
    return (
        <div>
            <h1>{user.username}'s Home Page</h1>
            <p>Welcome to your dashboard!</p>
            {/* Additional user-specific content goes here */}
            <p><Link to={`/shop?userId=${user.userId}`}>Shop</Link></p>
            <p><Link to="/cart">View Cart</Link></p>
            {/* Link to order history */}
        </div>
    );
};

export default UserHomePage;

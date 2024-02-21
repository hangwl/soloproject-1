import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import AuthService from '../../services/AuthService';

const LoggedInNavbar = ({ userId, onLogout }) => {
    const navigate = useNavigate();

    const handleLogout = () => {
        AuthService.logout();
        onLogout();
        navigate('/login');
    };

    return (
        <nav>
            <Link to="/home">Home</Link>
            <Link to={`/user/${userId}`}>Profile</Link>
            <Link to="/login" onClick={handleLogout}>Logout</Link>
        </nav>
    );
};

export default LoggedInNavbar;

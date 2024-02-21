import React, { useState } from "react";
import AuthService from "../../services/AuthService";
import { useNavigate } from 'react-router-dom';

const LoginPage = ({ onLogin }) => {

    const [credentials, setCredentials] = useState({ username: '', password: '', });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const userData = await AuthService.login(credentials.username, credentials.password);
            onLogin(userData);
            navigate('/home');
            setError('');

        } catch (err) {
            if (err && err.response && err.response.data && err.response.data.message) {
                setError(err.response.data.message);
            } else {
                setError('Login failed. Please check your credentials.');
            }
        }
    };

    const handleChange = (e) => {
        setCredentials({
            ...credentials,
            [e.target.name]: e.target.value,
        });
    };

    return (
        <div>
            <h1>Login</h1>
            <form onSubmit={handleLogin}>
                <input
                    name="username"
                    type="text"
                    placeholder="Username"
                    value={credentials.username}
                    onChange={handleChange}
                />
                <input
                    name="password"
                    type="password"
                    placeholder="Password"
                    value={credentials.password}
                    onChange={handleChange}
                />
                <button type="submit">Login</button>
                {error && <p className="error">{error}</p>}
            </form>
        </div>
    );
};

export default LoginPage;
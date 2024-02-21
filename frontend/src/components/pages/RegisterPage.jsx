import React, { useState } from "react";
import AuthService from "../../services/AuthService";

const RegisterPage = () => {

    const [userData, setUserData] = useState(
        {
            username: '',
            password: '',
        }
    );

    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            await AuthService.register(userData);
            // Handle successful registration
            setSuccess(true);
            setError('');
        } catch (err) {
            // Check if the error has a response and a data field
            if (err && err.response && err.response.data && err.response.data.message) {
                setError(err.response.data.message);
            } else {
                setError('Registration failed. Please try again.');
            }

            setSuccess(false);

        }
    };

    const handleChange = (e) => {
        setUserData({
            ...userData,
            [e.target.name]: e.target.value,
        });
    };

    return (
        <div>
            <h1>Register</h1>
            <form onSubmit={handleRegister}>
                <input
                    name="username"
                    type="text"
                    placeholder="Username"
                    value={userData.username}
                    onChange={handleChange}
                />
                <input
                    name="password"
                    type="password"
                    placeholder="Password"
                    value={userData.password}
                    onChange={handleChange}
                />
                {/* Add other fields as needed */}
                <button type="submit">Register</button>
                {success && <p className="success">Registration successful!</p>}
                {error && <p className="error">{error}</p>}
            </form>
        </div>
    );
};

export default RegisterPage;
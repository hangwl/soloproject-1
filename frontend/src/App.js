import React, { useState } from 'react';
import './App.css';
import './styles/global.css';
import { Route, Routes, Navigate } from 'react-router-dom';
import LandingPage from './components/pages/LandingPage';
import LoginPage from './components/pages/LoginPage';
import RegisterPage from './components/pages/RegisterPage';
import UserHomePage from './components/pages/UserHomePage';
import LoggedInNavbar from './components/layout/LoggedInNavbar';
import Navbar from './components/layout/Navbar';
import UserProfilePage from './components/pages/UserProfilePage';
import ShopPage from './components/pages/ShopPage';
import CartPage from './components/pages/CartPage';

function App() {
  const [user, setUser] = useState(null);

  const handleLogin = (userData) => {
    setUser(userData);
  };

  const handleLogout = () => {
    setUser(null);
  }

  const handleUserUpdate = (updatedUserData) => {
    setUser(updatedUserData);
};

  return (
    <div className="App">
            {user ? <LoggedInNavbar userId={user.userId} onLogout={handleLogout} /> : <Navbar />}
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/home" element={user ? <UserHomePage user={user} /> : <Navigate to="/login" />} />
        <Route path="/login" element={<LoginPage onLogin={handleLogin} />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/user/:userId" element={<UserProfilePage user={user} onUserUpdate={handleUserUpdate} />} />
        <Route path="/shop" element={<ShopPage user={user}/>} />
        <Route path="/cart" element={<CartPage user={user} />} />
      </Routes>
    </div>
  );
}

export default App;
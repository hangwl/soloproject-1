import React, { useState, useEffect } from 'react';
import AuthService from '../../services/AuthService';
import { useParams } from 'react-router-dom';

const UserProfilePage = ({ onUserUpdate }) => {
    const { userId } = useParams();
    const [userProfile, setUserProfile] = useState(null);
    const [editableUser, setEditableUser] = useState({ email: '', address: '' });
    const [isEditing, setIsEditing] = useState(false);

    useEffect(() => {
        if (userId) {
            AuthService.getUserProfile(userId)
                .then(data => {
                    setUserProfile(data);
                    setEditableUser({ email: data.email, address: data.address });
                })
                .catch(error => {
                    console.error("Error fetching user profile:", error);
                });
        }
    }, [userId]);

    const handleChange = (e) => {
        setEditableUser({ ...editableUser, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // Make sure to include the user ID in the updated data
            const updatedData = { ...editableUser, userId: userProfile.userId };
            const updatedUser = await AuthService.updateProfile(updatedData);

            setUserProfile(updatedUser);

            if (onUserUpdate) {
                onUserUpdate(updatedUser);
            }
            
            setIsEditing(false);
        } catch (error) {
            console.error("Failed to update profile:", error);
        }
    };

    return (
        <div>
            <h1>{userProfile?.username}'s Profile</h1>
            {isEditing ? (
                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Email </label>
                        <input
                            name="email"
                            type="email"
                            value={editableUser.email}
                            onChange={handleChange}
                        />
                    </div>
                    <div>
                        <label>Address </label>
                        <input
                            name="address"
                            value={editableUser.address}
                            onChange={handleChange}
                        />
                    </div>
                    <button type="submit">Save Changes</button>
                    <button onClick={() => setIsEditing(false)}>Cancel</button>
                </form>
            ) : (
                <div>
                    <p>Email: {userProfile?.email}</p>
                    <p>Address: {userProfile?.address}</p>
                    <button onClick={() => setIsEditing(true)}>Edit Profile</button>
                </div>
            )}
        </div>
    );
};

export default UserProfilePage;
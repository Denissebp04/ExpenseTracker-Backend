import React, { useState } from 'react';
import axios from 'axios'; // Import axios directly for testing

const Login = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleRegister = async (e) => {
        e.preventDefault();
        setError('');
        
        const userData = {
            username,
            email,
            password
        };
        
        console.log('Sending registration data:', userData);
        
        try {
            const response = await axios.post('http://localhost:8080/api/user/register', userData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            
            console.log('Registration successful:', response.data);
            // Handle success (e.g., redirect or show success message)
            
        } catch (error) {
            console.error('Full error object:', error);
            setError(error.response?.data || 'Registration failed. Please try again.');
        }
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        setError('');
        
        // Debug log to check what's being sent
        console.log('Sending login data:', { username, password });
        
        try {
            const response = await axios.post('http://localhost:8080/api/user/login', {
                username: username,  // Make sure these match your LoginRequest.java fields
                password: password
            }, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            
            console.log('Login successful:', response.data);
            localStorage.setItem('user', JSON.stringify(response.data));
            // Handle successful login (redirect, etc.)
            
        } catch (error) {
            console.error('Login error details:', error.response?.data);
            setError(error.response?.data?.message || 'Login failed');
        }
    };

    return (
        <form onSubmit={handleRegister}>
            {error && <div style={{color: 'red'}}>{error}</div>}
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <button type="submit">Register</button>
        </form>
    );
};

export default Login; 
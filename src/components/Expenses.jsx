import React, { useState, useEffect } from "react";
import "./Expenses.css";
import axios from "axios"; // Make sure to install axios: npm install axios

function Expenses({ onBack }) {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [currentExpense, setCurrentExpense] = useState({
        id: null,
        title: "",
        amount: "",
        category: "OTHERS", // Add default category
        date: new Date().toISOString().split('T')[0], // Add default date
        userId: 1  // Add this - use the actual logged-in user's ID
    });

    const [expenses, setExpenses] = useState([]);
    
    // Fetch expenses when component mounts
    useEffect(() => {
        console.log('Current userId:', localStorage.getItem('userId'));
        fetchExpenses();
    }, []);

    const fetchExpenses = async () => {
        try {
            const userId = localStorage.getItem('userId');
            if (!userId) {
                console.error('No user ID found in localStorage');
                return;
            }
            console.log('Fetching expenses for user:', userId);

            const response = await axios.get(
                `http://localhost:8080/api/expenses/user/${userId}`,
                getAuthHeader()
            );
            console.log('Fetched expenses:', response.data);
            setExpenses(response.data);
        } catch (error) {
            console.error('Error details:', {
                message: error.message,
                response: error.response?.data,
                status: error.response?.status
            });
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const userId = localStorage.getItem('userId');
        console.log('Submitting with userId:', userId);

        if (!userId) {
            alert('Please log in first');
            return;
        }

        console.log('Form data:', {
            title: currentExpense.title,
            amount: currentExpense.amount,
            category: currentExpense.category,
            date: currentExpense.date,
            userId: userId
        });

        try {
            const expenseData = {
                ...currentExpense,
                userId: parseInt(userId),
                amount: parseFloat(currentExpense.amount)
            };

            console.log('Submitting expense data:', expenseData);

            if (currentExpense.id) {
                const response = await axios.put(
                    `http://localhost:8080/api/expenses/${currentExpense.id}`,
                    expenseData,
                    getAuthHeader()
                );
                console.log('Update response:', response.data);
            } else {
                const response = await axios.post(
                    'http://localhost:8080/api/expenses',
                    expenseData,
                    getAuthHeader()
                );
                console.log('Create response:', response.data);
            }

            fetchExpenses();
            setIsModalOpen(false);
        } catch (error) {
            console.error('Error saving expense:', error.response?.data || error.message);
            alert('Error saving expense. Please check console for details.');
        }
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/api/expenses/${id}`);
            fetchExpenses();
        } catch (error) {
            console.error('Error deleting expense:', error);
            alert('Error deleting expense. Please try again.');
        }
    };

    return (
        <div className="expenses-container">
            {/* ... existing header JSX ... */}

            <div className="expenses-list">
                {expenses.map(expense => (
                    <div key={expense.id} className="expense-card">
                        <div className="expense-info">
                            <div className="expense-icon">
                                {expense.amount > 0 ? '💵' : '🛍️'}
                            </div>
                            <div className="expense-details">
                                <h3>{expense.title}</h3>
                                <span className="expense-date">
                                    {new Date(expense.date).toLocaleDateString()}
                                </span>
                                <span className="expense-category">{expense.category}</span>
                            </div>
                        </div>
                        <div className="expense-actions">
                            <span className={`expense-amount ${expense.amount > 0 ? 'positive' : 'negative'}`}>
                                ${Math.abs(expense.amount).toFixed(2)}
                            </span>
                            <button 
                                className="edit-button"
                                onClick={() => {
                                    setCurrentExpense(expense);
                                    setIsModalOpen(true);
                                }}
                            >
                                Edit
                            </button>
                            <button 
                                className="delete-button"
                                onClick={() => handleDelete(expense.id)}
                            >
                                Delete
                            </button>
                        </div>
                    </div>
                ))}
            </div>

            {isModalOpen && (
                <div className="modal-overlay">
                    <div className="modal">
                        <div className="modal-header">
                            <h2>Manage Expenses</h2>
                            <button 
                                className="close-button"
                                onClick={() => {
                                    setIsModalOpen(false);
                                    setCurrentExpense({ 
                                        id: null, 
                                        title: "", 
                                        amount: "",
                                        category: "OTHERS",
                                        date: new Date().toISOString().split('T')[0],
                                        userId: 1
                                    });
                                }}
                            >
                                ×
                            </button>
                        </div>
                        <form onSubmit={handleSubmit}>
                            <input
                                type="text"
                                placeholder="Transaction Name"
                                value={currentExpense.title}
                                onChange={(e) => setCurrentExpense({...currentExpense, title: e.target.value})}
                                required
                            />
                            <input
                                type="number"
                                placeholder="Amount"
                                value={currentExpense.amount}
                                onChange={(e) => setCurrentExpense({
                                    ...currentExpense,
                                    amount: parseFloat(e.target.value)
                                })}
                                required
                            />
                            <select
                                value={currentExpense.category}
                                onChange={(e) => setCurrentExpense({...currentExpense, category: e.target.value})}
                                required
                            >
                                <option value="">Select category</option>
                                <option value="FOOD">Food</option>
                                <option value="TRANSPORT">Transport</option>
                                <option value="SHOPPING">Shopping</option>
                                <option value="HOUSING">Housing</option>
                                <option value="UTILITIES">Utilities</option>
                                <option value="ENTERTAINMENT">Entertainment</option>
                                <option value="HEALTHCARE">Healthcare</option>
                                <option value="OTHER">Other</option>
                            </select>
                            <input
                                type="date"
                                value={currentExpense.date}
                                onChange={(e) => setCurrentExpense({
                                    ...currentExpense,
                                    date: e.target.value
                                })}
                                required
                            />
                            <div className="modal-buttons">
                                <button type="button" className="cancel-button" onClick={() => setIsModalOpen(false)}>
                                    Cancel
                                </button>
                                <button type="submit" className="save-button">
                                    {currentExpense.id ? 'Save' : 'Add'}
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Expenses; 
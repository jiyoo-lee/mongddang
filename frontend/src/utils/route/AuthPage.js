import React from 'react';
import { Navigate } from 'react-router-dom';

const AuthPage = ({ children }) => {
  return sessionStorage.getItem("token") ? children : <Navigate to='/login' />;
};

export default AuthPage;
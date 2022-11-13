import React from 'react';
import { Navigate } from 'react-router-dom';

const NonAuthPage = ({ children }) => {
  return sessionStorage.getItem("token") ? <Navigate to='/home/feed' /> : children;
};

export default NonAuthPage;
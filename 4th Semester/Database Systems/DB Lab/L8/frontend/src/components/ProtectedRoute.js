import { Navigate } from 'react-router-dom';

function ProtectedRoute({ isAuthenticated, children }) {
  if (!isAuthenticated) {
    return <Navigate to="/signin" replace />;
  }
  return children;
}

export default ProtectedRoute;
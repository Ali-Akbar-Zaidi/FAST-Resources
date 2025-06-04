import { useState } from 'react';

function SignUp() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`Signed up with Email: ${email}`);  // Use backticks (`) for template literals
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Sign Up</h2>
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}  // Fix: Use curly braces `{ }`
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}  // Fix: Use curly braces `{ }`
      />
      <button type="submit">Sign Up</button>
    </form>
  );
}

export default SignUp;  // Don’t forget to export!
import React, { useState, useEffect } from 'react';
function App() {
const [message, setMessage] = useState('');
useEffect(() => { 
  fetch('http://localhost:5000')
.then((response) => response.text()) 
.then((data) => setMessage(data));
}, []);
return (
<div style={{ textAlign: 'center', marginTop: '50px' }}> 
<h1>Name: Syed Hadi Ali Akbar Zaidi</h1>
<p>{message}</p>
<p>I was born in Karachi and have lived most of my life in punjab and did my O and A levels from FFC Grammar School & College.</p>
<img src="23l3023.jpg" alt="W3Schools.com" width="200" height="200"></img>
<h2>Life Goals</h2>
<p>Master software engineering and technology</p>
<p> Contribute to open-source projects</p>
<p>Travel the world and experience different cultures</p>
<p> Give back to the community through teaching</p>
</div>

);
}
export default App;
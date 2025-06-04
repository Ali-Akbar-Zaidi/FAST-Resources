import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';

const SignIn = ({ setCurrentHero }) => {
  const [formData, setFormData] = useState({
    alias: '',
    password: ''
  });

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    // In real app, verify credentials here
    const heroData = JSON.parse(localStorage.getItem('currentHero'));
    if (heroData) {
      setCurrentHero(heroData);
      navigate('/');
    }
  };

  return (
    <div className="min-h-screen bg-[url('/hero-bg.jpg')] bg-cover bg-center flex items-center justify-center p-4">
      <motion.div 
        initial={{ opacity: 0, scale: 0.9 }}
        animate={{ opacity: 1, scale: 1 }}
        className="w-full max-w-md bg-black/70 backdrop-blur-sm rounded-xl shadow-2xl overflow-hidden border border-yellow-400/30"
      >
        <div className="p-8">
          <div className="text-center mb-8">
            <div className="w-20 h-20 bg-yellow-400 rounded-full flex items-center justify-center mx-auto mb-4">
              <svg className="w-10 h-10 text-black" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 11c0 3.517-1.009 6.799-2.753 9.571m-3.44-2.04l.054-.09A13.916 13.916 0 008 11a4 4 0 118 0c0 1.017-.07 2.019-.203 3m-2.118 6.844A21.88 21.88 0 0015.171 17m3.839 1.132c.645-2.266.99-4.659.99-7.132A8 8 0 008 4.07M3 15.364c.64-1.319 1-2.8 1-4.364 0-1.457.39-2.823 1.07-4" />
              </svg>
            </div>
            <h2 className="text-3xl font-bold text-yellow-400">Hero Access</h2>
            <p className="text-white/70 mt-2">Prove your identity</p>
          </div>

          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label className="block text-sm font-medium text-white/80 mb-1">Hero Alias</label>
              <input
                type="text"
                className="w-full px-4 py-3 bg-white/5 border border-white/10 rounded-lg text-white placeholder-white/30 focus:outline-none focus:ring-2 focus:ring-yellow-400 transition"
                placeholder="Enter your hero name"
                value={formData.alias}
                onChange={(e) => setFormData({...formData, alias: e.target.value})}
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-white/80 mb-1">Secret Code</label>
              <input
                type="password"
                className="w-full px-4 py-3 bg-white/5 border border-white/10 rounded-lg text-white placeholder-white/30 focus:outline-none focus:ring-2 focus:ring-yellow-400 transition"
                placeholder="••••••••"
                value={formData.password}
                onChange={(e) => setFormData({...formData, password: e.target.value})}
                required
              />
            </div>

            <motion.button
              whileHover={{ scale: 1.02 }}
              whileTap={{ scale: 0.98 }}
              type="submit"
              className="w-full bg-gradient-to-r from-yellow-500 to-yellow-600 text-black font-bold py-3 px-4 rounded-lg shadow-lg hover:shadow-yellow-500/40 transition-all"
            >
              Authenticate
            </motion.button>
          </form>
        </div>
      </motion.div>
    </div>
  );
};

export default SignIn;
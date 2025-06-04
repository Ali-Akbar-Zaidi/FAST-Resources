import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';
import PowerSelector from '../components/PowerSelector';
import AllianceFlag from '../components/AllianceFlag';

const SignUp = ({ setCurrentHero }) => {
  const [formData, setFormData] = useState({
    alias: '',
    password: '',
    power: 'telepathy',
    alliance: 'justice'
  });

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    const newHero = {
      ...formData,
      id: Date.now(),
      team: ['Captain Pakistan', 'Miss Marvel PK', 'Shaheen'],
      avatar: `https://api.dicebear.com/7.x/avataaars/svg?seed=${formData.alias}`
    };
    localStorage.setItem('currentHero', JSON.stringify(newHero));
    setCurrentHero(newHero);
    navigate('/');
  };

  return (
    <motion.div 
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      className="min-h-screen bg-gradient-to-b from-blue-900 to-purple-900 flex items-center justify-center p-4"
    >
      <motion.div 
        initial={{ y: -50 }}
        animate={{ y: 0 }}
        className="w-full max-w-md bg-white/10 backdrop-blur-lg rounded-2xl shadow-xl overflow-hidden border border-white/20"
      >
        <div className="p-8">
          <div className="text-center mb-8">
            <img 
              src="/logo-jlp.png" 
              alt="Justice League Pakistan" 
              className="w-32 mx-auto mb-4"
            />
            <h2 className="text-3xl font-bold text-white">Hero Registration</h2>
            <p className="text-white/80 mt-2">Join our elite alliance</p>
          </div>

          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label className="block text-sm font-medium text-white/80 mb-1">Hero Alias</label>
              <input
                type="text"
                className="w-full px-4 py-3 bg-white/10 border border-white/20 rounded-lg text-white placeholder-white/50 focus:outline-none focus:ring-2 focus:ring-yellow-400 transition"
                placeholder="e.g. Shaheen"
                value={formData.alias}
                onChange={(e) => setFormData({...formData, alias: e.target.value})}
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-white/80 mb-1">Secret Code</label>
              <input
                type="password"
                className="w-full px-4 py-3 bg-white/10 border border-white/20 rounded-lg text-white placeholder-white/50 focus:outline-none focus:ring-2 focus:ring-yellow-400 transition"
                placeholder="••••••••"
                value={formData.password}
                onChange={(e) => setFormData({...formData, password: e.target.value})}
                required
              />
            </div>

            <PowerSelector 
              selectedPower={formData.power}
              onSelect={(power) => setFormData({...formData, power})}
            />

            <AllianceFlag 
              alliance={formData.alliance}
              onChange={(alliance) => setFormData({...formData, alliance})}
            />

            <motion.button
              whileHover={{ scale: 1.03 }}
              whileTap={{ scale: 0.98 }}
              type="submit"
              className="w-full bg-gradient-to-r from-yellow-400 to-yellow-600 text-black font-bold py-3 px-4 rounded-lg shadow-lg hover:shadow-yellow-500/30 transition-all"
            >
              Activate Hero Profile
            </motion.button>
          </form>
        </div>
      </motion.div>
    </motion.div>
  );
};

export default SignUp;
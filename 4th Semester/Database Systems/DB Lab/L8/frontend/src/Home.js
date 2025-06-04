import { motion } from 'framer-motion';
import HeroProfileCard from '../components/HeroProfileCard';
import TeamMembers from '../components/TeamMembers';
import PowerDisplay from '../components/PowerDisplay';

const Home = ({ currentHero }) => {
  return (
    <div className="min-h-screen bg-gradient-to-br from-gray-900 to-blue-900 text-white">
      {/* Header */}
      <header className="bg-black/40 backdrop-blur-md border-b border-white/10">
        <div className="container mx-auto px-6 py-4 flex justify-between items-center">
          <div className="flex items-center space-x-2">
            <img src="/logo-jlp-sm.png" alt="JLP" className="h-10" />
            <span className="text-xl font-bold bg-gradient-to-r from-yellow-400 to-yellow-600 bg-clip-text text-transparent">
              Justice League Pakistan
            </span>
          </div>
          <button className="px-4 py-2 bg-red-600/90 hover:bg-red-700 rounded-lg text-sm font-medium transition">
            Emergency Signal
          </button>
        </div>
      </header>

      <main className="container mx-auto px-4 py-8">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.2 }}
          className="grid grid-cols-1 lg:grid-cols-3 gap-8"
        >
          {/* Hero Profile */}
          <div className="lg:col-span-1">
            <HeroProfileCard hero={currentHero} />
          </div>

          {/* Main Content */}
          <div className="lg:col-span-2 space-y-8">
            {/* Alliance Info */}
            <motion.div 
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              transition={{ delay: 0.4 }}
              className="bg-white/5 backdrop-blur-sm rounded-xl border border-white/10 p-6"
            >
              <h2 className="text-2xl font-bold mb-4 flex items-center">
                <svg className="w-6 h-6 mr-2 text-yellow-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z" />
                </svg>
                Alliance Information
              </h2>
              <div className="space-y-4">
                <div className="flex items-center space-x-4">
                  <div className="flex-shrink-0">
                    <img 
                      src={currentHero.alliance === 'justice' ? '/justice-badge.png' : '/villain-badge.png'} 
                      alt="Alliance Badge" 
                      className="h-16 w-16"
                    />
                  </div>
                  <div>
                    <h3 className="text-xl font-semibold">
                      {currentHero.alliance === 'justice' 
                        ? 'Justice League Pakistan' 
                        : 'Secret Society'}
                    </h3>
                    <p className="text-white/80">
                      {currentHero.alliance === 'justice'
                        ? 'Defenders of peace and justice across Pakistan'
                        : 'Working in the shadows to reshape society'}
                    </p>
                  </div>
                </div>
              </div>
            </motion.div>

            {/* Superpowers Section */}
            <PowerDisplay powers={[currentHero.power]} />

            {/* Team Members */}
            <TeamMembers members={currentHero.team} />
          </div>
        </motion.div>
      </main>
    </div>
  );
};

export default Home;
const PowerSelector = ({ selectedPower, onSelect }) => {
    const powers = [
      { id: 'telepathy', name: 'Telepathy', icon: '🧠', color: 'bg-purple-500' },
      { id: 'strength', name: 'Super Strength', icon: '💪', color: 'bg-red-500' },
      { id: 'speed', name: 'Super Speed', icon: '⚡', color: 'bg-blue-500' },
      { id: 'flight', name: 'Flight', icon: '🦅', color: 'bg-sky-500' },
      { id: 'tech', name: 'Tech Mastery', icon: '🤖', color: 'bg-emerald-500' },
    ];
  
    return (
      <div>
        <label className="block text-sm font-medium text-white/80 mb-2">Primary Superpower</label>
        <div className="grid grid-cols-3 gap-3">
          {powers.map((power) => (
            <button
              key={power.id}
              type="button"
              onClick={() => onSelect(power.id)}
              className={`flex flex-col items-center justify-center p-3 rounded-lg border-2 transition-all ${
                selectedPower === power.id
                  ? `${power.color} border-yellow-400 scale-105`
                  : 'bg-white/5 border-white/10 hover:bg-white/10'
              }`}
            >
              <span className="text-2xl mb-1">{power.icon}</span>
              <span className="text-xs font-medium">{power.name}</span>
            </button>
          ))}
        </div>
      </div>
    );
  };
  
  export default PowerSelector;
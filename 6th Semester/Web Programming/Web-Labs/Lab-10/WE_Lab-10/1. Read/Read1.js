const fs = require('fs');

fs.readFile('index.html', 'utf8', (err, data) => {
  if (err) {
    console.error('Error reading file:', err);
    return;
  }

  console.log(data);
});

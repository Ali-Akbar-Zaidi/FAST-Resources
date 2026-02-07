const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function updateHTMLFile(keyword, replacement) {
    fs.readFile('about.html', 'utf8', (err, data) => {
        if (err) {
            console.error('Error reading file:', err);
            rl.close();
            return;
        }

        const updatedContent = data.replace(new RegExp(keyword, 'g'), replacement);

        fs.writeFile('about.html', updatedContent, (err) => {
            if (err) {
                console.error('Error writing file:', err);
                rl.close();
                return;
            }
            console.log('HTML file updated successfully!');
            rl.close();
        });
    });
}

rl.question('Enter the keyword to replace: ', (keyword) => {
    rl.question('Enter the replacement string: ', (replacement) => {
        updateHTMLFile(keyword, replacement);
    });
});

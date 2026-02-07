const fs = require('fs');
const path = require('path');
const cheerio = require('cheerio');

const directoryPath = 'html_files';

fs.readdir(directoryPath, (err, files) => {
    if (err) {
        console.error('Error reading directory:', err);
        return;
    }

    const htmlFiles = files.filter(file => path.extname(file) === '.html');

    htmlFiles.forEach(file => {
        const filePath = path.join(directoryPath, file);

        fs.readFile(filePath, 'utf8', (err, data) => {
            if (err) {
                console.error('Error reading file:', err);
                return;
            }

            const $ = cheerio.load(data);
            const imgCount = $('img').length;

            console.log(`${file}: ${imgCount} <img> tags`);
        });
    });
});

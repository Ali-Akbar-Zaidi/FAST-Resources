const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function generateHTML(title, content) {
    return `
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
  </head>
  <body>
    <h1>${title}</h1>
    <p>${content}</p>
  </body>
  </html>
  `;
}

function createHTMLFile(filename, content) {
    fs.writeFile(filename, content, (err) => {
        if (err) {
            console.error('Error creating HTML file:', err);
            return;
        }
        console.log(`HTML file "${filename}" created successfully!`);
    });
}

rl.question('Enter the number of HTML files to generate: ', (numFiles) => {
    numFiles = parseInt(numFiles);

    if (isNaN(numFiles) || numFiles <= 0) {
        console.error('Invalid input. Please enter a positive integer.');
        rl.close();
        return;
    }

    let filesGenerated = 0;

    function generateNextHTMLFile() {
        if (filesGenerated >= numFiles) {
            rl.close();
            return;
        }

        rl.question(`Enter the title for page ${filesGenerated + 1}: `, (title) => {
            rl.question(`Enter the content for page ${filesGenerated + 1}: `, (content) => {
                const filename = `page_${filesGenerated + 1}.html`;
                const htmlContent = generateHTML(title, content);
                createHTMLFile(filename, htmlContent);

                filesGenerated++;
                generateNextHTMLFile();
            });
        });
    }

    generateNextHTMLFile();
});

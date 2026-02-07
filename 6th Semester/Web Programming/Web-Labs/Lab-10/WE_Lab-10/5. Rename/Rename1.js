const fs = require('fs');

function renameHTMLFile() {
    fs.rename('old_page.html', 'new_page.html', (err) => {
        if (err) {
            console.error('Error renaming file:', err);
            return;
        }
        console.log('HTML file renamed successfully!');
    });
}

renameHTMLFile();

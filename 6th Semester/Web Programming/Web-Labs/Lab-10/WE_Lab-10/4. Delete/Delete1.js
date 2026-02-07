const fs = require('fs');

function deleteHTMLFile() {
    fs.unlink('page.html', (err) => {
        if (err) {
            console.error('Error deleting file:', err);
            return;
        }
        console.log('HTML file deleted successfully!');
    });
}

deleteHTMLFile();

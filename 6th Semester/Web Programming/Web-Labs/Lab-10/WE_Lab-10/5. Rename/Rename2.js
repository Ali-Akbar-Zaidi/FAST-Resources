const fs = require('fs');
const path = require('path');

function getCurrentTimestamp() {
    const now = new Date();
    const timestamp = `${now.getFullYear()}${(now.getMonth() + 1).toString().padStart(2, '0')}${now.getDate().toString().padStart(2, '0')}_${now.getHours().toString().padStart(2, '0')}${now.getMinutes().toString().padStart(2, '0')}${now.getSeconds().toString().padStart(2, '0')}`;
    return timestamp;
}

const directoryPath = 'html_backup';

fs.readdir(directoryPath, (err, files) => {
    if (err) {
        console.error('Error reading directory:', err);
        return;
    }

    files.forEach((file) => {
        const filePath = path.join(directoryPath, file);
        const fileExtension = path.extname(file);
        const fileNameWithoutExtension = path.basename(file, fileExtension);
        const timestamp = getCurrentTimestamp();
        const newFileName = `${fileNameWithoutExtension}_${timestamp}${fileExtension}`;
        const newFilePath = path.join(directoryPath, newFileName);

        fs.rename(filePath, newFilePath, (err) => {
            if (err) {
                console.error(`Error renaming file ${file}:`, err);
                return;
            }
            console.log(`File "${file}" renamed to "${newFileName}" successfully!`);
        });
    });
});

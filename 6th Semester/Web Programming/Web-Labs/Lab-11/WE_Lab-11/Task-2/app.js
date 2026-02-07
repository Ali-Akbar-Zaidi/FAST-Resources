//To test the application, modify the following data and use postman for sending the request:-
// {
// "to": "recipient@example.com",
// "subject": "Test Email",
// "text": "This is a test email."
// }

const express = require('express');
const sendEmail = require('./emailSender');

const app = express();

app.use(express.json());

app.post('/send-email', async (req, res) => {
    const { to, subject, text } = req.body;
    if (!to || !subject || !text) {
        return res.status(400).send('Missing required fields');
    }
    try {
        await sendEmail(to, subject, text);
        res.status(200).send.json({
            msg: 'Email sent successfully',
        });
    } catch (error) {
        res.status(500).send('Error sending email');
    }
});

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
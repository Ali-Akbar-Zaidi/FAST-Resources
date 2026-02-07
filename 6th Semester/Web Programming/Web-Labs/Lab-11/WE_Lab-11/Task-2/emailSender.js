const nodemailer = require('nodemailer');

const transporter = nodemailer.createTransport({
    service: 'Gmail',
    auth: {
        user: 'abubakarshahid1309@gmail.com',
        pass: 'kaggvgypeicwmrsi'
    }
});

const sendEmail = async (to, subject, text) => {

    const mailOptions = {
        from: "abubakarshahid1309@gmail.com",
        to: to,
        subject: subject,
        text: text
    };

    try {
        const info = await transporter.sendMail(mailOptions);
        console.log('Email sent: ' + info.response);
    } catch (error) {
        console.error('Error sending email: ' + error.message);
    }
};

module.exports = sendEmail;

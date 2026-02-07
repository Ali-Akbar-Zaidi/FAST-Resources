const express = require('express');
const app = express();
const productsRouter = require('./router/productRouter');

app.use(express.json());

app.use('/products', productsRouter);

const PORT = 5000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
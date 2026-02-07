let products = [];
let currentId = 1;

const getProducts = (req, res) => {
    res.json(products);
};

const getProductById = (req, res) => {
    const productId = parseInt(req.params.id);
    const product = products.find(p => p.id === productId);
    if (product) {
        res.json(product);
    } else {
        res.status(404).send('Product not found');
    }
};

const createProduct = (req, res) => {
    const newProduct = { id: currentId++, ...req.body };
    products.push(newProduct);
    res.status(201).json(newProduct);
};

const updateProduct = (req, res) => {
    const productId = parseInt(req.params.id);
    const productIndex = products.findIndex(p => p.id === productId);
    if (productIndex !== -1) {
        products[productIndex] = { id: productId, ...req.body };
        res.json(products[productIndex]);
    } else {
        res.status(404).send('Product not found');
    }
};

const deleteProduct = (req, res) => {
    const productId = parseInt(req.params.id);
    const productIndex = products.findIndex(p => p.id === productId);
    if (productIndex !== -1) {
        products.splice(productIndex, 1);
        res.status(204).send();
    } else {
        res.status(404).send('Product not found');
    }
};

module.exports = { getProducts, getProductById, createProduct, updateProduct, deleteProduct }
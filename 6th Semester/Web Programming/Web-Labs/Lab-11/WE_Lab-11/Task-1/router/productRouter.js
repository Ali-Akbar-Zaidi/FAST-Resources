const express = require("express");
const router = express.Router();
const productController = require("../controller/productController");

router.route("/").get(productController.getProducts);
router.route("/createProduct").get(productController.createProduct);
router.route("/getProductById/:id").get(productController.getProductById);
router.route("/updateProduct/:id").get(productController.updateProduct);
router.route("/deleteProduct/:id").get(productController.deleteProduct);

module.exports = router;
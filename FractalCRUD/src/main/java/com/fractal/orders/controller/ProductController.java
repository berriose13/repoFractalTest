package com.fractal.orders.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fractal.orders.dto.ProductDTO;
import com.fractal.orders.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
  private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping("/add")
  public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDto) {
    logger.info("START  [POST] /create-product: {}", productDto.toString());
    HttpStatus status = null;
    ProductDTO productResponse = null;
    try {
      productResponse = productService.createProduct(productDto);
      status = HttpStatus.OK;
    } catch (Exception e) {
      productResponse = null;
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      logger.error("Exception ocurred in create product {}  ", e.getMessage());

    }
    logger.info("END  [POST] /create-product: {}", productDto.toString());

    return new ResponseEntity<>(productResponse, status);
  }

  @PatchMapping
  public ResponseEntity<ProductDTO> editProduct(@RequestBody ProductDTO productDto) {
    logger.info("START  [POST] /edit-product: {}", productDto.toString());

    HttpStatus status = null;
    try {
      productService.editProduct(productDto);
      status = HttpStatus.OK;
    } catch (Exception e) {
      productDto = null;
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      logger.error("Exception ocurred in edit product {}", e.getMessage());

    }
    logger.info("END  [POST] /edit-product: {}", productDto.toString());
    return new ResponseEntity<ProductDTO>(productDto, status);
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
    logger.info("START  [DELETE] /delete-product {}", productId);
    HttpStatus status = null;
    try {
      productService.deleteProduct(Long.valueOf(productId));
      status = HttpStatus.OK;
    } catch (Exception e) {
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      logger.error("Exception ocurred in delete product {}", e.getMessage());

    }
    logger.info("END  [DELETE] /delete-product ");
    return new ResponseEntity<>(status);
  }

  @GetMapping
  public List<ProductDTO> listAllProducts() {
    logger.info("START  [GET] /list-all-products");

    List<ProductDTO> products = productService.getAllProducts();
    logger.info("END  [GET] /list-all-orders: {}", products.toString());
    return products;
  }

}

package com.hemanttechie.facade.impl;

import com.hemanttechie.dto.ProductDetailDTO;
import com.hemanttechie.entity.Inventory;
import com.hemanttechie.entity.Price;
import com.hemanttechie.entity.Product;
import com.hemanttechie.facade.ProductASyncFacade;
import com.hemanttechie.service.InventoryService;
import com.hemanttechie.service.PriceService;
import com.hemanttechie.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductASyncFacadeImpl implements ProductASyncFacade {

    private final ProductService productService;

    private final InventoryService inventoryService;

    private final PriceService priceService;


  /*  public ProductDetailDTO getProductDetails(long productId) {

        //fetch all async
        CompletableFuture<Product> productFuture = getProductById(productId);
        CompletableFuture<Price> priceFuture = getPriceByProductById(productId);
        CompletableFuture<Inventory> inventoryFuture = getInventoryByProductId(productId);

        //wait for all futures to complete
        CompletableFuture.allOf(priceFuture, productFuture, inventoryFuture);

        //combine the result
        Product product = productFuture.join();
        Price price = priceFuture.join();
        Inventory inventory = inventoryFuture.join();

        //build and return

        return new ProductDetailDTO(productId, product.getCategory().getName(),
                product.getName(), product.getDescription(),
                inventory.getAvailableQuantity(), price.getPrice(),
                inventory.getStatus());
    }*/

    public ProductDetailDTO getProductDetails(long productId) {
        // Fetch all async and combine the results without blocking
        CompletableFuture<Product> productFuture = getProductById(productId);
        CompletableFuture<Price> priceFuture = getPriceByProductById(productId);
        CompletableFuture<Inventory> inventoryFuture = getInventoryByProductId(productId);

        return CompletableFuture.allOf(productFuture, priceFuture, inventoryFuture)
                .thenApply(voidResult -> {
                    Product product = productFuture.join();
                    Price price = priceFuture.join();
                    Inventory inventory = inventoryFuture.join();

                    // Build and return ProductDetailDTO
                    return new ProductDetailDTO(
                            productId,
                            product.getCategory().getName(),
                            product.getName(),
                            product.getDescription(),
                            inventory.getAvailableQuantity(),
                            price.getPrice(),
                            inventory.getStatus()
                    );
                }).join();
    }

    private CompletableFuture<Product> getProductById(long productId) {
        return CompletableFuture
                .supplyAsync(() -> productService.findById(productId));
    }

    private CompletableFuture<Price> getPriceByProductById(long productId) {
        return CompletableFuture
                .supplyAsync(() -> priceService.getPriceByProductId(productId));
    }

    private CompletableFuture<Inventory> getInventoryByProductId(long productId) {
        return CompletableFuture
                .supplyAsync(() -> inventoryService.getInventoryByProductId(productId));
    }

}

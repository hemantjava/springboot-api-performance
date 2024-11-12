package com.hemanttechie.facade;


import com.hemanttechie.dto.ProductDetailDTO;
import com.hemanttechie.entity.Inventory;
import com.hemanttechie.entity.Price;
import com.hemanttechie.entity.Product;
import com.hemanttechie.service.InventoryService;
import com.hemanttechie.service.PriceService;
import com.hemanttechie.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductSyncFacade {

    private  ProductService productService;
    private  InventoryService inventoryService;
    private  PriceService priceService;

    public ProductSyncFacade(ProductService productService, InventoryService inventoryService, PriceService priceService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.priceService = priceService;
    }

    public ProductDetailDTO getProductDetails(long productId) {
        log.info("Sync facade for getting product details for the product id {}",productId);

        //fetch product
        Product product = productService.findById(productId);

        //fetch the price
        Price price = priceService.getPriceByProductId(productId);

        //fetch the inventory
        Inventory inventory = inventoryService.getInventoryByProductId(productId);

        //combine result
        return new ProductDetailDTO(productId, product.getCategory().getName(),
                product.getName(), product.getDescription(),
                inventory.getAvailableQuantity(), price.getPrice(),
                inventory.getStatus());
    }

}

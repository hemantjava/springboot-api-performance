package com.hemanttechie.facade;


import com.hemanttechie.dto.ProductDetailDTO;
import com.hemanttechie.entity.Inventory;
import com.hemanttechie.entity.Price;
import com.hemanttechie.entity.Product;
import com.hemanttechie.service.InventoryService;
import com.hemanttechie.service.PriceService;
import com.hemanttechie.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSyncFacade {

    private final ProductService productService;
    private final InventoryService inventoryService;
    private final PriceService priceService;


    public ProductDetailDTO getProductDetails(long productId) {
        log.info("Sync facade for getting product details for the product id {}", productId);

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

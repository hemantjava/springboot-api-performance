package com.hemanttechie.facade;

import com.hemanttechie.dto.ProductDetailDTO;

public interface ProductSyncFacade {
    ProductDetailDTO getProductDetails(long productId);
}

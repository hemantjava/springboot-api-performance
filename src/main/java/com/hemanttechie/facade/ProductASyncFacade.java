package com.hemanttechie.facade;

import com.hemanttechie.dto.ProductDetailDTO;

public interface ProductASyncFacade {

    ProductDetailDTO getProductDetails(long productId);

}

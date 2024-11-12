package com.hemanttechie.service;

import com.hemanttechie.entity.Price;

public interface PriceService {
    Price getPriceByProductId(Long productId);
}

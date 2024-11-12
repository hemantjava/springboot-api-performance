package com.hemanttechie.service;

import com.hemanttechie.entity.Inventory;

public interface InventoryService {
    Inventory getInventoryByProductId(Long productId);
}

INSERT INTO `category` (`id`, `name`, `type`, `status`) VALUES
                                                            (1, 'Electronics', 'Goods', 'active'),
                                                            (2, 'Clothing', 'Goods', 'active'),
                                                            (3, 'Home Appliances', 'Goods', 'active');

-- Insert data into products table
INSERT INTO `products` (`id`, `category_id`, `name`, `description`, `status`) VALUES
                                                                                  (1, 1, 'Smartphone', 'Latest smartphone with high-speed performance', 'active'),
                                                                                  (2, 1, 'Laptop', '15-inch laptop with 8GB RAM, 256GB SSD', 'active'),
                                                                                  (3, 2, 'Jacket', 'Waterproof jacket for outdoor activities', 'active'),
                                                                                  (4, 3, 'Air Conditioner', '1.5 ton AC with energy-efficient cooling', 'active');

-- Insert data into price table
INSERT INTO `price` (`id`, `product_id`, `price`, `valid_from`, `valid_to`, `status`) VALUES
                                                                                          (1, 1, 699.99, NOW(), NOW(), 'active'),
                                                                                          (2, 2, 1299.99, NOW(), NOW(), 'active'),
                                                                                          (3, 3, 59.99, NOW(), NOW(), 'active'),
                                                                                          (4, 4, 299.99, NOW(), NOW(), 'active');

-- Insert data into inventory table
INSERT INTO `inventory` (`id`, `product_id`, `warehouse_id`, `available_quantity`, `reserved_quantity`, `status`) VALUES
                                                                                                                      (1, 1, 1, 100, 10, 'available'),
                                                                                                                      (2, 2, 1, 50, 5, 'available'),
                                                                                                                      (3, 3, 2, 200, 20, 'available'),
                                                                                                                      (4, 4, 3, 30, 2, 'available');
package com.ecommerce.ecommercebackend.components;

import com.ecommerce.ecommercebackend.models.Product;

public interface StockUpdateObserver {
    void onStockAvailable(Product product);
}

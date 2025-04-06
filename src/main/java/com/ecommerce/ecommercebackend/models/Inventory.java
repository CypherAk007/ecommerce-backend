package com.ecommerce.ecommercebackend.models;

import com.ecommerce.ecommercebackend.components.StockUpdateObserver;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Inventory extends BaseModel{
    @OneToOne
    private Product product;
    private Double quantity;//current stock based on product units
    @Transient
    private List<StockUpdateObserver> observers = new ArrayList<>();

    public void addObserver(StockUpdateObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(StockUpdateObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        if (this.quantity > 0) {
            for (StockUpdateObserver observer : this.observers) {
                observer.onStockAvailable(this.product);
            }
        }
        // Optionally, clear the observers after notification if they are one-time
//        observers.clear();
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
        if (this.quantity > 0 && !this.observers.isEmpty()) {
            System.out.println("Inside Update s tock inventory service setQuantity Observers!!");
            notifyObservers();
        }
    }


}

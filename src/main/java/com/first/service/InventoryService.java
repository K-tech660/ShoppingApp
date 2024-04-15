package com.first.Service;

import com.first.entity.Inventory;

public interface InventoryService {
    Inventory getInventory();
    void updateInventory(int orderedQuantity);
	boolean checkAvailability(int quantity);
    
    
}
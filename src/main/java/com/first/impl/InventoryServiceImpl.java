package com.first.impl;

import com.first.Service.InventoryService;
import com.first.entity.Inventory;

import com.first.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory getInventory() {
        return inventoryRepository.findById(1L).orElse(new Inventory());
    }

    @Override
    @Transactional
    public void updateInventory(int orderedQuantity) {
        try {
            Inventory inventory = getInventory();
            inventory.setOrdered(inventory.getOrdered() + orderedQuantity);
            inventory.setAvailable(inventory.getAvailable() - orderedQuantity);
            inventoryRepository.save(inventory);
        } catch (Exception ex) {
        
            throw new RuntimeException("Failed to update inventory: " + ex.getMessage());
        }
    }

    @Override
    public boolean checkAvailability(int quantity) {
        try {
            Inventory inventory = inventoryRepository.findById(1L).orElse(null);
            return inventory != null && inventory.getAvailable() >= quantity;
        } catch (Exception ex) {
           
            throw new RuntimeException("Failed to check inventory availability: " + ex.getMessage());
        }
    }
}
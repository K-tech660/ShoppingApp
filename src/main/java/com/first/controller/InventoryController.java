package com.first.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.first.Service.InventoryService;
import com.first.entity.Inventory;

@RestController
@RequestMapping("/api")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public ResponseEntity<Inventory> getInventory() {
        Inventory inventory;
        try {
            inventory = inventoryService.getInventory();
            return ResponseEntity.ok(inventory);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/availability")
    public ResponseEntity<Boolean> checkProductAvailability(@RequestParam int quantity) {
        try {
            boolean isAvailable = inventoryService.checkAvailability(quantity);
            return ResponseEntity.ok(isAvailable);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
}
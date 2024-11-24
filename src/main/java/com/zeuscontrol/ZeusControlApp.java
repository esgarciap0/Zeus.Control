package com.zeuscontrol;

import com.zeuscontrol.controller.InventoryController;
import com.zeuscontrol.repository.ProductRepository;
import com.zeuscontrol.service.ProductService;

public class ZeusControlApp {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        ProductService service = new ProductService(repository);
        InventoryController controller = new InventoryController(service);

        controller.displayMenu();
    }
}

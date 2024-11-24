package com.zeuscontrol.controller;

import com.zeuscontrol.model.Product;
import com.zeuscontrol.service.ProductService;

import java.util.Scanner;

public class InventoryController {
    private final ProductService productService;

    public InventoryController(ProductService productService) {
        this.productService = productService;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        do {
            try {
                System.out.println("\n--- Zeus Control: Inventory Management ---");
                System.out.println("1. Listar todos los productos");
                System.out.println("2. Añadir nuevo producto");
                System.out.println("3. Actualizar producto");
                System.out.println("4. Eliminar producto");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                // Captura la entrada como cadena y conviértela en entero
                String input = scanner.nextLine();
                option = Integer.parseInt(input);

                switch (option) {
                    case 1 -> listAllProducts();
                    case 2 -> addProduct(scanner);
                    case 3 -> updateProduct(scanner);
                    case 4 -> deleteProduct(scanner);
                    case 5 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        } while (option != 5);

        scanner.close();
    }


    private void listAllProducts() {
        productService.listAllProducts().forEach(System.out::println);
    }

    private void addProduct(Scanner scanner) {
        try {
            System.out.print("Ingrese el ID del producto: ");
            String id = scanner.nextLine();
            System.out.print("Ingrese el nombre del producto: ");
            String name = scanner.nextLine();
            System.out.print("Ingrese la categoría: ");
            String category = scanner.nextLine();
            System.out.print("Ingrese el precio: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Ingrese cantidad (por ejemplo, '5 gramos', '10 unidades'): ");
            String quantity = scanner.nextLine();

            productService.addProduct(new Product(id, name, category, price, quantity));
            System.out.println("¡Producto añadido exitosamente!");
        } catch (NumberFormatException e) {
            System.out.println("Error: El precio debe ser un número válido.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateProduct(Scanner scanner) {
        System.out.print("Ingrese el ID del product para actualizar: ");
        String id = scanner.next();
        System.out.print("Ingrese nuevo nombre: ");
        String name = scanner.next();
        System.out.print("Ingrese nueva categoria: ");
        String category = scanner.next();
        System.out.print("Ingrese nuevo precio: ");
        double price = scanner.nextDouble();
        System.out.print("Ingrese nueva cantidad: ");
        String quantity = String.valueOf(scanner.nextInt());

        try {
            productService.updateProduct(new Product(id, name, category, price, quantity));
            System.out.println("¡Producto actualizado exitosamente!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProduct(Scanner scanner) {
        System.out.print("Ingrese el ID del producto para eliminar: ");
        String id = scanner.next();

        try {
            productService.deleteProduct(id);
            System.out.println("¡Producto eliminado exitosamente!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

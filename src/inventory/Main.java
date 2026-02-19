package inventory;

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Product> products = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "products.txt";

    public static void main(String[] args) {
        loadFromFile();

        while (true) {
            System.out.println("\n--- Inventory System ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    saveToFile();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void addProduct() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        products.add(new Product(id, name, quantity, price));
        System.out.println("Product added.");
    }

    static void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        for (Product p : products) {
            System.out.println(p);
        }
    }

    static void searchProduct() {
        System.out.print("Enter ID to search: ");
        int id = scanner.nextInt();

        for (Product p : products) {
            if (p.getId() == id) {
                System.out.println(p);
                return;
            }
        }

        System.out.println("Product not found.");
    }

    static void deleteProduct() {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();

        boolean removed = products.removeIf(p -> p.getId() == id);

        if (removed) System.out.println("Product deleted.");
        else System.out.println("Product not found.");
    }

    static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Product p : products) {
                writer.println(p.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                products.add(Product.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading file.");
        }
    }
}

package inventory;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;

    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String toFileString() {
        return id + "," + name + "," + quantity + "," + price;
    }

    public static Product fromFileString(String line) {
        String[] parts = line.split(",");
        return new Product(
                Integer.parseInt(parts[0]),
                parts[1],
                Integer.parseInt(parts[2]),
                Double.parseDouble(parts[3])
        );
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name +
                " | Quantity: " + quantity +
                " | Price: $" + price;
    }
}

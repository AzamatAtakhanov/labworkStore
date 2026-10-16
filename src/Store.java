import java.util.List;
import java.util.Scanner;

public class Store {
    private List<Product> products;

    public Store(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public void addProduct(Product product) {
        this.products.add(product);
    }
    public void removeProduct(Product product) {
        this.products.remove(product);
    }
    public void chooseQuantity(Product product, int quantity) {

    }
    public void listProducts() {
        for (int i = 0; i<products.size(); i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.print(products.get(i).getName());
            System.out.print(" - ");
            System.out.println("$" +  products.get(i).getPrice());
        }
    }
    public int chooseQuantity(Product product, Scanner sc) {
        System.out.print("Choose quantity: ");
        int quantity = sc.nextInt();
        if (quantity == 0) {
            System.out.println("Sorry! You must choose at least 1 product.");
        } else if (quantity <= product.getQuantity()) {
            product.setQuantity(product.getQuantity() - quantity);
            return quantity;
        }else {
            System.out.println("Sorry! There are only " + product.getQuantity() + " of them in this store.");
        }
        return chooseQuantity(product, sc);
    }
    public float purchase(float finalTotal, Scanner sc) {
        System.out.print("Enter payment amount: $");
        float paymentAmount = sc.nextFloat();
        if (paymentAmount < finalTotal){
            System.out.println("Insufficient payment.");
            System.out.println("You still need to pay: $" + (finalTotal - paymentAmount));
            purchase(finalTotal -  paymentAmount, sc);
        }else  {
            System.out.println("Payment successful!");
            System.out.println("Change: $" + (paymentAmount - finalTotal));
        }
        return paymentAmount;
    }
    public float discount(float subTotal) {
        float discount = 0;
        if (subTotal >= 100) {
            discount = subTotal * 0.1f;
        }
        return discount;
    }

    public void receipt(int count, float subTotal, float discount, float paymentAmount) {
        System.out.println("====================");
        System.out.println("    SIMPLE STORE");
        System.out.println("====================");
        System.out.println();
        System.out.println("Items purchased: " + count);
        System.out.println("Subtotal: $" + subTotal);
        System.out.println("Discount: $" + discount);
        System.out.println("Final total: $" + (subTotal - discount));
        System.out.println("Payment: $" + paymentAmount);
        System.out.println("Change: $" + ((subTotal - discount) - paymentAmount));
        System.out.println();
        System.out.println("====================");
        System.out.println("Thank you for shopping!");
        System.out.println("====================");
    }

}

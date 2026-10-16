import java.util.ArrayList;
import java.util.Scanner;

public class StoreController {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        float subTotal = 0;
        int count = 0;
        // Creating products
        Store store = new Store(new ArrayList<>());
        store.addProduct(new Product("Mouse", 20, 5));
        store.addProduct(new Product("Keyboard", 40, 4));
        store.addProduct(new Product("Headphones", 50, 3));
        store.addProduct(new Product("Laptop Stand", 50, 2));

        while(true){
            int range =  store.getProducts().size();
            System.out.println("===== SIMPLE STORE =====");
            store.listProducts();
            System.out.println((range + 1) + ". Checkout");
            System.out.println("Current total: $" + subTotal);
            
            System.out.print("Choose a product: ");
            int choice = sc.nextInt();
            
            if (choice == range + 1) {
                if (subTotal == 0) {
                    System.out.println("Sorry! You need to add something to your cart.");
                    enterToContinue();
                    continue;
                }
                float discount = store.discount(subTotal);
                float finalTotal = subTotal - discount;
                
                System.out.println("===== CHECKOUT =====");
                System.out.println("Subtotal: $" + subTotal);
                System.out.println("Discount: $" + discount);
                System.out.println("Final total: $" + finalTotal);
                
                float paymentAmount = store.purchase(finalTotal, sc);
                store.receipt(count, subTotal, discount, paymentAmount);
                break;
            } else if (choice > 0 && choice <= range) {
                Product chosenProduct = store.getProducts().get(choice - 1);
                System.out.println("You selected: " + chosenProduct.getName());
                System.out.println("Price: $" +  chosenProduct.getPrice());

                int quantity = store.chooseQuantity(chosenProduct, sc);
                int totalPrice =  chosenProduct.getPrice() * quantity;
                subTotal += totalPrice;
                count += quantity;

                if(chosenProduct.getQuantity() == 0){
                    store.removeProduct(chosenProduct);
                }

                System.out.println("Added to cart!");
                System.out.println("Current total: $" + subTotal);
                sc.nextLine();
                enterToContinue();
            }
            else{
                System.out.println("Invalid option");
                System.out.println("Please choose a number between 1 to " + (store.getProducts().size() + 1));
                enterToContinue();
            }

        }
        sc.close();
    }
    public static void enterToContinue(){
        System.out.print("Press enter to continue...");
        sc.nextLine();
    }
}

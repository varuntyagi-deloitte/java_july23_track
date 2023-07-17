import java.util.*;

class Product {
    private String productName;
    private String productDescription;
    private double productPrice;
    private String sellerName;

    public Product(String name, String desc, double price, String seller_name) {
        this.productName = name;
        this.productDescription = desc;
        this.productPrice = price;
        this.sellerName = seller_name;
    }
    public void viewProduct(){
        System.out.println("The product "+this.productName+" is "+this.productDescription+". It's price is "+this.productPrice+".Seller "+this.sellerName+" is selling this.");
    }
}

class User {
    private String userName;
    private String userEmail;

    //    public User(String name, String email) {
//        this.userName = name;
//        this.userEmail = email;
//    }
    public void setName(String name) {
        this.userName = name;
    }

    public void setEmail(String email) {
        this.userEmail = email;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }
}

class Seller extends User {
    List<Product> productsSelling = new ArrayList<>();

    public void addProduct(Product product) {
        productsSelling.add(product);
    }

    public void removeProduct(Product product) {
        productsSelling.remove(product);
    }

    public void viewProducts() {
        for (Product product : productsSelling) {
            product.viewProduct();
        }
    }
}

class Order {
    List<Product> productOrdered = new ArrayList<>();
    private String customerName;
    private String dateOfOrderPlaced;

    public void getTotal() {

    }

    public String toString() {
        return "pass";
    }

}

class Customer extends User {
    //List<Order> ordersPlaced = new ArrayList<>();
    Dictionary<Product, Integer> ordersPlaced = new Hashtable<>();

    public void addToCart(Product product, int quantity) {
        ordersPlaced.put(product, quantity);
    }

    public void removeFromCart(Product product, int quantity) {
        int quantity_before = ordersPlaced.get(product);
        int new_quantity = quantity_before - quantity;
        ordersPlaced.put(product, new_quantity);
    }

    public void viewCart() {
        System.out.println("The products in the cart are: ");
        for (Enumeration k = ordersPlaced.keys(); k.hasMoreElements(); ) {
            System.out.println(k.nextElement());
        }
    }

    public void placeOrder() {

    }
}

class ShoppingCart {
    Dictionary<Product, Integer> productInCart = new Hashtable<>();

    //List<Product> productInCart = new ArrayList<>();
    public void addProduct(Product product, int quantity) {
        productInCart.put(product, quantity);
    }

    public void removeProduct(Product product, int quantity) {
        int quantity_before = productInCart.get(product);
        int new_quantity = quantity_before - quantity;
        productInCart.put(product, new_quantity);
    }

    public void viewProducts() {
        System.out.println("The products in the cart are: ");
        for (Enumeration k = productInCart.keys(); k.hasMoreElements(); ) {
            System.out.println(k.nextElement());
        }
    }
}

public class OnlineMarketPlace {
    public static void main(String a[]) {
        Scanner sc = new Scanner(System.in);
        User user1 = new User();
        System.out.println("Enter name of the user: ");
        String user_name = sc.nextLine();
        user1.setName(user_name);
        System.out.println("Enter email of the user: ");
        String user_email = sc.nextLine();
        user1.setEmail(user_email);
        Product p1 = new Product("Product 1","Clothing",123.45,"Seller1");
        Product p2 = new Product("Product 2","Clothing",132.45,"Seller2");
        Product p3 = new Product("Product 3","Clothing",12.45,"Seller3");
        Product p4 = new Product("Product 4","Clothing",13.45,"Seller1");
        Seller seller1 = new Seller();
        seller1.addProduct(p1);
        seller1.addProduct(p4);
        Seller seller2 = new Seller();
        seller2.addProduct(p2);
        Seller seller3 = new Seller();
        seller3.addProduct(p3);
        seller1.removeProduct(p1);
        seller1.viewProducts();
        seller2.viewProducts();
        seller3.viewProducts();
        Customer customer = new Customer();
        customer.addToCart(p2,5);
        customer.addToCart(p3,3);
        customer.removeFromCart(p2,3);
        customer.viewCart();
    }
}

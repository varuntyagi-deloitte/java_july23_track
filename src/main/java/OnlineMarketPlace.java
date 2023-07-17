import java.util.*;

class Product {
    private String productName;
    private String productDescription;
    private double productPrice;
    private Seller sellerName;

    public Product(String name, String desc, double price, Seller seller_name) {
        this.productName = name;
        this.productDescription = desc;
        this.productPrice = price;
        this.sellerName = seller_name;
    }
    public String getProductName(){
        return this.productName;
    }
    public double getProductPrice(){
        return this.productPrice;
    }

    public String getProductDesc(){
        return this.productDescription;
    }
    public Seller getSellerName(){
        return this.sellerName;
    }
}


class User {
    private String userName;
    private String userEmail;

        public User(String name, String email) {
        this.userName = name;
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
    List<Product> productsSelling;
    public Seller(String username,String email){
        super(username,email);
        this.productsSelling = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productsSelling.add(product);
    }

    public void removeProduct(Product product) {
        productsSelling.remove(product);
    }

    public void viewProducts() {
        for (Product product : productsSelling) {
            System.out.println(product.getProductName());
        }
    }
}

class Customer extends User {
    private List<Order> ordersPlaced;
    private ShoppingCart cart;

    public Customer(String username,String email){
        super(username,email);
        this.ordersPlaced = new ArrayList<>();
        this.cart = new ShoppingCart();
    }

    public void addToCart(Product product, int quantity) {
        this.cart.addProduct(product,quantity);
        }

    public void removeFromCart(Product product, int quantity) {
        this.cart.removeProduct(product,quantity);
    }

    public void viewCart() {
        this.cart.viewProducts();
    }

    public void placeOrder() {
        Order order = new Order(this.cart.getProducts(),this);
        this.ordersPlaced.add(order);
        this.cart.clear();
    }
    public List<Order> getOrders() {
        return this.ordersPlaced;
    }

}

class ShoppingCart {
    private Map<Product, Integer> productInCart;
    public ShoppingCart(){
        this.productInCart = new HashMap<>();
    }
    public void addProduct(Product product, int quantity) {
        if(this.productInCart.containsKey(product)) {
            this.productInCart.put(product,this.productInCart.get(product)+quantity);
        }
        else {
            this.productInCart.put(product, quantity);
        }
    }

    public void removeProduct(Product product, int quantity) {
        if(this.productInCart.containsKey(product))
        {
            if(this.productInCart.get(product)<=quantity){
                this.productInCart.remove(product);
            }
            else{
                this.productInCart.put(product,this.productInCart.get(product)-quantity);
            }
        }
        else{
            System.out.println("No such product found");
        }
    }

    public void viewProducts() {
        System.out.println("The products in the cart are: ");
        for (Map.Entry<Product, Integer> entry : this.productInCart.entrySet()) {
            System.out.println(entry.getKey().getProductName() + " x " + entry.getValue());
        }
    }
    public Map<Product,Integer> getProducts(){
        return this.productInCart;
    }
    public void clear(){
        this.productInCart.clear();
    }
}

class Order {
    private Map<Product,Integer> productOrdered;
    private Customer customerName;
    private Date dateOfOrderPlaced;
    public Order(Map<Product,Integer> products,Customer customerName){
        this.productOrdered = products;
        this.customerName = customerName;
        this.dateOfOrderPlaced = new Date();
    }

    public double getTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : this.productOrdered.entrySet()) {
            total += entry.getKey().getProductPrice() * entry.getValue();
        }
        return total;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order details:\n");
        for (Map.Entry<Product, Integer> entry : this.productOrdered.entrySet()) {
            sb.append(entry.getKey().getProductName()).append(" x ").append(entry.getValue()).append("\n");
        }
        sb.append("Customer: ").append(this.customerName.getUserName()).append("\n");
        sb.append("Date: ").append(this.dateOfOrderPlaced).append("\n");
        sb.append("Total: ").append(getTotal());
        return sb.toString();
    }

}

public class OnlineMarketPlace {
    public static void main(String a[]) {

        // Create some sellers
        Seller seller1 = new Seller("Varun", "varun@example.com");
        Seller seller2 = new Seller("Mani", "mani@example.com");

        // Create some products
        Product product1 = new Product("Product 1", "This is product 1", 123.45, seller1);
        Product product2 = new Product("Product 2", "This is product 2", 134.54, seller1);
        Product product3 = new Product("Product 3", "This is product 3", 122.67, seller2);

        // Add products to sellers' lists
        seller1.addProduct(product1);
        seller1.addProduct(product2);
        seller2.addProduct(product3);

        // Create some customers
        Customer customer1 = new Customer("Customer_1", "c1@example.com");
        Customer customer2 = new Customer("Customer_2", "c2@example.com");

        // Add products to customers' shopping carts
        customer1.addToCart(product1, 2);
        customer1.addToCart(product2, 1);
        customer2.addToCart(product3, 3);

        // View customers' shopping carts
        System.out.println("Customer 1's shopping cart:");
        customer1.viewCart();
        System.out.println("Customer 2's shopping cart:");
        customer2.viewCart();

        // Place orders
        customer1.placeOrder();
        customer2.placeOrder();

        // View customers' orders
        System.out.println("Customer 1's orders:");
        for (Order order : customer1.getOrders()) {
            System.out.println(order.toString());
        }
        System.out.println("Customer 2's orders:");
        for (Order order : customer2.getOrders()) {
            System.out.println(order.toString());
        }
    }
    }


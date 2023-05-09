import java.util.Scanner;

public class CartManager {

    public CartManager () {
    }

    public static void main (String[] args) throws IllegalArgumentException{
        // Initialize a new cart manager
        CartManager cartManager = new CartManager();

        // Initialize a new ticket for stage 1
        Ticket ticket = new Ticket();
        System.out.println("********** Stage 1 **********");
        ticket = cartManager.stage1(ticket);
        System.out.println(ticket.toString());

        // Initialize a new ticket for stage 2 and 3
        ShoppingCart cart = new ShoppingCart();
        cart = cartManager.stage2(cart);

        // Stage 3 (use data from stage 2)
        cartManager.stage3(cart);

        // Stage 4
        cartManager.stage4();

    }

    public Ticket stage1 (Ticket ticket) throws IllegalArgumentException{
        // Initialize a scanner to take the user input
        Scanner scanner = new Scanner(System.in);

        // Take the user's input for the name of the ticket
        System.out.println("Enter the name of the ticket:");
        String ticketName = scanner.nextLine();

        // Take the user's input for the price of the ticket
        System.out.println("Enter the ticket price:");
        int ticketPrice = scanner.nextInt();
        scanner.nextLine();

        // Take the user's input for the quantity of the ticket
        System.out.println("Enter the quantity:");
        int ticketQuantity = scanner.nextInt();
        scanner.nextLine();

        // Create a new ticket using the user's input
        ticket = new Ticket(ticketName, ticketPrice, ticketQuantity);
        return ticket;
    }

    public ShoppingCart stage2 (ShoppingCart cart) throws IllegalArgumentException{
        System.out.println("********** Stage 2 **********");

        // Initialize a scanner to take the user input
        Scanner scanner = new Scanner(System.in);
        // Take the user's input for the name of the customer
        System.out.println("Enter the name of the customer:");
        String customerName = scanner.nextLine();

        // Take the user's input for the current date
        System.out.println("Enter the current date:");
        String currentDate = scanner.nextLine();

        // Create a new cart using the user's input and print the total cost of the cart
        cart = new ShoppingCart(customerName, currentDate);
        cart.printTotal();

        // Take the user input for 2 tickets and add the tickets to cart
        for (int i = 0; i < 2; i++) {
            Ticket newTicket = this.stage1(new Ticket());
            cart.add(newTicket);
        }

        // Print the total cost of the cart
        cart.printTotal();
        return cart;
    }

    public void stage3 (ShoppingCart cart) throws IllegalArgumentException{
        System.out.println("********** Stage 3 **********");

        // Initialize a scanner to take the user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user if he/she wants to remove a ticket from cart
        System.out.println("Do you want to remove a ticket from the cart? Y/N");
        String isRemoveTicket = scanner.nextLine();

        if (isRemoveTicket.equals("Y")) {
            // Take the user's input for the ticket's name and remove the ticket, then print the total of the cart
            System.out.println("Enter the name of the ticket:");
            String ticketToBeRemoved = scanner.nextLine();
            cart.removeTicket(ticketToBeRemoved);
            cart.printTotal();
        }

        // Ask the user if he/she wants to update a ticket in the cart
        System.out.println("Do you want to update a ticket from the cart? Y/N");
        String isUpdateTicket = scanner.nextLine();

        if (isUpdateTicket.equals("Y")) {
            // Take the user's input for the ticket's name and update the ticket, then print the total of the cart
            System.out.println("Enter the name of the ticket:");
            String ticketName = scanner.nextLine();
            cart.updateTicket(ticketName);
            cart.printTotal();
        }

        // Ask the user if he/she wants to update a ticket in the cart
        System.out.println("Do you want to checkout? Y/N");
        String isCheckout = scanner.nextLine();

        if (isCheckout.equals("Y")) {
            // Checkout and leave a thank you message
            cart.checkout();
            System.out.println("Thank you for shopping");
        }
    }

    public void stage4 () throws IllegalArgumentException{
        System.out.println("********** Stage 4 **********");

        // Initialize a scanner to take the user input
        Scanner scanner = new Scanner(System.in);

        // Take the user input for the VIP customer's name
        System.out.println("Enter the name of the VIP customer:");
        String VIPCustomerName = scanner.nextLine();

        // Take the user input for the current date
        System.out.println("Enter the current date:");
        String currentDate = scanner.nextLine();

        // Pass the input to the VIPCart to create a cart
        VIPCart VIPCustomer = new VIPCart(VIPCustomerName, currentDate);

        // Take the user input for the VIP points
        System.out.println("Enter the VIP points available:");
        int VIPPoints = scanner.nextInt();
        scanner.nextLine();
        VIPCustomer.setVIPPoints(VIPPoints);

        // Use the create ticket steps in stage 1 and add the new ticket to the cart
        Ticket newTicket = this.stage1(new Ticket());
        VIPCustomer.add(newTicket);

        VIPCustomer.printTotal();

        // Ask the user if he/she wants to checkout
        System.out.println("Do you want to checkout? Y/N");
        String isCheckout = scanner.nextLine();

        if (isCheckout.equals("Y")) {
            // Customer checkout
            VIPCustomer.checkout();
        }
    }
}

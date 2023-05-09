import java.util.Scanner;

public class ShoppingCart {
    private static final int CAPACITY = 5;
    private String customerName;
    private String date;
    private Ticket[] inCartTickets;
    private int count;

    public ShoppingCart () {
        this.customerName = "UNKNOWN";
        this.date = "1 May 2022";
        this.inCartTickets = new Ticket[CAPACITY];
        this.count = 0;
    }

    public ShoppingCart (String name, String date) {
        this.customerName = name;
        this.date = date;
        this.inCartTickets = new Ticket[CAPACITY];
        this.count = 0;
    }

    public static int getCAPACITY () {
        return CAPACITY;
    }

    public boolean add (Ticket ticket) {
        // Check if the ticket passed in is not null
        if (ticket != null) {
            // Check if the cart is not full
            if (this.count < CAPACITY) {
                // Check if the cart is not empty
                if (this.count > 0) {
                    // Loop through each ticket in the cart and return false if the ticket already exists
                    for (int i = 0; i < this.count; i++) {
                        if (ticket.getName().toLowerCase().equals(this.inCartTickets[i].getName().toLowerCase())) {
                            System.out.println("Ticket invalid or already added.");
                            return false;
                        }
                    }
                }
                // Add the ticket to cart, increase the number of ticket by 1, and return true
                inCartTickets[this.count] = ticket;
                this.count++;
                return true;
            }
            System.out.println("SHOPPING CART IS FULL");
            return false;
        }
        System.out.println("Ticket invalid or already added.");
        return false;
    }

    public boolean removeTicket (String ticketName) {
        // Check if the cart is not empty
        if (this.count > 0) {
            // Loop through each ticket in the shopping cart and find the ticket with the same name as the parameter.
            // Once found, print out the name of the removed ticket and remove the ticket (set to null)
            // Shift all tickets after the remove tickets backward 1 index.
            for (int i = 0; i < this.count; i++) {
                if (ticketName.toLowerCase().equals(this.inCartTickets[i].getName().toLowerCase())) {
                    System.out.println(this.inCartTickets[i].getName() + " removed from the shopping cart.");
                    this.inCartTickets[i] = null;
                    for (int j = i; j < this.count; j++) {
                        this.inCartTickets[j] = this.inCartTickets[j + 1];
                    }
                    break;
                }
            }
            // Reduce the number of ticket in the cart by 1 and return true
            this.count--;
            return true;
        }
        System.out.println("Ticket not found. Cart remains unchanged.");
        return false;
    }

    public boolean updateTicket (String ticketName) {
        // Check if the cart is not empty
        if (this.count > 0) {
            // Loop through each ticket in the shopping cart and find the ticket with the same name as the parameter
            // Once found, prompt the user to update the ticket's quantity
            for (int i = 0; i < this.count; i++) {
                if (ticketName.toLowerCase().equals(this.inCartTickets[i].getName().toLowerCase())) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Please enter the new quantity: ");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine();
                    this.inCartTickets[i].setQuantity(newQuantity);
                    return true;
                }
            }
        }
        System.out.println("Ticket not found. Cart remains unchanged.");
        return false;
    }

    public boolean checkout () {
        if (this.count > 0) {
            this.printTotal();
            //remove tickets from the cart
            this.emptyCart();
            return true;
        }
        System.out.println("SHOPPING CART IS EMPTY.");
        return false;
    }

    public int getTotalCount () {
        int numTotalTicket = 0;
        for (int i = 0; i < this.count; i++) {
            numTotalTicket += this.inCartTickets[i].getQuantity();
        }
        return numTotalTicket;
    }

    public int getCost () {
        int cartCost = 0;
        for (int i = 0; i < this.count; i++) {
            cartCost += this.inCartTickets[i].getTotalPrice();
        }
        return cartCost;
    }

    public void printTotal () {
        System.out.println(this.customerName + " - " + this.date);

        // Check if the shopping cart is empty, print out the empty message
        if (this.count == 0) {
            System.out.println("SHOPPING CART IS EMPTY.");
        } else {
            // Print out the total number of the tickets
            System.out.println("Number of tickets: " + this.getTotalCount());
            // Print out each ticket information
            for (int i = 0; i < this.count; i++) {
                System.out.println(this.inCartTickets[i].toString());
            }
            // Print out the total cost of the cart
            System.out.println("Total cost: $" + this.getCost());
        }
    }

    public void emptyCart() {
        this.inCartTickets = new Ticket[CAPACITY];
        this.count = 0;
    }

    public String getCustomerName () {
        return this.customerName;
    }

    public void setCustomerName (String name) {
        this.customerName = name;
    }

    public String getDate () {
        return this.date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public int getCount () {
        return this.count;
    }

    public void setCount (int numDistinctTickets) {
        this.count = numDistinctTickets;
    }

    public Ticket[] getInCartTickets () {
        return inCartTickets;
    }

    public void setInCartTickets (Ticket[] inCartTickets) { this.inCartTickets = inCartTickets; }

}

class VIPCart extends ShoppingCart {
    private int VIPPoints;

    public VIPCart () {
        super();
        this.VIPPoints = 0;
    }

    public VIPCart (String name, String date) {
        super(name, date);
        this.VIPPoints = 0;
    }

    @Override
    public boolean checkout () {
        // Get the total cost of the cart. If the cost >= 100, make the last ticket free
        int totalCost = this.getCost();
        if (totalCost >= 100) {
            totalCost -= this.getInCartTickets()[this.getCount() - 1].getPrice();
        }
        // Check if the shopping cart is not empty
        if (this.getCount() > 0) {
            // Initialize a scanner to get the redeem points input from the user
            Scanner scanner = new Scanner(System.in);
            System.out.println("How many points to redeem? Enter -1 to checkout without using points.");
            int inputRedeemPoints = scanner.nextInt();
            scanner.nextLine();

            // If the user's redeem points input is > the total points available, keep asking the user to re-enter
            while (inputRedeemPoints > this.VIPPoints) {
                System.out.println("Not enough points. Please re-enter or enter -1 to quit point redeeming.");
                inputRedeemPoints = scanner.nextInt();
                scanner.nextLine();
            }
            // if the user's redeem point input is -1, add the total cost to the total points available
            if (inputRedeemPoints == -1) {
                System.out.println("No VIP points used");
                this.VIPPoints += totalCost;
            } else if (inputRedeemPoints >= 0) {
                // redeemableAmount is used to convert the inputRedeemPoints to the actual discount (in dollars)
                int redeemableAmount = (int) (inputRedeemPoints / 20) * 1;
                // Apply the discount to the total cost
                totalCost -= redeemableAmount;
                // Convert the actual discount to redeemed points,
                // subtract the redeemed points and add the total cost to VIP points
                this.VIPPoints -= redeemableAmount * 20;
                this.VIPPoints += totalCost;
                System.out.println("Redeemed " + redeemableAmount * 20 + " points for" + " $" + redeemableAmount);
            }
            // Print out order or checkout summary
            System.out.println("Total price paid: $" + totalCost);
            System.out.println(totalCost + " VIP pointed added. Available " + this.VIPPoints + " points.");
            System.out.println("Thank you for shopping");

            //remove tickets from the cart
            this.emptyCart();

            return true;
        }
        System.out.println("SHOPPING CART IS EMPTY.");
        return false;
    }

    @Override
    public void printTotal () {
        System.out.println(this.getCustomerName() + " - " + this.getDate());

        // Check if the shopping cart is empty, print out the empty message
        if (this.getCount() == 0) {
            System.out.println("SHOPPING CART IS EMPTY.");
        } else {
            // Print out the total number of the tickets
            System.out.println("Number of tickets: " + this.getTotalCount());

            // Get the total cost of the cart. If the total cost of the cart >= 100, make the last ticket free
            // and print out each ticket information
            int totalCost = this.getCost();
            for (int i = 0; i < this.getCount(); i++) {
                if (i == this.getCount() - 1 && totalCost >= 100) {
                    System.out.println(this.getInCartTickets()[i].getName() + " " + this.getInCartTickets()[i].getQuantity() + " X " + "$" + this.getInCartTickets()[i].getPrice() + " = $" + (this.getInCartTickets()[i].getTotalPrice() - this.getInCartTickets()[i].getPrice()) + " (with VIP discount)");
                    totalCost -= this.getInCartTickets()[i].getPrice();
                } else {
                    System.out.println(this.getInCartTickets()[i].toString());
                }
            }
            // Print out the total cost of the cart
            System.out.println("Total cost: $" + totalCost);
        }
    }

    public int getVIPPoints () {
        return VIPPoints;
    }

    public void setVIPPoints (int VIPPoints){
        if (this.VIPPoints >=0){
            this.VIPPoints = VIPPoints;
        }else {
            throw new IllegalArgumentException("Invalid VIP Points");
        }
    }
}
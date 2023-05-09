public class Ticket {
    private String name;
    private int price;
    private int quantity;

    public Ticket () {
        this.name = "UNKNOWN";
        this.price = -1;
        this.quantity = 0;
    }

    public Ticket (String name, int price, int quantity) {
        // Reuse the setName, setPrice, and setQuantity methods as the validation
        // for each parameter is included in these methods
        this.setName(name);
        this.setPrice(price);
        this.setQuantity(quantity);
    }

    public int getTotalPrice () {
        return this.price * this.quantity;
    }

    @Override
    public String toString () {
        return this.name + " " + this.quantity + " X " + "$" + this.price + " = $" + this.getTotalPrice();
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        // Set name if the parameter is not null, otherwise, throw an error
        if (name != null) {
            this.name = name;
        }else {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    public int getPrice () {
        return price;
    }

    public void setPrice (int price) {
        // Set price if the parameter >= 0, otherwise, throw an error
        if (price >= 0) {
            this.price = price;
        }else {
            throw new IllegalArgumentException("Invalid Price");
        }
    }

    public int getQuantity () {
        return quantity;
    }

    public void setQuantity (int quantity) {
        // Set price if the parameter >= 1, otherwise, throw an error
        if (quantity >= 1) {
            this.quantity = quantity;
        }else {
            throw new IllegalArgumentException("Invalid quantity");
        }
    }
}

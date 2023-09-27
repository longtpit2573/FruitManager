package MangerFruitLab211.model;

public class Fruit {
   private String id;
   private String name;
   private double Price;
   private int quantity;
   private String origin;

    public Fruit() {
    }

    public Fruit(String id, String name, double Price, int quantity, String origin) {
        this.id = id;
        this.name = name;
        this.Price = Price;
        this.quantity = quantity;
        this.origin = origin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return name+"   "+origin+"   "+Price+"$";
    }
   

    public void reduceQuantity(int quantityToReduce) {
        if (this.quantity >= quantityToReduce) {
            this.quantity -= quantityToReduce;
        } else {
            System.out.println("Not enough quantity in stock.");
        }
    }
   
}


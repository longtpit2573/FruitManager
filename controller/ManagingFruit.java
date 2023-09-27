package MangerFruitLab211.controller;
import java.util.ArrayList;
import java.util.Hashtable;

import MangerFruitLab211.common.Validation;
import MangerFruitLab211.model.Fruit;
import MangerFruitLab211.view.Menu;

public class ManagingFruit extends Menu<String> {
    private ArrayList<Fruit> list = new ArrayList<>();
    private Hashtable<String, ArrayList<Fruit>> billList = new Hashtable<>();
    private Validation check = new Validation();

    static String[] mc = { "Create", "View Order", "Shopping", "Exit" };

    public ManagingFruit() {
        super("=========Fruit System========", mc);
    }

    @Override
    public void excute(int n) {
       switch (n) {
            case 1:
                createFruit();
                break;
            case 2:
                viewListOrder();
                break;
            case 3:
                buy();
                break;
            case 4:
                System.exit(0);
        }
    }
   
    public void createFruit() {
        String id = check.getID(list);
        System.out.println("ID: " + id);
        String name = check.getStringDataCanBlankk("Enter fruit name: ");
        double price = check.getDouble("Enter fruit price");
        int quantity = check.getIntData("Enter fruit quantity", 1, 100);
        String origin = check.getString("Enter fruit origin: ");
        list.add(new Fruit(id, name, price, quantity, origin));

        String choice = val.getString("Do you want to continue(Y/N)", "Y|N");

        if (choice.equals("Y")) {
            createFruit();
        }
    }

    private void viewListOrder() {
        System.out.println("\n----------View Order----------");
        for (String customer : billList.keySet()) {
            System.out.println("Customer: " + customer);
            displayBills(billList.get(customer));
        }

        System.out.println("----------------------------------");
    }

    private void displayBills(ArrayList<Fruit> list) {
        int no = 0;
        double total = 0;
        System.out.printf("%10s%3s%10s%3s%10s%3s%10s", "Product", "|", "Quantity", "|", "Price", "|", "Amount");
        System.out.println("");
        for (Fruit fruit : list) {
            no++;
            total += fruit.getPrice() * fruit.getQuantity();
            System.out.printf("%11s%11s%13s%13s", no + ". " + fruit.getName(), "" + fruit.getQuantity(),
                    fruit.getPrice() + "$", "" + fruit.getPrice() * fruit.getQuantity());
            System.out.println("");
        }
        System.out.println("Total: " + total + "$");
    }

    public void buy() {
        ArrayList<Fruit> selectedList = new ArrayList<>();

        do {
            System.out.println("\nList of fruit: ");
            fruitDisplay();
            int select = val.getIntData("Your selection: ", 1, list.size());
            Fruit selectedFruit = list.get(select - 1);
            int quantity = 0;
            System.out.println("You selected: " + selectedFruit.getName());
            quantity = val.getIntData("Please input quantity: ", 1, selectedFruit.getQuantity());
            selectedList.add(new Fruit(selectedFruit.getId(), selectedFruit.getName(), selectedFruit.getPrice(),
                    quantity, selectedFruit.getOrigin()));
            selectedFruit.setQuantity(selectedFruit.getQuantity() - quantity);
        } while (val.getString("Do you want to continue(Y/N)", "Y|N").equalsIgnoreCase("Y"));

        displayBills(selectedList);
        String cus = val.getString("Input your name: ");
        billList.put(cus, selectedList);
    }

    private void fruitDisplay() {
        System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ | ");
        for (int i = 0; i < list.size(); i++) {
            Fruit f = list.get(i);
            System.out.printf("%6s%17s%19s%15s", (i + 1) + "", f.getName(), f.getOrigin(), f.getPrice() + "$");
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        new ManagingFruit().run();
    }

    
}

import java.util.TreeMap;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Shopping {

	private static TreeMap<String, Integer> items = new TreeMap<>();
      	private static TreeMap<String, ArrayList<String>> carts = new TreeMap<>();
	private static String parts[];
	private static String item;
	private static String name;
	private static String itemsFile;
	private static String ordersFile;
	private static String command;
	private static String line;

  public static void main(String[] args) throws FileNotFoundException {

    if (args.length != 2 ) {
	System.exit(1);
	}

	itemsFile = args[0];
	ordersFile = args[1];

	Carts();
	Items();
	Orders();

}

  public static void Items() throws FileNotFoundException{
    Scanner sc = new Scanner(new File(itemsFile));

	while (sc.hasNext()){
		line = sc.nextLine();
		parts = line.split("\\s+");
		item = parts[0];
		int price = Integer.parseInt(parts[1].substring(1));
		items.put(item, price);
		}

	sc.close();
	}


  public static void Carts() throws FileNotFoundException {
        Scanner kb = new Scanner(new File(ordersFile));
	while (kb.hasNext()) {
	line = kb.nextLine();
	parts = line.split(" ");
	name = parts[1];

		if (carts.containsKey(name) != true) {
		carts.put(name, new ArrayList<String>());
		}

	}
	kb.close();
}

  public static void Orders() throws FileNotFoundException {
    Scanner kb = new Scanner(new File(ordersFile));

    while (kb.hasNext()) {
	line = kb.nextLine();
	parts = line.split(" ");
	command = parts[0];
	name = parts[1];

	switch(command) {
	case "add":
		item = parts[2];
		if (carts.get(name).contains(item)) {
		System.out.println(name + " already has " + item);
		}else {
		carts.get(name).add(item);
		System.out.println(item + " added to " + name + "'s cart");
		}
		break;
	case "delete":
		item = parts[2];
		if (!carts.get(name).contains(item)) {
		System.out.println(item + " not found in " + name + "'s cart");
		} else {
		carts.get(name).remove(item);
		}
		break;
	case "cart":
		System.out.println("View " + name + "'s cart:" );
                System.out.println("---------------------"); 
                System.out.println("Item\t\tPrice");
                System.out.println("---------------------");
		for (int i = 0; i < carts.get(name).size(); i++) {
		System.out.println(String.format("%-15s$ %s" , carts.get(name).get(i), items.get(carts.get(name).get(i))));
		}
		break;
	case "checkout":
		int total = 0;
		for (int i = 0; i < carts.get(name).size(); i++) {
			total += items.get(carts.get(name).get(i));
		}
		System.out.println(String.format("%-15s %s" , name + " checked out.", "Total = $" + total ));
		break;
	}

    }
        kb.close();
}
} // ends class


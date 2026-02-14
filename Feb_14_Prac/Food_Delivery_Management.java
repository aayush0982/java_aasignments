/*
 # Question: Online Food Delivery System (OOP Design)

## Question Title

Online Food Delivery Order Management

## Detailed Description

You are developing the backend module for an online food delivery platform similar to Swiggy or Zomato. The system must support multiple types of users, restaurants, menu items, and order processing.

Design and implement the system using proper Object-Oriented Programming principles in Java.

The platform should support the following real-world requirements:

A customer can:

- Register and log in
- Browse restaurants
- View restaurant menus
- Add items to cart
- Place orders
- Track order status

A restaurant owner can:

- Add restaurant details
- Add or remove menu items
- Update item prices
- View incoming orders

The system must also handle different order states such as:

- CREATED
- CONFIRMED
- PREPARING
- OUT_FOR_DELIVERY
- DELIVERED
- CANCELLED

---

## Functional Requirements

### User Management

1. The system should support two types of users:
   - Customer
   - RestaurantOwner

2. Each user must have:
   - userId
   - name
   - email
   - phone

3. Only customers can place orders.

---

### Restaurant Management

1. Each restaurant must have:
   - restaurantId
   - restaurantName
   - location
   - list of menu items

2. Restaurant owners can:
   - Add menu items
   - Remove menu items
   - Update price of menu items

---

### Menu Item

Each menu item must contain:

- itemId
- itemName
- price
- availability status

---

### Cart System

1. Each customer has one active cart.
2. Cart should allow:
   - Add item
   - Remove item
   - Update quantity
   - View total price

3. Cart should not allow items from multiple restaurants in the same order.

---

### Order Management

When a customer places an order:

1. Order should be created from the cart.
2. Each order must contain:
   - orderId
   - customer
   - restaurant
   - ordered items with quantity
   - total amount
   - order status

3. Order status should be updatable.
4. Order history should be maintained per customer.

---

### Tracking System

Customer should be able to track the order status in real time.

---

## Technical Constraints

You must follow these strict OOP rules:

- Use inheritance where appropriate
- Use abstraction for common behavior
- Use encapsulation properly
- Use polymorphism at least once
- Use interfaces where meaningful
- Do NOT use static global variables for core logic
- Properly override toString where useful
- Follow clean class design

---

## Expected Class Hints (You may redesign)

You may consider (but are not limited to):

- User (abstract)
- Customer
- RestaurantOwner
- Restaurant
- MenuItem
- Cart
- CartItem
- Order
- OrderItem
- OrderStatus (enum)
- FoodDeliveryService (main controller)

---

## Sample Scenario

The main method should demonstrate something similar to:

1. Create restaurant owner
2. Owner creates restaurant
3. Owner adds menu items
4. Customer registers
5. Customer browses restaurant
6. Customer adds items to cart
7. Customer places order
8. System updates order status step by step
9. Customer tracks order

---

## Bonus Requirements (for advanced practice)

If you want to push yourself further:

- Prevent ordering unavailable items
- Apply discount coupon support
- Add delivery partner assignment
- Support multiple payment methods using polymorphism
- Implement simple in-memory search for restaurants

---

## What This Question Tests

This real-time problem evaluates your understanding of:

- Class design
- Inheritance
- Abstraction
- Polymorphism
- Encapsulation
- Enum usage
- Object relationships
- Real-world modeling

---
 */

package Feb_14_Prac;

import java.util.*;

enum OrderStatus {
	Created, Confirmed, Preparing, OutForDelivery, Delivered, Cancelled
}

class MenuItem {
	String itemId;
	String itemName;
	double price;
	boolean isAvailable;

	public MenuItem(String itemId, String itemName, double price, boolean isAvailable) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return itemName + " (" + itemId + ") - $" + price + " - " + (isAvailable ? "Available" : "Out of Stock");
	}
}

class Restaurant {
	Map<String, List<MenuItem>> availableRestaurant = new HashMap<>();

	public Restaurant() {
		availableRestaurant = new HashMap<>();

		availableRestaurant.put("The Spice Hub",
				new ArrayList<>(Arrays.asList(new MenuItem("I101", "Butter Chicken", 250, true),
						new MenuItem("I102", "Paneer Tikka", 180, true), new MenuItem("I103", "Garlic Naan", 40, true),
						new MenuItem("I104", "Mango Lassi", 60, false))));

		availableRestaurant.put("Green Bowl",
				new ArrayList<>(Arrays.asList(new MenuItem("I201", "Quinoa Salad", 200, true),
						new MenuItem("I202", "Avocado Toast", 150, true),
						new MenuItem("I203", "Veggie Wrap", 120, true),
						new MenuItem("I204", "Fresh Juice", 80, true))));

		availableRestaurant.put("Pizza Planet",
				new ArrayList<>(Arrays.asList(new MenuItem("I301", "Margherita Pizza", 300, true),
						new MenuItem("I302", "Pepperoni Pizza", 350, true),
						new MenuItem("I303", "Garlic Bread", 50, true),
						new MenuItem("I304", "Chocolate Brownie", 120, false))));

	}

	boolean checkAvailable(String itemName, String resName) {
		List<MenuItem> menu = availableRestaurant.get(resName);

		if (menu == null) {
			return false;
		}

		for (MenuItem m : menu) {
			if (itemName.equals(m.itemName)) {
				return m.isAvailable;
			}
		}
		return false;
	}

}

class User {
	String userId;
	String name;
	String email;
	int phone;
}

class OrderItem {
	int orderId;
	String orderName;
	int orderQuantity;
	int price;
	OrderStatus status;

	public OrderItem(int orderId, String orderName, int orderQuantity, int price, OrderStatus status) {
		this.orderId = orderId;
		this.orderName = orderName;
		this.orderQuantity = orderQuantity;
		this.price = price;
		this.status = status;
	}
}

class Order {
	Restaurant res;
	Map<String, OrderItem> order = new HashMap<>();

	Order(Restaurant res) {
		this.res = res;
	}

	void placedOrder(String name, Map<String, Integer> cart, Map<String, List<MenuItem>> restaurants) {

		int id = (int) (Math.random() * 1000);

		for (Map.Entry<String, Integer> cartEntry : cart.entrySet()) {

			String itemName = cartEntry.getKey();
			int quantity = cartEntry.getValue();
			int totalPrice = 0;

			for (Map.Entry<String, List<MenuItem>> restEntry : restaurants.entrySet()) {

				for (MenuItem m : restEntry.getValue()) {

					if (m.itemName.equals(itemName)) {
						totalPrice = (int) (m.price * quantity);
						break;
					}
				}
			}

			order.put(itemName, new OrderItem(id, itemName, quantity, totalPrice, OrderStatus.Created));
			System.out.println("Order Placed Successfully");
		}
	}

	OrderStatus trackOrder(String itemName) {
		OrderStatus st = null;
		for (OrderItem o : order.values()) {
			if (o.orderName.equals(itemName)) {
				st = o.status;
			}
		}
		return st;
	}
}

class Cart {
	Restaurant res;
	Order order;
	Map<String, Integer> cart = new HashMap<>();

	Cart(Restaurant res) {
		this.res = res;
		this.order = new Order(res);
	}

	void addItems(String itemname) {
		cart.put(itemname, cart.getOrDefault(itemname, 0) + 1);
	}

	void removeItem(String itemname) {
		cart.remove(itemname);
	}

	void updateQuantity(String itemname, Integer quant) {
		if (quant < 0) {
			cart.put(itemname, cart.get(itemname) - quant);
		} else {
			cart.put(itemname, cart.get(itemname) + quant);
		}
	}

	int viewTotalPrice() {
		int totalPrice = 0;

		for (String k : cart.keySet()) {
			Map<String, List<MenuItem>> temp = res.availableRestaurant;

			for (Map.Entry<String, List<MenuItem>> e : temp.entrySet()) {
				for (MenuItem m : e.getValue()) {
					if (m.itemName.equals(k)) {
						totalPrice += m.price;
					}
				}
			}
		}
		return totalPrice;
	}

	void orderPlaced(String name) {
		order.placedOrder(name, cart, res.availableRestaurant);
	}

}

class Customer {
	Restaurant res;
	Cart cart;
	String userName;

	Customer(Restaurant res) {
		this.res = res;
		this.cart = new Cart(res);
	}

	void register(String name, User userclass, String usertype) {
		userName = name;
	}

	List<String> browseRestaurant() {
		Map<String, List<MenuItem>> temp = res.availableRestaurant;
		List<String> restaurant = new ArrayList<>();
		for (String e : temp.keySet()) {
			restaurant.add(e);
		}
		return restaurant;
	}

	void addItems(String resname, String itemname) {
		boolean isAvailable = res.checkAvailable(itemname, resname);
		if (isAvailable) {
			cart.addItems(itemname);
		}
	}

	void removeItems(String resname, String itemname) {
		cart.removeItem(itemname);
	}

	void updateItem(String itemname, Integer quant) {
		cart.updateQuantity(itemname, quant);
	}

	int viewTotalPrice() {
		return cart.viewTotalPrice();
	}

	void placeOrder() {
		cart.orderPlaced(userName);
	}
}

class RestaurantOwner {
	String ownerName;
	Restaurant res;

	RestaurantOwner(Restaurant res) {
		this.res = res;
	}

	void addMenuItem(String restaurantName, MenuItem item) {

		if (!res.availableRestaurant.containsKey(restaurantName)) {
			System.out.println("Adding Restaurant");
			res.availableRestaurant.put(restaurantName, new ArrayList<>());
		}

		res.availableRestaurant.get(restaurantName).add(item);
	}
}

public class Food_Delivery_Management {
	public static void main(String[] args) {
		Restaurant sharedRestaurant = new Restaurant();
		RestaurantOwner owner = new RestaurantOwner(sharedRestaurant);

		Customer c1 = new Customer(sharedRestaurant);
		Customer c2 = new Customer(sharedRestaurant);
		Customer c3 = new Customer(sharedRestaurant);
		Customer c4 = new Customer(sharedRestaurant);

		// Create customer
		// ---------- CUSTOMER 1 ----------
		User u1 = new User();
		c1.register("Aayush", u1, "User");
		c1.addItems("The Spice Hub", "Butter Chicken");
		c1.addItems("The Spice Hub", "Garlic Naan");
		c1.addItems("Pizza Planet", "Margherita Pizza");
		c1.addItems("Pizza Planet", "Farmhouse Pizza");
		c1.addItems("Burger Barn", "Cheese Burger");

		System.out.println("\nCustomer 1 Total: " + c1.viewTotalPrice());
		c1.placeOrder();
		System.out.println("C1 Order Status (Butter Chicken): " + c1.cart.order.trackOrder("Butter Chicken"));

		// ---------- CUSTOMER 2 ----------
		User u2 = new User();
		c2.register("Rohit", u2, "User");
		c2.addItems("Burger Barn", "Veg Burger");
		c2.addItems("Burger Barn", "Veg Burger");
		c2.addItems("Pizza Planet", "Pepperoni Pizza");
		c2.addItems("The Spice Hub", "Paneer Tikka");
		c2.addItems("The Spice Hub", "Dal Makhani");
		c2.addItems("The Spice Hub", "Butter Naan");

		System.out.println("\nCustomer 2 Total: " + c2.viewTotalPrice());
		c2.placeOrder();
		System.out.println("C2 Order Status (Veg Burger): " + c2.cart.order.trackOrder("Veg Burger"));

		// ---------- CUSTOMER 3 ----------
		User u3 = new User();
		c3.register("Sneha", u3, "User");
		c3.addItems("Pizza Planet", "Margherita Pizza");
		c3.addItems("Pizza Planet", "Margherita Pizza");
		c3.addItems("Pizza Planet", "Garlic Bread");
		c3.addItems("Burger Barn", "French Fries");
		c3.addItems("Burger Barn", "Cold Coffee");

		System.out.println("\nCustomer 3 Total: " + c3.viewTotalPrice());
		c3.placeOrder();
		System.out.println("C3 Order Status (Garlic Bread): " + c3.cart.order.trackOrder("Garlic Bread"));

		// ---------- CUSTOMER 4 ----------
		User u4 = new User();
		c4.register("Neha", u4, "User");
		c4.addItems("The Spice Hub", "Chicken Biryani");
		c4.addItems("The Spice Hub", "Chicken Biryani");
		c4.addItems("The Spice Hub", "Raita");
		c4.addItems("Pizza Planet", "Cheese Burst Pizza");
		c4.addItems("Burger Barn", "Zinger Burger");
		c4.addItems("Burger Barn", "Chocolate Shake"); // extra

		System.out.println("\nCustomer 4 Total: " + c4.viewTotalPrice());
		c4.placeOrder();
		System.out.println("C4 Order Status (Chicken Biryani): " + c4.cart.order.trackOrder("Chicken Biryani"));

		owner.addMenuItem("Burger Barn", new MenuItem("B101", "Cheese Burger", 150, true));
		owner.addMenuItem("Burger Barn", new MenuItem("B102", "Veg Burger", 120, true));
		owner.addMenuItem("Burger Barn", new MenuItem("B103", "French Fries", 80, true));
		owner.addMenuItem("Burger Barn", new MenuItem("B104", "Cold Coffee", 90, true));

		owner.addMenuItem("The Spice Hub", new MenuItem("I105", "Chicken Biryani", 280, true));
		owner.addMenuItem("The Spice Hub", new MenuItem("I106", "Butter Naan", 45, true));

		System.out.println("\n===== UPDATED RESTAURANT MENUS =====");

		for (Map.Entry<String, List<MenuItem>> entry : owner.res.availableRestaurant.entrySet()) {
			System.out.println("\nRestaurant: " + entry.getKey());
			System.out.println("---------------------------");
			for (MenuItem item : entry.getValue()) {
				System.out.println(item);
			}
		}

	}

}

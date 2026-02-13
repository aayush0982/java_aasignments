/*
 ===========================
PARKING LOT SYSTEM DESIGN
===========================

Parking slots are numbered from 1 to N.
Each slot can hold at most one car.
A car is identified uniquely by its carNumber (String).

---------------------------------
METHOD EXPLANATIONS
---------------------------------

1) parkCar(String carNumber)

WHAT IT WANTS:
- Park the given car in the LOWEST available slot.
- If parking is full, do nothing or return failure.

RULES SATISFIED:
✔ Lowest available slot assigned
✔ No PriorityQueue used
✔ Slot reused after removal

---------------------------------

2) removeCar(String carNumber)

WHAT IT WANTS:
- Remove the given car from the parking lot.
- Free its slot so it can be reused.

RULES SATISFIED:
✔ Slot becomes reusable
✔ O(1) lookup using HashMap

---------------------------------

3) int getAvailableSlots()

WHAT IT WANTS:
- Return how many slots are currently free.

TIME COMPLEXITY:
O(1)

---------------------------------

4) List<Integer> getOccupiedSlots()

WHAT IT WANTS:
- Return all occupied slot numbers
- Must be SORTED in ASCENDING order


TIME COMPLEXITY:
O(N)

---------------------------------
EDGE CASES HANDLED
---------------------------------

✔ Parking full → no slot found
✔ Removing non-existing car → ignore safely
✔ Slot reuse guaranteed
✔ Always lowest slot chosen

---------------------------------
FINAL NOTES
---------------------------------

- No PriorityQueue used
- Simple, interview-friendly design
- Predictable behavior
- Easy to code & debug
 
 */

package Feb_13_Prac;

import java.util.*;

class ParkingLot {
	TreeSet<Integer> s;
	Map<String, Integer> m;
	List<Integer> list;

	ParkingLot(int n) {
		s = new TreeSet<>();
		m = new HashMap<>();
		list = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			s.add(i);
		}
	}

	void parkCar(String carNumber) {
		if (s.isEmpty()) {
			System.out.println("Parking Full");
			return;
		}

		int emptySlot = s.first();
		s.remove(emptySlot);
		m.put(carNumber, emptySlot);
	}

	void removeCar(String carNumber) {
		if (!m.containsKey(carNumber)) {
			System.out.println("Car not found");
			return;
		}
		int emptySlot = m.get(carNumber);
		s.add(emptySlot);
		m.remove(carNumber);
	}

	int getAvailableSlots() {
		return s.size();
	}

	List<Integer> getOccupiedSlots() {
		List<Integer> l = new ArrayList<>();
		for (int i : m.values()) {
			l.add(i);
		}
		l.sort((a, b) -> a - b);
		return l;
	}

}

public class Car_Parking_System {
	public static void main(String[] args) {

		// ===============================
		// TEST CASE 1: Create Parking Lot
		// ===============================
		ParkingLot lot = new ParkingLot(3);
		System.out.println("Available Slots: " + lot.getAvailableSlots());
		System.out.println();

		// ===============================
		// TEST CASE 2: Normal Parking
		// ===============================
		lot.parkCar("KA-01");
		lot.parkCar("KA-02");
		System.out.println("Available Slots: " + lot.getAvailableSlots());
		System.out.println("Occupied Slots: " + lot.getOccupiedSlots());
		System.out.println();

		// ===============================
		// TEST CASE 3: Remove a Car
		// ===============================
		lot.removeCar("KA-01");
		System.out.println("Available Slots: " + lot.getAvailableSlots());
		System.out.println("Occupied Slots: " + lot.getOccupiedSlots());
		System.out.println();

		// ===============================
		// TEST CASE 4: Slot Reuse (Lowest)
		// ===============================
		lot.parkCar("KA-03"); // should take slot 1 again
		System.out.println("Occupied Slots: " + lot.getOccupiedSlots());
		System.out.println();

		// ===============================
		// TEST CASE 5: Fill Parking
		// ===============================
		lot.parkCar("KA-04");
		lot.parkCar("KA-05"); // parking full here
		System.out.println();

		// ===============================
		// TEST CASE 6: Parking When Full
		// ===============================
		lot.parkCar("KA-06");
		System.out.println();

		// ===============================
		// TEST CASE 7: Remove Non-Existing Car
		// ===============================
		lot.removeCar("KA-99");
		System.out.println();

		// ===============================
		// TEST CASE 8: Duplicate Parking
		// ===============================
		lot.parkCar("KA-03");
		System.out.println();

		// ===============================
		// FINAL STATE
		// ===============================
		System.out.println("Final Available Slots: " + lot.getAvailableSlots());
		System.out.println("Final Occupied Slots: " + lot.getOccupiedSlots());

	}

}

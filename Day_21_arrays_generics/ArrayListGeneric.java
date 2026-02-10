package Day_21_arrays_generics;

public class ArrayListGeneric {
	public static void main(String[] args) {
		MyArraysList<Integer> list = new MyArraysList<>();

		list.add(70);
		list.add(50);
		list.add(40);
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		list.add(50);
		list.add(60);

		list.print();
		System.out.println("\nindex : " + list.get(4));

		list.remove(3);
		list.print();

		System.out.println("\nElement at index : " + list.search(60));

		list.sort();
		list.print();
	}
}

class MyArraysList<T extends Comparable<T>> {
	private Object[] data;
	private int size;

	public MyArraysList() {
		data = new Object[4];
		size = 0;
	}

	public void add(T value) {
		if (size == data.length) {
			resize();
		}
		data[size++] = value;
	}

	private void resize() {
		Object[] newData = new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}

	public void print() {
		for (int i = 0; i < size; i++) {
			System.out.print(data[i] + " ");
		}
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return (T) data[index];
	}

	public void remove(int index) {
		if (index < 0 || index >= size) {
			return;
		}

		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}

		size--;
		data[size] = null;
	}

	@SuppressWarnings("unchecked")
	public int search(T value) {
		for (int i = 0; i < size; i++) {
			if (((T) data[i]).equals(value)) {
				return i;
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public void sort() {
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1 - i; j++) {
				T a = (T) data[j];
				T b = (T) data[j + 1];

				if (a.compareTo(b) > 0) {
					Object t = data[j];
					data[j] = data[j + 1];
					data[j + 1] = t;
				}
			}
		}

	}
}

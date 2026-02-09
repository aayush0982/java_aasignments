package Day_20_sorting;

import java.util.ArrayList;

class Student {
	int id;
	String name;
}

class Employee {
	String name;
	double[] salary;

	Employee(String name, double[] salary) {
		this.name = name;
		this.salary = salary;
	}

}

public class Bubble_Sort {
	static boolean comp(double[] a, double[] b) {
		boolean flag = false;
		int n = Math.min(a.length, b.length);
		for(int i =0;i<n;i++) {
			if(a[i]>b[i]) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static void main(String[] args) {

		int[] n = { 3, 4, 5, 6, 7, 8, 9, 3, 2, 5, 1, 7 };
		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < n.length - 1-i; j++) {
				if (n[j] > n[j + 1]) {
					int temp = n[j];
					n[j] = n[j + 1];
					n[j + 1] = temp;
				}
			}
		}

		for (int i = 0; i < n.length; i++) {
			System.out.println(n[i]);
		}

		ArrayList<Student> students = new ArrayList<>();

		Student s1 = new Student();
		s1.id = 5;
		s1.name = "Aayush";
		Student s2 = new Student();
		s2.id = 1;
		s2.name = "Rohit";
		Student s3 = new Student();
		s3.id = 9;
		s3.name = "Ankit";
		Student s4 = new Student();
		s4.id = 1;
		s4.name = "Neha";
		Student s5 = new Student();
		s5.id = 7;
		s5.name = "Priya";
		Student s6 = new Student();
		s6.id = 4;
		s6.name = "Karan";
		Student s7 = new Student();
		s7.id = 1;
		s7.name = "Simran";
		Student s8 = new Student();
		s8.id = 3;
		s8.name = "Rahul";
		Student s9 = new Student();
		s9.id = 8;
		s9.name = "Sneha";
		Student s10 = new Student();
		s10.id = 6;
		s10.name = "Arjun";

		students.add(s1);
		students.add(s2);
		students.add(s3);
		students.add(s4);
		students.add(s5);
		students.add(s6);
		students.add(s7);
		students.add(s8);
		students.add(s9);
		students.add(s10);

		for (int i = 0; i < students.size(); i++) {
			for (int j = 0; j < students.size() - 1-i; j++) {
				if (students.get(j).id > students.get(j + 1).id) {
					Student temp = students.get(j);
					students.set(j, students.get(j + 1));
					students.set(j + 1, temp);
				}
			}
		}
		System.out.println();
		System.out.println("Printing sorted onject based on id");

		for (Student s : students) {
			System.out.println(s.id + " " + s.name);
		}

		for (int i = 0; i < students.size(); i++) {
			for (int j = 0; j < students.size() - 1-i; j++) {
				if (students.get(j).name.compareTo(students.get(j + 1).name) > 0) {
					Student temp = students.get(j);
					students.set(j, students.get(j + 1));
					students.set(j + 1, temp);
				}
			}
		}
		System.out.println();
		System.out.println("Printing sorted onject based on name");

		for (Student s : students) {
			System.out.println(s.id + " " + s.name);
		}

		ArrayList<Employee> e = new ArrayList<>();

		e.add(new Employee("Aayush", new double[] { 30000, 32000, 31000, 33000 }));
		e.add(new Employee("Rahul", new double[] { 30000, 32000, 30500 }));

		e.add(new Employee("Priya", new double[] { 30000, 32000, 31500, 32500, 33000 }));

		e.add(new Employee("Neha", new double[] { 35000, 34000, 36000 }));
		e.add(new Employee("Karan", new double[] { 35000, 34000, 35500, 36500 }));

		e.add(new Employee("Simran", new double[] { 27000, 28000, 29000, 29500 }));
		e.add(new Employee("Vikas", new double[] { 27000, 28000, 28500 }));

		e.add(new Employee("Sneha", new double[] { 31000, 33000, 32000, 34000, 35000 }));
		e.add(new Employee("Rohit", new double[] { 38000, 39000, 40000, 41000 }));


		for (int i = 0; i < e.size(); i++) {
			for (int j = 0; j < e.size() - 1-i; j++) {
				if (comp(e.get(j).salary, e.get(j + 1).salary) == true) {
					Employee temp = e.get(j);
					e.set(j, e.get(j + 1));
					e.set(j + 1, temp);
				}
			}
		}
		System.out.println();
		System.out.println("Printing custom sorted onject based on salaries of 3 months");

		for (Employee emp : e) {
		    System.out.print(emp.name + " ");
		    
		    for (double sal : emp.salary) {
		        System.out.print(sal + " ");
		    }
		    
		    System.out.println();
		}


	}
}

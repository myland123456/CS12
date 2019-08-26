import java.util.Scanner;

public class ContactList {
	
	public static void main(String[] args) {
		String name;
		String number;
		ContactNode e = null;
		Scanner input = new Scanner(System.in);
		int contactCount = 0;
		ContactNode[] contactList;
		contactList = new ContactNode[20];
		
		while (input.hasNext()) {
			name = input.nextLine();
			number = input.nextLine();
			System.out.println("You entered: " + name + ", " + number);
			e = new ContactNode(name, number);
			contactList[contactCount] = e;
			contactCount++;
		}
		
		System.out.println("\n" + 
				"CONTACT LIST");
		
		for (int i = 0; i < contactCount; i++) {
			contactList[i].printContactNode();
		}
	}
}

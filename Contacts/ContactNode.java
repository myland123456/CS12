public class ContactNode {
	
	private String contactName;
	private String contactPhoneNumber;
	private ContactNode nextNodePtr;
	
	public ContactNode(String name, String number) {
		this.contactName = name;
		this.contactPhoneNumber = number;
	}
	
	public String getName() {
		return contactName;
	}
	
	public String getPhoneNumber () {
		return contactPhoneNumber; 
	}
	
	public void insertAfter(ContactNode newEntry) {
		ContactNode tmpNext;
		
		tmpNext = this.nextNodePtr;
		this.nextNodePtr = newEntry;
		newEntry.nextNodePtr = tmpNext;
	}
	
	public ContactNode getNext() {
		return nextNodePtr;
	}
	
	public void printContactNode() {
		System.out.println("Name: " + this.contactName + "\n" + "Phone number: " + this.contactPhoneNumber + "\n");
	}
	
}

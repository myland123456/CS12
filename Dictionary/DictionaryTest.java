
public class DictionaryTest {
	
	// helper function, prints the state of the Dictionary with texts
	// easier to identify things this way
	private static void printDic(Dictionary d) {
		System.out.println("Dictionary: \n" + d.toString() + "End Dictionary\n");
	}

	public static void main(String[] args) {
		
		Dictionary dic = new Dictionary(); // constructor test
		
		System.out.println(dic.isEmpty()); // should be true
		printDic(dic); // should be empty here
		
		dic.insert("1", "Z"); 
		System.out.println(dic.isEmpty());
		printDic(dic); // should be print "1 Z"

		dic.insert("2", "Y"); 
		printDic(dic); // should be print "1 Z\n2 Y"
		
		dic.insert("3", "X");
		printDic(dic); // should be print "1 Z\n2 Y\n3 X"
		
		dic.insert("4", "W");
		System.out.println(dic.size()); // 4
		printDic(dic); // should be print "1 Z\n2 Y\n3 X\n4 W"
		
		dic.makeEmpty(); // empties the list

		printDic(dic); // should print nothing
		
		// mixed string and numbers 
		dic.insert("A", "9");
		dic.insert("8", "B");
		dic.insert("C", "7");
		dic.insert("6", "D");
		dic.insert("E", "5");
		dic.insert("4", "F");
		dic.insert("G", "3");
		dic.insert("2", "H");
		dic.insert("I", "1");
		dic.insert("0", "J");
	
		printDic(dic);
		
		dic.makeEmpty();
		
		System.out.println(dic.isEmpty()); // true 
		
		/*
		// should not be visible
		dic.findKey("1");
		*/
		
		dic.insert("null", "lol");
		printDic(dic);
		System.out.println(dic.lookup("null")); // should print "lol"
		dic.insert("lol", "null");
		/*
		dic.insert("lol", "lol"); // should throw DuplicateKeyException
		*/
		printDic(dic); // shouldn't throw any exceptions and print normally
		dic.insert(null, "null"); // should not throw DuplicateKeyException
		printDic(dic); // should print two null but with value lol and null
		
		
		dic.delete("null"); // should only delete "null, lol"
		printDic(dic);
		System.out.println(dic.isEmpty());
		/*
		dic.delete("lol"); // should throw KeyNotFoundException
		*/
		
	}

}

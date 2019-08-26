import java.io.PrintWriter;

public class Dictionary implements DictionaryInterface {

	private class Node {
		String key;
		String value;
		Node left;
		Node right;
		
		Node(String k, String v) {
			key = k;
			value = v;
			this.left = this.right = null;
		}
	}
	
	private Node root;
	private int numPairs;
	
	// findKey()
	// returns the Node containing key k in the subtree rooted at R, or returns
	// null if no such Node exists
	Node findKey (Node R, String k) {
		if(R==null || k.compareTo(R.key)==0)
			return R;
		if( k.compareTo(R.key)<0 )
			return findKey(R.left, k);
		else // strcmp(k, R.key)>0
			return findKey(R.right, k);
	}
	
	// findParent()
	// returns the parent of N in the subtree rooted at R, or returns null 
	// if N is equal to R. (pre: R != null)
	Node findParent (Node N, Node R) {
		Node P = null;
		if (N != R) {
			P = R;
		}
		while (P.left != N && P.right != N) {
			if (N.key.compareTo(P.key) < 0) {
				P = P.left;
			}
			else {
				P = P.right;
			}
		}
		return P;
	}
	
	// findLeftmost()
	// returns the leftmost Node in the subtree rooted at R, or null if R is null.
	Node findLeftmost(Node R){
		Node L = R;
		if( L!=null ) for( ; L.left!=null; L=L.left) ;
		return L;
	}

	String result = "";
	// printInOrder()
	// prints the (key, value) pairs belonging to the subtree rooted at R in order
	// of increasing keys to file pointed to by out.
	String printInOrder(Node R){
		if (R != null) {
			printInOrder(R.left);
			result = result + R.key + " " + R.value + "\n";
			printInOrder(R.right);
		}
		
		return result;
	}

	// deleteAll()
	// deletes all Nodes in the subtree rooted at N.
	void deleteAll(Node N){
		if (N != null) {
			N = null;
		}
	}
	
	// public functions -----------------------------------------------------------

	// newDictionary()
	// constructor for the Dictionary type
	public Dictionary() {
		this.root = null;
		this.numPairs = 0;
	}
	
	// freeDictionary()
	// destructor for the Dictionary type
	// java doesn't need this
	
	// isEmpty()
	// returns 1 (true) if D is empty, 0 (false) otherwise
	// pre: none
	@Override
	public boolean isEmpty() {
		return (numPairs == 0);
	}

	// size()
	// returns the number of (key, value) pairs in D
	// pre: none
	@Override
	public int size() {
		return numPairs;
	}

	// lookup()
	// returns the value v such that (k, v) is in D, or returns null if no 
	// such value v exists.
	// pre: none
	@Override
	public String lookup(String key) {
		Node N;
		N = findKey(this.root, key);
		return ( N==null ? null : N.value );
	}

	// insert()
	// inserts new (key,value) pair into D
	// pre: lookup(D, k)==NULL
	@Override
	public void insert(String key, String value) throws DuplicateKeyException {
		Node N, A, B;
		if( findKey(this.root, key)!=null ){
			throw new DuplicateKeyException("Dictionary Error: cannot insert() duplicate key: " + key);
		}
		N = new Node(key, value);
		B = null;
		A = this.root;
		while( A!=null ){
			B = A;
			if( key.compareTo(A.key)<0 ) A = A.left;
			else A = A.right;
		}
		if( B==null ) this.root = N;
		else if( key.compareTo(B.key)<0 ) B.left = N;
		else B.right = N;
		this.numPairs++;
	}

	// delete()
	// deletes pair with the key k
	// pre: lookup(D, k)!=NULL
	@Override
	public void delete(String key) throws KeyNotFoundException {
		Node N, P, S;
		   //if( this==null ){
		      //fprintf(stderr, 
		        // "Dictionary Error: calling delete() on null Dictionary reference\n");
		      //exit(EXIT_FAILURE);
		   //}
		   N = findKey(this.root, key);
		   if( N==null ){
		      throw new KeyNotFoundException("Dictionary Error: cannot delete() non-existent key: " + key);
		   }
		   if( N.left==null && N.right==null ){  // case 1 (no children)
		      if( N==this.root ){
		         this.root = null;
		      }else{
		         P = findParent(N, this.root);
		         if( P.right==N ) P.right = null;
		         else P.left = null;
		      }
		   }else if( N.right==null ){  // case 2 (left but no right child)
		      if( N==this.root ){
		         this.root = N.left;
		      }else{
		         P = findParent(N, this.root);
		         if( P.right==N ) P.right = N.left;
		         else P.left = N.left;
		      }
		   }else if( N.left==null ){  // case 2 (right but no left child)
		      if( N==this.root ){
		         this.root = N.right;
		      }else{
		         P = findParent(N, this.root);
		         if( P.right==N ) P.right = N.right;
		         else P.left = N.right;
		      }
		   }else{                     // case3: (two children: N.left!=null && N.right!=null)
		      S = findLeftmost(N.right);
		      N.key = S.key;
		      N.value = S.value;
		      P = findParent(S, N);
		      if( P.right==S ) P.right = S.right;
		      else P.left = S.right;
		   }
		   this.numPairs--;
	}

	// makeEmpty()
	// re-sets D to the empty state.
	// pre: none
	@Override
	public void makeEmpty() {
		deleteAll(this.root);
		this.root = null;
		this.numPairs = 0;
	}

	// printDictionary()
	// pre: none
	// prints a text representation of D to the file pointed to by out
	public String toString() {
		String temp = new String(printInOrder(this.root));
		result = "";
		return temp;
	}
}

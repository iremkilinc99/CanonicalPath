
public class SLLDriver {
	public static void main(String[] args) {
		SinglyLinkedList<String> sll = new SinglyLinkedList<>();
		sll.addFirst("A");
		sll.addFirst("B");
		sll.addLast("T");
		sll.addFirst("D");
		System.out.println(sll);
		sll.removeFirst();
		System.out.println("After removal of first:\n"+sll);
		
	}
}

package programa;

public class OrderedDoubleLinkedList<T> extends DoubleLinkedList<T> implements OrderedListADT<T> {
	
	@Override
	@SuppressWarnings("unchecked")
	public void add(T elem) {
	    if (!(elem instanceof Comparable))
	        throw new ClassCastException("Element must be Comparable");

	    Node<T> nodoBerria = new Node<T>(elem);
	    Comparable<T> elemento = (Comparable<T>) elem;

	    if (isEmpty()) {
	        last = nodoBerria;
	        count++;
	        return;
	    }
	    Node<T> current = last;
	    while (current.prev != null)
	        current = current.prev;

	    if (elemento.compareTo(current.data) <= 0) {
	        nodoBerria.next = current;
	        current.prev = nodoBerria;
	        count++;
	        return;
	    }

	    while (current.next != null && elemento.compareTo(current.next.data) > 0) {
	        current = current.next;
	    }

	    nodoBerria.next = current.next;
	    nodoBerria.prev = current;
	    current.next = nodoBerria;

	    if (nodoBerria.next != null)
	        nodoBerria.next.prev = nodoBerria;
	    else
	        last = nodoBerria;

	    count++;
	}


	@Override
	@SuppressWarnings("unchecked")
	public OrderedDoubleLinkedList<T> intersection(OrderedDoubleLinkedList<T> zerrenda) {
	    OrderedDoubleLinkedList<T> emaitza = new OrderedDoubleLinkedList<>();

	    if (isEmpty() || zerrenda.isEmpty())
	        return emaitza;

	    Node<T> n1 = last;
	    while (n1.prev != null)
	        n1 = n1.prev;

	    Node<T> n2 = zerrenda.last;
	    while (n2 != null && n2.prev != null)
	        n2 = n2.prev;

	    while (n1 != null && n2 != null) {
	        Comparable<T> e1 = (Comparable<T>) n1.data;
	        T e2 = n2.data;

	        int cmp = e1.compareTo(e2);

	        if (cmp == 0) { 
	            emaitza.add(n1.data);
	            n1 = n1.next;
	            n2 = n2.next;
	        } else if (cmp < 0) {
	            n1 = n1.next;
	        } else {
	            n2 = n2.next;
	        }
	    }

	    return emaitza;
	}



}

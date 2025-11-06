package programa;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements ListADT<T> {

	// Atributuak
	protected Node<T> last;  // azkenengoaren erreferentzia
	protected String deskr;  // deskribapena
	protected int count;

	public DoubleLinkedList() {
		last = null;
		deskr = "";
		count = 0;
	}
	
	public void setDeskr(String ize) {
	  deskr = ize;
	}

	public String getDeskr() {
	  return deskr;
	}

	public void setLast(Node<T> elem) {
		this.last= elem;
	}
	public void addToRear(T elem) {
	    Node<T> nodoBerria = new Node<T>(elem);

	    if (isEmpty()) {
	        last = nodoBerria;
	    } else {
	        last.next = nodoBerria;
	        nodoBerria.prev = last;
	        last = nodoBerria;
	    }
	    count++;
	}

	
		public T removeFirst() {
		    if (isEmpty()) return null;

		    Node<T> current = last;
		    while (current.prev != null)
		        current = current.prev;

		    T data = current.data;
		    if (current.next != null)
		        current.next.prev = null;
		    else
		        last = null;

		    count--;
		    return data;
		}


		public T removeLast() {
		    if (isEmpty()) return null;

		    T data = last.data;

		    if (last.prev != null) {
		        last = last.prev;
		        last.next = null;
		    } else {
		        last = null;
		    }

		    count--;
		    return data;
		}




	public T remove(T elem) {
	    if (isEmpty()) return null;

	    Node<T> current = last;
	    while (current.prev != null)
	        current = current.prev; 

	    while (current != null) {
	        if (current.data.equals(elem)) {
	            T data = current.data;
	            if (current.prev != null)
	                current.prev.next = current.next;
	            if (current.next != null)
	                current.next.prev = current.prev;
	            if (current == last)
	                last = current.prev;

	            count--;
	            return data;
	        }
	        current = current.next;
	    }
	    return null;
	}

		
	public void removeAll(T elem) {
	    if (isEmpty()) return;

	    Node<T> current = last;
	    while (current.prev != null)
	        current = current.prev;

	    while (current != null) {
	        if (current.data.equals(elem)) {
	            if (current.prev != null)
	                current.prev.next = current.next;
	            if (current.next != null)
	                current.next.prev = current.prev;
	            if (current == last)
	                last = current.prev;
	            count--;
	        }
	        current = current.next;
	    }
	}

   

	public T first() {
		// listako lehen elementua ematen du
		   // KODEA OSATU ETA KOSTUA KALKULATU
		if (isEmpty()) return null;
		Node<T> oraingoa = last;
		while (oraingoa.prev != null) {
			oraingoa = oraingoa.prev;
		}
		return oraingoa.data;
	
	}

	public T last() {
		// listako azken elementua ematen du
		   // KODEA OSATU ETA KOSTUA KALKULATU
		return last.data;
		}

	public DoubleLinkedList<T> clone() {
	    DoubleLinkedList<T> copy = new DoubleLinkedList<>();
	    Node<T> current = last;
	    while (current != null && current.prev != null)
	        current = current.prev; // ir al primero
	    
	    while (current != null) {
	        copy.addToRear(current.data);
	        current = current.next;
	    }
	    return copy;
	}


	public boolean contains(T elem) {
	    if (isEmpty()) return false;
	    Node<T> current = last;
	    while (current != null) {
	        if (current.data.equals(elem)) return true;
	        current = current.prev;
	    }
	    return false;
	}

	public T find(T elem) {
	    if (isEmpty()) return null;
	    Node<T> current = last;
	    while (current != null) {
	        if (current.data.equals(elem)) return current.data;
	        current = current.prev;
	    }
	    return null;
	}


	public boolean isEmpty() {
	    return last == null;
	}

	
	public int size() { 
	// KODEA OSATU ETA KOSTUA KALKULATU
	return count;
	}
	
	   public Iterator<T> iterator() { return new ListIterator(); } 

	   private class ListIterator implements Iterator<T> { 
		    private Node<T> current = firstNode(); 

		    private Node<T> firstNode() {
		        Node<T> temp = last;
		        while (temp != null && temp.prev != null)
		            temp = temp.prev;
		        return temp;
		    }

		    public boolean hasNext() { return current != null; }

		    public T next() {
		        if (!hasNext()) throw new NoSuchElementException();
		        T elem = current.data;
		        current = current.next;
		        return elem;
		    }
		}

	    // private class
		
		
		public void adabegiakInprimatu() {
			System.out.println(this.toString());
		}

		
		@Override
		public String toString() {
			String result = new String();
			Iterator<T> it = iterator();
			while (it.hasNext()) {
				T elem = it.next();
				result = result + "[" + elem.toString() + "] \n";
			}	
			return "DoubleLinkedList " + result + "]";
		}

}

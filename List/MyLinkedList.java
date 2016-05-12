package advance;

public class MyLinkedList<E> extends MyAbstractList<E> {
	private Node<E> head;
	private Node<E> tail;
	
	public MyLinkedList() {
		// TODO Auto-generated constructor stub
	}
	public MyLinkedList(E[] objects){
		for(int i=0;i<objects.length;i++)
			add(objects[i]);
	}
	
	public void addFirst(E e){
		Node<E> newNode=new Node<>(e);
		newNode.next=head;
		head=newNode;
		size++;
		if(tail==null)
			tail=head;
	}
	public void addLast(E e){
		if(size==0){
			head=new Node<E>(e);
			tail=head;
			size++;
		}
		else{
			Node<E> newNode=new Node<>(e);
			tail.next=new Node<E>(e);
			tail=newNode;
			size++;
			
			
		}
		add(e);
			
	}
	public E getFirst(){
		if(size==0)
			return null;
		return head.element;
	}
	public E getLast(){
		if(size==0)
			return null;
		return tail.element;
	}
	
	public E removeFirst(){
		if(size==0)
			return null;
		E e=head.element;
		head=head.next;
		size--;
		return e;
	}
	public E removeLast(){
		if(size==0)
			return null;
		else{
			E e=tail.element;
			Node<E> current=head;
			for(int i=0;i<size-2;i++){
			current=current.next;	
			}
			tail=current;
			current.next=null;
			size--;
			return e;	
		}
		
	}

	@Override
	public void add(int index, E e) {
		// TODO Auto-generated method stub
          if(index==0){
        	  addFirst(e);
          }
          else if(index>=size){
        	  addLast(e);;
          }
          else {
			Node<E> newNode=new Node<>(e);
			Node<E> current=head;
			for(int i=0;i<index;i++){
				current=current.next;
			}
			newNode.next=current.next;
			current.next=newNode;
			size++;
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
        head=null;
        tail=null;
        size=0;
	}

	@Override
	public boolean contains(E e) {
		// TODO Auto-generated method stub
		Node<E> current=head;
		for(int i=0;i<size;i++){
			if(current.element.equals(e))
				return true;
			current=current.next;
		}
		return false;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		if(index==0)
			return getFirst();
		else if(index==size-1)
			return getLast();
		else{
		Node<E> current=head;
		for(int i=0;i<index;i++){
          	current=current.next;
		}
		return current.element;
		}
	}

	@Override
	public int indexOf(E e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(E e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E e) {
		// TODO Auto-generated method stub
		return null;
	}

}
class Node<E>{
	E element;
	Node<E> next;
	public Node(E e) {
		// TODO Auto-generated constructor stub
		element=e;
	}
}
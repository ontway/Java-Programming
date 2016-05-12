package advance;

public abstract class MyAbstractList<E> implements MyList<E> {
	
	protected int size=0;
	protected MyAbstractList() {
		// TODO Auto-generated constructor stub
	}
	protected MyAbstractList(E[] objects){
		for(int i=0;i<objects.length;i++)
			add(objects[i]);
	}
	

	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
        add(size,e);
	}

	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	

	@Override
	public boolean remove(E e) {
		// TODO Auto-generated method stub
		if(indexOf(e)>=0){
			remove(indexOf(e));
			return true;
		}
		else
		return false;
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}



}

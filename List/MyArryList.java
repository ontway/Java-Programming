package advance;

import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class MyArryList<E> extends MyAbstractList<E> {
    public static final int INITIAL_CAPACITY=16;
    private E[] data=(E[])new Object[INITIAL_CAPACITY];
    
    public MyArryList() {
		// TODO Auto-generated constructor stub
	}
    public MyArryList(E[] objects){
    	for(int i=0;i<objects.length;i++)
    		add(objects[i]);
    }
	@Override
	public void add(int index, E e) {
		// TODO Auto-generated method stub
        
        ensureCapacity();
        for(int i=size-1;i>=index;i--){
        	data[i+1]=data[i];
        	
        }
        data[index]=e;
    	size++;
        
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
       size=0;
       data=(E[])new Object[INITIAL_CAPACITY];
	}

	@Override
	public boolean contains(E e) {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++){
			if(e.equals(data[i]))
				return true;
		}
		return false;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return data[index];
	}

	@Override
	public int indexOf(E e) {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++){
			if(e.equals(data[i]))
				return i;
		}
			
		return -1;
	}

	@Override
	public int lastIndexOf(E e) {
		// TODO Auto-generated method stub
		for(int i=size-1;i>=0;i--){
			if(e.equals(data[i]))
				return i;
		}
			
		return -1;
		
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		E e=data[index];
		for(int i=index;i<size-1;i++){
			data[i]=data[i+1];
			
		}
		data[size-1]=null;
		size--;
			
		return e;
	}

	@Override
	public E set(int index, E e) {
		// TODO Auto-generated method stub
		E old=data[index];
		data[index]=e;
		return old;
	}
	private void ensureCapacity(){
		if(size>=data.length){
			E[] newData=(E[])new Object[size*2+1];
			System.arraycopy(data, 0, newData, 0, size);
			data=newData;
		}
	}
	public void trimToSize(){
		if(size!=data.length){
			E[] newData=(E[])new Object[size];
			System.arraycopy(data, 0, newData, 0, size);
			data=newData;
			
		}
		
	}
	public String toString(){
		if(size!=data.length){
			E[] newData=(E[])new Object[size];
			System.arraycopy(data, 0, newData, 0, size);
			return Arrays.toString(newData);
		}
		else
			return Arrays.toString(data);
	}

}

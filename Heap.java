import java.util.ArrayList;

public class Heap <E extends Comparable<E>>{
	private ArrayList<E> list=new ArrayList<>();
	public Heap(){
		
	}
	public Heap(E[] objects){
		for(int i=0;i<objects.length;i++){
			add(objects[i]);
		}
	}
	public void add(E newObject){
		list.add(newObject);
		int currentIndex=list.size()-1;
		while(currentIndex>0){
			int parentIndex=(currentIndex-1)/2;
			if(list.get(parentIndex).compareTo(newObject)<0){
			
				list.set(currentIndex, list.get(parentIndex));
				list.set(parentIndex, newObject);
				
			}
			else break;
			currentIndex=parentIndex;
		}
	}
	public E remove(){
		if(list.size()==0) 
			return null;
		E rootObject=list.get(0);
		int currentIndex=list.size()-1;
		list.set(0, list.get(currentIndex));
		list.remove(currentIndex);
		currentIndex=0;
		
		
		while(currentIndex<list.size()){
			int leftChild=2*currentIndex+1;
			int rightChild=2*currentIndex+2;
			if(leftChild>=list.size())
				break;
			int maxIndex=leftChild;
			if(rightChild<list.size()&&list.get(rightChild).compareTo(list.get(leftChild))>0)
				maxIndex=rightChild;
			if(list.get(currentIndex).compareTo(list.get(maxIndex))<0){
				E t=list.get(currentIndex);
				list.set(currentIndex, list.get(maxIndex));
				list.set(maxIndex, t);
				currentIndex=maxIndex;
			}
			else
				break;
		}
		return rootObject;
		
		
	}
	public int getSize(){
		return list.size();
	}

}

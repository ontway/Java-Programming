package advance;

import java.util.Iterator;

public abstract class AbstractTree<E extends Comparable<E>> implements Tree<E> {

	
	@Override
	public void inorder() {
		// TODO Auto-generated method stub

	}

	@Override
	public void preorder() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postorder() {
		// TODO Auto-generated method stub

	}

	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return getSize()==0;
		
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	

}

package advance;

import java.util.ArrayList;
import java.util.Iterator;

public class BinaryTree<E extends Comparable<E>> extends AbstractTree<E> {
	protected TreeNode<E> root;
	protected int size=0;
	
	public BinaryTree() {
		// TODO Auto-generated constructor stub
	}
	public BinaryTree(E[] objects){
		for(int i=0;i<objects.length;i++){
			insert(objects[i]);
		}
	}

	@Override
	public boolean search(E e) {
		// TODO Auto-generated method stub
		TreeNode<E> current=root;
		if(root==null)
			return false;
		while(current!=null){
		if(current.element.compareTo(e)>0)
			current=current.left;
		else if(current.element.compareTo(e)<0)
			current=current.right;
		else 
			return true;
		}
		return false;
	}

	@Override
	public boolean insert(E e) {
		// TODO Auto-generated method stub
		TreeNode<E> current=root;
		TreeNode<E> parent=null;
		if(root==null){
			root=new TreeNode<E>(e);
			return true;
		}
		while(current!=null){
			if(current.element.equals(e)){
				return false;
			}
			else if(current.element.compareTo(e)>0){
				parent=current;
				current=current.left;
			}
			else {
				parent=current;
				current=current.right;
			}
		}
		if(parent.element.compareTo(e)>0)
	      	parent.left=new TreeNode<E>(e);
		else
			parent.right=new TreeNode<E>(e);
		size++;
		
		return true;
	}
	protected TreeNode<E> createNewNode(E e){
		return new TreeNode<E>(e);
	}
	public void inorder(){
		inorder(root);
	}
	protected void inorder(TreeNode<E> root){
		if(root==null)
			return;
		inorder(root.left);
		System.out.print(root.element+" ");
		inorder(root.right);
	}
	

	@Override
	public boolean delete(E e) {
		// TODO Auto-generated method stub
		if(!search(e)||root==null)
			return false;
		TreeNode<E> current=root;
		TreeNode<E> parent=null;
		while(current!=null){
			if(current.element.compareTo(e)>0){
				parent=current;
				current=current.left;
			}
			else if(current.element.compareTo(e)<0){
				parent=current;
				current=current.right;
			}
			else break;
		}
		if(current.left==null){
			if(parent==null){
				root=current.right;
				
			}
			else {if(parent.element.compareTo(current.element)>0)
				      parent.left=current.right;
			      else
				      parent.right=current.right;
			}
		}
		else{
			TreeNode<E> parentOfleftMost=current;
			TreeNode<E> leftMost=current.left;
			while(leftMost.right!=null){
				parentOfleftMost=parentOfleftMost.left;
				leftMost=leftMost.right;
				
			}
			E e1=leftMost.element;
			delete(leftMost.element);
			current.element=e1;
//			if(parent.element.compareTo(current.element)>0)
//			    parent.left=parentOfleftMost.left;
//			else
//			    parent.right=parentOfleftMost.left;
		}  
		
		size--;
		return true;
	}
	
	

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
          root=null;
          size=0;
	}
	public Iterator<E> iterator(){
		return new InorderIterator();
	}
	class InorderIterator implements java.util.Iterator<E>{
		
	    private ArrayList<E> list=new ArrayList<>();
	    private int current=0;
	    
	    
	    public InorderIterator() {
			// TODO Auto-generated constructor stub
	    	inorder(root);
		}
	    private void inorder(TreeNode<E> root){
	    	if(root==null)
	    		return;
	    	inorder(root.left);
	    	list.add(root.element);
	    	inorder(root.right);
	    }
	    
	    
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(current<list.size())
			  return true;
			return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			return list.get(current++);
			
		}
		
	}
	public static class TreeNode<E extends Comparable<E>>{
		E element;
		TreeNode<E> left;
		TreeNode<E> right;
		
		public TreeNode(E element) {
			// TODO Auto-generated constructor stub
			this.element=element;
		}
	}

}


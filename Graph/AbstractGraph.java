package advance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;



public  abstract class AbstractGraph<V> implements Graph<V> {
	
	protected List<V> vertices;
	protected List<List<Integer>> neighbors;
	//边用二维数组表示，定点用数组表示
	protected AbstractGraph(int[][] edges,V[] vetices){
		this.vertices=new ArrayList<>(Arrays.asList(vetices));
		creatAdjacencyLists(edges,vetices.length);
	}
	//边用edge对象的线性表表示，顶点用线性表表示
	protected AbstractGraph(List<Edge> edges,List<V> vertices){
		this.vertices=vertices;
		creatAdjacencyLists(edges, vertices.size());
	}
	//边用edges对象的线性表表示，顶点用数字表示
	protected AbstractGraph(List<Edge> edges,int numberOfVertices){
		this.vertices=new ArrayList<>();
		for(int i=0;i<numberOfVertices;i++)
			this.vertices.add((V)new Integer(i));
		creatAdjacencyLists(edges, numberOfVertices);
	}
	//边用二维数组表示，顶点用数字表示
	protected AbstractGraph(int[][] edges,int numberOfVertices){
		this.vertices=new ArrayList<>();
		for(int i=0;i<numberOfVertices;i++)
			this.vertices.add((V)new Integer(i));
        
		
		
		creatAdjacencyLists(edges,numberOfVertices);
	}
	private void creatAdjacencyLists(int[][] edges,int numberOfVertices){
		neighbors=new ArrayList<>();
		for(int i=0;i<numberOfVertices;i++)
		    neighbors.add(new ArrayList<>());
		for(int i=0;i<edges.length;i++){
			
			neighbors.get(edges[i][0]).add(edges[i][1]);
			
		}
	}
	
	private void creatAdjacencyLists(List<Edge> edges,int numberOfVertices){
		neighbors=new ArrayList<>();
		for(int i=0;i<numberOfVertices;i++)
		    neighbors.add(new ArrayList<>());
		for(int i=0;i<edges.size();i++)
			neighbors.get(edges.get(i).u).add(edges.get(i).v);
		
	}
	public static class Edge{
		public int u;
		public int v;
		public Edge(int u,int v){
			this.u=u;
			this.v=v;
		}
	}

	@Override
	public int getSize() {
	 	// TODO Auto-generated method stub
		return vertices.size();
	}

	@Override
	public List<V> getVertices() {
		// TODO Auto-generated method stub
		
		return vertices;
	}
	@Override
	public V getVertice(int index){
		
		return vertices.get(index);
	}

	@Override
	public int getIndex(V v) {
		// TODO Auto-generated method stub
		return vertices.indexOf(v);
	}

	@Override
	public List<Integer> getNeighbors(int index) {
		// TODO Auto-generated method stub
		return neighbors.get(index);
	}

	@Override
	public int getDegree(int v) {
		// TODO Auto-generated method stub
		return neighbors.get(v).size();
	}

	@Override
	public int[][] getAdjacencyMatrix() {
		// TODO Auto-generated method stub
		int[][] adjacencyMatrix=new int[vertices.size()][vertices.size()];
		for(int i=0;i<neighbors.size();i++){
			for(int j=0;j<neighbors.get(i).size();j++){
				adjacencyMatrix[i][neighbors.get(i).get(j)]=1;
			}
		}
		return adjacencyMatrix;
	}

	@Override
	public void printAdjacencyMartix() {
		// TODO Auto-generated method stub
		int[][] adjacencyMatrix=getAdjacencyMatrix();
		for(int i=0;i<adjacencyMatrix.length;i++){
			for(int j=0;j<adjacencyMatrix[i].length;j++)
				System.out.print(adjacencyMatrix[i][j]+" ");
			System.out.println();
			}

	}

	@Override
	public void printEdges() {
		// TODO Auto-generated method stub
      for(int i=0;i<neighbors.size();i++){
    	  for(int j=0;j<neighbors.get(i).size();j++){
    		  System.out.print("("+i+","+neighbors.get(i).get(j)+")");
    	  }
    	  System.out.println();
      }
	}

	@Override
	public Tree dfs(int v) {
		// TODO Auto-generated method stub
		List<Integer> searchOrders=new ArrayList<>();
		int[] parent=new int[vertices.size()];
		Arrays.fill(parent, -1);
		boolean[] isVisited=new boolean[vertices.size()];
		dfs(v,parent,searchOrders,isVisited);
		
		return new Tree(v, parent, searchOrders);
	}
	
	private void dfs(int v,int[] parent,List<Integer> searchOrders,boolean[] isVisited){
		searchOrders.add(v);
		isVisited[v]=true;
		for(int i:neighbors.get(v)){
			if(!isVisited[v]){
				parent[i]=v;
				dfs(i,parent,searchOrders,isVisited);
			}
		}
	}
	

//	@Override
//	public Tree bfs(int v) {
//		// TODO Auto-generated method stub
//		List<Integer> searchOrders=new ArrayList<>();
//		int[] parent=new int[vertices.size()];
//		Arrays.fill(parent, -1);
//		LinkedList<Integer> queue=new LinkedList<>();
//		boolean[] isVisited=new boolean[vertices.size()];
//		
//		bfs(v,parent,searchOrders,isVisited,queue);
//        return new Tree(v, parent, searchOrders);
//	}
//	private void bfs(int v,int[]parent,List<Integer> searchOrders,boolean[] isVisited,LinkedList<Integer> queue){
//		queue.offer(v);
//		searchOrders.add(v);
//		isVisited[v]=true;
//		for(int i:neighbors.get(v)){
//			if(!isVisited[i]){
//				
//			}
//		}
//	}
	public Tree bfs(int v) {
		List<Integer> searchOrders=new ArrayList<>();
		int[] parent=new int[vertices.size()];
		Arrays.fill(parent, -1);
		LinkedList<Integer> queue=new LinkedList<>();
		boolean[] isVisited=new boolean[vertices.size()];
		queue.offer(v);
		isVisited[v]=true;
		while(queue.size()!=0){
			int u=queue.poll();
			searchOrders.add(u);
			for(int i:neighbors.get(u)){
				if(!isVisited[i]){
					queue.offer(i);
					parent[i]=u;
					isVisited[i]=true;
				}
			}
		}
		return new Tree(v, parent, searchOrders);
			
	}
	
	
	public class Tree{
		private int root;
		private int[] parent;
		private List<Integer> searchOrders;
		
		public Tree(int root,int[] parent,List<Integer> searchOrders){
			this.root=root;
			this.parent=parent;
			this.searchOrders=searchOrders;
		}
		public Tree(int root,int[] parent){
			this.root=root;
			this.parent=parent;
		}
		public int getRoot(){
			return root;
		}
		public int getParent(int v){
			return parent[v];
		}
		public List<Integer> getSearchOrders(){
			return searchOrders;
		}
		public int getNumberOfVerticesFound(){
			return searchOrders.size();
			
		}
		public List<V> getPath(int index){
			ArrayList<V> list=new ArrayList<>();
			do{
				list.add(vertices.get(index));
				index=parent[index];
			}while(index!=-1);
			
			return list;
			
		}
		public void printPath(int index){
			List<V> list=getPath(index);
			for(int i=list.size()-1;i>=0;i--){
				System.out.print(list.get(i)+" ");
			}
			
		}
		public void printTree(){
			for(int i=0;i<parent.length;i++){
				if(parent[i]!=-1){
					System.out.print("("+vertices.get(parent[i])+","+vertices.get(i)+")");
				}
			}
		}
		
		
	}
	
}

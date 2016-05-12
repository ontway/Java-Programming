package advance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class WeightedGraph<V> extends AbstractGraph<V> {
	     private List<PriorityQueue<WeightedEdge>> queues;
	     
	     public WeightedGraph(int[][] edges,V[] vertices){
	    	 super(edges, vertices);
	    	 creatQueue(edges,vertices.length);
	    	 
	     }
	     public WeightedGraph(List<WeightedEdge> edges,List<V> vertices){
	    	 super((List)edges,vertices);
	    	 creatQueue(edges,vertices.size());
	    	 
	     }
	     public WeightedGraph(int[][] edges,int numberOfVertices){
	    	 super(edges, numberOfVertices);
	    	 creatQueue(edges,numberOfVertices);
	     }
	     public WeightedGraph(List<WeightedEdge> edges,int numberOfVertices){
	    	 super((List)edges,numberOfVertices);
	    	 creatQueue(edges,numberOfVertices);
	     }
	     private void creatQueue(List<WeightedEdge> edges,int numberOfVertices){
	    	 queues=new ArrayList<PriorityQueue<WeightedEdge>>();
	    	 for(int i=0;i<numberOfVertices;i++)
	    		 queues.add(new PriorityQueue<WeightedEdge>());
	    	 for(int i=0;i<edges.size();i++){
	    		 queues.get(edges.get(i).u).offer(edges.get(i));
	    	 }
	    	 
	     }
	     private void creatQueue(int[][] edges,int numberOfVertices){
	    	 queues=new ArrayList<PriorityQueue<WeightedEdge>>();
	    	 for(int i=0;i<numberOfVertices;i++)
	    		 queues.add(new PriorityQueue<WeightedEdge>());
	    	 for(int i=0;i<edges.length;i++){
	    		 
	    		 queues.get(edges[i][0]).offer(new WeightedEdge(edges[i][0], edges[i][1], edges[i][2]));

	    	 }
	    	 
	     }
	     public void printWeightedEdges(){
	    	 for(int i=0;i<queues.size();i++){
	    		 System.out.print("顶点"+i+":");
	    		 for(WeightedEdge edge:queues.get(i)){
	    			 System.out.println("("+edge.u+","+edge.v+","+edge.weight+")");
	    		 }
	    		 System.out.println();
	    	 }
	    	  
	     }
	     public MST getMinimumSpanningTree(){
	    	 
	    	 return getMinimumSpanningTree(0);
	     }
         public MST getMinimumSpanningTree(int startingIndex){
        	 List<Integer> T=new ArrayList<>();
        	 T.add(startingIndex);
        	 int numberOfVertices=vertices.size();
        	 int[] parent=new int[numberOfVertices];
        	 Arrays.fill(parent, -1);
        	 int totalWeight=0;
        	// List<PriorityQueue<WeightedEdge>> queues=deepClone(this.queues);
        	 
        	 while(T.size()<numberOfVertices){
        		 int v=-1;
        		 double smallestWeight=Double.MAX_VALUE;
        		 //遍历已在树中的所有顶点，找到一个最小边，将它添加到树中
        		 for(int u:T){
        			 //
        			 for(WeightedEdge edge:queues.get(u)){
        				 if(!T.contains(edge.v)){
        					 if(edge.weight<smallestWeight){
        						 smallestWeight=edge.weight;
        						 parent[edge.v]=u;
        						 v=edge.v;
        						 
        					 }
        				 }
        			 }
      	     	 }
        		 T.add(v);
        		 totalWeight+=smallestWeight;
        	 }
        	 
	    	 return new MST(startingIndex, parent, T, totalWeight);
	     }
//         private List<PriorityQueue<WeightedEdge>> deepClone(List<PriorityQueue<WeightedEdge>> queues){
//        	 
//        	 
//        	 return null;
//         }
         
         

	     public class MST extends Tree{
	    	 private int totalWeight;

			 public MST(int root, int[] parent, List<Integer> searchOrders,int totalWeight) {
				super(root, parent, searchOrders);
				this.totalWeight=totalWeight;
				// TODO Auto-generated constructor stub
			}
			 public int getTotalWeight(){
				 return totalWeight;
			 }
	    	 
	     }
	     public ShortestPathTree getShortestPath(int sourceIndex){
	    	 
	    	 List<Integer> T=new ArrayList<>();
	    	 T.add(sourceIndex);
	    	 int numberOfVertices=vertices.size();
	    	 int[] parent=new int[numberOfVertices];
	    	 parent[sourceIndex]=-1;
	    	 int[] costs=new int[numberOfVertices];
	    	 Arrays.fill(costs, Integer.MAX_VALUE);
	    	 costs[sourceIndex]=0;
	    	 
	    	 while(T.size()<numberOfVertices){
	    		 int v=-1;
	    		 int smallestCost=Integer.MAX_VALUE;
	    	    //两层循环可以找到一个加入树中的点
	    		 for(int i=0;i<numberOfVertices;i++){
	    			 if(costs[i]<Integer.MAX_VALUE){
	    				 for(WeightedEdge edge:queues.get(i)){
	    					 if(!T.contains(edge.v)){
	    						 if((costs[i]+edge.weight)<smallestCost){
	    							v=edge.v;
	    							smallestCost=costs[i]+edge.weight;
	    							parent[v]=i;
	    						 }
	    					 }
	    				 }
	    			 }
	    			 
	    		 }
	    	
	    		 T.add(v);
				 costs[v]=smallestCost;
				 
	    	 }
	    	 
	    	 return new ShortestPathTree(sourceIndex, parent, T, costs);
	    	 
	     }
	     public class ShortestPathTree extends Tree{
	    	 private int[] costs;
	    	 public ShortestPathTree(int root, int[] parent, List<Integer> searchOrders,int[] costs) {
				super(root, parent, searchOrders);
				this.costs=costs;
				// TODO Auto-generated constructor stub
			}
	    	 public int getCost(int v){
	    		 return costs[v];
	    	 }
	    	 public void printAllPaths(){
	    		 for(int i=0;i<costs.length;i++){
	    			 printPath(i);
	    			 System.out.println("cost"+costs[i]);
	    		 }
	    	 }
			
	    	 
	     }
	     
	     public List<PriorityQueue<WeightedEdge>> getWeightedEdges(){
	    	 return queues;
	     }
	     
	     public class WeightedEdge extends AbstractGraph.Edge implements Comparable<WeightedEdge>{
	    	 public int weight;
	    	 public WeightedEdge(int u,int v,int weight){
	    		 super(u, v);
	    		 this.weight=weight;
	    	 }
	    	 public int compareTo(WeightedEdge edge){
	    		 if(weight>edge.weight)
	    			 return 1;
	    		 else if(weight==edge.weight)
	    			 return 0;
	    		 else {
					return -1;
				}
	    	 }
	     }

}

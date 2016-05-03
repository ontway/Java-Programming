
import java.util.ArrayList;
import java.util.Arrays;



public class BucketSortt {
	

	public static void main(String[] args)  {
	
		int[] a={3,6,1,9,2,5,4,8};
		bucketSort(a);
		System.out.println(Arrays.toString(a));
		
		
		
	
	}
	public  static void bucketSort(int[] list){
	
		ArrayList<Integer>[] buckets=(ArrayList<Integer>[])new ArrayList[20];
		for(int i=0;i<list.length;i++){
			int key=list[i];
			if(buckets[key]==null)
				buckets[key]=new java.util.ArrayList<Integer>();
			buckets[key].add(key);
		}
		int index=0;
		for(int i=0;i<buckets.length;i++){
			if(buckets[i]!=null){
				for(int k=0;k<buckets[i].size();k++)
					list[index++]=buckets[i].get(k);
				
			}
			
		}
		
	}
	
}

import java.util.*;
public class HuaweiQishui{
	   
	public static int numberOfWater(int voidNum){
	   
	   if(voidNum<=1) return 0;
       if(voidNum==2) return 1;
       else return (voidNum/3)+numberOfWater(voidNum/3+voidNum%3);
	}
	
	public static void main(String args[]){
		 Scanner input=new Scanner(System.in);
		 int[] nums=new int[100];
		 int i=0;
		do{
		 
			 nums[i]=input.nextInt();
			 i++;
		 }while(nums[i-1]!=0);
		//System.out.println();
		 input.close();
		 int j=0;
		 while(nums[j]!=0){
			 
			 System.out.println(numberOfWater(nums[j]));
			 j++;
		 }
		 
	}
}  

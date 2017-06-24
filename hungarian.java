

import java.util.Scanner;

class hungarian {
	final static int N=4;
	public static void reduction(int[][] cm, int x) {
		int n=cm.length;
		int min[]=new int[n];
		int min1=cm[0][0];
		if(x==0){ //Row reduction
		for(int i=0;i<n;i++){
			min1=cm[i][0];
			for(int j=0;j<n;j++) 
				if(min1>cm[i][j])
					min1=cm[i][j];
			for(int j=0;j<n;j++)
				cm[i][j]=cm[i][j]-min1;
		}
		}
		if(x==1)  //Column Reduction
		{
			for(int i=0;i<n;i++){
				min1=cm[0][i];
				for(int j=0;j<n;j++) 
					if(min1>cm[j][i])
						min1=cm[j][i];
				for(int j=0;j<n;j++)
					cm[j][i]=cm[j][i]-min1;
			}
		}
		 
		}
	public static void subrow(int[][] cm,int[] rflag,int[] cflag)
	{
		int n=cm.length;
		double min = Double.POSITIVE_INFINITY;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(min>cm[i][j] && rflag[i]==0 && cflag[j]==0)
					min=cm[i][j];
				}
			System.out.println("Minimum element is:"+min);
		}
		for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			if(rflag[i]==0)
			cm[i][j]=cm[i][j]-(int)min;	
			if(cflag[j]==1)
				cm[i][j]=cm[i][j]+(int)min;
		}}
		print(cm);
		drawline(cm);
	}
	public static int drawlines(int[][] cm,int[] rflag,int[] cflag,int clines)
	{
		int n=cm.length;
		int zeros[]=new int[2*n];//array for storing max no of zeros in col/row
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++)
				if(cm[i][j]==0 && rflag[i]==0 && cflag[j]==0){
					zeros[i]++;
					zeros[j+n]++;
					}
			}
		for(int i=0;i<2*n;i++)
			 System.out.println("No. of zeros are: "+zeros[i]);
		//Finding max
		int rmax=zeros[0];
		int cmax=zeros[n];
		int r=0,c=0;//index of row or column with max zeros
		for(int i=0;i<n;i++){
				if(rmax<zeros[i]){
					rmax=zeros[i];
					r=i;
				}
				if(cmax<zeros[n+i])
				{
					cmax=zeros[n+i];
					c=i;
				}
				
			}
		System.out.println("index of row with max zeros and rmax :"+r+" rmax "+rmax+"index of column with max zeros and cmax is "+c+" "+cmax);
		if(rmax>0||cmax>0)
		{
		if(rmax==cmax && rmax!=1 && rmax!=2)//Column is containing max zeros
		{
			cflag[c]=1;
			clines++;
			rflag[r]=1;
			clines++;
		}
		else if(rmax>cmax)
		{
			rflag[r]=1;
			clines++;
		}
		else if(cmax>rmax)
		{
			cflag[c]=1;
			clines++;
		}
		else if(rmax==cmax && (rmax==1|| rmax==2))
		{
			rflag[r]=1;
			clines++;
		}
		else
		{
			cflag[c]=1;
			clines++;
		}
		}
		return clines;
	}
	public static void drawline(int[][] cm)
	{
		int n=cm.length;
		int c=0;
		int clines=0;//count of lines drawn
		int rflag[]=new int[n];//indexes of marked row 
		int cflag[]=new int [n];//indexes of marked column
		while(c<n)
		{
		clines=drawlines(cm,rflag,cflag,clines);
		System.out.println("No. of lines:"+clines);
		for(int i=0;i<n;i++)
			 System.out.println("Row:"+i+" "+rflag[i]);
		for(int i=0;i<n;i++)
			 System.out.println("Column"+i+" "+cflag[i]);
		c++;
		}
		if(clines<n){
			subrow(cm,rflag,cflag);
			//addcol(cm,rflag,cflag);
		}
			 
	}
	    static void printSolution(int board[][],int temp6[][])
    {  
		 int ar2[] = new int[10];
		 int ar1[] = new int[10];int k=0,sum=0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                {
					System.out.print(" " + board[i][j]
                                 + " ");
                           if(board[i][j]==999){sum+=temp6[i][j];ar1[k]=i;ar2[k]=j;k++;}      
                                 
                                 
                                 }
            System.out.println();
        }
        for(int i=0;i<k;i++){System.out.println(ar1[i]+" "+ar2[i]);}
                    System.out.println("Optimal  "+sum);

    }
	    static boolean isSafe(int board[][], int row, int col)
    {
        int i, j;
            if(board[row][col]!=0) return false;
            
        
        for (i = 0; i < col; i++)
            if (board[row][i] == 999)
                return false;
              
        return true;
    }
	static boolean solveuntil(int board[][], int col)
    {
      
        if (col >= N)
            return true;
 
      
        for (int i = 0; i < N; i++)
        {
            
            if (isSafe(board, i, col))
            {    System.out.print(i+" " +col+"\n");
                
                board[i][col] = 999;
 
              
                if (solveuntil(board, col + 1) == true)
                    return true;
                board[i][col] = 0; // BACKTRACK
            }
        }
 
        return false;
    }
	public static void print(int[][] cm){
		int n=cm.length;
		int m=cm[0].length;
		for(int i=0;i<n;i++){
			 for(int j=0;j<m;j++){
           System.out.print(cm[i][j]+" ");
			 }
			 System.out.println("");
			 
		 }
	}
	 public static void main(String args[])
	 {
		 int temp=4,temp1=4;
		 //int test[]=new int[temp];
		int CostMatrix[][]=new int [temp][temp1];
		int temp6[][]=new int [temp][temp1];
		
		 Scanner sc = new Scanner(System.in);
		 hungarian h = new hungarian();
		 for(int i=0;i<temp;i++)
			 for(int j=0;j<temp1;j++)
			  {CostMatrix[i][j]=sc.nextInt();temp6[i][j]=CostMatrix[i][j];}
		 h.print(CostMatrix);
		 h.reduction(CostMatrix,0);
		 h.print(CostMatrix);
		 h.reduction(CostMatrix,1);
		 h.print(CostMatrix);
		 System.out.println();
		 /*for(int i=0;i<temp;i++)
			 System.out.print(test[i]);*/
		 h.drawline(CostMatrix);
		 if (solveuntil(CostMatrix, 0) == false)
            System.out.print("Solution does not exist");
        
        printSolution(CostMatrix,temp6);
	    }
				 
	 
		 //System.out.println("Hello");
		 
	 }


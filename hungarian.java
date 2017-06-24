
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;


public class hungarian
{
  static JFrame frame;
  private static JTextField inputField [][];
  private static JTextField inputField1;
  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        displayJFrame();
      }
    });

	
  }
  
  
  //-----------------------column and row reduction------------------------------------
  
  
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
  
  
  
  // ---------------------------------sub row adding columns------------------------------
  
  
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
		
		drawline(cm);
	}
  
  
  //----------------------------drawing lines ----------------------------------------------
  
  
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
	
	//----------------counitng lines with zeroes---------------------------------
	
	
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
	
	
	//-----------------printsoln------------------------------
	
	
	 static void printSolution(int board[][],int temp6[][])
	    {  
			 int ar2[] = new int[10];
			 int ar1[] = new int[10];int k=0,sum=0;
	        for (int i = 0; i < board.length; i++)
	        {
	            for (int j = 0; j < board[0].length; j++)
	                {
						//System.out.print(" " + board[i][j]
	                              //   + " ");
	                           if(board[i][j]==999){sum+=temp6[i][j];ar1[k]=i;ar2[k]=j;k++;}      
	                                 
	                                 
	                                 }
	            //System.out.println();
	        }
	       /* for(int i=0;i<k;i++){System.out.println(ar1[i]+" "+ar2[i]);}
	                    System.out.println("Optimal  "+sum);*/
	        JFrame frame1 = new JFrame("Optimal Solution");
	        String x= "";
	        JButton[] buttons = new JButton[k+1];
	        for(int i=0;i<k;i++)
	        { 
	        	x=x+ar1[i];
	        	x=x+","+ar2[i];
	        	buttons[i]=new JButton(x);
	        	x="";
	        }
	        x="";
	        x=x+sum;
	        buttons[k]=new JButton(x);
	        frame1.getContentPane().setLayout(new FlowLayout());
	        for(int i=0;i<=k;i++){frame1.add(buttons[i]);}
	        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        frame1.setPreferredSize(new Dimension(300, 200));
	        frame1.pack();
	        frame1.setLocationRelativeTo(null);
	        frame1.setVisible(true);

	    }
	 
	 
	 // last step-------------------------------
	 
	 
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
	      
	        if (col >= board.length)
	            return true;
	 
	      
	        for (int i = 0; i < board.length; i++)
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

		
  static void displayJFrame()
  {
	  //----------------input cost matrix size---------------
	  
    frame = new JFrame("Project Hungarian");
     inputField1=new JTextField(5);
     Font bigFont = inputField1.getFont().deriveFont(Font.PLAIN, 30f);
                inputField1.setFont(bigFont);
    JButton three = new JButton("Go");
    three.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {  
		  int n12=  Integer.parseInt(inputField1.getText());
    	  setElements(n12,n12);
      }
    });
    frame.getContentPane().setLayout(new FlowLayout());
    frame.add(inputField1);
    
    frame.add(three);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(300, 100));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
  
  
  private  static void setElements(int row,int col)
  {
      int temp, temp1;           
      String tempString;
      JButton calculate = new JButton("calculate");
     JPanel choosePanel [] = new JPanel [row+3];
     choosePanel[0] = new JPanel();
     choosePanel[0].add(new Label("Fill cost matrix"));
     choosePanel[choosePanel.length-1] = new JPanel();
     choosePanel[choosePanel.length-1].add(new Label("Consider rows as teams and columns as projects"));
     //choosePanel[choosePanel.length-1].add(calculate);
     inputField  = new JTextField [row][col];
     for(temp = 1; temp <= row; temp++)
     {
         choosePanel[temp] = new JPanel();
         
         
         for(temp1 = 0; temp1 < col; temp1++)
         {
             inputField [temp-1][temp1] = new JTextField(4);
             choosePanel[temp].add(inputField [temp-1][temp1]);
             
             if(temp1 < col -1)
             {
             choosePanel[temp].add(Box.createHorizontalStrut(10));
             }
             
         }
         
     }
     int result;
    
    /* calculate.addActionListener(new ActionListener()
     {
       public void actionPerformed(ActionEvent e)
       {
         calchungarian(inputField);
       }
     });*/
    result = JOptionPane.showConfirmDialog(null, choosePanel, 
             "INPUT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
    	calchungarian(inputField);
      }
  }
  private static void calchungarian (JTextField field [][] )
  {   
	  int Costmatrix[][] = new int[field.length][field[0].length];
	  int temp6[][]=new int [field.length][field[0].length];
      for(int temp = 0; temp < field.length; temp++)
      {
          for(int temp1 = 0; temp1 < field[0].length; temp1++)
          {
              Costmatrix[temp][temp1]= Integer.parseInt(field[temp][temp1].getText());
              temp6[temp][temp1]=Costmatrix[temp][temp1];
          }
      }
      // h.print(CostMatrix);
 	   reduction(Costmatrix,0);
 	  // h.print(CostMatrix);
 	   reduction(Costmatrix,1);
 	 //  h.print(CostMatrix);
 	// System.out.println();
 	 /*for(int i=0;i<temp;i++)
 		 System.out.print(test[i]);*/
 	 drawline(Costmatrix);
 	 if (solveuntil(Costmatrix, 0) == false)
         System.out.print("Solution does not exist");
     
     printSolution(Costmatrix,temp6);
      
     /* JFrame frame1 = new JFrame("Project");
      String x= "";
      x=x+costmatrix[0][0];
      JButton calc = new JButton(x);
      frame1.getContentPane().setLayout(new FlowLayout());
      frame1.add(calc);
      frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame1.setPreferredSize(new Dimension(300, 200));
      frame1.pack();
      frame1.setLocationRelativeTo(null);
      frame1.setVisible(true);*/
      
  }
  
}

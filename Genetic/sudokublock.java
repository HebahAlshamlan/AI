public class sudokublock{
   
   private double fittness;
   public  int su[][];
  // private int fit[] =new int [5];
   
   public sudokublock(){
      fittness = 0.0;
      su= new int [4][4];
      //fit = new int [5];
  }
  
  
  /* public sudokublock(int[][] v){
       //fit = new int [5];
      su= v;
      fittness = 0.0;
  }*/
  /* public void printfit(){
      for(int i = 1; i<5; i++){
         System.out.print(fit[i]+" ");
      }
   System.out.print("\n");
   }*/
      private int[] fittnessR(int row, int[] fit){
      for(int i=0; i<4 ;i ++){
         for(int j = i+1; j< 4 ; j++){
            if(su[row][j]==su[row][i]){
            
               fit[su[row][i]]++; //increase the occurance of this integer on the fittness;
        break;}
        } 
       //printfit(); 
       }
     return fit;   
  }//end fittness row wise
  
 
   private int[] fittnessC(int col, int[] fit){
      for(int i=0; i<4 ;i ++){
         for(int j = i+1; j< 4 ; j++){
            if(su[j][col]==su[i][col]){
               fit[su[i][col]]++; //increase the occurance of this integer on the fittness;
                  break;}
                    } //printfit();
                    }
                    return fit;
  }//end fittness col wise

   public void fittnessTotal(){
    int[] fit =new int [5];
    fittness = 0;  int total=0;
      for(int i = 0 ; i<4 ; i++){
         fit = fittnessR(i, fit);
         
         }//row wise 
             
      //System.out.print("end row\n ");
      for(int j = 0 ; j<4 ; j++){
         fit = fittnessC(j, fit);
         
      }//column wise
     
      //System.out.print("end column\n ");
      
      for(int t = 1 ; t<5 ; t++){
         total = total + fit[t];
        
      }//average
      
      fittness = ((double)total)/4.0;
      
      
  // System.out.print("fitness: "+ fittness+"\n\n");
   }
   
   public double getfit(){
      //fittnessTotal();
      //System.out.println("Fittness: "+fittness+"\n");
   return fittness;
   }
   
     public void print(){
  
      for(int i =0; i < 4 ; i++){
         for(int j =0; j< 4 ; j++){
            System.out.print(su[i][j]+ " ");
         }
          
         System.out.println();
      }
          System.out.print("Fittness int print: "+getfit()+"\n");
      //fittnessTotal();
      }
      
  


}
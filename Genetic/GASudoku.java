import java.util.*;

public class GASudoku{

   
   static int count = 0, count2 =0;
   public static sudokublock[] bestP = new sudokublock[50];
   public static sudokublock[] bestP2 = new sudokublock[20];
   
   public static sudokublock[] generateS(sudokublock[] population){
      int[][] s = new int[4][4];
         
      for(int p =0; p< 500 ; p++){     
         sudokublock obj = new sudokublock();
         for(int i =0; i < 4 ; i++){
            for(int j =0; j< 4 ; j++){
               obj.su[i][j] =(int)(Math.random()*4+1);
            
            }} population[p] = obj;
         population[p].fittnessTotal();
      }
      return  population; }//end of generating populations.   

   public static void fillbest(sudokublock[] old ){
      for(int i=0; i<500; i++){
         sudokublock obj = new sudokublock();
         double d = old[i].getfit();
      
         if (d <= 2.0){
            obj = old[i];
         
            if(count<50){
               bestP[count++] = obj; }
         }
      }//end population for
   
   }//end filling the best states

////////////cross over/////////////
   public static sudokublock[] crossover(sudokublock p1, sudokublock p2) {
      sudokublock[] crossover = new sudokublock[4];
    
      sudokublock c1 = new sudokublock();
      sudokublock c2 = new sudokublock();
      sudokublock c3 = new sudokublock();
      sudokublock c4 = new sudokublock();
    
      int crosspoint = 4/ 2;
      for (int i = 0; i < crosspoint; i++) {
         for (int j = 0; j < 4; j++) {
            c1.su[i][j] = p1.su[i][j];
            c2.su[j][i] = p1.su[j][i];
            c3.su[i][j] = p2.su[i][j];
            c4.su[j][i] = p2.su[j][i];
         }
      }
      for (int i = crosspoint; i < 4; i++) {
         for (int j = 0; j < 4; j++) {
            c1.su[i][j] = p2.su[i][j];
            c2.su[j][i] = p2.su[j][i];
            c3.su[i][j] = p1.su[i][j];
            c4.su[j][i] = p1.su[j][i];
         }
      }
      c1.fittnessTotal();
      c2.fittnessTotal();
      c3.fittnessTotal();
      c4.fittnessTotal();
   
   
      crossover[0] = c1;
      crossover[1] = c2;
      crossover[2] = c3;
      crossover[3] = c4; 
  
      return crossover;
    
   }
   
   ////////////////mutation/////////////////
   public static sudokublock mutation(sudokublock b){
      double fit = b.getfit();
    
      for(int r = 0; r<4; r++){
         fit = b.getfit();
         if(b.getfit()==0){//this 
            return b; }
         b = rowTesting(r, fit, b); 
      
        
      }//end of row checking
   
      for(int c= 0; c<4; c++){
         fit = b.getfit();
         if(b.getfit()==0){
            return b; }
         b = colTesting(c, fit, b); 
         
      }//end of column testing 
     
      return b;
   }//end of mutation.

   public static boolean checkvalidrow(int col, int row, sudokublock s){
   //method to check if the row is complete or not
      boolean valid= true;
      for(int i =0; i< 4 ; i++){
      
         if(s.su[row][col] == s.su[i][col]&& row!=i){
            valid=false;
         }
      }
      return valid;
   } //end check validity


   public static sudokublock rowTesting(int row , double fit , sudokublock s){//this method is not entered unless fittness is 1 or less
      for(int i =0; i< 4 ; i++){// picking the first item column wise,, because the row is already fixed
         fit = s.getfit();
         for(int j =i+1; j<4 ; j++){//another item in this row to compare to.
            if(s.getfit()==0){//if fitness reached zero its a posipple solution no need to continue,
               return s;
            }
            if(s.su[row][i]==s.su[row][j]){ 
               if(!checkvalidrow(i, row , s)){//calling to check which of the two items affect the fitness badely if column isn't complete it changes this item
                  boolean flag = true;
                  while (flag){//loops until the sutable number is assigned by the random generator
                     int x = s.su[row][i];
                     s.su[row][i]= (int)(Math.random()*4+1);
                     s.fittnessTotal();
                  
                     if(s.getfit()<fit){//if fitness is less then the best random number is chosen
                        fit = s.getfit();
                        flag=false;   }
                  }//end while;
                     
               } else{//if the colmn in which element row,i in a complete the item row, j will be changed 
                  boolean flag = true;
                  while (flag){//same while
                     s.su[row][j]= (int)(Math.random()*4+1);
                     s.fittnessTotal();                  
                     if(s.getfit()<=fit){
                        flag=false;   }
                  }//end while;
               }//end else  
            
            }//end comparenig
         
         }//end checking items - loop 2
            
      }//end loop 2
   
      s.fittnessTotal(); //final calculating after modification
      return s;
   }//end method

   public static sudokublock colTesting(int col , double fit , sudokublock s){//this method is not entered unless fittness is 1 or less
      for(int i =0; i< 4 ; i++){
         fit = s.getfit();
         for(int j =i+1; j<4 ; j++){
            if(s.getfit()==0){//////////////////////////
               return s;
            
            }/////////////////////
            if(s.su[i][col]==s.su[j][col]){            
               boolean flag = true;
               while (flag){
                  int x = s.su[j][col];
                  s.su[j][col]= (int)(Math.random()*4+1);   
                  s.fittnessTotal();
                  if(s.su[i][col]!=x){
                     flag = false;
                  }
               
               }
            
            }
            
         }}
   
      s.fittnessTotal();
      return s;
   }//end method

   public static boolean check(sudokublock board) {
      int row , col , box = 0;
   
      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 4; j++) {
         
            int valu = board.su[i][j];
            row =0 ; col =0 ; box = 0;
         
         //check for BOX
            int r = i - i % (int) Math.sqrt(4);
            int c = j - j % (int) Math.sqrt(4);
            for (int b = r; b < r + (int) Math.sqrt(4); b++) {
               for (int h = c; h < c + (int) Math.sqrt(4); h++) {
                  if (board.su[b][h] == valu) {
                     box++;
                  // System.out.println(board[b][h] +""+ box);
                     if (box >= 2) {
                        return false;
                     }
                  }
               }
            }
         
            for (int x = 0; x < 4; x++) {
            //check for ROW
               if (board.su[x][j] == valu)
                  row++;
            //check for COLUMN
               if (board.su[i][x] == valu)
                  col++;
            
               if (col >= 2 || row >= 2) {
                  return false;
               }
            }
         }
      }
      return true;
   }



 
 /////////////main///////////////////
   public static void main(String [] args){
      sudokublock[] population = new sudokublock[500];
      sudokublock[] crossO = new sudokublock[4]; 
       
      long startTimeT = System.currentTimeMillis();
      
       
      population= generateS(population);
      fillbest(population);
   
      int a=0;
      while(a<50){ 
         for(int w = 1; w<count-2 ; w=w+2) {
            crossO = crossover(bestP[w-1], bestP[w]); 
            for(int h = 0; h<4 ; h++){
               if(crossO[h].getfit()<2.0&&count2<20){
               
                  bestP2[count2++] = crossO[h];
               
               }}
         }
         a++;
      }//end while that checks that all bestp 1 is crossoverd.
           
      if(count2>0){
         sudokublock result = new sudokublock(); 
         boolean flag = false;
         while(flag==false){
            for(int h = 0; h<count2 ; h++){
               if(bestP2[h].getfit()<=1.5){
                  result =  mutation(bestP2[h]);
                  if(result.getfit()==0){
                     if(check(result)){
                        result.print();
                        flag= true;
                        break;}
                  }
               }}
         
                   }//end of while
       
       
      
      }
     
      long endTimeT = System.currentTimeMillis();//printing time taken from the start to end
      long TotalTime = endTimeT - startTimeT;
      System.out.println("Time taken: " +TotalTime );
   
   }//end main


}
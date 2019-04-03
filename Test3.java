
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Noor Al Deen
 */

class FillThread extends Thread {
   private BufferedWriter w1,w2,w3;
    private boolean finished;
     
    public FillThread(BufferedWriter w1) throws IOException {
        String n;
        this.w1 = w1;
       
        
        
    }
    
    
   
    
    public void run() {
        
       
           
            try {
                for (int i=0;i<1000;i++){
        
                
                w1.write( Integer.toString((int)(Math.random()*51)));
                w1.newLine();
                }
                
                w1.flush();
                
                
                finished = true;
              
            }
                catch (IOException ex) {
                Logger.getLogger(FillThread.class.getName()).log(Level.SEVERE, null, ex);
            }
     
    
        
        
            
    


    }
        boolean isFinished(){
        return finished;
    }

}




class CheckThread extends Thread   {
    private BufferedReader r;
     private boolean finished=false;
     
private int sum=0;
    public CheckThread(BufferedReader r) throws IOException {
        String n;
        this.r = r;
       
        
    }
    
    public static boolean isPrime(int n) {  
       if (n <= 1) {  
           return false;  
       }  
       for (int i = 2; i < n; i++) {  
           if (n % i == 0) {  
               return false;  
           }  
       }  
       return true;  
   }  
    
    
    public void run() {
        
        try {
            String n;
            
            while(( n =r.readLine())!=null)
          if(isPrime(Integer.parseInt(n))) sum++;

            
            
           
            finished = true;
        } catch (IOException ex) {
            Logger.getLogger(CheckThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    int getSum(){
        return sum;
    }
    boolean isFinished(){
        return finished;
    }
}

public class Test {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        // inb = new BufferedReader(new FileReader("F:\\Company\\Project.txt"));
       BufferedWriter  out1 = new BufferedWriter(new FileWriter("E:\\New\\file1.txt"));
       BufferedWriter  out2 = new BufferedWriter(new FileWriter("E:\\New\\file2.txt"));
       BufferedWriter  out3 = new BufferedWriter(new FileWriter("E:\\New\\file3.txt"));
       
       
       BufferedReader  in1 = new BufferedReader(new FileReader("E:\\New\\file1.txt"));
       BufferedReader  in2 = new BufferedReader(new FileReader("E:\\New\\file2.txt"));
       BufferedReader  in3 = new BufferedReader(new FileReader("E:\\New\\file3.txt"));
       
       
       FillThread th1 = new FillThread(out1);
       FillThread th2 = new FillThread(out2);
       FillThread th3 = new FillThread(out3);
       
       th1.start();th2.start();th3.start();
       
       
       while (!th1.isFinished() || !th2.isFinished() || !th3.isFinished());
        
       
        CheckThread t1 = new CheckThread(in1);
        CheckThread t2 = new CheckThread(in2);
        CheckThread t3 = new CheckThread(in3);
         t1.start();t2.start();t3.start();
        while (!t1.isFinished() || !t2.isFinished() || !t3.isFinished());
        System.out.println("File# 1: " + t1.getSum()+"\n"+"File# 2: " + t2.getSum()+"\n"+"File# 3: " + t3.getSum()); 
       
    }
    
}

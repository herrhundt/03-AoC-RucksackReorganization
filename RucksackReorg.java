import java.io.*;
import java.util.*;

public class RucksackReorg
{
  //static String filename = "input-sample.txt"; //for testing purposes 
  static String filename = "input.txt"; //the original input 
    
  static String [] input;
 
  /**
   * solves part I
   */
   public static void countScore() throws IOException
   {
       readInputIntoArray(); 
       int sum =0;
       int tempScore=0; 
       for(int i=0; i<input.length;i++)
       {
           tempScore=countScore(i);
           sum+=tempScore; 
           System.out.println("Rucksack No."+i+", score: "+ tempScore); 
       }
       System.out.println("____________"); 
       System.out.println("Total score"+sum); 
       
    } 
  /**
   * solves part II
   */
  public static void countGroups() throws IOException
  {
      readInputIntoArray(); 
      int sum =0; 
      int rucksack =0; 
      String s1, s2, s3; 
        HashSet<Character> rucksackA = new HashSet<Character>();//create hashsets 
        HashSet<Character> rucksackB = new HashSet<Character>();
        HashSet<Character> rucksackC = new HashSet<Character>();
        Iterator<Character> iter; 
        char commonLetter; 
        int valueLetter=-1; 
      
      for(int iGroup=0; iGroup<input.length;iGroup+=3)
      {
        rucksackA.clear(); //clears all hashsets 
        rucksackB.clear(); 
        rucksackC.clear(); 
    
        s1 = input[iGroup]; //calc first group 
        s2 = input[iGroup+1];
        s3 = input[iGroup+2]; 
         
        rucksackA = new HashSet<Character>();//create hashsets 
        rucksackB = new HashSet<Character>();
        rucksackC = new HashSet<Character>();
        
        for(char c : s1.toCharArray()) {rucksackA.add(c); }//add items to hashsets 
        for(char c : s2.toCharArray()) {rucksackB.add(c);}
        for(char c : s3.toCharArray()) {rucksackC.add(c);}
            
        rucksackA.retainAll(rucksackB); 
        rucksackA.retainAll(rucksackC); 
        
        iter = rucksackA.iterator(); 
        commonLetter = (char)(iter.next());
        
        if(Character.isUpperCase(commonLetter))//uppercase?
        {
            valueLetter = (int)(commonLetter)-38;  
        }
        else //lowercase 
        {
         valueLetter = (int)(commonLetter)-96;    
        }
        sum+=valueLetter; 
        System.out.println(commonLetter + " int-value: "+valueLetter); 
      }
       System.out.println("Sum "+sum); 
  }  
  /**
  * counts the score of the rucksack 
  */
    public static int countScore(int rucksack)
    {
        int score = 0; 
        String getString = input[rucksack];
        int rucksackSize = getString.length(); 
        String s1 = getString.substring(0,rucksackSize/2); 
        String s2 = getString.substring(rucksackSize/2,rucksackSize); 
        
        Set<Character> rucksackA = new HashSet<Character>();//create two hashsets 
        Set<Character> rucksackB = new HashSet<Character>();
        
        for(char c : s1.toCharArray()) {rucksackA.add(c); }
        for(char c : s2.toCharArray()) {rucksackB.add(c);}
        
        //Durchschnitt bilden 
        rucksackA.retainAll(rucksackB); //intersection of two sets 
        
        Iterator iter =  rucksackA.iterator(); 
        char commonLetter = (char) (iter.next());
        int valueLetter=-1; 
        
        if(Character.isUpperCase(commonLetter))//uppercase?
        {
            valueLetter = (int)(commonLetter)-38;  
        }
        else //lowercase 
        {
         valueLetter = (int)(commonLetter)-96;    
        }
     
        System.out.println(commonLetter + " int-value: "+valueLetter); 
        
        
        return valueLetter; 
    }
    
    /**
     * Die Methode liest den Input der Textdatei in ein Array ein. 
     */
    public static void readInputIntoArray() throws IOException
    {
    int arraySize =0; 
    try
    {
        arraySize = getInputLength(); 
    }
    catch (IOException e) 
    {
        System.out.println("Es ist ein Fehler beim Einlesen passiert!"); 
        System.exit(1); //Programm beenden
    }
    input = new String[arraySize]; //Erstelle leeres Array mit der Anzahl 
    
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);      
    String zeile = "";
    
    for(int i=0; i<arraySize;i++)
    {
         input[i] = br.readLine();
    }
      br.close();
      //array should be read correctly  
    }  
    
    /**
     * Methode bestimmt die Länge der Texteingabe 
     */
    public static int getInputLength() throws IOException
    {
     int size = 0;  
     FileReader fr = new FileReader(filename);
     BufferedReader br = new BufferedReader(fr);  
      //Bestimme die Anzahl der Werte! 
     String zeile = "";
     while( (zeile = br.readLine()) != null )
     {
        System.out.println(zeile);
        size++; 
      }
      br.close();
    return size; 
    }    


}

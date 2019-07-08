import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class MediumDataGenerator {
	
	public static void main(String args[]) throws IOException
	{
		String outputFilename = "MediumDensity";
        PrintWriter pw = new PrintWriter(new FileWriter("medium" + outputFilename + ".in"), true);
		Random rand = new Random(); 
		int edge =0;
		
        for (int i = 1; i<=200; i++){
            for (int j=1; j<=200; j=j+20){
            	if(i!=j) {
                pw.printf("%s,%s,%d\n", i, j, rand.nextInt(40) + 1);
                pw.printf("");
                edge++;
                }
            }
        }
        pw.close();
       
        System.out.print(outputFilename + ".in" + " Generated");
	}
}

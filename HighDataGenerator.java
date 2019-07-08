import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class HighDataGenerator {

	
	public static void generateHighDensitydata() throws IOException
	{
		String outputFilename = "HighDensity";
        PrintWriter pw = new PrintWriter(new FileWriter(outputFilename + ".in"), true);
		Random rand = new Random(); 
		
		int sumEdge = 0;
		int vertEdge = 0;
        for (int i =1; i<=200; i++){
            for (int j=1; j<=200; j++){
            	if(i!=j) {
            		pw.printf("%s,%s,%d\n", i, j, rand.nextInt(40 - 1) + 1);
            		sumEdge++;
                }
            }
            vertEdge++;
        }
        pw.close();
       
        System.out.println("File " +outputFilename + ".in" + " Generated Successfully");
	}
	
	
	public static void generateMediumDensitydata() throws IOException
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
       
        System.out.print("File " +outputFilename + ".in" + " Generated Successfully");
	}
	
	public static void generateLowDensityData() throws IOException
	{
		String outputFilename = "LowDensity";
        PrintWriter pw = new PrintWriter(new FileWriter(outputFilename + ".in"), true);
		Random rand = new Random();
		
        for (int i = 1; i<=200; i++){
            for (int j=1; j<=200; j=j+50){
            	if(i!=j) {
                pw.printf("%s,%s,%d\n", i, j, rand.nextInt(10) + 1);
                pw.printf("");
                }
            }
        }
        pw.close();
        System.out.print("File " +outputFilename + ".in" + " Generated Successfully");
	}
	
	
	public static void main(String args[]) throws IOException
	{
		String outputFilename = "HighDensity";
        PrintWriter pw = new PrintWriter(new FileWriter(outputFilename + ".in"), true);
		Random random = new Random(); 
		
		int sumEdge = 0;
		int vertEdge = 0;
        for (int i =1; i<=250; i++){
            for (int j=1; j<=250; j++){
            	if(i!=j) {
            		pw.printf("%s,%s,%d\n", i, j, random.nextInt(10 - 1) + 1);
            		sumEdge++;
                }
            }
            vertEdge++;
        }
        pw.close();
       
        System.out.println("File " +outputFilename + ".in" + " Generated Successfully");
	}
}

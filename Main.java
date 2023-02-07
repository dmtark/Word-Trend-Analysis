/*
Main file used to run the program
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println(
					"\nUSAGE: java UniqueWords <year> <increased_frequency_percentage>" + "\n" +
					"Example: java UniqueWords 2008 150 --> shows words from 2008 with words 150% more common in 2008 than the average frequency over all years analyzed\n");

        if (args.length > 0) {

            UniqueWords.main(args[0], args[1]);
            System.out.println("Java file executed");

            ProcessBuilder pb = new ProcessBuilder("python3", "VisualizeWordTrends.py", args[0]);
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            p.waitFor();



            /*
            ProcessBuilder builder = new ProcessBuilder("python3", "VisualizeWordTrends.py", "yearArg");
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            } // end-while
            System.out.println("Python file executed");
            */
        } // end-if

        else {
            System.out.println("Must add arguments\n");
        }


    } // end main function

    
}
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    //main class with menu
    private static BufferedReader br, brIn; // to read input
    private static BufferedWriter wr; // to write to a file
    public static int compareIndex;
    public static String filePath="src/test.txt";
    public static void main(String[] args) throws FileNotFoundException {
        brIn = new BufferedReader(new InputStreamReader(System.in));
        printNavigation();
        try {
            command(brIn.readLine());
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void readAndCompare() throws FileNotFoundException
    {
        br = new BufferedReader(new FileReader(filePath));
        String line = null;
        List<String[]> lines = new ArrayList();
        int i;
        try {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                lines.add(data);

            }
            br.close();
            //command(brIn.readLine());
            lines.sort(new CustomComparator());
            lines.forEach(o -> System.out.println(Arrays.toString(o)));
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }
    private static void command(String string) throws IOException {
        switch (string) {
            case "0":
            case "1":
            case "2":
            case "3": {
                compareIndex = Integer.valueOf(string);
                readAndCompare();

            } break;
            case "5": {
                System.out.println(" type line num : ");
                String lineToRm = brIn.readLine();
                removeLineFromFile(filePath, lineToRm);

            } break;
            case "6": {
                System.out.println(" type record : ");
                String lineToAdd = brIn.readLine();
                addRecodrd(filePath, lineToAdd);

            } break;
            case "4": {
                System.exit(1);
            }
        }
        printNavigation();
        command(brIn.readLine());
    }
    public static class CustomComparator implements Comparator<String[]> {
        @Override
        public int compare(String[] o1, String[] o2) {

            System.out.println("compare index: "+compareIndex);
            return o1[compareIndex].compareTo(o2[compareIndex]);
        }
    }

    private static void printNavigation() {
        System.out.print("Where to ? | ");
        System.out.print("1. List - sort by name | ");
        System.out.print("2. List - sort by type | ");
        System.out.print("3. List - sort by 3 | ");
        System.out.print("4. Quit ");
        System.out.print("5. Remove record | ");
        System.out.print("6. Add record | ");
        System.out.println();
    }
    public static void removeLineFromFile(String file, String lineToRemove) throws FileNotFoundException {
        try {

            File inFile = new File(file);

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }
            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader brn = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = brn.readLine()) != null) {
                String[] element = line.split(",");

               if( !element[0].equals(lineToRemove))
               // if (!line.trim().equals(lineToRemove))
                {
                    System.out.println(line);
                    System.out.println(element[0]);
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            brn.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }compareIndex = Integer.valueOf("0");readAndCompare();
        //printNavigation();
    }
    private static void addRecodrd (String file, String record) throws IOException {
        File inFile = new File(file);
        BufferedWriter brw = new BufferedWriter(new FileWriter(inFile,true));
        record =  "\n"+record;
        brw.write(record);
        brw.flush();
        brw.close();

    }
}

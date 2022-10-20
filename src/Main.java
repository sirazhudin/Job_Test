import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

   //main class with menu
    private static BufferedReader br, brIn; // to read input
    //private static BufferedWriter wr; // to write to a file
    public static int compareIndex, managerId = 0;
    public static String filePath="src/test.txt";
    public static void main(String[] args)  {
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

        try {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int managerID = Integer.parseInt(data[2]);
                if (managerId!=0 ) {
                    if( managerID == managerId) lines.add(data);
                                    }
                else lines.add(data);
            }
            br.close();
            lines.sort(new CustomComparator());
           // lines.forEach(o -> System.out.println(Arrays.toString(o)));
            for(String[] o : lines) {
                System.out.println(Arrays.toString(o));
            }
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }
    private static void command(String string) throws IOException {
        switch (string) {
            case "0":{
                compareIndex =  Integer.valueOf(string);
                System.out.println("id,p.id,m.id,start date,birth date,name   ,second name,surname ,info");
                readAndCompare();
            } break;
            case "1":{
                compareIndex = 3;// Integer.valueOf(string);
                System.out.println("id,p.id,m.id,start date,birth date,name   ,second name,surname ,info");
                readAndCompare();
            } break;
            case "2": {
                compareIndex = 7;//Integer.valueOf(string);
                System.out.println("id,p.id,m.id,start date,birth date,name   ,second name,surname ,info");
                readAndCompare();

            } break;
            case "3": {
                //compareIndex = 7;//Integer.valueOf(string);
                System.out.println("Enter manager ID");
                managerId = Integer.parseInt(brIn.readLine());
                System.out.println("id,p.id,m.id,start date,birth date,name   ,second name,surname ,info");
                readAndCompare();
                managerId = 0;
            } break;
            case "5": {
                System.out.println(" type line num : ");
                String lineToRm = brIn.readLine();
                //RemoveRecord.removeLineFromFile(filePath, lineToRm);
                DeleteRecord.deleteRecord(filePath,lineToRm);
                compareIndex = Integer.valueOf("0");readAndCompare();

            } break;
            case "6": {
                System.out.println(" type record : ");
                String lineToAdd = brIn.readLine();
                addRecord(filePath, lineToAdd);
            } break;
            case "7": {
                System.out.println(" Value to change : ");
                String valToChange = brIn.readLine();
                System.out.println(" New Value : ");
                String newVal = brIn.readLine();
                ChangeRecord.modifyFile(filePath, valToChange,newVal);

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
            return o1[compareIndex].compareTo(o2[compareIndex]);
        }
    }

    private static void printNavigation() {
        System.out.print("Menu: \n");
        System.out.print("1. List-by surname | ");
        System.out.print("2. List-by start date | ");
        System.out.print("3. List-by manager ID | \n");
        System.out.print("4. Quit \n");
        System.out.print("5. Remove record | ");
        System.out.print("6. Add record | ");
        System.out.print("7. Change record | ");
        System.out.println();
    }

    private static void addRecord (String file, String record) throws IOException {
        File inFile = new File(file);
        BufferedWriter brw = new BufferedWriter(new FileWriter(inFile,true));
        record =  "\n"+record;
        brw.write(record);
        brw.flush();
        brw.close();
    }
}

// adds empty line to the end

//import java.io.*;
//
//public class RemoveRecord {
//    public static void removeLineFromFile(String file, String lineToRemove) throws FileNotFoundException {
//        try {
//            File inFile = new File(file);
//            if (!inFile.isFile()) {
//                System.out.println("Parameter is not an existing file");
//                return;
//            }
//            //Construct the new file that will later be renamed to the original filename.
//            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
//            BufferedReader brn = new BufferedReader(new FileReader(file));
//            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
//            String line = null;
//            //Read from the original file and write to the new
//            //unless content matches data to be removed.
//            while ((line = brn.readLine()) != null) {
//                String[] element = line.trim().split(",");
//                if( !element[0].equals(lineToRemove))
//                //if (!line.trim().contains(lineToRemove))
//                {                 //   System.out.println(line);          //System.out.println(element[0]);
//                    pw.println(line);
//                    // pw.write(line);
//                    pw.flush();
//                }
//            }
//            pw.close();
//            brn.close();
//            //Delete the original file
//            if (!inFile.delete()) {
//                System.out.println("Could not delete file");
//                return;
//            }
//            //Rename the new file to the filename the original file had.
//            if (!tempFile.renameTo(inFile))
//                System.out.println("Could not rename file");
//        }
//        catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//}

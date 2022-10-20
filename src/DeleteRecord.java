import java.io.*;

    public class DeleteRecord
    {
        static void deleteRecord(String filePath, String lineToDelete)
        {

            //File fileToBeModified = new File(filePath);
            String oldContent = "";
            BufferedReader reader = null;
            FileWriter writer = null;
            try
            {
                File inFile = new File(filePath);
                if (!inFile.isFile()) {
                    System.out.println("Parameter is not an existing file");
                    return;
                }
                File fileToBeModified = new File(inFile.getAbsolutePath() + ".tmp");
                reader = new BufferedReader(new FileReader(filePath));
                //Reading all the lines of input text file into oldContent
                String line = null; //= reader.readLine();
                while ((line = reader.readLine())!= null)
                {
                    String[] element = line.trim().split(",");
                    if(!element[0].equals(lineToDelete))
                    {
                    oldContent = oldContent + line + System.lineSeparator();
                    //line = reader.readLine();
                    }
                }
                //Replacing oldString with newString in the oldContent
               // String newContent = oldContent.replaceAll(oldString, newString);
                //Rewriting the input text file with newContent
                writer = new FileWriter(fileToBeModified);
                writer.write(oldContent.trim());

                if (!inFile.delete()) {
                    System.out.println("Could not delete file");
                    return;
                }
                //Rename the new file to the filename the original file had.
                if (!fileToBeModified.renameTo(inFile))
                    System.out.println("Could not rename file");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    //Closing the resources
                    reader.close();
                    writer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
//        public static void main(String[] args)
//        {
//            modifyFile("employee.txt", "", ");
//
//            System.out.println("done");
//        }
}

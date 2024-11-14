import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;
/**
 *
 * @author wulft
 *
 * Demonstrates how to use Java NIO, a thread safe File IO library
 * to write a text file
 */
public class DataSaver
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Test data the lines of the file we will write
        ArrayList <String>recs = new ArrayList<>();
        Scanner in = new Scanner(System.in);


        String firstName = "";
        String lastName = "";
        String idNum = "";
        String email = "";
        String csvRec = "";
        int yob = 0;
        boolean done = false;

        do {
            firstName = SafeInput.getNonZeroLenString(in, "Enter the first name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter the last name");
            idNum = SafeInput.getNonZeroLenString(in, "Enter the ID Num");
            email = SafeInput.getNonZeroLenString(in, "Enter the email");
            yob = SafeInput.getRangedInt(in, "Enter the birth year", 1000, 9999);

            csvRec = firstName + ", " + lastName + ", " + idNum + ", " + email + ", " + yob;

            recs.add(csvRec);

            done = SafeInput.getYNConfirm(in, "Are you done?");


        }while(!done);



        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));
            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}

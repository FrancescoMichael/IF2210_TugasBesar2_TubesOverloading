import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Muat {

    String pathFile;

    public Muat(String pathFile) {
        this.pathFile = pathFile;
    }

    public void printResult() {
        try {
            File myObj = new File(this.pathFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
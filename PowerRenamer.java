import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PowerRenamer {
    public static void main(String[] args) {
        new PowerRenamerGUI();
        Path sourceFolder = Paths.get("C:\\Users\\dluna\\Desktop\\projects\\Power Renameing\\testFolder");
        Path targetFolder = Paths.get("C:\\Users\\dluna\\Desktop\\projects\\Power Renameing\\testFolder");
        Scanner namer = new Scanner(System.in);

        try {
            // Get a list of all files in the source folder
            Files.walk(sourceFolder)
                    .filter(Files::isRegularFile)
                    .forEach(sourceFile -> {
                        try {

                            String targetFileName = sourceFile.getFileName().toString();
                            
                            long creationDate = sourceFile.toFile().lastModified();

                            String formatedDate = formatDate(creationDate);

                            String type = targetFileName.substring(targetFileName.indexOf('.') + 1).toUpperCase();

                    
                            // .replaceFirst("[.][^.]+$", "") + ".txt";
                            System.out.printf("%-10s",type);
                            System.out.printf("%-25s", targetFileName);
                            System.out.print(formatedDate);
                            System.out.println();

                            String newName = namer.nextLine();

                            if(newName.equals("")){
                                newName = targetFileName;
                            }

                            String newPath = type + "_" + formatedDate + "-" + newName + "." + type;

                            System.out.println(newPath );
                            System.out.println();


                            // Create the target path by appending the new file name to the target folder path
                            Path targetFile = targetFolder.resolve(newPath );

                            // Move the file from the source folder to the target folder with the new name
                            Files.move(sourceFile, targetFile );

                            System.out.println("File copied successfully: " + targetFile );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) { e.printStackTrace(); }

        namer.close();
    }

    public static String formatDate(long creationDate){
        Date date = new Date(creationDate);

        // Create a SimpleDateFormat object
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");

        // Format the date
        return  sdf.format(date);
    }
}

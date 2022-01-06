import java.io.*;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {
    public static final String FILE_NAME = "telebook.csv";

    public static void save(TeleBook teleBook) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Contact contact : teleBook){
            writer.write(contact.toCSV());
            writer.newLine();
        }
        writer.close();
    }
    public static TeleBook read(){
        TeleBook teleBook = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            Map<String, Contact> contacts = bufferedReader.lines()
                    .map(line -> line.split(":"))
                    .map(split -> new Contact(split[0], split[1]))
                    .collect(Collectors.toMap(Contact::getName, Function.identity()));
        } catch (FileNotFoundException e) { }
        return teleBook != null ? teleBook : new TeleBook();
    }
}

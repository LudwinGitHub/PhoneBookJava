import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TeleBookController {
    private TeleBook teleBook = new TeleBook();
    private Scanner input = new Scanner(System.in);

    public void loop() {
        TELEOPTIONS teleoptions = null;
        do {
    showOptions();
    try {
        teleoptions = chooseOption();
        executeOption(teleoptions);
    } catch (NoSuchElementException e){
        System.out.println("Incorrect option!");
    }
        } while (teleoptions != TELEOPTIONS.CLOSE);
    }

    private void showOptions() {
        System.out.println("Options:");
        TELEOPTIONS[] values = TELEOPTIONS.values();
        for (TELEOPTIONS value : values) {
            System.out.println(value);
        }
    }

    private TELEOPTIONS chooseOption() {
        int option = input.nextInt();
        input.nextLine();
        return TELEOPTIONS.convertToOption(option);
    }

    private void executeOption(TELEOPTIONS options) {
        switch (options) {
            case ADD_CONTACT:
                addContact();
                break;
            case SEARCH_BY_NAME:
                searchByName();
                break;
            case SEARCH_BY_TEL:
                searchByTelephone();
                break;
            case DELETE:
                delete();
                break;
            case CLOSE:
                close();
                break;
        }
    }

    private void delete() {
        System.out.println("Delete contact: ");
        String removed = input.nextLine();
        boolean remove = teleBook.remove(removed);
        if (remove){
            System.out.println("Contact " + removed + " deleted");
        } else {
            System.out.println("Contact can't be deleted");
        }

    }

    private void searchByTelephone() {
        System.out.println("Find contact by number");
        String number = input.nextLine();
        List<Contact> byTelephone = teleBook.findByTelephone(number);
        if (byTelephone.isEmpty()){
            System.out.println("OPS..no contacts :(");
        } else {
            System.out.println("Contacts: ");
            for (Contact contact : byTelephone) {
                System.out.println(contact);
            }
        }
    }

    private void searchByName() {
        System.out.println("Find contact by name");
        String name = input.nextLine();
        List<Contact> byName = teleBook.findByName(name);
        if (byName.isEmpty()){
            System.out.println("OPS..no contacts :(");
        } else {
            System.out.println("Contacts: ");
            byName.forEach(System.out::println);
        }
    }

    private void addContact() {
        System.out.println("Add new contact");
        System.out.println("Name:");
        String name = input.nextLine();
        System.out.println("Number:");
        String number = input.nextLine();
        try {
            boolean add = teleBook.add(name, number);
            if (add){
                System.out.println("Contact added");
            } else {
                System.out.println("Contact already exist");
            }
        } catch (IllegalArgumentException e){
            System.out.println("Name and number cannot be empty");
        }
    }

    private void close() {
    input.close();
        try {
            FileUtils.save(teleBook);
            System.out.println("Zapisano zmiany.");
        } catch (IOException e) {
            System.err.println("Nie udało się zapisać zmian");
        }
        System.out.println("Bye bye.");
    }
}

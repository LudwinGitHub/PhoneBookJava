import java.util.*;
import java.util.function.Consumer;

public class TeleBook implements Iterable<Contact> {

    Map<String, Contact> contacts = new TreeMap<>();

    public TeleBook() {}

    public TeleBook(Map<String, Contact> contacts) {
        this.contacts = contacts;
    }
    public boolean add(String name, String number){
        if (name == null|| number == null){
            throw new NullPointerException("name and number cannot be null");
        } if (name.isEmpty() || number.isEmpty()){
            throw new IllegalArgumentException("name and number cannot be empty");
        } if (!contacts.containsKey(name)){
            contacts.put(name, new Contact(name, number));
            return true;
        } else {
            return false;
        }
    }
    public boolean remove(String name){
     return contacts.remove(name) != null;
    }
    public List<Contact> findByName(String name){
        List<Contact> result = new ArrayList<>();
        for (var entry : contacts.entrySet()){
            if (entry.getKey().contains(name))
                result.add(entry.getValue());
        }
        return result;
    }
    public List<Contact> findByTelephone(String number) {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts.values()) {
            if(contact.getNumber().contains(number))
                result.add(contact);
        }
        return result;
    }

    @Override
    public Iterator<Contact> iterator() {
        return contacts.values().iterator();
    }

    @Override
    public void forEach(Consumer<? super Contact> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Contact> spliterator() {
        return Iterable.super.spliterator();
    }
}



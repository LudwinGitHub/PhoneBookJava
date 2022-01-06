public class Contact implements Comparable<Contact> {
    private String name;
    private String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return name + ": " + number;
    }
    @Override
    public int compareTo(Contact c){
        return this.name.compareTo(c.name);
    }
    public String toCSV(){
        return name + ": " + number;
    }
}
import java.util.NoSuchElementException;

public enum TELEOPTIONS {
    ADD_CONTACT(0, "Add new contact"),
    SEARCH_BY_NAME(1, "Find contact by name"),
    SEARCH_BY_TEL(2, "Find contact by number"),
    DELETE(3, "Delete contact"),
    CLOSE(4, "End");

    private final int shortcut;
    private final String description;

    TELEOPTIONS(int shortcut, String description) {
        this.shortcut = shortcut;
        this.description = description;
    }

    public int getShortcut() {
        return shortcut;
    }

    public String getDescription() {
        return description;
    }

    static TELEOPTIONS convertToOption(int option) {
        if (option >= values().length || option < 0)
            throw new NoSuchElementException();
        return values()[option];
    }

    @Override
    public String toString() {
        return shortcut + " " + description;
    }
}
package seedu.guestbook.model;

import javafx.collections.ObservableList;
import seedu.guestbook.model.person.Person;

/**
 * Unmodifiable view of an guestbook book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}

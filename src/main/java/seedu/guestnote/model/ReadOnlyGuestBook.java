package seedu.guestnote.model;

import javafx.collections.ObservableList;
import seedu.guestnote.model.guest.Guest;

/**
 * Unmodifiable view of an guestnote book
 */
public interface ReadOnlyGuestBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Guest> getGuestList();

}

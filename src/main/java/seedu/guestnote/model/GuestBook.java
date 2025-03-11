package seedu.guestnote.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.guestnote.commons.util.ToStringBuilder;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.UniquePersonList;

/**
 * Wraps all data at the guestnote-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class GuestBook implements ReadOnlyGuestBook {

    private final UniquePersonList persons = new UniquePersonList();

    public GuestBook() {}

    /**
     * Creates an GuestBook using the Persons in the {@code toBeCopied}
     */
    public GuestBook(ReadOnlyGuestBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the guest list with {@code guests}.
     * {@code guests} must not contain duplicate guests.
     */
    public void setPersons(List<Guest> guests) {
        this.persons.setPersons(guests);
    }

    /**
     * Resets the existing data of this {@code GuestBook} with {@code newData}.
     */
    public void resetData(ReadOnlyGuestBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// guest-level operations

    /**
     * Returns true if a guest with the same identity as {@code guest} exists in the guestnote book.
     */
    public boolean hasPerson(Guest guest) {
        requireNonNull(guest);
        return persons.contains(guest);
    }

    /**
     * Adds a guest to the guestnote book.
     * The guest must not already exist in the guestnote book.
     */
    public void addPerson(Guest p) {
        persons.add(p);
    }

    /**
     * Replaces the given guest {@code target} in the list with {@code editedGuest}.
     * {@code target} must exist in the guestnote book.
     * The guest identity of {@code editedGuest} must not be the same as another existing guest in the book.
     */
    public void setPerson(Guest target, Guest editedGuest) {
        requireNonNull(editedGuest);

        persons.setPerson(target, editedGuest);
    }

    /**
     * Removes {@code key} from this {@code GuestBook}.
     * {@code key} must exist in the guestnote book.
     */
    public void removePerson(Guest key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("guests", persons)
                .toString();
    }

    @Override
    public ObservableList<Guest> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GuestBook)) {
            return false;
        }

        GuestBook otherGuestBook = (GuestBook) other;
        return persons.equals(otherGuestBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

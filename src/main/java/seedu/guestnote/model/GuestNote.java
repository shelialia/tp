package seedu.guestnote.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.guestnote.commons.util.ToStringBuilder;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.UniqueGuestList;

/**
 * Wraps all data at the guestnote-book level
 * Duplicates are not allowed (by .isSameGuest comparison)
 */
public class GuestNote implements ReadOnlyGuestNote {

    private final UniqueGuestList guests = new UniqueGuestList();

    public GuestNote() {}

    /**
     * Creates an GuestNote using the Guests in the {@code toBeCopied}
     */
    public GuestNote(ReadOnlyGuestNote toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the guest list with {@code guests}.
     * {@code guests} must not contain duplicate guests.
     */
    public void setGuests(List<Guest> guests) {
        this.guests.setGuests(guests);
    }

    /**
     * Resets the existing data of this {@code GuestNote} with {@code newData}.
     */
    public void resetData(ReadOnlyGuestNote newData) {
        requireNonNull(newData);

        setGuests(newData.getGuestList());
    }

    //// guest-level operations

    /**
     * Returns true if a guest with the same identity as {@code guest} exists in the guestnote book.
     */
    public boolean hasGuest(Guest guest) {
        requireNonNull(guest);
        return guests.contains(guest);
    }

    /**
     * Adds a guest to the guestnote book.
     * The guest must not already exist in the guestnote book.
     */
    public void addGuest(Guest p) {
        guests.add(p);
    }

    /**
     * Replaces the given guest {@code target} in the list with {@code editedGuest}.
     * {@code target} must exist in the guestnote book.
     * The guest identity of {@code editedGuest} must not be the same as another existing guest in the book.
     */
    public void setGuest(Guest target, Guest editedGuest) {
        requireNonNull(editedGuest);

        guests.setGuest(target, editedGuest);
    }

    /**
     * Removes {@code key} from this {@code GuestNote}.
     * {@code key} must exist in the guestnote book.
     */
    public void removeGuest(Guest key) {
        guests.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("guests", guests)
                .toString();
    }

    @Override
    public ObservableList<Guest> getGuestList() {
        return guests.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GuestNote)) {
            return false;
        }

        GuestNote otherGuestNote = (GuestNote) other;
        return guests.equals(otherGuestNote.guests);
    }

    @Override
    public int hashCode() {
        return guests.hashCode();
    }
}

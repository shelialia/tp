package seedu.guestnote.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guestnote.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.guestnote.model.guest.exceptions.DuplicatePersonException;
import seedu.guestnote.model.guest.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A guest is considered unique by comparing using {@code Guest#isSamePerson(Guest)}. As such, adding and updating of
 * persons uses Guest#isSamePerson(Guest) for equality so as to ensure that the guest being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a guest uses Guest#equals(Object) so
 * as to ensure that the guest with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Guest#isSamePerson(Guest)
 */
public class UniquePersonList implements Iterable<Guest> {

    private final ObservableList<Guest> internalList = FXCollections.observableArrayList();
    private final ObservableList<Guest> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent guest as the given argument.
     */
    public boolean contains(Guest toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a guest to the list.
     * The guest must not already exist in the list.
     */
    public void add(Guest toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the guest {@code target} in the list with {@code editedGuest}.
     * {@code target} must exist in the list.
     * The guest identity of {@code editedGuest} must not be the same as another existing guest in the list.
     */
    public void setPerson(Guest target, Guest editedGuest) {
        requireAllNonNull(target, editedGuest);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedGuest) && contains(editedGuest)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedGuest);
    }

    /**
     * Removes the equivalent guest from the list.
     * The guest must exist in the list.
     */
    public void remove(Guest toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code guests}.
     * {@code guests} must not contain duplicate guests.
     */
    public void setPersons(List<Guest> guests) {
        requireAllNonNull(guests);
        if (!personsAreUnique(guests)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(guests);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Guest> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Guest> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniquePersonList)) {
            return false;
        }

        UniquePersonList otherUniquePersonList = (UniquePersonList) other;
        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code guests} contains only unique guests.
     */
    private boolean personsAreUnique(List<Guest> guests) {
        for (int i = 0; i < guests.size() - 1; i++) {
            for (int j = i + 1; j < guests.size(); j++) {
                if (guests.get(i).isSamePerson(guests.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

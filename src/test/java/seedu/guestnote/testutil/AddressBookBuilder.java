package seedu.guestnote.testutil;

import seedu.guestnote.model.GuestBook;
import seedu.guestnote.model.guest.Guest;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code GuestBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private GuestBook guestBook;

    public AddressBookBuilder() {
        guestBook = new GuestBook();
    }

    public AddressBookBuilder(GuestBook guestBook) {
        this.guestBook = guestBook;
    }

    /**
     * Adds a new {@code Guest} to the {@code GuestBook} that we are building.
     */
    public AddressBookBuilder withPerson(Guest guest) {
        guestBook.addPerson(guest);
        return this;
    }

    public GuestBook build() {
        return guestBook;
    }
}

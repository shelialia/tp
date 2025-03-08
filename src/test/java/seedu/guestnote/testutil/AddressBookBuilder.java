package seedu.guestnote.testutil;

import seedu.guestnote.model.GuestBook;
import seedu.guestnote.model.person.Person;

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
     * Adds a new {@code Person} to the {@code GuestBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        guestBook.addPerson(person);
        return this;
    }

    public GuestBook build() {
        return guestBook;
    }
}

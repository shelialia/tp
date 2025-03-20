package seedu.guestnote.model.util;

import java.util.Arrays;

import seedu.guestnote.model.GuestBook;
import seedu.guestnote.model.ReadOnlyGuestBook;
import seedu.guestnote.model.guest.Email;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.Name;
import seedu.guestnote.model.guest.Phone;
import seedu.guestnote.model.guest.RoomNumber;
import seedu.guestnote.model.request.Request;
import seedu.guestnote.model.request.UniqueRequestList;

/**
 * Contains utility methods for populating {@code GuestBook} with sample data.
 */
public class SampleDataUtil {
    public static Guest[] getSamplePersons() {
        return new Guest[] {
            new Guest(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new RoomNumber("12-33"),
                getRequestList("friends")),
            new Guest(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new RoomNumber("23-32"),
                getRequestList("colleagues", "friends")),
            new Guest(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new RoomNumber("01-57"),
                getRequestList("neighbours")),
            new Guest(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new RoomNumber("04-22"),
                getRequestList("family")),
            new Guest(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new RoomNumber("02-23"),
                getRequestList("classmates")),
            new Guest(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new RoomNumber("11-33"),
                getRequestList("colleagues"))
        };
    }

    public static ReadOnlyGuestBook getSampleAddressBook() {
        GuestBook sampleAb = new GuestBook();
        for (Guest sampleGuest : getSamplePersons()) {
            sampleAb.addGuest(sampleGuest);
        }
        return sampleAb;
    }

    /**
     * Returns a request set containing the list of strings given.
     */
    public static UniqueRequestList getRequestList(String... strings) {
        UniqueRequestList requestList = new UniqueRequestList();
        Arrays.stream(strings)
                .map(Request::new)
                .forEach(requestList::add);
        return requestList;
    }

}

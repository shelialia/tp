package seedu.guestnote.model.util;

import java.util.Arrays;

import seedu.guestnote.model.GuestBook;
import seedu.guestnote.model.ReadOnlyGuestBook;
import seedu.guestnote.model.guest.Email;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.Name;
import seedu.guestnote.model.guest.Phone;
import seedu.guestnote.model.guest.RoomNumber;
import seedu.guestnote.model.guest.Status;
import seedu.guestnote.model.request.Request;
import seedu.guestnote.model.request.UniqueRequestList;

/**
 * Contains utility methods for populating {@code GuestBook} with sample data.
 */
public class SampleDataUtil {
    public static Guest[] getSampleGuests() {
        return new Guest[] {
            new Guest(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new RoomNumber("12-33"), Status.BOOKED,
                getRequestList("Extra Pillow")),
            new Guest(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new RoomNumber("23-32"), Status.CHECKED_IN,
                getRequestList("High Floor", "Sea View Suite")),
            new Guest(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new RoomNumber("01-57"), Status.CHECKED_OUT,
                getRequestList("Extra Luggage Rack")),
            new Guest(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new RoomNumber("04-22"), Status.BOOKED,
                getRequestList("Extra Towel")),
            new Guest(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new RoomNumber("02-23"), Status.BOOKED,
                getRequestList("Airport Transfer")),
            new Guest(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new RoomNumber("11-33"), Status.BOOKED, new UniqueRequestList())
        };
    }

    public static ReadOnlyGuestBook getSampleAddressBook() {
        GuestBook sampleAb = new GuestBook();
        for (Guest sampleGuest : getSampleGuests()) {
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

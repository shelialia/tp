package seedu.guestnote.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.guestnote.commons.exceptions.IllegalValueException;
import seedu.guestnote.model.GuestBook;
import seedu.guestnote.model.ReadOnlyGuestBook;
import seedu.guestnote.model.guest.Guest;

/**
 * An Immutable GuestBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableGuestBook {

    public static final String MESSAGE_DUPLICATE_GUEST = "Guests list contains duplicate guest(s).";

    private final List<JsonAdaptedGuest> guests = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableGuestBook} with the given guests.
     */
    @JsonCreator
    public JsonSerializableGuestBook(@JsonProperty("guests") List<JsonAdaptedGuest> guests) {
        this.guests.addAll(guests);
    }

    /**
     * Converts a given {@code ReadOnlyGuestBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableGuestBook}.
     */
    public JsonSerializableGuestBook(ReadOnlyGuestBook source) {
        guests.addAll(source.getGuestList().stream().map(JsonAdaptedGuest::new).collect(Collectors.toList()));
    }

    /**
     * Converts this guestnote book into the model's {@code GuestBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public GuestBook toModelType() throws IllegalValueException {
        GuestBook guestBook = new GuestBook();
        for (JsonAdaptedGuest jsonAdaptedGuest : guests) {
            Guest guest = jsonAdaptedGuest.toModelType();
            if (guestBook.hasGuest(guest)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GUEST);
            }
            guestBook.addGuest(guest);
        }
        return guestBook;
    }

}

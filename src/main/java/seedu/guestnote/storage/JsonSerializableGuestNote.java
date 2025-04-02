package seedu.guestnote.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.guestnote.commons.exceptions.IllegalValueException;
import seedu.guestnote.model.GuestNote;
import seedu.guestnote.model.ReadOnlyGuestNote;
import seedu.guestnote.model.guest.Guest;

/**
 * An Immutable GuestNote that is serializable to JSON format.
 */
@JsonRootName(value = "guestNote")
class JsonSerializableGuestNote {

    public static final String MESSAGE_DUPLICATE_GUEST = "Guests list contains duplicate guest(s).";

    private final List<JsonAdaptedGuest> guests = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableGuestNote} with the given guests.
     */
    @JsonCreator
    public JsonSerializableGuestNote(@JsonProperty("guests") List<JsonAdaptedGuest> guests) {
        this.guests.addAll(guests);
    }

    /**
     * Converts a given {@code ReadOnlyGuestNote} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableGuestNote}.
     */
    public JsonSerializableGuestNote(ReadOnlyGuestNote source) {
        guests.addAll(source.getGuestList().stream().map(JsonAdaptedGuest::new).collect(Collectors.toList()));
    }

    /**
     * Converts this guestnote book into the model's {@code GuestNote} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public GuestNote toModelType() throws IllegalValueException {
        GuestNote guestNote = new GuestNote();
        for (JsonAdaptedGuest jsonAdaptedGuest : guests) {
            Guest guest = jsonAdaptedGuest.toModelType();
            if (guestNote.hasGuest(guest)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GUEST);
            }
            guestNote.addGuest(guest);
        }
        return guestNote;
    }

}

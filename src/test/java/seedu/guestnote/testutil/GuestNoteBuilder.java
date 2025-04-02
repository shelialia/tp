package seedu.guestnote.testutil;

import seedu.guestnote.model.GuestNote;
import seedu.guestnote.model.guest.Guest;

/**
 * A utility class to help with building GuestNote objects.
 * Example usage: <br>
 *     {@code GuestNote gn = new GuestNoteBuilder().withGuest("John", "Doe").build();}
 */
public class GuestNoteBuilder {

    private GuestNote guestNote;

    public GuestNoteBuilder() {
        guestNote = new GuestNote();
    }

    public GuestNoteBuilder(GuestNote guestNote) {
        this.guestNote = guestNote;
    }

    /**
     * Adds a new {@code Guest} to the {@code GuestNote} that we are building.
     */
    public GuestNoteBuilder withGuest(Guest guest) {
        guestNote.addGuest(guest);
        return this;
    }

    public GuestNote build() {
        return guestNote;
    }
}

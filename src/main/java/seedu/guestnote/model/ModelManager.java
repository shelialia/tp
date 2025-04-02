package seedu.guestnote.model;

import static java.util.Objects.requireNonNull;
import static seedu.guestnote.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.guestnote.commons.core.GuiSettings;
import seedu.guestnote.commons.core.LogsCenter;
import seedu.guestnote.model.guest.Guest;

/**
 * Represents the in-memory model of the guestnote book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final GuestNote guestNote;
    private final UserPrefs userPrefs;
    private final FilteredList<Guest> filteredGuests;

    /**
     * Initializes a ModelManager with the given guestNote and userPrefs.
     */
    public ModelManager(ReadOnlyGuestNote guestNote, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(guestNote, userPrefs);

        logger.fine("Initializing with guestnote book: " + guestNote + " and user prefs " + userPrefs);

        this.guestNote = new GuestNote(guestNote);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredGuests = new FilteredList<>(this.guestNote.getGuestList());
    }

    public ModelManager() {
        this(new GuestNote(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getGuestNoteFilePath() {
        return userPrefs.getGuestNoteFilePath();
    }

    @Override
    public void setGuestNoteFilePath(Path guestNoteFilePath) {
        requireNonNull(guestNoteFilePath);
        userPrefs.setGuestNoteFilePath(guestNoteFilePath);
    }

    //=========== GuestNote ================================================================================

    @Override
    public void setGuestNote(ReadOnlyGuestNote guestNote) {
        this.guestNote.resetData(guestNote);
    }

    @Override
    public ReadOnlyGuestNote getGuestNote() {
        return guestNote;
    }

    @Override
    public boolean hasGuest(Guest guest) {
        requireNonNull(guest);
        return guestNote.hasGuest(guest);
    }

    @Override
    public void deleteGuest(Guest target) {
        guestNote.removeGuest(target);
    }

    @Override
    public void addGuest(Guest guest) {
        guestNote.addGuest(guest);
        updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);
    }

    @Override
    public void setGuest(Guest target, Guest editedGuest) {
        requireAllNonNull(target, editedGuest);

        guestNote.setGuest(target, editedGuest);
    }

    //=========== Filtered Guest List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Guest} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Guest> getFilteredGuestList() {
        return filteredGuests;
    }

    @Override
    public void updateFilteredGuestList(Predicate<Guest> predicate) {
        requireNonNull(predicate);
        filteredGuests.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return guestNote.equals(otherModelManager.guestNote)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredGuests.equals(otherModelManager.filteredGuests);
    }

}

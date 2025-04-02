package seedu.guestnote.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.guestnote.commons.exceptions.DataLoadingException;
import seedu.guestnote.model.GuestNote;
import seedu.guestnote.model.ReadOnlyGuestNote;

/**
 * Represents a storage for {@link GuestNote}.
 */
public interface GuestNoteStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getGuestNoteFilePath();

    /**
     * Returns GuestNote data as a {@link ReadOnlyGuestNote}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyGuestNote> readGuestNote() throws DataLoadingException;

    /**
     * @see #getGuestNoteFilePath()
     */
    Optional<ReadOnlyGuestNote> readGuestNote(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyGuestNote} to the storage.
     * @param guestNote cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveGuestNote(ReadOnlyGuestNote guestNote) throws IOException;

    /**
     * @see #saveGuestNote(ReadOnlyGuestNote)
     */
    void saveGuestNote(ReadOnlyGuestNote addressBook, Path filePath) throws IOException;

}

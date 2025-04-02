package seedu.guestnote.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.guestnote.commons.core.LogsCenter;
import seedu.guestnote.commons.exceptions.DataLoadingException;
import seedu.guestnote.commons.exceptions.IllegalValueException;
import seedu.guestnote.commons.util.FileUtil;
import seedu.guestnote.commons.util.JsonUtil;
import seedu.guestnote.model.ReadOnlyGuestNote;

/**
 * A class to access GuestNote data stored as a json file on the hard disk.
 */
public class JsonGuestNoteStorage implements GuestNoteStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonGuestNoteStorage.class);

    private Path filePath;

    public JsonGuestNoteStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getGuestNoteFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGuestNote> readGuestNote() throws DataLoadingException {
        return readGuestNote(filePath);
    }

    /**
     * Similar to {@link #readGuestNote()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyGuestNote> readGuestNote(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableGuestNote> jsonGuestNote = JsonUtil.readJsonFile(
                filePath, JsonSerializableGuestNote.class);
        if (!jsonGuestNote.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonGuestNote.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveGuestNote(ReadOnlyGuestNote guestNote) throws IOException {
        saveGuestNote(guestNote, filePath);
    }

    /**
     * Similar to {@link #saveGuestNote(ReadOnlyGuestNote)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveGuestNote(ReadOnlyGuestNote guestNote, Path filePath) throws IOException {
        requireNonNull(guestNote);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGuestNote(guestNote), filePath);
    }

}

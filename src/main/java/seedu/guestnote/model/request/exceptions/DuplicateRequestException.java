package seedu.guestnote.model.request.exceptions;

/**
 * Signals that the operation will result in duplicate Requests
 * (Requests are considered duplicates if they have the same identity).
 */
public class DuplicateRequestException extends RuntimeException {
    public DuplicateRequestException() {
        super("Operation would result in duplicate requests");
    }
}

package seedu.guestnote.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.guestnote.commons.exceptions.IllegalValueException;
import seedu.guestnote.model.request.Request;

/**
 * Jackson-friendly version of {@link Request}.
 */
class JsonAdaptedRequest {

    private final String requestName;

    /**
     * Constructs a {@code JsonAdaptedRequest} with the given {@code requestName}.
     */
    @JsonCreator
    public JsonAdaptedRequest(String requestName) {
        this.requestName = requestName;
    }

    /**
     * Converts a given {@code Request} into this class for Jackson use.
     */
    public JsonAdaptedRequest(Request source) {
        requestName = source.requestName;
    }

    @JsonValue
    public String getRequestName() {
        return requestName;
    }

    /**
     * Converts this Jackson-friendly adapted request object into the model's {@code Request} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted request.
     */
    public Request toModelType() throws IllegalValueException {
        if (!Request.isValidRequestName(requestName)) {
            throw new IllegalValueException(Request.MESSAGE_CONSTRAINTS);
        }
        return new Request(requestName);
    }

}

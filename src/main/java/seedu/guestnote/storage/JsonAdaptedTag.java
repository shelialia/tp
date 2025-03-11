package seedu.guestnote.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.guestnote.commons.exceptions.IllegalValueException;
import seedu.guestnote.model.request.Request;

/**
 * Jackson-friendly version of {@link Request}.
 */
class JsonAdaptedTag {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Request} into this class for Jackson use.
     */
    public JsonAdaptedTag(seedu.guestnote.model.request.Request source) {
        tagName = source.tagName;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted request object into the model's {@code Request} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted request.
     */
    public seedu.guestnote.model.request.Request toModelType() throws IllegalValueException {
        if (!seedu.guestnote.model.request.Request.isValidTagName(tagName)) {
            throw new IllegalValueException(seedu.guestnote.model.request.Request.MESSAGE_CONSTRAINTS);
        }
        return new seedu.guestnote.model.request.Request(tagName);
    }

}

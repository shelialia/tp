package seedu.guestnote.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.guestnote.commons.exceptions.IllegalValueException;
import seedu.guestnote.model.guest.Email;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.Name;
import seedu.guestnote.model.guest.Phone;
import seedu.guestnote.model.guest.RoomNumber;
import seedu.guestnote.model.guest.Status;
import seedu.guestnote.model.request.UniqueRequestList;

/**
 * Jackson-friendly version of {@link Guest}.
 */
class JsonAdaptedGuest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Guest's %s field is missing!";

    private final String name;
    private final String phone;  // Can be null if not provided
    private final String email;
    private final String roomNumber;
    private final String status;
    private final List<JsonAdaptedRequest> requests = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedGuest} with the given guest details.
     */
    @JsonCreator
    public JsonAdaptedGuest(
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("roomNumber") String roomNumber,
            @JsonProperty("status") String status,
            @JsonProperty("requests") List<JsonAdaptedRequest> requests) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.roomNumber = roomNumber;
        this.status = status;
        if (requests != null) {
            this.requests.addAll(requests);
        }
    }

    /**
     * Converts a given {@code Guest} into this class for Jackson use.
     */
    public JsonAdaptedGuest(Guest source) {
        name = source.getName().fullName;
        // If the phone is present, we store it as a string, otherwise null.
        phone = source.getPhone().toString();
        email = source.getEmail().value;
        roomNumber = source.getRoomNumber().roomNumber;
        status = source.getStatus().name();
        requests.addAll(source.getRequests().stream()
                .map(JsonAdaptedRequest::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted guest object into the model's {@code Guest} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted guest.
     */
    public Guest toModelType() throws IllegalValueException {
        final List<seedu.guestnote.model.request.Request> guestRequests = new ArrayList<>();
        for (JsonAdaptedRequest request : requests) {
            guestRequests.add(request.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        // If phone is not null, validate and create the Phone object; otherwise, set it as Optional.empty()
        Optional<Phone> modelPhone = Optional.empty();
        if (phone != null) {
            if (!Phone.isValidPhone(phone)) {
                throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
            }
            modelPhone = Optional.of(new Phone(phone));  // Wrap in Optional
        }

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (roomNumber == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName())
            );
        }
        if (!RoomNumber.isValidRoomNumber(roomNumber)) {
            throw new IllegalValueException(RoomNumber.MESSAGE_CONSTRAINTS);
        }
        final RoomNumber modelRoomNumber = new RoomNumber(roomNumber);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        final Status modelStatus = Status.valueOf(status);

        final UniqueRequestList modelRequests = new UniqueRequestList();
        modelRequests.setRequests(guestRequests);

        // Create a new Guest, passing the Optional<Phone> value
        return new Guest(modelName, modelPhone, modelEmail, modelRoomNumber, modelStatus, modelRequests);
    }

}
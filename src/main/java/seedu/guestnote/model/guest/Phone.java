package seedu.guestnote.model.guest;

import static seedu.guestnote.commons.util.AppUtil.checkArgument;

import java.util.Optional;

/**
 * Represents a Guest's phone number in the guestnote book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final Optional<String> value;

    /**
     * Constructs a {@code Phone}.
     * If phone is null, it creates an Optional.empty().
     * Otherwise, it validates the phone number and stores it.
     *
     * @param phone A valid phone number or null if the phone is absent.
     */
    public Phone(String phone) {
        if (phone == null) {
            this.value = Optional.empty();  // Phone is optional, so we allow null values.
        } else {
            checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
            this.value = Optional.of(phone);
        }
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the phone number value as a String, if present.
     */
    public Optional<String> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.orElse("");  // Returns an empty string if the phone is absent
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
package seedu.guestnote.model.guest;

import static seedu.guestnote.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.guestnote.commons.util.ToStringBuilder;
import seedu.guestnote.model.request.Request;

/**
 * Represents a Guest in the guestnote book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Guest {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Request> requests = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Guest(Name name, Phone phone, Email email, Address address, Set<Request> requests) {
        requireAllNonNull(name, phone, email, address, requests);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.requests.addAll(requests);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable request set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Request> getTags() {
        return Collections.unmodifiableSet(requests);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameGuest(Guest otherGuest) {
        if (otherGuest == this) {
            return true;
        }

        return otherGuest != null
                && otherGuest.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Guest)) {
            return false;
        }

        Guest otherGuest = (Guest) other;
        return name.equals(otherGuest.name)
                && phone.equals(otherGuest.phone)
                && email.equals(otherGuest.email)
                && address.equals(otherGuest.address)
                && requests.equals(otherGuest.requests);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, requests);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("guestnote", address)
                .add("requests", requests)
                .toString();
    }

}

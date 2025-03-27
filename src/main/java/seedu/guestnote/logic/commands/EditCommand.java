package seedu.guestnote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.guestnote.logic.parser.CliSyntax.PREFIX_ADD_REQUEST;
import static seedu.guestnote.logic.parser.CliSyntax.PREFIX_DELETE_REQUEST;
import static seedu.guestnote.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.guestnote.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.guestnote.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.guestnote.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.guestnote.model.Model.PREDICATE_SHOW_ALL_GUESTS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.guestnote.commons.core.index.Index;
import seedu.guestnote.commons.util.CollectionUtil;
import seedu.guestnote.commons.util.ToStringBuilder;
import seedu.guestnote.logic.Messages;
import seedu.guestnote.logic.commands.exceptions.CommandException;
import seedu.guestnote.model.Model;
import seedu.guestnote.model.guest.Email;
import seedu.guestnote.model.guest.Guest;
import seedu.guestnote.model.guest.Name;
import seedu.guestnote.model.guest.Phone;
import seedu.guestnote.model.guest.RoomNumber;
import seedu.guestnote.model.guest.Status;
import seedu.guestnote.model.request.UniqueRequestList;

/**
 * Edits the details of an existing guest in the guestnote book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the guest identified "
            + "by the index number used in the displayed guest list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ROOMNUMBER + "ROOMNUMBER] "
            + "[" + PREFIX_ADD_REQUEST + "ADDREQUEST] "
            + "[" + PREFIX_DELETE_REQUEST + "DELETEREQUEST]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_GUEST_SUCCESS = "Edited Guest: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_GUEST = "This guest already exists in the guestnote book.";

    private final Index index;
    private final EditGuestDescriptor editGuestDescriptor;

    /**
     * @param index of the guest in the filtered guest list to edit
     * @param editGuestDescriptor details to edit the guest with
     */
    public EditCommand(Index index, EditGuestDescriptor editGuestDescriptor) {
        requireNonNull(index);
        requireNonNull(editGuestDescriptor);

        this.index = index;
        this.editGuestDescriptor = new EditGuestDescriptor(editGuestDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Guest> lastShownList = model.getFilteredGuestList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
        }

        Guest guestToEdit = lastShownList.get(index.getZeroBased());
        Guest editedGuest = createEditedGuest(guestToEdit, editGuestDescriptor);

        if (!guestToEdit.isSameGuest(editedGuest) && model.hasGuest(editedGuest)) {
            throw new CommandException(MESSAGE_DUPLICATE_GUEST);
        }

        model.setGuest(guestToEdit, editedGuest);
        model.updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);
        return new CommandResult(String.format(MESSAGE_EDIT_GUEST_SUCCESS, Messages.format(editedGuest)));
    }

    /**
     * Creates and returns a {@code Guest} with the details of {@code guestToEdit}
     * edited with {@code editGuestDescriptor}.
     */
    private static Guest createEditedGuest(Guest guestToEdit, EditGuestDescriptor editGuestDescriptor) {
        assert guestToEdit != null;

        Name updatedName = editGuestDescriptor.getName().orElse(guestToEdit.getName());
        Optional<Phone> updatedPhone = editGuestDescriptor.getPhone();
        Email updatedEmail = editGuestDescriptor.getEmail().orElse(guestToEdit.getEmail());
        RoomNumber updatedRoomNumber = editGuestDescriptor.getRoomNumber().orElse(guestToEdit.getRoomNumber());
        Status updatedStatus = guestToEdit.getStatus();

        // Extract existing requests and apply additions/removals
        UniqueRequestList updatedRequests = new UniqueRequestList();
        updatedRequests.setRequests(guestToEdit.getRequests());

        editGuestDescriptor.getRequestsToAdd().ifPresent(updatedRequests::addAll);
        editGuestDescriptor.getRequestsToDelete().ifPresent(updatedRequests::removeAll);

        return new Guest(updatedName, updatedPhone, updatedEmail, updatedRoomNumber, updatedStatus, updatedRequests);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editGuestDescriptor.equals(otherEditCommand.editGuestDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editGuestDescriptor", editGuestDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the guest with. Each non-empty field value will replace the
     * corresponding field value of the guest.
     */
    public static class EditGuestDescriptor {
        private Name name;
        private Optional<Phone> phone;
        private Email email;
        private RoomNumber roomNumber;
        private UniqueRequestList requests;
        private UniqueRequestList requestsToAdd;
        private UniqueRequestList requestsToDelete;

        public EditGuestDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code requests} is used internally.
         */
        public EditGuestDescriptor(EditGuestDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setRoomNumber(toCopy.roomNumber);
            setRequests(toCopy.requests);
            setRequestsToAdd(toCopy.requestsToAdd);
            setRequestsToDelete(toCopy.requestsToDelete);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, roomNumber, requestsToAdd, requestsToDelete);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Optional<Phone> phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return phone;
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setRoomNumber(RoomNumber roomNumber) {
            this.roomNumber = roomNumber;
        }

        public Optional<RoomNumber> getRoomNumber() {
            return Optional.ofNullable(roomNumber);
        }

        public void setRequests(UniqueRequestList requests) {
            this.requests = (requests != null) ? new UniqueRequestList() : null;
            if (requests != null) {
                this.requests.setRequests(requests);
            }
        }

        public Optional<UniqueRequestList> getRequests() {
            return (requests != null)
                    ? Optional.of(requests)
                    : Optional.empty();
        }

        /**
         * Sets {@code requestsToAdd} to this object's {@code requestsToAdd}.
         * A defensive copy of {@code requestsToAdd} is used internally.
         */
        public void setRequestsToAdd(UniqueRequestList requestsToAdd) {
            this.requestsToAdd = (requestsToAdd != null) ? new UniqueRequestList() : null;
            if (requestsToAdd != null) {
                this.requestsToAdd.setRequests(requestsToAdd);
            }
        }

        /**
         * Sets {@code requestsToDelete} to this object's {@code requestsToDelete}.
         * A defensive copy of {@code requestsToDelete} is used internally.
         */
        public void setRequestsToDelete(UniqueRequestList requestsToDelete) {
            this.requestsToDelete = (requestsToDelete != null) ? new UniqueRequestList() : null;
            if (requestsToDelete != null) {
                this.requestsToDelete.setRequests(requestsToDelete);
            }
        }

        /**
         * Returns an unmodifiable request set for adding, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code requests} is null.
         */
        public Optional<UniqueRequestList> getRequestsToAdd() {
            return (requestsToAdd != null)
                    ? Optional.of(requestsToAdd)
                    : Optional.empty();
        }

        /**
         * Returns an unmodifiable request set for deleting, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code requests} is null.
         */
        public Optional<UniqueRequestList> getRequestsToDelete() {
            return (requestsToDelete != null)
                    ? Optional.of(requestsToDelete)
                    : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditGuestDescriptor)) {
                return false;
            }

            EditGuestDescriptor otherEditPersonDescriptor = (EditGuestDescriptor) other;
            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(roomNumber, otherEditPersonDescriptor.roomNumber)
                    && Objects.equals(requestsToAdd, otherEditPersonDescriptor.requestsToAdd)
                    && Objects.equals(requestsToDelete, otherEditPersonDescriptor.requestsToDelete);
        }

        @Override
        public String toString() {
            System.out.println(phone);
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("roomNumber", roomNumber)
                    .add("requestsToAdd", requestsToAdd)
                    .add("requestsToDelete", requestsToDelete)
                    .toString();
        }
    }
}

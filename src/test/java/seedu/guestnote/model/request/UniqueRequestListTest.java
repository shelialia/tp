package seedu.guestnote.model.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guestnote.testutil.Assert.assertThrows;
import static seedu.guestnote.testutil.TypicalRequests.EXTRA_TOWELS;
import static seedu.guestnote.testutil.TypicalRequests.LATE_CHECKOUT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.guestnote.model.request.exceptions.DuplicateRequestException;
import seedu.guestnote.model.request.exceptions.RequestNotFoundException;

public class UniqueRequestListTest {

    private final UniqueRequestList uniqueRequestList = new UniqueRequestList();

    @Test
    public void contains_nullRequest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRequestList.contains(null));
    }

    @Test
    public void contains_requestNotInList_returnsFalse() {
        assertFalse(uniqueRequestList.contains(EXTRA_TOWELS));
    }

    @Test
    public void contains_requestInList_returnsTrue() {
        uniqueRequestList.add(EXTRA_TOWELS);
        assertTrue(uniqueRequestList.contains(EXTRA_TOWELS));
    }

    @Test
    public void contains_requestWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRequestList.add(EXTRA_TOWELS);
        Request duplicateExtraTowels = new Request("Extra Towels");
        assertTrue(uniqueRequestList.contains(duplicateExtraTowels));
    }

    @Test
    public void add_nullRequest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRequestList.add(null));
    }

    @Test
    public void add_duplicateRequest_throwsDuplicateRequestException() {
        uniqueRequestList.add(EXTRA_TOWELS);
        assertThrows(DuplicateRequestException.class, () -> uniqueRequestList.add(EXTRA_TOWELS));
    }

    @Test
    public void setRequest_nullTargetRequest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRequestList.setRequest(null, EXTRA_TOWELS));
    }

    @Test
    public void setRequest_nullEditedRequest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRequestList.setRequest(EXTRA_TOWELS, null));
    }

    @Test
    public void setRequest_targetRequestNotInList_throwsRequestNotFoundException() {
        assertThrows(RequestNotFoundException.class, () -> uniqueRequestList.setRequest(EXTRA_TOWELS, EXTRA_TOWELS));
    }

    @Test
    public void setRequest_editedRequestIsSameRequest_success() {
        uniqueRequestList.add(EXTRA_TOWELS);
        uniqueRequestList.setRequest(EXTRA_TOWELS, EXTRA_TOWELS);
        UniqueRequestList expectedUniqueRequestList = new UniqueRequestList();
        expectedUniqueRequestList.add(EXTRA_TOWELS);
        assertEquals(expectedUniqueRequestList, uniqueRequestList);
    }

    @Test
    public void setRequest_editedRequestHasSameIdentity_success() {
        uniqueRequestList.add(EXTRA_TOWELS);
        Request duplicateExtraTowels = new Request("Extra Towels");
        uniqueRequestList.setRequest(EXTRA_TOWELS, duplicateExtraTowels);
        UniqueRequestList expectedUniqueRequestList = new UniqueRequestList();
        expectedUniqueRequestList.add(duplicateExtraTowels);
        assertEquals(expectedUniqueRequestList, uniqueRequestList);
    }

    @Test
    public void setRequest_editedRequestHasDifferentIdentity_success() {
        uniqueRequestList.add(EXTRA_TOWELS);
        uniqueRequestList.setRequest(EXTRA_TOWELS, LATE_CHECKOUT);
        UniqueRequestList expectedUniqueRequestList = new UniqueRequestList();
        expectedUniqueRequestList.add(LATE_CHECKOUT);
        assertEquals(expectedUniqueRequestList, uniqueRequestList);
    }

    @Test
    public void setRequest_editedRequestHasNonUniqueIdentity_throwsDuplicateRequestException() {
        uniqueRequestList.add(EXTRA_TOWELS);
        uniqueRequestList.add(LATE_CHECKOUT);
        assertThrows(DuplicateRequestException.class, () -> uniqueRequestList.setRequest(EXTRA_TOWELS, LATE_CHECKOUT));
    }

    @Test
    public void remove_nullRequest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRequestList.remove(null));
    }

    @Test
    public void remove_requestDoesNotExist_throwsRequestNotFoundException() {
        assertThrows(RequestNotFoundException.class, () -> uniqueRequestList.remove(EXTRA_TOWELS));
    }

    @Test
    public void remove_existingRequest_removesRequest() {
        uniqueRequestList.add(EXTRA_TOWELS);
        uniqueRequestList.remove(EXTRA_TOWELS);
        UniqueRequestList expectedUniqueRequestList = new UniqueRequestList();
        assertEquals(expectedUniqueRequestList, uniqueRequestList);
    }

    @Test
    public void setRequests_nullUniqueRequestList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRequestList.setRequests((UniqueRequestList) null));
    }

    @Test
    public void setRequests_uniqueRequestList_replacesOwnListWithProvidedUniqueRequestList() {
        uniqueRequestList.add(EXTRA_TOWELS);
        UniqueRequestList expectedUniqueRequestList = new UniqueRequestList();
        expectedUniqueRequestList.add(LATE_CHECKOUT);
        uniqueRequestList.setRequests(expectedUniqueRequestList);
        assertEquals(expectedUniqueRequestList, uniqueRequestList);
    }

    @Test
    public void setRequests_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRequestList.setRequests((List<Request>) null));
    }

    @Test
    public void setRequests_list_replacesOwnListWithProvidedList() {
        uniqueRequestList.add(EXTRA_TOWELS);
        List<Request> requestList = Collections.singletonList(LATE_CHECKOUT);
        uniqueRequestList.setRequests(requestList);
        UniqueRequestList expectedUniqueRequestList = new UniqueRequestList();
        expectedUniqueRequestList.add(LATE_CHECKOUT);
        assertEquals(expectedUniqueRequestList, uniqueRequestList);
    }

    @Test
    public void setRequests_listWithDuplicateRequests_throwsDuplicateRequestException() {
        List<Request> listWithDuplicateRequests = Arrays.asList(EXTRA_TOWELS, EXTRA_TOWELS);
        assertThrows(DuplicateRequestException.class, () -> uniqueRequestList.setRequests(listWithDuplicateRequests));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueRequestList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueRequestList.asUnmodifiableObservableList().toString(), uniqueRequestList.toString());
    }
}

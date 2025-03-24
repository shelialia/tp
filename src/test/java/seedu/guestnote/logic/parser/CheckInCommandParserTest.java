package seedu.guestnote.logic.parser;

import static seedu.guestnote.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.guestnote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.guestnote.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.guestnote.testutil.Assert.assertThrows;
import static seedu.guestnote.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.guestnote.logic.commands.CheckInCommand;

/**
 * Parses input arguments and creates a new CheckInCommand object.
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the CheckInCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the CheckInCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class CheckInCommandParserTest {

    private final CheckInCommandParser parser = new CheckInCommandParser();

    @Test
    public void parse_validArgs_returnsCheckInCommand() throws Exception {
        assertParseSuccess(parser, "1", new CheckInCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CheckInCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckInCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_whitespaceArgs_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckInCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_boundaryValues_throwsParseException() {
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckInCommand.MESSAGE_USAGE));
    }
}

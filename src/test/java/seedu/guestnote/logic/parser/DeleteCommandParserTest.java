package seedu.guestnote.logic.parser;

import static seedu.guestnote.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.guestnote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.guestnote.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.guestnote.logic.parser.ParserUtil.MESSAGE_INDEX_HAS_LEADING_ZEROES;
import static seedu.guestnote.logic.parser.ParserUtil.MESSAGE_INDEX_TOO_LARGE;
import static seedu.guestnote.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.guestnote.testutil.TypicalIndexes.INDEX_FIRST_GUEST;

import org.junit.jupiter.api.Test;

import seedu.guestnote.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_GUEST));
    }

    @Test
    public void parse_nonNumeric_throwsParseException() {
        assertParseFailure(parser, "a",
                MESSAGE_INVALID_INDEX + "\n" + DeleteCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_leadingZero_throwsParseException() {
        assertParseFailure(parser, "00001",
                String.format(MESSAGE_INDEX_HAS_LEADING_ZEROES, "1", "00001") + "\n" + DeleteCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_overflow_throwsParseException() {
        assertParseFailure(parser, "10000000000000000000000000000000",
                MESSAGE_INDEX_TOO_LARGE + "\n" + DeleteCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}

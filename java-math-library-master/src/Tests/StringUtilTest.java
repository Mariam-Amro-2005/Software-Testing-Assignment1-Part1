package Tests;

import de.tilman_neumann.util.StringUtil;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StringUtilTest {

    @Test
    public void testRepeatWithNullString() {
        assertNull("Repeating a null string should return null", StringUtil.repeat(null, 5));
    }

    @Test
    public void testRepeatWithZeroN() {
        assertNull("Repeating with zero n should return null", StringUtil.repeat("test", 0));
    }

    @Test
    public void testRepeatWithNegativeN() {
        assertNull("Repeating with a negative n should return null", StringUtil.repeat("test", -5));
    }

    @Test
    public void testRepeatWithEmptyString() {
        assertEquals("Repeating an empty string should return an empty string", "", StringUtil.repeat("", 5));
    }

    @Test
    public void testRepeatWithValidString() {
        assertEquals("Should concatenate the string n times", "abcabcabc", StringUtil.repeat("abc", 3));
    }

    @Test
    public void testFormatLeftWithNullStringAndNullMask() {
        assertEquals("Should return empty string when both are null", "", StringUtil.formatLeft(null, null));
    }

    @Test
    public void testFormatLeftWithNullStringAndValidMask() {
        assertEquals("Should return the mask if string is null", "123456", StringUtil.formatLeft(null, "123456"));
    }

    @Test
    public void testFormatLeftWithValidStringAndNullMask() {
        assertEquals("Should return the string if mask is null", "abc", StringUtil.formatLeft("abc", null));
    }

    @Test
    public void testFormatLeftStringShorterThanMask() {
        assertEquals("Should pad the rest with mask's ending", "abc456", StringUtil.formatLeft("abc", "123456"));
    }

    @Test
    public void testFormatLeftStringLongerThanMask() {
        assertEquals("Should return string as is when longer than mask", "abcdef", StringUtil.formatLeft("abcdef", "123"));
    }

    @Test
    public void testFormatLeftStringEqualToMask() {
        assertEquals("Should return string when equal to mask length", "abc", StringUtil.formatLeft("abc", "123"));
    }

    @Test
    public void testFormatRightWithNullStringAndNullMask() {
        assertEquals("Should return empty string when both are null", "", StringUtil.formatRight(null, null));
    }

    @Test
    public void testFormatRightWithNullStringAndValidMask() {
        assertEquals("Should return the mask if string is null", "123456", StringUtil.formatRight(null, "123456"));
    }

    @Test
    public void testFormatRightWithValidStringAndNullMask() {
        assertEquals("Should return the string if mask is null", "abc", StringUtil.formatRight("abc", null));
    }

    @Test
    public void testFormatRightStringShorterThanMask() {
        assertEquals("Should pad the prefix with mask's start", "123abc", StringUtil.formatRight("abc", "123456"));
    }

    @Test
    public void testFormatRightStringLongerThanMask() {
        assertEquals("Should return string as is when longer than mask", "abcdef", StringUtil.formatRight("abcdef", "123"));
    }

    @Test
    public void testFormatRightStringEqualToMask() {
        assertEquals("Should return string when equal to mask length", "abc", StringUtil.formatRight("abc", "123"));
    }
}

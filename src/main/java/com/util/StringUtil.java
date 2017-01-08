/*
 *
 */
package com.util;



import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理方法
 * @author xu
 *
 */
public class StringUtil {

	public static final String EMPTY_STRING="";
	public static final String[] EMPTY_STRING_ARRAY=new String[0];
    /**
     * 获取字符串长度，如果字符串为空则返回0
     * @param str
     * @return
     */
    public static int getLength(String str) {
        return str == null ? 0 : str.length();
    }

    /**
     * 
     * <pre>
     * StringUtil.equalsIgnoreCase(null, null)   = true
     * StringUtil.equalsIgnoreCase(null, "abc")  = false
     * StringUtil.equalsIgnoreCase("abc", null)  = false
     * StringUtil.equalsIgnoreCase("abc", "abc") = true
     * StringUtil.equalsIgnoreCase("abc", "ABC") = true
     * </pre>
     * 
     */
    public static boolean isEqualsIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.equalsIgnoreCase(str2);
    }


    public static boolean isEmpty(List<String> str) {
        return str == null || str.size() == 0;
    }

    public static boolean isBlank(String str) {
        int length;

        if (str == null || (length = str.length()) == 0) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static String defaultIfEmpty(String str, String defaultStr) {
        return str == null || str.length() == 0 ? defaultStr : str;
    }

    public static String defaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String trimToNull(String str) {
        if (str == null) {
            return null;
        }

        String result = str.trim();

        if (result == null || result.length() == 0) {
            return null;
        }

        return result;
    }

    public static String trimToEmpty(String str) {
        if (str == null) {
            return EMPTY_STRING;
        }

        return str.trim();
    }

    public static String trim(String str, String stripChars) {
        return trim(str, stripChars, 0);
    }

    public static String trimStart(String str) {
        return trim(str, null, -1);
    }

    public static String trimStart(String str, String stripChars) {
        return trim(str, stripChars, -1);
    }

    public static String trimEnd(String str) {
        return trim(str, null, 1);
    }

    public static String trimEnd(String str, String stripChars) {
        return trim(str, stripChars, 1);
    }

    public static String trimToNull(String str, String stripChars) {
        String result = trim(str, stripChars);

        if (result == null || result.length() == 0) {
            return null;
        }

        return result;
    }

    public static String trimToEmpty(String str, String stripChars) {
        String result = trim(str, stripChars);

        if (result == null) {
            return EMPTY_STRING;
        }

        return result;
    }

    private static String trim(String str, String stripChars, int mode) {
        if (str == null) {
            return null;
        }

        int length = str.length();
        int start = 0;
        int end = length;

        if (mode <= 0) {
            if (stripChars == null) {
                while (start < end && Character.isWhitespace(str.charAt(start))) {
                    start++;
                }
            } else if (stripChars.length() == 0) {
                return str;
            } else {
                while (start < end && stripChars.indexOf(str.charAt(start)) != -1) {
                    start++;
                }
            }
        }

        if (mode >= 0) {
            if (stripChars == null) {
                while (start < end && Character.isWhitespace(str.charAt(end - 1))) {
                    end--;
                }
            } else if (stripChars.length() == 0) {
                return str;
            } else {
                while (start < end && stripChars.indexOf(str.charAt(end - 1)) != -1) {
                    end--;
                }
            }
        }

        if (start > 0 || end < length) {
            return str.substring(start, end);
        }

        return str;
    }

    public static String capitalize(String str) {
        int strLen;

        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        return new StringBuilder(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1))
                .toString();
    }

    public static String uncapitalize(String str) {
        int strLen;

        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        if (strLen > 1 && Character.isUpperCase(str.charAt(1)) && Character.isUpperCase(str.charAt(0))) {
            return str;
        }

        return new StringBuilder(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1))
                .toString();
    }

    public static String swapCase(String str) {
        int strLen;

        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        StringBuilder buffer = new StringBuilder(strLen);

        char ch = 0;

        for (int i = 0; i < strLen; i++) {
            ch = str.charAt(i);

            if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }

            buffer.append(ch);
        }

        return buffer.toString();
    }

    public static String toUpperCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toUpperCase();
    }

    public static String toLowerCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toLowerCase();
    }

    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = EMPTY_STRING;
        }

        int arraySize = array.length;
        int bufSize;

        if (arraySize == 0) {
            bufSize = 0;
        } else {
            int firstLength = array[0] == null ? 16 : array[0].toString().length();
            bufSize = arraySize * (firstLength + separator.length());
        }

        StringBuilder buf = new StringBuilder(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (separator != null && i > 0) {
                buf.append(separator);
            }

            if (array[i] != null) {
                buf.append(array[i]);
            }
        }

        return buf.toString();
    }

    public static String join(Iterable<?> list, String separator) {
        if (list == null) {
            return null;
        }

        StringBuilder buf = new StringBuilder(256); // JavaĬ��ֵ��16, ����ƫС

        for (Iterator<?> i = list.iterator(); i.hasNext();) {
            Object obj = i.next();

            if (obj != null) {
                buf.append(obj);
            }

            if (separator != null && i.hasNext()) {
                buf.append(separator);
            }
        }

        return buf.toString();
    }

    public static int indexOf(String str, char searchChar) {
        if (str == null || str.length() == 0) {
            return -1;
        }

        return str.indexOf(searchChar);
    }

    public static int indexOf(String str, char searchChar, int startPos) {
        if (str == null || str.length() == 0) {
            return -1;
        }

        return str.indexOf(searchChar, startPos);
    }

    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }

        return str.indexOf(searchStr);
    }

    public static int indexOf(String str, String searchStr, int startPos) {
        if (str == null || searchStr == null) {
            return -1;
        }

        // JDK1.3�����°汾��bug��������ȷ������������
        if (searchStr.length() == 0 && startPos >= str.length()) {
            return str.length();
        }

        return str.indexOf(searchStr, startPos);
    }

    public static int indexOfAny(String str, char[] searchChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length == 0) {
            return -1;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            for (char searchChar : searchChars) {
                if (searchChar == ch) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static int indexOfAny(String str, String searchChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length() == 0) {
            return -1;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            for (int j = 0; j < searchChars.length(); j++) {
                if (searchChars.charAt(j) == ch) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static int indexOfAny(String str, String[] searchStrs) {
        if (str == null || searchStrs == null) {
            return -1;
        }

        int sz = searchStrs.length;

        // String's can't have a MAX_VALUEth index.
        int ret = Integer.MAX_VALUE;

        int tmp = 0;

        for (int i = 0; i < sz; i++) {
            String search = searchStrs[i];

            if (search == null) {
                continue;
            }

            tmp = str.indexOf(search);

            if (tmp == -1) {
                continue;
            }

            if (tmp < ret) {
                ret = tmp;
            }
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    public static int indexOfAnyBut(String str, char[] searchChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length == 0) {
            return -1;
        }

        outer: for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            for (int j = 0; j < searchChars.length; j++) {
                if (searchChars[j] == ch) {
                    continue outer;
                }
            }

            return i;
        }

        return -1;
    }

    public static int indexOfAnyBut(String str, String searchChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length() == 0) {
            return -1;
        }

        for (int i = 0; i < str.length(); i++) {
            if (searchChars.indexOf(str.charAt(i)) < 0) {
                return i;
            }
        }

        return -1;
    }

    public static int lastIndexOf(String str, char searchChar) {
        if (str == null || str.length() == 0) {
            return -1;
        }

        return str.lastIndexOf(searchChar);
    }

    public static int lastIndexOf(String str, char searchChar, int startPos) {
        if (str == null || str.length() == 0) {
            return -1;
        }

        return str.lastIndexOf(searchChar, startPos);
    }

    public static int lastIndexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }

        return str.lastIndexOf(searchStr);
    }

    public static int lastIndexOf(String str, String searchStr, int startPos) {
        if (str == null || searchStr == null) {
            return -1;
        }

        return str.lastIndexOf(searchStr, startPos);
    }

    public static int lastIndexOfAny(String str, String[] searchStrs) {
        if (str == null || searchStrs == null) {
            return -1;
        }

        int searchStrsLength = searchStrs.length;
        int index = -1;
        int tmp = 0;

        for (int i = 0; i < searchStrsLength; i++) {
            String search = searchStrs[i];

            if (search == null) {
                continue;
            }

            tmp = str.lastIndexOf(search);

            if (tmp > index) {
                index = tmp;
            }
        }

        return index;
    }

    /**
     * ����ַ����Ƿ��ָ�����ַ�����ַ�Ϊ<code>null</code>��������<code>false</code>��
     * 
     * <pre>
     * StringUtil.contains(null, *)    = false
     * StringUtil.contains("", *)      = false
     * StringUtil.contains("abc", 'a') = true
     * StringUtil.contains("abc", 'z') = false
     * </pre>
     * 
     * @param str Ҫɨ����ַ�
     * @param searchChar Ҫ���ҵ��ַ�
     * @return ����ҵ����򷵻�<code>true</code>
     */
    public static boolean contains(String str, char searchChar) {
        if (str == null || str.length() == 0) {
            return false;
        }

        return str.indexOf(searchChar) >= 0;
    }

    /**
     * ����ַ����Ƿ��ָ�����ַ�����ַ�Ϊ<code>null</code>��������<code>false</code>��
     * 
     * <pre>
     * StringUtil.contains(null, *)     = false
     * StringUtil.contains(*, null)     = false
     * StringUtil.contains("", "")      = true
     * StringUtil.contains("abc", "")   = true
     * StringUtil.contains("abc", "a")  = true
     * StringUtil.contains("abc", "z")  = false
     * </pre>
     * 
     * @param str Ҫɨ����ַ�
     * @param searchStr Ҫ���ҵ��ַ�
     * @return ����ҵ����򷵻�<code>true</code>
     */
    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }

        return str.indexOf(searchStr) >= 0;
    }

    /**
     * ����ַ����Ƿ�ֻ��ָ���ַ���е��ַ�
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>false</code>�� ����ַ��Ϊ<code>null</code>
     * �򷵻�<code>false</code>�� ���ǿ��ַ���Զ����<code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtil.containsOnly(null, *)       = false
     * StringUtil.containsOnly(*, null)       = false
     * StringUtil.containsOnly("", *)         = true
     * StringUtil.containsOnly("ab", '')      = false
     * StringUtil.containsOnly("abab", 'abc') = true
     * StringUtil.containsOnly("ab1", 'abc')  = false
     * StringUtil.containsOnly("abz", 'abc')  = false
     * </pre>
     * 
     * @param str Ҫɨ����ַ�
     * @param valid Ҫ���ҵ��ַ�
     * @return ����ҵ����򷵻�<code>true</code>
     */
    public static boolean containsOnly(String str, char[] valid) {
        if (valid == null || str == null) {
            return false;
        }

        if (str.length() == 0) {
            return true;
        }

        if (valid.length == 0) {
            return false;
        }

        return indexOfAnyBut(str, valid) == -1;
    }

    /**
     * ����ַ����Ƿ�ֻ��ָ���ַ���е��ַ�
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>false</code>�� ����ַ��Ϊ<code>null</code>
     * �򷵻�<code>false</code>�� ���ǿ��ַ���Զ����<code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtil.containsOnly(null, *)       = false
     * StringUtil.containsOnly(*, null)       = false
     * StringUtil.containsOnly("", *)         = true
     * StringUtil.containsOnly("ab", "")      = false
     * StringUtil.containsOnly("abab", "abc") = true
     * StringUtil.containsOnly("ab1", "abc")  = false
     * StringUtil.containsOnly("abz", "abc")  = false
     * </pre>
     * 
     * @param str Ҫɨ����ַ�
     * @param valid Ҫ���ҵ��ַ�
     * @return ����ҵ����򷵻�<code>true</code>
     */
    public static boolean containsOnly(String str, String valid) {
        if (str == null || valid == null) {
            return false;
        }

        return containsOnly(str, valid.toCharArray());
    }

    /**
     * ����ַ����Ƿ񲻰�ָ���ַ���е��ַ�
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>false</code>�� ����ַ��Ϊ<code>null</code>
     * �򷵻�<code>true</code>�� ���ǿ��ַ���Զ����<code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtil.containsNone(null, *)       = true
     * StringUtil.containsNone(*, null)       = true
     * StringUtil.containsNone("", *)         = true
     * StringUtil.containsNone("ab", '')      = true
     * StringUtil.containsNone("abab", 'xyz') = true
     * StringUtil.containsNone("ab1", 'xyz')  = true
     * StringUtil.containsNone("abz", 'xyz')  = false
     * </pre>
     * 
     * @param str Ҫɨ����ַ�
     * @param invalid Ҫ���ҵ��ַ�
     * @return ����ҵ����򷵻�<code>true</code>
     */
    public static boolean containsNone(String str, char[] invalid) {
        if (str == null || invalid == null) {
            return true;
        }

        int strSize = str.length();
        int validSize = invalid.length;

        for (int i = 0; i < strSize; i++) {
            char ch = str.charAt(i);

            for (int j = 0; j < validSize; j++) {
                if (invalid[j] == ch) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * ����ַ����Ƿ񲻰�ָ���ַ���е��ַ�
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>false</code>�� ����ַ��Ϊ<code>null</code>
     * �򷵻�<code>true</code>�� ���ǿ��ַ���Զ����<code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtil.containsNone(null, *)       = true
     * StringUtil.containsNone(*, null)       = true
     * StringUtil.containsNone("", *)         = true
     * StringUtil.containsNone("ab", "")      = true
     * StringUtil.containsNone("abab", "xyz") = true
     * StringUtil.containsNone("ab1", "xyz")  = true
     * StringUtil.containsNone("abz", "xyz")  = false
     * </pre>
     * 
     * @param str Ҫɨ����ַ�
     * @param invalidChars Ҫ���ҵ��ַ�
     * @return ����ҵ����򷵻�<code>true</code>
     */
    public static boolean containsNone(String str, String invalidChars) {
        if (str == null || invalidChars == null) {
            return true;
        }

        return containsNone(str, invalidChars.toCharArray());
    }

    /**
     * ȡ��ָ���Ӵ����ַ��г��ֵĴ���
     * <p>
     * ����ַ�Ϊ<code>null</code>��գ��򷵻�<code>0</code>��
     * 
     * <pre>
     * StringUtil.countMatches(null, *)       = 0
     * StringUtil.countMatches("", *)         = 0
     * StringUtil.countMatches("abba", null)  = 0
     * StringUtil.countMatches("abba", "")    = 0
     * StringUtil.countMatches("abba", "a")   = 2
     * StringUtil.countMatches("abba", "ab")  = 1
     * StringUtil.countMatches("abba", "xxx") = 0
     * </pre>
     * 
     * </p>
     * 
     * @param str Ҫɨ����ַ�
     * @param subStr ���ַ�
     * @return �Ӵ����ַ��г��ֵĴ�������ַ�Ϊ<code>null</code>��գ��򷵻�<code>0</code>
     */
    public static int countMatches(String str, String subStr) {
        if (str == null || str.length() == 0 || subStr == null || subStr.length() == 0) {
            return 0;
        }

        int count = 0;
        int index = 0;

        while ((index = str.indexOf(subStr, index)) != -1) {
            count++;
            index += subStr.length();
        }

        return count;
    }

    // ==========================================================================
    // ȡ�Ӵ�����
    // ==========================================================================

    /**
     * ȡָ���ַ���Ӵ���
     * <p>
     * �����������β����ʼ���㡣����ַ�Ϊ<code>null</code>���򷵻�<code>null</code>��
     * 
     * <pre>
     * StringUtil.substring(null, *)   = null
     * StringUtil.substring("", *)     = ""
     * StringUtil.substring("abc", 0)  = "abc"
     * StringUtil.substring("abc", 2)  = "c"
     * StringUtil.substring("abc", 4)  = ""
     * StringUtil.substring("abc", -2) = "bc"
     * StringUtil.substring("abc", -4) = "abc"
     * </pre>
     * 
     * </p>
     * 
     * @param str �ַ�
     * @param start ��ʼ�������Ϊ�����ʾ��β������
     * @return �Ӵ������ԭʼ��Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String substring(String str, int start) {
        if (str == null) {
            return null;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }

        if (start > str.length()) {
            return EMPTY_STRING;
        }

        return str.substring(start);
    }

    /**
     * ȡָ���ַ���Ӵ���
     * <p>
     * �����������β����ʼ���㡣����ַ�Ϊ<code>null</code>���򷵻�<code>null</code>��
     * 
     * <pre>
     * StringUtil.substring(null, *, *)    = null
     * StringUtil.substring("", * ,  *)    = "";
     * StringUtil.substring("abc", 0, 2)   = "ab"
     * StringUtil.substring("abc", 2, 0)   = ""
     * StringUtil.substring("abc", 2, 4)   = "c"
     * StringUtil.substring("abc", 4, 6)   = ""
     * StringUtil.substring("abc", 2, 2)   = ""
     * StringUtil.substring("abc", -2, -1) = "b"
     * StringUtil.substring("abc", -4, 2)  = "ab"
     * </pre>
     * 
     * </p>
     * 
     * @param str �ַ�
     * @param start ��ʼ�������Ϊ�����ʾ��β������
     * @param end ����������������Ϊ�����ʾ��β������
     * @return �Ӵ������ԭʼ��Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        if (end < 0) {
            end = str.length() + end;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return EMPTY_STRING;
        }

        if (start < 0) {
            start = 0;
        }

        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * ȡ�ó���Ϊָ���ַ��������ߵ��Ӵ���
     * 
     * <pre>
     * StringUtil.left(null, *)    = null
     * StringUtil.left(*, -ve)     = ""
     * StringUtil.left("", *)      = ""
     * StringUtil.left("abc", 0)   = ""
     * StringUtil.left("abc", 2)   = "ab"
     * StringUtil.left("abc", 4)   = "abc"
     * </pre>
     * 
     * @param str �ַ�
     * @param len �����Ӵ��ĳ���
     * @return �Ӵ������ԭʼ�ִ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String left(String str, int len) {
        if (str == null) {
            return null;
        }

        if (len < 0) {
            return EMPTY_STRING;
        }

        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(0, len);
        }
    }

    /**
     * ȡ�ó���Ϊָ���ַ�������ұߵ��Ӵ���
     * 
     * <pre>
     * StringUtil.right(null, *)    = null
     * StringUtil.right(*, -ve)     = ""
     * StringUtil.right("", *)      = ""
     * StringUtil.right("abc", 0)   = ""
     * StringUtil.right("abc", 2)   = "bc"
     * StringUtil.right("abc", 4)   = "abc"
     * </pre>
     * 
     * @param str �ַ�
     * @param len �����Ӵ��ĳ���
     * @return �Ӵ������ԭʼ�ִ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String right(String str, int len) {
        if (str == null) {
            return null;
        }

        if (len < 0) {
            return EMPTY_STRING;
        }

        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(str.length() - len);
        }
    }

    /**
     * ȡ�ô�ָ������ʼ����ġ�����Ϊָ���ַ�����Ӵ���
     * 
     * <pre>
     * StringUtil.mid(null, *, *)    = null
     * StringUtil.mid(*, *, -ve)     = ""
     * StringUtil.mid("", 0, *)      = ""
     * StringUtil.mid("abc", 0, 2)   = "ab"
     * StringUtil.mid("abc", 0, 4)   = "abc"
     * StringUtil.mid("abc", 2, 4)   = "c"
     * StringUtil.mid("abc", 4, 2)   = ""
     * StringUtil.mid("abc", -2, 2)  = "ab"
     * </pre>
     * 
     * @param str �ַ�
     * @param pos ��ʼ�������Ϊ��������<code>0</code>
     * @param len �Ӵ��ĳ��ȣ����Ϊ������������Ϊ<code>0</code>
     * @return �Ӵ������ԭʼ�ִ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String mid(String str, int pos, int len) {
        if (str == null) {
            return null;
        }

        if (len < 0 || pos > str.length()) {
            return EMPTY_STRING;
        }

        if (pos < 0) {
            pos = 0;
        }

        if (str.length() <= pos + len) {
            return str.substring(pos);
        } else {
            return str.substring(pos, pos + len);
        }
    }

    // ==========================================================================
    // ������ȡ�Ӵ�����
    // ==========================================================================

    /**
     * ȡ�õ�һ�����ֵķָ��Ӵ�֮ǰ���Ӵ���
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>null</code>�� ���ָ��Ӵ�Ϊ<code>null</code>
     * ��δ�ҵ����Ӵ����򷵻�ԭ�ַ�
     * 
     * <pre>
     * StringUtil.substringBefore(null, *)      = null
     * StringUtil.substringBefore("", *)        = ""
     * StringUtil.substringBefore("abc", "a")   = ""
     * StringUtil.substringBefore("abcba", "b") = "a"
     * StringUtil.substringBefore("abc", "c")   = "ab"
     * StringUtil.substringBefore("abc", "d")   = "abc"
     * StringUtil.substringBefore("abc", "")    = ""
     * StringUtil.substringBefore("abc", null)  = "abc"
     * </pre>
     * 
     * </p>
     * 
     * @param str �ַ�
     * @param separator Ҫ�����ķָ��Ӵ�
     * @return �Ӵ������ԭʼ��Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String substringBefore(String str, String separator) {
        if (str == null || separator == null || str.length() == 0) {
            return str;
        }

        if (separator.length() == 0) {
            return EMPTY_STRING;
        }

        int pos = str.indexOf(separator);

        if (pos == -1) {
            return str;
        }

        return str.substring(0, pos);
    }

    /**
     * ȡ�õ�һ�����ֵķָ��Ӵ�֮����Ӵ���
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>null</code>�� ���ָ��Ӵ�Ϊ<code>null</code>
     * ��δ�ҵ����Ӵ����򷵻�ԭ�ַ�
     * 
     * <pre>
     * StringUtil.substringAfter(null, *)      = null
     * StringUtil.substringAfter("", *)        = ""
     * StringUtil.substringAfter(*, null)      = ""
     * StringUtil.substringAfter("abc", "a")   = "bc"
     * StringUtil.substringAfter("abcba", "b") = "cba"
     * StringUtil.substringAfter("abc", "c")   = ""
     * StringUtil.substringAfter("abc", "d")   = ""
     * StringUtil.substringAfter("abc", "")    = "abc"
     * </pre>
     * 
     * </p>
     * 
     * @param str �ַ�
     * @param separator Ҫ�����ķָ��Ӵ�
     * @return �Ӵ������ԭʼ��Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String substringAfter(String str, String separator) {
        if (str == null || str.length() == 0) {
            return str;
        }

        if (separator == null) {
            return EMPTY_STRING;
        }

        int pos = str.indexOf(separator);

        if (pos == -1) {
            return EMPTY_STRING;
        }

        return str.substring(pos + separator.length());
    }

    /**
     * ȡ�����һ���ķָ��Ӵ�֮ǰ���Ӵ���
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>null</code>�� ���ָ��Ӵ�Ϊ<code>null</code>
     * ��δ�ҵ����Ӵ����򷵻�ԭ�ַ�
     * 
     * <pre>
     * StringUtil.substringBeforeLast(null, *)      = null
     * StringUtil.substringBeforeLast("", *)        = ""
     * StringUtil.substringBeforeLast("abcba", "b") = "abc"
     * StringUtil.substringBeforeLast("abc", "c")   = "ab"
     * StringUtil.substringBeforeLast("a", "a")     = ""
     * StringUtil.substringBeforeLast("a", "z")     = "a"
     * StringUtil.substringBeforeLast("a", null)    = "a"
     * StringUtil.substringBeforeLast("a", "")      = "a"
     * </pre>
     * 
     * </p>
     * 
     * @param str �ַ�
     * @param separator Ҫ�����ķָ��Ӵ�
     * @return �Ӵ������ԭʼ��Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String substringBeforeLast(String str, String separator) {
        if (str == null || separator == null || str.length() == 0 || separator.length() == 0) {
            return str;
        }

        int pos = str.lastIndexOf(separator);

        if (pos == -1) {
            return str;
        }

        return str.substring(0, pos);
    }

    /**
     * ȡ�����һ���ķָ��Ӵ�֮����Ӵ���
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>null</code>�� ���ָ��Ӵ�Ϊ<code>null</code>
     * ��δ�ҵ����Ӵ����򷵻�ԭ�ַ�
     * 
     * <pre>
     * StringUtil.substringAfterLast(null, *)      = null
     * StringUtil.substringAfterLast("", *)        = ""
     * StringUtil.substringAfterLast(*, "")        = ""
     * StringUtil.substringAfterLast(*, null)      = ""
     * StringUtil.substringAfterLast("abc", "a")   = "bc"
     * StringUtil.substringAfterLast("abcba", "b") = "a"
     * StringUtil.substringAfterLast("abc", "c")   = ""
     * StringUtil.substringAfterLast("a", "a")     = ""
     * StringUtil.substringAfterLast("a", "z")     = ""
     * </pre>
     * 
     * </p>
     * 
     * @param str �ַ�
     * @param separator Ҫ�����ķָ��Ӵ�
     * @return �Ӵ������ԭʼ��Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String substringAfterLast(String str, String separator) {
        if (str == null || str.length() == 0) {
            return str;
        }

        if (separator == null || separator.length() == 0) {
            return EMPTY_STRING;
        }

        int pos = str.lastIndexOf(separator);

        if (pos == -1 || pos == str.length() - separator.length()) {
            return EMPTY_STRING;
        }

        return str.substring(pos + separator.length());
    }

    /**
     * ȡ��ָ���ָ����ǰ���γ���֮����Ӵ���
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>null</code>�� ���ָ��Ӵ�Ϊ<code>null</code>
     * ���򷵻�<code>null</code>��
     * 
     * <pre>
     * StringUtil.substringBetween(null, *)            = null
     * StringUtil.substringBetween("", "")             = ""
     * StringUtil.substringBetween("", "tag")          = null
     * StringUtil.substringBetween("tagabctag", null)  = null
     * StringUtil.substringBetween("tagabctag", "")    = ""
     * StringUtil.substringBetween("tagabctag", "tag") = "abc"
     * </pre>
     * 
     * </p>
     * 
     * @param str �ַ�
     * @param tag Ҫ�����ķָ��Ӵ�
     * @return �Ӵ������ԭʼ��Ϊ<code>null</code>��δ�ҵ��ָ��Ӵ����򷵻�<code>null</code>
     */
    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag, 0);
    }

    /**
     * ȡ�������ָ���֮����Ӵ���
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>null</code>�� ���ָ��Ӵ�Ϊ<code>null</code>
     * ���򷵻�<code>null</code>��
     * 
     * <pre>
     * StringUtil.substringBetween(null, *, *)          = null
     * StringUtil.substringBetween("", "", "")          = ""
     * StringUtil.substringBetween("", "", "tag")       = null
     * StringUtil.substringBetween("", "tag", "tag")    = null
     * StringUtil.substringBetween("yabcz", null, null) = null
     * StringUtil.substringBetween("yabcz", "", "")     = ""
     * StringUtil.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtil.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     * 
     * </p>
     * 
     * @param str �ַ�
     * @param open Ҫ�����ķָ��Ӵ�1
     * @param close Ҫ�����ķָ��Ӵ�2
     * @return �Ӵ������ԭʼ��Ϊ<code>null</code>��δ�ҵ��ָ��Ӵ����򷵻�<code>null</code>
     */
    public static String substringBetween(String str, String open, String close) {
        return substringBetween(str, open, close, 0);
    }

    /**
     * ȡ�������ָ���֮����Ӵ���
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>null</code>�� ���ָ��Ӵ�Ϊ<code>null</code>
     * ���򷵻�<code>null</code>��
     * 
     * <pre>
     * StringUtil.substringBetween(null, *, *)          = null
     * StringUtil.substringBetween("", "", "")          = ""
     * StringUtil.substringBetween("", "", "tag")       = null
     * StringUtil.substringBetween("", "tag", "tag")    = null
     * StringUtil.substringBetween("yabcz", null, null) = null
     * StringUtil.substringBetween("yabcz", "", "")     = ""
     * StringUtil.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtil.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     * 
     * </p>
     * 
     * @param str �ַ�
     * @param open Ҫ�����ķָ��Ӵ�1
     * @param close Ҫ�����ķָ��Ӵ�2
     * @param fromIndex ��ָ��index������
     * @return �Ӵ������ԭʼ��Ϊ<code>null</code>��δ�ҵ��ָ��Ӵ����򷵻�<code>null</code>
     */
    public static String substringBetween(String str, String open, String close, int fromIndex) {
        if (str == null || open == null || close == null) {
            return null;
        }

        int start = str.indexOf(open, fromIndex);

        if (start != -1) {
            int end = str.indexOf(close, start + open.length());

            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }

        return null;
    }

    // ==========================================================================
    // ɾ���ַ� 
    // ==========================================================================

    /**
     * ɾ��������<code>Character.isWhitespace(char)</code>������Ŀհס�
     * 
     * <pre>
     * StringUtil.deleteWhitespace(null)         = null
     * StringUtil.deleteWhitespace("")           = ""
     * StringUtil.deleteWhitespace("abc")        = "abc"
     * StringUtil.deleteWhitespace("   ab  c  ") = "abc"
     * </pre>
     * 
     * @param str Ҫ������ַ�
     * @return ȥ�հ׺���ַ����ԭʼ�ַ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String deleteWhitespace(String str) {
        if (str == null) {
            return null;
        }

        int sz = str.length();
        StringBuilder buffer = new StringBuilder(sz);

        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                buffer.append(str.charAt(i));
            }
        }

        return buffer.toString();
    }

    // ==========================================================================
    // �滻�Ӵ��� 
    // ==========================================================================

    /**
     * �滻ָ�����Ӵ����滻���г��ֵ��Ӵ���
     * <p>
     * ����ַ�Ϊ<code>null</code>�򷵻�<code>null</code>�����ָ���Ӵ�Ϊ<code>null</code>
     * ���򷵻�ԭ�ַ�
     * 
     * <pre>
     * StringUtil.replace(null, *, *)        = null
     * StringUtil.replace("", *, *)          = ""
     * StringUtil.replace("aba", null, null) = "aba"
     * StringUtil.replace("aba", null, null) = "aba"
     * StringUtil.replace("aba", "a", null)  = "aba"
     * StringUtil.replace("aba", "a", "")    = "b"
     * StringUtil.replace("aba", "a", "z")   = "zbz"
     * </pre>
     * 
     * </p>
     * 
     * @param text Ҫɨ����ַ�
     * @param repl Ҫ�������Ӵ�
     * @param with �滻�ַ�
     * @return ���滻����ַ����ԭʼ�ַ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    /**
     * �滻ָ�����Ӵ����滻ָ���Ĵ���
     * <p>
     * ����ַ�Ϊ<code>null</code>�򷵻�<code>null</code>�����ָ���Ӵ�Ϊ<code>null</code>
     * ���򷵻�ԭ�ַ�
     * 
     * <pre>
     * StringUtil.replace(null, *, *, *)         = null
     * StringUtil.replace("", *, *, *)           = ""
     * StringUtil.replace("abaa", null, null, 1) = "abaa"
     * StringUtil.replace("abaa", null, null, 1) = "abaa"
     * StringUtil.replace("abaa", "a", null, 1)  = "abaa"
     * StringUtil.replace("abaa", "a", "", 1)    = "baa"
     * StringUtil.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtil.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtil.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtil.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     * 
     * </p>
     * 
     * @param text Ҫɨ����ַ�
     * @param repl Ҫ�������Ӵ�
     * @param with �滻�ַ�
     * @param max maximum number of values to replace, or <code>-1</code> if no
     *            maximum
     * @return ���滻����ַ����ԭʼ�ַ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String replace(String text, String repl, String with, int max) {
        if (text == null || repl == null || with == null || repl.length() == 0 || max == 0) {
            return text;
        }

        StringBuilder buf = new StringBuilder(text.length());
        int start = 0;
        int end = 0;

        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            if (--max == 0) {
                break;
            }
        }

        buf.append(text.substring(start));

        return buf.toString();
    }

    /**
     * ���ַ�������ָ�����ַ��滻����һ����
     * <p>
     * ����ַ�Ϊ<code>null</code>�򷵻�<code>null</code>��
     * 
     * <pre>
     * StringUtil.replaceChars(null, *, *)        = null
     * StringUtil.replaceChars("", *, *)          = ""
     * StringUtil.replaceChars("abcba", 'b', 'y') = "aycya"
     * StringUtil.replaceChars("abcba", 'z', 'y') = "abcba"
     * </pre>
     * 
     * </p>
     * 
     * @param str Ҫɨ����ַ�
     * @param searchChar Ҫ�������ַ�
     * @param replaceChar �滻�ַ�
     * @return ���滻����ַ����ԭʼ�ַ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String replaceChar(String str, char searchChar, char replaceChar) {
        if (str == null) {
            return null;
        }

        return str.replace(searchChar, replaceChar);
    }

    /**
     * ���ַ�������ָ�����ַ��滻����һ����
     * <p>
     * ����ַ�Ϊ<code>null</code>�򷵻�<code>null</code>����������ַ�Ϊ<code>null</code>
     * ��գ��򷵻�ԭ�ַ�
     * </p>
     * <p>
     * ���磺
     * <code>replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly</code>
     * ��
     * </p>
     * <p>
     * ͨ�������ַ���滻�ַ��ǵȳ��ģ���������ַ���滻�ַ����������ַ�ɾ�� ��������ַ���滻�ַ�̣���ȱ�ٵ��ַ����ԡ�
     * 
     * <pre>
     * StringUtil.replaceChars(null, *, *)           = null
     * StringUtil.replaceChars("", *, *)             = ""
     * StringUtil.replaceChars("abc", null, *)       = "abc"
     * StringUtil.replaceChars("abc", "", *)         = "abc"
     * StringUtil.replaceChars("abc", "b", null)     = "ac"
     * StringUtil.replaceChars("abc", "b", "")       = "ac"
     * StringUtil.replaceChars("abcba", "bc", "yz")  = "ayzya"
     * StringUtil.replaceChars("abcba", "bc", "y")   = "ayya"
     * StringUtil.replaceChars("abcba", "bc", "yzx") = "ayzya"
     * </pre>
     * 
     * </p>
     * 
     * @param str Ҫɨ����ַ�
     * @param searchChars Ҫ�������ַ�
     * @param replaceChars �滻�ַ�
     * @return ���滻����ַ����ԭʼ�ַ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String replaceChars(String str, String searchChars, String replaceChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length() == 0) {
            return str;
        }

        char[] chars = str.toCharArray();
        int len = chars.length;
        boolean modified = false;

        for (int i = 0, isize = searchChars.length(); i < isize; i++) {
            char searchChar = searchChars.charAt(i);

            if (replaceChars == null || i >= replaceChars.length()) {
                // ɾ��
                int pos = 0;

                for (int j = 0; j < len; j++) {
                    if (chars[j] != searchChar) {
                        chars[pos++] = chars[j];
                    } else {
                        modified = true;
                    }
                }

                len = pos;
            } else {
                // �滻
                for (int j = 0; j < len; j++) {
                    if (chars[j] == searchChar) {
                        chars[j] = replaceChars.charAt(i);
                        modified = true;
                    }
                }
            }
        }

        if (!modified) {
            return str;
        }

        return new String(chars, 0, len);
    }

    /**
     * ��ָ�����Ӵ�����һָ���Ӵ����ǡ�
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>null</code>�� ��������ֵ��������<code>0</code>
     * ��Խ�������ֵ�������ó��ַ�ĳ�����ͬ��ֵ��
     * 
     * <pre>
     * StringUtil.overlay(null, *, *, *)            = null
     * StringUtil.overlay("", "abc", 0, 0)          = "abc"
     * StringUtil.overlay("abcdef", null, 2, 4)     = "abef"
     * StringUtil.overlay("abcdef", "", 2, 4)       = "abef"
     * StringUtil.overlay("abcdef", "", 4, 2)       = "abef"
     * StringUtil.overlay("abcdef", "zzzz", 2, 4)   = "abzzzzef"
     * StringUtil.overlay("abcdef", "zzzz", 4, 2)   = "abzzzzef"
     * StringUtil.overlay("abcdef", "zzzz", -1, 4)  = "zzzzef"
     * StringUtil.overlay("abcdef", "zzzz", 2, 8)   = "abzzzz"
     * StringUtil.overlay("abcdef", "zzzz", -2, -3) = "zzzzabcdef"
     * StringUtil.overlay("abcdef", "zzzz", 8, 10)  = "abcdefzzzz"
     * </pre>
     * 
     * </p>
     * 
     * @param str Ҫɨ����ַ�
     * @param overlay �������ǵ��ַ�
     * @param start ��ʼ����
     * @param end ��������
     * @return �����Ǻ���ַ����ԭʼ�ַ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String overlay(String str, String overlay, int start, int end) {
        if (str == null) {
            return null;
        }

        if (overlay == null) {
            overlay = EMPTY_STRING;
        }

        int len = str.length();

        if (start < 0) {
            start = 0;
        }

        if (start > len) {
            start = len;
        }

        if (end < 0) {
            end = 0;
        }

        if (end > len) {
            end = len;
        }

        if (start > end) {
            int temp = start;

            start = end;
            end = temp;
        }

        return new StringBuilder(len + start - end + overlay.length() + 1).append(str.substring(0, start))
                .append(overlay).append(str.substring(end)).toString();
    }

    // ==========================================================================
    // �ظ��ַ� 
    // ==========================================================================

    /**
     * ��ָ���ַ��ظ�n�顣
     * 
     * <pre>
     * StringUtil.repeat(null, 2)   = null
     * StringUtil.repeat("", 0)     = ""
     * StringUtil.repeat("", 2)     = ""
     * StringUtil.repeat("a", 3)    = "aaa"
     * StringUtil.repeat("ab", 2)   = "abab"
     * StringUtil.repeat("abcd", 2) = "abcdabcd"
     * StringUtil.repeat("a", -2)   = ""
     * </pre>
     * 
     * @param str Ҫ�ظ����ַ�
     * @param repeat �ظ��������С��<code>0</code>������<code>0</code>
     * @return �ظ�n�ε��ַ����ԭʼ�ַ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String repeat(String str, int repeat) {
        if (str == null) {
            return null;
        }

        if (repeat <= 0) {
            return EMPTY_STRING;
        }

        int inputLength = str.length();

        if (repeat == 1 || inputLength == 0) {
            return str;
        }

        int outputLength = inputLength * repeat;

        switch (inputLength) {
            case 1:

                char ch = str.charAt(0);
                char[] output1 = new char[outputLength];

                for (int i = repeat - 1; i >= 0; i--) {
                    output1[i] = ch;
                }

                return new String(output1);

            case 2:

                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char[] output2 = new char[outputLength];

                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }

                return new String(output2);

            default:

                StringBuilder buf = new StringBuilder(outputLength);

                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }

                return buf.toString();
        }
    }

    // ==========================================================================
    // Perl����chomp��chop����
    // ==========================================================================

    /**
     * ɾ���ַ�ĩβ�Ļ��з�����ַ��Ի��н�β����ʲôҲ������
     * <p>
     * ���з����������Σ�&quot;<code>\n</code>&quot;��&quot;<code>\r</code>&quot;��&quot;
     * <code>\r\n</code>&quot;��
     * 
     * <pre>
     * StringUtil.chomp(null)          = null
     * StringUtil.chomp("")            = ""
     * StringUtil.chomp("abc \r")      = "abc "
     * StringUtil.chomp("abc\n")       = "abc"
     * StringUtil.chomp("abc\r\n")     = "abc"
     * StringUtil.chomp("abc\r\n\r\n") = "abc\r\n"
     * StringUtil.chomp("abc\n\r")     = "abc\n"
     * StringUtil.chomp("abc\n\rabc")  = "abc\n\rabc"
     * StringUtil.chomp("\r")          = ""
     * StringUtil.chomp("\n")          = ""
     * StringUtil.chomp("\r\n")        = ""
     * </pre>
     * 
     * </p>
     * 
     * @param str Ҫ������ַ�
     * @return ���Ի��н�β���ַ����ԭʼ�ִ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String chomp(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        if (str.length() == 1) {
            char ch = str.charAt(0);

            if (ch == '\r' || ch == '\n') {
                return EMPTY_STRING;
            } else {
                return str;
            }
        }

        int lastIdx = str.length() - 1;
        char last = str.charAt(lastIdx);

        if (last == '\n') {
            if (str.charAt(lastIdx - 1) == '\r') {
                lastIdx--;
            }
        } else if (last == '\r') {
        } else {
            lastIdx++;
        }

        return str.substring(0, lastIdx);
    }

    /**
     * ɾ���ַ�ĩβ��ָ���ַ�����ַ��Ը��ַ��β����ʲôҲ������
     * 
     * <pre>
     * StringUtil.chomp(null, *)         = null
     * StringUtil.chomp("", *)           = ""
     * StringUtil.chomp("foobar", "bar") = "foo"
     * StringUtil.chomp("foobar", "baz") = "foobar"
     * StringUtil.chomp("foo", "foo")    = ""
     * StringUtil.chomp("foo ", "foo")   = "foo "
     * StringUtil.chomp(" foo", "foo")   = " "
     * StringUtil.chomp("foo", "foooo")  = "foo"
     * StringUtil.chomp("foo", "")       = "foo"
     * StringUtil.chomp("foo", null)     = "foo"
     * </pre>
     * 
     * @param str Ҫ������ַ�
     * @param separator Ҫɾ����ַ�
     * @return ����ָ���ַ��β���ַ����ԭʼ�ִ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String chomp(String str, String separator) {
        if (str == null || str.length() == 0 || separator == null) {
            return str;
        }

        if (str.endsWith(separator)) {
            return str.substring(0, str.length() - separator.length());
        }

        return str;
    }

    /**
     * ɾ�����һ���ַ�
     * <p>
     * ����ַ���<code>\r\n</code>��β����ͬʱɾ�����ǡ�
     * 
     * <pre>
     * StringUtil.chop(null)          = null
     * StringUtil.chop("")            = ""
     * StringUtil.chop("abc \r")      = "abc "
     * StringUtil.chop("abc\n")       = "abc"
     * StringUtil.chop("abc\r\n")     = "abc"
     * StringUtil.chop("abc")         = "ab"
     * StringUtil.chop("abc\nabc")    = "abc\nab"
     * StringUtil.chop("a")           = ""
     * StringUtil.chop("\r")          = ""
     * StringUtil.chop("\n")          = ""
     * StringUtil.chop("\r\n")        = ""
     * </pre>
     * 
     * </p>
     * 
     * @param str Ҫ������ַ�
     * @return ɾ�����һ���ַ���ַ����ԭʼ�ַ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String chop(String str) {
        if (str == null) {
            return null;
        }

        int strLen = str.length();

        if (strLen < 2) {
            return EMPTY_STRING;
        }

        int lastIdx = strLen - 1;
        String ret = str.substring(0, lastIdx);
        char last = str.charAt(lastIdx);

        if (last == '\n') {
            if (ret.charAt(lastIdx - 1) == '\r') {
                return ret.substring(0, lastIdx - 1);
            }
        }

        return ret;
    }

    // ==========================================================================
    // ��ת�ַ�
    // ==========================================================================

    /**
     * ��ת�ַ��е��ַ�˳��
     * <p>
     * ����ַ�Ϊ<code>null</code>���򷵻�<code>null</code>��
     * </p>
     * 
     * <pre>
     * StringUtil.reverse(null)  = null
     * StringUtil.reverse("")    = ""
     * StringUtil.reverse("bat") = "tab"
     * </pre>
     * 
     * @param str Ҫ��ת���ַ�
     * @return ��ת����ַ����ԭ�ַ�Ϊ<code>null</code>���򷵻�<code>null</code>
     */
    public static String reverse(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        return new StringBuilder(str).reverse().toString();
    }


    // ==========================================================================
    // ȡ���ַ�����ԡ�
    // ==========================================================================

    /**
     * ���ַ�ת����ָ�����ȵ����ԣ����磺
     * ��"Now is the time for all good men"ת����"Now is the time for..."��
     * <ul>
     * <li>���<code>str</code>��<code>maxWidth</code>�̣�ֱ�ӷ��أ�</li>
     * <li>������ת�������ԣ�<code>substring(str, 0, max-3) + "..."</code>��</li>
     * <li>���<code>maxWidth</code>С��<code>4</code>�׳�
     * <code>IllegalArgumentException</code>��</li>
     * <li>���ص��ַ����ܳ���ָ����<code>maxWidth</code>��</li>
     * </ul>
     * 
     * <pre>
     * StringUtil.abbreviate(null, *)      = null
     * StringUtil.abbreviate("", 4)        = ""
     * StringUtil.abbreviate("abcdefg", 6) = "abc..."
     * StringUtil.abbreviate("abcdefg", 7) = "abcdefg"
     * StringUtil.abbreviate("abcdefg", 8) = "abcdefg"
     * StringUtil.abbreviate("abcdefg", 4) = "a..."
     * StringUtil.abbreviate("abcdefg", 3) = IllegalArgumentException
     * </pre>
     * 
     * @param str Ҫ�����ַ�
     * @param maxWidth ��󳤶ȣ���С��<code>4</code>�����С��<code>4</code>������
     *            <code>4</code>
     * @return �ַ����ԣ����ԭʼ�ַ�Ϊ<code>null</code>�򷵻�<code>null</code>
     */
    public static String abbreviate(String str, int maxWidth) {
        return abbreviate(str, 0, maxWidth);
    }

    /**
     * ���ַ�ת����ָ�����ȵ����ԣ����磺
     * ��"Now is the time for all good men"ת����"...is the time for..."��
     * <p>
     * ��<code>abbreviate(String, int)</code>���ƣ�����������һ������߽硱ƫ������
     * ע�⣬����߽硱�����ַ�δ�س����ڽ���ַ������ߣ���һ�������ڽ���ַ��С�
     * </p>
     * <p>
     * ���ص��ַ����ܳ���ָ����<code>maxWidth</code>��
     * 
     * <pre>
     * StringUtil.abbreviate(null, *, *)                = null
     * StringUtil.abbreviate("", 0, 4)                  = ""
     * StringUtil.abbreviate("abcdefghijklmno", -1, 10) = "abcdefg..."
     * StringUtil.abbreviate("abcdefghijklmno", 0, 10)  = "abcdefg..."
     * StringUtil.abbreviate("abcdefghijklmno", 1, 10)  = "abcdefg..."
     * StringUtil.abbreviate("abcdefghijklmno", 4, 10)  = "abcdefg..."
     * StringUtil.abbreviate("abcdefghijklmno", 5, 10)  = "...fghi..."
     * StringUtil.abbreviate("abcdefghijklmno", 6, 10)  = "...ghij..."
     * StringUtil.abbreviate("abcdefghijklmno", 8, 10)  = "...ijklmno"
     * StringUtil.abbreviate("abcdefghijklmno", 10, 10) = "...ijklmno"
     * StringUtil.abbreviate("abcdefghijklmno", 12, 10) = "...ijklmno"
     * StringUtil.abbreviate("abcdefghij", 0, 3)        = IllegalArgumentException
     * StringUtil.abbreviate("abcdefghij", 5, 6)        = IllegalArgumentException
     * </pre>
     * 
     * </p>
     * 
     * @param str Ҫ�����ַ�
     * @param offset ��߽�ƫ����
     * @param maxWidth ��󳤶ȣ���С��<code>4</code>�����С��<code>4</code>������
     *            <code>4</code>
     * @return �ַ����ԣ����ԭʼ�ַ�Ϊ<code>null</code>�򷵻�<code>null</code>
     */
    public static String abbreviate(String str, int offset, int maxWidth) {
        if (str == null) {
            return null;
        }

        // ���������
        if (maxWidth < 4) {
            maxWidth = 4;
        }

        if (str.length() <= maxWidth) {
            return str;
        }

        if (offset > str.length()) {
            offset = str.length();
        }

        if (str.length() - offset < maxWidth - 3) {
            offset = str.length() - (maxWidth - 3);
        }

        if (offset <= 4) {
            return str.substring(0, maxWidth - 3) + "...";
        }

        // ���������
        if (maxWidth < 7) {
            maxWidth = 7;
        }

        if (offset + maxWidth - 3 < str.length()) {
            return "..." + abbreviate(str.substring(offset), maxWidth - 3);
        }

        return "..." + str.substring(str.length() - (maxWidth - 3));
    }

    // ==========================================================================
    // �Ƚ������ַ����ͬ��
    // ==========================================================================

    /**
     * �Ƚ������ַ�ȡ�õڶ����ַ��У��͵�һ���ַ�ͬ�Ĳ��֡�
     * 
     * <pre>
     * StringUtil.difference("i am a machine", "i am a robot")  = "robot"
     * StringUtil.difference(null, null)                        = null
     * StringUtil.difference("", "")                            = ""
     * StringUtil.difference("", null)                          = ""
     * StringUtil.difference("", "abc")                         = "abc"
     * StringUtil.difference("abc", "")                         = ""
     * StringUtil.difference("abc", "abc")                      = ""
     * StringUtil.difference("ab", "abxyz")                     = "xyz"
     * StringUtil.difference("abcde", "abxyz")                  = "xyz"
     * StringUtil.difference("abcde", "xyz")                    = "xyz"
     * </pre>
     * 
     * @param str1 �ַ�1
     * @param str2 �ַ�2
     * @return �ڶ����ַ��У��͵�һ���ַ�ͬ�Ĳ��֡���������ַ���ͬ���򷵻ؿ��ַ�<code>""</code>
     */
    public static String difference(String str1, String str2) {
        if (str1 == null) {
            return str2;
        }

        if (str2 == null) {
            return str1;
        }

        int index = indexOfDifference(str1, str2);

        if (index == -1) {
            return EMPTY_STRING;
        }

        return str2.substring(index);
    }

    /**
     * �Ƚ������ַ�ȡ�����ַ�ʼ��ͬ������ֵ��
     * 
     * <pre>
     * StringUtil.indexOfDifference("i am a machine", "i am a robot")   = 7
     * StringUtil.indexOfDifference(null, null)                         = -1
     * StringUtil.indexOfDifference("", null)                           = -1
     * StringUtil.indexOfDifference("", "")                             = -1
     * StringUtil.indexOfDifference("", "abc")                          = 0
     * StringUtil.indexOfDifference("abc", "")                          = 0
     * StringUtil.indexOfDifference("abc", "abc")                       = -1
     * StringUtil.indexOfDifference("ab", "abxyz")                      = 2
     * StringUtil.indexOfDifference("abcde", "abxyz")                   = 2
     * StringUtil.indexOfDifference("abcde", "xyz")                     = 0
     * </pre>
     * 
     * @param str1 �ַ�1
     * @param str2 �ַ�2
     * @return ���ַ�ʼ������������ֵ��������ַ���ͬ���򷵻�<code>-1</code>
     */
    public static int indexOfDifference(String str1, String str2) {
        if (str1 == str2 || str1 == null || str2 == null) {
            return -1;
        }

        int i;

        for (i = 0; i < str1.length() && i < str2.length(); ++i) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
        }

        if (i < str2.length() || i < str1.length()) {
            return i;
        }

        return -1;
    }

    // ==========================================================================
    // �����ֻ��ֽ�ת����ASCII�ַ�ĺ��� 
    // ==========================================================================

    private static final char[] DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] DIGITS_NOCASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * ��һ��������ת����62���Ƶ��ַ�
     */
    public static String longToString(long longValue) {
        return longToString(longValue, false);
    }

    /**
     * ��һ��������ת����62���Ƶ��ַ�
     */
    public static String longToString(long longValue, boolean noCase) {
        char[] digits = noCase ? DIGITS_NOCASE : DIGITS;
        int digitsLength = digits.length;

        if (longValue == 0) {
            return String.valueOf(digits[0]);
        }

        if (longValue < 0) {
            longValue = -longValue;
        }

        StringBuilder strValue = new StringBuilder();

        while (longValue != 0) {
            int digit = (int) (longValue % digitsLength);
            longValue = longValue / digitsLength;

            strValue.append(digits[digit]);
        }

        return strValue.toString();
    }
    

    /**
     *  app使用 2015-11-17
     * 判断是否是手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles){
    	Pattern p = Pattern.compile("^\\d{11}$");
    	Matcher m = p.matcher(mobiles);
    	return m.matches();
    }
    
    /**
     *  app使用 2015-11-17
     * 判断是否是手机号
     * @param
     * @return
     */
    public static boolean isEmailAddr(String email){
    	Pattern p = Pattern.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
    	Matcher m = p.matcher(email);
    	return m.matches();
    }
    
    /**
     * 判断是否为合法短信验证码
     * @param code
     * @return
     */
    public static boolean isMsgCode(String code){
    	Pattern p = Pattern.compile("^\\d{4}$");
    	Matcher m = p.matcher(code);
    	return m.matches();
    }
    /**
     * 返回一个小于int的正整数
     * @param max
     * @return
     */
    public static int getOneInt(int max){
    	return new Random().nextInt(max);
    }
    
    /**
     * 根据时间00：01：41.1生成timer的int值
     * @param timer
     * @return
     */
    public static int vedioTimer(String timer){
    	try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			int hours=sdf.parse(timer).getHours();
			int min=sdf.parse(timer).getMinutes();
			int sec=sdf.parse(timer).getSeconds();
			System.out.println(hours +"==="+min+"==="+sec);
			return hours*60*60*1000+min*60*1000+sec*1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return 0;
    }
    
    /**
     * 去掉手机号中得-
     * @param str
     * @return
     */
    public static String removeShortBar(String str){
    	return str.replace("-","");
    }
    
    /**
     * 去掉手机号中得+186
     * @param str
     * @return
     */
    public static String remove86(String str){
    	return str.replace("+86","");
    }
    
    /**app使用 2015-11-17
     * 格式化手机号（逻辑：先对字符串前后空格进行处理，然后取字符串的后11位）
     * @author hjn
     */
    public static String formatePhoneNum(String phnum){
    	phnum=phnum.trim();
    	String regEx="[`~!@#$%^&*()-+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\-\\t\\n\\r\\s]";
    	phnum=phnum.replaceAll(regEx, "");
    	if(phnum.length() >=11){
    		return phnum.substring(phnum.length()-11,phnum.length());
    	}else{
    		return null;
    	}
    	
    }
    /**
     * 根据服务器地址，和网络访问头，和本地存放地址头得到文件http访问地址
     * @param localUrl
     * @param httpTitle
     * @param localTitle
     * @return
     */
    public static String getHttpUrl(String localUrl,String httpTitle,String localTitle){
    	return httpTitle+localUrl.substring(localTitle.length());
    }
//    public static void main(String[] args) {
//    	System.out.println(formatePhoneNum("+86- 186-2215-7505"));
//	}
    
    
//    /** 
//     *  
//     * @param version1 
//     * @param version2 
//     * @return if version1 > version2, return 1, if equal, return 0, else return -1 
//     */  
//    public static int compare(String version1, String version2) {  
//        if (version1 == null || version1.length() == 0 || version2 == null || version2.length() == 0 )  
//            throw new IllegalArgumentException("Invalid parameter!");  
//          
//        int index1 = 0;  
//        int index2 = 0;  
//        while(index1 < version1.length() && index2 < version2.length()) {  
//            int[] number1 = getValue(version1, index1);  
//            int[] number2 = getValue(version2, index2);  
//              
//            if (number1[0] < number2[0]) return -1;  
//            else if (number1[0] > number2[0]) return 1;  
//            else {  
//                index1 = number1[1] + 1;  
//                index2 = number2[1] + 1;  
//            }             
//        }  
//        if(index1 == version1.length() && index2 == version2.length()) return 0;  
//        if(index1 < version1.length())   
//            return 1;  
//        else  
//            return -1;  
//    }  
//      
//    /** 
//     *  
//     * @param version 
//     * @param index the starting point 
//     * @return the number between two dots, and the index of the dot 
//     */  
//    public static int[] getValue(String version, int index) {  
//        int[] value_index = new int[2];  
//        StringBuilder sb = new StringBuilder();  
//        while(index < version.length() && version.charAt(index) != '.') {  
//            sb.append(version.charAt(index));  
//            index++;  
//        }  
//        value_index[0] = Integer.parseInt(sb.toString());  
//        value_index[1] = index;  
//          
//        return value_index;  
//    }  
    
    /** 
     * 计算地球上任意两点(经纬度)距离 
     *  
     * @param long1 
     *            第一点经度 
     * @param lat1 
     *            第一点纬度 
     * @param long2 
     *            第二点经度 
     * @param lat2 
     *            第二点纬度 
     * @return 返回距离 单位：米 
     */  
    public static double Distance(double long1, double lat1, double long2,  
            double lat2) {  
        double a, b, R;  
        R = 6378137; // 地球半径  
        lat1 = lat1 * Math.PI / 180.0;  
        lat2 = lat2 * Math.PI / 180.0;  
        a = lat1 - lat2;  
        b = (long1 - long2) * Math.PI / 180.0;  
        double d;  
        double sa2, sb2;  
        sa2 = Math.sin(a / 2.0);  
        sb2 = Math.sin(b / 2.0);  
        d = 2  
                * R  
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
                        * Math.cos(lat2) * sb2 * sb2));  
        BigDecimal   bd   =   new   BigDecimal(d);  
        double   f   =   bd.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
        return f/1000;  
    }
    
    /**
     * 传入一个字符串截取
     * @param str
     * @return
     */
    public static String getStr(String str){
    	if(StringUtil.isBlank(str)){
    		return "";
    	}else{
    		if(str.length() <= 10){
    			return str;
    		}else{
    			return str.substring(0, 10);
    		}
    	}
    }
    
 
    
    public static int compare(int a,int b){
    	if(a > b){
    		return b;
    	}else{
    		return a;
    	}
    }
    
    public static int getPage(int a,int b){
    	if(a >= (b-1)){
    		return 0;
    	}else{
    		return a+1;
    	}
    }
    /**
     * 获得一天的开始时间
     * @return
     */
    public static long getDateStartTime(){
    	Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	     
	    Date start = calendar.getTime();	 
	    calendar.add(Calendar.DAY_OF_MONTH, 1);
	    calendar.add(Calendar.SECOND, -1);
	    long beign=start.getTime();
	    return beign;
    }
    /**
     * 获得一天的结束时间
     * @return
     */
    public static long getDateEndTime(){   	
    	Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.add(Calendar.DAY_OF_MONTH, 1);
	    calendar.add(Calendar.SECOND, -1);
	     
	    Date end = calendar.getTime();
	    long endTime = end.getTime();
	    return endTime;
    	
    }

  
}

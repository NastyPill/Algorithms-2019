package lesson1;

import lesson2.JavaAlgorithms;
import org.junit.Assert;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public class TaskTestsJava extends AbstractFileTests {

    @Test
    @Tag("Easy")
    public void timeSortTest() {
        JavaTasks.sortTimes("input/time_in4.txt", "input/time_out.txt");
        String expected = "12:11:43 AM\n" +
                "12:54:27 AM\n" +
                "01:13:39 AM\n" +
                "01:15:00 AM\n" +
                "05:43:46 AM\n" +
                "07:56:44 AM\n" +
                "08:30:32 AM\n" +
                "10:08:41 AM\n" +
                "12:09:11 PM\n" +
                "02:09:02 PM\n" +
                "02:27:41 PM\n" +
                "02:29:40 PM\n" +
                "05:45:19 PM\n" +
                "07:11:16 PM\n" +
                "07:21:01 PM\n" +
                "10:17:22 PM\n" +
                "10:45:58 PM";
        assertFileContent("input/time_out.txt", expected);

        JavaTasks.sortTimes("input/time_in5.txt", "input/time_out.txt");
        expected = "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "12:11:43 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM\n" +
                "07:56:44 AM";
        assertFileContent("input/time_out.txt", expected);
    }

    @Test
    @Tag("Normal")
    public void addrSortTest() {
        JavaTasks.sortAddresses("input/addr_in.txt", "input/addr_out.txt");
        assertFilesContent("input/addr_out2.txt", "input/addr_out.txt");
        JavaTasks.sortAddresses("input/addr_in3.txt", "input/addr_out.txt");
        assertFilesContent("input/addr_out3.txt", "input/addr_out.txt");
    }

    @Test
    @Tag("Normal")
    public void tmpSortTest() {
        JavaTasks.sortTemperatures("input/temp_in1.txt", "input/temp_out.txt");
        assertFilesContent("input/temp_out1.txt", "input/temp_out.txt");
        JavaTasks.sortTemperatures("input/temp_in2.txt", "input/temp_out.txt");
        assertFilesContent("input/temp_out2.txt", "input/temp_out.txt");
    }

    @Test
    @Tag("Normal")
    public void longestCommonSubStrTest() {
        //pseudo random tests
        Assert.assertEquals("asdfgh", JavaAlgorithms.longestCommonSubstring("asdfghjkl", "fghjklqwehfjaasdfghasdfgqwd"));
        Assert.assertEquals("yruh", JavaAlgorithms.longestCommonSubstring("78243yruhbal", "2637iyruhgjf734"));
        //test for first common substring
        Assert.assertEquals("111", JavaAlgorithms.longestCommonSubstring("111333111", "11123332"));
        //test for no substrings
        Assert.assertEquals("", JavaAlgorithms.longestCommonSubstring("11111111111", "2"));
    }

    @Test
    @Tag("Hard")
    public void baldaTest() {
        Set<String> test1 = new HashSet<>();
        test1.add("abc");
        test1.add("bcd");
        test1.add("bfjkl");
        test1.add("i");
        test1.add("abcjk");
        Set<String> expected1 = new HashSet<>();
        expected1.add("bcd");
        expected1.add("abc");
        expected1.add("i");
        expected1.add("bfjkl");
        Assert.assertEquals(expected1, JavaAlgorithms.baldaSearcher("input/balda_in1.txt", test1));

        Set<String> test2 = new HashSet<>();
        test2.add("adbqjs");
        test2.add("aggqws");
        test2.add("sdfsad");
        test2.add("ssssasd");
        test2.add("sa");
        test2.add("sssss");
        Set<String> expected2 = new HashSet<>();
        expected2.add("sssss");
        expected2.add("sa");
        Assert.assertEquals(expected2, JavaAlgorithms.baldaSearcher("input/balda_in2.txt", test2));

        Set<String> test3 = new HashSet<>();
        test3.add("a");
        test3.add("aa");
        test3.add("abaa");
        test3.add("aca");
        Set<String> expected3 = new HashSet<>();
        expected3.add("aa");
        expected3.add("abaa");
        expected3.add("a");
        Assert.assertEquals(expected3, JavaAlgorithms.baldaSearcher("input/balda_in3.txt", test3));
    }
}
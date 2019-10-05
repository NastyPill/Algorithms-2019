package lesson1;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


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
}
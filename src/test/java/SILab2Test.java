import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SILab2Test {

    private final SILab2 siLab2 = new SILab2();

    private List<Time> createList(Time... e) {
        return new ArrayList<>(Arrays.asList(e));
    }
    private List<Integer> createListInt(Integer... e) {
        return new ArrayList<>(Arrays.asList(e));
    }

    @Test
    void everyBranchTest(){

        // 10:20:16
        Time time1 = new Time(10, 20, 16);
        List<Time> timeList1 = createList(time1);
        assertEquals(createListInt(37216), siLab2.function(timeList1));

        // 24:00:00; 24:20:16
        Time time2 = new Time(24, 0, 0);
        Time time3 = new Time(24, 20, 16);
        List<Time> timeList2 = createList(time2, time3);
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(timeList2));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

        // 10:20:61
        Time time5 = new Time(10, 20, 61);
        List<Time> timeList3 = createList(time5);
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(timeList3));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        // 10:-20:16
        Time time6 = new Time(10, -20, 16);
        List<Time> timeList4 = createList(time6);
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(timeList4));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        // 25:20:16
        Time time7 = new Time(25, 20, 16);
        List<Time> timeList5 = createList(time7);
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(timeList5));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));

        // -10:20:16
        Time time8 = new Time(-10, 20, 16);
        List<Time> timeList6 = createList(time8);
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(timeList6));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));
    }

    @Test
    void multipleCondition(){
        RuntimeException ex;

        // 10:20:-16
        Time time1 = new Time(10, 20, -16);
        List<Time> timeList1 = createList(time1);
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(timeList1));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        // 10:61:16
        Time time2 = new Time(10, 61, 16);
        List<Time> timeList2 = createList(time2);
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(timeList2));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        // 24:00:00
        Time time3 = new Time(24, 0, 0);
        List<Time> timeList3 = createList(time3);
        assertEquals(createListInt(86400), siLab2.function(timeList3));

        // 24:00:16
        Time time4 = new Time(24, 0, 16);
        List<Time> timeList4 = createList(time4);
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(timeList4));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

        // 24:20:16
        Time time5 = new Time(24, 20, 16);
        List<Time> timeList5 = createList(time5);
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(timeList5));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));
    }
}

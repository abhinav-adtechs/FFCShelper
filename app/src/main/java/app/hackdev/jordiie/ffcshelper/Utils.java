package app.hackdev.jordiie.ffcshelper;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Utils {

    public static final String sep = ";yog;";

    /**
     * Get all the slots
     *
     * @return All the slots available in VIT
     */
    public static String[] getSlots() {
        return new String[]{"A1", "B1", "C1", "D1", "E1", "F1", "G1", "A1+TA1", "D1+TD1", "E1+TE1", "F1+TF1",
                "A2", "B2", "C2", "D2", "E2", "F2", "G2", "A2+TA2", "B2+TB2", "C2+TC2", "D2+TD2", "E2+TE1", "F2+TF2", "G2+TG2",
                "L1+L2", "L3+L4", "L5+L6", "L7+L8", "L9+L10", "L11+L12", "L13+L14", "L19+L20", "L21+L22", "L23+L24", "L25+L26", "L27+L28", "L29+L30",
                "L31+L32", "L33+L34", "L35+L36", "L37+L38", "L39+L40", "L41+L42", "L43+L44", "L45+L46", "L47+L48", "L49+L50", "L51+L52", "L53+L54", "L55+56", "L57+L58", "L59+60"};
    }

    /**
     * Check for slot clashing
     *
     * @param l slot 1
     * @param r slot 2
     * @return true if slots clashes , false otherwise
     */
    public static boolean equals(Slot l, Slot r) {
        M.L("crs", Collections.disjoint(l.day, r.day) + "\t\t" + Collections.disjoint(l.pos, r.pos) + "\t\t" + l.day.size() + "\t\t" + r.day.size() + "\t\t" + l.pos.size() + "\t\t" + r.pos.size());
        /*if (l.isMorn == r.isMorn) {
            if (Collections.disjoint(l.day, r.day)) {
                return false;
            } else {
                if (Collections.disjoint(l.pos, r.pos)) {
                    return false;
                } else
                    return true;
            }
        } else
            return false;*/
        if (l.isTh && r.isTh) {
            for (int i = 0; i < l.day.size(); i++) {
                if (l.day.get(i).equals(r.day.get(i)))
                    if (l.pos.get(i).equals(r.pos.get(i)))
                        return true;
            }
        } else {
            for (int i = 0; i < l.day.size(); i++)
                for (int j = 0; j < r.day.size(); j++)
                    if (l.day.get(i).equals(r.day.get(j)))
                        if (l.pos.get(i).equals(r.pos.get(j)))
                            return true;
        }
        return false;
    }


    /**
     * Check if the slot is a Theory or a Lab
     *
     * @param slot slot selected from the spinner
     * @return true if slot is theory ,false otherwise
     */
    public static boolean isTh(int slot) {
        return slot >= 0 && slot <= 24;
    }

    /**
     * Check if the slot is a Morning or Afternoon
     *
     * @param slot slot selected from the spinner
     * @return true if slot is Morning ,false otherwise
     */
    public static boolean isMorn(int slot) {
        return slot >= 0 && slot <= 10 || slot >= 25 && slot <= 37;
    }

    /**
     * Get the position in the time table
     *
     * @param slot slot selected from the spinner
     * @param isTh Theory or Lab
     * @return List of integers which has the position of a slot in the time table
     */
    public static List<Integer> getPos(int slot, boolean isTh) {
        if (isTh) {
            switch (slot) {
                case 0:
                case 11:
                    return Arrays.asList(0, 1);
                case 1:
                case 12:
                    return Arrays.asList(0, 1);
                case 2:
                case 13:
                    return Arrays.asList(2, 0, 3);
                case 3:
                case 14:
                    return Arrays.asList(2, 0, 3);
                case 4:
                case 15:
                    return Arrays.asList(3, 2, 0);
                case 5:
                case 16:
                    return Arrays.asList(1, 1, 2);
                case 6:
                case 17:
                    return Arrays.asList(1, 2);

                /*case 7:
                    return Arrays.asList(0, 3, 1);
                case 8:
                    return Arrays.asList(4, 2, 0, 3);
                case 9:
                    return Arrays.asList(3, 2, 4, 0);
                case 10:
                    return Arrays.asList(1, 4, 1, 2);*/
                case 7:
                case 18:
                    return Arrays.asList(0, 3, 1);
                case 19:
                    return Arrays.asList(0, 3, 1);
                case 20:
                    return Arrays.asList(2, 0, 3, 4);
                case 8:
                case 21:
                    return Arrays.asList(4, 2, 0, 3);
                case 9:
                case 22:
                    return Arrays.asList(3, 2, 4, 0);
                case 10:
                case 23:
                    return Arrays.asList(1, 4, 1, 2);
                case 24:
                    return Arrays.asList(1, 4, 2);
                default:
                    return new LinkedList<>();
            }
        } else {
            switch (slot) {
                case 25:
                case 38:
                case 28:
                case 41:
                case 31:
                case 44:
                case 32:
                case 47:
                case 35:
                case 50:
                    return Arrays.asList(0, 1);
                case 26:
                case 39:
                case 29:
                case 42:
                case 45:
                case 33:
                case 48:
                case 36:
                case 51:
                    return Arrays.asList(2, 3);
                case 27:
                case 40:
                case 30:
                case 43:
                case 46:
                case 34:
                case 49:
                case 37:
                case 52:
                    return Arrays.asList(4, 5);
                /*case 28:
                case 41:
                    return Arrays.asList(6, 7);
                case 29:
                case 42:
                    return Arrays.asList(8, 9);
                case 30:
                case 43:
                    return Arrays.asList(10, 11);*/
                /*case 31:
                case 44:
                    return Arrays.asList(12, 13);
                case 45:
                    return Arrays.asList(14, 15);
                case 46:
                    return Arrays.asList(16, 17);*/
                /*case 32:
                case 47:
                    return Arrays.asList(18, 19);
                case 33:
                case 48:
                    return Arrays.asList(20, 21);
                case 34:
                case 49:
                    return Arrays.asList(22, 23);*/
                /*case 35:
                case 50:
                    return Arrays.asList(24, 25);
                case 36:
                case 51:
                    return Arrays.asList(26, 27);
                case 37:
                case 52:
                    return Arrays.asList(28, 29);*/
                default:
                    return new LinkedList<>();
            }
        }
    }

    /**
     * Get days for a slot
     *
     * @param slot slot selected from spinner
     * @param isTh Theory or Lab
     * @return List of integer which contains days for a particular slot
     */
    public static List<Integer> getDays(int slot, boolean isTh) {
        if (isTh) {
            switch (slot) {
                case 0:
                case 11:
                    return Arrays.asList(0, 3);
                case 1:
                case 12:
                    return Arrays.asList(1, 4);
                case 2:
                case 13:
                    return Arrays.asList(0, 2, 3);
                case 3:
                case 14:
                    return Arrays.asList(1, 3, 4);
                case 4:
                case 15:
                    return Arrays.asList(0, 2, 4);
                case 5:
                case 16:
                    return Arrays.asList(0, 2, 3);
                case 6:
                case 17:
                    return Arrays.asList(1, 4);
                case 7:
                case 18:
                    return Arrays.asList(0, 1, 3);
                case 19:
                    return Arrays.asList(1, 2, 4);
                case 20:
                    return Arrays.asList(0, 2, 3, 4);
                case 8:
                case 21:
                    return Arrays.asList(0, 1, 3, 4);
                case 9:
                case 22:
                    return Arrays.asList(0, 2, 3, 4);
                case 10:
                case 23:
                    return Arrays.asList(0, 1, 2, 3);
                case 24:
                    return Arrays.asList(1, 2, 4);
            }
        } else {
            switch (slot) {
                case 25:
                case 26:
                case 27:
                case 38:
                case 39:
                case 40:
                    return Collections.singletonList(0);
                case 28:
                case 29:
                case 30:
                case 41:
                case 42:
                case 43:
                    return Collections.singletonList(1);
                case 31:
                case 44:
                case 45:
                case 46:
                    return Collections.singletonList(2);
                case 32:
                case 33:
                case 34:
                case 47:
                case 48:
                case 49:
                    return Collections.singletonList(3);
                case 35:
                case 36:
                case 37:
                case 50:
                case 51:
                case 52:
                    return Collections.singletonList(4);
            }
        }
        return new LinkedList<>();
    }

    /**
     * Get the data of Slot
     *
     * @param s String data of slot from file
     * @return String to Slot for displaying
     */
    public static Slot decodeSlot(String s) {
        Slot slot = new Slot();
        String[] sl = s.split(sep);
        slot.slot = Integer.valueOf(sl[0]);
        slot.courseTitle = sl[1];
        slot.courseCode = sl[2];
        slot.credits = Integer.valueOf(sl[3]);
        slot.isTh = Boolean.parseBoolean(sl[4]);
        slot.isMorn = Boolean.parseBoolean(sl[5]);
        String[] days = sl[6].replace("d:", "").split(",");
        String[] pos = sl[7].replace("s:", "").split(",");
        List<Integer> d = new LinkedList<>();
        for (String day : days) d.add(Integer.valueOf(day));
        slot.day = d;
        List<Integer> p = new LinkedList<>();
        for (String po : pos) p.add(Integer.valueOf(po));
        slot.pos = p;
        return slot;
    }

    /**
     * Convert the slot to String for saving it
     *
     * @param slot Slot data
     * @return Slot to String for saving
     */
    public static String encodeSlot(Slot slot) {
        String s = "";
        s += (slot.slot + sep + slot.courseTitle + sep + slot.courseCode + sep + slot.credits + sep + slot.isTh + sep + slot.isMorn + sep);
        s += "d:";
        for (int i = 0; i < slot.day.size(); i++)
            s += (i + ",");
        s += sep;
        s += "s:";
        for (int i = 0; i < slot.pos.size(); i++)
            s += (i + ",");
        s += (sep + "\n");
        M.L("slot", s);
        return s;
    }

    /**
     * Get the timings of a particular slot
     *
     * @param slot Slot data
     * @return Timings in string
     */
    public static String getTimings(Slot slot) {
        String s = "";
        if (slot.isTh) {
            for (int i = 0; i < slot.day.size(); i++) {
                s += day(i);
                int a;
                if (slot.isMorn) {
                    a = 8 + slot.day.get(i);
                } else {
                    a = 2 + slot.day.get(i);
                }
                String t;
                if (a >= 8)
                    t = "AM";
                else
                    t = "PM";
                s += (a + ":00 " + t + " - " + a + ":50 " + t);
                s += "\n";
            }
        } else {
            for (int i = 0; i < slot.day.size(); i++) {
                for (int j = 0; j < slot.pos.size(); j++) {
                    s += day(i);
                    int a;
                    if (slot.isMorn) {
                        a = 8 + slot.pos.get(j);
                    } else {
                        a = 2 + slot.pos.get(j);
                    }
                    String t;
                    if (a >= 8)
                        t = "AM";
                    else
                        t = "PM";
                    s += (a + ":00 " + t + " - " + a + ":50 " + t);
                    s += "\n";
                }
                s += "\n";
            }
        }
        return s;
    }

    /**
     * Get the day of the week
     *
     * @param i Position of day
     * @return The day of the week
     */
    public static String day(int i) {
        switch (i) {
            case 0:
                return "Mon : ";
            case 1:
                return "Tue : ";
            case 2:
                return "Wed : ";
            case 3:
                return "Thu : ";
            case 4:
                return "Fri : ";
            default:
                return "";
        }
    }
}
package app.hackdev.jordiie.ffcshelper;


import java.util.Collections;

import yogesh.firzen.mukkiasevaigal.M;

public class Utils {
    /*public final static String A1 = "Mon : 8:00 AM - 8:50 AM\nThu : 9:00 AM - 9:50 AM\n";
    public final static String TA1 = "Mon : 11:00 AM = 11:50 AM";
    public final static String A1TA1 = A1 + TA1;

    public final static String A2 = "Mon : 2:00 PM - 2:50 PM\nThu : 3:00 PM - 3:50 PM\n";
    public final static String TA2 = "Mon : 5:00 PM = 5:50 PM";
    public final static String A2TA2 = A2 + TA2;

    public final static String B1 = "Tue : 8.00 AM - 8:50 AM\nFri : 9:00 AM - 9:50 AM\n";
    *//*public final static String TB1 = "Mon : 11:00 AM = 11:50 AM";
    public final static String B1TB1 = B1 + TB1;*//*

    public final static String B2 = "Tue : 2:00 PM - 2:50 PM\nFri : 3:00 PM - 3:50 PM\n";
    public final static String TB2 = "Wed : 5:00 PM = 5:50 PM";
    public final static String B2TB2 = B2 + TB2;

    public final static String C1 = "Mon : 10:00 AM - 10:50 AM\nWed : 8:00 AM - 8:50 AM\nThu : 11:00 AM - 11:50 AM\n";
    public final static String C2 = "Mon : 4:00 PM - 4:50 PM\nWed : 2:00 PM - 2:50 PM\nThu : 5:00 PM - 5:50 PM\n";*/

    //public final static String[] monT = {""}

    public static String[] getSlots() {
        return new String[]{"A1", "B1", "C1", "D1", "E1", "F1", "G1", "A1+TA1", "D1+TD1", "E1+TE1", "F1+TF1",
                "A2", "B2", "C2", "D2", "E2", "F2", "G2", "A2+TA2", "B2+TB2", "C2+TC2", "D2+TD2", "E2+TE1", "F2+TF2", "G2+TG2",
                "L1+L2", "L3+L4", "L5+L6", "L7+L8", "L9+L10", "L11+L12", "L13+L14", "L19+L20", "L21+L22", "L23+L24", "L25+L26", "L27+L28", "L29+L30",
                "L31+L32", "L33+L34", "L35+L36", "L37+L38", "L39+L40", "L41+L42", "L43+L44", "L45+L46", "L47+L48", "L49+L50", "L51+L52", "L53+L54", "L55+56", "L57+L58", "L59+60"};
    }

    public static boolean equals(Slot l, Slot r) {
        M.L("crs", Collections.disjoint(l.day, r.day) + "\t\t" + Collections.disjoint(l.pos, r.pos) + "\t\t" + l.day.size() + "\t\t" + r.day.size() + "\t\t" + l.pos.size() + "\t\t" + r.pos.size());
        if (l.isMorn == r.isMorn) {
            if (Collections.disjoint(l.day, r.day)) {
                return false;
            } else {
                if (Collections.disjoint(l.pos, r.pos)) {
                    return false;
                } else
                    return true;
            }
        } else
            return false;
    }

    /*public static final Integer A[] = {0, 1};
    public static final Integer B[] = {0, 1};
    public static final Integer C[] = {2, 0, 3};
    public static final Integer D[] = {2, 0, 3};
    public static final Integer E[] = {3, 2, 0};
    public static final Integer F[] = {1, 1, 2};
    public static final Integer G[] = {1, 2};

    public static final Integer TA[] = {0, 3, 1};
    public static final Integer TB[] = {0, 3, 1};
    public static final Integer TC[] = {2, 0, 3, 4};
    public static final Integer TD[] = {4, 2, 0, 3};
    public static final Integer TE[] = {3, 2, 4, 0};
    public static final Integer TF[] = {1, 4, 1, 2};
    public static final Integer TG[] = {1, 4, 2};*/

    /*if (slot >= 0 && slot <= 6 || slot >= 11 || slot <= 17) {
                if (slot - 12 < 0)
                    s = slot;
                else
                    s = slot - 12;

            } else {
                switch (slot) {
                    case 7:
                        return Arrays.asList(0, 3, 1);
                    case 8:
                        return Arrays.asList(4, 2, 0, 3);
                    case 9:
                        return Arrays.asList(3, 2, 4, 0);
                    case 10:
                        return Arrays.asList(1, 4, 1, 2);
                    case 18:
                        return Arrays.asList(0, 3, 1);
                    case 19:
                        return Arrays.asList(0, 3, 1);
                    case 20:
                        return Arrays.asList(2, 0, 3, 4);
                    case 21:
                        return Arrays.asList(4, 2, 0, 3);
                    case 22:
                        return Arrays.asList(3, 2, 4, 0);
                    case 23:
                        return Arrays.asList(1, 4, 1, 2);
                    case 24:
                        return Arrays.asList(1, 4, 2);
                    default:
                        return new LinkedList<>();
                }
            }*/

    /**
     * if (slot >= 0 && slot <= 6 || slot >= 11 || slot <= 17) {
     if (slot >= 11) {
     s = slot - 11;
     }

     } else {
     if (slot >= 18)
     s = slot - 18;
     switch (s) {
     case 18:
     return Arrays.asList(0, 1, 3);
     case 19:
     return Arrays.asList(1, 2, 4);
     case 20:
     return Arrays.asList(0, 2, 3, 4);
     case 21:
     return Arrays.asList(0, 1, 3, 4);
     case 22:
     return Arrays.asList(0, 2, 3, 4);
     case 23:
     return Arrays.asList(0, 1, 2, 3);
     case 24:
     return Arrays.asList(1, 2, 4);
     }
     }
     */

}
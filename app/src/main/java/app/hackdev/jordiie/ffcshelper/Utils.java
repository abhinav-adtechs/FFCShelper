package app.hackdev.jordiie.ffcshelper;


import java.util.Collections;

public class Utils {

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
}
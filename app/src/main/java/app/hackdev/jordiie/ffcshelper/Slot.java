package app.hackdev.jordiie.ffcshelper;


import java.util.LinkedList;
import java.util.List;

public class Slot {
    public boolean isMorn;
    public boolean isTh;
    public List<Integer> day = new LinkedList<>();
    public List<Integer> pos = new LinkedList<>();
    public String courseTitle;
    public String courseCode;
    public int credits;
    public int slot;
}

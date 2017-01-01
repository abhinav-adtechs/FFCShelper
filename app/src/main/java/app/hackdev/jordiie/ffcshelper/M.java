package app.hackdev.jordiie.ffcshelper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by yogesh on 08-01-2016.
 * Message Class(Toast or Log)
 */
public class M {

    /***
     * Shows toast
     *
     * @param context context from activity or service or broadcase receiver
     * @param message message to be displayed as toast
     */
    public static void T(Context context, Object message) {
        Toast.makeText(context, message.toString(), Toast.LENGTH_SHORT).show();
    }


    /***
     * Shows toast
     *
     * @param context  context from activity or service or broadcase receiver
     * @param messages array of message to be displayed as toast
     */
    public static void T(Context context, Object... messages) {
        String s = "";
        for (Object o : messages) {
            s += (o.toString() + "\t");
        }
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Log the message in the Logcat
     *
     * @param tag     tag for identification
     * @param message message to logged
     */
    public static void L(String tag, Object message) {
        Log.d(tag, message.toString());
    }

    /**
     * Log the message in the Logcat
     *
     * @param tag      tag for identification
     * @param messages array of messages to logged
     */
    public static void L(String tag, Object... messages) {
        String s = "";
        for (Object o : messages) {
            s += (o.toString() + "\t");
        }
        Log.d(tag, s);
    }

    public static void L(String tag, List<?> messages) {
        String s = "";
        for (Object o : messages) {
            s += (o.toString() + "\t");
        }
        Log.d(tag, s);
    }

    public static void L(Slot slot) {
        Log.d("slot", slot.courseTitle + "\t\t" + slot.courseCode + "\t\t" + slot.slot + "\t\t" + slot.isMorn + "\t\t" + slot.isTh + "\t\t" + slot.credits + "\t\t" + slot.courseType);
    }

}

package app.hackdev.jordiie.ffcshelper;

/**
 * Listener for slot change
 */
public interface CourseChangeListener {
    /**
     * Triggered when a course is removed
     *
     * @param s The slot that is removed
     */
    void onCourseRemoved(Slot s);
}

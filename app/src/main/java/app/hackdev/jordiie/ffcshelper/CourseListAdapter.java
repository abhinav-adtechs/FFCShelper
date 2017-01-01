package app.hackdev.jordiie.ffcshelper;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseListHolder> {

    private List<Slot> data;
    private LayoutInflater inflater;
    private CourseChangeListener courseChangeListener;

    public void setCourseChangeListener(CourseChangeListener l) {
        courseChangeListener = l;
    }

    public CourseListAdapter(List<Slot> list, Context context) {
        inflater = LayoutInflater.from(context);
        data = list;
    }

    @Override
    public CourseListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CourseListHolder(inflater.inflate(R.layout.item_course, parent, false));
    }

    @Override
    public void onBindViewHolder(CourseListHolder holder, int position) {
        Slot c = data.get(position);
        holder.title.setText(c.courseTitle.toUpperCase());
        holder.code.setText(c.courseCode.toUpperCase());
        String slot = Utils.getSlots().get(c.slot);
        holder.slot.setText(slot);
        holder.credits.setText((c.credits) + "");
        holder.time.setText(Utils.getTimings(c));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CourseListHolder extends RecyclerView.ViewHolder {
        TextView title, slot, code, time, credits;

        public CourseListHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            slot = (TextView) itemView.findViewById(R.id.slot);
            code = (TextView) itemView.findViewById(R.id.code);
            time = (TextView) itemView.findViewById(R.id.timings);
            credits = (TextView) itemView.findViewById(R.id.credits);
            itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        File file = new File(inflater.getContext().getExternalFilesDir(null), "courses.yog");
                        List<String> courses = FileUtils.readLines(file);
                        file.delete();
                        if (courseChangeListener != null)
                            courseChangeListener.onCourseRemoved(data.get(getPosition()));
                        courses.remove(getPosition());
                        FileUtils.writeLines(file, courses, true);
                        data.remove(getPosition());
                        notifyItemRemoved(getPosition());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void addAt(int pos) {
        notifyItemInserted(pos);
    }
}

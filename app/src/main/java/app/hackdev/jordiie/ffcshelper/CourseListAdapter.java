package app.hackdev.jordiie.ffcshelper;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseListHolder> {

    private List<Slot> data;
    private LayoutInflater inflater;

    public CourseListAdapter(List<Slot> list, Context context) {
        inflater = LayoutInflater.from(context);
        data = list;
    }

    @Override
    public CourseListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CourseListHolder(inflater.inflate(R.layout.course_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CourseListHolder holder, int position) {
        Slot c = data.get(position);
        holder.title.setText(c.courseTitle.toUpperCase());
        holder.code.setText(c.courseCode.toUpperCase());
        String slot = Utils.getSlots()[c.slot];
        holder.slot.setText(slot);
        holder.credits.setText((c.credits + 1) + "");
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
        }
    }

    public void addAt(int pos) {
        notifyItemInserted(pos);
    }
}

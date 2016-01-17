package app.hackdev.jordiie.ffcshelper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CourseChangeListener {

    private List<Slot> morn = new LinkedList<>();
    private List<Slot> noon = new LinkedList<>();
    private List<Slot> courses = new LinkedList<>();
    private CourseListAdapter adapter;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        RecyclerView rview = (RecyclerView) findViewById(R.id.rview);
        try {
            List<String> cor = FileUtils.readLines(new File(getExternalFilesDir(null), "courses.yog"));
            for (int i = 0; i < cor.size(); i++) {
                Slot s = Utils.decodeSlot(cor.get(i));
                courses.add(s);
                if (s.isMorn)
                    morn.add(s);
                else
                    noon.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter = new CourseListAdapter(courses, this);
        adapter.setCourseChangeListener(this);
        rview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Show a dialog box for registering
     */
    private void register() {
        final View details = View.inflate(this, R.layout.dialog_view_course_details, null);
        final AppCompatSpinner cred_th = (AppCompatSpinner) details.findViewById(R.id.c_credits_th);
        final AppCompatSpinner cred_la = (AppCompatSpinner) details.findViewById(R.id.c_credits_la);
        final AppCompatSpinner slot_th = (AppCompatSpinner) details.findViewById(R.id.c_slot_th);
        final AppCompatSpinner slot_la = (AppCompatSpinner) details.findViewById(R.id.c_slot_la);
        cred_th.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"1", "2", "3", "4", "5"}));
        cred_la.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"1", "2", "3", "4", "5"}));
        slot_th.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Utils.getSlots().subList(0, 25)));
        slot_la.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Utils.getSlots().subList(25, 53)));
        final LinearLayout labs, thers, therc, labc;
        labs = (LinearLayout) details.findViewById(R.id.labs);
        thers = (LinearLayout) details.findViewById(R.id.thers);
        labc = (LinearLayout) details.findViewById(R.id.labc);
        therc = (LinearLayout) details.findViewById(R.id.therc);
        final AppCompatRadioButton r1, r2, r3;
        r1 = (AppCompatRadioButton) details.findViewById(R.id.thr);
        r2 = (AppCompatRadioButton) details.findViewById(R.id.lar);
        r3 = (AppCompatRadioButton) details.findViewById(R.id.emlr);
        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    r2.setChecked(false);
                    r3.setChecked(false);
                    thers.setVisibility(View.VISIBLE);
                    labs.setVisibility(View.GONE);
                    therc.setVisibility(View.VISIBLE);
                    labc.setVisibility(View.GONE);
                }
            }
        });
        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    r1.setChecked(false);
                    r3.setChecked(false);
                    thers.setVisibility(View.GONE);
                    labs.setVisibility(View.VISIBLE);
                    therc.setVisibility(View.GONE);
                    labc.setVisibility(View.VISIBLE);
                }
            }
        });
        r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    r1.setChecked(false);
                    r2.setChecked(false);
                    thers.setVisibility(View.VISIBLE);
                    labs.setVisibility(View.VISIBLE);
                    therc.setVisibility(View.VISIBLE);
                    labc.setVisibility(View.VISIBLE);
                }
            }
        });
        r1.setChecked(true);
        dialog = new AlertDialog.Builder(this)
                .setView(details)
                .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatEditText c_title, c_code;
                c_title = (AppCompatEditText) details.findViewById(R.id.c_name);
                c_code = (AppCompatEditText) details.findViewById(R.id.c_code);
                slot_th.getSelectedItem();
                if (TextUtils.isEmpty(c_title.getText().toString()) && TextUtils.isEmpty(c_code.getText().toString())) {
                    M.T(MainActivity.this, "Please fill out both Course Title and Course Code");
                } else {
                    int c_type;
                    if (r1.isChecked())
                        c_type = 0;
                    else if (r2.isChecked())
                        c_type = 1;
                    else
                        c_type = 2;
                    int c = Utils.getCredits(courses);
                    M.L("credits", c);
                    if (c_type != 2) {
                        if (c_type == 0) {
                            if ((c + cred_th.getSelectedItemPosition() + 1) <= 27)
                                check(c_title.getText().toString(), c_code.getText().toString(), c_type,
                                        cred_th.getSelectedItemPosition() + 1, slot_th.getSelectedItemPosition());
                            else
                                M.T(MainActivity.this, "Credits exceeding 27");
                        } else {
                            if ((c + cred_la.getSelectedItemPosition() + 1) <= 27)
                                check(c_title.getText().toString(), c_code.getText().toString(), c_type,
                                        cred_la.getSelectedItemPosition() + 1, slot_la.getSelectedItemPosition());
                            else
                                M.T(MainActivity.this, "Credits exceeding 27");
                        }
                    } else {
                        if ((c + (cred_th.getSelectedItemPosition() + 1) + (cred_la.getSelectedItemPosition() + 1)) <= 27)
                            check(c_title.getText().toString(), c_code.getText().toString(), cred_th.getSelectedItemPosition() + 1,
                                    slot_th.getSelectedItemPosition(), cred_la.getSelectedItemPosition() + 1, slot_la.getSelectedItemPosition());
                        else
                            M.T(MainActivity.this, "Credits exceeding 27");
                    }
                }
            }
        });
    }

    /**
     * Check for clashes and add if no clashes
     *
     * @param c_name    Course Title
     * @param c_code    Course Code
     * @param c_type    Course Type
     * @param c_credits Course Credits
     * @param c_slot    Course Slot
     */
    private void check(String c_name, String c_code, int c_type, int c_credits, int c_slot) {
        if (c_type == 1)
            c_slot += 25;
        Slot slot = new Slot();
        slot.isMorn = Utils.isMorn(c_slot);
        slot.isTh = Utils.isTh(c_slot);
        slot.courseCode = c_code;
        slot.courseTitle = c_name;
        slot.credits = c_credits;
        slot.courseType = c_type;
        slot.slot = c_slot;
        slot.pos = Utils.getPos(c_slot, slot.isTh);
        slot.day = Utils.getDays(c_slot, slot.isTh);
        M.L("slotdays", slot.day);
        M.L("slotpos", slot.pos);
        M.L(slot);
        //String data = c_name
        if (slot.isMorn) {
            boolean clash = false;
            for (int i = 0; i < morn.size(); i++) {
                if (Utils.equals(morn.get(i), slot)) {
                    clash = true;
                    break;
                }
            }
            if (!clash) {
                morn.add(slot);
                courses.add(slot);
                adapter.addAt(courses.size() - 1);
                try {
                    FileUtils.write(new File(getExternalFilesDir(null), "courses.yog"), Utils.encodeSlot(slot), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                M.T(this, "Course successfully registered");
                dialog.dismiss();
            } else {
                M.T(this, "This slot clashes with another slot.\nPlease check the slots");
            }
            M.L("course", clash + "\t\t" + "\t\t" + slot.isMorn);
        } else {
            boolean clash = false;
            for (int i = 0; i < noon.size(); i++) {
                if (Utils.equals(slot, noon.get(i))) {
                    clash = true;
                    break;
                }
            }
            if (!clash) {
                noon.add(slot);
                courses.add(slot);
                adapter.addAt(courses.size() - 1);
                try {
                    FileUtils.write(new File(getExternalFilesDir(null), "courses.yog"), Utils.encodeSlot(slot), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                M.T(this, "Course successfully registered");
                dialog.dismiss();
            } else {
                M.T(this, "This slot clashes with another slot.\nPlease check the slots");
            }
            M.L("course", clash + "\t\t" + "\t\t" + slot.isMorn);
        }
    }

    /**
     * Check and add course if not clashing
     *
     * @param c_name       Course Name
     * @param c_code       Course Code
     * @param c_credits_th Credits for Theory
     * @param c_slot_th    Slot for Theory
     * @param c_credits_la Credits for Lab
     * @param c_slot_la    Slot for lab
     */
    private void check(String c_name, String c_code, int c_credits_th, int c_slot_th, int c_credits_la, int c_slot_la) {
        Slot th = new Slot(), la = new Slot();
        th.courseType = 2;
        th.courseCode = c_code;
        th.courseTitle = c_name;
        la.courseType = 2;
        la.courseCode = c_code;
        la.courseTitle = c_name;

        th.slot = c_slot_th;
        th.credits = c_credits_th;
        th.isMorn = Utils.isMorn(c_slot_th);
        th.isTh = Utils.isTh(c_slot_th);
        th.day = Utils.getDays(c_slot_th, th.isTh);
        th.pos = Utils.getPos(c_slot_th, th.isTh);

        c_slot_la += 25;
        la.slot = c_slot_la;
        la.credits = c_credits_la;
        la.isMorn = Utils.isMorn(c_slot_la);
        la.isTh = Utils.isTh(c_slot_la);
        la.day = Utils.getDays(c_slot_la, la.isTh);
        la.pos = Utils.getPos(c_slot_la, la.isTh);

        if (Utils.equals(th, la)) {
            M.T(this, "This course clashes with itself.Please check the slots");
        } else {
            boolean tc = false, lc = false;
            if (th.isMorn) {
                for (int i = 0; i < morn.size(); i++) {
                    if (Utils.equals(morn.get(i), th)) {
                        tc = true;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < noon.size(); i++) {
                    if (Utils.equals(noon.get(i), th)) {
                        tc = true;
                        break;
                    }
                }
            }

            if (la.isMorn) {
                for (int i = 0; i < morn.size(); i++) {
                    if (Utils.equals(morn.get(i), la)) {
                        lc = true;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < noon.size(); i++) {
                    if (Utils.equals(noon.get(i), la)) {
                        lc = true;
                        break;
                    }
                }
            }
            if (!tc && !lc) {
                if (th.isMorn)
                    morn.add(th);
                else
                    noon.add(th);
                courses.add(th);
                adapter.addAt(courses.size() - 1);
                try {
                    FileUtils.write(new File(getExternalFilesDir(null), "courses.yog"), Utils.encodeSlot(th), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (la.isMorn)
                    morn.add(la);
                else
                    noon.add(la);
                courses.add(la);
                adapter.addAt(courses.size() - 1);
                try {
                    FileUtils.write(new File(getExternalFilesDir(null), "courses.yog"), Utils.encodeSlot(la), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                M.T(this, "Course successfully registered");
                dialog.dismiss();
            } else {
                M.T(this, "This slot clashes with another slot.\nPlease check the slots");
            }
        }
    }

    @Override
    public void onCourseRemoved(Slot s) {
        if (s.isMorn)
            morn.remove(s);
        else
            noon.remove(s);
    }
}

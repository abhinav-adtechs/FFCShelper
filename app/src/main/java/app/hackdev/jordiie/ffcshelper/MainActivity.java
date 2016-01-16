package app.hackdev.jordiie.ffcshelper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CourseRemoveListener {

    private EditText passwordInput;
    private View positiveAction;

    private List<Slot> morn = new LinkedList<>();
    private List<Slot> noon = new LinkedList<>();
    private List<Slot> courses = new LinkedList<>();
    private CourseListAdapter adapter;


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
        adapter.setCourseRemoveListener(this);
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

    /*public void showCustomView() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.choosecourse)
                .customView(R.layout.dialog_course, true)
                .positiveText(R.string.setcourse)
                .negativeText(android.R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                }).build();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        //noinspection ConstantConditions
        *//*passwordInput = (EditText) dialog.getCustomView().findViewById(R.id.password);
        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                positiveAction.setEnabled(s.toString().trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        *//*
        // Toggling the show password CheckBox will mask or unmask the password input EditText
        CheckBox checkbox = (CheckBox) dialog.getCustomView().findViewById(R.id.addlab);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                passwordInput.setInputType(!isChecked ? InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_TEXT);
                passwordInput.setTransformationMethod(!isChecked ? PasswordTransformationMethod.getInstance() : null);
            }
        });

        dialog.show();
        positiveAction.setEnabled(false); // disabled by default
    }*/

    /**
     * Show a dialog box for registering
     */
    private void register() {
        final View details = View.inflate(this, R.layout.dialog_view_course_details, null);
        final AppCompatSpinner cred = (AppCompatSpinner) details.findViewById(R.id.c_credits);
        final AppCompatSpinner slot = (AppCompatSpinner) details.findViewById(R.id.c_slot);
        cred.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"1", "2", "3", "4", "5"}));
        slot.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Utils.getSlots()));
        final AlertDialog dialog = new AlertDialog.Builder(this)
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
                slot.getSelectedItem();
                if (TextUtils.isEmpty(c_title.getText().toString()) && TextUtils.isEmpty(c_code.getText().toString())) {
                    M.T(MainActivity.this, "Please fill out both Course Title and Course Code");
                } else {
                    dialog.dismiss();
                    check(c_title.getText().toString(), c_code.getText().toString(),
                            cred.getSelectedItemPosition(), slot.getSelectedItemPosition()
                    );
                }
            }
        });
    }

    /**
     * Check for clashes and add if no clashes
     *
     * @param c_name    Course Title
     * @param c_code    Coirse Code
     * @param c_credits Course Credits
     * @param c_slot    Course Slot
     */
    private void check(String c_name, String c_code, int c_credits, int c_slot) {
        Slot slot = new Slot();
        slot.isMorn = Utils.isMorn(c_slot);
        slot.isTh = Utils.isTh(c_slot);
        slot.courseCode = c_code;
        slot.courseTitle = c_name;
        slot.credits = c_credits;
        slot.slot = c_slot;
        slot.pos = Utils.getPos(c_slot, slot.isTh);
        slot.day = Utils.getDays(c_slot, slot.isTh);
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
            } else {
                M.T(this, "This slot clashes with another slot.\nPlease check the slots");
            }
            M.L("course", clash + "\t\t" + "\t\t" + slot.isMorn);
        }
    }

    @Override
    public void onCourseRemoved(Slot s) {
        if (s.isMorn)
            morn.remove(s);
        else
            noon.remove(s);
        M.L("course", morn.size() + "\t\t" + noon.size());
    }
}

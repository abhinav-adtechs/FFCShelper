package app.hackdev.jordiie.ffcshelper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import yogesh.firzen.mukkiasevaigal.M;

public class MainActivity extends AppCompatActivity {

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
        adapter = new CourseListAdapter(courses, this);
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

    private void register() {
        final View details = View.inflate(this, R.layout.course_details, null);
        final AppCompatSpinner cred = (AppCompatSpinner) details.findViewById(R.id.c_credits);
        final AppCompatSpinner slot = (AppCompatSpinner) details.findViewById(R.id.c_slot);
        cred.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"1", "2", "3", "4", "5"}));
        slot.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Utils.getSlots()));
        AppCompatDialog dialog = new AlertDialog.Builder(this)
                .setView(details)
                .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppCompatEditText c_title, c_code;
                        c_title = (AppCompatEditText) details.findViewById(R.id.c_name);
                        c_code = (AppCompatEditText) details.findViewById(R.id.c_code);
                        slot.getSelectedItem();
                        if (TextUtils.isEmpty(c_title.getText().toString()) && TextUtils.isEmpty(c_code.getText().toString()))
                            M.T(MainActivity.this, "Please fill out both Course Title and Course Code");
                        else {
                            check(c_title.getText().toString(), c_code.getText().toString(),
                                    cred.getSelectedItemPosition(), slot.getSelectedItemPosition(),
                                    String.valueOf(slot.getSelectedItem()));
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        dialog.show();
    }

    private void check(String c_name, String c_code, int c_credits, int c_slot, String s) {
        Slot slot = new Slot();
        slot.isMorn = isMorn(c_slot);
        slot.isTh = isTh(c_slot);
        slot.courseCode = c_code;
        slot.courseTitle = c_name;
        slot.credits = c_credits;
        slot.slot = c_slot;
        slot.pos = getPos(c_slot, slot.isTh);
        slot.day = getDays(c_slot, slot.isTh);
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
                //FileUtils.write(new File(getFilesDir(), "courses.yog"), data, true);
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
                //FileUtils.write(new File(getFilesDir(), "courses.yog"), data, true);
                M.T(this, "Course successfully registered");
            } else {
                M.T(this, "This slot clashes with another slot.\nPlease check the slots");
            }
            M.L("course", clash + "\t\t" + "\t\t" + slot.isMorn);
        }
    }

    private boolean isTh(int slot) {
        return slot >= 0 && slot <= 24;
    }

    private boolean isMorn(int slot) {
        return slot >= 0 && slot <= 10 || slot >= 25 && slot <= 37;
    }

    private List<Integer> getPos(int slot, boolean isTh) {
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
            /*int s = slot;
            if (slot >= 38) {
                s = slot - 13;
            }*/

        }
    }

    private List<Integer> getDays(int slot, boolean isTh) {
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
            /*int s = slot;
            if (slot >= 38) {
                s = slot - 13;
            }*/

        }
        return new LinkedList<>();
    }

}

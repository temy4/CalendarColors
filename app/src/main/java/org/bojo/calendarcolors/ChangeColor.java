package org.bojo.calendarcolors;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import org.bojo.calendarcolors.ColorPickerDialog;

import java.util.Calendar;


public class ChangeColor extends ActionBarActivity {
    public final static int calId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView oldColor = (TextView) findViewById(R.id.oldColor);
        final TextView newColor = (TextView) findViewById(R.id.newColor);

        final SeekBar sbRed = (SeekBar) findViewById(R.id.sbRed);
        final SeekBar sbGreen = (SeekBar) findViewById(R.id.sbGreen);
        final SeekBar sbBlue = (SeekBar) findViewById(R.id.sbBlue);

        final EditText numRed = (EditText) findViewById(R.id.numRed);
        final EditText numGreen = (EditText) findViewById(R.id.numGreen);
        final EditText numBlue = (EditText) findViewById(R.id.numBlue);

        final Button btnSave = (Button) findViewById(R.id.btnSave);
        final Button btnCancel = (Button) findViewById(R.id.btnCancel);

        numRed.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    sbRed.setProgress(Integer.parseInt(s.toString()));
                }
                catch (Exception ex) {}
            }
        });

        numGreen.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    sbGreen.setProgress(Integer.parseInt(s.toString()));
                }
                catch (Exception ex) {}
            }
        });

        numBlue.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    sbBlue.setProgress(Integer.parseInt(s.toString()));
                }
                catch (Exception ex) {}
            }
        });

        sbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar sbRed) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar sbRed) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar sbRed, int progress,boolean fromUser) {
                if (fromUser) {
                    numRed.setText(String.valueOf(sbRed.getProgress()));
                }
                newColor.setBackgroundColor(
                        Color.argb(
                                255, progress,
                                sbGreen.getProgress(), sbBlue.getProgress()
                        )
                );
            }
        });

        sbGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar sbGreen) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar sbGreen) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar sbGreen, int progress,boolean fromUser) {
                if (fromUser) {
                    numGreen.setText(String.valueOf(sbGreen.getProgress()));
                }
                newColor.setBackgroundColor(
                        Color.argb(
                                255, sbRed.getProgress(),
                                progress, sbBlue.getProgress()
                        )
                );
            }
        });

        sbBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar sbBlue) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar sbBlue) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar sbBlue, int progress,boolean fromUser) {
                if (fromUser) {
                    numBlue.setText(String.valueOf(sbBlue.getProgress()));
                }
                newColor.setBackgroundColor(
                        Color.argb(
                                255, sbRed.getProgress(),
                                sbGreen.getProgress(), progress
                        )
                );
            }
        });

        Intent intent = getIntent();
        int oColor = intent.getIntExtra("oColor", 0);
        final int calID = intent.getIntExtra("calID", 1);

        oldColor.setBackgroundColor(oColor);
        newColor.setBackgroundColor(oColor);

        ColorDrawable cd = (ColorDrawable) oldColor.getBackground();
        final int color = cd.getColor();
        int oRed = Color.red(color);
        int oGreen = Color.green(color);
        int oBlue = Color.blue(color);
        numRed.setText(String.valueOf(oRed));
        numGreen.setText(String.valueOf(oGreen));
        numBlue.setText(String.valueOf(oBlue));

        ColorDrawable cdn = (ColorDrawable) newColor.getBackground();
        final int colorn = cdn.getColor();

        newColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                ColorPickerDialog.OnColorChangedListener getColorFromPicker = new ColorPickerDialog.OnColorChangedListener() {
                    public void colorChanged(String key, int color) {
                        Context context = getApplicationContext();
                        CharSequence text = "Hello toast!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                };
//                Context c, OnColorChangedListener l, int color,int defaultColor)
                 new ColorPickerDialog(context, getColorFromPicker, "color", colorn, color).show();
                final ColorPickerDialog colorDialog = new ColorPickerDialog(this, initialValue);

                colorDialog.setAlphaSliderVisible(true);
                colorDialog.setTitle("Pick a Color!");
            }
        });

        btnSave.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                Uri l_calendars;
                ContentResolver cr = getContentResolver();
                String[] l_projection = new String[]{"_id", "calendar_displayName", "calendar_color"};

                if (Build.VERSION.SDK_INT >= 8 ) {
                    l_calendars = Uri.parse("content://com.android.calendar/calendars");
                } else {
                    l_calendars = Uri.parse("content://calendar/calendars");
                }
                values.put(
                        CalendarContract.Calendars.CALENDAR_COLOR,
                        Color.argb(255,
                                sbRed.getProgress(),
                                sbGreen.getProgress(),
                                sbBlue.getProgress()
                        )
                );
                cr.update(l_calendars, values, "_id=" + calID, l_projection);

                finish();
            }
        });

        btnCancel.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_color, menu);
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
}

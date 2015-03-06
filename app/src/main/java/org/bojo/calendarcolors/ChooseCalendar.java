package org.bojo.calendarcolors;

import android.content.ContentResolver;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.Typeface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

public class ChooseCalendar extends ActionBarActivity {

    TableLayout calendars;
    TableRow cal;
    TextView calClr,calName,calNF, line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_calendar);

        calendars = (TableLayout) findViewById(R.id.calendars);
        calendars.setBackgroundColor(Color.WHITE);

        listUserCalendars();
    }

    @Override
    public void onResume() {
        super.onResume();
        listUserCalendars();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_calendar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_about:
                View v = this.findViewById(android.R.id.content);
                Intent intAbout = new Intent(v.getContext(), About.class);
                startActivity(intAbout);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void printCalendars(Cursor l_managedCursor, String[] l_projection, int titleString){
        if (l_managedCursor.moveToFirst()) {

            cal = new TableRow(this);
            TextView delimiter = new TextView(this);
            delimiter.setText(titleString);
            delimiter.setTextSize(15);

            calClr = new TextView(this);
            cal.addView(calClr);

            cal.addView(delimiter);
            line = new TextView(this);
            line.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 1));
            line.setBackgroundColor(Color.rgb(240, 240, 240));

            // Add the TableRow to the TableLayout
            calendars.addView(cal);
            calendars.addView(line);

            String l_calId;
            String l_calName;
            String l_calClr;
            int l_idCol = l_managedCursor.getColumnIndex(l_projection[0]);
            int l_nameCol = l_managedCursor.getColumnIndex(l_projection[1]);
            int l_clrCol = l_managedCursor.getColumnIndex(l_projection[2]);
            do {
                l_calName = l_managedCursor.getString(l_nameCol);
                l_calId = l_managedCursor.getString(l_idCol);
                l_calClr = l_managedCursor.getString(l_clrCol);

                cal = new TableRow(this);
                cal.setLayoutParams(new LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
                cal.setBackgroundColor(Color.WHITE);

                calClr = new TextView(this);
                calClr.setWidth(15);
                calClr.setHeight(110);
                calClr.setBackgroundColor(0xff000000 + Integer.parseInt(l_calClr));
                calClr.setPadding(30,30,30,30);
                cal.addView(calClr);

                calName = new TextView(this);
                calName.setHeight(110);
                calName.setText(l_calName);
                calName.setTextColor(Color.BLACK);
                calName.setTextSize(20);
                calName.setPadding(20, 20, 20, 30);
                calName.setClickable(true);
                cal.addView(calName);

                final int editID = Integer.valueOf(l_calId);
                final int oColor = 0xff000000 + Integer.parseInt(l_calClr);
                calName.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //editID
                        v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                        Intent intChangeColor = new Intent(v.getContext(), ChangeColor.class);
                        intChangeColor.putExtra("calID", editID);
                        intChangeColor.putExtra("oColor", oColor);
                        startActivity(intChangeColor);
                        v.setBackgroundColor(Color.WHITE);
                    }
                });

                line = new TextView(this);
                line.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 1));
                line.setBackgroundColor(Color.rgb(200, 200, 200));

                // Add the TableRow to the TableLayout
                calendars.addView(cal, new TableLayout.LayoutParams(
                        LayoutParams.FILL_PARENT,
                        LayoutParams.WRAP_CONTENT));
                calendars.addView(line);
            } while (l_managedCursor.moveToNext());
        }
    }

    public void listUserCalendars() {
        calendars.removeAllViewsInLayout();

        String[] l_projection = new String[]{"_id", "calendar_displayName", "calendar_color"};
        Uri l_calendars;
        ContentResolver cr = getContentResolver();

        if (Build.VERSION.SDK_INT >= 8 ) {
            l_calendars = Uri.parse("content://com.android.calendar/calendars");
        } else {
            l_calendars = Uri.parse("content://calendar/calendars");
        }
        Cursor c_activeCalendars = cr.query(l_calendars, l_projection, "visible=1", null, null);    //all calendars
        Cursor c_inActiveCalendars = cr.query(l_calendars, l_projection, "visible=0", null, null);    //all calendars

        if (c_activeCalendars.getCount() > 0){
            printCalendars(c_activeCalendars, l_projection, R.string.active);
        }
        if (c_inActiveCalendars.getCount() > 0){
            printCalendars(c_inActiveCalendars, l_projection, R.string.inactive);
        }
        if (c_activeCalendars.getCount() == 0 && c_inActiveCalendars.getCount() == 0){
            calNF = new TextView(this);
            calNF.setText(R.string.cal_nf);
            calNF.setTypeface(null, Typeface.ITALIC);
            calNF.setHeight(30);
            calNF.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            calendars.addView(calNF, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    50));
        }
    }
}

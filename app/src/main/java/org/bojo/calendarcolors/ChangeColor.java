package org.bojo.calendarcolors;

import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class ChangeColor extends ActionBarActivity {
    public final static int calId = 0;
    public final static int oColor= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);

        final EditText hexColor = (EditText) findViewById(R.id.hexColor);
        final TextView oldColor = (TextView) findViewById(R.id.oldColor);
        final TextView newColor = (TextView) findViewById(R.id.newColor);

        final SeekBar sbRed = (SeekBar) findViewById(R.id.sbRed);
        final SeekBar sbGreen = (SeekBar) findViewById(R.id.sbGreen);
        final SeekBar sbBlue = (SeekBar) findViewById(R.id.sbBlue);
        final SeekBar sbAlpha = (SeekBar) findViewById(R.id.sbAlpha);

        final EditText numRed = (EditText) findViewById(R.id.numRed);
        final EditText numGreen = (EditText) findViewById(R.id.numGreen);
        final EditText numBlue = (EditText) findViewById(R.id.numBlue);
        final EditText numAlpha = (EditText) findViewById(R.id.numAlpha);

        oldColor.setBackgroundColor(oColor);
        
        numRed.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    hexColor.setText(String.format("#%06X", (0xFFFFFF & newColor.getPaint().getColor())));
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
                    hexColor.setText(String.format("#%06X", (0xFFFFFF & newColor.getPaint().getColor())));
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
                    hexColor.setText(String.format("#%06X", (0xFFFFFF & newColor.getPaint().getColor())));
                    sbBlue.setProgress(Integer.parseInt(s.toString()));
                }
                catch (Exception ex) {}
            }
        });

        numAlpha.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    hexColor.setText(String.format("#%06X", (0xFFFFFF & newColor.getPaint().getColor())));
                    sbAlpha.setProgress(Integer.parseInt(s.toString()));
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
                    hexColor.setText(String.format("#%06X", (0xFFFFFF & newColor.getPaint().getColor())));
                }
                newColor.setBackgroundColor(
                        Color.argb(
                                255 - sbAlpha.getProgress(), progress,
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
                    hexColor.setText(String.format("#%06X", (0xFFFFFF & newColor.getPaint().getColor())));
                }
                newColor.setBackgroundColor(
                        Color.argb(
                                255 - sbAlpha.getProgress(), sbRed.getProgress(),
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
                    hexColor.setText(String.format("#%06X", (0xFFFFFF & newColor.getPaint().getColor())));
                }
                newColor.setBackgroundColor(
                        Color.argb(
                                255 - sbAlpha.getProgress(), sbRed.getProgress(),
                                sbGreen.getProgress(), progress
                        )
                );
            }
        });

        sbAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar sbAlpha) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar sbAlpha) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar sbAlpha, int progress,boolean fromUser) {
                if (fromUser) {
                    numAlpha.setText(String.valueOf(sbAlpha.getProgress()));
                }
                newColor.setBackgroundColor(
                        Color.argb(
                                255 - progress, sbRed.getProgress(),
                                sbGreen.getProgress(), sbBlue.getProgress()
                        )
                );
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

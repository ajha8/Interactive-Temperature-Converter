package edu.iit.ajha8.Converter;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.View;
import android.widget.TextView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity
{

    private EditText text_input;
    private RadioGroup conversion_type;
    private TextView text_history;
    private TextView conversion_result;
    private TextView degtype;
    private TextView convdeg;
    private TextView convhistory;
    private Button clearhistory;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_vertical);
        text_input = (EditText) findViewById(R.id.text_input);
        text_history = (TextView) findViewById(R.id.text_output);
        conversion_result = (TextView) findViewById(R.id.conversion_result);
        conversion_type = (RadioGroup) findViewById(R.id.radioGroup);
        text_history.setMovementMethod(ScrollingMovementMethod.getInstance());
        degtype = (TextView) findViewById(R.id.degtype);
        convdeg = (TextView) findViewById(R.id.convdeg);
        convhistory = (TextView) findViewById(R.id.convhistory);
        degtype.setText(String.format("Celcius Degrees:"));

        if (savedInstanceState != null)
        {
            text_history.setText(savedInstanceState.getString("textView"));
            conversion_result.setText(savedInstanceState.getString("result"));
            degtype.setText(savedInstanceState.getString("textView2"));
            convdeg.setText(savedInstanceState.getString("textView3"));
            convhistory.setText(savedInstanceState.getString("convhistory"));
        }
    }

    public void radio_button_changed(View view) {
        if(conversion_type.getCheckedRadioButtonId() == R.id.far2celcius)
        {
            degtype.setText(String.format("Fahrenheit Degrees:"));
        }
        else {
            degtype.setText(String.format("Celcius Degrees:"));
        }
    }

    public void convert(View view) {
        String input = text_input.getText().toString();
        if (input.length() == 0) {
            return;
        }
        String history = text_history.getText().toString();
        float output_val;
        convhistory.setText(String.format("Coversion History"));
        clearhistory = (Button) findViewById(R.id.clearhistory);
        clearhistory.setEnabled(true);



        if (conversion_type.getCheckedRadioButtonId() == R.id.celcius2far) {
            float input_val = Float.valueOf(input).floatValue();
            convdeg.setText(String.format("Fahrenheit Degrees:"));
            output_val = (float) ((input_val * (9.0 / 5.0)) + 32.0);
            conversion_result.setText(String.format(" %.1f F ", output_val));
            text_history.append(String.format(String.valueOf(input_val) + " C => %.1f F \n", output_val));
        } else {
            float input_val = Float.valueOf(input).floatValue();
            convdeg.setText(String.format("Celcius Degrees:"));
            output_val = (float) ((input_val - 32.0) * (5.0 / 9.0));
            conversion_result.setText(String.format(" %.1f C", output_val));
            text_history.append(String.format(String.valueOf(input_val) + " F => %.1f C\n", output_val));
        }
        //inputText.setText("");
    }

    public void clear_history_clicked(View view){
        text_history.setText(" ");
        clearhistory.setEnabled(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        outState.putString("textView", text_history.getText().toString());
        outState.putString("textView2", degtype.getText().toString());
        outState.putString("textView3", convdeg.getText().toString());
        outState.putString("result", conversion_result.getText().toString());
        outState.putString("convhistory", convhistory.getText().toString());
        super.onSaveInstanceState(outState);
    }
}



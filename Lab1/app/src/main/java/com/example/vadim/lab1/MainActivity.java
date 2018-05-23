package com.example.vadim.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner Size = (Spinner) findViewById(R.id.Size);

        ArrayAdapter<String> MyAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Size));
        MyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Size.setAdapter(MyAdapter);
    }

    public void OkClick(View V){
        EditText InputText = (EditText) findViewById(R.id.InputText);
        TextView OutputText = (TextView) findViewById(R.id.OutputText);
        Spinner Size = (Spinner) findViewById(R.id.Size);
        OutputText.setTextSize(Float.parseFloat(Size.getSelectedItem().toString()));
        OutputText.setText(InputText.getText());
    }

    public void CancelClick(View V){
        EditText InputText = (EditText) findViewById(R.id.InputText);
        TextView OutputText = (TextView) findViewById(R.id.OutputText);
        OutputText.setText(null);
        InputText.setText(null);
    }
}

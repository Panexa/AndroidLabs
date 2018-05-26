package com.example.vadim.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "Text", EXTRA_SIZE="Size";
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

        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("file", MODE_PRIVATE)));
            // пишем данные
            bw.write(InputText.getText().toString()+"\n"+Size.getSelectedItem().toString());
            // закрываем поток
            bw.close();
            Log.d("MyLog", "File saved");
            Toast.makeText(this, "File saved", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void CancelClick(View V){
        EditText InputText = (EditText) findViewById(R.id.InputText);
        TextView OutputText = (TextView) findViewById(R.id.OutputText);
        OutputText.setText(null);
        InputText.setText(null);
    }

    public void OpenClick(View V){
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput("file")));
            String str = "", str2 = "";
            // читаем содержимое
            str = br.readLine();
            str2 = br.readLine();
            if(str == "")
            {
                Toast.makeText(this, "File is empty!", Toast.LENGTH_LONG).show();
            }
            else
            {
                try {
                    Intent intent = new Intent(this, DisplayActivity.class);
                    intent.putExtra(EXTRA_TEXT, str);
                    intent.putExtra(EXTRA_SIZE, str2);
                    startActivity(intent);
                }
                catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

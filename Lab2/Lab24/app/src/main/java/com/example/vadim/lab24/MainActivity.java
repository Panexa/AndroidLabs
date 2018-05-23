package com.example.vadim.lab24;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements InputFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Fragment frag2 = new InputFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.inputFragment, frag2);
        ft.commit();

        Spinner Size = (Spinner) findViewById(R.id.Size);

        ArrayAdapter<String> MyAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Size));
        MyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Size.setAdapter(MyAdapter);
    }

    @Override
    public void onFragmentInteraction(String link) {
        OutputFragment fragment = (OutputFragment) getFragmentManager()
                .findFragmentById(R.id.outputFragment);

        if (fragment != null && fragment.isInLayout()) {
            fragment.setText(link);
            fragment.setSize(Float.parseFloat(((Spinner) findViewById(R.id.Size)).getSelectedItem().toString()));
        }
    }

}
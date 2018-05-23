package com.example.vadim.lab24;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OutputFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.output_fragment, container, false);
        return view;
    }

    // обновление текстового поля
    public void setText(String text) {
        TextView view = (TextView) getView().findViewById(R.id.outputText);
        view.setText(text);
    }

    public void setSize(Float size) {
        TextView view = (TextView) getView().findViewById(R.id.outputText);
        view.setTextSize(size);
    }

}

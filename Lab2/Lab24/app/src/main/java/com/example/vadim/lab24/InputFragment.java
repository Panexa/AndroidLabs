package com.example.vadim.lab24;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class InputFragment extends Fragment{
    private OnFragmentInteractionListener mListener;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.input_fragment, container, false);

        Button OkB = (Button) view.findViewById(R.id.OkB);
        OkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOutput();
            }
        });
        Button CancelB = (Button) view.findViewById(R.id.CancelB);
        CancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOutput();
            }
        });

        return view;
    }

    interface OnFragmentInteractionListener {

        void onFragmentInteraction(String link);
    }

    @Override
    public void onAttach(Activity MainActivity) {
        super.onAttach(MainActivity);
        mListener = (OnFragmentInteractionListener) MainActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    public void deleteOutput(){
        ((EditText) view.findViewById(R.id.InputText)).setText(null);
        mListener.onFragmentInteraction(null);
    }

    public void updateOutput() {
        // генерируем некоторые данные
        String text = ((EditText) view.findViewById(R.id.InputText)).getText().toString();
        // Посылаем данные Activity
        mListener.onFragmentInteraction(text);
    }
}

package com.example.calculatordegree.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculatordegree.ConverterClass;
import com.example.calculatordegree.MainActivity;
import com.example.calculatordegree.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DegreesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DegreesFragment extends Fragment {

    private Button btnConverter;
    private RadioGroup radioGroup;
    //private RadioButton radioButton;
    //private RadioButton toCelsius;
    //private RadioButton toFahrenheit;
    private EditText inputValue;
    private TextView showValueConverted;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DegreesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DegreesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DegreesFragment newInstance(String param1, String param2) {
        DegreesFragment fragment = new DegreesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_degrees, container, false);

        btnConverter = view.findViewById(R.id.bnt_converter);
        radioGroup = view.findViewById(R.id.radio_group);
        inputValue = view.findViewById(R.id.input_value);
        showValueConverted = view.findViewById(R.id.show_value_converted);

        btnConverter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int choseOpt = radioGroup.getCheckedRadioButtonId();
                ConverterClass converterClass = new ConverterClass();

                if(choseOpt != -1) {
                    SQLiteDatabase db = view.getContext().openOrCreateDatabase("app", Context.MODE_PRIVATE, null);
                    db.execSQL("CREATE TABLE IF NOT EXISTS operations (id INTEGER PRIMARY KEY AUTOINCREMENT, initVal VARCHAR, valConverted VARCHAR)");

                    RadioButton opt = radioGroup.findViewById(choseOpt);

                    String selectedOpt = opt.getText().toString();
                    double value = Double.parseDouble(inputValue.getText().toString());

                    if(selectedOpt.equals("Dollar")) {
                        double doll = converterClass.realToDollar(value);
                        db.execSQL("INSERT INTO operations(initVal, valConverted) VALUES('" + value +  " R$', '" + Double.toString(doll) + " $')");
                        showValueConverted.setText(Double.toString(doll));
                    } else {
                        double eur = converterClass.realToEuro(value);
                        db.execSQL("INSERT INTO operations(initVal, valConverted) VALUES('" + value + " R$', '" + Double.toString(eur) + " €')");
                        showValueConverted.setText(Double.toString(eur));
                    }
                }
            }
        });

        return view;
    }
}
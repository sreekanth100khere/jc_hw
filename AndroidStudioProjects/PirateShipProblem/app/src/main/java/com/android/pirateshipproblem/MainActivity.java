package com.android.pirateshipproblem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public Button mCalculateNoOfDaysBtn;
    public EditText mArrayInputEt;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mArrayInputEt = findViewById(R.id.id_input_et);

        mCalculateNoOfDaysBtn = findViewById(R.id.id_calculate_btn);

        mCalculateNoOfDaysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String arrayInput = mArrayInputEt.getText().toString();
                ArrayList<Integer> itemsListOfInteger = new ArrayList<Integer>();

                List<String> itemsListOfString = null;
                try {

                     itemsListOfString = Arrays.asList(arrayInput.split("\\s*,\\s*"));

                    for (String s : itemsListOfString) itemsListOfInteger.add(Integer.valueOf(s));
                }catch (Exception e) {
                    Toast.makeText(mContext, "Invalid Input", Toast.LENGTH_LONG).show();
                }

                boolean hasRemovalHappened = true;

                int noOfDaysPassed  = 0;

                do {
                    hasRemovalHappened = false;
                    for (int i = 0; i < itemsListOfInteger.size(); i++) {
                        if(i+1 < itemsListOfInteger.size()) {
                            if (itemsListOfInteger.get(i + 1) > itemsListOfInteger.get(i)) {
                                //Item on right> Item on left
                                //Remove item on right.
                                itemsListOfInteger.remove(i + 1);
                                hasRemovalHappened = true;

                                Log.d("After each removal", "Array"+itemsListOfInteger.toString());

                            }
                        }
                    }

                    if(hasRemovalHappened){
                        noOfDaysPassed+=1;
                    }
                }while(hasRemovalHappened);

                Toast.makeText(mContext,String.valueOf(noOfDaysPassed),Toast.LENGTH_LONG).show();

            }
        });
    }
}
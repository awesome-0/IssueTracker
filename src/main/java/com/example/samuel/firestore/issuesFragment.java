package com.example.samuel.firestore;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.samuel.firestore.models.IssuesSpinnerItems;


/**
 * A simple {@link Fragment} subclass.
 */
public class issuesFragment extends Fragment {
    Spinner taskSpinner;
    TaskAdapter taskAdapter;
    TaskAdapter priorityAdapter;
    Spinner prioritySpinner;



    public issuesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_issues, container, false);
        setUpWidgets(view);
        taskAdapter = new TaskAdapter(getActivity(), R.layout.spinner_layout,IssuesSpinnerItems.taskImages,IssuesSpinnerItems.taskNames);
       priorityAdapter = new TaskAdapter(getActivity(),R.layout.spinner_layout,IssuesSpinnerItems.priorityImages,IssuesSpinnerItems.priorityNames);


       // taskAdapter.setDropDownViewResource( R.layout.spinner_layout);
        taskSpinner.setAdapter(taskAdapter);
        prioritySpinner.setAdapter(priorityAdapter);



        taskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        prioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),IssuesSpinnerItems.priorityNames[i],Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;
    }


    private void setUpWidgets(View view) {
        taskSpinner = view.findViewById(R.id.task_spinner);
        prioritySpinner = view.findViewById(R.id.priority_spinner);
    }

}

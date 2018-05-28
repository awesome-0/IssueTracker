package com.example.samuel.firestore;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuel.firestore.models.IssuesSpinnerItems;


/**
 * A simple {@link Fragment} subclass.
 */
public class issuesFragment extends Fragment {


    private ImageView textView;


    public issuesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_issues, container, false);
        textView = view.findViewById(R.id.create);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),NewIssueActivity.class);
                getActivity().startActivityForResult(intent,Consts.ADDED_ISSUE_SUCCESS);

            }
        });

        return view;
    }




}

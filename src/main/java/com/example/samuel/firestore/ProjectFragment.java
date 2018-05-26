package com.example.samuel.firestore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class ProjectFragment extends Fragment {

    public issuesInterface issuesInterface;
    private ImageView addProject;
    private EditText searchProject;
    public ProjectFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_project, container, false);
        searchProject = view.findViewById(R.id.searchProject);
        searchProject.clearFocus();
       addProject = view.findViewById(R.id.add);


       addProject.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               getActivity().startActivity(new Intent(getActivity(),NewProjectActivity.class));
           }
       });



       return  view;

    }
}

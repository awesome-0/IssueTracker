package com.example.samuel.firestore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class ProjectFragment extends Fragment {

    public issuesInterface issuesInterface;
    private ImageView addProject;
    private EditText searchProject;
    private static final String TAG = "ProjectFragment";
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
               Intent intent  =  new Intent(getActivity(),NewProjectActivity.class);
             //  intent.putExtra("","");
               startActivityForResult(intent,Consts.ADDED_PROJECT_SUCCESS);
           }
       });

       return  view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: result code is " + resultCode);
        if(data != null) {
            if (resultCode == Consts.ADDED_PROJECT_SUCCESS ) {
                Log.d(TAG, "onActivityResult: success adding result");
                issuesInterface.buildSnackBarMessage(data.getStringExtra(getString(R.string.snackbar_message)));
            } else {
                issuesInterface.buildSnackBarMessage(data.getStringExtra(getString(R.string.snackbar_message)));
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       try{
           issuesInterface = (issuesInterface) getActivity();
       }
       catch (ClassCastException e){
           e.printStackTrace();
       }



    }
}

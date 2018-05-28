package com.example.samuel.firestore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskAdapter extends ArrayAdapter<String> {
    private int[] spinnerImages;
    private String [] spinnerTitles;
    Context context;
    int resource;
   // int position = 0;

    public TaskAdapter(@NonNull Context context, int resource, int[] spinnerImages, String[] spinnerTitles) {
        super(context, resource);
        this.spinnerImages = spinnerImages;
        this.spinnerTitles = spinnerTitles;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
     //   this.position = position;
        ViewHolder holder ;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource,parent,false);
            holder = new ViewHolder(convertView);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.taskTitle.setText(spinnerTitles[position]);
        holder.taskImage.setImageResource(spinnerImages[position]);

        return convertView;
    }

    @Override
    public int getCount() {
        return  spinnerImages.length;
    }
    public static class ViewHolder{
        ImageView taskImage;
        TextView taskTitle;
        public ViewHolder(View view){
            taskImage = view.findViewById(R.id.spinner_image);
            taskTitle = view.findViewById(R.id.spinner_text);
            view.setTag(this);
        }
    }


//    public String getSelectedText(){
//      //  return spinnerTitles[position];
//    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position,convertView,parent);
    }
}

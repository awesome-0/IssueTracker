package com.example.samuel.firestore.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Project implements Parcelable {
    private String name;
    private String description;
    private String creator;
    private @ServerTimestamp Date timeStamp;
    private String avatar;
    private String projectId;


    protected Project(Parcel in) {
        name = in.readString();
        description = in.readString();
        avatar = in.readString();
        projectId = in.readString();
        creator = in.readString();
        timeStamp = (Date)in.readSerializable();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public Project() {
    }



    public Project(String name, String description,String creator, Date timeStamp, String avatar, String projectId) {
        this.name = name;
        this.description = description;
        this.timeStamp = timeStamp;
        this.creator = creator;
        this.avatar = avatar;
        this.projectId = projectId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(avatar);
        parcel.writeString(projectId);
        parcel.writeString(creator);
        parcel.writeSerializable(timeStamp);
    }
}

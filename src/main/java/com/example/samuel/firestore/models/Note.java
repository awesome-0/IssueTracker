package com.example.samuel.firestore.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Note implements Parcelable {
    private String title;
    private String content;
    private @ServerTimestamp Date timeStamp;
    private String note_id;
    private String user_id;


    protected Note(Parcel in) {
        title = in.readString();
        content = in.readString();
        note_id = in.readString();
        user_id = in.readString();
        timeStamp = (Date)in.readSerializable();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public Note() {
    }



    public Note(String title, String content, Date timeStamp, String note_id, String user_id) {
        this.title = title;
        this.content = content;
        this.timeStamp = timeStamp;
        this.note_id = note_id;
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNote_id() {
        return note_id;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(note_id);
        parcel.writeString(user_id);
        parcel.writeSerializable(timeStamp);
    }
}

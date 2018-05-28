package com.example.samuel.firestore.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Issue implements Parcelable {
    public static final String IN_PROGRESS = "In Progress";
    public static final String DONE = "Done";
    public static final String IDLE = "Idle";
    public static final String HIGH = "High";
    public static final String MEDIUM = "Medium";
    public static final String LOW = "Low";
    public static final String Task = "Task";
    public static final String BUG = "Bug";

    private String summary;
    private String status; //either in progress, done or idle
    private String description;
    private int priority; //high = 3, medium = 2,low 1
    private String issue_type ;//either a task or a bug
    private @ServerTimestamp Date time_reported;
    private String reporter;
    private String assignee;
    private String issue_id;
    private String project_id;

    protected Issue(Parcel in) {
        summary = in.readString();
        status = in.readString();
        description = in.readString();
        priority = in.readInt();
        issue_type = in.readString();
        reporter = in.readString();
        assignee = in.readString();
        issue_id = in.readString();
        project_id = in.readString();
        time_reported = (Date)in.readSerializable();
    }

    public Issue() {
    }
    public static int getPriorityInteger(String selectedPriority){
        if(selectedPriority == HIGH){
            return 3;
        }else if(selectedPriority == MEDIUM){

            return 2;
        }
        else {
            return 1;
        }
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getIssue_type() {
        return issue_type;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "summary='" + summary + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", issue_type='" + issue_type + '\'' +
                ", time_reported=" + " " +
                ", reporter='" + reporter + '\'' +
                ", assignee='" + " " + '\'' +
                ", issue_id='" + issue_id + '\'' +
                ", project_id='" + project_id + '\'' +
                '}';
    }

    public void setIssue_type(String issue_type) {
        this.issue_type = issue_type;
    }

    public Date getTime_reported() {
        return time_reported;
    }

    public void setTime_reported(Date time_reported) {
        this.time_reported = time_reported;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(String issue_id) {
        this.issue_id = issue_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getPriorityString(){
        if(priority == 1){
            return LOW;
        }else if(priority == 2){
            return MEDIUM;
        }
        else {
            return HIGH;
        }
    }

    public static final Creator<Issue> CREATOR = new Creator<Issue>() {
        @Override
        public Issue createFromParcel(Parcel in) {
            return new Issue(in);
        }

        @Override
        public Issue[] newArray(int size) {
            return new Issue[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(summary);
        parcel.writeString(status);
        parcel.writeString(description);
        parcel.writeInt(priority);
        parcel.writeString(issue_type);
        parcel.writeString(reporter);
        parcel.writeString(assignee);
        parcel.writeString(issue_id);
        parcel.writeString(project_id);
        parcel.writeSerializable(time_reported);
    }
}

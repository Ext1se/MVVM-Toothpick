package com.elegion.test.behancer.data.model.project;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(primaryKeys = {"id", "project_id"},
        foreignKeys = @ForeignKey(
        entity = Project.class,
        parentColumns = "id",
        childColumns = "project_id",
        onUpdate = CASCADE,
        onDelete = CASCADE
))
public class Owner implements Serializable {

    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "username")
    @SerializedName("username")
    private String mUsername;

    @ColumnInfo(name = "project_id")
    private int mProjectId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(@NonNull String username) {
        mUsername = username;
    }

    public int getProjectId() {
        return mProjectId;
    }

    public void setProjectId(int projectId) {
        mProjectId = projectId;
    }
}

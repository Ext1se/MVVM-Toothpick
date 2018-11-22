package com.elegion.test.behancer.data.model.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Image {

    @PrimaryKey
    @ColumnInfo(name = "image_id")
    private int mId;

    @ColumnInfo(name = "photo_url")
    @SerializedName("230")
    private String mPhotoUrl;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(@Nullable String photoUrl) {
        mPhotoUrl = photoUrl;
    }
}

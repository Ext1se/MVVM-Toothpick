package com.elegion.test.behancer.data.model.project;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/*
    Для связи М:М. Более правильная идея.
    Пока таблица Owner выполняет эту функцию. Прописал в ней составной primary key, чтобы данные нормально вытягивались из БД.
 */
@Entity(foreignKeys = {
        @ForeignKey(entity = Project.class, parentColumns = "id", childColumns = "project_id", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = Owner.class, parentColumns = "id", childColumns = "owner_id", onDelete = CASCADE, onUpdate = CASCADE)
})
public class OwnerProject {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "owner_id")
    private int mOwnerId;

    @ColumnInfo(name = "project_id")
    private int mProjectId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(int ownerId) {
        mOwnerId = ownerId;
    }

    public int getProjectId() {
        return mProjectId;
    }

    public void setProjectId(int projectId) {
        mProjectId = projectId;
    }
}

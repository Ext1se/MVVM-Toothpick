package com.elegion.test.behancer.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.elegion.test.behancer.data.model.project.Cover;
import com.elegion.test.behancer.data.model.project.Owner;
import com.elegion.test.behancer.data.model.project.Project;
import com.elegion.test.behancer.data.model.project.RichProject;
import com.elegion.test.behancer.data.model.user.Image;
import com.elegion.test.behancer.data.model.user.User;

import java.util.List;

@Dao
public interface BehanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProjects(List<Project> projects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCovers(List<Cover> covers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOwners(List<Owner> owners);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("select * from project")
    List<Project> getProjects();

    @Query("select * from project order by published_on desc")
    LiveData<List<RichProject>> getProjectsLive();

    @Query("select * from project order by published_on desc")
    DataSource.Factory<Integer, RichProject> getProjectsPaged();

    @Query("select * from project, owner " +
            "where owner.username like :username and owner.project_id = project.id " +
            "order by project.published_on desc")
    DataSource.Factory<Integer, RichProject> getProjectsPagedByUsername(String username);

    @Query("select * from owner where project_id = :projectId")
    List<Owner> getOwnersFromProject(int projectId);

    @Query("select * from user where username = :userName")
    User getUserByName(String userName);

    @Query("select * from user where username = :userName")
    LiveData<User> getUserLiveByName(String userName);

    @Query("delete from project")
    void clearProjectTable();

    @Query("delete from owner")
    void clearOwnerTable();

    @Query("delete from cover")
    void clearCoverTable();

    @Query("select * from user")
    List<User> getUsers();

}

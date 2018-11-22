package com.elegion.test.behancer;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.Build;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.database.BehanceDatabase;
import com.elegion.test.behancer.helper_picasso.CustomOkHttp3Downloader;
import com.elegion.test.behancer.helper_picasso.CustomOkHttpClient;
import com.elegion.test.behancer.helper_picasso.PicassoHelper;
import com.squareup.picasso.Picasso;

public class AppDelegate extends Application {

    private Storage mStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        createDatabase();
        PicassoHelper.createPicasso(this);
    }

    public Storage getStorage() {
        return mStorage;
    }

    private void createDatabase()
    {
        BehanceDatabase database = Room.databaseBuilder(this, BehanceDatabase.class, "behance_database")
                .fallbackToDestructiveMigration()
                .build();

        mStorage = new Storage(database.getBehanceDao());
    }
}

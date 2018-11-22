package com.elegion.test.behancer.utils;

import android.arch.paging.PagedList;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.data.model.project.Project;
import com.elegion.test.behancer.data.model.project.RichProject;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomBindingAdapter {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String urlImage) {
        Picasso.get().load(urlImage).fit().into(imageView);
    }

    /*
      todo: Восстановление позиции RecyclerView
      Если оставить код без изменения, то позиция не восстанавливается после поворота.
      + Неоткуда брать данные для восстановления, так как adapter и layoutManager постоянно пересоздаются.
   */
    @BindingAdapter({"bind:data", "bind:clickHandler"})
    public static void configureRecyclerView(RecyclerView recyclerView, PagedList<RichProject> projects, ProjectsAdapter.OnItemClickListener listener) {
        ProjectsAdapter adapter = new ProjectsAdapter(listener);
        adapter.submitList(projects);
        recyclerView.setAdapter(adapter);
    }

    /*
      На данный момент состояние восстанавливается с помощью Parcelable, который мы получаем из layoutManager,
      сохраняем в Bundle в методе onSaveInstanceState у фрагмента, затем считываем.
      Нелучшее решение, но пока так...
   */
    @BindingAdapter("bind:layoutState")
    public static void restoreRecyclerView(RecyclerView recyclerView, Parcelable state) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        if (state != null) {
            layoutManager.onRestoreInstanceState(state);
        }
        recyclerView.setLayoutManager(layoutManager);
    }

    @BindingAdapter({"bind:refreshState", "bind:onRefresh"})
    public static void configureSwipeRefreshLayout(SwipeRefreshLayout layout, boolean isLoading, SwipeRefreshLayout.OnRefreshListener listener) {
        layout.setOnRefreshListener(listener);
        layout.post(() -> layout.setRefreshing(isLoading));
    }

    /*
       Проблема с векторами для методов drawableLeft, drawableRight ... для версий меньше Леденца
       Получаем id ресурса и аккуратно преобразуем.
    */
    @BindingAdapter("bind:drawable")
    public static void setDrawable(AppCompatButton button, @IdRes int idDrawable)
    {
        Context context = button.getContext();
        Drawable icon;
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            icon = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_folder, context.getTheme());
        } else {
            icon = context.getResources().getDrawable(R.drawable.ic_folder);
        }
        button.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
        button.setCompoundDrawablePadding(12);
    }
}

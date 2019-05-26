package com.example.clientforbehance.toothpick;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.ui.projects.ProjectsAdapter;
import com.example.clientforbehance.ui.projects.ProjectsViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import toothpick.Toothpick;


public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;


    public ViewModelFactory () {
    }

//    @NonNull
//    @Override
//    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        return new ProjectsViewModel();
//    }
}

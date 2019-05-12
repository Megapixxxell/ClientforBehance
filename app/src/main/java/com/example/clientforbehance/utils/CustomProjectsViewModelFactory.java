package com.example.clientforbehance.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.ui.projects.ProjectsAdapter;
import com.example.clientforbehance.ui.projects.ProjectsViewModel;

public class CustomProjectsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Storage mStorage;
    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;

    public CustomProjectsViewModelFactory(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        mStorage = storage;
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProjectsViewModel(mStorage, mOnItemClickListener);
    }
}

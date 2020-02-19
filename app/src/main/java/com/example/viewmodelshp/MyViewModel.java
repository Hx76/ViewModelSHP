package com.example.viewmodelshp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {
    public SavedStateHandle handle;
    String key = getApplication().getResources().getString(R.string.data_key);

    public LiveData<Integer> getNumber() {
        return handle.getLiveData(key);
    }

    public MyViewModel(@NonNull Application application,SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if (!handle.contains(key)){
            load();
        }
    }

    void load(){
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("MY_DATA",Context.MODE_PRIVATE);
        int x = sharedPreferences.getInt(key,0);
        handle.set(key,x);
    }

    void save(){
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("MY_DATA",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,getNumber().getValue());
        editor.apply();
    }

    public void add(int n){
        handle.set(key,getNumber().getValue() + n);
    }
}

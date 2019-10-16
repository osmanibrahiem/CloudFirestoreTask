package com.osman.cloudfirestoretask.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.osman.cloudfirestoretask.model.Car;
import com.osman.cloudfirestoretask.model.NetworkState;
import com.osman.cloudfirestoretask.repository.Repository;

import java.util.List;

public class CarsViewModel extends AndroidViewModel {

    private Repository repository;

    public CarsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void loadCars() {
        repository.loadDataList();
    }

    public LiveData<List<Car>> getCarsList() {
        return repository.getList();
    }

    public LiveData<NetworkState> getNetworkState() {
        return repository.getNetworkState();
    }

    public void addNewCar() {
        repository.addData();
    }
}

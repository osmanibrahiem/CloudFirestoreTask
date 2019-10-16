package com.osman.cloudfirestoretask.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.osman.cloudfirestoretask.R;
import com.osman.cloudfirestoretask.helper.CarUtil;
import com.osman.cloudfirestoretask.helper.Constants;
import com.osman.cloudfirestoretask.helper.Utils;
import com.osman.cloudfirestoretask.model.Car;
import com.osman.cloudfirestoretask.model.NetworkState;

import java.util.List;

public class Repository {

    private static final String TAG = "NetWorkLogging";

    private MutableLiveData<List<Car>> dataList;
    private MutableLiveData<NetworkState> networkState;

    private Context context;


    public Repository(Context context) {
        this.context = context;
        dataList = new MutableLiveData<>();
        networkState = new MutableLiveData<>();
    }

    public void addData() {
        if (Utils.isNetworkAvailable(context)) {
            FirebaseFirestore.getInstance()
                    .collection(Constants.COLLECTION_CARS)
                    .add(CarUtil.getRandomCar(context))
                    .addOnSuccessListener(documentReference -> loadDataList());
        }
    }

    public void loadDataList() {
        if (Utils.isNetworkAvailable(context)) {
            networkState.setValue(NetworkState.LOADING);
            FirebaseFirestore.getInstance().collection(Constants.COLLECTION_CARS).get()
                    .addOnSuccessListener(snapshot -> {
                        if (snapshot.isEmpty()) {
                            dataList.setValue(null);
                            networkState.setValue(new NetworkState(NetworkState.Status.FAILED, context.getString(R.string.empty_list)));
                        } else {
                            dataList.setValue(snapshot.toObjects(Car.class));
                            networkState.setValue(NetworkState.LOADED);
                        }
                    })
                    .addOnFailureListener(e -> {
                        dataList.setValue(null);
                        networkState.setValue(new NetworkState(NetworkState.Status.FAILED, context.getString(R.string.message_no_internet)));
                    });
        } else {
            dataList.setValue(null);
            networkState.setValue(new NetworkState(NetworkState.Status.FAILED, context.getString(R.string.message_no_internet)));
        }
    }

    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public MutableLiveData<List<Car>> getList() {
        return dataList;
    }
}

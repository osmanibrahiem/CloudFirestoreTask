package com.osman.cloudfirestoretask.ui.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osman.cloudfirestoretask.R;
import com.osman.cloudfirestoretask.helper.RecyclerDividerItemDecoration;
import com.osman.cloudfirestoretask.helper.Utils;
import com.osman.cloudfirestoretask.model.Car;
import com.osman.cloudfirestoretask.model.NetworkState;
import com.osman.cloudfirestoretask.ui.adapter.CarsAdapter;
import com.osman.cloudfirestoretask.ui.viewmodel.CarsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.empty)
    AppCompatTextView emptyMsg;

    protected CarsViewModel viewModel;
    private CarsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
        initActions();
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        viewModel = ViewModelProviders.of(this).get(CarsViewModel.class);
        viewModel.loadCars();
    }

    private void initActions() {
        adapter = new CarsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickLister(this::onCarClicked);
        observersRegisters();
    }

    private void observersRegisters() {
        viewModel.getNetworkState().observe(this, networkState -> {
            if (networkState.getStatus() == NetworkState.Status.RUNNING) {
                Utils.showLoadingDialog(this);
            } else if (networkState.getStatus() == NetworkState.Status.FAILED) {
                Utils.hideLoadingDialog();
                recyclerView.setVisibility(View.GONE);
                emptyMsg.setText(networkState.getMsg());
                emptyMsg.setVisibility(View.VISIBLE);
            } else {
                Utils.hideLoadingDialog();
            }
        });

        viewModel.getCarsList().observe(this, cars -> {
            if (cars != null)
                adapter.setData(cars);
//            else adapter.clearList();
        });

    }

    private void onCarClicked(Car car) {
        Toast.makeText(this, "you clicked on : " + car.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            addItem();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addItem() {
        viewModel.addNewCar();
    }
}

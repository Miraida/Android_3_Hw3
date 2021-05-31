package com.geek.android3_3.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.geek.android3_3.R;
import com.geek.android3_3.data.model.Publish;
import com.geek.android3_3.data.storage.PublishStorage;
import com.geek.android3_3.databinding.FragmentHomeBinding;
import com.geek.android3_3.ui.base.BaseFragment;
import com.geek.android3_3.ui.description.DescriptionFragment;
import com.geek.android3_3.ui.form.FormFragment;
import com.geek.android3_3.utils.CustomAlertDialog;
import com.geek.android3_3.utils.Keys;

import java.util.List;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private HomeAdapter adapter;
    private final PublishStorage storage = new PublishStorage();

    @Override
    public ViewBinding binding() {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        getData();
        setClickListeners();
    }

    private void setClickListeners() {
        adapter.setItemClickListener(this::openDescription);
        adapter.setOnLongItemClickListener(id -> CustomAlertDialog.showDialog(requireContext(), () -> storage.deletePublish(id, o -> Toast.makeText(requireContext(), o.toString(), Toast.LENGTH_SHORT).show())));
        ui.addBtn.setOnClickListener(v -> openForm());
        ui.mySwipeRefreshLayout.setOnRefreshListener(() -> {
            getData();
            ui.mySwipeRefreshLayout.setRefreshing(false);
        });
    }

    private void openDescription(Integer id) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Keys.getPublishKey(), id);
        fragment.setArguments(bundle);
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void initViews() {
        RecyclerView recyclerView = ui.recycler;
        adapter = new HomeAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        storage.getPublishList(this::setData);
    }

    private void setData(List<Publish> publishes) {
        ui.progressBar.setVisibility(View.GONE);
        adapter.updateList(publishes);
    }


    private void openForm() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new FormFragment())
                .addToBackStack(null)
                .commit();

    }

}
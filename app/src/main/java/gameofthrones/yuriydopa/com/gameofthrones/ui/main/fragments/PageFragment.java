package gameofthrones.yuriydopa.com.gameofthrones.ui.main.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gameofthrones.yuriydopa.com.gameofthrones.R;
import gameofthrones.yuriydopa.com.gameofthrones.data.managers.DataManager;
import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.CharacterEntity;
import gameofthrones.yuriydopa.com.gameofthrones.ui.adapters.CharactersPageAdapter;
import gameofthrones.yuriydopa.com.gameofthrones.ui.characterprofile.CharacterDetailsActivity;
import gameofthrones.yuriydopa.com.gameofthrones.utils.ConstsManager;
import gameofthrones.yuriydopa.com.gameofthrones.utils.SimpleDividerItemDecoration;

import static gameofthrones.yuriydopa.com.gameofthrones.ui.characterprofile.CharacterDetailsActivity.KEY_MEMBER_DETAILS_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment implements IPageView {

    private static final String TAG = "NewsPageFragment";
    public static final String KEY_HOUSE_ID = "KEY_HOUSE_ID";

    CharactersPageAdapter mAdapter;
    private PagePresenter mPresenter;
    private String mHouseId;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.contentView) SwipeRefreshLayout contentView;

    private Unbinder unbinder;

    public PageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mPresenter = PagePresenter.getInstance();
        mPresenter.takeView(this);
        mHouseId = getArguments().getString(ConstsManager.KEY_HOUSE_ID);
        mAdapter = new CharactersPageAdapter(DataManager.getInstance().getmPicasso());
        mAdapter.setOnItemClickListener(new CharactersPageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mPresenter.clickByItemListView(mAdapter.getData().get(position).getId());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, inflater.toString() + container + savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, view.toString() + savedInstanceState);
        loadData();
        contentView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    public void loadData() {
        Log.d(TAG, "loadData()");
        mPresenter.loadDataByHouse(mHouseId);
    }


    public void setData(List<CharacterEntity> data) {
        Log.d(TAG, "setData(" + data + ")");
        mAdapter.setData(data);
    }

    @Override
    public void showContent() {
        Log.d(TAG, "showContent()");
        contentView.post(new Runnable() {
            @Override
            public void run() {
                contentView.setRefreshing(false);
            }
        });
    }

    @Override
    public void navigateToProfileActivity(long id) {
        Log.d(TAG, "navigateToMemberDetailActivity(" + id + ")");
        Intent i = new Intent(getContext(), CharacterDetailsActivity.class);
        i.putExtra(KEY_MEMBER_DETAILS_ID, id);
        startActivity(i);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView()");
        unbinder.unbind();
    }
}

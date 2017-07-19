package com.bojonegorotic.amrizalns.botic.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.botic.coreapps.callbacks.PageCallback;
import com.botic.coreapps.models.ObjectItem;
import com.botic.coreapps.networks.ApiService;
import com.botic.coreapps.networks.RetrofitApi;
import com.botic.coreapps.responses.BaseResponse;
import com.bojonegorotic.amrizalns.botic.R;
import com.bojonegorotic.amrizalns.botic.activity.signIn;
import com.bojonegorotic.amrizalns.botic.recyclerViewAdapter;
import com.bojonegorotic.amrizalns.botic.utils.Constants;
import com.bojonegorotic.amrizalns.botic.utils.SessionLogin;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by amrizalns on 7/5/17.
 */

public class item_content extends Fragment {
    private GridLayoutManager lLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    View view;
    private recyclerViewAdapter rcAdapter;
    private List<ObjectItem> rowListItem = new ArrayList<>();
    ProgressDialog dialog;
    private TextView tvError;
    private RecyclerView rView;
    private int idCategory;
    private String objectType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_content, null);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        tvError = (TextView) view.findViewById(R.id.tv_error);
        lLayout = new GridLayoutManager(view.getContext(), 3);
        rView = (RecyclerView) view.findViewById(R.id.rv_itemcontent);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        rcAdapter = new recyclerViewAdapter(view.getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
        idCategory = getArguments().getInt(Constants.TAG_ID_CATEGORY);
        objectType = getArguments().getString(Constants.TAG_OBJECT_TYPE);
        getByCategory(idCategory, objectType);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getByCategory(idCategory, objectType);
    }

    private void getByCategory(int idCategory, String objectType) {
        ApiService service = RetrofitApi.getInstance(getActivity()).getApiService(SessionLogin.getAccessToken());
        Call<BaseResponse<List<ObjectItem>>> request = null;
        switch (objectType) {
            case Constants.TAG_KULINER:
                request = service.getCulinaryByCategory(idCategory);
                break;
            case Constants.TAG_PRAYING:
                request = service.getPrayingByCategory(idCategory);
                break;
            case Constants.TAG_SHOPPING:
                request = service.getShoppingByCategory(idCategory);
                break;
            case Constants.TAG_TRANSPORTASI:
                request = service.getTransportationByCategory(idCategory);
                break;
            case Constants.TAG_PUBLIC_SERVICE:
                request = service.getPublicServiceByCategory(idCategory);
                break;
            case Constants.TAG_FINANCE:
                request = service.getFinanceByCategory(idCategory);
                break;
            case Constants.TAG_HEALTH:
                request = service.getHealthByCategory(idCategory);
                break;
            case Constants.TAG_LEISURE:
                request = service.getLeisureByCategory(idCategory);
                break;
        }
        if (request != null)
            request.enqueue(new PageCallback<List<ObjectItem>>(getActivity()) {
                @Override
                protected void onFinish() {
                    if (rowListItem.size() == 0) {
                        tvError.setVisibility(View.VISIBLE);
                    } else {
                        tvError.setVisibility(View.GONE);
                        rView.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                protected void onStart() {
                    rView.setVisibility(View.GONE);
                }

                @Override
                protected void onSuccess(List<ObjectItem> data) {
                    super.onSuccess(data);
                    rowListItem.clear();
                    rowListItem.addAll(data);
                    rcAdapter.notifyDataSetChanged();
                }

                @Override
                protected void onUnauthorized() {
                    SessionLogin.reset();
                    Intent intent = new Intent(getActivity(), signIn.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                    getActivity().startActivity(intent);
                }
            });
    }
}

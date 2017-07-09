package com.example.amrizalns.botic.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.itemObject;
import com.example.amrizalns.botic.recyclerViewAdapter;
import com.example.amrizalns.botic.utils.Constants;
import com.example.amrizalns.botic.utils.SessionLogin;

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
    private List<itemObject> rowListItem = new ArrayList<>();
    ProgressDialog dialog;
    private TextView tvError;
    private RecyclerView rView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_content, null);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        tvError = (TextView) view.findViewById(R.id.tv_error);
        lLayout = new GridLayoutManager(view.getContext(), 2);
        rView = (RecyclerView) view.findViewById(R.id.rv_itemcontent);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        rcAdapter = new recyclerViewAdapter(view.getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
        int idCategory = getArguments().getInt(Constants.TAG_ID_CATEGORY);
        String objectType = getArguments().getString(Constants.TAG_OBJECT_TYPE);
        getByCategory(idCategory, objectType);
        return view;
    }

    private void getByCategory(int idCategory, String objectType) {
        ApiService service = RetrofitApi.getInstance().getApiService(SessionLogin.getAccessToken());
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
                    for (ObjectItem objectItem : data) {
                        rowListItem.add(new itemObject(R.mipmap.ic_botic,
                                objectItem.getName(),
                                objectItem.getAddress(),
                                objectItem.getPrice(),
                                objectItem.getOpen(),
                                objectItem.getClose(),
                                objectItem.getDescription()));
                    }
                    rcAdapter.notifyDataSetChanged();
                }
            });
    }

}

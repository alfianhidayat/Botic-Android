package com.bojonegorotic.amrizalns.botic.helper;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.bojonegorotic.amrizalns.botic.model.ObjectItem;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Book.class
    public void clearAll() {
        realm.beginTransaction();
        realm.clear(ObjectItem.class);
        realm.commitTransaction();
    }

    public void remove(final int id, final int idMenu) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ObjectItem> rows = realm.where(ObjectItem.class).equalTo("idObject", id).equalTo("idMenu", idMenu).findAll();
                rows.clear();
            }
        });
    }

    //find all objects in the Book.class
    public RealmResults<ObjectItem> getObjects() {

        return realm.where(ObjectItem.class).findAll();
    }

    //query a single item with the given id
    public ObjectItem getObject(int id, int idMenu) {
        return realm.where(ObjectItem.class).equalTo("idObject", id).equalTo("idMenu", idMenu).findFirst();
    }

    //check if Book.class is empty
    public boolean hasObject() {
        return !realm.allObjects(ObjectItem.class).isEmpty();
    }
}
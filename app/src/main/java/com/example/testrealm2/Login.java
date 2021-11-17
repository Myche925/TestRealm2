package com.example.testrealm2;


import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.RealmClass;

public class Login extends RealmObject {
    public String L_ID;
    public String L_PW;
    @LinkingObjects("login")
    private final RealmResults<Person> owners = null;

    public String getL_ID() {
        return L_ID;
    }
    public void setL_ID(String l_id) {
        this.L_ID = l_id;
    }

    public String getL_PW() {
        return L_PW;
    }
    public void setL_PW(String l_pw) { this.L_PW = l_pw;    }

}

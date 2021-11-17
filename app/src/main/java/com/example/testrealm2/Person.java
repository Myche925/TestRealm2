package com.example.testrealm2;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class Person extends RealmObject {
    @Index
    private String name;
    private int age;
    private boolean Graduation;
    @PrimaryKey
    private int id;
  //  public Login login;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public boolean getGraduation() {return Graduation;}
    public void setGraduation(boolean Graduation) {this.Graduation = Graduation;}

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Login getLogin(){return login;}
    public void setLogin(Login login) {this.login = login;}

}



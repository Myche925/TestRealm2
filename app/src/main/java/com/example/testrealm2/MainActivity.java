package com.example.testrealm2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Arrays;


import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.OrderedRealmCollectionSnapshot;
import io.realm.Realm;
import io.realm.RealmResults;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import io.realm.Sort;
import com.example.testrealm2.Person;
import com.example.testrealm2.Login;

public class MainActivity extends Activity {

    public static final String TAG = "IntroExampleActivity";
    private LinearLayout rootLayout;
    private Realm realm;
    private RealmResults<Person> persons;
    EditText edtName, edtNumber;
    Button btnInit, btnInsert, btnSelect;

    private final OrderedRealmCollectionChangeListener<RealmResults<Person>> realmChangeListener = (people, changeSet) -> {
        String insertions = changeSet.getInsertions().length == 0 ? "" : "\n - 입력: " + Arrays.toString(changeSet.getInsertions());
        String deletions = changeSet.getDeletions().length == 0 ? "" : "\n - 삭제: " + Arrays.toString(changeSet.getDeletions());
        String changes = changeSet.getChanges().length == 0 ? "" : "\n - 변경: " + Arrays.toString(changeSet.getChanges());
      //  showStatus("쓰는중" + insertions + deletions + changes);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Realm DB테스트");
        rootLayout = findViewById(R.id.container);
        rootLayout.removeAllViews();

        edtName = (EditText) findViewById(R.id.edtName);
        edtNumber = (EditText) findViewById(R.id.edtNumber);

        btnInit = (Button) findViewById(R.id.btnInit);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnSelect = (Button) findViewById(R.id.btnSelect);

      //  Realm.deleteRealm(Realm.getDefaultConfiguration());

        realm = Realm.getDefaultInstance();
        persons = realm.where(Person.class).findAllAsync();
        persons.addChangeListener(realmChangeListener);
         basicCRUD(realm);
        // basicQuery(realm);
        // basicLinkQuery(realm);
        // new ComplexBackgroundOperations(this).execute();

        btnInit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                realm.executeTransaction(r -> r.delete(Person.class));
                Toast.makeText(getApplicationContext(), "삭제",
                        Toast.LENGTH_SHORT).show();
            }

        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                realm.executeTransaction(r -> {
                    Person person = r.createObject(Person.class,
                            realm.where(Person.class).count()+1);
                    person.setName(edtName.getText().toString());
                    person.setAge(Integer.parseInt(edtNumber.getText().toString()));
                });
                Toast.makeText(getApplicationContext(), "입력",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rootLayout.removeAllViews();
                showStatus("이름    나이");
                for(Person person1 : realm.where(Person.class).findAll()){
                    showStatus(person1.getName() +" : "+ person1.getAge()); }
                Toast.makeText(getApplicationContext(), "조회",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persons.removeAllChangeListeners(); // Remove the change listener when no longer needed.
        realm.close(); // Remember to close Realm when done.
    }

    private void showStatus(String text) {
        Log.i(TAG, text);
        TextView textView = new TextView(this);
        textView.setText(text);
        rootLayout.addView(textView);
    }



    private void basicCRUD(Realm realm) {


        showStatus("Realm ::::" + realm.getPath() );


        //RealmResults<Person> results = realm.where(Person.class).equalTo("age", 26).findAll();
        //showStatus("일치하는결과값: " + results.size());
        //RealmResults<Person> results = realm.where(Person.class).findAll();
        //for(Person person1 : realm.where(Person.class).findAll()){
        //showStatus("이름: " + person1.getName() +" 나이: " + person1.getAge()); }
    }
}



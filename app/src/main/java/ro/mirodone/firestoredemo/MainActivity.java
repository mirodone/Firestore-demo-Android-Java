package ro.mirodone.firestoredemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //keys
    public static final String KEY_TITLE = "title";
    public static final String KEY_DETAILS = "details";

    private static final String TAG = "MainActivity";
    List list = new List();
    private EditText enterTitle;
    private EditText enterDetails;
    private Button saveButton, showDataButton, updateButton, deleteButton;
    private TextView recTitle;
    private TextView recDetails;
    //connection to Firestore
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private DocumentReference listRef = database.collection("List")
            .document("Item details");

    private CollectionReference collectionReference = database.collection("List");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        saveButton = findViewById(R.id.btn_save);
        enterTitle = findViewById(R.id.et_title);
        enterDetails = findViewById(R.id.et_details);
        showDataButton = findViewById(R.id.btn_show_data);
        recTitle = findViewById(R.id.received_title);
        recDetails = findViewById(R.id.received_details);
        updateButton = findViewById(R.id.btn_update_data);
        deleteButton = findViewById(R.id.btn_delete_data);

        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);


        showDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getItems();


 /*               listRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    list = documentSnapshot.toObject(List.class);

                                    // String title = documentSnapshot.getString(KEY_TITLE);
                                    //   String details = documentSnapshot.getString(KEY_DETAILS);

                                    if (list != null) {
                                        recTitle.setText(list.getTitle());
                                        recDetails.setText(list.getDetails());
                                    }

                                } else {
                                    Toast.makeText(MainActivity.this, "No data exists", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure" + e.toString());
                            }
                        });
*/
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                addAnotherItem();

/*                final String title = enterTitle.getText().toString().trim();
                String details = enterDetails.getText().toString().trim();


                list.setTitle(title);
                list.setDetails(details);

//                Map<String, Object> data = new HashMap<>();
//                data.put(KEY_TITLE, title);
//                data.put(KEY_DETAILS, details);

                listRef.set(list)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                enterTitle.getText().clear();
                                enterTitle.clearFocus();
                                enterDetails.getText().clear();
                                enterDetails.clearFocus();

                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: " + e.toString());
                            }
                        });*/

            }
        });


    }

    private void addAnotherItem () {

        String title = enterTitle.getText().toString().trim();
        String detail = enterDetails.getText().toString().trim();


        list = new List();
        list.setTitle(title);
        list.setDetails(detail);

        collectionReference.add(list);

    }

    private void getItems(){

        collectionReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots){
                          //  Log.d(TAG, "onSuccess" +snapshots.getString(KEY_DETAILS));
                            Log.d(TAG, "onSuccess" +snapshots.getId());
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_update_data:

                updateMyTitle();
                break;

            case R.id.btn_delete_data:

                deleteMyData();
                break;
        }

    }

    private void deleteMyData() {

        // listRef.update(KEY_TITLE, FieldValue.delete());
        // listRef.update(KEY_DETAILS, FieldValue.delete());

        listRef.delete();


    }

    private void updateMyTitle() {

        String titleUpdate = enterTitle.getText().toString().trim();

        Map<String, Object> data = new HashMap<>();
        data.put(KEY_TITLE, titleUpdate);

        listRef.update(data);

    }
}

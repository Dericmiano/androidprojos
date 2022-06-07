package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText enterText;
    private EditText enterThoughts;
    private Button saveButton;
    private TextView recTitle;
//    private TextView recThought;
    private Button showData, updateTitle, deleteThought;
    //keys
    public static final String KEY_TITLE = "title";
    public static final String KEY_THOUGHT = "thoughts";


    //connecting to firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private DocumentReference journalRef = db.document("journal/First Thoughts");
    //these can also work very well

    private DocumentReference journalRef = db.collection("journal")
            .document("First Thoughts");
    private CollectionReference collectionReference = db.collection("journal");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveButton = findViewById(R.id.saveButton);
        enterText = findViewById(R.id.myeditText);
        enterThoughts = findViewById(R.id.myeditDescription);
//        recThought = findViewById(R.id.rec_description);
        recTitle = findViewById(R.id.rec_title);
        showData = findViewById(R.id.showDataText);
        updateTitle = findViewById(R.id.updateDataText);
        deleteThought = findViewById(R.id.deleteText);

        updateTitle.setOnClickListener(this);
        deleteThought.setOnClickListener(this);
        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getThoughts();
//                journalRef.get()
//                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                            @Override
//                            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                if (documentSnapshot.exists()){
//                                    Journal journal = documentSnapshot.toObject(Journal.class);
//
////                                    String title = documentSnapshot.getString(KEY_TITLE);
////                                    String thought = documentSnapshot.getString(KEY_THOUGHT);
//                                    assert journal != null;
//                                    recTitle.setText(journal.getTitle());
//                                    recThought.setText(journal.getThought());
//
//                                }else{
//                                    Toast.makeText(MainActivity.this, "No data exists", Toast.LENGTH_SHORT).show();
//
//                                }
//
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.d("TAG1", "onFailure: " + e.toString());
//                            }
//                        });

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addThought();
//                String title = enterText.getText().toString().trim();
//                String thoughts = enterThoughts.getText().toString().trim();
//                Journal journal = new Journal();
//                journal.setThought(title);
//                journal.setThought(thoughts);

//                Map<String, Object> data = new HashMap<>();
//                data.put(KEY_TITLE, title);
//                data.put(KEY_THOUGHT, thoughts);

//                journalRef.set(journal)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.d("TAG", "onFailure: "+e.toString());
//
//                            }
//                        });


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        collectionReference.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Log.d("TAG3", "onEvent: "+error.toString());

                }
                String data = "";

                assert queryDocumentSnapshots != null;
                for(QueryDocumentSnapshot  snapshots : queryDocumentSnapshots){

                    Log.d("TAG2", "onSuccess: "+snapshots.getString(KEY_TITLE));
                    Journal journal = snapshots.toObject(Journal.class);
                    data += "Title:" +journal.getTitle() + "\n"
                            +"thought: "+journal.getThought()+"\n\n";
//                    assert journal != null;
//                            recTitle.setText(journal.getThought());
//                            recThought.setText(journal.getThought());
                }
                recTitle.setText(data);


            }
        });
//        journalRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
//                if (error != null){
//                    Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
//                }
//                if (documentSnapshot !=null && documentSnapshot.exists()){
//                    String data = "";
//                    Journal journal = documentSnapshot.toObject(Journal.class);
//
////                                    String title = documentSnapshot.getString(KEY_TITLE);
////                                    String thought = documentSnapshot.getString(KEY_THOUGHT);
//                    data += "Title:" +journal.getTitle() + "\n"
//                            +"thought: "+journal.getThought()+"\n\n";
////                    assert journal != null;
//                    recTitle.setText(data);
////                    recThought.setText(journal.getThought());
//
//                }else{
////                    recThought.setText("");
//                    recTitle.setText("");
//                }
//
//            }
//        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updateDataText:
                //call update
                updateMyTitle();
                break;
                
            case R.id.deleteText:
                deleteAll();
//                deleteMythoughts();

                break;
        }


    }
    private void deleteAll() {
        journalRef.delete();
    }


    private void deleteMythoughts() {
        Map<String, Object>data = new HashMap<>();
        data.put(KEY_THOUGHT, FieldValue.delete());
        journalRef.update(data);
        // also a good way
//        journalRef.update(KEY_THOUGHT,FieldValue.delete());
    }

    private void updateMyTitle() {
        String title = enterText.getText().toString().trim();
        String thought= enterThoughts.getText().toString().trim();
        Map<String, Object> data = new HashMap<>();
        data.put(KEY_TITLE, title);
        data.put(KEY_THOUGHT, thought);
        journalRef.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_LONG).show();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    private void getThoughts(){
        collectionReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";

                        for(QueryDocumentSnapshot  snapshots : queryDocumentSnapshots){

                            Log.d("TAG2", "onSuccess: "+snapshots.getString(KEY_TITLE));
                            Journal journal = snapshots.toObject(Journal.class);
                            data += "Title:" +journal.getTitle() + "\n"
                                    +"thought: "+journal.getThought()+"\n\n";
//                    assert journal != null;
//                            recTitle.setText(journal.getThought());
//                            recThought.setText(journal.getThought());
                        }
                        recTitle.setText(data);


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    private void addThought(){
        String title = enterText.getText().toString().trim();
        String thoughts = enterThoughts.getText().toString().trim();
        Journal journal = new Journal(title, thoughts);
//        journal.setThought(title);
//        journal.setThought(thoughts);

        collectionReference.add(journal);

    }

}
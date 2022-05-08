package hu.kotprog.mobilalkfejlkotprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import hu.kotprog.mobilalkfejlkotprog.Filters.MinMaxFilter;
import hu.kotprog.mobilalkfejlkotprog.Model.Jarat;

public class Insert extends AppCompatActivity {
    FirebaseFirestore db;
    Switch sw;
    EditText nev;
    EditText indul;
    EditText erk;
    EditText perc;
    Notify notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_insert);
        db=FirebaseFirestore.getInstance();
        db.collection("Jaratok");
        sw=findViewById(R.id.unnep);
        nev=findViewById(R.id.Jarat_nev_ins);
        indul=findViewById(R.id.Jarat_indulas_ins);
        erk=findViewById(R.id.Jarat_erekezes_ins);
        perc=findViewById(R.id.perc_ins);
        perc.setFilters(new InputFilter[]{new MinMaxFilter(0,59)});
        notify=new Notify(this);

    }

    public void insert(View view) {
        if(nev.getText().toString().isEmpty()||indul.getText().toString().isEmpty()||erk.getText().toString().isEmpty()||perc.getText().toString().isEmpty()){
            Toast.makeText(this, "Hiba az összesmező kitöltése kötelező", Toast.LENGTH_LONG).show();
        }
        else{
            Jarat jarat=new Jarat(nev.getText().toString(),perc.getText().toString(),sw.isChecked(),indul.getText().toString(),erk.getText().toString());
            db.collection("Jaratok").add(jarat).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                        notify.send("Új járat hozzáadva");
                        finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Insert.this,"Hiba új adat felvétele közben",Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}
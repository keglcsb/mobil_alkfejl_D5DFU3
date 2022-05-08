package hu.kotprog.mobilalkfejlkotprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import hu.kotprog.mobilalkfejlkotprog.Model.Jarat;
import hu.kotprog.mobilalkfejlkotprog.Model.JaratAdapter;

public class Shop extends AppCompatActivity {
    private FirebaseAuth mauth;
    private FirebaseFirestore db;
    private JaratAdapter adapter;
    ArrayList<Jarat> jaratok;
    private RecyclerView mRecyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        mauth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        jaratok=new ArrayList<>();
        mRecyclerview=findViewById(R.id.shop_recview);
        System.out.println(mRecyclerview);
        mRecyclerview.setLayoutManager(new GridLayoutManager(Shop.this,1));
        adapter=new JaratAdapter(Shop.this,jaratok);
        mRecyclerview.setAdapter(adapter);

        getSupportActionBar().setTitle("Jegyfoglalás");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2E7D32")));
        getSupportActionBar().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shop_menu,menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.insert){
            Intent intent =new Intent(this,Insert.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.logout){
            mauth.signOut();
            Intent intent =new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
    public void queryData(){
        jaratok.clear();

        db.collection("Jaratok").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for(QueryDocumentSnapshot document:queryDocumentSnapshots){
                Jarat jarat=document.toObject(Jarat.class);
                jarat.setId(document.getId());
                jaratok.add(jarat);
            }
            adapter.notifyDataSetChanged();
        });
    }
    public void delete(Jarat jarat){
        DocumentReference reference=db.collection("Jaratok").document(jarat._getId());
        reference.delete().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Shop.this,"Hiba nem sikerült törölni a járatot",Toast.LENGTH_LONG).show();
            }
        });
        queryData();
    }

    public void foglal(Jarat jarat) {
        db.collection("Jaratok").document(jarat._getId()).update("foglalasCount",jarat.getFoglalasCount()+1).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Shop.this,"Hiba nem sikerült jegyet foglalni",Toast.LENGTH_LONG);
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Notify notify=new Notify(Shop.this);
                notify.send("Sikeres jegyfoglalas");
                queryData();
            }
        });
    }
}
package hu.kotprog.mobilalkfejlkotprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Regist extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailField;
    EditText pwField;
    EditText pw2Field;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        mAuth=FirebaseAuth.getInstance();
        emailField= findViewById(R.id.email);
        pwField=findViewById(R.id.pw);
        pw2Field=findViewById(R.id.pw2);
        getSupportActionBar().hide();
    }

    public void regist(View view) {
        String email=emailField.getText().toString();
        String pw=pwField.getText().toString();
        String pw2=pw2Field.getText().toString();
        if(!email.isEmpty()&&!pw.isEmpty()&&!pw2.isEmpty()){
            if(!pw.equals(pw2)){
                Toast.makeText(Regist.this,"Hiba: A kétjelszó nem egyezik",Toast.LENGTH_LONG).show();
            }
            else {
                mAuth.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Regist.this,"Sikeres regisztráció",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Regist.this, Login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Regist.this, "Ismeretlen Hiba", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

        }
        else{
            Toast.makeText(Regist.this,"Az összes mező kitöltése kötelező",Toast.LENGTH_LONG).show();
        }
    }
}
package hu.kotprog.mobilalkfejlkotprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import hu.kotprog.mobilalkfejlkotprog.Model.Jarat;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailField;
    EditText pwField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        emailField=findViewById(R.id.login_email);
        pwField=findViewById(R.id.login_pw);
        getSupportActionBar().hide();

    }


    public void login(View view) {
        String email=emailField.getText().toString();
        String pw=pwField.getText().toString();
        mAuth.signInWithEmailAndPassword(email,pw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Login.this,"Sikeres bejelentkezés",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Login.this,Shop.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this,"Hiba bejelentkezés közben ellenőrizd az adataidat",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
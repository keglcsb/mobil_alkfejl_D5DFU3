package hu.kotprog.mobilalkfejlkotprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void login(View view) {
        Intent intent=new Intent(this,hu.kotprog.mobilalkfejlkotprog.Login.class);
        startActivity(intent);
    }

    public void regist(View view) {
        Intent intent=new Intent(this,hu.kotprog.mobilalkfejlkotprog.Regist.class);
        startActivity(intent);
    }
}
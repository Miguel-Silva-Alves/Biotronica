package no.nordicsemi.android.nrfthingy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private TextView registerText;
    private EditText editEmail, editPassword;
    private Button buttonSignIn;
    private ProgressBar progressBar;
    String[] message = {"Preencha todos os campos!", "Tá indo, uhu!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ResgisterActivity.class);
                startActivity(intent);
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString();
                String pass = editPassword.getText().toString();
                if (email.isEmpty() || pass.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, message[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    SQLiteDatabase bd = openOrCreateDatabase( "biotronica.db", MODE_PRIVATE, null );
                    String cmd;
                    cmd="select id from user where email like '"+email+"' and password like '"+pass+"' ;";
                    Cursor dadosLidos = bd.rawQuery( cmd, null );
                    if(dadosLidos.moveToNext()) {
                        Toast.makeText( getApplicationContext(), "Deu Certo", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText( getApplicationContext(), "Usuário não Cadastrado", Toast.LENGTH_SHORT).show();
                    }
                    Snackbar snackbar = Snackbar.make(view, message[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    // private void ProfileScreen() {
    //    Intent intent = new Intent(LoginActivity.this, ProfileScreen.class);
    //     startActivity(intent);
    //     finish();
    // }

    private void initComponents() {
        registerText = findViewById(R.id.registerText);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        buttonSignIn  = findViewById(R.id.buttonSignIn );
        progressBar  = findViewById(R.id.progressBar);
    }
}
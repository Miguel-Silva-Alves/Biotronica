package no.nordicsemi.android.nrfthingy;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class ResgisterActivity extends AppCompatActivity {

    private EditText editName, editLastName, editPhone, editEmail, editPassword;
    private Button buttonRegister;
    String[] message = {"Preencha todos  os campos!", "Cadastro realizado com sucesso!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister);

        initComponents();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String lastName = editLastName.getText().toString();
                String phone = editPhone.getText().toString();
                String email = editEmail.getText().toString();
                String pass = editPassword.getText().toString();
                if (name.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, message[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    SQLiteDatabase bd = openOrCreateDatabase( "biotronica.db", MODE_PRIVATE, null );
                    //string para comandos SQL
                    String cmd;
                    // criar a tabela usuario, se a mesma não existir
                    cmd = "CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, last_name VARCHAR, phone VARCHAR, email VARCHAR, password VARCHAR)";
                    bd.execSQL( cmd );
                    cmd="insert into user (name, last_name, phone, email, password)  values('"+name+"' , '"+lastName+"' , '"+phone+"' , '"+email+"' , '"+pass+"');";
                    bd.execSQL(cmd);
                    Snackbar snackbar = Snackbar.make(view, message[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void initComponents() {
        editName = findViewById(R.id.editName);
        editLastName = findViewById(R.id.editLastName);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
    }
}
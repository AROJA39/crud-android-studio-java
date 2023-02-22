package com.cdp.Bdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.Bdb.db.DbContactos;
import com.cdp.agenda.R;

import java.util.regex.Pattern;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreoElectronico;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if(txtNombre.length()== 0  ){
                    txtNombre.setError("Nombre es obligatorio");
                    txtNombre.requestFocus();
                }else  if( txtTelefono.length()== 0 ){
                    txtTelefono.setError("Telefono es obligatorio");
                    txtTelefono.requestFocus();
                }else  if(!validarEmail(txtCorreoElectronico.getText().toString())){
                    txtCorreoElectronico.setError("Email no válido");
                }
                if(!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {

                    DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                    long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreoElectronico.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private void limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }
}
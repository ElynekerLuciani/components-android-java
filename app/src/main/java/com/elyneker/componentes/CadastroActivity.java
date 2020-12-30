package com.elyneker.componentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.AutoText;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class CadastroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static int RESULT_ADD = 1;
    public static int RESULT_CANCELAR = 2;

    EditText edtNome;
    RadioGroup radioGroup;
    ToggleButton toggleButton;

    boolean edit;
    int idParticipante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Spinner spinner = findViewById(R.id.spinnerCursos);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Cursos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        edtNome = findViewById(R.id.editextNome);
        radioGroup = findViewById(R.id.rdGrupo);
        toggleButton = findViewById(R.id.toggleButtonConfirmar);

        edit = false;

        //pegando o valor vindo da tela principal
        if (getIntent().getExtras() != null) {
            String nome = (String) getIntent().getExtras().get("nome");
            idParticipante = (int) getIntent().getExtras().get("id");
            //COLOCAR OS OUTROS DADOS AQUI

            edtNome.setText(nome);

            edit = true;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void cancelar(View view) {
        setResult(RESULT_CANCELAR);
        finish();
    }

    public void adicionar(View view) {
        Intent intent = new Intent();

        //selecionando o tipo de participante
        int itemRadioGrouSelecionado = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButtonSelecionado = findViewById(itemRadioGrouSelecionado);
        String tipoParticipante = radioButtonSelecionado.getText().toString();

        String nome = edtNome.getText().toString();
        String categoria = tipoParticipante;

        intent.putExtra("nome", nome);
        intent.putExtra("categoria", categoria);


        if (edit) intent.putExtra("id", idParticipante);

        //aceitar as regras de participação
        if(toggleButton.isChecked()) {
            setResult(RESULT_ADD, intent);
            finish();
        } else {
            Toast.makeText(this, tipoParticipante + ", aceite as regras de participação!", Toast.LENGTH_SHORT).show();
        }


    }
}
package com.elyneker.componentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.AutoText;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class CadastroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static int RESULT_ADD = 1;
    public static int RESULT_CANCELAR = 2;
    private static final String[] CAMPUS = new String[] {
            "Sobral", "Quixadá", "Russas", "Tauá"
    };

    EditText edtNome;
    RadioGroup radioGroup;
    ToggleButton toggleButton;
    RadioButton categ;
    Spinner spinner;

    boolean edit;
    int idParticipante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        AutoCompleteTextView editarText = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapterText = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CAMPUS);
        editarText.setAdapter(adapterText);

        spinner = findViewById(R.id.spinnerCursos);
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
            String categoria = (String) getIntent().getExtras().get("categoria");
            String curso = (String) getIntent().getExtras().get("curso");
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
        String curso = spinner.getSelectedItem().toString();


        intent.putExtra("nome", nome);
        intent.putExtra("categoria", categoria);
        //intent.putExtra("curso", curso);


        if (edit) intent.putExtra("id", idParticipante);

        //aceitar as regras de participação
        if(toggleButton.isChecked()) {
            Toast.makeText(this, curso, Toast.LENGTH_SHORT).show();
            setResult(RESULT_ADD, intent);
            finish();
        } else {
            Toast.makeText(this, tipoParticipante + ", aceite as regras de participação!", Toast.LENGTH_SHORT).show();
        }


    }
}
package com.elyneker.componentes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.provider.Telephony;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static int REQUEST_ADD = 1;
    public static int REQUEST_EDITAR = 2;

    public static int RESULT_ADD = 1;
    public static int RESULT_EDITAR = 2;

    ArrayList<Participante> listaParticipantes;
    ListView listViewContatos;
    ArrayAdapter adapter;
    int selected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaParticipantes = new ArrayList<Participante>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaParticipantes);

        listViewContatos = (ListView) findViewById(R.id.listViewContatos);
        listViewContatos.setAdapter(adapter);
        listViewContatos.setSelector(android.R.color.holo_blue_light);

        //adicionando click na lista de participantes
        listViewContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + listaParticipantes.get(position).toString(), Toast.LENGTH_SHORT).show();
                //posição selecionada na lista de participantes
                selected = position;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ADD && resultCode == RESULT_ADD ) {
            String nome = (String) data.getExtras().get("nome");
            //COLOCAR AQUI AS OUTRAS INFORMAÇÕES CADASTRADAS

            Participante participante = new Participante(nome);

            listaParticipantes.add(participante);
            adapter.notifyDataSetChanged();
        } else if(requestCode == REQUEST_EDITAR && resultCode == RESULT_ADD) {
            String nome = (String) data.getExtras().get("nome");
            //COLOCAR OS OSTROS DADOS AQUI

            int idEditar = (int) data.getExtras().get("id");

            for (Participante participante:listaParticipantes) {
                if (participante.getId() == idEditar) {
                    participante.setNome(nome);
                }
            }

            adapter.notifyDataSetChanged();
        } else if (resultCode == CadastroActivity.RESULT_CANCELAR) {
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.add:
                clicarCadastro();
                break;
            case R.id.delete:
                apagarItemLista();
                break;
            case R.id.edit:
                clicarEditar();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clicarCadastro() {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivityForResult(intent, REQUEST_ADD);
    }

    public void clicarImagens(View view) {
        Intent intent = new Intent(this, ImagensActivity.class);
        startActivity(intent);
    }

    public void clicarPlay(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void clicarEditar() {
        Intent intent = new Intent(this, CadastroActivity.class);
        //passando para a outra tela o participante selecionado
        Participante participante = listaParticipantes.get(selected);
        intent.putExtra("id", participante.getId());
        intent.putExtra("nome", participante.getNome());
        //COLOCAR DEPOIS OS OUTROS DADOS AQUI

        startActivityForResult(intent, REQUEST_EDITAR);
    }

    private void apagarItemLista() {
        if(selected >= 0) {
            listaParticipantes.remove(selected);
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(this, "Selecione um participante", Toast.LENGTH_SHORT).show();
        }

    }

}
package com.elyneker.componentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImagensActivity extends AppCompatActivity {

    GridView gridView;
    String[] nomes = {"Imagem1", "Imagem2", "Imagem3", "Imagem4", "Imagem5"};
    int[] imagens = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagens);

        gridView = findViewById(R.id.gridView);
        CustomAdapter customAdapter = new CustomAdapter(nomes, imagens,this);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectNome = nomes[position];
                int selectedImage = imagens[position];

                startActivity(new Intent(ImagensActivity.this, CliqueActivity.class).putExtra("nome", selectNome).putExtra("imagens", selectedImage));
            }
        });
    }

}
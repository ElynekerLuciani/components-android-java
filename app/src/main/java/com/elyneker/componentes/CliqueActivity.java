package com.elyneker.componentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CliqueActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clique);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textNome);

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            String selectedNome = intent.getStringExtra("nome");
            int selectedImage = intent.getIntExtra("imagens", 0);

            textView.setText(selectedNome);
            imageView.setImageResource(selectedImage);
        }
    }
}
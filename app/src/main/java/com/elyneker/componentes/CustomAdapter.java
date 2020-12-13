package com.elyneker.componentes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    private String[] imagemNome;
    private int[] imagensFoto;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(String[] imagemNome, int[] imagens, Context context) {
        this.imagemNome = imagemNome;
        this.imagensFoto = imagens;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imagensFoto.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_items, parent, false);
        }
        TextView textNome = convertView.findViewById(R.id.textNome);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        textNome.setText(imagemNome[position]);
        imageView.setImageResource(imagensFoto[position]);




        return convertView;
    }
}

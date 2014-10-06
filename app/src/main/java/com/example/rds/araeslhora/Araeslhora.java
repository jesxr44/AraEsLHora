package com.example.rds.araeslhora;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;


public class Araeslhora extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_araeslhora);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public void araEsLHora(View v)
    {
        Button araButton = (Button)findViewById(R.id.araButton);
        Button tornarButton = (Button)findViewById(R.id.tornarButton);

        TextView resposta = (TextView)findViewById(R.id.resposta);
        TextView frase = (TextView)findViewById(R.id.frase);

        resposta.setVisibility(View.VISIBLE);
        resposta.setText(getResposta());

        frase.setText(getFrase());
        frase.setVisibility(View.VISIBLE);

        tornarButton.setVisibility(View.VISIBLE);
        araButton.setVisibility(View.GONE);
    }

    private String getResposta() {
        return (esElDia() == true) ? FrasesRandom.fraseSi : FrasesRandom.fraseNo;
    }

    private String getFrase() {
        //pequeña modificación para que la frase random sea la fraseCasi
        //return (esElDia() == true) ? "" : getFraseRandom();

        String fraseRetornar;

        if(esElDia() == true){
            fraseRetornar = "";
        }else{
                fraseRetornar = (esLaHora() == true) ? FrasesRandom.fraseCasi : getFraseRandom();
        }
        return fraseRetornar;


    }

    private Boolean esElDia()
    {
        Calendar c1 = new GregorianCalendar();
        c1.set(Calendar.DAY_OF_MONTH, 9); //anything 0 - 23
        c1.set(Calendar.MONTH, Calendar.NOVEMBER);
        c1.set(Calendar.YEAR, 2014);

        Calendar c2 = new GregorianCalendar();
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);

        Boolean esElDia = c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) &&
                c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);

        return esElDia;
    }

    private Boolean esLaHora()
    {
        Calendar c1 = new GregorianCalendar();
        c1.set(Calendar.HOUR_OF_DAY, 17);
        c1.set(Calendar.MINUTE, 14);
        c1.set(Calendar.SECOND, 0);

        Calendar c2 = new GregorianCalendar();

        Boolean esLaHora = c1.get(Calendar.HOUR_OF_DAY) == c2.get(Calendar.HOUR_OF_DAY) &&
                c1.get(Calendar.MINUTE) == c2.get(Calendar.MINUTE);

        return esLaHora;
    }

    private String getFraseRandom() {
        Random r = new Random();
        int index = r.nextInt(FrasesRandom.frases.length - 0) + 0;
        return FrasesRandom.frases[index];
    }

    public void tornar(View v)
    {
        Button araButton = (Button)findViewById(R.id.araButton);
        Button tornarButton = (Button)findViewById(R.id.tornarButton);
        TextView resposta = (TextView)findViewById(R.id.resposta);
        TextView frase = (TextView)findViewById(R.id.frase);
        resposta.setVisibility(View.GONE);
        frase.setVisibility(View.GONE);
        tornarButton.setVisibility(View.GONE);
        araButton.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.araeslhora, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId())
        {
            case R.id.action_settings:
                startActivityForResult(new Intent(Araeslhora.this, About.class), 100);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_araeslhora, container, false);
            return rootView;
        }
    }
}

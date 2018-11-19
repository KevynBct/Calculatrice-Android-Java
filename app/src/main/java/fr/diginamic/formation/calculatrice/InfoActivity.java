package fr.diginamic.formation.calculatrice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    public static String EXTRA_HISTO = "text_histo";
    private TextView textHisto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textHisto = findViewById(R.id.text_histo);
        textHisto.setText(getIntent().getStringExtra(InfoActivity.EXTRA_HISTO));

        findViewById(R.id.mail_button).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            //intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Historique des calculs");
            intent.putExtra(Intent.EXTRA_TEXT   , textHisto.getText().toString());

            startActivity(Intent.createChooser(intent, "Send email..."));
        });

    }

}

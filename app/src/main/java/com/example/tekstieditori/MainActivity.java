package com.example.tekstieditori;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    EditText fileName;
    EditText text;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        System.out.println(context.getFilesDir());
    }

    public void readFile(View v) {

        fileName = findViewById(R.id.editTextTextFileName);
        output = findViewById(R.id.textView);
        output.setText("");

        try {
            InputStream ins = context.openFileInput(fileName.getText().toString()); //TODO Tälle arvo!

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";

            while ((s=br.readLine()) != null) {
                output.append(s + "\n");
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOExceotion", "Virhe syötteessä");
        } finally{
            System.out.println("Luettu");
        }
    }

    public void writeFile(View v) {

        try {
            fileName = findViewById(R.id.editTextTextFileName);
            output = findViewById(R.id.textView);
            output.setText("");

            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fileName.getText().toString(),Context.MODE_PRIVATE));

            text = findViewById(R.id.editTextTextMultiLine);

            osw.write(text.getText().toString());
            osw.close();
            output = findViewById(R.id.textView);
            output.setText("Teksti tallennettu tiedostoon: " + fileName.getText());
        } catch (IOException e) {
            Log.e("IOExceotion", "Virhe syötteessä");
        } finally {
            System.out.println("Kirjoitettu");
        }
    }
}
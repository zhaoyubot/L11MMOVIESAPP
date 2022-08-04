package sg.edu.rp.c346.id20011119.mymoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert,btnList;
    EditText etTitle, etGenre,etYear;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert=findViewById(R.id.btnInsert);
        btnList=findViewById(R.id.btnList);
        etGenre =findViewById(R.id.etGenre);
        etTitle=findViewById(R.id.etTitle);
        etYear=findViewById(R.id.etYear);
        spinner=findViewById(R.id.spinner);



        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        Display.class);
                startActivity(i);
            }
        });


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleData = etTitle.getText().toString();
                String genreData = etGenre.getText().toString();
                String yearData = etYear.getText().toString();
                String rate = spinner.getSelectedItem().toString();
                System.out.println("******RATE: " + rate);
                DBHelper db = new DBHelper(MainActivity.this);
                long inserted_id = db.insertMovie(titleData,genreData, yearData, rate);

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                    clear();
                }
            }
        });

    }

    private void clear() {
        etTitle.setText("");
        etGenre.setText("");
        etYear.setText("");
    }
}
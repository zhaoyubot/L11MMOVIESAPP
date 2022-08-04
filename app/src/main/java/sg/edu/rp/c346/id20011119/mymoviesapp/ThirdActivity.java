package sg.edu.rp.c346.id20011119.mymoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    TextView tvID, tvTitle, tvGenre, tvYear, tvRating;
    EditText etID,etTitle, etGenre, etYear;
    Button update, delete, btnReturn;
    Spinner spinner;

    Movie edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvID = findViewById(R.id.tvID);
        tvTitle = findViewById(R.id.tvTitle);
        tvGenre = findViewById(R.id.tvGenre);
        tvYear = findViewById(R.id.tvYear);
        tvRating = findViewById(R.id.tvRating);
        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etName);
        etGenre = findViewById(R.id.etGenre);
        etYear= findViewById(R.id.etYear);
        spinner = findViewById(R.id.spinner);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        btnReturn = findViewById(R.id.btnReturn);


        Intent a = getIntent();
        edit = (Movie) a.getSerializableExtra("edit");

        etID.setText(edit.get_id()+"");
        etTitle.setText(edit.getTitle());
        etGenre.setText(edit.getGenre());
        etYear.setText(edit.getYear()+"");
        if (edit.getRating().equals("G")){
            spinner.setSelection(0);
        } else if (edit.getRating().equals("PG")){
            spinner.setSelection(1);
        } else if (edit.getRating().equals("PG13")){
            spinner.setSelection(2);
        }else if (edit.getRating().equals("NC16")){
            spinner.setSelection(3);
        }else if (edit.getRating().equals("M18")){
            spinner.setSelection(4);
        }else if (edit.getRating().equals("R21")){
            spinner.setSelection(5);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                edit.setTitle(etTitle.getText().toString());
                edit.setGenre(etGenre.getText().toString());
                edit.setYear(Integer.parseInt(etYear.getText().toString()));
                edit.setRating(spinner.getSelectedItem().toString());
                dbh.updateMovie(edit);
                dbh.close();
                Toast.makeText(ThirdActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                clear();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteMovie(edit.get_id());
                Toast.makeText(ThirdActivity.this, "Delete Successful", Toast.LENGTH_SHORT).show();
                clear();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void clear() {

        etTitle.setText("");
        etGenre.setText("");
        etYear.setText("");
    }
}
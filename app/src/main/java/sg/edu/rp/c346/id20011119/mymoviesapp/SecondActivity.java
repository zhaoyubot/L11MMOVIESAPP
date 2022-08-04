package sg.edu.rp.c346.id20011119.mymoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    Button btnShow;
    ArrayList<Movie> al;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle(getTitle().toString() + " ~ " + "Show Movies");

        lv = findViewById(R.id.lv);
        btnShow = findViewById(R.id.btnShow);

        DBHelper dbh = new DBHelper(this);
        al = dbh.getAllMovie();
        dbh.close();

        adapter = new CustomAdapter(SecondActivity.this, R.layout.activity_second, al);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("movie", al);
                startActivity(intent);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper((SecondActivity.this));
                al.clear();
                al.addAll(dbh.getAllMovie());

            }
        });

    }
}
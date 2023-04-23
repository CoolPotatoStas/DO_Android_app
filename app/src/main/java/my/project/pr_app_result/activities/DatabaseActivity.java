package my.project.pr_app_result.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import my.project.pr_app_result.R;

public class DatabaseActivity extends AppCompatActivity {

    SQLiteDatabase mydb;

    final String[] names = {"Василий", "Борис", "Никита", "Евгений", "Мария", "София", "Надежда", "Ева"};
    final int[] ages = {45, 12, 56, 78, 24, 36, 9, 62};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        mydb = getBaseContext().openOrCreateDatabase("myDataBase.db", MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER)");
    }

    public void createUser(View v){

        int randomNum1 = new Random().nextInt(7);
        int randomNum2 = new Random().nextInt(7);

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("myDataBase.db", MODE_PRIVATE, null);
        db.execSQL("INSERT OR IGNORE INTO users VALUES ('" + names[randomNum1] + "', " + ages[randomNum2] + ");");

        Cursor query = db.rawQuery("SELECT * FROM users;", null);
        TextView textView = findViewById(R.id.textViewDataFromDB);
        textView.setText("");
        while(query.moveToNext()){
            String name = query.getString(0);
            int age = query.getInt(1);
            textView.append("Имя: " + name + " Возраст: " + age + "\n");
        }
        query.close();
        db.close();
    }
}
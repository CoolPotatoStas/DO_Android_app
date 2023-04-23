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

    //имена для генерации
    final String[] names = {"Василий", "Борис", "Никита", "Евгений", "Мария", "София", "Надежда", "Ева"};

    //возраст для генерации
    final int[] ages = {45, 12, 56, 78, 24, 36, 9, 62};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        //инициализируем объект бд. Если бд не было, создаем ее, если бд была - мы к ней просто подключаемся
        mydb = getBaseContext().openOrCreateDatabase("myDataBase.db", MODE_PRIVATE, null);
        //если таблички с юзерами нет, мы ее создаем
        mydb.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER)");
        mydb.close();
    }

    //метод-обработчик кнопки "Сгенерировать"
    public void createUser(View v){

        //генерируем позиции в массивах
        int randomNum1 = new Random().nextInt(7);
        int randomNum2 = new Random().nextInt(7);

        //покдлючаемся к бд (создаем, если ее не было)
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("myDataBase.db", MODE_PRIVATE, null);
        //добавляем наши сгенерированные значения
        db.execSQL("INSERT OR IGNORE INTO users VALUES ('" + names[randomNum1] + "', " + ages[randomNum2] + ");");

        //читаем данные таблицы users из бд с помощью объекта cursor
        Cursor query = db.rawQuery("SELECT * FROM users;", null);
        TextView textView = findViewById(R.id.textViewDataFromDB);
        textView.setText("");
        while(query.moveToNext()){
            String name = query.getString(0);
            int age = query.getInt(1);
            textView.append("Имя: " + name + " Возраст: " + age + "\n");
        }
        //закрываем коннекты
        query.close();
        db.close();
    }
}
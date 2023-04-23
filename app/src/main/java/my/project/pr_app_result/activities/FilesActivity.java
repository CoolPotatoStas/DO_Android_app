package my.project.pr_app_result.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import my.project.pr_app_result.R;

//activity для работы с файлом
public class FilesActivity extends AppCompatActivity {

    //название файла, с которым в данном классе будет происходит работа
    private final static String FILE_NAME = "file.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
    }

    //метод-обработчик для кнопки "сохранить"
    public void saveText(View view){
        //создаем ссылку здесь, чтобы в случае чего закрыть поток в блоке finally
        FileOutputStream fos = null;

        //оборачиваем в try, поскольку работа с input/output-стримами требует этого
        try{
            //достаем объект поля ввода
            EditText textBox = (EditText) findViewById(R.id.saving);
            //и достаем из него текст, введенный внутри поля
            String text = textBox.getText().toString();

            //тут инициализируем поток на запись
            fos = openFileOutput(FILE_NAME,MODE_PRIVATE);

            //записываем текст в файл
            fos.write(text.getBytes());
            Toast.makeText(this,"Файл сохранен",Toast.LENGTH_SHORT).show();
        }catch (IOException exception){
            Toast.makeText(this, exception.getMessage(),Toast.LENGTH_SHORT).show();
        }finally {
            try {
                if(fos!=null) fos.close();
            }catch (IOException exception){
                Toast.makeText(this, exception.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }

    //метод-обработчик для кнопки "отобразить"
    public void openText(View view){
        //создаем ссылку тут, чтобы в случае чего закрыть в блоке finally
        FileInputStream fin = null;
        try {
            //достаем объект поля отображения
            TextView textView = (TextView) findViewById(R.id.open_text);

            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            //читаем файл
            fin.read(bytes);

            String text = new String(bytes);
            //преобразуем данные файла из байтов в строку и записываем в поле
            textView.setText(text);
        }catch (IOException exception){
            Toast.makeText(this, exception.getMessage(),Toast.LENGTH_SHORT).show();
        }finally {
            try {
                if(fin!=null) fin.close();
            }catch (IOException exception){
                Toast.makeText(this, exception.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
package my.project.pr_app_result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import my.project.pr_app_result.activities.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //отображение уведомления о запуске приложения
        Toast.makeText(this, "Приложение запущено", Toast.LENGTH_LONG).show();
    }

    public void toFiles(View view){
        Intent intent = new Intent(MainActivity.this, FilesActivity.class);
        startActivity(intent);
    }

    public void toDatabase(View view){
        Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
        startActivity(intent);
    }

    public void toWebView(View v){
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        startActivity(intent);
    }

    public void toListJob(View v){
        Intent intent = new Intent(MainActivity.this, ListJobActivity.class);
        startActivity(intent);
    }
}
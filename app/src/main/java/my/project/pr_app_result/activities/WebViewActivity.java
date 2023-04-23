package my.project.pr_app_result.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import my.project.pr_app_result.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //ищет по id из разметки xml объект webview
        WebView webView = findViewById(R.id.webView);
        //и загружаем ссылку на гитхаб
        webView.loadUrl("https://github.com/CoolPotatoStas");
        //для правдивости выписываем ссылку в консоль
        System.out.println(webView.getOriginalUrl());
    }
}
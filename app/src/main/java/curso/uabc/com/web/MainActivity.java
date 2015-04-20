package curso.uabc.com.web;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TabHost;

import org.apache.http.util.EncodingUtils;


public class MainActivity extends ActionBarActivity {
    //Declaración de WebViews
    private WebView webView;
    private WebView webViewB;
    private WebView webViewC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se obtiene la clase de Recursos de la aplicación

        Resources res = getResources();
        //Se obtiene el componente de TabHost

        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        //Se crea un tab nuevo que el contenido de una URL en especial

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Normal",
                res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);
        //Se crea un tab nuevo que carga una URL con datos post

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Parametros",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        //Se crea un tab nuevo que muestra html interno
        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("HTML-Interno",
                res.getDrawable(android.R.drawable.ic_delete));
        tabs.addTab(spec);

        //posicionar sobre un Tab en especifico
        tabs.setCurrentTab(0);
    }


    @Override
    protected void onStart() {
        super.onStart();
        //Se crea un WebView que carga una URL

        webView= (WebView) findViewById(R.id.webViewA);
        //Se crea un nuevo cliente de vista web, sin esta linea posiblemente abra el navegador por default
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://google.com");

        //Se crea un WebView que carga HTML desde un archivo interno o una cadena de texto
        webViewB= (WebView) findViewById(R.id.webViewB);
        webViewB.loadData("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><title>Hello World</title></head><body><h1>Hello World</h1><p>Jamie was here.</p></body></html>","text/html",null);


        //Se crea un WebView que carga una URL con datos POST

        String url = "https://selfsolve.apple.com/wcResults.do";
        String postData = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
        webViewC= (WebView) findViewById(R.id.webViewC);
        //Se habilita JAVASCRIPT
        webViewC.getSettings().setJavaScriptEnabled(true);
        webViewC.postUrl(url, EncodingUtils.getBytes(postData, "BASE64"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

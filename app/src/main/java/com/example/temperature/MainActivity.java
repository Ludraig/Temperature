package com.example.temperature;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textDate, textCity, textTemp, textDesc;
    ImageView Lomas;
    String myCity="Mississauga";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDate=findViewById(R.id.textDate);
        textCity=findViewById(R.id.textCity);
        textTemp=findViewById(R.id.textTemp);
        textDesc=findViewById(R.id.textDesc);
    }

    public void afficher(){
        String url="http://api.openweathermap.org/data/2.5/weather?q=Mississauga&appid=0104135a347d249765126dc011d0f073&units=metric";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object=response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    Log.d("Tag", "Resultat = " + array.toString());
                    textCity.setText(array.toString());
                } catch (JSONException e){
                    e.printStackTrace();
                }
                }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest)
;    }

}
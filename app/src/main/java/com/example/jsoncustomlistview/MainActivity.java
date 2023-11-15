package com.example.jsoncustomlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jsoncustomlistview.Adapter.QuocGiaAdapter;
import com.example.jsoncustomlistview.Model.Quocgia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    QuocGiaAdapter adapter;
    List<Quocgia> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         countryList = new ArrayList<>();
        listView = findViewById(R.id.listview);
        adapter =new QuocGiaAdapter(MainActivity.this,R.layout.lv_country,countryList);
        new ReadJson().execute("https://ltddsocialnetwork.000webhostapp.com/DongNamA.json");
    }

    private class ReadJson extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("danhsach");

                for (int i =0; i<array.length();i++)
                {
                    JSONObject object1= array.getJSONObject(i);

                    String hinh = object1.getString("tenquocgia");
                    String ten = object1.getString("hinhquocgia");
                    countryList.add(new Quocgia(ten,hinh));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            adapter.notifyDataSetChanged();

            listView.setAdapter(adapter);

        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content.toString();
        }
    }
}
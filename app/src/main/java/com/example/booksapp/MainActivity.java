package com.example.booksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewBooks;
    CustomListAdapter customListAdapter;
    ArrayList<Book> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewBooks = findViewById(R.id.listViewBooks);
        arrayList = new ArrayList<>();
        customListAdapter = new CustomListAdapter(arrayList);
        listViewBooks.setAdapter(customListAdapter);
        new ReadJSON().execute("https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=jdshA9RO4lP7K5O7M15KKt1a5RFPmwAM");
    }
    private class ReadJSON extends AsyncTask<String, Void, String> {
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject resultObject = jsonObject.getJSONObject("results");
                JSONArray bookArray = resultObject.getJSONArray("books");
                for (int i = 0; i < bookArray.length(); i++) {
                    JSONObject bookObject = bookArray.getJSONObject(i);
                    String publisher = bookObject.getString("publisher");
                    String description = bookObject.getString("description");
                    String title = bookObject.getString("title");
                    String author = bookObject.getString("author");
                    String bookImageLink = bookObject.getString("book_image");
                    arrayList.add(new Book(title, author, publisher, description, bookImageLink));
                }
                customListAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class GetImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap;
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }
}
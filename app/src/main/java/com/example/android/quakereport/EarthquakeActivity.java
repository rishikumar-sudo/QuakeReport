
package com.example.android.quakereport;
/*
Code Is Written By  Rishi Kumar  Date :20/12/2019
 */
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static String USGS_REQUEST_URL = "";
    ArrayList<EarthquakeDetails> earthquakes;
    ListView earthquakeListView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        Bundle extras = getIntent().getExtras();
        String startDate = extras.getString("Value1");
        String endDate = extras.getString("Value2");
        System.out.println(startDate+"\n"+endDate);
       // String startDate = "2014-01-01", endDate = "2014-01-02";
        USGS_REQUEST_URL = String.format("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=%s&endtime=%s",startDate, endDate);
        earthquakes = new ArrayList<>();
        /* Create a new {@link ArrayAdapter} of earthquakes */
        earthquakeListView   = (ListView) findViewById(R.id.list);
         new EarthAsyncTask().execute();

    }
    private  class EarthAsyncTask extends AsyncTask<URL,Void,String>
    {


        @Override
        protected String doInBackground(URL... urls) {
            URL url=null;
            try {
                url = new URL(USGS_REQUEST_URL);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
            }
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }


            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            try {

                // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
                // build up a list of Earthquake objects with the corresponding data.
                JSONObject jsonObject=new JSONObject(jsonResponse);
                JSONArray jsonArray=jsonObject.getJSONArray("features");
                for(int i=0;i<4;i++)
                {
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    JSONObject properties=jsonObject1.getJSONObject("properties");
                    String mag=properties.getString("mag");
                    String place=properties.getString("place");
                    String[] pl = place.split("of");
                    long timeInMilliseconds =properties.getLong("time");
                    Date dateObject = new Date(timeInMilliseconds);
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
                    String date=timeFormat.format(dateObject);
                    String dateToDisplay = dateFormatter.format(dateObject);
                    String p=pl[0];
                    String q=pl[1];
                    earthquakes.add(new EarthquakeDetails(p,q,date,dateToDisplay,mag));
                }


            } catch (JSONException e) {

                Log.e("EarthquakeActivity", "Problem parsing the earthquake JSON results", e);
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String jsonResponse) {

            EarthquakeAdapter adapter = new EarthquakeAdapter(EarthquakeActivity.this, earthquakes);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(adapter);
        }

        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                // TODO: Handle the exception
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        private String readFromStream(InputStream inputStream) {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = null;
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (line != null) {
                    output.append(line);
                    try {
                        line = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return output.toString();
        }


    }
}

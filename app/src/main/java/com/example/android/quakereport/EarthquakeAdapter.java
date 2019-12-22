/*

Code Is Written By  Rishi Kumar  Date :20/12/2019

*/
package com.example.android.quakereport;
import android.graphics.drawable.GradientDrawable;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<EarthquakeDetails>  {


    public EarthquakeAdapter(Context context, ArrayList<EarthquakeDetails> earthquakeDetails) {
        super(context, 0, earthquakeDetails);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        EarthquakeDetails currentearthquakeDetails=getItem(position);
        TextView textView1=(TextView)listItemView.findViewById(R.id.textLeft);
        textView1.setText(currentearthquakeDetails.getMag());
        TextView textView2=(TextView)listItemView.findViewById(R.id.location_offset);
        textView2.setText(currentearthquakeDetails.getCityName());
        TextView textView3=(TextView)listItemView.findViewById(R.id.primary_location);
        textView3.setText(currentearthquakeDetails.getPrimary_location());
        TextView textView4=(TextView)listItemView.findViewById(R.id.date);
        textView4.setText(currentearthquakeDetails.getDate());
        TextView textView5=(TextView)listItemView.findViewById(R.id.time);
        textView5.setText(currentearthquakeDetails.getTime());

        textView1.setBackgroundColor(getMagnitudeColor(currentearthquakeDetails.getMag()));
        return listItemView;
    }

    private int getMagnitudeColor(String mag) {
        int magnitudeColorResourceId;
        double magnitude=Double.parseDouble(mag);
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}

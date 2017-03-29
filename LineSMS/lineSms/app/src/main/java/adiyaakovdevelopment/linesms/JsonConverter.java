package adiyaakovdevelopment.linesms;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by androiddevelopment on 3/27/17.
 */

public class JsonConverter {
    private String data;
    private OnDoneParseList onDoneParseList;

    public JsonConverter(String data, OnDoneParseList onDoneParseList){
        this.data = data;
        this.onDoneParseList = onDoneParseList;

    }

    public ArrayList<Invited> convertToInvitedList(){
        ArrayList<Invited> arr = new ArrayList();
        try {
            JSONArray j = new JSONArray(data);


            for(int i = 0 ; i<j.length() ; i++){
                JSONObject obj = j.getJSONObject(i);
                Invited in = new Invited(obj.getString("cName"),obj.getString("cPhone"),obj.getInt("numOfPeople"),obj.getLong("d"));
                arr.add(in);
                Log.d("TAG", "convertToInvitedList:  append new Invited to arrayList");

            }

            onDoneParseList.onObjectReady(arr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arr;


    }

}

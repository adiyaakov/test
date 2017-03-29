package adiyaakovdevelopment.linesms;


import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.google.gson.Gson;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnDoneParseList{
    private Gson g = new Gson();
    private RecyclerView rv;
    private MyRecylerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView)findViewById(R.id.activityMainRv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecylerAdapter();
        rv.setAdapter(adapter);
        retrieveAndConvertJson();


    }
    //parse the arraylist<Invited> to Json Obj
    private String convertListToJson(){

        String json = g.toJson(adapter.getList());
        System.out.println(json);
       return json;
    }

    //save the json that -> from convertListToJson() method in sharedPref
    private void saveJsonList(String json){
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("json",json).commit();
        Log.d("TAG", "saveJsonList: "+ json);

    }

    //convert the json that saved in sharedPref to this.list
    private void retrieveAndConvertJson(){

        String json = PreferenceManager.getDefaultSharedPreferences(this).getString("json", "defaultStringIfNothingFound");
        if (json != null)
        {
            System.out.println("the json is: " + json);
            JsonConverter j = new JsonConverter(json,this);
            j.convertToInvitedList();




        }else {
            System.out.println("null SF");
        }

    }


    @Override
    public void onObjectReady(ArrayList<Invited> list) {
        System.out.println("the list size is:" + list.size());
        //this.list = list;
        adapter.setList(list);
        //adapter.notifyDataSetChanged();
    }

    @Override
    public void onFinishEditDialog(Invited i) {
        adapter.addNewItemTolist(i);


    }

    public void onAddBtnClicked(View view) {

        InvitedDialogFragment fragment = new InvitedDialogFragment();
        fragment.onAttach(this);
        fragment.show(getSupportFragmentManager(),"");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "onPause: called");
        saveJsonList(convertListToJson());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: called");

    }







}

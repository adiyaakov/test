package adiyaakovdevelopment.linesms;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by androiddevelopment on 3/28/17.
 */

public class InvitedDialogFragment extends DialogFragment {
    private Context context;
    private OnDoneParseList listener;

    //outlets declaration:
    private EditText cName,cPhone,cNumOfPeople;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //inflate the xml layout
        AlertDialog.Builder builder  = new AlertDialog.Builder(context);//THE context is null here!
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fragment_create_invited, null);

        //outlets:

        cName = (EditText)view.findViewById(R.id.input_df_cName);
        cPhone = (EditText)view.findViewById(R.id.input_df_cPhone);
        cNumOfPeople = (EditText)view.findViewById(R.id.input_df_cNumOfPeople);
        Button add = (Button)view.findViewById(R.id.input_df_addButton);

        //onClick Handle
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = cNumOfPeople.getText().toString();
                String sName = cName.getText().toString();
                String sPhone = cPhone.getText().toString();

                validation(sName,sPhone,n);

            }
        });

        //set the view and create on the screen
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("TAG", "onAttach: called");
        this.context = context;//get the context from the activity
        listener = (OnDoneParseList) context;
    }

    private void validation(String sName,String sPhone, String numOfPeople){
        if (isNumOfPeopleValid(numOfPeople) && isValidName(sName) && isValidPhone(sPhone)){
            int n = Integer.parseInt(cNumOfPeople.getText().toString());//parse string to int

            //create new Instanse
            Invited i = new Invited(sName,sPhone,n);

            //alert the listner to work
            listener.onFinishEditDialog(i);
            dismiss();

        }
    }

    private boolean isValidName(String n){
        if (n.length() >= 2 && n.length()<10){
            return true;
        }
        else {
            cName.setError("error");
            return false;
        }
    }

    private boolean isValidPhone(String p){
        if (p.length()>=9 && p.length()<=11){
            return true;
        }
        else {
            cPhone.setError("error");
            return false;
        }
    }

    private boolean isNumOfPeopleValid(String n){
        if (n!=null && !n.isEmpty() && n!="0"){
            return true;
        }
        else {
            cNumOfPeople.setError("error");
            return false;
        }
    }




}

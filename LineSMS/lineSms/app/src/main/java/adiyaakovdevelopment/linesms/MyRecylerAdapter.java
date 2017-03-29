package adiyaakovdevelopment.linesms;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by androiddevelopment on 3/27/17.
 */

public class MyRecylerAdapter extends RecyclerView.Adapter<MyRecylerAdapter.MyViewHolder>{
    private ArrayList<Invited> list = new ArrayList<>();


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cell,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Invited i = list.get(position);
        holder.cNumberOfPeople.setText(i.getNumOfPeople()+"");
        holder.cName.setText(i.getcName());
        holder.cSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms(i.getcPhone());
            }
        });
        holder.waitingTime.setText(minutesLeftCounter(i.getD()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                eraseCell(position);
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    private void sendSms(String to){
        Log.d("sms", "sendSms to: " + to);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(to,null,"your turn is now",null,null);

    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        //rv cell outlets:
        TextView cName, cNumberOfPeople, waitingTime;
        Button cSms;


        public MyViewHolder(View itemView) {
            super(itemView);

            waitingTime = (TextView)itemView.findViewById(R.id.rv_cell_label_wating_time);
            cName = (TextView)itemView.findViewById(R.id.rv_cell_label_cName);
            cNumberOfPeople = (TextView)itemView.findViewById(R.id.rv_cell_label_numOfPeople);
            cSms = (Button)itemView.findViewById(R.id.rv_cell_sms_btn);


        }
    }

    private String minutesLeftCounter(long d){
        long cTime= System.currentTimeMillis();
        long diff = cTime - d;

        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        return (minutes + "m");

    }

    public void setList(ArrayList<Invited> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addNewItemTolist(Invited i){
        this.list.add(i);
        notifyDataSetChanged();
    }

    public ArrayList<Invited> getList() {
        return list;

    }

    private void eraseCell(int position){
        list.remove(position);
        notifyDataSetChanged();
    }


}

package adiyaakovdevelopment.linesms;

import java.util.Date;

/**
 * Created by androiddevelopment on 3/27/17.
 */

public class Invited {
    private String cName;
    private String cPhone;
    private int numOfPeople;
    private long d;// == creation time in long


    public Invited(String cName, String cPhone, int numOfPeople,long d){
        this.cName = cName;
        this.cPhone = cPhone;
        this.numOfPeople = numOfPeople;
        this.d = d;
    }

    public Invited(String cName, String cPhone, int numOfPeople){
        this.cName = cName;
        this.cPhone = cPhone;
        this.numOfPeople = numOfPeople;
        this.d = System.currentTimeMillis();

    }


    public String getcPhone() {
        return cPhone;
    }

    public String getcName() {
        return cName;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public long getD() {
        return d;
    }



}

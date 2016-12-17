package bgbingogame.bingo;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class Bingo_Main_Game extends Activity {

    //creates array which will store the bingo ball numbers
    public static ArrayList<Integer> Ball = new ArrayList<Integer>();
    public static ArrayList<String> DatabaseBall=new ArrayList<String>();
    public String[] random_ball_array=new String[100];
    //creates array which will store the bingo cards numbers
    int[] BingoCardArray = new int[25];

    final boolean[] display_previous_number = {false};
    final String [] Intended=new String[4];
    //chillin on main thread
    Handler handler_sun=new Handler(){
        public void handleMessage(Message msg){
            CurrentBall=DatabaseBall.get(index).toString();
           // BingoBall = New_Random_Bingo_Ball_Result;
            final TextView ball_button=(TextView)findViewById(R.id.sun_bingo_ball_txt);
            ball_button.setText(CurrentBall);
            Bingo_Main_Game.Ball.add(Integer.parseInt(CurrentBall));

            String message=Bingo_Main_Game.Ball.toString();
            String message1=message.replace("["," ");
            String message2=message1.replace("]"," ");
            //DISPLAYS PREVIOUS BALL NUMBERS ON SCREEN
            final TextView previous_numbers=(TextView) findViewById(R.id.bingo_numbers);
            previous_numbers.setText(message2);


        }
    };
    Handler handler6=new Handler() {
        public void  handleMessage(Message msg) {
            View cloud6 = findViewById(R.id.cloud_6);
            ViewGroup.MarginLayoutParams mlp6 = (ViewGroup.MarginLayoutParams) cloud6.getLayoutParams();
            int top = mlp6.topMargin;
            int bottom = mlp6.bottomMargin;
            mlp6.setMargins(newLeft6, top, newRight6, bottom);//all in pixels
            cloud6.setLayoutParams(mlp6);
        }
    };
    Handler handler2=new Handler() {
        public void  handleMessage(Message msg) {

            View cloud2=findViewById(R.id.cloud_2);
            ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams) cloud2.getLayoutParams();
            int top2=mlp2.topMargin;
            int bottom2=mlp2.bottomMargin;
            mlp2.setMargins(newLeft2, top2, newRight2, bottom2);//all in pixels
            cloud2.setLayoutParams(mlp2);
        }
    };
    Handler handler1=new Handler() {
        public void  handleMessage(Message msg) {

            View cloud1=findViewById(R.id.cloud_1);
            ViewGroup.MarginLayoutParams mlp1 = (ViewGroup.MarginLayoutParams) cloud1.getLayoutParams();
            int top1=mlp1.topMargin;
            int bottom1=mlp1.bottomMargin;
            mlp1.setMargins(newLeft1, top1, newRight1, bottom1);//all in pixels
            cloud1.setLayoutParams(mlp1);
        }
    };
    Handler handler3=new Handler() {
        public void  handleMessage(Message msg) {

            View cloud3=findViewById(R.id.cloud_3);
            ViewGroup.MarginLayoutParams mlp3 = (ViewGroup.MarginLayoutParams) cloud3.getLayoutParams();
            int top3=mlp3.topMargin;
            int bottom3=mlp3.bottomMargin;
            mlp3.setMargins(newLeft3, top3, newRight3, bottom3);//all in pixels
            cloud3.setLayoutParams(mlp3);
        }
    };
    Handler handler4=new Handler() {
        public void  handleMessage(Message msg) {
            View cloud4=findViewById(R.id.cloud_4);
            ViewGroup.MarginLayoutParams mlp4 = (ViewGroup.MarginLayoutParams) cloud4.getLayoutParams();
            int top4=mlp4.topMargin;
            int bottom4=mlp4.bottomMargin;
            mlp4.setMargins(newLeft4, top4, newRight4, bottom4);//all in pixels
            cloud4.setLayoutParams(mlp4);
        }
    };
    Handler handler11=new Handler() {
        public void  handleMessage(Message msg) {
            View cloud11=findViewById(R.id.cloud_11);
            ViewGroup.MarginLayoutParams mlp11 = (ViewGroup.MarginLayoutParams) cloud11.getLayoutParams();
            int top11=mlp11.topMargin;
            int bottom11=mlp11.bottomMargin;
            mlp11.setMargins(newLeft11, top11, newRight11, bottom11);//all in pixels
            cloud11.setLayoutParams(mlp11);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bingo__main);

        Intent intent3 = getIntent();
        final String game = intent3.getStringExtra("Game");
        final String username1=intent3.getStringExtra("username1");
        final String group_name1 = intent3.getStringExtra("group_name1");
        final String winner_username=username1;
        //final String[] New_Game = new String[1];
        Intended[0]=game;
        Intended[1]=group_name1;
        Intended[2]=username1;
        Intended[3]=winner_username;

        TextView user_info=(TextView) findViewById(R.id.intended_inv);
        user_info.setText(game+" "+username1+" "+group_name1+" "+winner_username);


        RandomBallData();
        BingoNumbers();
        RandomBallClass();
        setupboard();

        Loser();
        Clouds();
      //  Bingo_Ball_Situation();
        PreviousNumbers();
        Exit();

    }//closes onCreate

    int newRight2,newRight1,newRight3,newRight6, newRight4,newRight11;
    int newLeft2,newLeft1,newLeft3,newLeft6, newLeft4,newLeft11;

    public void Clouds(){
        int delay = 0; // delay for half a second.
        int period1 = 40; // repeat every  sec.
        final int pix=1;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
               View cloud6=findViewById(R.id.cloud_6);

                    ViewGroup.MarginLayoutParams mlp6 = (ViewGroup.MarginLayoutParams) cloud6.getLayoutParams();
                    int right6=mlp6.rightMargin;
                    int left6=mlp6.leftMargin;
                if (left6>-520){
                    newRight6=right6+pix;
                    newLeft6=left6-pix;
                    handler6.sendEmptyMessage(0);
                }
                else {
                    int right_6=right6;
                    int left_6=(int)(1350);
                    newRight6=right_6-pix;
                    newLeft6=left_6+pix;
                    handler6.sendEmptyMessage(0);
                }
                View cloud2=findViewById(R.id.cloud_2);
                ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams) cloud2.getLayoutParams();
                int right2 = mlp2.rightMargin;
                int left2 = mlp2.leftMargin;
                if (left2>-520) {
                    newRight2 = right2 + pix;
                    newLeft2 = left2 - pix;
                    handler2.sendEmptyMessage(0);
                }
                else{
                    int right_2 = right2;
                    int left_2=(int)(1350);
                    newRight2 = right_2 - pix;
                    newLeft2 = left_2 + pix;
                    handler2.sendEmptyMessage(0);
                }


            }
        }, delay, period1);

        int period2 = 20; // repeat every  sec.

        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                View cloud1=findViewById(R.id.cloud_1);
                ViewGroup.MarginLayoutParams mlp1 = (ViewGroup.MarginLayoutParams) cloud1.getLayoutParams();
                int right1=mlp1.rightMargin;
                int left1=mlp1.leftMargin;
                if(left1>-520){
                    newRight1=right1+pix;
                    newLeft1=left1-pix;
                    handler1.sendEmptyMessage(0);
                }
                else{
                    int right_1 = right1;
                    int left_1 = (int)(1350);;
                    newRight1 = right_1 - pix;
                    newLeft1 = left_1 + pix;
                    handler1.sendEmptyMessage(0);
                }
                View cloud3=findViewById(R.id.cloud_3);
                ViewGroup.MarginLayoutParams mlp3 = (ViewGroup.MarginLayoutParams) cloud3.getLayoutParams();
                int right3=mlp3.rightMargin;
                int left3=mlp3.leftMargin;
                if (left3>-520){
                    newRight3=right3+pix;
                    newLeft3=left3-pix;
                    handler3.sendEmptyMessage(0);
                }
                else{
                    int right_3 = right3;
                    int left_3 = (int)(1350);;
                    newRight3 = right_3 - pix;
                    newLeft3 = left_3 + pix;
                    handler3.sendEmptyMessage(0);
                }
                View cloud4=findViewById(R.id.cloud_4);
                ViewGroup.MarginLayoutParams mlp4 = (ViewGroup.MarginLayoutParams) cloud4.getLayoutParams();
                int right4=mlp4.rightMargin;
                int left4=mlp4.leftMargin;
                if(left4>-520){
                    newRight4=right4+pix;
                    newLeft4=left4-pix;
                    handler4.sendEmptyMessage(0);
                }
                else{
                    int right_4 = right4;
                    int left_4 = (int)(1350);;
                    newRight4 = right_4 - pix;
                    newLeft4 = left_4 + pix;
                    handler4.sendEmptyMessage(0);

                }

            }
        }, delay, period2);
        int period3 =75;
        Timer timer3 = new Timer();
        timer3.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                View cloud11=findViewById(R.id.cloud_11);

                ViewGroup.MarginLayoutParams mlp11 = (ViewGroup.MarginLayoutParams) cloud11.getLayoutParams();
                int right11=mlp11.rightMargin;
                int left11=mlp11.leftMargin;
                if (left11>-520){
                    newRight11=right11+pix;
                    newLeft11=left11-pix;
                    handler11.sendEmptyMessage(0);
                }
                else {
                    int right_11=right11;
                    int left_11=(int)(1350);
                    newRight11=right_11-pix;
                    newLeft11=left_11+pix;
                    handler11.sendEmptyMessage(0);
                }

            }
        },10, period3);
    }//close Clouds



    //CREATE THE RANDOM BALL FUNCTION
    public void RandomBallClass(){
        // THIS MAKES THE RANDOM BALL BUTTON
        final TextView RandomBalls=(TextView)findViewById(R.id.sun_bingo_ball_txt);
       // final Button RandomBalls=(Button)findViewById(ball);
        RandomBalls.setText("READY");
    }//close RandomBallClass()

    //CHECK TO SEE IF RANDOM BALL DATA IS ALREAD IN THE DATABASE IF IT IS NOT THEN ADD IT.
    public void RandomBallData(){
        TextView user_info=(TextView) findViewById(R.id.intended_inv);
        String user= (String) user_info.getText();
        String[] userinfo= user.split(" ");
        String group_name=userinfo[2].toString();

        Response.Listener<String> response_listener= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse= new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success){
                        String random_ball=jsonResponse.getString("random_ball");
                        String[] random_ball_array=random_ball.split(" ");
                        for (String ball:random_ball_array) {
                            DatabaseBall.add(ball);
                        }
                       // DatabaseBall.remove(0);
                        Bingo_Ball_Timer_Thread();

                    }//close if statement
                    else{
                       InsertRandomBall();
                    }//close else statement
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RandomBallExhists random_ball_exhists = new RandomBallExhists(group_name,response_listener);
        RequestQueue queue = Volley.newRequestQueue(Bingo_Main_Game.this);
        queue.add(random_ball_exhists);
    }

    //CHECK TO SEE IF RANDOM BALL DATA IS NOT IN THE DATABASE IF IT IS NOT THEN ADD IT.
    private void InsertRandomBall(){
       // int Random_Bingo_Result=(int)Math.floor((Math.random()*100)+1);
       // Ball.add(Random_Bingo_Result);
        for(int i=0;i<100;i++){
            int New_Random_Bingo_Ball_Result = (int)Math.floor((Math.random()*100)+1);
            while (Ball.contains(New_Random_Bingo_Ball_Result)){
                New_Random_Bingo_Ball_Result = (int)Math.floor((Math.random()*100)+1);
            }//closes while loop
            Bingo_Main_Game.Ball.add(New_Random_Bingo_Ball_Result);
        }

        String message=Bingo_Main_Game.Ball.toString();
        String message1=message.replace("["," ");
        final String message2=message1.replace("]"," ");
        final String random_ball=message2.replace(","," ");
        TextView user_info=(TextView) findViewById(R.id.intended_inv);
        String user= (String) user_info.getText();
        String[] userinfo= user.split(" ");
        String group_name=userinfo[2].toString();


        Response.Listener<String> response_listener= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse= new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success){
                        Ball.clear();
                        RandomBallData();
                    }//close if statement
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Bingo_Main_Game.this);
                        builder.setMessage("The Random Ball Database Failed to be Updated")
                                .create()
                                .show();
                    }//close else statement
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };// closes InsertRandomBall

        RandomBallSender random_ball_sender = new RandomBallSender(group_name,random_ball,response_listener);
        RequestQueue queue = Volley.newRequestQueue(Bingo_Main_Game.this);
        queue.add(random_ball_sender);
    }

    //WHILE THE NEW RANDOM BINGO BALL NUMBER EQUALS AND EXHISTING BALL NUMBER PICK ANTHER RANDOM NUMBER
    //THIS CREATES THE BINGO BALL ACTIONS/METHODS
    //THIS IS THE BINGO SUN THREAD SO NUMBERS CHANGE
    Timer Sun_timer = new Timer();
    String CurrentBall;
    int index=0;
    public void Bingo_Ball_Timer_Thread(){
        int delaysun = 1000; // delay for 3 sec.
        int periodsun = 3000; // repeat every 6 sec.

        Sun_timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                index++;

                runOnUiThread(new Runnable() //run on ui threa
                {
                    public void run()
                    {
                        if (index % 2 !=0){
                            handler_sun.sendEmptyMessage(0);
                        }

                    }
                });
           }

        }, delaysun, periodsun);

    }//closes Bingo_Ball_Timer_Thread

    //THIS IS JUST A CHECK THAT NUMBERS ON THE CARDS DON'T REPEAT
    public void BingoNumbers(){

        for(int num=0; num<25; num++){
            int Random_Bingo_Card_Number = (int)Math.floor((Math.random()*100)+1);
            String array= Arrays.toString(BingoCardArray);

            //WHILE THE RANDOM NUMBER EQUALS AND EXHISTING CARD NUMBER REPEAT
            while(array.contains(Integer.toString(Random_Bingo_Card_Number))){
                Random_Bingo_Card_Number = (int)Math.floor((Math.random()*100)+1);
            }//closes while loop

            BingoCardArray[num] = Random_Bingo_Card_Number;
        }//closes for loop
    }//close BingoCardNumbers()

    private void setupboard() {
        for(int num=1; num<=25; num++){

            int nameID = getResources().getIdentifier("button"+num, "id", getPackageName());
            final Button boardbutton=(Button)findViewById(nameID);
            String Random_Bingo_Card = Integer.toString(BingoCardArray[num-1]);

            if(num==13){
                boardbutton.setText("Free");
            }
            else{
                //set boardbutton text equal to a random number
                boardbutton.setText(Random_Bingo_Card);
            }//closes else
        }//closes for loop

    }//closes setupboard() class


    public void Game(View view){

        Button b = (Button)view;
        String buttonText = b.getText().toString();
        //Because buttonText can be a number(42) or "X" we use
        // try/catch to catch the errors that happen when you convert "X" to an integer
        //if numbers equal instead set red: ff0000
        try{
            int button_number=Integer.parseInt(buttonText);
           // int Random_Bingo_Ball=Integer.parseInt(CurrentBall);
            if(Ball.contains(button_number)){
                //if(button_number==button_number){
                view.setBackgroundResource(R.drawable.red_button);
                view.setTag(1);
                b.setTextColor(Color.parseColor("#cce6ff"));

            }//close if
            else{
                Toast.makeText(
                        Bingo_Main_Game.this,
                        "Sorry Not A Match",
                        Toast.LENGTH_LONG
                ).show();

            }//close else
        }//close try
        catch (NumberFormatException e1){
            Toast.makeText(

                    Bingo_Main_Game.this,
                    "you got this",
                    Toast.LENGTH_LONG
            ).show();

        }//close catch

        if (WIN_LOSE()==true){
            /**
             * SO Basically what i need to do:
             *  when Bingo_Main_Game.WIN_LOSE()==true
             *      Get the User_Name and Group_Name
             *      Send win to php document ...this will insert it into the GameStatus database table
             **/

            TextView user_info=(TextView) findViewById(R.id.intended_inv);
            String user= (String) user_info.getText();
            String[] userinfo= user.split(" ");

            String game=userinfo[0].toString();
            String username=userinfo[1].toString();
            String group_name=userinfo[2].toString();
            String winner_username=userinfo[3].toString();

            final String status="win";

            timer_loser.cancel();//this cancels the thread that checks if the player has lost
            Response.Listener<String> response_listener= new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse= new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if (success){
                            Sun_timer.cancel();
                            final TextView ball_button=(TextView)findViewById(R.id.sun_bingo_ball_txt);
                            ball_button.setText("YAY");
                            WINNER_CLOUD();
                            Toast.makeText(
                                    Bingo_Main_Game.this,
                                    "Congratulations!!!!",
                                    Toast.LENGTH_LONG
                            ).show();
                            final TextView previous=(TextView) findViewById(R.id.cloud_bingo_numbers);
                            final TextView previoustext=(TextView) findViewById(R.id.bingo_numbers);
                            if(display_previous_number[0] ==true){
                                previoustext.setVisibility(View.INVISIBLE);
                                previous.setText("Previous Numbers");
                                display_previous_number[0] =false;
                            }
                            Ball.clear();
                            Intent registerIntent4=new Intent(Bingo_Main_Game.this,GroupPage.class);
                            startActivity(registerIntent4);

                        }//close if statement
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(Bingo_Main_Game.this);
                            builder.setMessage("Win Status Failed to Update")
                                    .create()
                                    .show();
                        }//close else statement
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            Game_Won_Output WinUpdate = new Game_Won_Output(game,status,group_name,username,winner_username,response_listener);
            RequestQueue queue = Volley.newRequestQueue(Bingo_Main_Game.this);
            queue.add(WinUpdate);

        }//close WIN_Lose if statement

    }//close Game
    public void WINNER_CLOUD(){
        final TextView cloud_win=(TextView) findViewById(R.id.cloud_win);
                    cloud_win.setVisibility(View.VISIBLE);

    }
    //NOW FOR THE RULES
    public boolean WIN_LOSE (){

        Button button1=(Button)findViewById(R.id.button1);
        Button button2=(Button)findViewById(R.id.button2);
        Button button3=(Button)findViewById(R.id.button3);
        Button button4=(Button)findViewById(R.id.button4);
        Button button5=(Button)findViewById(R.id.button5);
        Button button6=(Button)findViewById(R.id.button6);
        Button button7=(Button)findViewById(R.id.button7);
        Button button8=(Button)findViewById(R.id.button8);
        Button button9=(Button)findViewById(R.id.button9);
        Button button10=(Button)findViewById(R.id.button10);
        Button button11=(Button)findViewById(R.id.button11);
        Button button12=(Button)findViewById(R.id.button12);
        Button button13=(Button)findViewById(R.id.button13);
        Button button14=(Button)findViewById(R.id.button14);
        Button button15=(Button)findViewById(R.id.button15);
        Button button16=(Button)findViewById(R.id.button16);
        Button button17=(Button)findViewById(R.id.button17);
        Button button18=(Button)findViewById(R.id.button18);
        Button button19=(Button)findViewById(R.id.button19);
        Button button20=(Button)findViewById(R.id.button20);
        Button button21=(Button)findViewById(R.id.button21);
        Button button22=(Button)findViewById(R.id.button22);
        Button button23=(Button)findViewById(R.id.button23);
        Button button24=(Button)findViewById(R.id.button24);
        Button button25=(Button)findViewById(R.id.button25);

        //THIS CHECKS FOR DIAGONAL WIN
       boolean diag0=(String.valueOf(button1.getTag())=="1")
               &&(String.valueOf(button7.getTag())=="1")
               &&(button13.getText()=="Free")
               &&(String.valueOf(button19.getTag())=="1")
               &&(String.valueOf(button25.getTag())=="1");
        boolean diag1=(String.valueOf(button5.getTag())=="1")
                &&(String.valueOf(button9.getTag())=="1")
                &&(button13.getText()=="Free")
                &&(String.valueOf(button17.getTag())=="1")
                &&(String.valueOf(button21.getTag())=="1");

        //LEFT->RIGHT DIAGONAL
        if (diag0){
            for (int j=1;j<=25;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                //  button_id.setBackgroundColor(Color.BLUE);
                button_id.setBackgroundResource(R.drawable.button_board);
            }
            button13.setTextColor(Color.parseColor("#cce6ff"));
            for (int j=1;j<=25;j+=6){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
              //  button_id.setBackgroundColor(Color.BLUE);
                button_id.setBackgroundResource(R.drawable.button_win_board);
                button_id.setTextColor(Color.WHITE);
            }
            return true;
        }//closes IF condition

        //RIGHT->LEFT DIAGONAL
        if (diag1){
            for (int j=1;j<=25;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                //  button_id.setBackgroundColor(Color.BLUE);
                button_id.setBackgroundResource(R.drawable.button_board);
            }
            for (int j=5;j<=21;j+=4){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                button_id.setBackgroundResource(R.drawable.button_win_board);
                button_id.setTextColor(Color.WHITE);
            }
            return true;
        }//closes IF condition

        //THIS CHECKS FOR A LEFT/RIGHT WIN <-->

        boolean row0=(String.valueOf(button1.getTag())=="1")
                &&(String.valueOf(button2.getTag())=="1")
                &&(String.valueOf(button3.getTag())=="1")
                &&(String.valueOf(button4.getTag())=="1")
                &&(String.valueOf(button5.getTag())=="1");
        boolean row1=(String.valueOf(button6.getTag())=="1")
                &&(String.valueOf(button7.getTag())=="1")
                &&(String.valueOf(button8.getTag())=="1")
                &&(String.valueOf(button9.getTag())=="1")
                &&(String.valueOf(button10.getTag())=="1");
        boolean row2=(String.valueOf(button11.getTag())=="1")
                &&(String.valueOf(button12.getTag())=="1")
                &&(String.valueOf(button13.getText())=="Free")
                &&(String.valueOf(button14.getTag())=="1")
                &&(String.valueOf(button15.getTag())=="1");
        boolean row3=(String.valueOf(button16.getTag())=="1")
                &&(String.valueOf(button17.getTag())=="1")
                &&(String.valueOf(button18.getTag())=="1")
                &&(String.valueOf(button19.getTag())=="1")
                &&(String.valueOf(button20.getTag())=="1");
        boolean row4=(String.valueOf(button21.getTag())=="1")
                &&(String.valueOf(button22.getTag())=="1")
                &&(String.valueOf(button23.getTag())=="1")
                &&(String.valueOf(button24.getTag())=="1")
                &&(String.valueOf(button25.getTag())=="1");

        if(row0){
            for (int j=1;j<=25;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                //  button_id.setBackgroundColor(Color.BLUE);
                button_id.setBackgroundResource(R.drawable.button_board);
            }
            for (int j=1;j<=5;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                button_id.setBackgroundResource(R.drawable.button_win_board);
                button_id.setTextColor(Color.WHITE);
            }
            return true;
        }//closes if condition

        if(row1){
            for (int j=1;j<=25;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                //  button_id.setBackgroundColor(Color.BLUE);
                button_id.setBackgroundResource(R.drawable.button_board);
            }
            for (int j=6;j<=10;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                button_id.setBackgroundResource(R.drawable.button_win_board);
                button_id.setTextColor(Color.WHITE);
            }
            return true;
        }//closes if condition

        if(row2){
            button13.setTextColor(Color.parseColor("#cce6ff"));
            for (int j=11;j<=15;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                button_id.setBackgroundResource(R.drawable.button_win_board);
                button_id.setTextColor(Color.WHITE);
            }
            return true;
        }//closes if condition

        if(row3){
            for (int j=1;j<=25;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                //  button_id.setBackgroundColor(Color.BLUE);
                button_id.setBackgroundResource(R.drawable.button_board);
            }
            for (int j=16;j<=20;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                button_id.setBackgroundResource(R.drawable.button_win_board);
                button_id.setTextColor(Color.WHITE);
            }
            return true;
        }//closes if condition

        if(row4){
            for (int j=1;j<=25;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                //  button_id.setBackgroundColor(Color.BLUE);
                button_id.setBackgroundResource(R.drawable.button_board);
            }
            for (int j=21;j<=25;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                button_id.setBackgroundResource(R.drawable.button_win_board);
                button_id.setTextColor(Color.WHITE);

            }
            return true;
        }//closes if condition
        //THIS CHECKS FOR A UP/DOWN WIN ^^^
        boolean col0=(String.valueOf(button1.getTag())=="1")
                &&(String.valueOf(button6.getTag())=="1")
                &&(String.valueOf(button11.getTag())=="1")
                &&(String.valueOf(button16.getTag())=="1")
                &&(String.valueOf(button21.getTag())=="1");
        boolean col1=(String.valueOf(button2.getTag())=="1")
                &&(String.valueOf(button7.getTag())=="1")
                &&(String.valueOf(button12.getTag())=="1")
                &&(String.valueOf(button17.getTag())=="1")
                &&(String.valueOf(button22.getTag())=="1");
        boolean col2=(String.valueOf(button3.getTag())=="1")
                &&(String.valueOf(button8.getTag())=="1")
                &&(String.valueOf(button13.getText())=="Free")
                &&(String.valueOf(button18.getTag())=="1")
                &&(String.valueOf(button23.getTag())=="1");
        boolean col3=(String.valueOf(button4.getTag())=="1")
                &&(String.valueOf(button9.getTag())=="1")
                &&(String.valueOf(button14.getTag())=="1")
                &&(String.valueOf(button19.getTag())=="1")
                &&(String.valueOf(button24.getTag())=="1");
        boolean col4=(String.valueOf(button5.getTag())=="1")
                &&(String.valueOf(button10.getTag())=="1")
                &&(String.valueOf(button15.getTag())=="1")
                &&(String.valueOf(button20.getTag())=="1")
                &&(String.valueOf(button25.getTag())=="1");
        if(col0){
            for (int j=1;j<=25;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                //  button_id.setBackgroundColor(Color.BLUE);
                button_id.setBackgroundResource(R.drawable.button_board);
            }
            for(int i=1;i<=21;i+=5){
                int buttonid = getResources().getIdentifier("button"+i, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                button_id.setBackgroundResource(R.drawable.button_win_board);
                button_id.setTextColor(Color.WHITE);
            }
            return true;
        }//closes if condition

        if(col1){
            for (int j=1;j<=25;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                //  button_id.setBackgroundColor(Color.BLUE);
                button_id.setBackgroundResource(R.drawable.button_board);
            }
            for(int i=2;i<=22;i+=5){
                int buttonid = getResources().getIdentifier("button"+i, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                button_id.setBackgroundResource(R.drawable.button_win_board);
                button_id.setTextColor(Color.WHITE);
            }
            return true;
        }//closes if condition

        if(col2){
            for (int j=1;j<=25;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                //  button_id.setBackgroundColor(Color.BLUE);
                button_id.setBackgroundResource(R.drawable.button_board);
            }
            button13.setTextColor(Color.parseColor("#cce6ff"));
            for(int i=3;i<=23;i+=5){
                int buttonid = getResources().getIdentifier("button"+i, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                button_id.setBackgroundResource(R.drawable.button_win_board);
                button_id.setTextColor(Color.WHITE);
            }
            return true;
        }//closes if condition

        if(col3){
            for (int j=1;j<=25;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                //  button_id.setBackgroundColor(Color.BLUE);
                button_id.setBackgroundResource(R.drawable.button_board);
            }
            for(int i=4;i<=24;i+=5){
                int buttonid = getResources().getIdentifier("button"+i, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                button_id.setBackgroundResource(R.drawable.button_win_board);
                button_id.setTextColor(Color.WHITE);
            }
            return true;
        }//closes if condition

        if(col4){
            for (int j=1;j<=25;j++){
                int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                //  button_id.setBackgroundColor(Color.BLUE);
                button_id.setBackgroundResource(R.drawable.button_board);
            }
            for(int i=5;i<=25;i+=5){
                int buttonid = getResources().getIdentifier("button"+i, "id", getPackageName());
                Button button_id=(Button)findViewById(buttonid);
                button_id.setBackgroundResource(R.drawable.button_win_board);
                button_id.setTextColor(Color.WHITE);
            }
            return true;
        }//closes if condition
        else{
            return false;
        }
    }//closes win
public void Exit(){
    final TextView exit_cloud=(TextView) findViewById(R.id.cloud_exit);
    final TextView cloud_exit_lost = (TextView) findViewById(R.id.cloud_exit_lost);

    //when register here is clicked
    exit_cloud.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Sun_timer.cancel();
            Ball.clear();
            BingoCardArray=null;

            Intent registerIntent1=new Intent(Bingo_Main_Game.this,Bingo_Main_Login.class);
            startActivity(registerIntent1);

        }
    });
    cloud_exit_lost.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Sun_timer.cancel();
            Ball.clear();
            BingoCardArray=null;

            Intent registerIntent2=new Intent(Bingo_Main_Game.this,Bingo_Main_Login.class);
            startActivity(registerIntent2);
        }
    });
}
    public void PreviousNumbers(){
        final TextView previous=(TextView) findViewById(R.id.cloud_bingo_numbers);
        final TextView previoustext=(TextView) findViewById(R.id.bingo_numbers);

        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (display_previous_number[0] ==false){
                    previoustext.setVisibility(View.VISIBLE);
                    previous.setText("Hide Numbers");
                    display_previous_number[0] =true;
                }
                else{
                    previoustext.setVisibility(View.INVISIBLE);
                    previous.setText("Previous Numbers");
                    display_previous_number[0] =false;
                }

            }
        });

    }
    Timer timer_loser = new Timer();

    public void Loser(){
        /**
         * So Basically what i need to do:
         *   create a thread which continusouly asks the database if any user in a certain group has won
         *   if the search returns "win"
         *   player looses
         **/
        //Threads to consistently check win/lose all the time.
            int delay = 3000; // delay for 3 sec.
            int period = 1000; // repeat every 6 sec.

            // Timer timer = new Timer();
            timer_loser.scheduleAtFixedRate(new TimerTask()
            {
                public void run()
                {
                    TextView user_info=(TextView) findViewById(R.id.intended_inv);
                    String user= (String) user_info.getText();
                    String[] userinfo= user.split(" ");

                    String group_name=userinfo[2].toString();

                    Response.Listener<String> response_listener= new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse= new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success){
                                    //clears the ball array
                                    Ball.clear();
                                    //stops the Sun Ball timer
                                    Sun_timer.cancel();
                                    //sets the background color dark blue
                                    RelativeLayout sky=(RelativeLayout)findViewById(R.id.activity_bingo__main);
                                    sky.setBackgroundColor(Color.parseColor("#2a4d6f"));

                                    //sets the loser cloud and sheen to visible
                                    final TextView cloud_lost=(TextView) findViewById(R.id.cloud_lost);
                                    cloud_lost.setVisibility(View.VISIBLE);
                                    final LinearLayout sheen_lost=(LinearLayout) findViewById(R.id.lost_sheen);
                                    sheen_lost.setVisibility(View.VISIBLE);
                                    final TextView cloud_exit= (TextView)findViewById(R.id.cloud_exit);
                                    cloud_exit.setVisibility(View.INVISIBLE);
                                    final TextView cloud_exit_lost=(TextView) findViewById(R.id.cloud_exit_lost);
                                    cloud_exit_lost.setVisibility(View.VISIBLE);


                                    //darkens all of the button
                                    for (int j=1;j<=25;j++){
                                        int buttonid = getResources().getIdentifier("button"+j, "id", getPackageName());
                                        Button button_id=(Button)findViewById(buttonid);
                                        button_id.setTextColor(Color.LTGRAY);
                                        button_id.setBackgroundResource(R.drawable.button_lost);
                                    }

                                    timer_loser.cancel();
                                }//close if statement

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    Game_Lost_Output gamelostoutput= new Game_Lost_Output(group_name, response_listener);
                    RequestQueue queue = Volley.newRequestQueue(Bingo_Main_Game.this);
                    queue.add(gamelostoutput);

                }
            }, delay, period);

        }//close Loser();


}//closes Bingo_Main class

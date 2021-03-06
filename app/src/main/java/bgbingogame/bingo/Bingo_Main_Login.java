package bgbingogame.bingo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class Bingo_Main_Login extends AppCompatActivity {
    Handler handler6=new Handler() {
        public void  handleMessage(Message msg) {
            View cloud6 = findViewById(R.id.cloud_40);
            ViewGroup.MarginLayoutParams mlp6 = (ViewGroup.MarginLayoutParams) cloud6.getLayoutParams();
            int top = mlp6.topMargin;
            int bottom = mlp6.bottomMargin;
            mlp6.setMargins(newLeft6, top, newRight6, bottom);//all in pixels
            cloud6.setLayoutParams(mlp6);
        }
    };
    Handler handler2=new Handler() {
        public void  handleMessage(Message msg) {

            View cloud2=findViewById(R.id.cloud_41);
            ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams) cloud2.getLayoutParams();
            int top2=mlp2.topMargin;
            int bottom2=mlp2.bottomMargin;
            mlp2.setMargins(newLeft2, top2, newRight2, bottom2);//all in pixels
            cloud2.setLayoutParams(mlp2);
        }
    };
    Handler handler1=new Handler() {
        public void  handleMessage(Message msg) {

            View cloud1=findViewById(R.id.cloud_42);
            ViewGroup.MarginLayoutParams mlp1 = (ViewGroup.MarginLayoutParams) cloud1.getLayoutParams();
            int top1=mlp1.topMargin;
            int bottom1=mlp1.bottomMargin;
            mlp1.setMargins(newLeft1, top1, newRight1, bottom1);//all in pixels
            cloud1.setLayoutParams(mlp1);
        }
    };
    Handler handler3=new Handler() {
        public void  handleMessage(Message msg) {

            View cloud3=findViewById(R.id.cloud_43);
            ViewGroup.MarginLayoutParams mlp3 = (ViewGroup.MarginLayoutParams) cloud3.getLayoutParams();
            int top3=mlp3.topMargin;
            int bottom3=mlp3.bottomMargin;
            mlp3.setMargins(newLeft3, top3, newRight3, bottom3);//all in pixels
            cloud3.setLayoutParams(mlp3);
        }
    };
    Handler handler4=new Handler() {
        public void  handleMessage(Message msg) {
            View cloud4=findViewById(R.id.cloud_44);
            ViewGroup.MarginLayoutParams mlp4 = (ViewGroup.MarginLayoutParams) cloud4.getLayoutParams();
            int top4=mlp4.topMargin;
            int bottom4=mlp4.bottomMargin;
            mlp4.setMargins(newLeft4, top4, newRight4, bottom4);//all in pixels
            cloud4.setLayoutParams(mlp4);
        }
    };
    Handler handler11=new Handler() {
        public void  handleMessage(Message msg) {
            View cloud11=findViewById(R.id.cloud_45);
            ViewGroup.MarginLayoutParams mlp11 = (ViewGroup.MarginLayoutParams) cloud11.getLayoutParams();
            int top11=mlp11.topMargin;
            int bottom11=mlp11.bottomMargin;
            mlp11.setMargins(newLeft11, top11, newRight11, bottom11);//all in pixels
            cloud11.setLayoutParams(mlp11);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername=(EditText) findViewById(R.id.etUsername);
        final EditText etPassword=(EditText) findViewById(R.id.etPassword);
        final EditText etGroupName=(EditText) findViewById(R.id.etGroupName);
        final Button login_button=(Button) findViewById(R.id.login_button);
        final TextView register_link=(TextView) findViewById(R.id.registerlink);
        Clouds3();
        //when register here is clicked
        register_link.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(Bingo_Main_Login.this,Register.class);
                Bingo_Main_Login.this.startActivity(registerIntent);
            }
        });
        //when user clicks login
        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String group_name = etGroupName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> response_listener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse= new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                String username=jsonResponse.getString("username");
                                String group_name= jsonResponse.getString("group_name");

                                Intent intent=new Intent(Bingo_Main_Login.this,GroupPage.class);
                                intent.putExtra("username",username);
                                intent.putExtra("group_name",group_name);

                                Bingo_Main_Login.this.startActivity(intent);
                            }//close if statement
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Bingo_Main_Login.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }//close else statement
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest= new LoginRequest(username,group_name,password,response_listener);
                RequestQueue queue = Volley.newRequestQueue(Bingo_Main_Login.this);
                queue.add(loginRequest);
            }//close onclick
        });
    }//closes onCreate

    int newRight2,newRight1,newRight3,newRight6, newRight4,newRight11;
    int newLeft2,newLeft1,newLeft3,newLeft6, newLeft4,newLeft11;
    public void Clouds3(){
        int delay = 0; // delay for half a second.
        int period1 = 40; // repeat every  sec.
        final int pix=1;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                View cloud6=findViewById(R.id.cloud_40);

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
                View cloud2=findViewById(R.id.cloud_41);
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
                View cloud1=findViewById(R.id.cloud_42);
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
                View cloud3=findViewById(R.id.cloud_43);
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
                View cloud4=findViewById(R.id.cloud_44);
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
                View cloud11=findViewById(R.id.cloud_45);

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


}

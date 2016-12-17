package bgbingogame.bingo;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GroupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_page);
        Intent intent2 = getIntent();
        final String username1=intent2.getStringExtra("username");
        final String group_name1 = intent2.getStringExtra("group_name");
        final String[] New_Game = new String[1];
        //final ArrayList<String> User_Winner=new ArrayList<String>();
        final Integer[] User_Winner = {0};
                // String message = "Hello "+username+", welcome to BGBingo Game."+"\n"+"You are in group: "+group_name+".";
        //welcome_message.setText(message);*/

        Response.Listener<String> response_listener= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse= new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    TextView winner_stat=(TextView) findViewById(R.id.group_winner);
                    TextView Game_number=(TextView) findViewById(R.id.group_stat);
                    TextView Test=(TextView)findViewById(R.id.individual_stats);

                    if (success){
                        String message = null;
                        ArrayList<String> game = new ArrayList<String>();
                        ArrayList<Integer> game_2=new ArrayList<Integer>();
                        ArrayList<String> winner = new ArrayList<String>();

                        String results=jsonResponse.getString("results");
                        String[] q_results=results.split(",|\\|");

                        for (int i=0; i<q_results.length; i+=2){
                                game.add(" "+q_results[i]);
                        }
                        String games= "Played Games\n"+game.toString();
                        String game1=games.replace("[","");
                        String game2=game1.replace("]","");
                        String game3=game2.replace(" , ","\n");

                        Game_number.setText(game3);

                        /*Each Game played gets assigned a game number
                        * This function gathers the game numbers from the database
                        * Calculates the Maximum number
                        * Now if the player plays a new game the game will be assigned
                        * a Game Number 1+ than the previous game number*/

                        for (String element:game) {
                            game_2.add(Integer.parseInt(element.replace(" ","")));
                        }
                        Integer Max_Group= Collections.max(game_2);
                        Integer new_games=Max_Group+1;
                        New_Game[0] = new_games.toString();


                            for (int i=0; i<q_results.length; i+=3){
                                if (i==0){
                                    winner.add(0," "+q_results[0]);
                                }
                                else{
                                    winner.add(i-2," "+q_results[i]);
                                }


                            }

                        String wins= "Winners\n"+winner.toString();
                        String win1=wins.replace("[","");
                        String win2=win1.replace("]","");
                        String win3=win2.replace(" , ","\n");
                        winner_stat.setText(win3);
                        
                    }//close if statement
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(GroupPage.this);
                        builder.setMessage("Retrieval Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }//close else statement
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        GameStatistics gamestats= new GameStatistics(group_name1,response_listener);
        RequestQueue queue = Volley.newRequestQueue(GroupPage.this);
        queue.add(gamestats);




    final Button new_game_button=(Button) findViewById(R.id.new_game_button);
        new_game_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String Game=New_Game[0].toString();
               /* Toast.makeText(
                        GroupPage.this,
                        Game,
                        Toast.LENGTH_LONG
                ).show();*/
                Intent intent=new Intent(GroupPage.this,Bingo_Main_Game.class);
                intent.putExtra("username1",username1);
                intent.putExtra("group_name1",group_name1);
                intent.putExtra("Game", Game);

                GroupPage.this.startActivity(intent);

            }

        });
        final TextView logout_button=(TextView) findViewById(R.id.cloud_logout);
        logout_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GroupPage.this,Bingo_Main_Login.class);
                GroupPage.this.startActivity(intent);
                Response.Listener<String> response_listener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse= new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){

                                Intent intent=new Intent(GroupPage.this,Bingo_Main_Login.class);
                                intent.putExtra("username",username1);
                                intent.putExtra("group_name",group_name1);

                                GroupPage.this.startActivity(intent);
                            }//close if statement
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(GroupPage.this);
                                builder.setMessage("Logout Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }//close else statement
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LogOut logout= new LogOut(username1,group_name1,response_listener);
                RequestQueue queue = Volley.newRequestQueue(GroupPage.this);
                queue.add(logout);
            }

        });
        GroupStats();
    }
    public void GroupStats(){
        Intent intent2 = getIntent();
        final String username1=intent2.getStringExtra("username");
        final String group_name1 = intent2.getStringExtra("group_name");
        TextView background1=(TextView)findViewById(R.id.group_stat_background);
        String text="GROUP: "+group_name1.toUpperCase()+" STATISTICS";
        background1.setText(text);

        TextView background2=(TextView)findViewById(R.id.individual_stat_background);
        String text2="Player: "+username1.toUpperCase()+" STATISTICS";
        background2.setText(text2);


    }

}

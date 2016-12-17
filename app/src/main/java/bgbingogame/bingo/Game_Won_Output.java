package bgbingogame.bingo;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class Game_Won_Output extends StringRequest{
    //this class will allow us make request to php file on server
    // and to get a response as a string (stringRequest).
    private static final String GAME_STATUS_REQUEST_URL="http://bgbingo.x10host.com/Win_Status.php";
    private Map<String,String> params;
    public Game_Won_Output(String game, String status,String group_name, String username, String winner_username, Response.Listener<String> listener){
        super(Method.POST,GAME_STATUS_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("game",game);
        params.put("status",status);
        params.put("group_name",group_name);
        params.put("username",username);
        params.put("winner_username",winner_username);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


package bgbingogame.bingo;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by #872278 on 12/6/2016.
 */

public class Game_Lost_Output extends StringRequest{
    //this class will allow us make request to php file on server
    // and to get a response as a string (stringRequest).
    private static final String GAME_LOST_REQUEST_URL="http://bgbingo.x10host.com/Lose_Status.php";
    private Map<String,String> params;

    public Game_Lost_Output(String group_name,Response.Listener<String> listener){
        super(Method.POST,GAME_LOST_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("group_name",group_name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


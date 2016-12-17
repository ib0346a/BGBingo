package bgbingogame.bingo;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by #872278 on 12/16/2016.
 */

public class RandomBallSender extends StringRequest {
    //this class will allow us make request to php file on server
    // and to get a response as a string (stringRequest).
    private static final String LOGIN_REQUEST_URL="http://bgbingo.x10host.com/send_random_ball.php";
    private Map<String,String> params;

    public RandomBallSender(String group_name,String random_ball, Response.Listener<String> listener){
        super(Method.POST,LOGIN_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("group_name",group_name);
        params.put("random_ball",random_ball);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

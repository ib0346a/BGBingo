package bgbingogame.bingo;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by #872278 on 12/13/2016.
 */

public class LogOut extends StringRequest {
    //this class will allow us make request to php file on server
    // and to get a response as a string (stringRequest).
    private static final String LOGOUT_REQUEST_URL="http://bgbingo.x10host.com/LogOut.php";
    private Map<String,String> params;
    public LogOut(String username, String group_name,Response.Listener<String> listener){
        super(Method.POST,LOGOUT_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("username",username);
        params.put("group_name",group_name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

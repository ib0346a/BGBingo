package bgbingogame.bingo;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by #872278 on 11/26/2016.
 */

public class RegistrationRequest extends StringRequest {
    //this class will allow us make request to php file on server
    // and to get a response as a string (stringRequest).
    private static final String REGISTER_REQUEST_URL="http://bgbingo.x10host.com/Register.php";
    private Map<String,String> params;

    public RegistrationRequest(String name, String group_name, String username, String password, Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("name",name);
        params.put("group_name",group_name);
        params.put("username",username);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

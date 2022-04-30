package sample.model.util;

import org.json.JSONObject;

public class MessageJSON {
    public static final String TEXT_TYPE="text";
    public static final String FILE_TYPE="file";
    public static JSONObject createMessageJSON(String from,String to,String type,Object data)
    {
        JSONObject jo=new JSONObject();
        jo.put("from",from);
        jo.put("to",to);
        jo.put("type",type);
        jo.put("data",data);
        return jo;
    }
}

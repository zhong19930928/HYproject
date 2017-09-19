package core;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by suhu on 2017/4/8.
 */

public class ApiRequestMethods {

    /**
     *@method 请求方法
     *@author suhu
     *@time 2017/4/8 15:09
     *
    */
    public static void getData(Context context, String url, int id , int pager, int rows , final ApiRequestFactory.HttpCallBackListener httpCallBackListener, boolean isShowDialog){
        Map<String ,String> map = new HashMap<>();
        map.put("name","18701526994");
        map.put("passwd","123456");
        map.put("uniquecode","sndwgs");
        ApiRequestFactory.postJson(context,url,map,httpCallBackListener,true);
    }


}

package com.example.khanh.melody;

/**
 * Created by Administrator on 1/4/2018.
 */

public final class Link {
     public static final  String PORT = "http://api.melody.vn";
//     http://melody.vn
//http://10.85.75.157
     public static final String ScreenShort(String lat, String lng){
          String url =  "https://maps.googleapis.com/maps/api/staticmap?" +
                  "center="+lat+","+lng+
                  "&zoom=16&scale=1&size=600x300&maptype=roadmap&format=png&visual_refresh=true&markers=size:mid%7Ccolor:0x6a6aff%7Clabel:%7C" +
                  lat+","+lng;
          return url;
     }


}

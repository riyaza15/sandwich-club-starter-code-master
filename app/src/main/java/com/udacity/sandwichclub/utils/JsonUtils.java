package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

// As sugession of last review  I Used string constants instead of hardcoded strings.
    private static final String NAME="name";
    private static final String MAINNA = "mainName";
    private static final String ALSKNAS="alsoKnownAs";
    private static final String PLOFOR="placeOfOrigin";
    private static final String DESC="description";
    private static final String IMG="image";



//Json passing I learned from Android Basic Networking session by udacity
    public static Sandwich parseSandwichJson(String json) {


      try {
          JSONObject root = new JSONObject(json);
          JSONObject name = root.getJSONObject(NAME);
          String mainName = name.getString(MAINNA);
          // This content obtained from stack flow
          // The URL  https://stackoverflow.com/questions/17037340/converting-jsonarray-to-arraylist
          //  https://www.screencast.com/t/oxzjWk5AhW
          List<String> alsKnAsList = new ArrayList<>();
          JSONArray alsKnAs= name.getJSONArray(ALSKNAS);
          for (int i = 0; i < alsKnAs.length(); i++) {
              alsKnAsList.add(alsKnAs.getString(i));
          }
          String plcOfOr= root.getString(PLOFOR);
          String dsctn = root.getString(DESC);

          // This content obtained from stack flow     https://www.screencast.com/t/oxzjWk5AhW
          List<String> ingtsList = new ArrayList<>();
          String ingredients="ingredients";
          JSONArray ingts= root.getJSONArray(ingredients);
          for (int i = 0; i < ingts.length(); i++) {
              ingtsList.add(ingts.getString(i));
          }
          String img = root.getString(IMG);
          Sandwich sandwich= new Sandwich(mainName,alsKnAsList,plcOfOr,dsctn,img,ingtsList);
          return sandwich;
      } catch (JSONException e) {
          e.printStackTrace();
      }
        return null;
    }
}

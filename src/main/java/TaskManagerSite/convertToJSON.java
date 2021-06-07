package TaskManagerSite;

import org.json.simple.JSONArray;

import java.util.ArrayList;

public class convertToJSON {
    public static JSONArray convert(ArrayList arrayList){
        var jsArray = new JSONArray();
        for (int i = 0; i < arrayList.size(); i++){
            jsArray.add(arrayList.get(i));
        }
        System.out.println(jsArray);
        return jsArray;
    }
}

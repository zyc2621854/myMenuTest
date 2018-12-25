package android.kanzz.com.mymenutest.Network;

import android.kanzz.com.mymenutest.Entity.City;
import android.kanzz.com.mymenutest.Entity.County;
import android.kanzz.com.mymenutest.Entity.Province;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

public class Utility {
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                Log.d("ChooseAreaFragment","进入了处理省份JSON数据函数");
                JSONArray allProvinces=new JSONArray(response);
                for(int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject=allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();//使用JSon数组接收后直接存进数据库
                }
                Log.d("ChooseAreaFragment","成功存储省份");
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                Log.d("ChooseAreaFragment","进入了处理城市JSON数据函数");
                JSONArray allCities=new JSONArray(response);
                for(int i=0;i<allCities.length();i++){
                    JSONObject cityObject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                    Log.d("ChooseAreaFragment","城市表加入了"+cityObject.getString("name"));
                }
                Log.d("ChooseAreaFragment","成功存储城市");
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties=new JSONArray(response);
                for(int i=0;i<allCounties.length();i++){
                    JSONObject countyObject=allCounties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                    Log.d("ChooseAreaFragment","乡村表加入了"+countyObject.getString("name"));
                }
                Log.d("ChooseAreaFragment","成功存储城镇");
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void deleteProvinces(){
        DataSupport.deleteAll(Province.class);
    }

    public static void deleteCities(){
        DataSupport.deleteAll(City.class);
    }

    public static void deleteCounties(){
        DataSupport.deleteAll(County.class);
    }
}

package android.kanzz.com.mymenutest.Fragement;

import android.app.ProgressDialog;
import android.content.Context;
import android.kanzz.com.mymenutest.Entity.City;
import android.kanzz.com.mymenutest.Entity.County;
import android.kanzz.com.mymenutest.Entity.Province;
import android.kanzz.com.mymenutest.Network.Utility;
import android.kanzz.com.mymenutest.Network.okHttpUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.kanzz.com.mymenutest.R;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ChooseAreaFragment extends Fragment {
    public static final int LEVEL_PROVINCE=0;
    public static final int LEVEL_CITY=1;
    public static final int LEVEL_COUNTY=2;
    private ProgressDialog progressDialog;
    private TextView titleText;
    private Button backButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList=new ArrayList<>();

    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;
    private Province selectedProvince;
    private City selectedCity;
    private int currentLevel;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_choose_area,container,false);
        titleText=(TextView)view.findViewById(R.id.title_text);
        backButton=(Button)view.findViewById(R.id.back_button_choose_area);
        listView=(ListView)view.findViewById(R.id.list_view_area);
        adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        Log.d("ChooseAreaFragment","加载了适配器");
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentLevel==LEVEL_PROVINCE){
                    Log.d("ChooseAreaFragment","点击了省份正在请求城市");
                    selectedProvince=provinceList.get(position);
                    queryCites();
                }else if(currentLevel==LEVEL_CITY){
                    selectedCity=cityList.get(position);
                    queryCounties();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(currentLevel==LEVEL_COUNTY){
                    queryCites();
                }else if(currentLevel==LEVEL_CITY){
                    queryProvinces();
                }
            }
        });
        Utility.deleteProvinces();
        Utility.deleteCities();
        Utility.deleteCounties();
        queryProvinces();

    }

    private void queryProvinces(){

        titleText.setText("中国");
        backButton.setVisibility(View.GONE);
        provinceList= DataSupport.findAll(Province.class);
        Log.d("ChooseAreaFragment","是否存储了省份");
        if(provinceList.size()>0){
            Log.d("ChooseAreaFragment","已经存储了省份");
            dataList.clear();
            for(Province province:provinceList){
                dataList.add(province.getProvinceName());
                Log.d("ChooseAreaFragment","省份list里存储了"+province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel=LEVEL_PROVINCE;
        }else {
            String address="http://guolin.tech/api/china";
            queryFromServer(address,"province");
            Log.d("ChooseAreaFragment","还没存储省份，去访问服务器");
        }
    }

    private void queryCites(){
        titleText.setText(selectedProvince.getProvinceName());
        backButton.setVisibility(View.VISIBLE);
        cityList= DataSupport.where("provinceid=?",String.valueOf(selectedProvince.getId())).find(City.class);
        Log.d("ChooseAreaFragment","是否存储了城市");
        if(cityList.size()>0){
            Log.d("ChooseAreaFragment","已经储了城市");
            dataList.clear();
            for(City city:cityList){
                dataList.add(city.getCityName());
                Log.d("ChooseAreaFragment","城市list存储了"+city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(1);
            currentLevel=LEVEL_CITY;
        }else {

            int provinceCode=selectedProvince.getProvinceCode();
            String address="http://guolin.tech/api/china/"+provinceCode;
            Log.d("ChooseAreaFragment","还没存储城市，准备去服务器访问http://guolin.tech/api/china/"+provinceCode);
            queryFromServer(address,"city");
        }
    }

    private void queryCounties(){
        titleText.setText(selectedCity.getCityName());
        backButton.setVisibility(View.VISIBLE);
        countyList= DataSupport.where("cityid=?",String.valueOf(selectedCity.getId())).find(County.class);
        Log.d("ChooseAreaFragment","是否存储了乡村");
        if(countyList.size()>0){
            Log.d("ChooseAreaFragment","已经储了乡村");
            dataList.clear();
            for(County county:countyList){
                dataList.add(county.getCountyName());
                Log.d("ChooseAreaFragment","乡村list存储了"+county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(2);
            currentLevel=LEVEL_COUNTY;
        }else {
            int provinceCode=selectedProvince.getProvinceCode();
            int cityCode=selectedCity.getCityCode();
            String address="http://guolin.tech/api/china/"+provinceCode+"/"+cityCode;
            Log.d("ChooseAreaFragment","还没存储乡村，准备去服务器访问http://guolin.tech/api/china/"+provinceCode+"/"+cityCode);
            queryFromServer(address,"county");
        }
    }

    private void queryFromServer(String address,final String type){
        showProgressDialog();
        okHttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("ChooseAreaFragment","访问失败了");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("ChooseAreaFragment","访问url成功了");
                String responseText=response.body().string();
                boolean result=false;
                if("province".equals(type)){
                    Log.d("ChooseAreaFragment","正在去解析访问的province返回值");
                    result= Utility.handleProvinceResponse(responseText);//查询后response被解析并存入数据库
                }else if("city".equals(type)){
                    Log.d("ChooseAreaFragment","正在去解析访问的city返回值");
                    result=Utility.handleCityResponse(responseText,selectedProvince.getId());
                    Log.d("ChooseAreaFragment","result获得是否成功存储的信息");
                }else if("county".equals(type)){
                    Log.d("ChooseAreaFragment","正在去解析访问的county返回值");
                    result=Utility.handleCountyResponse(responseText,selectedCity.getId());
                    Log.d("ChooseAreaFragment","result获得是否成功存储的信息");
                }
                if (result){
                    Log.d("ChooseAreaFragment","result成功了");
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            closeProgressDialog();
                            Log.d("ChooseAreaFragment","开启了线程");
                            if ("province".equals(type)){
                                Log.d("ChooseAreaFragment","访问了省份");
                                queryProvinces();
                            }else if("city".equals(type)){
                                Log.d("ChooseAreaFragment","访问了城市");
                                queryCites();
                            }else if("county".equals(type)){
                                Log.d("ChooseAreaFragment","访问了城镇");
                                queryCounties();
                            }
                        }
                    });
                }
            }
        });
    }

    private void showProgressDialog(){
        if (progressDialog==null){
            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }
    private void closeProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }
}

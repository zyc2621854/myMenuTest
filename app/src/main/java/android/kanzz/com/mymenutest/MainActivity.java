package android.kanzz.com.mymenutest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.kanzz.com.mymenutest.Entity.User;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;
import java.util.List;



public class MainActivity extends BaseActivity implements View.OnClickListener{

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button loginButton;
    private TextView registerText;
    private boolean loginCheck=false;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    private String account;
    private String password;
    private boolean isRemember;



    private  void initView(){
        accountEdit=(EditText)findViewById(R.id.edit_account);
        passwordEdit=(EditText)findViewById(R.id.edit_password);
        loginButton=(Button)findViewById(R.id.button_login);
        loginButton.setOnClickListener(this);
        registerText=(TextView)findViewById(R.id.login_text_register);
        registerText.setOnClickListener(this);
        rememberPass=(CheckBox)findViewById(R.id.checkbox_remember);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                List<User> users= DataSupport.findAll(User.class);
                account=accountEdit.getText().toString();
                password=passwordEdit.getText().toString();
                for(User u:users){
//                    Log.d("MainActivity","account is "+u.getAccount());
//                    Log.d("MainActivity","password is "+u.getPassword());
                    if(account.equals(u.getAccount())&&
                            password.equals(u.getPassword())){
                        editor=pref.edit();
                        if(rememberPass.isChecked()){
//                            editor.putBoolean("remember_password",true);
                            Log.d("MainActivity","isChecked()=true");
                            editor.putString("account",account);
                            Log.d("MainActivity","account="+account);
                            editor.putString("password",password);
                            Log.d("MainActivity","password="+password);
                        }else {
                            editor.clear();
                        }
                        editor.apply();
                        loginCheck=true;


                        Intent intent=new Intent(MainActivity.this,ChooseActivity.class);
                        startActivity(intent);
                        finish();
                        Log.d("MainActivity","MainActivity finish()");
                    }
                }
                if(loginCheck==false)
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_text_register:
                Intent intent1=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent1);
        }
    }

    public String test(boolean a){
        if(a==true) return "isRemember=TRUE";
        else if(a==false)return  "isRemember=FALSE";
        else return "isRemember=空";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Log.d("MainActivity","MainActivity onCreate()");

        pref= PreferenceManager.getDefaultSharedPreferences(this);
//        Log.d("MainActivity",test(isRemember));
//        isRemember=pref.getBoolean("remember_password",false);
//        Log.d("MainActivity",test(isRemember));
//        if(isRemember){
            account=pref.getString("account","");
            Log.d("MainActivity","account="+account);
            password=pref.getString("password","");
            Log.d("MainActivity","password="+password);
            accountEdit.setText(account);
            passwordEdit.setText(password);
            if(account!=""){
                rememberPass.setChecked(true);
            }

//        }

        Connector.getDatabase();
        final User user=new User();
        user.setAccount("abc");
        user.setPassword("123");
        user.save();
    }

}

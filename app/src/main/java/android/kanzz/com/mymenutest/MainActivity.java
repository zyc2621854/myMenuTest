package android.kanzz.com.mymenutest;

import android.content.Intent;
import android.kanzz.com.mymenutest.Entity.User;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;
import java.util.List;



public class MainActivity extends BaseActivity{

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button loginButton;
    private boolean loginCheck=false;


    private  void initView(){
        accountEdit=(EditText)findViewById(R.id.edit_account);
        passwordEdit=(EditText)findViewById(R.id.edit_password);
        loginButton=(Button)findViewById(R.id.button_login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Connector.getDatabase();
        final User user=new User();
        user.setAccount("abc");
        user.setPassword("123");
        user.save();


        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                List<User> users= DataSupport.findAll(User.class);
                for(User u:users){
//                    Log.d("MainActivity","account is "+u.getAccount());
//                    Log.d("MainActivity","password is "+u.getPassword());
                    if(accountEdit.getText().toString().equals(u.getAccount())&&
                            passwordEdit.getText().toString().equals(u.getPassword())){
                        Intent intent=new Intent(MainActivity.this,ChooseActivity.class);
                        startActivity(intent);
                        loginCheck=true;
                    }
                }
                if(loginCheck==false)
                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

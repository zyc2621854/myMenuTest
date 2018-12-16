package android.kanzz.com.mymenutest;

import android.kanzz.com.mymenutest.Entity.User2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText usernameEdit;
    private EditText passwordEdit1;
    private EditText passwordEdit2;
    private EditText phoneNumEdit;
    private EditText emailEdit;
    private Button registerButton;
    private void initView(){
        usernameEdit=(EditText)findViewById(R.id.register_et_username);
        passwordEdit1=(EditText)findViewById(R.id.register_et_password_1);
        passwordEdit2=(EditText)findViewById(R.id.register_et_password_2);
        phoneNumEdit=(EditText)findViewById(R.id.register_et_phoneNumber);
        emailEdit=(EditText)findViewById(R.id.register_et_email);
        registerButton=(Button)findViewById(R.id.register_button_1);
        registerButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.register_button_1:
                User2 user=new User2();
                user.setAccount(usernameEdit.getText().toString());
                user.setPassword(passwordEdit1.getText().toString());
                user.setMobilePhoneNumber(phoneNumEdit.getText().toString());
                user.setEmail(emailEdit.getText().toString());
                user.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if(e==null){
                            ShowToast("添加数据成功，返回objectId为"+objectId);
                        }else{
                            ShowToast("创建数据失败："+e.getMessage());
                        }
                    }
                });
                break;
                default:break;
        }
    }

}

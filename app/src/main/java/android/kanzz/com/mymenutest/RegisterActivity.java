package android.kanzz.com.mymenutest;

import android.kanzz.com.mymenutest.Entity.User2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText usernameEdit;
    private EditText passwordEdit1;
    private EditText passwordEdit2;
    private EditText phoneNumEdit;
    private EditText emailEdit;
    private Button registerButton;
    private RadioGroup sexRadioGroup;
    private RadioButton sexRadioButton1;
    private RadioButton sexRadioButton2;
    private CheckBox allowCheckBox;
    private Boolean isAllowRegister;
    private Boolean isMale;


    private void initView(){
        usernameEdit=(EditText)findViewById(R.id.register_et_username);
        passwordEdit1=(EditText)findViewById(R.id.register_et_password_1);
        passwordEdit2=(EditText)findViewById(R.id.register_et_password_2);
        phoneNumEdit=(EditText)findViewById(R.id.register_et_phoneNumber);
        emailEdit=(EditText)findViewById(R.id.register_et_email);
        registerButton=(Button)findViewById(R.id.register_button_1);
        registerButton.setOnClickListener(this);
        sexRadioGroup=(RadioGroup)findViewById(R.id.sex_radioGroup);
        sexRadioButton1=(RadioButton)findViewById(R.id.male_radioButton);
        sexRadioButton2=(RadioButton)findViewById(R.id.female_radioButton);
        allowCheckBox=(CheckBox)findViewById(R.id.allow_checkBox);
        isAllowRegister=false;

        sexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == sexRadioButton1.getId()){
                    isMale=true;
                }else if(checkedId == sexRadioButton2.getId()){
                    isMale=false;
                }
            }
        });

        allowCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                    isAllowRegister=true;
            }
        });

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
                if(isAllowRegister){
                User2 user=new User2();
                user.setAccount(usernameEdit.getText().toString());
                user.setPassword(passwordEdit1.getText().toString());
                user.setMobilePhoneNumber(phoneNumEdit.getText().toString());
                user.setEmail(emailEdit.getText().toString());
                user.setSex(isMale);
                user.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if(e==null){
                            ShowToast("添加数据成功，返回objectId为"+objectId);
                        }else{
                            ShowToast("创建数据失败："+e.getMessage());
                        }
                    }
                });}
                else ShowToast("您尚未同意条款");
                break;
                default:break;
        }
    }

}

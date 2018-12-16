package android.kanzz.com.mymenutest.Entity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;

public class User2 extends BmobObject{
    private String account;
    private String password;
    private String mobilePhoneNumber;
    private String email;
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

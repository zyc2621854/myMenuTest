package android.kanzz.com.mymenutest.Entity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;

public class User2 extends BmobObject{
    private String account;
    private String password;

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
}

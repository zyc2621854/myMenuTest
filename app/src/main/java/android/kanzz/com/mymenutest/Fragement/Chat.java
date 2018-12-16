package android.kanzz.com.mymenutest.Fragement;

import android.content.Context;
import android.kanzz.com.mymenutest.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Chat extends Fragment {
    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        if(mView==null){
            mView=inflater.inflate(R.layout.fragment_chat,null);
        }
        return mView;
    }
}

package android.kanzz.com.mymenutest.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.kanzz.com.mymenutest.R;
import android.os.Build;
import android.support.v7.widget.TintTypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NumberAddSubView extends RelativeLayout implements View.OnClickListener {
    private int maxValue;
    private int minValue;
    private int value;

    private Button btn_add;
    private Button btn_sub;
    private EditText et_num;

    public NumberAddSubView (Context context) {                                                             //嵌套构造
        this(context,null);
    }

    public NumberAddSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("RestrictedApi")
    public NumberAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
        //获取自定义控件在layout中设置的自定义属性的值
        TintTypedArray a=TintTypedArray.obtainStyledAttributes(context,attrs, R.styleable.NumberAddSubView,defStyleAttr,0);
        int val = a.getInt(R.styleable.NumberAddSubView_value, 1);
        setValue(val);
        int maxVal = a.getInt(R.styleable.NumberAddSubView_maxValue, 1);
        setMaxValue(maxVal);
        int minVal = a.getInt(R.styleable.NumberAddSubView_minValue, 1);
        setMinValue(minVal);
//        Drawable btnBg=a.getDrawable(R.styleable.NumberAddSubView_btnDrawable);
//        if(btnBg!=null){
//            btn_add.setBackgroundDrawable(btnBg);
//            btn_sub.setBackgroundDrawable(btnBg);
//        }
        a.recycle();
    }

    private void initView(Context context){
        View view=LayoutInflater.from(context).inflate(R.layout.number_add_sub_view,this,true);
        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_sub = (Button) view.findViewById(R.id.btn_sub);
        et_num = (EditText) view.findViewById(R.id.et_num);

        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_add:
                if(mListener!=null){                    //检查代码是否setonlistener了
                    numAdd();
                mListener.onButtonAddClick(view,value);}
            case R.id.btn_sub:
                if(mListener!=null){
                    numSub();
                    mListener.onButtonSubClick(view,value);
                }
        }
    }

    public interface OnButtonClickListener{
        public void onButtonAddClick(View view,int value);
        public void onButtonSubClick(View view,int value);
    }

    private OnButtonClickListener mListener;

    public void setOnButtonClickListener(OnButtonClickListener listener){
        this.mListener=listener;
    }

    private void numAdd(){
        getValue();
        if(this.value<=this.maxValue)
            this.value++;
        et_num.setText(this.value);
    }

    private void numSub(){
        getValue();
        if(this.value>=this.minValue)
            this.value--;
        et_num.setText(this.value);
    }

    private void setValue(int value){
        et_num.setText(value);
        this.value=value;
    }

    private int getValue(){
        this.value=Integer.parseInt(et_num.getText().toString());
        return this.value;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }


}
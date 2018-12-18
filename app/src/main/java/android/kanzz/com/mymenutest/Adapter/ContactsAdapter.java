package android.kanzz.com.mymenutest.Adapter;

import android.content.Context;
import android.kanzz.com.mymenutest.Entity.Contacts;
import android.kanzz.com.mymenutest.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter{
    private int resourceId;
    public ContactsAdapter(Context context, int textViewResourceId, List<Contacts> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        Contacts contacts=(Contacts)getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.contactsImage=(ImageView)view.findViewById(R.id.contacts_image);
            viewHolder.contactsName=(TextView)view.findViewById(R.id.contacts_name);
            viewHolder.contactsMessage=(TextView)view.findViewById(R.id.contacts_message);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.contactsImage.setImageResource(contacts.getImageId());
        viewHolder.contactsName.setText(contacts.getName());
        viewHolder.contactsMessage.setText(contacts.getMessage());
        return view;
    }

    class ViewHolder{
        ImageView contactsImage;
        TextView contactsName;
        TextView contactsMessage;
    }
}

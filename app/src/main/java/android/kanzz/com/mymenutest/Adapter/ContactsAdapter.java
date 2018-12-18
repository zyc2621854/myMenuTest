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
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView contactsImage=(ImageView) view.findViewById(R.id.contacts_image);
        TextView contactsName=(TextView) view.findViewById(R.id.contacts_name);
        TextView contactsMessage=(TextView) view.findViewById(R.id.contacts_message);
        contactsImage.setImageResource(contacts.getImageId());
        contactsName.setText(contacts.getName());
        contactsMessage.setText(contacts.getMessage());
        return view;
    }
}

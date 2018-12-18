package android.kanzz.com.mymenutest.Adapter;

import android.kanzz.com.mymenutest.Entity.Contacts;
import android.kanzz.com.mymenutest.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter2 extends RecyclerView.Adapter<ContactsAdapter2.ViewHolder>{
    private List<Contacts> mContactsList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView contactsImage;
        TextView contactsName;
        TextView contactsMessage;

        public ViewHolder(View view){
            super(view);
            contactsImage=(ImageView)view.findViewById(R.id.contacts_image);
            contactsName=(TextView)view.findViewById(R.id.contacts_name);
            contactsMessage=(TextView)view.findViewById(R.id.contacts_message);
        }
    }

    public ContactsAdapter2(List<Contacts> contactslist){
        mContactsList=contactslist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Contacts contacts=mContactsList.get(position);
        holder.contactsImage.setImageResource(contacts.getImageId());
        holder.contactsName.setText(contacts.getName());
        holder.contactsMessage.setText(contacts.getMessage());
    }

    @Override
    public int getItemCount(){
        return mContactsList.size();
    }
}

package com.example8.mbatara.demo_1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
/**
 * Created by mbatara on 18/01/2018.
 */

public class AdapterList extends BaseAdapter
{
    private LayoutInflater inflater;
    private Context context;
    private ViewHolder holder;
    public List <SetterGeter> list;

    public AdapterList(Context c, List<SetterGeter> setter_geter)
    {
        this.context=c;
        this.list=setter_geter;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    private class ViewHolder
    {
        TextView val_col_1,val_col_2,val_col_3;
    }

    @SuppressWarnings("static-access")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final SetterGeter valueList = list.get(position);
        holder = new ViewHolder();

        if (convertView==null) {
            inflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);

            convertView=inflater.inflate(R.layout.adapter_list,null);

            holder.val_col_1= (TextView) convertView.findViewById(R.id.txt_v_col_1);
            holder.val_col_2= (TextView) convertView.findViewById(R.id.txt_v_col_2);
            holder.val_col_3= (TextView) convertView.findViewById(R.id.txt_v_col_3);

            convertView.setTag(holder);
        } else {
            holder=(ViewHolder) convertView.getTag();
        }

        if (valueList != null) {
            holder.val_col_1.setText(valueList.getColumn_1());
            holder.val_col_2.setText(valueList.getColumn_2());
            holder.val_col_3.setText(valueList.getColumn_3());
        }
        return convertView;
    }

}
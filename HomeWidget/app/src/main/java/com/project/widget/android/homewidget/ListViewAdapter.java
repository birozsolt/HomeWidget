package com.project.widget.android.homewidget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zsolt on 2016. 12. 18..
 */

public class ListViewAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private ArrayList<DictionaryItem> originalData;
    private ArrayList<DictionaryItem> filteredData;
    private Spinner spinner;
    Activity activity;
    ImageView flag;
    ImageView flag2;
    DictionaryHolder holder;
    String language;

    public ListViewAdapter(Context context, ArrayList<DictionaryItem> arraylist) {
        super();
        this.context = context;
        originalData = arraylist;

    }

    public int getCount() {
        return originalData.size();
    }

    public DictionaryItem getItem(int position) {
        return originalData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.list_view_item, parent, false);
            holder = new DictionaryHolder();
            flag = (ImageView) convertView.findViewById(R.id.flag);
            flag2 = (ImageView) convertView.findViewById(R.id.flag2);
            if (language.equals("Hungarian")) {
                holder.magyar = (TextView) convertView.findViewById(R.id.word1);
                holder.angol = (TextView) convertView.findViewById(R.id.word2);
                holder.roman = (TextView) convertView.findViewById(R.id.word3);
                flag.setImageResource(R.drawable.eng);
                flag2.setImageResource(R.drawable.rom);
            }
            else if (language.equals("English")){
                holder.magyar = (TextView) convertView.findViewById(R.id.word2);
                holder.angol = (TextView) convertView.findViewById(R.id.word1);
                holder.roman = (TextView) convertView.findViewById(R.id.word3);
                flag.setImageResource(R.drawable.hun);
                flag2.setImageResource(R.drawable.rom);
            }
            else if (language.equals("Romanian")){
                holder.magyar = (TextView) convertView.findViewById(R.id.word2);
                holder.angol = (TextView) convertView.findViewById(R.id.word3);
                holder.roman = (TextView) convertView.findViewById(R.id.word1);
                flag.setImageResource(R.drawable.hun);
                flag2.setImageResource(R.drawable.eng);
            }


            convertView.setTag(holder);

        } else {
            holder = (DictionaryHolder) convertView.getTag();
        }
        holder.magyar.setText(originalData.get(position).getMagyar());
        holder.angol.setText(originalData.get(position).getAngol());
        holder.roman.setText(originalData.get(position).getRoman());
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String filterString = constraint.toString().toLowerCase();
                FilterResults results = new FilterResults();
                ArrayList<DictionaryItem> filteredList = new ArrayList<>();

                if (filteredData == null) {
                    filteredData = originalData;
                }
                if (filterString != null) {
                    if (filteredData != null && filteredData.size() > 0) {
                        for (final DictionaryItem item : filteredData) {
                            switch (language){
                                case "Hungarian":
                                    if (item.getMagyar().toLowerCase().contains(filterString)) {
                                        filteredList.add(item);
                                    }
                                    break;
                                case "English":
                                    if (item.getAngol().toLowerCase().contains(filterString)) {
                                        filteredList.add(item);
                                    }
                                    break;
                                case "Romanian":
                                    if (item.getRoman().toLowerCase().contains(filterString)) {
                                        filteredList.add(item);
                                    }
                                    break;
                                default: break;
                            }

                        }
                    }
                    results.values = filteredList;
                }
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                originalData = (ArrayList<DictionaryItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class DictionaryHolder {
        TextView magyar;
        TextView angol;
        TextView roman;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

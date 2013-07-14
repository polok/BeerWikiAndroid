package com.polak.beer.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.polak.beer.R;
import com.polak.beer.entity.Beer;

public class BeerAdapter extends BaseAdapter {

	private List<Beer> beers = new ArrayList<Beer>();
	private LayoutInflater inflater;

	public BeerAdapter(Context context) {
		 this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return beers.size();
	}

	@Override
	public Beer getItem(int position) {
		return beers.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BeerViewHolder viewHolder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.l_item_beer, parent, false);

			viewHolder = new BeerViewHolder();
			viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_beer_name);
			viewHolder.tvAvg = (TextView) convertView.findViewById(R.id.tv_beer_avg);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (BeerViewHolder) convertView.getTag();
		}

		Beer beer = beers.get(position);
		if (beer != null) {
			viewHolder.tvName.setText(beer.getName());
			viewHolder.tvAvg.setText(String.valueOf(beer.getAbv()));
		}
		return convertView;
	}

	public void updateDataEntries(List<Beer> beers) {
		this.beers = beers;
		notifyDataSetChanged();
	}

    public void clearDataEntries() {
        this.beers.clear();
        notifyDataSetChanged();
    }

	static class BeerViewHolder {
		TextView tvName;
		TextView tvAvg;
	}
	

}

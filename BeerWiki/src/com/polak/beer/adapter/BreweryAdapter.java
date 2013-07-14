package com.polak.beer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.polak.beer.R;
import com.polak.beer.entity.Brewery;

import java.util.ArrayList;
import java.util.List;

public class BreweryAdapter extends BaseAdapter {

	private List<Brewery> breweries = new ArrayList<Brewery>();
	private LayoutInflater inflater;

	public BreweryAdapter(Context context) {
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return breweries.size();
	}

	@Override
	public Brewery getItem(int position) {
		return breweries.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BreweryViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.l_item_brewery, parent, false);

			viewHolder = new BreweryViewHolder();
			viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_brewery_name);
			viewHolder.tvUrl = (TextView) convertView.findViewById(R.id.tv_brewery_url);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (BreweryViewHolder) convertView.getTag();
		}

		Brewery brewery = breweries.get(position);
		if (brewery != null) {
			viewHolder.tvName.setText(brewery.getName());
			viewHolder.tvUrl.setText(brewery.getUrl());
		}
		return convertView;
	}

	public void updateDataEntries(List<Brewery> breweries) {
		this.breweries = breweries;
		notifyDataSetChanged();
	}

	static class BreweryViewHolder {
		TextView tvName;
		TextView tvUrl;
	}

}

package com.polak.beer.adapter;

import com.polak.beer.entity.Beer;

import java.util.List;

/**
 * User: marcin
 * Date: 14.07.13
 * Time: 14:56
 */
public interface OnAdapterDataChanged<T> {

    public void updateDataEntries(List<T> data);

    public void clearDataEntries();

}

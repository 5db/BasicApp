package com.structure.appstructure;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by jsingh on 2014-03-26.
 */
public class HeadlinesFragment extends ListFragment {

    OnHeadlineSelectedListener mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout = android.R.layout.simple_list_item_activated_1;
        int datastore = getArguments().getInt("MENUITEM");

        switch (datastore) {
            case 1:
                setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Content.fragment_headlines));
                break;
            case 2:
                setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Content.menu_headlines));
                break;
            default:
                setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Content.headlines));
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if(getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnHeadLineSelectedInterface");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallback.onArticleSelected(position);
        getListView().setItemChecked(position, true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }
}

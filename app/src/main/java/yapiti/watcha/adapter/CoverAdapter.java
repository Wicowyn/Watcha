package yapiti.watcha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;

import yapiti.watcha.entity.Movie;
import yapiti.watcha.view.CoverView;

/**
 * Created by yapiti on 31/01/15.
 */
public class CoverAdapter extends RecyclerView.Adapter {
    private ArrayList<Movie> list=new ArrayList<>();
    private Context context;


    public CoverAdapter(Context context){
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CoverHolder(new CoverView(context));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((CoverView) viewHolder.itemView).update(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(Movie object) {
        list.add(object);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends Movie> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }

    public Movie get(int index) {
        return list.get(index);
    }

    public class CoverHolder extends RecyclerView.ViewHolder {

        public CoverHolder(View itemView) {
            super(itemView);
        }

    }
}

package yapiti.watcha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import yapiti.watcha.view.SeanceView;

/**
 * Created by yapiti on 31/01/15.
 */
public class SeanceAdapter extends RecyclerView.Adapter {
    private ArrayList<SeanceView.Holder> list=new ArrayList<>();
    private Context context;


    public SeanceAdapter(Context context){
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SeanceView seanceView=new SeanceView(context);

        return new SeanceHolder(seanceView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SeanceView) holder.itemView).update(get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public boolean add(SeanceView.Holder object) {
        boolean result=list.add(object);

        notifyDataSetChanged();

        return result;
    }

    public boolean addAll(Collection<? extends SeanceView.Holder> collection) {
        boolean result=list.addAll(collection);

        notifyDataSetChanged();

        return result;
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public SeanceView.Holder get(int index) {
        return list.get(index);
    }

    public void sort(Comparator<SeanceView.Holder> comparator){
        Collections.sort(list, comparator);
        notifyDataSetChanged();
    }

    public class SeanceHolder extends RecyclerView.ViewHolder {

        public SeanceHolder(View itemView) {
            super(itemView);
        }

    }
}

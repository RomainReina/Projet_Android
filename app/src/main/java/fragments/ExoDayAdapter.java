package fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_android.R;

import java.util.ArrayList;
import java.util.List;

import classes.Day;
import classes.Exercice;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class ExoDayAdapter extends RecyclerView.Adapter<ExoDayAdapter.ViewHolder>{

    public List<Day> mDays=new ArrayList<>();
    public ArrayList<Exercice> mExos=new ArrayList<>();
    private int mNumDay;

    public ExoDayAdapter(List<Day> days, int numDay) {
        mDays=days;
        mNumDay=numDay;
        mExos.addAll(mDays.get(numDay).getExos());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercice_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(String.valueOf(mExos.get(position).getId()));
        holder.sets.setText(String.valueOf(mExos.get(position).getSets()));
    }


    @Override
    public int getItemCount() {
        return mExos.size();
    }


    public interface RecyclerViewClickListener
    {
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;
        public final TextView sets;
        public final Button exoShow;
        public final WebView webView;
        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.exoDayName);
            sets = view.findViewById(R.id.setsNumber);
            exoShow = view.findViewById(R.id.viewExo);
            webView = view.findViewById(R.id.dayExoVimeo);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }


    }



    /*private Exercice recupExoById(int id){
        for(int i=0;i<mExos.size();i++){
            if(mExos.get(i).getId()==id){
                return mExos.get(i);
            }
        }
        return new Exercice(0,"null","null");
    }*/


}
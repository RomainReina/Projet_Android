package fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import classes.Exercice;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class ExerciceAdapter extends RecyclerView.Adapter<ExerciceAdapter.ViewHolder> implements Filterable {

    private String mUrl;
    public ArrayList<Exercice> copyExos;
    public ArrayList<Exercice> mExos;
    private RecyclerViewClickListener mListener;

    public ExerciceAdapter(String url, RecyclerViewClickListener listener, ArrayList<Exercice> exos) {

        mUrl = url;
        mExos=exos;
        copyExos=exos;
        mListener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(com.example.projet_android.R.layout.exercice_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mExos.get(position).getName());
    }



    @Override
    public int getItemCount() {
        return mExos.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Exercice> filteredList = new ArrayList<>();
            if (constraint ==null || constraint.length() == 0)
            {
                filteredList.addAll(copyExos);
            }
            else
            {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Exercice exo : copyExos)
                {
                    if (exo.getName().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(exo);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mExos.clear();
            mExos.addAll((List)results.values);
            notifyDataSetChanged();


        }
    };

    public interface RecyclerViewClickListener
    {
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView name;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(com.example.projet_android.R.id.exoName);
            view.setOnClickListener(this);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v,getAdapterPosition());
        }
    }

}
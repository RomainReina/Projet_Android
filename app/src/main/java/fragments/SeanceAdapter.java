package fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.lang.reflect.Type;
import java.util.ArrayList;

import classes.Seance;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class SeanceAdapter extends RecyclerView.Adapter<SeanceAdapter.ViewHolder>{ //Même principe que pour l'adapter "Exercice"

    private String mUrl;
    public ArrayList<Seance> mSeances;
    private RecyclerViewClickListener mListener;

    public SeanceAdapter(String url, RecyclerViewClickListener listener,ArrayList<Seance> seances) {

        mUrl = url;
        mListener = listener;
        mSeances=seances;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(com.example.projet_android.R.layout.exercice_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mSeances.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return mSeances.size();
    }


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
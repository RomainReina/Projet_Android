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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class SeanceAdapter extends RecyclerView.Adapter<SeanceAdapter.ViewHolder>{

    private String mUrl;
    public ArrayList<String> mSeances=new ArrayList<>();
    private Context mContext;
    private RecyclerViewClickListener mListener;

    public SeanceAdapter(String url, Context context, RecyclerViewClickListener listener) {

        mUrl = url;
        mContext = context;
        mListener = listener;
        recupSeances(url);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(com.example.projet_android.R.layout.exercice_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mSeances.get(position));
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

    private void recupSeances(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            for (int i = 0; i < response.getJSONArray("plans").length(); i++) {
                                mSeances.add(response.getJSONArray("plans").getJSONObject(i).getString("name"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response", error.toString());
                    }
                });

        requestQueue.add(objectRequest);
    }

}
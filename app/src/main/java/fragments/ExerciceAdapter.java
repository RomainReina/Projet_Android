package fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.projet_android.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.R;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class ExerciceAdapter extends RecyclerView.Adapter<ExerciceAdapter.ViewHolder> implements Filterable {

    private String mUrl;
    public ArrayList<String> copyExosName=new ArrayList<>();
    public ArrayList<String> mExosName = new ArrayList<String>();
    public ArrayList<String> mExosUrl = new ArrayList<String>();
    private Context mContext;
    private RecyclerViewClickListener mListener;

    public ExerciceAdapter(String url, Context context, RecyclerViewClickListener listener) {

        mUrl = url;
        mContext=context;
        mListener=listener;
        recupExos(url);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(com.example.projet_android.R.layout.exercice_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mExosName.get(position));
    }



    @Override
    public int getItemCount() {
        return mExosName.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();
            if (constraint ==null || constraint.length() == 0)
            {
                filteredList.addAll(copyExosName);
            }
            else
            {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (String exo : copyExosName)
                {
                    if (exo.toLowerCase().contains(filterPattern))
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
            mExosName.clear();
            mExosName.addAll((List)results.values);
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
            name = (TextView) view.findViewById(com.example.projet_android.R.id.exoName);
            view.setOnClickListener((View.OnClickListener) this);

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

    private void recupExos(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            for (int i = 0; i < response.getJSONArray("exercises").length(); i++) {
                                mExosName.add(response.getJSONArray("exercises").getJSONObject(i).getString("name"));
                                copyExosName.add(response.getJSONArray("exercises").getJSONObject(i).getString("name"));
                                mExosUrl.add(response.getJSONArray("exercises").getJSONObject(i).getString("video"));
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
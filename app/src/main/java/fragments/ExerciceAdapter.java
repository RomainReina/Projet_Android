package fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ExerciceAdapter extends RecyclerView.Adapter<ExerciceAdapter.ViewHolder> {

    private String mUrl;
    private ArrayList<String> mExos = new ArrayList<String>();
    private Context mContext;

    public ExerciceAdapter(String url, Context context) {

        mUrl = url;
        mContext=context;
        recupExos(url);
        Log.i("tesst", String.valueOf(mExos));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(com.example.projet_android.R.layout.exercice_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mExos.get(position));
    }



    @Override
    public int getItemCount() {
        return mExos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(com.example.projet_android.R.id.exoName);
            /*view.setOnClickListener((v)-> //à chaque fois qu'on va cliquer sur le layout, la méthode onViewTweet va être appelée
                {
                    mContext.startActivity();
                }
            );*/

        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
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
                                mExos.add(response.getJSONArray("exercises").getJSONObject(i).getString("name"));
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
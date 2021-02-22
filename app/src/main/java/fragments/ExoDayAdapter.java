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
import com.example.projet_android.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import classes.Day;
import classes.Exercice;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class ExoDayAdapter extends RecyclerView.Adapter<ExoDayAdapter.ViewHolder>{

    private String mUrl;
    public List<Day> mDays=new ArrayList<>();
    public ArrayList<Exercice> mExos=new ArrayList<>();
    private int mSeanceId;
    private Context mContext;
    private RecyclerViewClickListener mListener;

    public ExoDayAdapter(String url, Context context, RecyclerViewClickListener listener, int seanceId) {

        mUrl = url;
        mContext = context;
        mListener = listener;
        mSeanceId=seanceId;
        recupDays(url);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercice_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText("Day "+String.valueOf(mDays.get(position).getId()+1));
    }


    @Override
    public int getItemCount() {
        return mDays.size();
    }


    public interface RecyclerViewClickListener
    {
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.exoName);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }


    }

    private void recupDays(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        Gson gson = new Gson();
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Type mDaysType = new TypeToken<ArrayList<Day>>() {}.getType();
                            mDays=gson.fromJson(String.valueOf(response.getJSONArray("plans").getJSONObject(mSeanceId).getJSONArray("days")), mDaysType);
                        }
                        catch (JSONException e) {
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

    private Exercice recupExoById(int id){
        for(int i=0;i<mExos.size();i++){
            if(mExos.get(i).getId()==id){
                return mExos.get(i);
            }
        }
        return new Exercice(0,"null","null");
    }

    /*private List<Day> recupDays(String seance){

    }*/

}
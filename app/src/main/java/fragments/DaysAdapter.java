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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import classes.Day;
import classes.Exercice;
import classes.Seance;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder>{

    private String mUrl;
    public List<Day> mDays=new ArrayList<>();
    public ArrayList<Exercice> mExos=new ArrayList<>();
    private String mSeance;
    private Context mContext;
    private RecyclerViewClickListener mListener;

    public DaysAdapter(String url, Context context, RecyclerViewClickListener listener,String seance) {

        mUrl = url;
        mContext = context;
        mListener = listener;
        mSeance=seance;
        recupSeances(url);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercice_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(String.valueOf(mDays.get(position).getmId()));
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
                            for (int j=0;j<response.getJSONArray("exercises").length();j++){
                                JSONObject obj=response.getJSONArray("exercises").getJSONObject(j);
                                mExos.add(new Exercice(obj.getInt("id"),obj.getString("name"),obj.getString("video")));
                            }
                            for (int i = 0; i < response.getJSONArray("plans").length(); i++) {
                                Log.i("days", String.valueOf(mDays));
                                if (response.getJSONArray("plans").getJSONObject(i).getString("name").equals(mSeance)) {
                                    for (int j = 0; j < response.getJSONArray("plans").getJSONObject(i).getJSONArray("days").length(); j++) {
                                        List<Exercice> mExosDay=new ArrayList<>();
                                        List<Integer> mSets=new ArrayList<>();
                                        for (int k = 0; k < response.getJSONArray("plans").getJSONObject(i).getJSONArray("days").getJSONObject(j).getJSONArray("exercises").length(); k++) {
                                            JSONObject jsonExo = response.getJSONArray("plans").getJSONObject(i).getJSONArray("days").getJSONObject(j).getJSONArray("exercises").getJSONObject(k);
                                            mExosDay.add(recupExoById(jsonExo.getInt("id")));
                                            mSets.add(jsonExo.getInt("sets"));
                                        }
                                        mDays.add(new Day(j,mExosDay,mSets));
                                    }
                                }
                            }
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
            if(mExos.get(i).getmId()==id){
                return mExos.get(i);
            }
        }
        return new Exercice(0,"null","null");
    }

    /*private List<Day> recupDays(String seance){

    }*/

}
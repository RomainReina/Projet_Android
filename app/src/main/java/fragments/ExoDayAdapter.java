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

    private List<Day> mDays=new ArrayList<>();
    private ArrayList<Exercice> mExos=new ArrayList<>();
    private ArrayList<Exercice> copyExos=new ArrayList<>();
    private int mNumDay;
    private Context mContext;
    public ExoDayAdapter(List<Day> days, int numDay,Context context, String url,ArrayList<Exercice> exos) {
        mDays=days;
        mNumDay=numDay;
        mContext=context;
        mExos.addAll(mDays.get(numDay).getExos());
        copyExos=exos;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercice_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(copyExos.get(position).getName());
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




}
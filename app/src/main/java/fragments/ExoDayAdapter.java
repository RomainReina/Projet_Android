package fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
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

    private List<Day> mDays;
    private ArrayList<Exercice> mExos=new ArrayList<>();
    private ArrayList<Exercice> copyExos;
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
        Exercice exo=recupExoById(mExos.get(position).getId());
        holder.name.setText(exo.getName());
        holder.sets.setText(String.valueOf(mExos.get(position).getSets())+" séries");
        holder.video=exo.getVideo();
    }


    @Override
    public int getItemCount() {
        return mExos.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;
        public final TextView sets;
        public final Button exoShow;
        public final WebView webView;
        public String video;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.exoDayName);
            sets = view.findViewById(R.id.setsNumber);
            exoShow = view.findViewById(R.id.viewExo);
            webView = view.findViewById(R.id.dayExoVimeo);
            exoShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);

                    webView.loadUrl(video);
                }
            });

        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }


    }

    private Exercice recupExoById(int id){
        for(int i=0; i<copyExos.size();i++){
            if(copyExos.get(i).getId()==id){
                return copyExos.get(i);
            }
        }
        return new Exercice(0,"null","null");
    }



}
package fragments;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_android.R;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.R;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class ExerciceAdapter extends RecyclerView.Adapter {

    private String mUrl;
    private ArrayList<String> mExos;
    public ExerciceAdapter(String url) {
        mUrl=url;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        @SuppressLint("ResourceType") View view = LayoutInflater.from(parent.getContext())
                .inflate(com.example.projet_android.R.id.list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //holder.name.setText(mExos.get(position));
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
                    if (mListener != null) mListener.onViewTweet(mItem);
                }
            );*/

        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }
    }
}
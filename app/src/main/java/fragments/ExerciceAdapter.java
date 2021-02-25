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
    public ArrayList<Exercice> copyExos; //La variable copyExos va contenir l'ensemble des exercices et ne va jamais être modifiée, elle va servir de référence
    //à la variable mExos
    public ArrayList<Exercice> mExos; //La variable mExos contient la liste des exercices qui va s'adapter selon la recherche
    private RecyclerViewClickListener mListener; //Pour faire en sorte que les éléments soient "cliquables"

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
        holder.name.setText(mExos.get(position).getName()); //Pour remplir le texte de l'élément avec le nom de l'exercice
    }



    @Override
    public int getItemCount() {
        return mExos.size();
    } //Pour définir la taille de la liste avec le nombre d'exercices

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() { //On met en place le filtre de recherche d'exercices
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Exercice> filteredList = new ArrayList<>();
            if (constraint ==null || constraint.length() == 0) //S'il n'y a pas de recherche d'effectuée, on affiche la liste complète des exercices
            {
                filteredList.addAll(copyExos);
            }
            else
            {
                String filterPattern = constraint.toString().toLowerCase().trim(); //On stocke dans la variable filterPattern le texte saisi par l'utilisateur
                for (Exercice exo : copyExos)
                {
                    if (exo.getName().toLowerCase().contains(filterPattern)) //On affiche les exercices dont le texte saisi est contenu dans le nom de l'exercice
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
            mExos.clear(); //A chaque fois que la recherche est effectuée, on vide la liste mExos et on la remplit avec la liste d'exercices
            //correspondant à la recherche
            mExos.addAll((List)results.values);
            notifyDataSetChanged(); //Pour actualiser les données


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
        } //Permet de faire en sorte que l'élément sélectionné soit bien celui sur lequel l'utilisateur a cliqué, grâce à la position de l'élément dans l'adapter
    }

}
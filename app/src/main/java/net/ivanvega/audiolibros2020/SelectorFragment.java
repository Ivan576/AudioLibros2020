package net.ivanvega.audiolibros2020;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SelectorFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView recycler;
    private GridLayoutManager layoutManager;

    //comit

    MainActivity mainActivity;

    public SelectorFragment() {
        // Required empty public constructor
    }

    //Metodo estatico para la creacion de una instancia de un fragmento
    public static SelectorFragment newInstance(String param1, String param2) {
        SelectorFragment fragment = new SelectorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //Se utiliza para llamar al fragmetno dentro de la actividad
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }


    //Creacion del fragmento
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //Metodo para crear una interfaz de usuario con base con el fragmento de alguna interfaz
    //Al igual se diseña el diseño del layoutManager con los libros a crear y tambien se les asigna
    //el evento de click a los elementos.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_selector,
                container, false);

        //se le asigna las diferentes propiedades a nuestro recyclerView para que este muestre los diferentes formatos
        recycler = (RecyclerView) v.findViewById(R.id.recyclerView);

        layoutManager = new GridLayoutManager(getActivity(), 2);

        recycler.setLayoutManager(layoutManager);

        //Creamos la instancia del adaptador de libros y le asignamos los libros a crear.
        AdaptadorLibros adaptadorLibros =
                new AdaptadorLibros(getActivity(), Libro.ejemploLibros());

        //Le asignamos el evento de click a los libros el cual cuando se le de click
    //a algun libro este mostrara su posicion con un toast y despues se lanzara  el mostrarDetalle
        //el cual nos mostrara el libro seleccionado
            adaptadorLibros.setOnclickListener(
                    vl -> {
                        Toast.makeText(getActivity(),
                        "Elemento seleccionado: "
                                + recycler.getChildAdapterPosition(vl) ,
                                Toast.LENGTH_LONG).show();

                        mainActivity.mostrarDetalle(recycler.getChildAdapterPosition(vl));

                    }
            );

            //Asignacion del click largo, el cual consiste si este se lanza, se crea un alertDialog, con el cual
        //se muestra una ventana con diferentes opciones.
            adaptadorLibros.setOnLongClickListener(view -> {
                AlertDialog.Builder cuadroDialogo = new AlertDialog.Builder(getContext());

                cuadroDialogo.setTitle("Seleccione una opcion");
               // cuadroDialogo.setMessage("Este es un cuadro de dialogo");

                cuadroDialogo.setItems( new String[]{"Agregar","Eliminar","Compartir"},(dialogInterface, i) -> {
                   Toast.makeText(getActivity(),"Elemento seleccionado "+i,Toast.LENGTH_LONG).show();

                   switch (i){

                   }

                });


                cuadroDialogo.create().show();
                return false;
            });


        recycler.setAdapter(adaptadorLibros);

        return v;
    }
}
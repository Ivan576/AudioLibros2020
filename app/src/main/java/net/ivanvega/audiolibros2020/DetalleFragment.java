package net.ivanvega.audiolibros2020;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleFragment extends Fragment {

    public static String ARG_ID_LIBRO = "id_libro";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleFragment.
     */
    // TODO: Rename and change types and number of parameters

    //Creacion de la nueva instancia de nuestro detalleFragment
    public static DetalleFragment newInstance(String param1, String param2) {
        DetalleFragment fragment = new DetalleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    //Se crea nuestro fragmento
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //Creacion de la interfaz del fragemnto
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Con esto se crea la interfaz del fragmento
       View vista = inflater.inflate(R.layout.fragment_detalle, container, false);

       //se crea un Bundle con los argumentos de este fragmento.
        Bundle args = getArguments();

        //Si este es diferente de null, la posicion se le asiganara el int que tiene ese argumento,
        //y asi se le asigna a la informacion del libro junto con la vista creada.
        if (args != null) {
            int position = args.getInt(ARG_ID_LIBRO);
            ponInfoLibro(position, vista);
        } else {
            //si esto no es asi, se manda la posicion 0 para que no ocurra algun error.
            ponInfoLibro(0, vista);
        }



        return vista;
    }

    //se le asigna la informacion con el id obtenido
    public void ponInfoLibro(int id) {
        ponInfoLibro(id, getView());
    }

    //se crea la informacion del libro con los datos mandados anterior mente
    private void ponInfoLibro(int id, View vista) {
        Libro libro =
                Libro.ejemploLibros().elementAt(id);
        ((TextView) vista.findViewById(R.id.titulo)).setText(libro.titulo);
        ((TextView) vista.findViewById(R.id.autor)).setText(libro.autor);
        ((ImageView) vista.findViewById(R.id.portada)).setImageResource(libro.recursoImagen);


    }

}
package net.ivanvega.audiolibros2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Seleccionamos el fragmento que se estara ejecutando
        SelectorFragment selectorFragment
                = new SelectorFragment();

        //Buscamos el fragmento de la actividad, el cual si este no esta vacia se comienza a realizar una transaccion,
        //la cual añadimos el nuevo contenedor en nuestro fragmento.
        if (findViewById(R.id.contenedor_pequeno) != null &&
                getSupportFragmentManager().findFragmentById(R.id.contenedor_pequeno) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_pequeno,
                            selectorFragment).commit();
        }

    }


    //Metodo con el cual mostramos el detalle de nuestro libro
    public void mostrarDetalle(int index) {
        //Creamos una instancia de FragmentManager el cual nos ayudara a manejar los fragmentos.
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Verificamos que nuestro fragmento no  este vacio
        if (fragmentManager.findFragmentById(R.id.detalle_fragment) != null) {

            //Creamos un nuevo DetalleFragment el cual obtendremos y castearemos el fragmento que este en ejecucion
            //para despues mandar el index a la informacion del libro
            DetalleFragment fragment =
                    (DetalleFragment)
                            fragmentManager.findFragmentById(R.id.detalle_fragment);

            fragment.ponInfoLibro(index);


        } else {

            //Si este se encuentra vacio asignamos un DetalleFrgament
            DetalleFragment detalleFragment =
                    new DetalleFragment();

            //Creamos un Bundle el cual nos ayudara a asociar el libro que se selecciono
            //junto con la posicion que se le asigno en el detalleFragment
            Bundle bundle = new Bundle();

            //
            bundle.putInt(DetalleFragment.ARG_ID_LIBRO, index);

            detalleFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentManager.beginTransaction().replace(R.id.contenedor_pequeno
                    , detalleFragment).addToBackStack(null).commit();

        }

    }
}
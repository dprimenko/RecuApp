package es.dpinfo.recuapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import es.dpinfo.recuapp.AccountPreferences;
import es.dpinfo.recuapp.R;
import es.dpinfo.recuapp.Repository;
import es.dpinfo.recuapp.adapters.ProductsAdapter;

/**
 * Created by dprimenko on 2/02/17.
 */
public class ListProductsFragment extends Fragment {

    private ListView lwProducts;
    private ProductsAdapter adapter;

    public ListProductsFragment() {
        setHasOptionsMenu(true);
    }

    public static ListProductsFragment newInstance() {
        return new ListProductsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_products, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View rootView = view;

        lwProducts = (ListView) rootView.findViewById(R.id.lw_products);
        adapter = new ProductsAdapter(getActivity(), R.layout.item_product);
        lwProducts.setAdapter(adapter);
        adapter.addList(Repository.getInstance().getListProducts());

        lwProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                showAlertDialog();

                return false;
            }
        });

    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.edt_dialog, null);
        builder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edt_dialog);

        builder.setTitle("Test");
        builder.setMessage("Desc");
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_sort_alph) {
            adapter.sortAlphabethically();
        }

        return true;
    }
}

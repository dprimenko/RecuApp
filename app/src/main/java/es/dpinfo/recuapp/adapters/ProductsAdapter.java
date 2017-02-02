package es.dpinfo.recuapp.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import es.dpinfo.recuapp.AccountPreferences;
import es.dpinfo.recuapp.R;
import es.dpinfo.recuapp.Repository;
import es.dpinfo.recuapp.models.Product;

/**
 * Created by dprimenko on 2/02/17.
 */
public class ProductsAdapter extends ArrayAdapter {

    private ProductHolder holder;

    public ProductsAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void addList(List<Product> products) {
        addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_product, null);

            holder = new ProductHolder();
            holder.rlItem = (RelativeLayout) view.findViewById(R.id.rl_item);
            holder.imvCategory = (ImageView) view.findViewById(R.id.imv_category);
            holder.txvProdName = (TextView) view.findViewById(R.id.txv_prod_name);
            holder.txvProdPrice = (TextView) view.findViewById(R.id.txv_prod_price);

            view.setTag(holder);

        } else {
            holder = (ProductHolder) view.getTag();
        }

        Product item = (Product) getItem(position);
        boolean wantSports = AccountPreferences.getInstance(getContext()).getSportsConfig();
        boolean wantTech = AccountPreferences.getInstance(getContext()).getTechConfig();
        boolean wantHome = AccountPreferences.getInstance(getContext()).getHomeConfig();

        switch (item.getmCategory()) {
            case "sports":
                holder.imvCategory.setImageResource(R.drawable.sports);
                break;
            case "tech":
                holder.imvCategory.setImageResource(R.drawable.tech);
                break;
            case "home":
                holder.imvCategory.setImageResource(R.drawable.home);
                break;
        }

        holder.txvProdName.setText(item.getmNombre());
        holder.txvProdPrice.setText(String.format("%s €", String.valueOf(item.getmPrice())));


        if (AccountPreferences.getInstance(getContext()).getImportantConfig()) {
            switch (item.getmImportance()) {
                case Product.VERY_IMPORTANT:
                    holder.rlItem.setBackgroundColor(getContext().getResources().getColor(R.color.veryImportant));
                    break;
                case Product.MEDIUM_IMPORTANT:
                    holder.rlItem.setBackgroundColor(getContext().getResources().getColor(R.color.mediumImportant));
                    break;
                case Product.LOW_IMPORTANT:
                    holder.rlItem.setBackgroundColor(getContext().getResources().getColor(R.color.lowImportant));
                    break;
            }
        }

        if ((item.getmCategory().equals(AccountPreferences.SPORTS) && wantSports) ||
                        (item.getmCategory().equals(AccountPreferences.TECH) && wantTech) ||
                        (item.getmCategory().equals(AccountPreferences.HOME) && wantHome)) {
            holder.rlItem.setVisibility(View.VISIBLE);
        } else {
            holder.rlItem.setVisibility(View.GONE);
        }

        return view;
    }

    public void sortAlphabethically() {
        clear();
        List<Product> productList = Repository.getInstance().getListProducts();
        Collections.sort(productList, Product.priceComparator);
        addAll(productList);
    }

    class ProductHolder {
        RelativeLayout rlItem;
        ImageView imvCategory;
        TextView txvProdName, txvProdPrice;
    }
}

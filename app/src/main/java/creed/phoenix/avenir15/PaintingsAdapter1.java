package creed.phoenix.avenir15;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.utils.Views;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

public class PaintingsAdapter1 extends ItemsAdapter<Painting1> implements View.OnClickListener {

    int pagepos;
    public PaintingsAdapter1(Context context, int allocatedpos) {
        super(context);
        pagepos = allocatedpos;
        setItemsList(Arrays.asList(Painting1.getAllPaintings(context.getResources(),pagepos)));


    }

    @Override
    protected View createView(Painting1 item, int pos, ViewGroup parent, LayoutInflater inflater) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder();
        vh.image = Views.find(view, R.id.list_item_image);
        vh.image.setOnClickListener(this);
        vh.title = Views.find(view, R.id.list_item_title);
        view.setTag(vh);

        return view;
    }

    @Override
    protected void bindView(Painting1 item, int pos, View convertView) {
        ViewHolder vh = (ViewHolder) convertView.getTag();

        vh.image.setTag(item);
        Picasso.with(convertView.getContext()).load(item.getImageId()).noFade().into(vh.image);
        vh.title.setText(item.getTitle());
    }

    @Override
    public void onClick(View view) {
        if (view.getContext() instanceof MyActivity) {

            MyActivity activity = (MyActivity) view.getContext();
            //activ ity.openDetails(view, (Painting) view.getTag());
            EventFragment1 fragment;

            int x = TabbedActivity1.current;
            fragment = (EventFragment1) activity.getSupportFragmentManager().findFragmentByTag("hey").getChildFragmentManager()
                    .findFragmentByTag("android:switcher:"+R.id.pager+":"+x);
            if(fragment!=null)
                fragment.openDetails(view, (Painting1) view.getTag());
        }
    }

    private static class ViewHolder {
        ImageView image;
        TextView title;
    }

}

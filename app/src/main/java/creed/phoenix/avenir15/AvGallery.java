package creed.phoenix.avenir15;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexvasilkov.foldablelayout.FoldableListLayout;

public class AvGallery extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";

    public AvGallery() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gallery, container, false);

        FoldableListLayout foldableListLayout = (FoldableListLayout) rootView.findViewById(R.id.foldable_list);
        foldableListLayout.setAdapter(new PaintingsAdapter(getActivity().getApplicationContext(),99));

        return rootView;
    }
}
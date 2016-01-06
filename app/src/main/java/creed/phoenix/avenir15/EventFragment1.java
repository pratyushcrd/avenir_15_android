package creed.phoenix.avenir15;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.utils.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.squareup.picasso.Picasso;

/**
 * Created by Pratyush on 25-12-2014.
 */
public class EventFragment1 extends Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    NiftyDialogBuilder dialogBuilder;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    int allocatedpos;

    public EventFragment1() {
    }
    public ListView mListView;
    private View mListTouchInterceptor;
    private View mDetailsLayout;
    private UnfoldableView mUnfoldableView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);


        dialogBuilder=NiftyDialogBuilder.getInstance(getActivity());




        allocatedpos = this.getArguments().getInt("scroll");

        mListView = (ListView) rootView.findViewById(R.id.list_view);

        mListView.setAdapter(new PaintingsAdapter1(getActivity().getApplicationContext(),allocatedpos));

        mListTouchInterceptor = rootView.findViewById(R.id.touch_interceptor_view);
        mListTouchInterceptor.setClickable(false);
        mDetailsLayout = rootView.findViewById(R.id.details_layout);
        mDetailsLayout.setVisibility(View.INVISIBLE);

        mUnfoldableView = (UnfoldableView) rootView.findViewById(R.id.unfoldable_view);

        mUnfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(true);
                mDetailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(false);
                mDetailsLayout.setVisibility(View.INVISIBLE);
            }
        });
        MyActivity.fabAtt(mListView);



        return rootView;
    }
    public void openDetails(View coverView, final Painting1 painting1) {
        ImageView image = Views.find(mDetailsLayout, R.id.details_image);
        TextView title = Views.find(mDetailsLayout, R.id.details_title);
        Button description = Views.find(mDetailsLayout, R.id.details_text);

        Picasso.with(getActivity().getApplicationContext()).load(painting1.getImageId()).into(image);
        title.setText(painting1.getTitle());


        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogBuilder
                        .withTitle(painting1.getTitle())                                  //.withTitle(null)  no title
                        .withTitleColor("#FFFFFF")                                  //def
                        .withDividerColor("#11000000")                              //def
                        .withMessage("")                     //.withMessage(null)  no Msg
                        .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                        .withDialogColor("#FFE74C3C")
                        .withDuration(500)                                          //def
                        .withEffect(Effectstype.Slidetop)                                         //def Effectstype.Slidetop
                        .withButton1Text("OK")                                     //def gone
                        .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                        .setCustomView(painting1.getYear(), getActivity().getApplicationContext())         //.setCustomView(View or ResId,context)
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogBuilder.hide();
                            }
                        })
                        .show();
            }
        });

        mUnfoldableView.unfold(coverView, mDetailsLayout);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //((MyActivity) activity).onSectionAttached(
        //    getArguments().getInt(ARG_SECTION_NUMBER));

        String str=activity.getResources().getString(R.string.app_name);

    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    onBackPressed();
                    return true;
                }
                return false;
            }
        });
    }


    Boolean onBackPressed(){
        if (mUnfoldableView != null && (mUnfoldableView.isUnfolded() || mUnfoldableView.isUnfolding())) {
            mUnfoldableView.foldBack();
        } else {
            getActivity().onBackPressed();
            return true;
        }

        return false;
    }


}

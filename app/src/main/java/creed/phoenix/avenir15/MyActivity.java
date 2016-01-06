package creed.phoenix.avenir15;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private static Toolbar toolbar;
    public static DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private static ListView listView;
    public static Boolean isActive = false;
    private ArrayAdapter<String> navigationDrawerAdapter;
    private static FloatingActionButton fab;
    private String[] leftSliderData = {"Avenir", "Events","Trending", "Workshops", "Gallery", "Our Team", "Admin", "Sponsors", "About","Register"};
    private int[] navIc = {R.drawable.zo1, R.drawable.zo2,R.drawable.trend, R.drawable.zo3, R.drawable.zo4, R.drawable.commit, R.drawable.zo5, R.drawable.spon,R.drawable.zo6,R.drawable.reg};
    public RowItem item;
    public static RowItem[] item_arr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        isActive = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#1a1a1a"));


        }

        nitView();
        if (toolbar != null) {
            toolbar.setTitle("Avenir 15");
            setSupportActionBar(toolbar);
            toolbar.bringToFront();
        }
        initDrawer();

        fragchange(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActive = false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        isActive = true;
    }

    public static void title_change(String str) {
        toolbar.setTitle(str);
    }


    private void nitView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        item_arr = new RowItem[leftSliderData.length];

        List<RowItem> rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < leftSliderData.length; i++) {
            item = new RowItem(navIc[i], leftSliderData[i]);
            item_arr[i] = item;
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.left_drawer);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setColorRipple(Color.parseColor("#ff80ab"));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent("creed.phoenix.avenir15.Messaging");
                startActivity(i);
            }
        });

    }

    private void initDrawer() {

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.setScrimColor(Color.parseColor("#ee000000"));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);

        menu.add("Map").setIcon(android.R.drawable.ic_dialog_map).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                startActivity(new Intent("creed.phoenix.avenir15.MAP"));

                return false;
            }
        });
        return true;
    }

    static void fabAtt(AbsListView x) {

        fab.attachToListView(x);
    }

    static void fabShow() {
        if (!fab.isVisible())
            fab.show();
    }

    static void fabHide() {
        if (fab.isVisible())
            fab.hide();
    }

    static void fabToggle() {
        if (fab.isVisible())
            fab.hide();
        else
            fab.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (/**id == R.id.action_settings**/true) {
            return true;
        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void fragchange(int position) {
        fabShow();
        Fragment fragment;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#c0392b"));
            window.setNavigationBarColor(Color.parseColor("#c0392b"));


        }
        toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_back));
        toolbar.setTitle("Avenir 15");
        if (position == 1) {
            fragment = new TabbedActivity();
            toolbar.setTitle("Gaming");
        }else if (position==2){
            fragment = new TabbedActivity1();
            toolbar.setTitle("Year's Trending");
        } else if (position == 3) {
            fragment = new Workshop();
        } else if (position == 4) {
            fragment = new AvGallery();
        } else if (position == 6) {
            fragment = new Admin();
        } else if (position == 7) {
            fragment = new ItemsActivity();
        } else if (position == 8) {
            fragment = new AboutFrag();
        }else if (position==9){
            fragment=new Registration();
        }
        else if (position==5){
            fragment= new ItemsActivity1();
            //toolbar.setTitle("Our Team");
        }

        else {
            fragment = new PlanetFragment();
            toolbar.setTitle("");


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(Color.parseColor("#000000"));
                window.setNavigationBarColor(Color.parseColor("#000000"));

            }

            toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment, "hey")
                .commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        fragchange(position);
        // Highlight the selected item, update the title, and close the drawer
        listView.setItemChecked(position, true);
        drawerLayout.closeDrawers();


    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(findViewById(R.id.nav_drawer)))
            drawerLayout.closeDrawers();
        else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please press back again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }


    public static class PlanetFragment extends Fragment implements View.OnClickListener {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        ImageView b1, b2, b3, b4, b5;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);

            b1 = (ImageView) rootView.findViewById(R.id.pl_bt1);
            b2 = (ImageView) rootView.findViewById(R.id.pl_bt2);
            b3 = (ImageView) rootView.findViewById(R.id.pl_bt3);
            b4 = (ImageView) rootView.findViewById(R.id.pl_bt4);
            b5 = (ImageView) rootView.findViewById(R.id.pl_bt5);

            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            b3.setOnClickListener(this);
            b4.setOnClickListener(this);
            b5.setOnClickListener(this);
            return rootView;
        }

        @Override
        public void onClick(View v) {
            MyActivity.drawerLayout.openDrawer(getActivity().findViewById(R.id.nav_drawer));
            

        }
    }
}
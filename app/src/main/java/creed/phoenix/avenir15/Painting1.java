package creed.phoenix.avenir15;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import android.widget.Toast;

public class Painting1 {

    private final int imageId;
    private final String title;
    private final int year;
    private final String location;


    //static int[] cyber={R.layout.cyber_encoding,R.layout.cyber_web,R.layout.cyber_hack,R.layout.cyber_bonapp};
    static int[] trend={R.layout.tech_fair,R.layout.fun_zorbsoccer,R.layout.cyber_bonapp,R.layout.general_inception};
    //static int[] robo ={R.layout.robo_metal,R.layout.robo_intrusion,R.layout.robo_linetracer,R.layout.robo_robosoccer,R.layout.robo_terraranger,R.layout.robo_irescuer};
    //static int[] gen={R.layout.general_menemonics,R.layout.general_inception,R.layout.general_contrive,R.layout.general_quizzard,R.layout.general_dispute,R.layout.general_arithmos,R.layout.general_puzzle,  R.layout.fun_con2win,R.layout.game_knighted,R.layout.game_sudoku};
    //static int[] len={R.layout.lensi_lenz,R.layout.lensi_clickinstant,R.layout.lensi_bioscope,R.layout.lensi_cinearticulate,R.layout.lensi_spectrum};
   // static int[] fun ={R.layout.general_adenacting,R.layout.fun_dartboard,R.layout.fun_facepainting,R.layout.fun_freewheelin,R.layout.fun_treasure,R.layout.fun_zorbsoccer};

    private Painting1(int imageId, String title, int year, String location) {
        this.imageId = imageId;
        this.title = title;
        this.year = year;
        this.location = location;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getLocation() {
        return location;
    }

    public static Painting1[] getAllPaintings(Resources res, int pos) {
        String[] titles;
        String[] years;
        int[] rules;
        rules = new int[10];

        years = res.getStringArray(R.array.avenir_gaming);


        TypedArray images;
        switch (pos) {
            case 0:
                images = res.obtainTypedArray(R.array.event_trending);
                titles = res.getStringArray(R.array.av_trending);
                rules = trend;
                break;
            /*case 1:
                images = res.obtainTypedArray(R.array.event_cyber);
                titles = res.getStringArray(R.array.av_cyber);
                rules = cyber;
                break;*/
          /*  case 3:
                images = res.obtainTypedArray(R.array.event_general);
                titles = res.getStringArray(R.array.av_general);
                rules = gen;
                break;
            case 2:
                images = res.obtainTypedArray(R.array.event_robotics);
                titles = res.getStringArray(R.array.av_robo);
                rules = robo;
                break;
            case 4:
                images = res.obtainTypedArray(R.array.event_lens);
                titles = res.getStringArray(R.array.av_lensified);
                rules = len;
                break;
            case 5:
                images = res.obtainTypedArray(R.array.event_fun);
                titles = res.getStringArray(R.array.av_fun);
                rules = fun;
                break;
            case 6:
                images = res.obtainTypedArray(R.array.event_gaming);
                titles = res.getStringArray(R.array.av_gaming);
                rules = gaming;
                break;
          */  case 99:
                images = res.obtainTypedArray(R.array.gallery_drawable);
                titles = res.getStringArray(R.array.gallery_title);
                break;
            default:
                images = res.obtainTypedArray(R.array.event_gaming);
                titles = res.getStringArray(R.array.av_gaming);
                rules = new int[]{0,0,0,0,0,0,0,0};
        }

        int size = titles.length;
        Painting1[] paintings1 = new Painting1[size];

        for (int i = 0; i < size; i++) {
            paintings1[i] = new Painting1(images.getResourceId(i, -1), titles[i], rules[i], "");
        }

        return paintings1;
    }

}

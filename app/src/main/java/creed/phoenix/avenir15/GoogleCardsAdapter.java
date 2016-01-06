/*
 * Copyright 2014 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package creed.phoenix.avenir15;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nhaarman.listviewanimations.ArrayAdapter;

public class GoogleCardsAdapter extends ArrayAdapter<Integer> {

    private final Context mContext;
    private final BitmapCache mMemoryCache;

    GoogleCardsAdapter(final Context context) {
        mContext = context;
        mMemoryCache = new BitmapCache();
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_googlecards_card, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) view.findViewById(R.id.activity_googlecards_card_textview);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.activity_googlecards_card_textview2);
            viewHolder.textView3 = (TextView) view.findViewById(R.id.activity_googlecards_card_textview3);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }



        viewHolder.textView1.setText(Messaging.txt1[getItem(position)]);
        viewHolder.textView2.setText(Messaging.txt2[getItem(position)]);
        viewHolder.textView3.setText("posted by : "+Messaging.txt3[getItem(position)]);

        return view;
    }

    @NonNull
    @Override
    public Integer remove(int location) {
        return super.remove(location);
    }

    private Bitmap getBitmapFromMemCache(final int key) {
        return mMemoryCache.get(key);
    }



    @SuppressWarnings({"PackageVisibleField", "InstanceVariableNamingConvention"})
    private static class ViewHolder {
        TextView textView1,textView2,textView3;
    }
}
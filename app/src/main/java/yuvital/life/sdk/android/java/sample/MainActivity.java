package yuvital.life.sdk.android.java.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.yuvital.yuvitallife.sdk.ReactNativeHostActivity;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.grid_cards);

        List<YuvitalCardConfig> cards = Arrays.asList(
                new YuvitalCardConfig("Open Yuvital Life", R.drawable.yuvital_life, true, true),
                new YuvitalCardConfig("Heart rate", R.drawable.heart_metric_icon, false, false),
                new YuvitalCardConfig("Nutrition", R.drawable.nutrition_metric_icon, false, false),
                new YuvitalCardConfig("Sleep", R.drawable.sleep_metric_icon, false, false),
                new YuvitalCardConfig("Mindfulness", R.drawable.mindfulness_metric_icon, false, false),
                new YuvitalCardConfig("Walking", R.drawable.walking_metric_icon, false, false));

        gridView.setAdapter(new YuvitalCardAdapter(this, cards));
    }

    private static class YuvitalCardConfig {
        final String title;
        final int iconRes;
        final boolean isPrimary;
        final boolean isClickable;

        YuvitalCardConfig(String title, int iconRes, boolean isPrimary, boolean isClickable) {
            this.title = title;
            this.iconRes = iconRes;
            this.isPrimary = isPrimary;
            this.isClickable = isClickable;
        }
    }

    private static class YuvitalCardAdapter extends BaseAdapter {

        private final Context context;
        private final List<YuvitalCardConfig> cards;
        private final LayoutInflater inflater;

        YuvitalCardAdapter(Context context, List<YuvitalCardConfig> cards) {
            this.context = context;
            this.cards = cards;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return cards.size();
        }

        @Override
        public Object getItem(int position) {
            return cards.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_yuvital_card, parent, false);
                holder = new ViewHolder();
                holder.icon = convertView.findViewById(R.id.image_icon);
                holder.title = convertView.findViewById(R.id.text_title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final YuvitalCardConfig card = cards.get(position);

            holder.title.setText(card.title);
            holder.icon.setImageResource(card.iconRes);

            int backgroundColor;
            if (card.isPrimary) {
                backgroundColor = ContextCompat.getColor(context, android.R.color.holo_blue_dark);
            } else {
                backgroundColor = ContextCompat.getColor(context, android.R.color.darker_gray);
            }
            convertView.setBackgroundColor(backgroundColor);

            if (card.isClickable) {
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.animate().cancel();
                        v.setAlpha(0.7f);
                        v.animate()
                                .alpha(1f)
                                .setDuration(150)
                                .start();

                        Intent intent = new Intent(context, ReactNativeHostActivity.class);
                        context.startActivity(intent);
                    }
                });
            } else {
                convertView.setOnClickListener(null);
            }

            return convertView;
        }

        private static class ViewHolder {
            ImageView icon;
            TextView title;
        }
    }
}

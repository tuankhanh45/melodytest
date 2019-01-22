package com.example.khanh.melody.HomeDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khanh.melody.R;

import java.util.ArrayList;

public class HomeDetailsActivity extends AppCompatActivity {
    int[] imv = {R.drawable.app_icon, R.drawable.alert, R.drawable.bathroom, R.drawable.app_icon, R.drawable.alert, R.drawable.bathroom};
    String[] name = {"hello1", "h2", "asjf", "hello1", "h2", "asjf"};
    String des = "Truyền hình FPTDecember 25, PM ·  · m của HLV Nguyễn Hữu Thắng từng có chiến thắng tưng bừng 5-2 trước CHDCND Triều Tiên trong trận giao hữu vào tháng 6/2016.Đội hình Việt Nam lúc bấy giờ pha trộn giữa những cựu binh như Công Vinh, Thành Lương, Đình Luật, kết hợp cùng nhóm sao mai từ HAGL gồm Tuấn Anh, Xuân Trường, Công Phượng, Văn Thanh, Văn Toàn đã chơi một trận tuyệt hay, nhấn chìm đội khách bằng lối chơi tấn công rực lửa khắp mặt sân. Cần biết Triều Tiên năm đó mang sang Việt Nam đ";

    Button show;
    ImageView back;
    ImageView list;
    ImageView edit;
    TextView txtHomeDetails;
    RecyclerView rcitems;
    Items items;
    ArrayList<Items> itemsList;
    RecyclerListAdapterItem recyclerListAdapterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);

        //set id
        rcitems = (RecyclerView) findViewById(R.id.rc_item);
        txtHomeDetails = (TextView) findViewById(R.id.txt_home_details);
        show = (Button) findViewById(R.id.btn_show_more);
        back = (ImageView) findViewById(R.id.img_back);
        edit = (ImageView) findViewById(R.id.img_edit);
        list = (ImageView) findViewById(R.id.img_list);

        //set rc view show
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(HomeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rcitems.setLayoutManager(horizontalLayoutManager);
        itemsList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            items = new Items();
            items.setIcon(imv[i]);
            items.setName(name[i]);
            itemsList.add(items);
        }
        recyclerListAdapterItem = new RecyclerListAdapterItem(itemsList, HomeDetailsActivity.this);
        rcitems.setAdapter(recyclerListAdapterItem);
        recyclerListAdapterItem.notifyDataSetChanged();

        txtHomeDetails.setText(des);
        makeTextViewResizable(txtHomeDetails, 4, "View More", true);
        // onclick action
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    // show more less view
    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                String text;
                int lineEndIndex;
                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    lineEndIndex = tv.getLayout().getLineEnd(0);
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                } else {
                    lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                }
                tv.setText(text);
                tv.setMovementMethod(LinkMovementMethod.getInstance());
                tv.setText(
                        addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                viewMore), TextView.BufferType.SPANNABLE);
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {
            ssb.setSpan(new ClickableSpan() {

                @Override
                public void onClick(View widget) {
                    tv.setLayoutParams(tv.getLayoutParams());
                    tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                    tv.invalidate();
                    if (viewMore) {
                        makeTextViewResizable(tv, -1, "View Less", false);
                    } else {
                        makeTextViewResizable(tv, 3, "View More", true);
                    }

                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }
}

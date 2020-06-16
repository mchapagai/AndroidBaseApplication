package com.example.mchapagai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.mchapagai.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.about_toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.licenses)
    LinearLayout layout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about_activity_container);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }


        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        collapsingToolbar.setTitle(getString(R.string.title_about_activity));

        initLicenses();
    }


    private void initLicenses() {
        String[] softwareList = getResources().getStringArray(R.array.software_list);
        String[] licenseList = getResources().getStringArray(R.array.license_list);
        layout.addView(createItemsText(softwareList));

        for (int i = 0; i < softwareList.length; i++) {
            layout.addView(createHeader(softwareList[i]));
            layout.addView(createHtmlText(licenseList[i]));
        }
    }

    private TextView createHeader(final String name) {
        String s = "<big><b>" + name + "</b></big>";
        return createHtmlText(s, 8);
    }

    private TextView createItemsText(final String... names) {
        StringBuilder s = new StringBuilder();
        for (String name : names) {
            if (s.length() > 0) {
                s.append("<br>");
            }
            s.append("\u2022 ");
            s.append(name);
        }
        return createHtmlText(s.toString(), 8);
    }

    private TextView createHtmlText(final String s) {
        return createHtmlText(s, 8);
    }

    private TextView createHtmlText(final String s, final int margin) {
        TextView text = new TextView(this);
        text.setAutoLinkMask(Linkify.WEB_URLS | Linkify.EMAIL_ADDRESSES);
        text.setText(Html.fromHtml(s));
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        int marginPx = (0 < margin) ? (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin,
                getResources().getDisplayMetrics()) : 0;
        layoutParams.setMargins(0, marginPx, 0, marginPx);
        text.setLayoutParams(layoutParams);
        return text;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}

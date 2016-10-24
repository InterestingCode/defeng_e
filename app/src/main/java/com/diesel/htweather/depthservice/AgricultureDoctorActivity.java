package com.diesel.htweather.depthservice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.depthservice.model.DoctorBean;
import com.diesel.htweather.util.PhoneUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgricultureDoctorActivity extends BaseActivity {

    @BindView(R.id.tvDoctorTitle)
    TextView tvDoctorTitle;

    @BindView(R.id.tvDoctorTime)
    TextView tvAgricultureTime;

    @BindView(R.id.tvDoctorContent)
    TextView tvDoctorContent;

    DoctorBean doctorBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_doctor);
        ButterKnife.bind(this);
        doctorBean = (DoctorBean) getIntent().getSerializableExtra("doctorBean");
        tvDoctorTitle.setText(doctorBean.getTitle());
        tvAgricultureTime.setText(doctorBean.getSendTime());
        //tvDoctorContent.setText(doctorBean.getContent());
        tvDoctorContent.setText(Html.fromHtml(doctorBean.getContent(), new HtmlImageGetter(), null));
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }

    class HtmlImageGetter implements Html.ImageGetter {

        /**
         * 获取图片
         */
        @Override
        public Drawable getDrawable(String source) {
            LevelListDrawable d = new LevelListDrawable();
            Drawable empty = ContextCompat.getDrawable(mContext, R.drawable.test_depth);
            d.addLevel(0, 0, empty);
            d.setBounds(0, 0, PhoneUtils.getScreenWidth(mContext), empty.getIntrinsicHeight());
            new LoadImage().execute(source, d);
            return d;
        }
    }

    class LoadImage extends AsyncTask<Object, Void, Bitmap> {

        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                /**
                 * 适配图片大小 <br/>
                 * 默认大小：bitmap.getWidth(), bitmap.getHeight()<br/>
                 * 适配屏幕：getDrawableAdapter
                 */
                mDrawable = getDrawableAdapter(mContext, mDrawable, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);

                /**
                 * 图片下载完成之后重新赋值textView<br/>
                 * mtvActNewsContent:我项目中使用的textView
                 *
                 */
                tvDoctorContent.invalidate();
                CharSequence t = tvDoctorContent.getText();
                tvDoctorContent.setText(t);

            }
        }

        public LevelListDrawable getDrawableAdapter(Context context, LevelListDrawable drawable, int oldWidth, int oldHeight) {
            LevelListDrawable newDrawable = drawable;
            long newHeight = 0;// 未知数
            int newWidth = PhoneUtils.getScreenWidth(context);// 默认屏幕宽
            newHeight = (newWidth * oldHeight) / oldWidth;
            newDrawable.setBounds(0, 0, newWidth, (int) newHeight);
            return newDrawable;
        }
    }
}

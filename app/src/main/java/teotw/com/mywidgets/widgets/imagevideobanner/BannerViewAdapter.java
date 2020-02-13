package teotw.com.mywidgets.widgets.imagevideobanner;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BannerViewAdapter extends PagerAdapter {
 
    private Context context;
    private List<BannerModel> listBean;
 
    public BannerViewAdapter(Activity context, List<BannerModel> list) {
        this.context = context;
        if (list == null || list.size() == 0) {
            this.listBean = new ArrayList<>();
        } else {
            this.listBean = list;
        }
    }
 
    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        if (listBean.get(position).getUrlType() == 0) {//图片
            final ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            Glide.with(context)
                    .load(listBean.get(position).getBannerUrl())
                    .fitCenter()
                    .into(imageView);
            container.addView(imageView);
            
            return imageView;
        }else{//视频
            VideoView videoView = new VideoView(context);
            videoView.setVideoURI(Uri.parse(listBean.get(position).getBannerUrl()));
//            videoView.setMediaController(new MediaController(context));
            //开始播放
            videoView.start();
            container.addView(videoView);
 
            return videoView;
        }
 
    }
 
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
 
    @Override
    public int getCount() {
        return listBean.size();
    }
 
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }
}
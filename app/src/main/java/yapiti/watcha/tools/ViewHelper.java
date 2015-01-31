package yapiti.watcha.tools;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by yapiti on 27/10/14.
 */
public class ViewHelper {

    public static String getStringAttribute(Context context, AttributeSet attributeSet, int index){
        int id=attributeSet.getAttributeResourceValue(index, 0);

        return id==0 ?
                attributeSet.getAttributeValue(index)
                : context.getString(id);
    }

    public static Integer getIntAttribute(Context context, AttributeSet attributeSet, int index){
        int id=attributeSet.getAttributeResourceValue(index, 0);

        return id==0 ?
                Integer.parseInt(attributeSet.getAttributeValue(index))
                : context.getResources().getInteger(id);
    }

    public static void waitLayoutLoad(final View view, final LayoutChange listener){
        if(view.getWidth()!=0) listener.done(view);
        else view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                view.removeOnLayoutChangeListener(this);
                listener.done(view);
            }
        });
    }

    public static Uri imageUriByWidth(Uri uri, Integer width){
        return Uri.withAppendedPath(uri, width.toString());
    }

    public static Uri imageUriByHeight(Uri uri, Integer height){
        return imageUri(uri, 0, height);
    }

    public static Uri imageUri(Uri uri, Integer width, Integer height){
        return Uri.withAppendedPath(imageUriByWidth(uri, width), height.toString());
    }

    public interface LayoutChange{
        public void done(View view);
    }
}

package vv.aotu.base.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView;

/**
 * Some useful methods for views.
 *

 */
public class ViewUtils {

  private static final int MAX_SMOOTH_SCROLL_POSITION = 5;
  private static final String REDIRECT_URL_PREFIX = "url=";
  private static final String ELLIPSIS = "...";
  private static final int TOAST_LENGTH = 25;
  private static final long TOAST_TIME = 500L;

  private ViewUtils() {
  }

  /**
   * Changes the size of a view.
   *
   * @param view   the view to change size
   * @param width  the new width
   * @param height the new height
   */
  public static void setViewSize(View view, int width, int height) {
    LayoutParams layoutParams = view.getLayoutParams();
    layoutParams.width = width;
    layoutParams.height = height;
    view.setLayoutParams(layoutParams);
  }


  /**
   * Sets the background of view.
   *
   * @param view       view
   * @param background background drawable
   */
  @SuppressWarnings("deprecation")
  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  public static void setBackground(View view, Drawable background) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      view.setBackground(background);
    } else {
      view.setBackgroundDrawable(background);
    }
  }

  /**
   * Creates a view.
   *
   * @param parent parent view
   * @param resId  resource id
   * @return view
   */
  public static View newInstance(ViewGroup parent, int resId) {
    return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
  }

  /**
   * Creates a view.
   *
   * @param context context
   * @param resId   resource id
   * @return view
   */
  public static View newInstance(Context context, int resId) {
    return LayoutInflater.from(context).inflate(resId, null);
  }


  /**
   * get a String of which with a Drawable in front.
   *
   * @param textSize the size of text
   * @param text     the content of text
   * @param drawable the drawable you want to put in front of text
   * @return String with drawable
   */
  public static CharSequence getDrawableTextSpan(final int textSize,
                                                 String text, final Drawable drawable) {
    SpannableStringBuilder sb = new SpannableStringBuilder(" ");
    sb.append(text);
    DynamicDrawableSpan drawableSpan = new DynamicDrawableSpan() {
      @Override
      public Drawable getDrawable() {
        float height = textSize;
        float width =
                drawable.getIntrinsicWidth() * height / drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, (int) width, (int) height);
        return drawable;
      }
    };
    sb.setSpan(drawableSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
    return sb;
  }

  public static boolean isViewAttachedToDecorView(View view) {
    if (!(view.getContext() instanceof Activity)) {
      return true;
    }
    View decorView = ((Activity) view.getContext()).getWindow().getDecorView();
    if (view == decorView) {
      return true;
    }
    if (view.getWindowToken() != null && view.getWindowToken() != decorView.getWindowToken()) {
      // The view is not in the same window with activity. It's probably in a Dialog.
      return true;
    }
    ViewParent parent = view.getParent();
    while (parent != null) {
      if (parent == decorView) {
        return true;
      }
      parent = parent.getParent();
    }
    return false;
  }

  /**
   * Scroll the specific AbsListView to top.
   *
   * @param listView the specific list view
   * @return true if scrollToTop is handled, false otherwise
   */
  @TargetApi(Build.VERSION_CODES.FROYO)
  public static boolean scrollToTop(AbsListView listView) {
    if (listView.getFirstVisiblePosition() == 0) {
      View firstChild = listView.getChildAt(0);
      if (firstChild == null) {
        return false;
      }
      if (firstChild.getTop() == listView.getPaddingTop()) {
        return false;
      }
    }
    scrollToChildAt(listView, 0);
    return true;
  }

  /**
   * Scroll the specific AbsListView to top.
   *
   * @param listView the specific list view
   * @return true if scrollToTop is handled, false otherwise
   */
  @TargetApi(Build.VERSION_CODES.FROYO)
  public static void scrollToChildAt(AbsListView listView, int position) {
    if (SystemUtil.aboveApiLevel(Build.VERSION_CODES.FROYO)) {
      // This is used to stop the previous scroll runnable
      listView.smoothScrollBy(0, 0);
      if (Math.abs(listView.getFirstVisiblePosition() - position) <= MAX_SMOOTH_SCROLL_POSITION) {
        listView.smoothScrollToPosition(position);
      } else {
        listView.setSelection(position);
      }
    } else {
      listView.setSelection(position);
    }
  }

  /**
   * clear selected status without given view
   *
   * @param viewGroup
   * @param withoutView
   */
  public static void clearSelected(ViewGroup viewGroup, View withoutView) {
    if (viewGroup == null) {
      return;
    }
    for (int i = 0; i < viewGroup.getChildCount(); i++) {
      View child = viewGroup.getChildAt(i);
      if (child != withoutView) {
        child.setSelected(false);
      }
    }
  }

  public static boolean isParentSonView(View sonView, View parentView) {
    boolean isChild = false;
    View parent = sonView;
    while (null != parent) {
      if (parent == parentView) {
        isChild = true;
        break;
      }
      if (parent.getParent() instanceof View){
        parent = (View) parent.getParent();
      } else {
        isChild = false;
        break;
      }
    }
    return isChild;
  }

  /**
   * @return child view
   */
  public static View findChildViewById(ViewGroup parent, int id) {
    if (parent == null) {
      return null;
    }
    final int count = parent.getChildCount();
    for (int i = 0; i < count; ++i) {
      View child = parent.getChildAt(i);
      if (child.getId() == id) {
        return child;
      }
    }
    return null;
  }

}

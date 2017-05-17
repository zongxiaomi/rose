package vv.aotu.business.home.activity;

import android.os.AsyncTask;
import android.support.annotation.MainThread;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by mi on 2017/5/6.
 */

public class HtmlAsyncTask extends AsyncTask<String, Integer, Document> {


  @Override
  protected Document doInBackground(String... params) {
    String url = params[0];
    Document document = null;
    try {
      document = Jsoup.connect(url).timeout(10000).get();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return document;
  }

  private Callback mCallback;

  public void setDataCallback(Callback callback) {
    this.mCallback = callback;
  }

  @Override
  protected void onPostExecute(Document document) {
    super.onPostExecute(document);
    if (mCallback != null) {
      mCallback.onDataCallback(document);
    }

  }

  @Override
  protected void onProgressUpdate(Integer... values) {
    super.onProgressUpdate(values);
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
  }

  interface Callback {
    @MainThread
    void onDataCallback(Document document);

  }
}

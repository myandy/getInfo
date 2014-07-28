package com.myth.getinfo;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class LoadHtmlTask extends AsyncTask<String, String, String>
{

    // 异步获取信息

    ProgressDialog bar;

    Document doc;

    private Context context;

    public LoadHtmlTask(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params)
    {
        // TODO Auto-generated method stub
        try
        {
            doc = Jsoup.connect(params[0]).timeout(5000).get();
            Elements divs = doc.select("[class=question_link]");
            Log.d("element", divs.toString());
            for (Element links : divs)
            {
                String title = links.getElementsByTag("a").text();

                String link = links.select("a").attr("href").replace("/", "").trim();
                Log.d("element", title + ":" + link);
            }

        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result)
    {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        // Log.d("doc", doc.toString().trim());
        bar.dismiss();
        // ListItemAdapter adapter = new ListItemAdapter(context,
        // usedatabase.getlist());
        // listmenu.setAdapter(adapter);
    }

    @Override
    protected void onPreExecute()
    {
        // TODO Auto-generated method stub
        super.onPreExecute();

        bar = new ProgressDialog(context);
        bar.setMessage("正在加载数据····");
        bar.setIndeterminate(false);
        bar.setCancelable(false);
        bar.show();
    }

}

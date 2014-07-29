package com.myth.getinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NoteListActivity extends Activity
{

    private ListView listView;

    private int adapterData;

    private Map<String, String> map = new HashMap<String, String>();

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        new LoadHtmlTask(NoteListActivity.this).execute("http://www.zhihu.com/explore");
        listView = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(NoteListActivity.this, R.layout.note_list_item, new ArrayList());
        listView.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ArrayList<String> a = new ArrayList<String>();
                a.addAll(map.values());
                WebViewActivity.url = a.get(position);
                startActivity(new Intent(NoteListActivity.this, WebViewActivity.class));

            }
        });
    }

    public class LoadHtmlTask extends AsyncTask<String, String, String>
    {

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

                    String link = links.select("a").attr("href").trim();
                    Log.d("element", title + ":" + link);
                    map.put(title, "http://www.zhihu.com" + link);
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
            listView.setAdapter(adapter);
            adapter.clear();
            adapter.addAll(map.keySet());
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub
            super.onPreExecute();

            bar = new ProgressDialog(context);
            bar.setMessage("���ڼ�����ݡ�������");
            bar.setIndeterminate(false);
            bar.setCancelable(false);
            bar.show();
        }
    }
}

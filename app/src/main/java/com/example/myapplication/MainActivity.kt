package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cubee_list.view.*

data class Item (
    val photo: Int, //圖片ID
    val name: String //名稱
)
class MyAdapter constructor(private val layout: Int, private val data:
ArrayList<Item>) : BaseAdapter() {
    //回傳資料來源筆數
    override fun getCount() = data.size
    //回傳某筆項目
    override  fun getItem(position: Int) = data[position]
    //回傳某筆項目id
    override  fun getItemId(position: Int) = 0L
    //取得畫面元件
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = View.inflate(parent?.context, layout,null)
        view.imageView.setImageResource(data[position].photo)
        view.name.text = data[position].name
        return view
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val item = ArrayList<Item>()
        val array3 = resources.obtainTypedArray(R.array.trans_list)
        val transNameArray3 = arrayOf("腳踏車", "機車", "汽車", "巴士","飛機","船")
        for(i in 0 until array3.length())
            item.add(Item(array3.getResourceId(i,0),"${transNameArray3[i]}"))
        array3.recycle()

        val item2 = ArrayList<Item>()
        val array2 = resources.obtainTypedArray(R.array.cubee_list)
        val transNameArray2   = arrayOf("哭哭", "發抖", "再見", "生氣", "昏倒", "竊笑", "很棒",
            "你好", "驚嚇", "大笑")
        for(i in 0 until array2.length())
            item2.add(Item(array2.getResourceId(i,0),"${transNameArray2[i]}"))
        array2.recycle()

        spinner.adapter = MyAdapter(R.layout.trans_list, item)
        gridView.numColumns = 3
        gridView.adapter = MyAdapter(R.layout.cubee_list, item2)
        listView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListOf("訊息1", "訊息2", "訊息3", "訊息4", "訊息5", "訊息6"))
    }
}
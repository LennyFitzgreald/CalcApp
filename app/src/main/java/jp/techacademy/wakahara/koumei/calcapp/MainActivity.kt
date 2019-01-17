package jp.techacademy.wakahara.koumei.calcapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener(this) // ＋
        button2.setOnClickListener(this) // －
        button3.setOnClickListener(this) // ×
        button4.setOnClickListener(this) // ÷
    }

    override fun onClick(v: View) {
        // 数値は小数（ダブル)
        var value1: Double
        var value2: Double

        // 例外処理の準備
        val value1Str = editText1.getText().toString()
        val value2Str = editText2.getText().toString()


        // 例外処理
        try{
            // 例外が起きるコードをかく
            value1 = value1Str.toDouble()
            value2 = value2Str.toDouble()

            // 除法、かつ「ゼロで割ろうとしたとき」の処理
            if (v.id==R.id.button4 && value2 ==0.0 ) {
                Snackbar.make(v, "ゼロで割ることはできません", Snackbar.LENGTH_INDEFINITE)
                    .setAction("了解"){
                    }.show()
                return // "else"は要らないので、ここでif文を抜ける
            }

            var result = 0.0 // var result: Double = 0.0 だと"redundant"と言われる

            when (v.id){
                R.id.button1 -> result = value1 + value2
                R.id.button2 -> result = value1 - value2
                R.id.button3 -> result = value1 * value2
                R.id.button4 -> result = value1 / value2
            }

            // SecondActivityに渡す
            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("RESULT", result)

            startActivity(intent)

        }catch(e:Exception){
            //例外が起きた際のコードをかく。
            Snackbar.make(v, "数値を入力してください", Snackbar.LENGTH_INDEFINITE)
                .setAction("了解"){
                }.show()
            return // ここでtry-catchを抜ける
        }
    }
}

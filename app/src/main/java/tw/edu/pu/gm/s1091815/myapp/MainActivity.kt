package tw.edu.pu.gm.s1091815.myapp

import android.content.pm.ActivityInfo
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import tw.edu.pu.gm.s1091815.myapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var img: ImageView
    lateinit var mysv:MySurfaceView
    lateinit var binding: ActivityMainBinding
    var flag:Boolean = false
    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.img.setOnClickListener({
            if (flag){
                flag = false
                binding.img.setImageResource(R.drawable.start)
                job.cancel()
            }
            else{
                flag = true
                binding.img.setImageResource(R.drawable.stop)
                job = GlobalScope.launch(Dispatchers.Main) {
                    while(flag) {
                        delay(1)

                        var canvas: Canvas = binding.mysv.surfaceHolder.lockCanvas()
                        binding.mysv.drawSomething(canvas)
                        binding.mysv.surfaceHolder.unlockCanvasAndPost(canvas)
                    }
                }

            }
        })

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }
}

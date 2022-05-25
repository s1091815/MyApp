package tw.edu.pu.gm.s1091815.myapp

import android.content.pm.ActivityInfo
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.coroutines.*
import tw.edu.pu.gm.s1091815.myapp.databinding.ActivityMainBinding
@GlideModule
public final class MyAppGlideModule : AppGlideModule()


class MainActivity : AppCompatActivity() {

    lateinit var img: ImageView
    lateinit var mysv:MySurfaceView
    lateinit var binding: ActivityMainBinding
    var flag:Boolean = false
    lateinit var job: Job
    lateinit var handsomechang : ImageView
    lateinit var fly1:plane

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlideApp.with(this)
            .load(R.drawable.me)
            .circleCrop()
            .override(800, 600)
            .into(binding.handsomechang)

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
                        binding.mysv.fly1.update()

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

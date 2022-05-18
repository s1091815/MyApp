package tw.edu.pu.gm.s1091815.myapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class MySurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs), SurfaceHolder.Callback {
    lateinit var surfaceHolder: SurfaceHolder
    lateinit var BG: Bitmap
    init {
        surfaceHolder = getHolder()
        BG = BitmapFactory.decodeResource(getResources(), R.drawable.background)
        surfaceHolder.addCallback(this)
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {

    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        var canvas: Canvas = surfaceHolder.lockCanvas()
        drawSomething(canvas)
        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    fun drawSomething(canvas:Canvas) {
        val W = this.width
        val H = this.height
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val resizeBG = Bitmap.createScaledBitmap(BG, W, H, true)
        canvas.drawBitmap(resizeBG, 0f, 0f, null)
        paint.color = Color.BLUE
        paint.textSize = 50f
        canvas.drawText("射擊遊戲(作者：張家豪)", 50f,50f, paint)
    }
}
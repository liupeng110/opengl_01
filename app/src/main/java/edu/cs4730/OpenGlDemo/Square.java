package edu.cs4730.OpenGlDemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class Square {

	private float vertices[] = {   // 4  顶点
			-1.0f,  0.5f, 0.0f,    // 0  Top Left
			-1.0f, -0.5f, 0.0f    // 1  Bottom Left
//		    ,1.0f, -0.5f, 0.0f,   // 2  Bottom Right
//			 1.0f,  0.5f, 0.0f,    // 3  Top Right
	};


	private short[] indices = { 0, 1, 2, 0, 2, 3 }; // 想要连接的点  indexs
	private FloatBuffer vertexBuffer;//顶点缓冲区
	private ShortBuffer indexBuffer; //索引缓冲区

	public Square() {
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);//分配系统内存,jvm之外  操作多时 节省时间
		vbb.order(ByteOrder.nativeOrder());//设置字节顺序为本地操作系统顺序
		vertexBuffer = vbb.asFloatBuffer();//转换为浮点(Float)型缓冲
		vertexBuffer.put(vertices);        //在缓冲区内写入数据（这里写入顶点）
		vertexBuffer.position(0);          //设置缓冲区起始位置
	}

	  public void draw(GL10 gl) {
		 gl.glFrontFace(GL10.GL_CCW);          // 逆时针
		 gl.glEnable(GL10.GL_CULL_FACE);       // 设置剔除背面
		 gl.glCullFace(GL10.GL_BACK);

		  gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f); //设置绘制颜色
		  gl.glPointSize(16f);
		  gl.glLoadIdentity();
		  gl.glTranslatef(0, 0, -4);            //默认渲染位置 000

		  gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);////在上边打开在下边就要关闭,这个原因是然后边的绘制不会复用前边的颜色等的样式
		  gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);//设置顶点数据 3代表xyz
		  gl.glDrawArrays(GL10.GL_POINTS, 0, 2);   //绘制 2点-1点-3点

		  DrawLine(gl);

	  }


	public void DrawLine(GL10 gl) {
		gl.glLineWidth(8);
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
		vbb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = vbb.asFloatBuffer();
		vertex.put(vertices);
		vertex.position(0);//顶点数据 转化


		gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
		gl.glColor4f(0.0f, 1.0f, 0.0f, 1.0f);
		gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);// 设置顶点数据，3代表XYZ坐标系
		gl.glDrawArrays(GL10.GL_LINES, 0, 2);           // 最后参数表示绘制2点

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);//最下面关闭
	}


}


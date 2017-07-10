package edu.cs4730.OpenGlDemo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class OpenGLRenderer implements Renderer {

	// Initialize our square.
	Square square = new Square();//初始化自定义渲染器


	@Override public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);  // 背景
		gl.glShadeModel(GL10.GL_SMOOTH);          // 启用平滑阴影
		gl.glClearDepthf(1.0f);                   // 深度缓冲设置
		gl.glEnable(GL10.GL_DEPTH_TEST);          // 启用深度测试
		gl.glDepthFunc(GL10.GL_LEQUAL);           //要做的深度测试的类型。
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_NICEST); // 透视计算
	}


	@Override public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);//清除屏幕和深度缓冲区
		gl.glLoadIdentity();                                            //用恒等矩阵替换当前矩阵
		gl.glTranslatef(0, 0, -4); //平移4单位

		square.draw(gl);
	}

	@Override public void onSurfaceChanged(GL10 gl, int width, int height) {

		gl.glViewport(330, 330, width, height);// 设置窗口大小
		gl.glMatrixMode(GL10.GL_PROJECTION);   // 选择投影矩阵
		gl.glLoadIdentity();                   // 重置投影矩阵
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height,0.8f, 100.0f);//计算裁剪窗口的长宽比

		gl.glMatrixMode(GL10.GL_MODELVIEW);   // 选择模型矩阵
		gl.glLoadIdentity();                  // 重置模型矩阵
	}
}


package com.richmobi.amwayfc.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeComponent;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;

public class CutImage extends JPanel{
	private static final long serialVersionUID = 1L;

	// 行分隔符
	final static public String LS = System.getProperty("line.separator", "\n");

	// 文件分割符
	final static public String FS = System.getProperty("file.separator", "\\");

	//以javascript脚本获得网页全屏后大小
	final static StringBuffer jsDimension;
	static {
		jsDimension = new StringBuffer();
		jsDimension.append("var width = 0;").append(LS);
		jsDimension.append("var height = 0;").append(LS);
		jsDimension.append("if(document.documentElement) {").append(LS);
		jsDimension.append(
						"  width = Math.max(width, document.documentElement.scrollWidth);")
				.append(LS);
		jsDimension.append(
						"  height = Math.max(height, document.documentElement.scrollHeight);")
				.append(LS);
		jsDimension.append("}").append(LS);
		jsDimension.append("if(self.innerWidth) {").append(LS);
		jsDimension.append("  width = Math.max(width, self.innerWidth);")
				.append(LS);
		jsDimension.append("  height = Math.max(height, self.innerHeight);")
				.append(LS);
		jsDimension.append("}").append(LS);
		jsDimension.append("if(document.body.scrollWidth) {").append(LS);
		jsDimension.append(
				"  width = Math.max(width, document.body.scrollWidth);")
				.append(LS);
		jsDimension.append(
				"  height = Math.max(height, document.body.scrollHeight);")
				.append(LS);
		jsDimension.append("}").append(LS);
		jsDimension.append("return width + ':' + height;");
	}
	public CutImage(final String url, final int maxWidth, final int maxHeight,final int realHeight, final String outSrc,final Object lock) {
		super(new BorderLayout());
		
//		try {
//			t.wait();
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		final Thread mainThead = Thread.currentThread();
		final JPanel webBrowserPanel = new JPanel(new BorderLayout());
		final JWebBrowser webBrowser = new JWebBrowser(null);
		 SwingUtilities.invokeLater(new Runnable() {
				public void run()
				{
					try{
						System.err.println("init thread:" + Thread.currentThread().getName());
					webBrowser.setBarsVisible(false);
					System.out.println("需要截图的页面url为：" + url);
					webBrowser.navigate(url);
					}catch (Exception e) {
						e.printStackTrace();
					}
				webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
				add(webBrowserPanel, BorderLayout.CENTER);

				JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
				webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
					// 监听加载进度
					public void loadingProgressChanged(WebBrowserEvent e) {
						// 当加载完毕时
						if (e.getWebBrowser().getLoadingProgress() == 100) {
							String result = (String) webBrowser
									.executeJavascriptWithResult(jsDimension.toString());
							int index = result == null ? -1 : result.indexOf(":");
							NativeComponent nativeComponent = webBrowser
									.getNativeComponent();
							Dimension originalSize = nativeComponent.getSize();
							Dimension imageSize = new Dimension(Integer.parseInt(result
									.substring(0, index)), Integer.parseInt(result
									.substring(index + 1)));
							imageSize.width = Math.max(originalSize.width,
									imageSize.width + 50);
							imageSize.height = Math.max(originalSize.height,
									imageSize.height + 50);
							System.out.println(imageSize.height+" "+imageSize.width);
							nativeComponent.setSize(imageSize);
							BufferedImage image = new BufferedImage(imageSize.width,
									imageSize.height, BufferedImage.TYPE_INT_RGB);
							nativeComponent.paintComponent(image);
							nativeComponent.setSize(originalSize);
							// 当网页超出目标大小时
							if (imageSize.width > maxWidth
									|| imageSize.height > maxHeight) {
								//截图部分图形
								image = image.getSubimage(0, 0, maxWidth, maxHeight);
							
								int width = image.getWidth(), height = image
									.getHeight();
								 AffineTransform tx = new AffineTransform();
								tx.scale((double) maxWidth / width, (double) maxHeight
										/ height);
								AffineTransformOp op = new AffineTransformOp(tx,
										AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
								//缩小
								image = op.filter(image, null);
							}
							try {
								//裁图
								image = image.getSubimage(156,135,957,realHeight);
								// 输出图像
								ImageIO.write(image, "jpg", new File(outSrc));
								webBrowser.removeAll();
							} catch (IOException ex) {
								ex.printStackTrace();
							}
							System.out.println("截图完毕");
							synchronized (lock) {
								lock.notifyAll();
							}
						}
					}
				}

				);
				add(panel, BorderLayout.SOUTH);
				}
				
			});
		
		

	}
	public void cut(final String basePath,final String fileName,final CutImage ci){
		NativeInterface.open();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				System.err.println("start to cut image thread:" + Thread.currentThread().getName());
				// SWT组件转Swing组件，不初始化父窗体将无法启动webBrowser
				JFrame frame = new JFrame("");
				// 加载google，最大保存为640x480的截图
				frame.getContentPane().add(
						ci,
						BorderLayout.CENTER);
				frame.setSize(5000, 4000);
				// 仅初始化，但不显示
				frame.invalidate();
				frame.pack();
				frame.setVisible(false);
				
			}
		});
		
//		// SWT组件转Swing组件，不初始化父窗体将无法启动webBrowser
//		JFrame frame = new JFrame("");
//		// 加载google，最大保存为640x480的截图
//		frame.getContentPane().add(
//				ci,
//				BorderLayout.CENTER);
//		frame.setSize(5000, 4000);
//		// 仅初始化，但不显示
//		frame.invalidate();
//		frame.pack();
//		frame.setVisible(false);
		

//		NativeInterface.runEventPump();

	}
//	public static void main(String[] args) {
//		NativeInterface.open();
//		 
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				// SWT组件转Swing组件，不初始化父窗体将无法启动webBrowser
//				JFrame frame = new JFrame("");
//				// 加载google，最大保存为640x480的截图
//				String fileName = System.currentTimeMillis() + ".jpg";
//				frame.getContentPane().add(
//						new CutImage("http://t.cn/zWihOGr", 5000, 4000,"f:\\he1"+fileName),
//						BorderLayout.CENTER);
//				frame.setSize(5000, 4000);
//				// 仅初始化，但不显示
//				frame.invalidate();
//				frame.pack();
//				frame.setVisible(false);
//			}
//		});
//		NativeInterface.runEventPump();
//	}
	
}

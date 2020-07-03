package handong.edu.csee.s;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageIOSurpporter {

	public ImageIOSurpporter() { 	}
	
	private File selectedFile;
	private String filePath;
	private BufferedImage buffImage;
	
	//파일 오프으으은 
	public void openFile() {
		JFileChooser fileChooser = new JFileChooser();
		//디폴트 위치 셋업
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		// 확장자 필터 
		 FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("*.Images", "jpg","jpeg","gif","png");
		
		 fileChooser.setAcceptAllFileFilterUsed(false);
		 fileChooser.addChoosableFileFilter(fileFilter);
		 int result = fileChooser.showDialog(null, null);
		
		 
		 if(result == JFileChooser.APPROVE_OPTION) {
			 selectedFile = fileChooser.getSelectedFile();
			 filePath = selectedFile.getAbsolutePath();
		 }
		 else if(result == JFileChooser.CANCEL_OPTION) {
			 System.out.println("No File Select");
		 }
	}
	
	public BufferedImage ResizeImage(int w, int h) {
		try {
			buffImage = ImageIO.read(new File(filePath));
			Image temp = buffImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			buffImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			
			Graphics2D g2d = buffImage.createGraphics();
			g2d.drawImage(temp, 0, 0, null);
			g2d.dispose();
			    
		} catch (IOException e) {
			e.printStackTrace();
		}
        return buffImage;
	}
}

package handong.edu.csee.s;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JSlider;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class WorkFrame extends JComponent implements ActionListener, ChangeListener{

	private JFrame workFrame = new JFrame("Work Space");
	private JMenuItem openMenuItem, saveMenuItem;
	private ImageIOSurpporter supporter = new ImageIOSurpporter();
	private JLabel imageLabel, magnifyLabel;
	private JSlider SaturationSlider, ContrastSlider, exposureSlider;
	private JLabel exposureLevelLabel, contrastLevelLabel, saturationLevelLabel;
	private BufferedImage nowImage, tempImage;
	private JCheckBox edgeCheckBox, magnifyCheckBox;
	private JCheckBox BWCheckBox;
	private JLabel magnifyingLabel;

	
	private Double constrastFactor;
	private  Double saturationFactor; 
	private float brightenFactor;
	
	private boolean doBW;
	
	
	
	public WorkFrame() {
		workFrame.getContentPane().setFont(new Font("DXMSubtitlesStd", workFrame.getContentPane().getFont().getStyle(),
				workFrame.getContentPane().getFont().getSize()));
		workFrame.setSize(1100, 800);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(
				new Font("DX\uC2DC\uC778\uACFC\uB098", menuBar.getFont().getStyle(), menuBar.getFont().getSize()));
		workFrame.getContentPane().setBackground(Color.GRAY);
		workFrame.getContentPane().setLayout(null);

	
		JLabel exposureLabel = new JLabel("Exposure");
		exposureLabel.setFont(new Font("DXMSubtitlesStd", exposureLabel.getFont().getStyle(), 13));
		exposureLabel.setBounds(933, 33, 61, 16);
		workFrame.getContentPane().add(exposureLabel);

		exposureLevelLabel = new JLabel("0");
		exposureLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exposureLevelLabel.setFont(new Font("DXMSubtitlesStd", exposureLabel.getFont().getStyle(), 13));
		exposureLevelLabel.setBounds(1017, 33, 61, 16);
		workFrame.getContentPane().add(exposureLevelLabel);

		exposureSlider = new JSlider();
		exposureSlider.setFont(
				new Font("DXMSubtitlesStd", exposureSlider.getFont().getStyle(), exposureSlider.getFont().getSize()));
		exposureSlider.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Exposure",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		exposureSlider.setBackground(Color.DARK_GRAY);
		exposureSlider.setValue(100);
		exposureSlider.setSnapToTicks(true);
		exposureSlider.setMinorTickSpacing(1);
		exposureSlider.setMajorTickSpacing(10);
		exposureSlider.setMaximum(200);
		exposureSlider.setBounds(910, 60, 190, 53);
		workFrame.getContentPane().add(exposureSlider);

		JLabel contrastLabel = new JLabel("Contrast");
		contrastLabel.setFont(
				new Font("DXMSubtitlesStd", contrastLabel.getFont().getStyle(), contrastLabel.getFont().getSize()));
		contrastLabel.setBounds(933, 125, 61, 16);
		workFrame.getContentPane().add(contrastLabel);

		contrastLevelLabel = new JLabel("0");
		contrastLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contrastLevelLabel.setFont(
				new Font("DXMSubtitlesStd", contrastLabel.getFont().getStyle(), contrastLabel.getFont().getSize()));
		contrastLevelLabel.setBounds(1017, 125, 61, 16);
		workFrame.getContentPane().add(contrastLevelLabel);

		ContrastSlider = new JSlider();
		ContrastSlider.setFont(
				new Font("DXMSubtitlesStd", ContrastSlider.getFont().getStyle(), ContrastSlider.getFont().getSize()));
		ContrastSlider.setValue(0);
		ContrastSlider.setSnapToTicks(true);
		ContrastSlider.setMinorTickSpacing(1);
		ContrastSlider.setMinimum(-50);
		ContrastSlider.setMaximum(50);
		ContrastSlider.setMajorTickSpacing(10);
		ContrastSlider.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Contrast",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ContrastSlider.setBackground(Color.DARK_GRAY);
		ContrastSlider.setBounds(910, 152, 190, 53);
		workFrame.getContentPane().add(ContrastSlider);

		JLabel saturationLabel = new JLabel("Saturation");
		saturationLabel.setFont(
				new Font("DXMSubtitlesStd", saturationLabel.getFont().getStyle(), saturationLabel.getFont().getSize()));
		saturationLabel.setBounds(933, 217, 61, 16);
		workFrame.getContentPane().add(saturationLabel);

		saturationLevelLabel = new JLabel("0");
		saturationLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saturationLevelLabel.setFont(
				new Font("DXMSubtitlesStd", saturationLabel.getFont().getStyle(), saturationLabel.getFont().getSize()));
		saturationLevelLabel.setBounds(1017, 217, 61, 16);
		workFrame.getContentPane().add(saturationLevelLabel);

		SaturationSlider = new JSlider();
		SaturationSlider.setFont(new Font("DXMSubtitlesStd", SaturationSlider.getFont().getStyle(),
				SaturationSlider.getFont().getSize()));
		SaturationSlider.setValue(100);
		SaturationSlider.setSnapToTicks(true);
		SaturationSlider.setMinorTickSpacing(1);
		SaturationSlider.setMaximum(200);
		SaturationSlider.setMajorTickSpacing(10);
		SaturationSlider.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Saturation",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		SaturationSlider.setBackground(Color.DARK_GRAY);
		SaturationSlider.setBounds(910, 244, 190, 53);
		workFrame.getContentPane().add(SaturationSlider);

		edgeCheckBox = new JCheckBox("Edge Extraction");
		edgeCheckBox.setFont(
				new Font("DXMSubtitlesStd", edgeCheckBox.getFont().getStyle(), edgeCheckBox.getFont().getSize()));
		edgeCheckBox.setBounds(910, 333, 184, 23);
		workFrame.getContentPane().add(edgeCheckBox);

		magnifyCheckBox = new JCheckBox("Magnifying Helper");
		magnifyCheckBox.setFont(
				new Font("DXMSubtitlesStd", edgeCheckBox.getFont().getStyle(), edgeCheckBox.getFont().getSize()));
		magnifyCheckBox.setBounds(910, 423, 184, 23);
		workFrame.getContentPane().add(magnifyCheckBox);

		imageLabel = new JLabel(" ");
		imageLabel.setBounds(30, 75, 800, 600);
		workFrame.getContentPane().add(imageLabel);
		
		BWCheckBox = new JCheckBox("Black n White View");
		BWCheckBox.setFont(new Font("DXMSubtitlesStd", BWCheckBox.getFont().getStyle(), BWCheckBox.getFont().getSize()));
		BWCheckBox.setBounds(910, 378, 184, 23);
		workFrame.getContentPane().add(BWCheckBox);
		
		magnifyingLabel = new JLabel(" ");
		magnifyingLabel.setBounds(910, 567, 170, 170);
		workFrame.getContentPane().add(magnifyingLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(937, 458, 141, 23);
		workFrame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setBounds(937, 493, 141, 23);
		workFrame.getContentPane().add(rdbtnNewRadioButton_1);
		
		JRadioButton radioButton = new JRadioButton("New radio button");
		radioButton.setBounds(937, 528, 141, 23);
		workFrame.getContentPane().add(radioButton);
		workFrame.setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File / 파일");
		fileMenu.setFont(new Font("DXMSubtitlesStd", fileMenu.getFont().getStyle(), 14));
		menuBar.add(fileMenu);

		openMenuItem = new JMenuItem("Open File");
		openMenuItem.setFont(new Font("DXMSubtitlesStd", openMenuItem.getFont().getStyle(), 14));
		fileMenu.add(openMenuItem);

		saveMenuItem = new JMenuItem("Save File");
		saveMenuItem.setFont(new Font("DXMSubtitlesStd", openMenuItem.getFont().getStyle(), 14));
		fileMenu.add(saveMenuItem);
		// TODO Auto-generated constructor stub

		workFrame.setVisible(true);

		openMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);

		exposureSlider.addChangeListener(this);
		ContrastSlider.addChangeListener(this);
		SaturationSlider.addChangeListener(this);

		magnifyCheckBox.addActionListener(this);
		edgeCheckBox.addActionListener(this);
		BWCheckBox.addActionListener(this);
		
	}

	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
//		constrastFactor = ContrastSlider.getValue() * 0.01;
//		 saturationFactor = SaturationSlider.getValue() * 0.01;
//		 brightenFactor = exposureSlider.getValue() * 0.01F;
		 
		if (e.getSource().equals(openMenuItem)) {
			System.out.println("FILE OPEN");
			supporter.openFile();
			nowImage = supporter.ResizeImage();
			tempImage = deepCopy(nowImage);

			imageLabel.setIcon(new ImageIcon(tempImage));
			imageLabel.revalidate();
			imageLabel.repaint();
		}
		else if (e.getSource().equals(BWCheckBox)) {
			doBW = !doBW;
			System.out.println(doBW);
			setPhotoInfo(brightenFactor, constrastFactor ,saturationFactor);

		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		tempImage = deepCopy(nowImage);
		
		
		
		// 단순하게 필터링해서 그리는 거
		if (e.getSource().equals(exposureSlider)|| e.getSource().equals(SaturationSlider)) {
			setPhotoInfo(brightenFactor, constrastFactor ,saturationFactor);

//			RescaleOp op = new RescaleOp(brightenFactor, 0, null);
//			tempImage = op.filter(tempImage, tempImage);
//			
//			for (int x = 0; x < nowImage.getWidth(); x++) {
//				for (int y = 0; y < nowImage.getHeight(); y++) {
//					Color color = new Color(tempImage.getRGB(x, y));
//					float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
//					
//				    float hue = hsb[0]; 
//				    float saturation = hsb[1];
//				    float brightness = hsb[2];
//					saturation = (float) (saturation*saturationFactor);
//
//					if(saturation>1)
//						saturation = 1;
//					
//					tempImage.setRGB(x,y, Color.HSBtoRGB(hue, saturation, brightness));
//					
//				}
//			}
//			
//			imageLabel.setIcon(new ImageIcon(tempImage));
//			imageLabel.revalidate();
//			imageLabel.repaint();
		}
//		else if (e.getSource().equals(ContrastSlider) ) {
//			// 컨트라스트 어떻게 계산하는지 모르겠음;
//			for (int x = 0; x < nowImage.getWidth(); x++) {
//				for (int y = 0; y < nowImage.getHeight(); y++) {
//					Color color = new Color(tempImage.getRGB(x, y));
//					float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
//					
//				    float hue = hsb[0]; 
//				    float saturation = hsb[1];
//				    float brightness = hsb[2];
//					
//				    	if(brightness>0.55) {
//					    	brightness = (float) (brightness + constrastFactor);
//					    	if(brightness >0.9 ) brightness = (float) 0.9;
//					    }
//					    else {
//					    	brightness = (float) (brightness - constrastFactor);
//					    	if(brightness<0.) brightness =(float) 0.2;
//					    }
//				   
//				    	saturation = (float) (saturation*saturationFactor);
//
//						if(saturation>1)
//							saturation = 1;
//					
//					tempImage.setRGB(x,y, Color.HSBtoRGB(hue, saturation, brightness));
//				}
//			}
//			imageLabel.setIcon(new ImageIcon(tempImage));
//			imageLabel.revalidate();
//			imageLabel.repaint();
//			
//		}
		

		setPhotoInfo(brightenFactor, constrastFactor ,saturationFactor);
	}
	
	public void setPhotoInfo (float brightenFactor,Double constrastFactor,Double saturationFactor) {

		tempImage = deepCopy(nowImage);
		
		exposureLevelLabel.setText(String.format("%.2f", exposureSlider.getValue() / 50.0 - 2));
		contrastLevelLabel.setText(String.valueOf(ContrastSlider.getValue()));
		saturationLevelLabel.setText(String.format("%.2f", SaturationSlider.getValue() / 50.0 - 2));
		
		constrastFactor = ContrastSlider.getValue() * 0.01;
		saturationFactor = SaturationSlider.getValue() * 0.01;
		brightenFactor = exposureSlider.getValue() * 0.01F;
		
		//exposure 조절 
		RescaleOp op = new RescaleOp(brightenFactor, 0, null);
		tempImage = op.filter(tempImage, tempImage);
		
		//saturation 조절
		for (int x = 0; x < nowImage.getWidth(); x++) {
			for (int y = 0; y < nowImage.getHeight(); y++) {
				Color color = new Color(tempImage.getRGB(x, y));
				float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
				
			    float hue = hsb[0]; 
			    float saturation = hsb[1];
			    float brightness = hsb[2];
				saturation = (float) (saturation*saturationFactor);

				if(saturation>1)
					saturation = 1;
				
				tempImage.setRGB(x,y, Color.HSBtoRGB(hue, saturation, brightness));
				
				//흑백인 경우 노출,  채도 반영한 뒤에  그 위에 흑백으로
				if(doBW) {
					Color BWColor = new Color(tempImage.getRGB(x, y));
					int BWValue = (int) (
							0.2126 * BWColor.getRed()
							+ 0.7152 * BWColor.getGreen()
							+ 0.0722 * BWColor.getBlue());
					tempImage.setRGB(x, y, new Color(BWValue, BWValue,BWValue).getRGB());
				}
				
			}
		}
		
		imageLabel.setIcon(new ImageIcon(tempImage));
		imageLabel.revalidate();
		imageLabel.repaint();
	}
}

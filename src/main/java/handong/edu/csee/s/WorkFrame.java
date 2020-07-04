package handong.edu.csee.s;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;
import javax.swing.border.EtchedBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class WorkFrame extends JComponent implements ActionListener, ChangeListener, MouseMotionListener, MouseInputListener{

	private JFrame workFrame = new JFrame("Work Space");
	private JMenuItem openMenuItem, saveMenuItem,  composeFileLoadMenuItem ;
	private ImageIOSurpporter supporter = new ImageIOSurpporter();
	private ImageIOSurpporter composeSup = new ImageIOSurpporter();
	private JLabel imageLabel, magnifyLabel;
	private JSlider SaturationSlider, ContrastSlider, exposureSlider;
	private JLabel exposureLevelLabel, contrastLevelLabel, saturationLevelLabel;
	private BufferedImage originImage, tempImage;
	private JCheckBox edgeCheckBox, magnifyCheckBox;
	private JCheckBox BWCheckBox;
	private JLabel magnifyingLabel;
	private JRadioButton radio100Button,radio150Button,radio200Button;
	private ButtonGroup group ;
	private Double constrastFactor;
	private  Double saturationFactor; 
	private float brightenFactor;
	
	private boolean doBW, doMag;
	
	private int testCount=0;
	private boolean doCompose;
	
	private BufferedImage composeImage;
	private BufferedImage compoImageTemp;
	private Color ImageBackColor;
	private Point clickPoint;
	
	private JComboBox comboBox;
	
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
		exposureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exposureLabel.setFont(new Font("DXMSubtitlesStd", exposureLabel.getFont().getStyle(), 15));
		exposureLabel.setBounds(865, 6, 97, 16);
		workFrame.getContentPane().add(exposureLabel);

		exposureLevelLabel = new JLabel("0");
		exposureLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exposureLevelLabel.setFont(new Font("DXMSubtitlesStd", exposureLabel.getFont().getStyle(), 13));
		exposureLevelLabel.setBounds(949, 6, 151, 16);
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
		exposureSlider.setBounds(842, 33, 258, 53);
		workFrame.getContentPane().add(exposureSlider);

		JLabel contrastLabel = new JLabel("Contrast");
		contrastLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contrastLabel.setFont(
				new Font("DXMSubtitlesStd", exposureLabel.getFont().getStyle(), 15));
		contrastLabel.setBounds(865, 98, 97, 16);
		workFrame.getContentPane().add(contrastLabel);

		contrastLevelLabel = new JLabel("0");
		contrastLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contrastLevelLabel.setFont(
				new Font("DXMSubtitlesStd", contrastLabel.getFont().getStyle(), contrastLabel.getFont().getSize()));
		contrastLevelLabel.setBounds(949, 98, 151, 16);
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
		ContrastSlider.setBounds(842, 125, 258, 53);
		workFrame.getContentPane().add(ContrastSlider);

		JLabel saturationLabel = new JLabel("Saturation");
		saturationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saturationLabel.setFont(
				new Font("DXMSubtitlesStd", exposureLabel.getFont().getStyle(), 15));
		saturationLabel.setBounds(865, 190, 97, 16);
		workFrame.getContentPane().add(saturationLabel);

		saturationLevelLabel = new JLabel("0");
		saturationLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saturationLevelLabel.setFont(
				new Font("DXMSubtitlesStd", saturationLabel.getFont().getStyle(), saturationLabel.getFont().getSize()));
		saturationLevelLabel.setBounds(949, 190, 151, 16);
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
		SaturationSlider.setBounds(842, 217, 258, 53);
		workFrame.getContentPane().add(SaturationSlider);

		edgeCheckBox = new JCheckBox("Edge Extraction");
		edgeCheckBox.setFont(
				new Font("DXMSubtitlesStd", edgeCheckBox.getFont().getStyle(), edgeCheckBox.getFont().getSize() + 2));
		edgeCheckBox.setBounds(860, 280, 184, 23);
		workFrame.getContentPane().add(edgeCheckBox);

		magnifyCheckBox = new JCheckBox("Magnifying Helper");
		magnifyCheckBox.setFont(
				new Font("DXMSubtitlesStd", edgeCheckBox.getFont().getStyle(), edgeCheckBox.getFont().getSize()));
		magnifyCheckBox.setBounds(860, 370, 184, 23);
		workFrame.getContentPane().add(magnifyCheckBox);

		imageLabel = new JLabel(" ");
		imageLabel.setBounds(30, 75, 800, 600);
		workFrame.getContentPane().add(imageLabel);
		
		BWCheckBox = new JCheckBox("Black n White View");
		BWCheckBox.setFont(new Font("DXMSubtitlesStd", BWCheckBox.getFont().getStyle(), 15));
		BWCheckBox.setBounds(860, 325, 184, 23);
		workFrame.getContentPane().add(BWCheckBox);
		
		magnifyingLabel = new JLabel(" ");
		magnifyingLabel.setBounds(854, 510, 240, 240);
		workFrame.getContentPane().add(magnifyingLabel);
		
		 radio100Button = new JRadioButton("100%");
		radio100Button.setFont(new Font("DXMSubtitlesStd", radio100Button.getFont().getStyle(), radio100Button.getFont().getSize()));
		radio100Button.setBounds(887, 405, 141, 23);
		workFrame.getContentPane().add(radio100Button);
		radio100Button.setVisible(false);
		
		 radio150Button = new JRadioButton("150%");
		radio150Button.setFont(new Font("DXMSubtitlesStd", radio100Button.getFont().getStyle(), radio100Button.getFont().getSize()));
		radio150Button.setBounds(887, 440, 141, 23);
		workFrame.getContentPane().add(radio150Button);
		radio150Button.setVisible(false);

		 radio200Button = new JRadioButton("200%");
		radio200Button.setFont(new Font("DXMSubtitlesStd", radio100Button.getFont().getStyle(), radio100Button.getFont().getSize()));
		radio200Button.setBounds(887, 475, 141, 23);
		workFrame.getContentPane().add(radio200Button);
		workFrame.setJMenuBar(menuBar);
		radio200Button.setVisible(false);

		group = new ButtonGroup();
		group.add(radio100Button);
		group.add(radio150Button);
		group.add(radio200Button);
		radio100Button.setSelected(true);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("DXMSubtitlesStd", comboBox.getFont().getStyle(), comboBox.getFont().getSize()));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"100 x   75  - S", "200 x 150  - M", "400 x 300  - L", "600 x 450  - XL"}));
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(611, 36, 184, 27);
		workFrame.getContentPane().add(comboBox);
		comboBox.setVisible(false);
		

		JMenu fileMenu = new JMenu("File / 파일");
		fileMenu.setFont(new Font("DXMSubtitlesStd", fileMenu.getFont().getStyle(), 14));
		menuBar.add(fileMenu);

		openMenuItem = new JMenuItem("Open File");
		openMenuItem.setFont(new Font("DXMSubtitlesStd", openMenuItem.getFont().getStyle(), 14));
		fileMenu.add(openMenuItem);

		saveMenuItem = new JMenuItem("Save File");
		saveMenuItem.setFont(new Font("DXMSubtitlesStd", openMenuItem.getFont().getStyle(), 14));
		fileMenu.add(saveMenuItem);
		
		JMenu composeMenu = new JMenu("Compose / 합성");
		composeMenu.setFont(new Font("DXMSubtitlesStd", composeMenu.getFont().getStyle(), composeMenu.getFont().getSize()));
		menuBar.add(composeMenu);
		
		 composeFileLoadMenuItem = new JMenuItem("Load File");
		composeFileLoadMenuItem.setFont(new Font("DXMSubtitlesStd", composeFileLoadMenuItem.getFont().getStyle(), composeFileLoadMenuItem.getFont().getSize()));
		composeMenu.add(composeFileLoadMenuItem);

		workFrame.setVisible(true);

		openMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);
		composeFileLoadMenuItem.addActionListener(this);
		
		exposureSlider.addChangeListener(this);
		ContrastSlider.addChangeListener(this);
		SaturationSlider.addChangeListener(this);

		magnifyCheckBox.addActionListener(this);
		edgeCheckBox.addActionListener(this);
		BWCheckBox.addActionListener(this);
		radio100Button.addActionListener(this);
		radio150Button.addActionListener(this);
		radio200Button.addActionListener(this);

		imageLabel.addMouseMotionListener(this);
		imageLabel.addMouseListener(this);
	}

	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//composeFileLoadMenuItem
		if (e.getSource().equals(openMenuItem)) {
			System.out.println("FILE OPEN");
			supporter.openFile();
			originImage = supporter.ResizeImage(imageLabel.getWidth(),imageLabel.getHeight());
			tempImage = deepCopy(originImage);

			imageLabel.setIcon(new ImageIcon(tempImage));
			imageLabel.revalidate();
			imageLabel.repaint();
		}
		else if (e.getSource().equals(composeFileLoadMenuItem)) {

			System.out.println("Compose");
			composeSup.openFile();
			composeImage = composeSup.ResizeImage(200, 150);
			//XL 600 450
			//L 400 300 
			//M 200 150
			//S 100 75
			doCompose = true;
			comboBox.setVisible(doCompose);
			
			

		} else if (e.getSource().equals(saveMenuItem)) {

			System.out.println("Save");
			supporter.saveImage(tempImage);

		}
		else if (e.getSource().equals(BWCheckBox)) {
			
			doBW = !doBW;
			System.out.println(doBW);
			setPhotoInfo(brightenFactor, constrastFactor ,saturationFactor);
			

		}
		else if(e.getSource().equals(magnifyCheckBox)) {
			
			doMag = !doMag;
			
			radio100Button.setVisible(doMag);
			radio150Button.setVisible(doMag);
			radio200Button.setVisible(doMag);
			magnifyingLabel.setVisible(doMag);

		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		// 단순하게 필터링해서 그리는 거
		if (e.getSource().equals(exposureSlider)|| e.getSource().equals(SaturationSlider) || e.getSource().equals(ContrastSlider)) {
			setPhotoInfo(brightenFactor, constrastFactor ,saturationFactor);
		}
	}
	
	public void setPhotoInfo (float brightenFactor,Double constrastFactor,Double saturationFactor) {

		exposureLevelLabel.setText(String.format("%.2f", exposureSlider.getValue() / 50.0 - 2));
		contrastLevelLabel.setText(String.valueOf(ContrastSlider.getValue()));
		saturationLevelLabel.setText(String.format("%.2f", SaturationSlider.getValue() / 50.0 - 2));
		
		constrastFactor = ContrastSlider.getValue() * 0.01;
		saturationFactor = SaturationSlider.getValue() * 0.01;
		brightenFactor = exposureSlider.getValue() * 0.01F;
		
		tempImage = deepCopy(originImage);
		
		//exposure 조절 
		RescaleOp op = new RescaleOp(brightenFactor, 0, null);
		tempImage = op.filter(tempImage, tempImage);
		
		//saturation 조절
		for (int x = 0; x < originImage.getWidth(); x++) {
			for (int y = 0; y < originImage.getHeight(); y++) {
				
				Color color = new Color(tempImage.getRGB(x, y));
				float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
				
			    float hue = hsb[0]; 
			    float saturation = hsb[1];
			    float brightness = hsb[2];
				saturation = (float) (saturation*saturationFactor);

				if(saturation>1)
					saturation = 1;
				
				//고대비를 만들때, 뭉쳐있던 친구들을 나눠서 차이를 선명하게 둠 
				if(constrastFactor>0) {
					
					// 한 임계값으로 다가가되, 다가가는 속도의 차이를 둠 
					if(brightness>0.5) {// 밝은 친구들은 더 밝은 곳으로 
						if(brightness>0.5 && brightness<0.6 ) {
							brightness = (float) (brightness + constrastFactor*0.5);
						}
						else if(brightness>0.6 && brightness<0.7) {
							brightness = (float) (brightness + constrastFactor*0.4);
						}
						else if (brightness > 0.7 && brightness < 0.8) {
							brightness = (float) (brightness + constrastFactor*0.3);
						}
						else if (brightness > 0.8 && brightness < 0.9) {
							brightness = (float) (brightness + constrastFactor*0.2);
						} 
						else if (brightness > 0.9 && brightness < 1) {
							brightness = (float) (brightness + constrastFactor*0.1);
						}
						
						if(brightness > 1)
							brightness = 1;
					}
					else {	// 어두운 친구들은 더 어두운 곳으로 
						if(brightness>0.4 && brightness<0.5 ) {
							brightness = (float) (brightness - constrastFactor*0.5);
						}
						else if(brightness>0.3 && brightness<0.4) {
							brightness = (float) (brightness - constrastFactor*0.4);
						}
						else if (brightness > 0.2 && brightness < 0.3) {
							brightness = (float) (brightness - constrastFactor*0.3);
						}
						else if (brightness > 0.1 && brightness < 0.2) {
							brightness = (float) (brightness - constrastFactor*0.2);
						} 
						else if (brightness > 0 && brightness < 0.1) {
							brightness = (float) (brightness - constrastFactor*0.1);
						}
						
						if(brightness < 0)
							brightness = 0;
					}
				}
				else {
					
					if(brightness>0.5) {// 밝은 친구들은 중간방향으로 조금 더 어두운 값으로 
						if(brightness>0.5 && brightness<0.6 ) {
							brightness = (float) (brightness + constrastFactor*0.1);
						}
						else if(brightness>0.6 && brightness<0.7) {
							brightness = (float) (brightness + constrastFactor*0.2);
						}
						else if (brightness > 0.7 && brightness < 0.8) {
							brightness = (float) (brightness + constrastFactor*0.3);
						}
						else if (brightness > 0.8 && brightness < 0.9) {
							brightness = (float) (brightness + constrastFactor*0.4);
						} 
						else if (brightness > 0.9 && brightness < 1) {
							brightness = (float) (brightness + constrastFactor*0.5);
						}
						
						if(brightness < 0.5)
							brightness = 0.5F;
					}
					else {			// 어두운 친구들은 중간임계점으로 조금 더 밝은 값으로 
						if(brightness>0.4 && brightness<0.5 ) {
							brightness = (float) (brightness - constrastFactor*0.1);
						}
						else if(brightness>0.3 && brightness<0.4) {
							brightness = (float) (brightness - constrastFactor*0.2);
						}
						else if (brightness > 0.2 && brightness < 0.3) {
							brightness = (float) (brightness - constrastFactor*0.3);
						}
						else if (brightness > 0.1 && brightness < 0.2) {
							brightness = (float) (brightness - constrastFactor*0.4);
						} 
						else if (brightness > 0 && brightness < 0.1) {
							brightness = (float) (brightness - constrastFactor*0.5);
						}
						
						if(brightness > 0.5)
							brightness = 0.5F;
					}
					
				}
				
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
		
		
		
		if(doCompose) {
			
			//XL 600 450
			//L 400 300 
			//M 200 150
			//S 100 75
			if(comboBox.getSelectedIndex() == 0 ) {
				composeImage = composeSup.ResizeImage(100, 75);
			}
			else if (comboBox.getSelectedIndex() == 1) {
				composeImage = composeSup.ResizeImage(200, 150);
			} 
			else if (comboBox.getSelectedIndex() == 2) {
				composeImage = composeSup.ResizeImage(400, 300);
			} 
			else if (comboBox.getSelectedIndex() == 3) {
				composeImage = composeSup.ResizeImage(600, 450);
			}
			
			

			
			//exposure 조절 
			compoImageTemp = deepCopy(composeImage);
//			RescaleOp op = new RescaleOp(brightenFactor, 0, null);
			compoImageTemp = op.filter(compoImageTemp, compoImageTemp);
			
			for(int x = 0 ; x < composeImage.getWidth(); x++) {
				for(int y = 0 ; y < composeImage.getHeight(); y++) {
							ImageBackColor = new Color(compoImageTemp.getRGB(0,0));
							Color compoColor = new Color(compoImageTemp.getRGB(x, y));
							
							if(compoColor.equals(ImageBackColor))
								continue;
							
							float[] hsb = Color.RGBtoHSB(compoColor.getRed(), compoColor.getGreen(), compoColor.getBlue(), null);
							
						    float hue = hsb[0]; 
						    float saturation = hsb[1];
						    float brightness = hsb[2];
							saturation = (float) (saturation*saturationFactor);

							if(saturation>1)
								saturation = 1;
							
							
							if(constrastFactor>0) {
								
								// 한 임계값으로 다가가되, 다가가는 속도의 차이를 둠 
								if(brightness>0.5) {// 밝은 친구들은 더 밝은 곳으로 
									if(brightness>0.5 && brightness<0.6 ) {
										brightness = (float) (brightness + constrastFactor*0.5);
									}
									else if(brightness>0.6 && brightness<0.7) {
										brightness = (float) (brightness + constrastFactor*0.4);
									}
									else if (brightness > 0.7 && brightness < 0.8) {
										brightness = (float) (brightness + constrastFactor*0.3);
									}
									else if (brightness > 0.8 && brightness < 0.9) {
										brightness = (float) (brightness + constrastFactor*0.2);
									} 
									else if (brightness > 0.9 && brightness < 1) {
										brightness = (float) (brightness + constrastFactor*0.1);
									}
									
									if(brightness > 1)
										brightness = 1;
								}
								else {	// 어두운 친구들은 더 어두운 곳으로 
									if(brightness>0.4 && brightness<0.5 ) {
										brightness = (float) (brightness - constrastFactor*0.5);
									}
									else if(brightness>0.3 && brightness<0.4) {
										brightness = (float) (brightness - constrastFactor*0.4);
									}
									else if (brightness > 0.2 && brightness < 0.3) {
										brightness = (float) (brightness - constrastFactor*0.3);
									}
									else if (brightness > 0.1 && brightness < 0.2) {
										brightness = (float) (brightness - constrastFactor*0.2);
									} 
									else if (brightness > 0 && brightness < 0.1) {
										brightness = (float) (brightness - constrastFactor*0.1);
									}
									
									if(brightness < 0)
										brightness = 0;
								}
							}
							else {
								
								if(brightness>0.5) {// 밝은 친구들은 중간방향으로 조금 더 어두운 값으로 
									if(brightness>0.5 && brightness<0.6 ) {
										brightness = (float) (brightness + constrastFactor*0.1);
									}
									else if(brightness>0.6 && brightness<0.7) {
										brightness = (float) (brightness + constrastFactor*0.2);
									}
									else if (brightness > 0.7 && brightness < 0.8) {
										brightness = (float) (brightness + constrastFactor*0.3);
									}
									else if (brightness > 0.8 && brightness < 0.9) {
										brightness = (float) (brightness + constrastFactor*0.4);
									} 
									else if (brightness > 0.9 && brightness < 1) {
										brightness = (float) (brightness + constrastFactor*0.5);
									}
									
									if(brightness < 0.5)
										brightness = 0.5F;
								}
								else {			// 어두운 친구들은 중간임계점으로 조금 더 밝은 값으로 
									if(brightness>0.4 && brightness<0.5 ) {
										brightness = (float) (brightness - constrastFactor*0.1);
									}
									else if(brightness>0.3 && brightness<0.4) {
										brightness = (float) (brightness - constrastFactor*0.2);
									}
									else if (brightness > 0.2 && brightness < 0.3) {
										brightness = (float) (brightness - constrastFactor*0.3);
									}
									else if (brightness > 0.1 && brightness < 0.2) {
										brightness = (float) (brightness - constrastFactor*0.4);
									} 
									else if (brightness > 0 && brightness < 0.1) {
										brightness = (float) (brightness - constrastFactor*0.5);
									}
									
									if(brightness > 0.5)
										brightness = 0.5F;
								}
								
							}
							
							tempImage.setRGB(x+clickPoint.x-compoImageTemp.getWidth()/2,y+clickPoint.y-compoImageTemp.getHeight()/2, Color.HSBtoRGB(hue, saturation, brightness));
							
							//흑백인 경우 노출,  채도 반영한 뒤에  그 위에 흑백으로
							if(doBW) {
								Color BWColor = new Color(compoImageTemp.getRGB(x, y));
								int BWValue = (int) (
										0.2126 * BWColor.getRed()
										+ 0.7152 * BWColor.getGreen()
										+ 0.0722 * BWColor.getBlue());
								tempImage.setRGB(x+clickPoint.x-compoImageTemp.getWidth()/2,y+clickPoint.y-compoImageTemp.getHeight()/2, new Color(BWValue, BWValue,BWValue).getRGB());
							}
				}
			}
		}
	
		imageLabel.setIcon(new ImageIcon(tempImage));
		imageLabel.revalidate();
		imageLabel.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		BufferedImage bi = new BufferedImage(magnifyingLabel.getWidth(),magnifyingLabel.getHeight(),BufferedImage.TYPE_INT_RGB);

		if(magnifyCheckBox.isSelected()) {
			
			if(radio100Button.isSelected()) {
				for(int x=0 ; x < magnifyingLabel.getWidth() ; x ++) {
					for(int y = 0 ; y < magnifyingLabel.getHeight() ; y++){
						
						Point mPoint = e.getPoint();
						if(mPoint.x>imageLabel.getWidth() - magnifyingLabel.getWidth()/2)
							mPoint.x = imageLabel.getWidth() - magnifyingLabel.getWidth()/2;
						
						if(mPoint.x<magnifyingLabel.getWidth()/2)
							mPoint.x = magnifyingLabel.getWidth()/2;
						
						if(mPoint.y>imageLabel.getHeight() - magnifyingLabel.getHeight())
							mPoint.y = imageLabel.getHeight() - magnifyingLabel.getHeight();
						
						if(mPoint.y<magnifyingLabel.getHeight()/2)
							mPoint.y = magnifyingLabel.getHeight()/2;
						
						Color color = new Color(tempImage.getRGB(
								x + mPoint.x - magnifyingLabel.getWidth()/2,
								y + mPoint.y - magnifyingLabel.getWidth()/2));
						bi.setRGB(x, y, color.getRGB());
						
					}
				}
			}
			else if(radio150Button.isSelected()) {
				for(int x=0 ; x < magnifyingLabel.getWidth()/2 ; x  ++) {
					for(int y = 0 ; y < magnifyingLabel.getHeight()/2 ; y ++){
						
						Point mPoint = e.getPoint();
						if(mPoint.x>imageLabel.getWidth()-magnifyingLabel.getWidth()+magnifyingLabel.getWidth()/2)
							mPoint.x =imageLabel.getWidth()-magnifyingLabel.getWidth()+magnifyingLabel.getWidth()/2;
						
						if(mPoint.x<magnifyingLabel.getWidth()/4)
							mPoint.x = magnifyingLabel.getWidth()/4;
						
						if(mPoint.y>imageLabel.getHeight() - magnifyingLabel.getHeight()+magnifyingLabel.getHeight()/2)
							mPoint.y = imageLabel.getHeight() - magnifyingLabel.getHeight()+magnifyingLabel.getHeight()/2;
						
						if(mPoint.y<magnifyingLabel.getHeight()/4)
							mPoint.y = magnifyingLabel.getHeight()/4;
						
						Color color = new Color(tempImage.getRGB(
								x + mPoint.x - magnifyingLabel.getWidth()/4,
								y + mPoint.y - magnifyingLabel.getHeight()/4));
						bi.setRGB(2*x, 2*y, color.getRGB());
						bi.setRGB(2*x, 2*y+1, color.getRGB());
						bi.setRGB(2*x+1, 2*y, color.getRGB());
						bi.setRGB(2*x+1, 2*y+1, color.getRGB());
						
					}
				}
			}
			else if(radio200Button.isSelected()) {
				for(int x=0 ; x < magnifyingLabel.getWidth()/3 ;  x++ ) {
					for(int y = 0 ; y < magnifyingLabel.getHeight()/3 ; y++){
						
						Point mPoint = e.getPoint();
						
						if(mPoint.x>imageLabel.getWidth()-magnifyingLabel.getWidth()+magnifyingLabel.getWidth()/3)
							mPoint.x =imageLabel.getWidth()-magnifyingLabel.getWidth()+magnifyingLabel.getWidth()/3;
						
						if(mPoint.x<magnifyingLabel.getWidth()/6)
							mPoint.x = magnifyingLabel.getWidth()/6;
						
						if(mPoint.y>imageLabel.getHeight() - magnifyingLabel.getHeight()+magnifyingLabel.getHeight()/3)
							mPoint.y = imageLabel.getHeight() - magnifyingLabel.getHeight()+magnifyingLabel.getHeight()/3;
						
						if(mPoint.y<magnifyingLabel.getHeight()/6)
							mPoint.y = magnifyingLabel.getHeight()/6;
						
						Color color = new Color(tempImage.getRGB(
								x + mPoint.x - magnifyingLabel.getWidth()/6,
								y + mPoint.y - magnifyingLabel.getHeight()/6));						bi.setRGB(3*x, 3*y, color.getRGB());
						bi.setRGB(3*x, 3*y+1, color.getRGB());
						bi.setRGB(3*x, 3*y+2, color.getRGB());
						bi.setRGB(3*x+1, 3*y, color.getRGB());
						bi.setRGB(3*x+1, 3*y+1, color.getRGB());
						bi.setRGB(3*x+1, 3*y+2, color.getRGB());
						bi.setRGB(3*x+2, 3*y, color.getRGB());
						bi.setRGB(3*x+2, 3*y+1, color.getRGB());
						bi.setRGB(3*x+2, 3*y+2, color.getRGB());
						
					}
				}
			}
			magnifyingLabel.setIcon(new ImageIcon(bi));
			magnifyingLabel.revalidate();
			magnifyingLabel.repaint();
		}
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		clickPoint = new Point(e.getPoint());
		setPhotoInfo(brightenFactor, constrastFactor ,saturationFactor);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}


//else if (e.getSource().equals(ContrastSlider) ) {
//// 컨트라스트 어떻게 계산하는지 모르겠음;
//for (int x = 0; x < originImage.getWidth(); x++) {
//	for (int y = 0; y < originImage.getHeight(); y++) {
//		Color color = new Color(tempImage.getRGB(x, y));
//		float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
//		
//	    float hue = hsb[0]; 
//	    float saturation = hsb[1];
//	    float brightness = hsb[2];
//		
//	    	if(brightness>0.55) {
//		    	brightness = (float) (brightness + constrastFactor);
//		    	if(brightness >0.9 ) brightness = (float) 0.9;
//		    }
//		    else {
//		    	brightness = (float) (brightness - constrastFactor);
//		    	if(brightness<0.) brightness =(float) 0.2;
//		    }
//	   
//	    	saturation = (float) (saturation*saturationFactor);
//
//			if(saturation>1)
//				saturation = 1;
//		
//		tempImage.setRGB(x,y, Color.HSBtoRGB(hue, saturation, brightness));
//	}
//}
//imageLabel.setIcon(new ImageIcon(tempImage));
//imageLabel.revalidate();
//imageLabel.repaint();
//
//}
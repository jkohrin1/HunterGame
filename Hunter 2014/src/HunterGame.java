import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.image.BufferStrategy;
import java.util.*;
import java.awt.geom.AffineTransform;

public class HunterGame extends Canvas {
	private int weapSelect = 0;
	private int animSelect = 0;
	private int bgSelect = 0;
	JFrame container = new JFrame("Hunter 2014");
	private BufferStrategy strategy;
	private Graphics g;
	private ImagePanel backPanel;	
	private boolean gameRunning = true;
	private double lastAngle = 0;
	private boolean gameCanStart;
	private boolean clickFire = false;
		

	public HunterGame(){
		
		container.setPreferredSize(new Dimension(800,600));

		myMouseListener cml = new myMouseListener();
		container.addMouseListener(cml);
		
		backPanel = new ImagePanel(new ImageIcon("src/for.jpg").getImage());
		BorderLayout back = new BorderLayout();
		back.setVgap(200);
		backPanel.setLayout(back);

		JLabel hunt = new JLabel();
		hunt.setText("Hunter 2014");
		hunt.setBorder(BorderFactory.createEmptyBorder(50,200,0,0));
		hunt.setForeground(Color.orange);
		hunt.setFont(new Font("Serif", Font.BOLD, 80));
		backPanel.add(hunt, BorderLayout.PAGE_START);
		

		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(400,300));
		FlowLayout flow2 = new FlowLayout();
		flow2.setHgap(200);
		flow2.setVgap(30);
		leftPanel.setLayout(flow2);
		leftPanel.setLocation(200,600);	
		leftPanel.setBackground(Color.GRAY);
		leftPanel.setOpaque(true);
		

		JButton chooseHunt = new JButton("Choose Your Hunt");
		chooseHunt.setBackground(Color.orange);
		chooseHunt.setForeground(Color.white);
		chooseHunt.setBounds(200,120,200,120);
		

		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(400,300));
		FlowLayout flow3 = new FlowLayout();
		flow3.setHgap(200);
		rightPanel.setLayout(flow3);
		rightPanel.setOpaque(false);

		ImagePanel deer = new ImagePanel(new ImageIcon("src/hunter.png").getImage());
		deer.setBorder(BorderFactory.createEmptyBorder(0,0,300,300));
		deer.setSize(new Dimension(300,300));


		JLabel text = new JLabel();
		text.setForeground(Color.white);
		text.setBackground(Color.white);
		text.setFont(new Font("Serif", Font.BOLD, 12));
		text.setText("<html> To begin the game, click the 'Choose Your Hunt' button below <br> then select your weapon, the animal you would like to hunt, <br>and the terrain you would like to hunt on.<br><br> Controls: Mouse to aim fire. Click to shoot. <br><br> Developed By: Josh Kohring</html>");

		
		
		rightPanel.add(deer);
		leftPanel.add(text);
		leftPanel.add(chooseHunt);
		backPanel.add(leftPanel, BorderLayout.LINE_START);
		backPanel.add(rightPanel, BorderLayout.LINE_END);
		container.add(backPanel);
		setIgnoreRepaint(true);
		
		// finally make the window visible 
		container.pack();
		container.setResizable(false);
		container.setVisible(true);

		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		chooseHunt.addActionListener(new 
			ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					backPanel.remove(leftPanel);
					backPanel.remove(rightPanel);
					backPanel.remove(hunt);
					backPanel.repaint();
					backPanel.setVisible(false);
					startGame(backPanel);
					container.invalidate();
					container.validate();
					container.pack();
					container.repaint();
				}
			});
		
		}

	private void startGame(ImagePanel frame){
			FlowLayout cont = new FlowLayout();
			frame.setLayout(cont);

			JPanel weap = new JPanel();
			FlowLayout weapflow = new FlowLayout();
			weap.setLayout(weapflow);
			weap.setPreferredSize(new Dimension (800,175));
			weap.setBackground(Color.gray);

			JPanel riflePanel = new JPanel();
			FlowLayout rifPanLayout = new FlowLayout();
			rifPanLayout.setHgap(50);
			rifPanLayout.setVgap(35);
			riflePanel.setLayout(rifPanLayout);
			riflePanel.setPreferredSize(new Dimension(350,165));
			riflePanel.setBackground(Color.white);
			
			JRadioButton rifle = new JRadioButton();
			rifle.setText("Rifle");
			rifle.setBackground(Color.orange);

			ImagePanel rifImg = new ImagePanel(new ImageIcon("src/rifle.png").getImage());

			JLabel rifSpec = new JLabel();
			rifSpec.setText("<html>.270 Rifle: A semi-automatic rifle with high damage<br> and good range. Most effective for hunting deer or larger game.</html>");
			rifSpec.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			rifSpec.setForeground(Color.black);
			rifSpec.setFont(new Font("Serif", Font.PLAIN, 12));

			riflePanel.add(rifle);
			riflePanel.add(rifImg);
			riflePanel.add(rifSpec);

			JPanel shotPanel = new JPanel();
			FlowLayout shotPanLayout = new FlowLayout();
			shotPanLayout.setHgap(50);
			shotPanLayout.setVgap(35);
			shotPanel.setLayout(shotPanLayout);
			shotPanel.setPreferredSize(new Dimension(350,165));
			shotPanel.setBackground(Color.white);

			JRadioButton shotgun = new JRadioButton();
			shotgun.setText("Shotgun");
			shotgun.setBackground(Color.orange);

			ImagePanel shotImg = new ImagePanel(new ImageIcon("src/shotgun.png").getImage());

			JLabel shotSpec = new JLabel();
			shotSpec.setText("<html>12 Guage Shotgun: A pump-action shotgun with average damage <br> that lessens with range. Most effective for hunting birds</html>");
			shotSpec.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			shotSpec.setForeground(Color.black);
			shotSpec.setFont(new Font("Serif", Font.PLAIN, 12));


			shotPanel.add(shotgun);
			shotPanel.add(shotImg);
			shotPanel.add(shotSpec);

		
			ButtonGroup weapons = new ButtonGroup();
			
			weapons.add(rifle);
			weapons.add(shotgun);

			weap.add(riflePanel);
			weap.add(shotPanel);

			JPanel animals = new JPanel();
			FlowLayout animflow = new FlowLayout();
			animals.setLayout(animflow);
			animals.setPreferredSize(new Dimension (800,175));
			animals.setBackground(Color.gray);
			

			JPanel deerPan = new JPanel();
			FlowLayout deerFlow = new FlowLayout();
			deerFlow.setHgap(25);
			deerPan.setPreferredSize(new Dimension(250,165));
			deerPan.setLayout(deerFlow);
			deerPan.setBackground(Color.white);			

			JRadioButton deer = new JRadioButton();
			deer.setText("Deer");
			deer.setBackground(Color.orange);

			ImagePanel deerImg = new ImagePanel(new ImageIcon("src/deerImg.png").getImage());

			deerPan.add(deer);
			deerPan.add(deerImg);

			JPanel pheasPan = new JPanel();
			FlowLayout pheasFlow = new FlowLayout();
			pheasFlow.setHgap(25);
			pheasPan.setPreferredSize(new Dimension(250,165));
			pheasPan.setLayout(pheasFlow);
			pheasPan.setBackground(Color.white);

			JRadioButton pheasants = new JRadioButton();
			pheasants.setText("Pheasants");
			pheasants.setBackground(Color.orange);

			ImagePanel pheasImg = new ImagePanel(new ImageIcon("src/pheasant.png").getImage());

			pheasPan.add(pheasants);
			pheasPan.add(pheasImg);

			JPanel bearPan = new JPanel();
			FlowLayout bearFlow = new FlowLayout();
			bearFlow.setHgap(25);
			bearPan.setPreferredSize(new Dimension(250,165));
			bearPan.setLayout(bearFlow);
			bearPan.setBackground(Color.white);

			JRadioButton mLion = new JRadioButton();
			mLion.setText("Mountain Lion");
			mLion.setBackground(Color.orange);

			ImagePanel lionImg = new ImagePanel(new ImageIcon("src/lion.png").getImage());

			bearPan.add(mLion);
			bearPan.add(lionImg);
		
			ButtonGroup anim = new ButtonGroup();
			
			anim.add(deer);
			anim.add(pheasants);
			anim.add(mLion);

			animals.add(deerPan);
			animals.add(bearPan);
			animals.add(pheasPan);

			JPanel background = new JPanel();
			FlowLayout backflow = new FlowLayout();
			backflow.setHgap(0);
			background.setLayout(backflow);
			background.setPreferredSize(new Dimension (800,175));
			background.setBackground(Color.gray);

			JPanel forPan = new JPanel();
			FlowLayout forLayout = new FlowLayout();
			forLayout.setHgap(25);
			forPan.setPreferredSize(new Dimension(250,165));
			forPan.setLayout(forLayout);
			forPan.setBackground(Color.white);

			JRadioButton forest = new JRadioButton();
			forest.setText("Forest");
			forest.setBackground(Color.orange);

			ImagePanel forImg = new ImagePanel(new ImageIcon("src/forImg.jpg").getImage());

			forPan.add(forest);
			forPan.add(forImg);

			JPanel mountPan = new JPanel();
			FlowLayout mountLayout = new FlowLayout();
			mountLayout.setHgap(25);
			mountPan.setLayout(mountLayout);
			mountPan.setPreferredSize(new Dimension(250,165));
			mountPan.setBackground(Color.white);

			JRadioButton mountain = new JRadioButton();
			mountain.setText("Mountain");
			mountain.setBackground(Color.orange);

			ImagePanel mountImg = new ImagePanel(new ImageIcon("src/mountImg.jpg").getImage());

			mountPan.add(mountain);
			mountPan.add(mountImg);

			JPanel prairPan = new JPanel();
			FlowLayout prairLayout = new FlowLayout();
			prairLayout.setHgap(25);
			prairPan.setLayout(prairLayout);
			prairPan.setPreferredSize(new Dimension(250,165));
			prairPan.setBackground(Color.white);


			JRadioButton prairie = new JRadioButton();
			prairie.setText("Prairie");
			prairie.setBackground(Color.orange);

			ImagePanel prairImg = new ImagePanel(new ImageIcon("src/prairie.jpg").getImage());

			prairPan.add(prairie);
			prairPan.add(prairImg);

			ButtonGroup back = new ButtonGroup();
			back.add(forest);
			back.add(mountain);
			back.add(prairie);

			background.add(forPan);
			background.add(mountPan);
			background.add(prairPan);

			JButton startGame = new JButton();
			startGame.setText("Start Hunt");
			startGame.setBackground(Color.orange);

			frame.add(weap, BorderLayout.LINE_START);
			frame.add(animals, BorderLayout.CENTER);
			frame.add(background, BorderLayout.LINE_END);
			frame.add(startGame);

			frame.repaint();
			frame.setVisible(true);

			startGame.addActionListener(new 
			ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					if(rifle.isSelected()){
						weapSelect = 1;
						}
					if(shotgun.isSelected()){
						weapSelect = 2;
						}
					if(deer.isSelected()){
						animSelect = 1;
						}
					if(mLion.isSelected()){
						animSelect = 2;
						}
					if(pheasants.isSelected()){
						animSelect = 3;
						}
					if(forest.isSelected()){
						bgSelect = 1;
						}
					if(mountain.isSelected()){
						bgSelect = 2;
						}
					if(prairie.isSelected()){
						bgSelect = 3;
						}
					
					if(weapSelect==0){
						JOptionPane.showMessageDialog(frame, "Select a weapon to continue");
						}
					else if(animSelect==0){
						JOptionPane.showMessageDialog(frame, "Select an animal to hunt to continue");
						}
					else if(bgSelect==0){
						JOptionPane.showMessageDialog(frame, "Select a place to hunt to continue");
						}

					else if((animSelect==2)&&(bgSelect==3)){
						JOptionPane.showMessageDialog(frame, "I doubt you'll find a Mountain Lion in a prairie. Help me help you. Choose Again.");
						}
						
					else if((weapSelect!=0)&&(animSelect!=0)&&(bgSelect!=0)){
						container.remove(frame);
						container.invalidate();
						container.validate();
						container.repaint();
						gameCanStart = true;
						runGameLoop();
						}
				}
			});
		}
	
	public void runGameLoop()
	   {
	      Thread loop = new Thread()
	      {
	         public void run()
	         {
	            gameInit();
	         }
	      };
	      loop.start();
	   }

	private void gameInit(){
		long lastLoopTime = System.currentTimeMillis();
		container.remove(backPanel);
		
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(null);
		
		setBounds(0,0,800,600);
		panel.add(this);
		
		myMouseListener mml = new myMouseListener();
		panel.addMouseListener(mml);
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		requestFocus();
		setIgnoreRepaint(true);
		
//		Gun rifg = new Gun();
//		PlayerSprite s = new PlayerSprite();
//		Bullet bul = new Bullet();
		
		while(gameRunning){
			Graphics2D bkG = (Graphics2D) strategy.getDrawGraphics();

			Graphics2D bkT = (Graphics2D) strategy.getDrawGraphics();

		PointerInfo userInput = MouseInfo.getPointerInfo();
		Point b = userInput.getLocation();
		double mouseX = b.getX();
		double mouseY = b.getY();

		long delta = System.currentTimeMillis()-lastLoopTime;
		lastLoopTime = System.currentTimeMillis();
		
		bkG.setColor(Color.white);
		bkG.fillRect(0,0,800,600);

		if(bgSelect == 1){
			//bgForest f = new bgForest();
			//f.paint(bkG);
			}
		if(bgSelect == 2){
			//bgMountain m = new bgMountain();
			//m.paint(bkG);
			}
		if(bgSelect == 3){
			//bgPrairie p = new bgPrairie();
			//p.paint(bkG);
			}	

		if( (mouseY<550) && ((mouseX<400)||(mouseX>450))){
		double xDistance = mouseX-410;
		double yDistance = mouseY-550;
		double angleToTurn = Math.atan2(yDistance,xDistance) + 190;
		lastAngle = angleToTurn;
		bkT.rotate(angleToTurn,410,550);
			}
		else{
			bkT.rotate(lastAngle,410,550);
			}

		//bkT.rotate(angleToTurn,410,550);
		
		//rifg.paint(bkT);

		//s.paint(bkG);
		
		if(clickFire){
		//	bul.paint(bkG);
		}
		
		bkG.dispose();
		bkT.dispose();
		strategy.show();
		
		try { Thread.sleep(10); } catch (Exception e) {}
			}
			
		}
	
	class ImagePanel extends JPanel {
		private Image img;
		public ImagePanel(String img){
			this(new ImageIcon(img).getImage());
		}
		
		public ImagePanel(Image img){
			this.img = img;
			Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));  
    			setPreferredSize(size);  
   		 	setMinimumSize(size);  
    			setMaximumSize(size);  
    			setSize(size);  
    			setLayout(null);  	
  		}  
  
  		public void paintComponent(Graphics g) {  
    		g.drawImage(img, 0, 0, null);  
  		}  
	}
	
	class myMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) { 
		    clickFire = true;
		}

		@Override
		public void mouseEntered(MouseEvent arg0) { }

		@Override
		public void mouseExited(MouseEvent arg0) { }

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

		}
		}

	public static void main(String[] args){
		HunterGame g = new HunterGame();
		}

}
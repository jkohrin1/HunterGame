
import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.awt.geom.*;

/**
 * A class that runs the Hunter 2014 game. 
 * 
 * @author Josh Kohring
 *
 */
@SuppressWarnings("serial")
public class HunterGamev2 extends Canvas {
	
	//A variable to tell which weapon was selected
	private int weapSelect = 0;
	//A variable to tell which animal was selected
	private int animSelect = 0;
	//A variable to tell which background was selected
	private int bgSelect = 0;
	//Declares a JFrame that we will be using to hold the game menus and gameplay
	JFrame container;
	//Declares a BufferStrategy
	private BufferStrategy strategy;
	//Declares the ImagePanel to put the different menus on
	final private ImagePanel backPanel;	
	//Boolean to keep the game loop running
	private boolean gameRunning = true;
	//Boolean to check if the mouse has been clicked 
	private boolean clickFire = false;
	//A variable to store the x location of the mouse
	private int xloc;
	//A variable to control the reload time for the guns
	private int firingInterval = 2500;
	//A variable to tell when the last shot was fired
	private long lastFire = 0;
	//A variable to tell the starting X position of the animals
	private int animXStart;
	//A variable to tell the starting Y position of the animals
	private int animYStart;
	//A variable to keep the score
	private int score=0;
	//A variable to keep the highscore
	private int highscore;
	//A variable to keep track of the number on animals killed
	private int killCount = 0;
	
	public HunterGamev2(){
		/**
		 * Constructs a HunterGamev2 canvas with no parameters to bring up the starting menu
		 * inside of the JFrame container
		 */
		container = new JFrame("Hunter 2014");
		container.setPreferredSize(new Dimension(800,600));

		//Creates the background image of a forest on the startup menu
		backPanel = new ImagePanel(new ImageIcon("src/for.jpg").getImage());
		BorderLayout back = new BorderLayout();
		back.setVgap(200);
		backPanel.setLayout(back);

		//Creates the text 'Hunter 2014' displayed on the startup menu
		JLabel hunt = new JLabel();
		hunt.setText("Hunter 2014");
		hunt.setBorder(BorderFactory.createEmptyBorder(50,200,0,0));
		hunt.setForeground(Color.orange);
		hunt.setFont(new Font("Serif", Font.BOLD, 80));
		backPanel.add(hunt, BorderLayout.PAGE_START);
		
		//Creates a the bottom left box on the startup menu to contain 
		//instructional text and the start game button
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(400,300));
		FlowLayout flow2 = new FlowLayout();
		flow2.setHgap(200);
		flow2.setVgap(30);
		leftPanel.setLayout(flow2);
		leftPanel.setLocation(200,600);	
		leftPanel.setBackground(Color.GRAY);
		leftPanel.setOpaque(true);
		
		//Creates a button to start the game
		JButton chooseHunt = new JButton("Choose Your Hunt");
		chooseHunt.setBackground(Color.orange);
		chooseHunt.setForeground(Color.white);
		chooseHunt.setBounds(200,120,200,120);
		
		//Creates an invisible panel to hold the image of the deer on the startup menu
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(400,300));
		FlowLayout flow3 = new FlowLayout();
		flow3.setHgap(200);
		rightPanel.setLayout(flow3);
		rightPanel.setOpaque(false);

		//Creates the image of the deer
		ImagePanel deer = new ImagePanel(new ImageIcon("src/hunter.png").getImage());
		deer.setBorder(BorderFactory.createEmptyBorder(0,0,300,300));
		deer.setSize(new Dimension(300,300));

		//Creates the instructional text
		JLabel text = new JLabel();
		text.setForeground(Color.white);
		text.setBackground(Color.white);
		text.setFont(new Font("Serif", Font.BOLD, 12));
		text.setText("<html> To begin the game, click the 'Choose Your Hunt' button below <br> then select your weapon, the animal you would like to hunt, <br>and the terrain you would like to hunt on.<br><br> Controls: Mouse to aim fire. Click to shoot. <br><br> Developed By: Josh Kohring</html>");

		
		//Adds each of the components to their appropriate place
		rightPanel.add(deer);
		leftPanel.add(text);
		leftPanel.add(chooseHunt);
		backPanel.add(leftPanel, BorderLayout.LINE_START);
		backPanel.add(rightPanel, BorderLayout.LINE_END);
		container.add(backPanel);
		setIgnoreRepaint(true);
		
		//Makes the startup window visible 
		container.pack();
		container.setResizable(false);
		container.setVisible(true);

		//Adds a window listener to the container to exit when the 'x' is clicked
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		//Adds an action listener to the 'Choose Hunt' button on the startup menu
		chooseHunt.addActionListener(new 
			ActionListener()
			{
				/*
				 * Customizes the action listener to perform the following events
				 * on clicking the button
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent event)
				{
					//Clears the startup Menu
					backPanel.remove(leftPanel);
					backPanel.remove(rightPanel);
					backPanel.remove(hunt);
					backPanel.repaint();
					backPanel.setVisible(false);
					
					//passes the backPanel to startGame
					startGame(backPanel);
					
					//Repaints the container to show the changes made
					container.invalidate();
					container.validate();
					container.pack();
					container.repaint();
				}
			});
		
		}

	/*
	  Brings up the menu to select the settings for the hunt
	  @param frame an ImagePanel that will hold the selection menus
	 */
	private void startGame(ImagePanel frame){
			//Sets a new layout for the frame
			FlowLayout cont = new FlowLayout();
			frame.setLayout(cont);

			//Constructs a new panel to hold the weapons choices
			JPanel weap = new JPanel();
			FlowLayout weapflow = new FlowLayout();
			weap.setLayout(weapflow);
			weap.setPreferredSize(new Dimension (800,175));
			weap.setBackground(Color.gray);

			//Constructs a new panel to hold the image and button for the rifle option
			JPanel riflePanel = new JPanel();
			FlowLayout rifPanLayout = new FlowLayout();
			rifPanLayout.setHgap(50);
			rifPanLayout.setVgap(35);
			riflePanel.setLayout(rifPanLayout);
			riflePanel.setPreferredSize(new Dimension(350,165));
			riflePanel.setBackground(Color.white);
			
			//Constructs a JRadioButton for the rifle to tell if it has been selected
			JRadioButton rifle = new JRadioButton();
			rifle.setText("Rifle");
			rifle.setBackground(Color.orange);

			//Gets the image of the rifle
			ImagePanel rifImg = new ImagePanel(new ImageIcon("src/rifle.png").getImage());

			//Creates the description of the rifle
			JLabel rifSpec = new JLabel();
			rifSpec.setText("<html>.270 Rifle: A semi-automatic rifle with high damage<br> and good range. Most effective for hunting deer or larger game.</html>");
			rifSpec.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			rifSpec.setForeground(Color.black);
			rifSpec.setFont(new Font("Serif", Font.PLAIN, 12));

			//Adds all the rifle selection components to the panel
			riflePanel.add(rifle);
			riflePanel.add(rifImg);
			riflePanel.add(rifSpec);

			//Creates a new panel to hold the image and button for the Shotgun option
			JPanel shotPanel = new JPanel();
			FlowLayout shotPanLayout = new FlowLayout();
			shotPanLayout.setHgap(50);
			shotPanLayout.setVgap(35);
			shotPanel.setLayout(shotPanLayout);
			shotPanel.setPreferredSize(new Dimension(350,165));
			shotPanel.setBackground(Color.white);

			//Creates a JRadioButton to tell if the Shotgun has been selected
			JRadioButton shotgun = new JRadioButton();
			shotgun.setText("Shotgun");
			shotgun.setBackground(Color.orange);

			//Gets an image of a shotgun
			ImagePanel shotImg = new ImagePanel(new ImageIcon("src/shotgun.png").getImage());

			//Creates the description of the Shotgun
			JLabel shotSpec = new JLabel();
			shotSpec.setText("<html>12 Guage Shotgun: A pump-action shotgun with average damage <br> that lessens with range. Most effective for hunting birds</html>");
			shotSpec.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			shotSpec.setForeground(Color.black);
			shotSpec.setFont(new Font("Serif", Font.PLAIN, 12));

			//Adds all of the Shotgun selection components to the panel
			shotPanel.add(shotgun);
			shotPanel.add(shotImg);
			shotPanel.add(shotSpec);

			//Creates a new button group
			ButtonGroup weapons = new ButtonGroup();
			
			//Adds the Shotgun and the Rifle to the ButtonGroup so only one can
			//be selected at a time
			weapons.add(rifle);
			weapons.add(shotgun);

			//Adds the panels containing the rifle and the shotgun to the weap
			//panel
			weap.add(riflePanel);
			weap.add(shotPanel);

			//Creates a panel to hold the animal options 
			JPanel animals = new JPanel();
			FlowLayout animflow = new FlowLayout();
			animals.setLayout(animflow);
			animals.setPreferredSize(new Dimension (800,175));
			animals.setBackground(Color.gray);
			
			//Creates a panel to hold the deer option in
			JPanel deerPan = new JPanel();
			FlowLayout deerFlow = new FlowLayout();
			deerFlow.setHgap(25);
			deerPan.setPreferredSize(new Dimension(250,165));
			deerPan.setLayout(deerFlow);
			deerPan.setBackground(Color.white);			

			//Creates a JRadioButton to tell if deer has been selected
			JRadioButton deer = new JRadioButton();
			deer.setText("Deer");
			deer.setBackground(Color.orange);

			//Gets an image of the deer
			ImagePanel deerImg = new ImagePanel(new ImageIcon("src/deerImg.png").getImage());

			//Adds the image and selection button to the deer panel
			deerPan.add(deer);
			deerPan.add(deerImg);

			//Creates a panel to hold the Pheasant option in
			JPanel pheasPan = new JPanel();
			FlowLayout pheasFlow = new FlowLayout();
			pheasFlow.setHgap(25);
			pheasPan.setPreferredSize(new Dimension(250,165));
			pheasPan.setLayout(pheasFlow);
			pheasPan.setBackground(Color.white);

			//Creates a JRadioButton to tell if pheasant has been selected
			JRadioButton pheasants = new JRadioButton();
			pheasants.setText("Pheasants");
			pheasants.setBackground(Color.orange);

			//Gets an image of a pheasant
			ImagePanel pheasImg = new ImagePanel(new ImageIcon("src/pheasant.png").getImage());

			//Adds the selection button and image to the pheasant option panel
			pheasPan.add(pheasants);
			pheasPan.add(pheasImg);

			//Creates a panel to hold the lion option
			JPanel lionPan = new JPanel();
			FlowLayout lionFlow = new FlowLayout();
			lionFlow.setHgap(25);
			lionPan.setPreferredSize(new Dimension(250,165));
			lionPan.setLayout(lionFlow);
			lionPan.setBackground(Color.white);

			//Creates a JRadioButton to tell if the lion has been selected 
			JRadioButton mLion = new JRadioButton();
			mLion.setText("Mountain Lion");
			mLion.setBackground(Color.orange);

			//Gets an image of a lion
			ImagePanel lionImg = new ImagePanel(new ImageIcon("src/lion.png").getImage());

			//Adds the button and image to the lion option panel
			lionPan.add(mLion);
			lionPan.add(lionImg);
			
			//Creates a new button group so only one animal can be selected
			ButtonGroup anim = new ButtonGroup();
			
			//Adds the animal selection buttons to this group
			anim.add(deer);
			anim.add(pheasants);
			anim.add(mLion);

			//Adds the each animal panel to the animal panel
			animals.add(deerPan);
			animals.add(lionPan);
			animals.add(pheasPan);

			//Creates a panel to hold the background options
			JPanel background = new JPanel();
			FlowLayout backflow = new FlowLayout();
			backflow.setHgap(0);
			background.setLayout(backflow);
			background.setPreferredSize(new Dimension (800,175));
			background.setBackground(Color.gray);

			//Creates a panel to hold the forest option
			JPanel forPan = new JPanel();
			FlowLayout forLayout = new FlowLayout();
			forLayout.setHgap(25);
			forPan.setPreferredSize(new Dimension(250,165));
			forPan.setLayout(forLayout);
			forPan.setBackground(Color.white);

			//Creates a JRadioButton to tell if forest has been selected
			JRadioButton forest = new JRadioButton();
			forest.setText("Forest");
			forest.setBackground(Color.orange);

			//Gets an image of a forest
			ImagePanel forImg = new ImagePanel(new ImageIcon("src/forImg.jpg").getImage());

			//Adds the selection button and image to the forest option panel
			forPan.add(forest);
			forPan.add(forImg);

			//Creates a panel for the mountain option
			JPanel mountPan = new JPanel();
			FlowLayout mountLayout = new FlowLayout();
			mountLayout.setHgap(25);
			mountPan.setLayout(mountLayout);
			mountPan.setPreferredSize(new Dimension(250,165));
			mountPan.setBackground(Color.white);

			//Creates a JRadioButton to tell if mountain has been selected
			JRadioButton mountain = new JRadioButton();
			mountain.setText("Mountain");
			mountain.setBackground(Color.orange);

			//Gets an image of a mountain
			ImagePanel mountImg = new ImagePanel(new ImageIcon("src/mountImg.jpg").getImage());

			//Adds the selection button and image to the mountain option panel
			mountPan.add(mountain);
			mountPan.add(mountImg);

			//Creates a panel to hold the prairie option
			JPanel prairPan = new JPanel();
			FlowLayout prairLayout = new FlowLayout();
			prairLayout.setHgap(25);
			prairPan.setLayout(prairLayout);
			prairPan.setPreferredSize(new Dimension(250,165));
			prairPan.setBackground(Color.white);

			//Creates a JRadioButton to tell if prairie is selected
			JRadioButton prairie = new JRadioButton();
			prairie.setText("Prairie");
			prairie.setBackground(Color.orange);

			//Gets an image of a prairie
			ImagePanel prairImg = new ImagePanel(new ImageIcon("src/prairie.jpg").getImage());

			//Adds the selection button and image to the prairie option panel
			prairPan.add(prairie);
			prairPan.add(prairImg);

			//Creates a ButtonGroup so only one of the backgrounds can be selected
			ButtonGroup back = new ButtonGroup();
			back.add(forest);
			back.add(mountain);
			back.add(prairie);

			//Adds each background to the ButtonGroup
			background.add(forPan);
			background.add(mountPan);
			background.add(prairPan);

			//Creates a button to start the game when all selections have been made
			JButton startGame = new JButton();
			startGame.setText("Start Hunt");
			startGame.setBackground(Color.orange);

			//Adds all of the selection panels to the frame 
			frame.add(weap, BorderLayout.LINE_START);
			frame.add(animals, BorderLayout.CENTER);
			frame.add(background, BorderLayout.LINE_END);
			frame.add(startGame);

			//Repaints the frame to update the changes made to the frame and makes it
			//visible
			frame.repaint();
			frame.setVisible(true);

			//adds an action listener to the start button to control what happens when it
			//has been selected
			startGame.addActionListener(new 
			ActionListener()
			{
				/*
				 * Creates an action listener to start the game if all the selections have been
				 * made properly
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent event)
				{
					if(rifle.isSelected()){
						//The rifle button was selected set weapSelect to detect it 
						//and set the appropraite firingInterval for reload time
						weapSelect = 1;
						firingInterval = 2500;
						}
					if(shotgun.isSelected()){
						//The shotgun button was selected set weapSelect to detect it 
						// and set the appropriate firingInterval for reload time
						weapSelect = 2;
						firingInterval = 500;
						}
					if(deer.isSelected()){
						//Deer was selected and set animSelect to detect that
						//and set the number and starting position of the deer
						animSelect = 1;
						//numOfAnimals = 6;
						animXStart = 0;
						animYStart = 200;
						}
					if(mLion.isSelected()){
						//Mountain Lion was selected and set animSelect to detect that
						//and set the number and starting position of the mountain lions
						animSelect = 2;
						//numOfAnimals = 2;
						animXStart = 0;
						animYStart = 200;
						}
					if(pheasants.isSelected()){
						//Pheasants was selected and set animSelect to detect that 
						//and set number and starting position of the pheasants
						animSelect = 3;
						//numOfAnimals = 8;
						animXStart = 400;
						animYStart = 400;
						}
					if(forest.isSelected()){
						//Forest was selected and we set bgSelect to detect that
						bgSelect = 1;
						}
					if(mountain.isSelected()){
						//Mountain was selected and we set bgSelect to detect that
						bgSelect = 2;
						}
					if(prairie.isSelected()){
						//Prairie was selected and we set bgSelect to detect that
						bgSelect = 3;
						}
					
					if(weapSelect==0){
						//No weapons were selected. Create a message box to remind the user
						JOptionPane.showMessageDialog(frame, "Select a weapon to continue");
						}
					else if(animSelect==0){
						//No animal was selected. Create a message box to remind the user
						JOptionPane.showMessageDialog(frame, "Select an animal to hunt to continue");
						}
					else if(bgSelect==0){
						//No background was selected. Create a message box to remind the user
						JOptionPane.showMessageDialog(frame, "Select a place to hunt to continue");
						}

					else if((animSelect==2)&&(bgSelect==3)){
						//Mountain lion and Prairie was selected. Remind the user you wont find
						//a mountain lion in a prairie
						JOptionPane.showMessageDialog(frame, "I doubt you'll find a Mountain Lion in a prairie. Help me help you. Choose Again.");
						}
						
					else if((weapSelect!=0)&&(animSelect!=0)&&(bgSelect!=0)){
						//A selection has been made for a weapon animal and background.
						//the game can start. Removes the background and updates the container
						container.remove(frame);
						container.invalidate();
						container.validate();
						container.repaint();
						runGameLoop();			//goes to runGameLoop to start a new thread
						}
				}
			});
		}
	
	/*
	 * Starts a new thread to avoid any threading issues and then allows the game to be initialized
	 */
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
	
	/*
	 * Initializes and runs the game
	 */
	private void gameInit(){
		
		//Removes the menu panel from the container
		container.remove(backPanel);

		//Sets the bounds of the canvas to 800x600 and adds it to 
		//the container
		setBounds(0,0,800,600);
		container.add(this);
		
		//Creates a new custom mouse listener to detect mouse clicks and
		//adds it to the canvas
		myMouseListener mml = new myMouseListener();
		this.addMouseListener(mml);
		
		//Creates a 2 buffer bufferstrategy to allow for gameplay movement
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		//Creates a Background object with the background that was selected
		Background back = new Background(bgSelect);
		
		//Creates a HunterShape with parameters of x and y coordinates and width
		HunterShape s = new HunterShape(400,500,30);
		
		//Creates a RifShape of the weapon selected with parameters x,y coordinates and width and height
		RifShape r = new RifShape(weapSelect,0,0,5,50);
		
		//Creates ArrayLists to hold the Bullets, Animals, Blood, Killed Animals, Trees
		ArrayList<RifBullet> bul = new ArrayList<RifBullet>();
		ArrayList<Animal> buck = new ArrayList<Animal>();
		ArrayList<Blood> bleed = new ArrayList<Blood>();		
		ArrayList<Animal> killed = new ArrayList<Animal>();
		ArrayList<Tree> trees = new ArrayList<Tree>();
		
		randInt treenum = new randInt();
		int numTrees = treenum.randomInt(5, 12);
		
		if (bgSelect!=3){
			//The selected background was not prairie and add trees to the game
			for(int i = 0; i<numTrees; i++){
				//loops through the number of trees we want to have
				randInt rano = new randInt();		
				int tran = rano.randomInt(0,numTrees);		//Makes a random integer between -5 and 5
				int xpos = 400;
				int ypos = 300;
				randInt xtrand = new randInt();
				int xtran = xtrand.randomInt(0, 2);
				if(xtran==1){
					xpos += tran * 60;
				}
				if(xtran==2){
					xpos -= tran * 60;
				}
				int ytran = xtrand.randomInt(0,2);
				if(ytran==1){
					ypos += tran * 30;
				}
				if(ytran==2){
					ypos -= tran* 30;
				}
				//Create a new tree with parameters of the background, a somewhat random x, y,
				//width, and height and adds it to the tree list
				Tree treed = new Tree(bgSelect,xpos, ypos, 30,30);
				trees.add(treed);
				}
			}
		
		randInt num = new randInt();
		int numAn = num.randomInt(2, 10);
		
		for(int i = 0; i < numAn; i++){
			//loops through the number of animals we want to create and adds them "randomly"
			//to the animal list
			randInt ran = new randInt();
			int xran = ran.randomInt(-5, 5);		//Makes random integer between -5 and 5
			
			//Creates a new animal with the animal type, random x, random y, width and hight
			//as parameters
			Animal deers = new Animal(animSelect,animXStart+(xran* 20),animYStart+(i*30),40,40);
			buck.add(deers);
		}
		
		while(gameRunning){
			//sets our gameplay loop to continue while gameRunning is true
			
			//Gets the information on the mouses positioning and stores it to gunAim
			PointerInfo userInput = MouseInfo.getPointerInfo();
			Point b = userInput.getLocation();
			double mouseX = b.getX();
			int gunAim = (int)mouseX-50;
			
			//Creates a Graphics2D object for the bufferstrategy to flip between
			Graphics2D bkG = (Graphics2D) strategy.getDrawGraphics();
			
			//Sets the Graphics2D object to white and set its size to our canvas size
			bkG.setColor(Color.white);
			bkG.fillRect(0,0,800,600);
			
			//Paint the selected background onto the Graphics
			back.paint(bkG);

			//Moves the hunter and rifle to the mouses x position
			s.moveTo(gunAim, 500);
			r.moveTo(gunAim+40,520);
			
			//Draws the hunter and rifle to the graphics screen
			r.draw(bkG);
			s.draw(bkG);
			
			if (System.currentTimeMillis() - lastFire < firingInterval){
				//The user clicked while still reloading print a string to the bottom right
				//to tell them
				bkG.drawString("Reloading",15,570);
			}
			
			for(int i = 0; i < bul.size(); i++){
				//For each bullet in the list we loop through
				RifBullet bullet = bul.get(i);
				if(bullet.getY() < 5){
					//the bullet has gone too high and is off the screen it can be deleted
					bul.remove(i);
					i--;
				}
				else if(bullet.getY() == 0){
					//the bullet has gone too high and is off the screen it can be deleted
					bul.remove(i);
					i--;
				}
				
				}
			
			
			for(Tree tree : trees){
				//draw each tree in the list to the graphics
				tree.draw(bkG);
				}
			
			for(RifBullet bullet : bul){
				//draw each bullet in the list to the graphics and update its position
				//so it moves upwards
				bullet.update(bkG);
				bullet.draw(bkG);
			}
			
			for(int i = 0; i < buck.size(); i++){
				//for each animal in the list update its movement and draw 
				//it to the graphics
				Animal deers = buck.get(i);
				deers.update(bkG);
				deers.draw(bkG);
				if((deers.getX() >800)||(deers.getY()<0)){
					//if the animal goes off the screen to the right or the top
					//it is deleted
					buck.remove(i);
					i--;
				}
				}
			
			
			for(int i = 0; i < bul.size(); i++){
				//For each bullet check if it collides with an animal
				RifBullet bullet = bul.get(i);
				for(int j = 0; j < buck.size(); j++){
					//goes through each animal to check if the bullet collides with it
					Animal deers = buck.get(j);
					Rectangle2D bulcol = bullet.collider();
					Rectangle2D deercol = deers.collider();
					if(bulcol.intersects(deercol)){
						//the bullet has collided with the animal
						//the animal takes the damge from the bullet
						//the bullet is removed
						int dam = bullet.getDamage();
						deers.hit(dam);
						bul.remove(i);
						i--;
						if(deers.health()<=0){
							//the animal was hit and its health is gone
							//move it to simulate a fall and remove it from the list
							//and add it to the animals killed list. Increase the score 
							//and kill count
							deers.move(0, 5);
							buck.remove(j);
							j--;
							killed.add(deers);
							score += 100;
							killCount += 1;
						}
						
						//get the x and y position of where the deer was shot and 
						//add create a new instance of blood to add to the list
						int xpos = deers.getX();
						int ypos = deers.getY();
						Blood blo = new Blood(xpos+20, ypos+30,10);
						bleed.add(blo);
					}
					
				}
			}
			
			for(int k = 0; k < bleed.size(); k++){
				//for each instance of blood draw it to the graphics
				Blood blood = bleed.get(k);
				blood.draw(bkG);
				if(blood.getY()<125){
					//the blood is higher than the land this makes it fall to the ground
					blood.move(0, 1);
					blood.draw(bkG);
				}
			}
			
			for(int k = 0; k < killed.size(); k++){
				//for each animal that is killed draw its killed animation to the graphics
				Animal deer = killed.get(k);
				if(deer.getY()<125){
					//the animal is above land and must fall to the ground
					deer.move(0, 1);
				}
				deer.drawKilled(bkG);
			}
			
			for(int d = 0; d < buck.size(); d++){
				//for each animal check if it runs into a tree and move it 
				//above it if it does
				Animal deery = buck.get(d);
				for(int t = 0; t< trees.size();t++){
					//for each tree check if that animal hit it
					Tree treep = trees.get(t);
					Rectangle2D treecol = treep.collider();
					Rectangle2D buckcol = deery.collider();
					if(buckcol.intersects(treecol)){
						deery.move(0, -10);
					}
				}
			}
			
			if(clickFire){
				//The mouse has been clicked indicating a bullet was fired
				if (System.currentTimeMillis() - lastFire < firingInterval){
					//the last shot was not fired long enough ago and it is still 
					//reloading so do nothing
				}
				else{
					//The gun has reloaded and we are able to fire
					lastFire = System.currentTimeMillis();
					RifBullet bullet = new RifBullet(weapSelect,0,0,4);		//Create a new bullet
					bullet.setX(xloc);		//Move the bullet to the x position of the mouse
					bullet.setY(550);		//Move the bullet to the height of the guns tip
					bul.add(bullet);		//add the bullet to the list
					clickFire = false;		//set clickFire back to false
					}
			}
			
			
			for(int t = 0; t< trees.size(); t++){
				//for each tree in the list of trees check if a bullet has collided with it
				Tree tre = trees.get(t);
				for(int rb = 0; rb< bul.size(); rb++){
					RifBullet bullet = bul.get(rb);
					Rectangle2D treecol = tre.collider();
					Rectangle2D bulcol = bullet.collider();
					if(bulcol.intersects(treecol)){
						//a bullet hit the tree it can be deleted
						bul.remove(rb);
						rb--;
					}
				}
			}
			
			bkG.setColor(Color.black);
			bkG.drawString("Score: " + score, 725, 15);		//draws the score at the upper right of the screen
			
			if(score>highscore){
				highscore = score;		//if the current score is greater than the highscore update it
			}
			
			bkG.dispose();		//dispose of the buffer 
			strategy.show();	//show the other buffer
			
			if(buck.size()==0){
				//all the animals are dead or off screen
				strategy.dispose();		//remove the bufferstrategy
				container.remove(this); //remove the canvas from the screen
				
				//clear the lists 
				buck.clear();
				bleed.clear();
				killed.clear();
				bul.clear();
				trees.clear();
				//breaks out of the gamerunning loop
				break;
			}
			
			//sets the frame rate to 100fps
			try { Thread.sleep(10); } catch (Exception e) {}
			}
		
			if(killCount>= 2){
				//the user has gotten at least 2 kills and will be allowed to continue hunting
				gameContinue();
			}
			else{
				//the user has not gotten at least 2 kills and the game is over
				gameOver(container);
			}
		}
	
	/*
	 * Constructs the container to show a screen to continue the game
	 */
	public void gameContinue(){
		//Creates a panel to hold the game continue screen on
		JPanel gameCont = new JPanel();
		gameCont.setPreferredSize(new Dimension(800,600));
		gameCont.setBackground(Color.orange);
		gameCont.setLayout(new BoxLayout(gameCont, BoxLayout.PAGE_AXIS));
		
		//Creates text to say 'Continue'
		JLabel cont = new JLabel();
		cont.setFont(new Font("Serif", Font.PLAIN, 24));
		cont.setForeground(Color.black);
		cont.setText("Continue?");
		
		//Creates text to explain the user can continue hunting
		JLabel conText = new JLabel();
		conText.setFont(new Font("Serif", Font.PLAIN, 16));
		conText.setForeground(Color.black);
		conText.setText("<html>Congratulations! You got at least 2 kills<br> and can continue hunting. Click the button <br> below to choose your next hunt.</html>");
		
		//Creates text to show the user their current score
		JLabel gameScore = new JLabel();
		gameScore.setFont(new Font("Serif", Font.BOLD, 16));
		gameScore.setForeground(Color.black);
		gameScore.setText("Score: " + score);
		
		//Creates a button to continue the game
		JButton toCont = new JButton();
		toCont.setText("Continue");
		toCont.setPreferredSize(new Dimension(100,50));
		toCont.setBackground(Color.orange);
		toCont.setForeground(Color.black);
		
		//Creates a button to go to the main menu
		JButton toMain = new JButton();
		toMain.setText("Main Menu");
		toMain.setBounds(100,50,100,50);
		toMain.setBackground(Color.orange);
		toMain.setForeground(Color.black);
		
		//Creates button to exit the game
		JButton toExit = new JButton();
		toExit.setText("Exit Game");
		toExit.setBounds(100,50,100,50);
		toExit.setBackground(Color.orange);
		toExit.setForeground(Color.black);
		
		
		//Adds all the components to the panel
		gameCont.add(Box.createRigidArea(new Dimension(0,100)));
		gameCont.add(cont);
		gameCont.add(Box.createRigidArea(new Dimension(150,0)));
		gameCont.add(conText);
		gameCont.add(Box.createRigidArea(new Dimension(0,50)));
		gameCont.add(gameScore);
		gameCont.add(Box.createRigidArea(new Dimension(0,50)));
		gameCont.add(toCont);
		gameCont.add(toMain);
		gameCont.add(toExit);
		
		//Adds the panel of continue components to the screen
		container.add(gameCont);
		
		//Updates the changes made to the container
		container.pack();
		container.repaint();
		
		toCont.addActionListener(new 
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						//Adds an action listener to continue the game if the
						//continue button is hit
						container.remove(gameCont);
						killCount = 0;
						firingInterval= 0;
						container.add(backPanel);
						container.repaint();
						startGame(backPanel);	//goes back to the start game function to start a new hunt
					}
				});
		
		toMain.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						container.setVisible(false);
						new HunterGamev2();
					}
				});
		
		toExit.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						System.exit(0);
					}
				});		
	}
	
	/*
	 * Sets the container to show the game is over and gives the option of going back to the main 
	 * menu or exiting entirely
	 * @param frame the container to show the game over screen on
	 */
	public void gameOver(JFrame frame){
		//Creates a panel to hold the game over components in
		JPanel gameOver = new JPanel();
		gameOver.setPreferredSize(new Dimension(800,600));
		gameOver.setBackground(Color.black);
		gameOver.setLayout(new BoxLayout(gameOver, BoxLayout.PAGE_AXIS));
		
		//Creates text saying game over
		JLabel over = new JLabel();
		over.setFont(new Font("Serif", Font.PLAIN, 24));
		over.setForeground(Color.white);
		over.setText("Game Over");
		
		//Creates text explaining why the game is over
		JLabel overText = new JLabel();
		overText.setFont(new Font("Serif", Font.PLAIN, 16));
		overText.setForeground(Color.white);
		overText.setText("<html>You need to get at<br> least 2 kills to continue.</html>");
		
		//Creates text to show the score
		JLabel gameScore = new JLabel();
		gameScore.setFont(new Font("Serif", Font.BOLD, 16));
		gameScore.setForeground(Color.white);
		gameScore.setText("Score: " + score);
		
		//Adds a button to take the user back to the main menu
		JButton toMenu = new JButton();
		toMenu.setText("Main Menu");
		toMenu.setPreferredSize(new Dimension(100,50));
		toMenu.setBackground(Color.black);
		toMenu.setForeground(Color.white);
		
		//Adds a button to exit the game entirely
		JButton exit = new JButton();
		exit.setText("Exit Game");
		exit.setPreferredSize(new Dimension(100,50));
		exit.setBackground(Color.black);
		exit.setForeground(Color.white);
		
		//Adds the game over components to the panel
		gameOver.add(Box.createRigidArea(new Dimension(0,200)));
		gameOver.add(over);
		gameOver.add(Box.createRigidArea(new Dimension(175,0)));
		gameOver.add(overText);
		gameOver.add(gameScore);
		gameOver.add(toMenu);
		gameOver.add(exit);
		
		//Adds the panel to the frame
		frame.add(gameOver);
		
		//Updates the frame to show the changes made
		frame.pack();
		frame.repaint();
		
		toMenu.addActionListener(new 
				ActionListener()
				{
					/*
					 * The menu button has been selected and start a new instance of HunterGamev2
					 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
					 */
					public void actionPerformed(ActionEvent event)
					{
						frame.setVisible(false);
						new HunterGamev2();
					}
				});
		
		exit.addActionListener(new 
				ActionListener()
				{
					/*
					 * The exit button has been selected and the system exits the game
					 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
					 */
					public void actionPerformed(ActionEvent event)
					{
						System.exit(0);
					}
				});
	}
	
	/*
	 * Creates a new version of MouseListener to detect when the mouse has been clicked
	 */
	class myMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) { 
				
		}

		@Override
		public void mouseEntered(MouseEvent arg0) { }

		@Override
		public void mouseExited(MouseEvent arg0) { }

		@Override
		public void mousePressed(MouseEvent arg0) {
			//the mouse was clicked set a variable to detect it
		    clickFire = true;
		    
		    //Get the x position of the mouse click so a bullet can follow that path
		    PointerInfo userInput = MouseInfo.getPointerInfo();
			Point b = userInput.getLocation();
			double mouseX = b.getX();
			xloc = (int)mouseX - 10;
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			//The mouse was released so clickFire can be false
			clickFire = false;

		}
		}


	/*
	 * The main function that will run the HunterGamev2
	 */
	public static void main(String[] args){
		new HunterGamev2();
		}

}
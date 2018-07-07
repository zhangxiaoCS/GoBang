package gobang;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import edu.csuft.zhangxiao.go.Piece;

/**
 * 
 * Created by zhangxiao
 *
 */
public class UI{
	
		public JButton save;
		private ChessBoard chess = ChessBoard.getInstance();
	    private int userColor =chess.WHITE;
	    private int robotColor = chess.BLACK ;
	    private JFrame frame;
	    JLabel result;
        int who=1;
	    private MyChess drawArea;
	    //public Robot robot =Robot.getRobot();
	    public FoolRobot foolrobot =new FoolRobot(); 
	    public JMenuBar MainMenu;
	    private JMenu start,surrender,about,exit;
	    public JMenuItem startA,startB;
	    public JPanel panel;
	    
	    UI(int b){
	    	panel=new JPanel();
	    	frame = new JFrame("������ --������Ʒ");
	    	MainMenu=new JMenuBar();
	    	//���ð�ť
	    	
			startA=new JMenuItem("�˻���ս");
			startA.setDisabledSelectedIcon(null);
			startB=new JMenuItem("���˶�ս");
			startB.setDisabledSelectedIcon(null);
			surrender=new JMenu("     Ͷ��   ");
			about=new JMenu("     ����   ");
			exit=new JMenu("     �˳�   ");
			start=new JMenu("��ʼ");
			
			this.start.add(startA);
			this.start.add(startB);
			
			MainMenu.add(start);
			MainMenu.add(surrender);
			MainMenu.add(about);
			MainMenu.add(exit);
			
			frame.setSize(730,750);

			/**
			 * ����
			 */
			drawArea = new MyChess(b);
			frame.add(drawArea);
			
			//��Ļ���м�
			frame.setLocationRelativeTo(null);
	    	frame.add(MainMenu,BorderLayout.NORTH);
	    	MainMenu.setVisible(true);
	    	frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	        frame.setVisible(true);
			drawArea.setPreferredSize(new Dimension(720, 720));
	        //���������� [�˻���ս]
	        startA.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {

					chess.clear();
					init(b);
				}
	        });
	        //���������� [���˶�ս]
	        startB.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {

					chess.clear();
					initb(b);
				}
	        });
	        
	        //���������� [Ͷ��]
	        
	        surrender.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					JFrame fram=new JFrame("��Ϸ���");
					if(who%2==0)
						result=new JLabel("�׷�����");
					
					else
						result=new JLabel("�ڷ�����");
					fram.add(result);
					fram.setBounds(400,300,300,200);
					fram.setVisible(true);
					chess.clear();
				}
			});
	        
	        //���������� [����]
	        about.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					JFrame fram=new JFrame("����");
					JLabel result=new JLabel("JAVA������������� --������Ʒ");
					fram.add(result);
					fram.setBounds(400,300,300,200);
					fram.setVisible(true);
				}
			});
	        
	        //���������� [�˳�]
	        
	        exit.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					frame.setVisible(false);
					frame.dispose();
					System.exit(0);
				}
			});
	    }
	    
	    public void initb(int b){

	    	
	        drawArea.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int x = e.getX();
	                int y = e.getY();
	                
	                int i= 0,j= 0;
	                if(b==1) {
	                	i = (x-120)/80+1;
	                	j = (y-120)/80+1;
	                }
	                else if(b==2) {
	                	i= (x-90)/60+1;
	                	j = (y-90)/60+1;
	                }
	                
	                else if(b==3) {
	                	i= (x-60)/40+1;
	                	j= (y-60)/40+1 ;
	                }
	                if(chess.isEmpty(i, j)) {
	                	/**
	                	 * aΪ�����������
	                	 */
	                	if(who%2==1) {
	                		chess.makeMove(i, j, robotColor);
	                		int rel = chess.isEnd(i, j, robotColor);
	                		if(rel!=0) {
	                			new Clock(2);
	                			return;
	                		}
                			who++;
	                    drawArea.repaint();
	                	}
	                	
	                	/**
	                	 * aΪż���������
	                	 */
	                    else{
	                    	who++;
	                    	chess.makeMove(i, j, userColor);
	                    	int rel = chess.isEnd(i, j, userColor);
	                    	if(rel !=0) {
	                    		new Clock(3);
	                    		return;
	                    	}
	                    drawArea.repaint();
	                    }
	                }
	            }
	            @Override
	            public void mousePressed(MouseEvent e) {

	            }

	            @Override
	            public void mouseReleased(MouseEvent e) {

	            }

	            @Override
	            public void mouseEntered(MouseEvent e) {

	            }

	            @Override
	            public void mouseExited(MouseEvent e) {

	            }
	        });
	        
	    }
		public void init(int b){


			//����ִ��
	        chess.makeMove(chess.N/3, chess.N/3, chess.BLACK);
	        who++;
	        drawArea.setPreferredSize(new Dimension(720, 720));
	        
	        drawArea.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int x = e.getX();
	                int y = e.getY();
	                int i=0,j=0;
	                if(b==1) {
	                	i = (x-120)/80+1;//80
	                	j = (y-120)/80+1;
	                }
	                else if(b==2) {
	                	i= (x-90)/60+1;//60
	                	j = (y-90)/60+1;
	                }
	                
	                else if(b==3) {
	                	i= (x-60)/40+1;//40
	                	j = (y-60)/40+1;
	                }
	                if(chess.isEmpty(i, j)) {

	                    chess.makeMove(i, j, userColor);
	                    int rel = chess.isEnd(i, j, userColor);
	                    
	                    /**
	                     * ���ʤ��
	                     */
	                    if(rel != 0) {
	                    	new Clock(0);
	                        return;
	                    }
	                    drawArea.repaint();
	                    //int rob[] = robot.getNext(robotColor);
	                    int rob[] = foolrobot.getNext(i,j,robotColor);
	                    chess.makeMove(rob[0], rob[1], robotColor);
	                    rel = chess.isEnd(rob[0], rob[1], robotColor);
	                    /**
	                     * ����ʤ��
	                     */
	                    
	                    if(rel != 0) {
	                    	new Clock(1);
	                        return;
	                    }
	                    drawArea.repaint();
	                }
	            }
	            @Override
	            public void mousePressed(MouseEvent e) {

	            }

	            @Override
	            public void mouseReleased(MouseEvent e) {

	            }

	            @Override
	            public void mouseEntered(MouseEvent e) {

	            }

	            @Override
	            public void mouseExited(MouseEvent e) {

	            }
	        	});
	        frame.add(drawArea);
	    }
	}
	    	
	class MyChess extends Canvas{
		

	    private ChessBoard chess = ChessBoard.getInstance();
	    private int N;
	    
	    int square;
	    int stx;
	    int sty;
	    int length;
		int b;

	    public MyChess(int b) {
	    	this.b=b;
	    	if(b==1) {
	    		square =80;
	    		N = chess.N-7;
	    		stx= square;
	    		sty= square;
	    		length= (N-1)*square;
	    	}
	    	else if(b==2) {
		    	square =60;
		    	N = chess.N-4;
		    	stx= square;
		    	sty= square;
		    	length= (N-1)*square;
	    	}
	    	else if(b==3) {
		    	square =40;
		    	N = chess.N+2;
		    	stx= square;
		    	sty= square;
		    	length= (N-1)*square;
	    	}
	    }
	    /**
	     * ������
	     * 
	     * @param color
	     * @param x
	     * @param y
	     * @param g
	     */
		public void drawPiece(int color, int x, int y, Graphics g) {
	        if (color == chess.BLACK) {
	            g.setColor(new Color(0, 0, 0));
	            g.fillArc(stx + x * square-19, sty + y * square-19, 38, 38, 0, 360);//-19
	        } else if (color == chess.WHITE) {
	            g.setColor(new Color(255, 255, 255));
	            g.fillArc(stx+ x * square -19, sty+ y * square- 19, 38, 38, 0, 360);
	        }
	    }

	    public void paint(Graphics graphics) {
	    	super.paint(graphics);
	    	Graphics2D g = (Graphics2D) graphics;
	    	
	    	// ���� 2D ͼ����Ⱦ�Ŀ����ѡ��
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			/**
			 * ��������
			 */
	        g.setColor(new Color(0, 0, 0));
	        g.fillRect(stx-8, sty-8, stx+(N-2)*square+15, sty+(N-2)*square+15);
	        g.setColor(new Color(230, 200, 71));
	        g.fillRect(stx-4, sty-4, stx+(N-2)*square+7, sty+(N-2)*square+7);
	    	
	        g.setColor(new Color(0, 0, 0));

//	        /**
//	         * �����̱�ǵ�
//	         */
//	        g.fillArc(stx+8*square-6, sty+8*square-6, 12, 12, 0, 360);
//	        g.fillArc(stx+4*square-6, sty+4*square-6, 12, 12, 0, 360);
//	        g.fillArc(stx+4*square-6, sty+12*square-6, 12, 12, 0, 360);
//	        g.fillArc(stx+12*square-6, sty+4*square-6, 12, 12, 0, 360);
//	        g.fillArc(stx+12*square-6, sty+12*square-6, 12, 12, 0, 360);
	        
	        /**
	         * �������ϵ�����
	         */
	        for(int i = 0; i < N; i++) {

	        	g.drawLine(stx+i*square, sty, stx+i*square, sty+length);
	        	g.drawLine(stx, sty+i*square, stx+length, sty+i*square);
	        }

	        for(int i=0; i<=chess.N;i++) {
	        	for(int j=0; j<=chess.N;j++) {
	        		drawPiece(chess.board[i][j], i, j, g);
	        	}
	        }
	    }
	}

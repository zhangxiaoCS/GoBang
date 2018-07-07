package gobang;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
//import java.util.Timer;
//import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * 
 * Created by zhangxiao
 *
 */
public class Clock extends JFrame{
	public JLabel clockLabel=new JLabel();
	public Timer timer;
	
    public JButton save;
	int second=1;
	private int result;
	public Clock(int result) {
		super("��Ϸ���");

		this.result=result;
		save=new JButton("��ҫս��");
		setBounds(300,200,300,100);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    setVisible(true);
	    InitLabel();
	    InitClockLabel();
	}
	private void InitLabel() {
		if(result==0)
			this.add(new JLabel("�˻���ս�У���ң��׷���ʤ��"),BorderLayout.NORTH);
		else if(result==1)
			this.add(new JLabel("�˻���ս�У�����ʤ��"),BorderLayout.NORTH);
		else if(result==2)
			this.add(new JLabel("���˶�ս�У���ң��ڷ���ʤ��"),BorderLayout.NORTH);
		else
			this.add(new JLabel("���˶�ս�У���ң��׷���ʤ��"),BorderLayout.NORTH);
		this.add(save,BorderLayout.SOUTH);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    BufferedImage  bi = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_ARGB);
			    Graphics2D  g2d = bi.createGraphics();
			    paint(g2d);
			    try {
			          ImageIO.write(bi, "PNG", new File("frame.png"));
			          } catch (IOException e) {
			          e.printStackTrace();
			          }
			    }
		});
	}
	public void InitClockLabel() {
	    this.add(clockLabel);
		this.clockLabel.setText(InitTime()); 
		
	}
	public String InitTime() {
		Date date=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("y��M��d��hʱm��s�� a E");
		String hehe = dateFormat.format(date);
		return hehe;
	}
}

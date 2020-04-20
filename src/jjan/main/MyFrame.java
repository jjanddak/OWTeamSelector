package jjan.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MyFrame extends JFrame implements ActionListener{
	//필드
	JTextArea tarea;
	JTextField inputPt, inputName;
	JComboBox<String> comTank,comDps,comHeal;
	DefaultTableModel model;
	JTable table;
	
	//생성자
	public MyFrame(String title) {//생성자의 인자로 프레임의 제목을 전달받아서
		super(title);//부모 생성자에 전달한다.
		initUI(); //UI 초기화
		//선수목록을 출력하는 메소드 실행
	}
	
//	UI 초기화 메소드
	public void initUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// x, y, width, height 를 한번에 지정하기 
		setBounds(200, 200, 700, 700);
		//보더 레이아웃으로 동작하게 하기 
		setLayout(new BorderLayout());
		//화면 중앙에 위치시키기
		setLocationRelativeTo(null);
		
		//표 만들기
		String header[]= {"닉네임","점수","탱","딜","힐"};
		String contents[][]= {
				{"짱닭","3500","서브탱","서브딜","메인힐 서브힐"},
				{"루시우","3100","메인탱","서브딜","서브힐"}
		};
		String tank[]= {"","메인탱","서브탱","올라운더"};
		String dps[]= {"","히트스캔","투사체","올라운더"};
		String heal[]= {"","메인힐","서브힐","올라운더"};		
		
		model=new DefaultTableModel(contents,header);
		table=new JTable(model);
		JScrollPane scpane=new JScrollPane(table);
		
		//라벨&입력창
		JLabel ptLabel=new JLabel("점수");
		JLabel nameLabel=new JLabel("닉네임");
		
		inputPt=new JTextField(10);
		inputName=new JTextField(10);
		
		comTank=new JComboBox(tank);
		comDps=new JComboBox(dps);
		comHeal=new JComboBox(heal);
		
		JButton insertBtn=new JButton("추가");
		JButton deleteBtn=new JButton("삭제");
		
		//패널생성, UI 추가
		JPanel panelTop=new JPanel();
		
		panelTop.setPreferredSize(new Dimension(700,100));
		
		//패널에 라벨과 입력창 추가
		panelTop.add(ptLabel);
		panelTop.add(inputPt);
		panelTop.add(nameLabel);
		panelTop.add(inputName);

		//콤보박스 추가
		panelTop.add(comTank);
		panelTop.add(comDps);
		panelTop.add(comHeal);
		
		panelTop.add(insertBtn);		
		panelTop.add(deleteBtn);
		
		//패널을 프레임 상단에 배치
		add(panelTop, BorderLayout.NORTH);
		add(scpane, BorderLayout.CENTER);
		
		//버튼에 리스너
		insertBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		
		//버튼 액션 커맨드 설정
		insertBtn.setActionCommand("insert");
		deleteBtn.setActionCommand("delete");
		
		//화면에 보이게 하기
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//눌러진 버튼의 command 문자열을 읽어와서 
		String command=e.getActionCommand();
		
		if(command.equals("insert")) { //선수를 목록에 추가
			//입력값 표에 추가
			String info[]=new String[5];
			info[0]=inputPt.getText();
			info[1]=inputName.getText();
			info[2]=comTank.getSelectedItem().toString();
			info[3]=comDps.getSelectedItem().toString();
			info[4]=comHeal.getSelectedItem().toString();
			model.addRow(info);
			
			inputPt.setText("");
			inputName.setText("");
		}else if(command.equals("delete")) { //선수 삭제
			if(table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "삭제할 셀을 선택해주세요!");
				return;
			}else {
				model.removeRow(table.getSelectedRow());
			}
		}
	}
}













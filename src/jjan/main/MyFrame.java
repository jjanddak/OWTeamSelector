package jjan.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
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
	DefaultTableModel model,model2,model3;
	JTable table, table2,table3;
	
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
		setBounds(200, 200, 1000, 700);
		//보더 레이아웃으로 동작하게 하기 
		setLayout(new FlowLayout());
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
		
		//기본모델, 테이블 생성 
		model=new DefaultTableModel(contents,header);
		model2=new DefaultTableModel(header,0);
		model3=new DefaultTableModel(header,0);
		
		table=new JTable(model);
		table2=new JTable(model2);
		table3=new JTable(model3);
		
		//테이블을 스크롤 할 수 있도록 JScrollPane 사용
		JScrollPane scpane=new JScrollPane(table);
		//MaximumSize와 PrefferredSize 둘다 사용해야 테이블의 가로세로 크기가 제대로 조정된다.(?)
		scpane.setMaximumSize(new Dimension(400,0));
		scpane.setPreferredSize(new Dimension(400,119));
		JScrollPane scpane2=new JScrollPane(table2);
		scpane2.setMaximumSize(new Dimension(400,0));
		scpane2.setPreferredSize(new Dimension(400,119));
		JScrollPane scpane3=new JScrollPane(table3);
		
		//라벨&입력창
		JLabel ptLabel=new JLabel("점수");
		JLabel nameLabel=new JLabel("닉네임");
		JLabel vsLabel=new JLabel("VS");
		JLabel team1Label=new JLabel("1팀");
		JLabel team2Label=new JLabel("2팀");
		JLabel spectateLabel=new JLabel("관전자");
		
		inputPt=new JTextField(10);
		inputName=new JTextField(10);
		
		comTank=new JComboBox(tank);
		comDps=new JComboBox(dps);
		comHeal=new JComboBox(heal);
		
		JButton insertBtn=new JButton("추가");
		JButton deleteBtn=new JButton("삭제");
		
		//패널생성, UI 추가
		JPanel panelTop=new JPanel();
		JPanel panelVs=new JPanel();
		JPanel panelTeam1=new JPanel();
		JPanel panelTeam2=new JPanel();
		JPanel panelSpec=new JPanel();
		
		//패널 사이즈
		panelTop.setPreferredSize(new Dimension(700,100));
		
		//패널에 라벨과 입력창 추가		
		panelTop.add(nameLabel);
		panelTop.add(inputName);
		panelTop.add(ptLabel);
		panelTop.add(inputPt);
		
		panelVs.add(vsLabel);
		
		//패널에 콤보박스 추가
		panelTop.add(comTank);
		panelTop.add(comDps);
		panelTop.add(comHeal);
		
		panelTop.add(insertBtn);		
		panelTop.add(deleteBtn);
		
		//Box 생성
		Box team1Box=Box.createVerticalBox();
		Box team2Box=Box.createVerticalBox();
		Box VsBox=Box.createVerticalBox();
		Box centerBox=Box.createHorizontalBox();
		Box spectateBox=Box.createVerticalBox();
		
		//관전자 테이블 사이즈
		spectateBox.setPreferredSize(new Dimension(500,250));
		
		//Box에 관전자 패널(라벨)과 테이블 추가
		panelTeam1.add(team1Label);
		team1Box.add(panelTeam1);
		team1Box.add(scpane);
		
		panelTeam2.add(team2Label);
		team2Box.add(panelTeam2);
		team2Box.add(scpane2);
		
		panelSpec.add(spectateLabel);
		spectateBox.add(panelSpec);
		spectateBox.add(scpane3);
		
		VsBox.add(VsBox.createVerticalStrut(74));
		VsBox.add(panelVs);
		
		//1팀, VS패널(라벨), 2팀 세개를 centerBox에 삽입
		centerBox.add(team1Box);
		centerBox.add(VsBox);
		centerBox.add(team2Box);
		
		
		//패널을 프레임 상단에 배치
		add(panelTop, BorderLayout.NORTH);
		
		//Box 프레임에 배치
		add(centerBox, BorderLayout.WEST);
		add(spectateBox, BorderLayout.SOUTH);
		
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
			info[0]=inputName.getText();
			info[1]=inputPt.getText();
			info[2]=comTank.getSelectedItem().toString();
			info[3]=comDps.getSelectedItem().toString();
			info[4]=comHeal.getSelectedItem().toString();
			
			if(model.getRowCount()<6) {
				model.addRow(info); //데이터를 모델에 추가				
			}else if(model2.getRowCount()<6){
				model2.addRow(info);
			}else {
				model3.addRow(info);
			}
			
			inputPt.setText("");
			inputName.setText("");//입력창 초기화
			
		}else if(command.equals("delete")) { //선수 삭제
			int selectedRow=table.getSelectedRow();
			int selectedRow2=table2.getSelectedRow();
			int selectedRow3=table3.getSelectedRow();
			
			if(selectedRow == -1 && selectedRow2 == -1
					&& selectedRow3 == -1) {// 선택한 셀이 없을 때
				JOptionPane.showMessageDialog(this, "삭제할 셀을 선택해주세요!");
				return;
			}else if(selectedRow != -1){
				model.removeRow(table.getSelectedRow()); //선택한 셀 모델에서 삭제
			}else if(selectedRow2 != -1){
				model2.removeRow(table2.getSelectedRow()); //선택한 셀 모델에서 삭제
			}else if(selectedRow3 != -1){
				model3.removeRow(table3.getSelectedRow()); //선택한 셀 모델에서 삭제
			} 
		}
	}
}













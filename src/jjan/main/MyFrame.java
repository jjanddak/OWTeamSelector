package jjan.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
	String[] header={"닉네임","점수","탱","딜","힐"};
	
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
		//샘플데이터
//		String contents[][]= {
//				{"루시우","3100","","투사체",""},
//				{"루시우3","2200","","","메인힐"},
//				{"희님","3900","","","서브힐"},
//				{"루시우2","2500","올라운더","",""},
//				{"짱닭","3500","","히트스캔",""},
//				{"야돈","4000","서브탱","",""},
//		};
		String tank[]= {"","메인탱","서브탱","올라운더"};
		String dps[]= {"","히트스캔","투사체","올라운더"};
		String heal[]= {"","메인힐","서브힐","올라운더"};		

		//샘플데이터 추가
//		model=new DefaultTableModel(contents,header);
//		model2=new DefaultTableModel(contents,header);
		//기본모델, 테이블 생성 
		model=new DefaultTableModel(header,0);
		model2=new DefaultTableModel(header,0);
		model3=new DefaultTableModel(header,0);
		
		table=new JTable(model);
		table2=new JTable(model2);
		table3=new JTable(model3);
		
		//테이블을 스크롤 할 수 있도록 JScrollPane 사용
		JScrollPane scpane=new JScrollPane(table);
		//테이블 크기 조정
		//MaximumSize와 PrefferredSize 둘다 사용해야 테이블의 가로세로 크기가 제대로 조정된다.(?)
		scpane.setMaximumSize(new Dimension(400,0));
		scpane.setPreferredSize(new Dimension(400,119));
		JScrollPane scpane2=new JScrollPane(table2);
		scpane2.setMaximumSize(new Dimension(400,0));
		scpane2.setPreferredSize(new Dimension(400,119));
		JScrollPane scpane3=new JScrollPane(table3);
		
		//표 배경이 흰색으로 표시되도록
		scpane.getViewport().setBackground(Color.WHITE);
		scpane2.getViewport().setBackground(Color.WHITE);
		scpane3.getViewport().setBackground(Color.WHITE);
		
		//라벨&입력창
		JLabel ptLabel=new JLabel("점수");
		JLabel nameLabel=new JLabel("닉네임");
		JLabel vsLabel=new JLabel("VS");
		JLabel team1Label=new JLabel("1팀");
		JLabel team2Label=new JLabel("2팀");
		JLabel spectateLabel=new JLabel("관전자");
		
		//점수,닉네임 입력창
		inputPt=new JTextField(10);
		inputName=new JTextField(10);
		
		//콤보박스
		comTank=new JComboBox(tank);
		comDps=new JComboBox(dps);
		comHeal=new JComboBox(heal);
		
		//버튼
		JButton insertBtn=new JButton("추가");
		JButton deleteBtn=new JButton("삭제");
		JButton sortptHiBtn=new JButton("고점수 정렬");
		JButton sortptLowBtn=new JButton("저점수 정렬");
		JButton sortposBtn=new JButton("포지션 정렬");
		
		//패널생성
		JPanel panelTop=new JPanel();
		JPanel panelTop2=new JPanel();		
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
		
		//패널에 버튼 추가
		panelTop.add(insertBtn);		
		panelTop.add(deleteBtn);
		
		panelTop2.add(sortposBtn);
		panelTop2.add(sortptHiBtn);
		panelTop2.add(sortptLowBtn);
		
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
		add(panelTop);
		add(panelTop2);
		
		//Box 프레임에 배치
		add(centerBox);
		add(spectateBox);
		
		//버튼에 리스너
		insertBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		sortptHiBtn.addActionListener(this);
		sortptLowBtn.addActionListener(this);
		sortposBtn.addActionListener(this);
		
		//버튼 액션 커맨드 설정
		insertBtn.setActionCommand("insert");
		deleteBtn.setActionCommand("delete");
		sortptHiBtn.setActionCommand("ptHi");
		sortptLowBtn.setActionCommand("ptLow");
		sortposBtn.setActionCommand("pos");
		
		//화면에 보이게 하기
		setVisible(true);
		JOptionPane.showMessageDialog(this, "OW_TeamSelector 사용법 (만든이.짱닭)\n\n"
				+ "1. 추가 버튼으로 경기에 참여하는 선수의 정보(닉네임, 점수, 포지션)를 최소 12명 이상 추가합니다.\n"
				+ "2. 선수정보에는 탱커, 딜러, 힐러 셋중의 하나의 포지션만을 입력해야합니다.\n"
				+ "3. 최소한 한 포지션에 메인 또는 올라운더가 두 명이상 존재해야합니다.\n"
				+ "4. 선수 입력이 끝나면 정렬 버튼을 누릅니다.\n"
				+ "5. 행을 드래그하거나 클릭하여 선택한 후, 삭제버튼을 누르면 행이 삭제됩니다.\n"
				+ "6. 선수 정보에 필요한 정보가 하나라도 빠지면 정렬 기능이 작동하지 않습니다.");
	}//initUI end
	
	
	//버튼이 눌렸을 때
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
			
			if(model.getRowCount() < 6) {
				model.addRow(info); //데이터를 모델에 추가				
			}else if(model2.getRowCount() < 6){
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
				JOptionPane.showMessageDialog(this, "삭제할 셀을 선택해주세요!"
						,"ERROR",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(selectedRow != -1){
				model.removeRow(table.getSelectedRow()); //선택한 셀 모델에서 삭제
			}else if(selectedRow2 != -1){
				model2.removeRow(table2.getSelectedRow()); //선택한 셀 모델에서 삭제
			}else if(selectedRow3 != -1){
				model3.removeRow(table3.getSelectedRow()); //선택한 셀 모델에서 삭제
			}
		}else if(command.equals("ptHi")) { //고점수 정렬
			//점수 내림차순 
			DefaultTableModel total=pointDesc();
			
			if(!total.equals(null)) {//total이 null 아니면 메소드 실행
				//양팀에 한명씩 분배
				teamSelect(total);				
			}
		}
		else if(command.equals("ptLow")) { //저점수 정렬
			//점수 오름차순 
			DefaultTableModel total=pointAsc();
			
			if(!total.equals(null)) {//total이 null 아니면 메소드 실행
				//양팀에 한명씩 분배
				teamSelect(total);				
			}
		}
		else if(command.equals("pos")) { //포지션 정렬
			//내림차순으로 정렬
			DefaultTableModel total=pointDesc();
			
			if(!total.equals(null)) {//total이 null 아니면 메소드 실행			
				//포지션 별로 재정렬 (탱딜힐 순서)
				total=posSelect(total);
			}
			
			//양팀에 분배
			teamSelect(total);
		}//if(command.equals) end
	}//actionPerformed end
	
	//모든 인원을 점수 내림차순으로 정렬하는 메소드
	public DefaultTableModel pointDesc() {
		//선수의 숫자가 12명 이하면 에러메세지 출력
		if(model.getRowCount()<6 || model2.getRowCount()<6) {
			JOptionPane.showMessageDialog(this, "최소한 12명의 선수 정보를 입력해주세요!",
					"ERROR",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		DefaultTableModel total=new DefaultTableModel(header,0); //모든인원의 정보를 담을 객체
				
		//모든 입력된 row를 total에 담는다
		for(int i=0 ; i < model.getRowCount() ; i++) {
			try {
				total.addRow((Vector)model.getDataVector().get(i));			
				total.addRow((Vector)model2.getDataVector().get(i));			
				
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e);
			}
		}
		
		//관전자가 있을 때 total에 추가
		if(model3.getRowCount()!=0) {
			for(int i=0 ; i < model3.getRowCount() ; i++) {
				total.addRow((Vector)model3.getDataVector().get(i));							
			}
		}
		
		//버블정렬로 내림차순 정렬 수행
		for(int i=0 ; i < total.getRowCount()-1 ; i++) {			
			for(int j=i+1 ; j < total.getRowCount() ; j++) {
				if(Integer.parseInt((String) total.getValueAt(i, 1)) < 
						Integer.parseInt((String)total.getValueAt(j, 1))) {
					
					Object rdata,rdata2;
					rdata=total.getDataVector().get(i);//현재줄
					rdata2=total.getDataVector().get(j);//다음줄
					
					//swap
					total.getDataVector().set(i, rdata2);
					total.getDataVector().set(j, rdata);
					
					
				}//if end
			}//for end			
		}//for end
		
		return total;
		
	}//pointDesc end
	
	//모든 인원을 점수 오름차순으로 정렬하는 메소드
	public DefaultTableModel pointAsc() {
		//선수의 숫자가 12명 이하면 에러메세지 출력
		if(model.getRowCount()<6 || model2.getRowCount()<6) {
			JOptionPane.showMessageDialog(this, "최소한 12명의 선수 정보를 입력해주세요!",
					"ERROR",JOptionPane.ERROR_MESSAGE);
			return null;
		}
				
		DefaultTableModel total=new DefaultTableModel(header,0); //모든인원의 정보를 담을 객체
		
		//모든 입력된 row를 total에 담는다
		for(int i=0 ; i < model.getRowCount() ; i++) {
			try {
				total.addRow((Vector)model.getDataVector().get(i));			
				total.addRow((Vector)model2.getDataVector().get(i));			
				
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e);
			}
		}
		
		//관전자가 있을 때 total에 추가
		if(model3.getRowCount()!=0) {
			for(int i=0 ; i < model3.getRowCount() ; i++) {
				total.addRow((Vector)model3.getDataVector().get(i));							
			}
		}
		
		//버블정렬로 오름차순 정렬 수행
		for(int i=0 ; i < total.getRowCount()-1 ; i++) {			
			for(int j=i+1 ; j < total.getRowCount() ; j++) {
				if(Integer.parseInt((String) total.getValueAt(i, 1)) > 
						Integer.parseInt((String)total.getValueAt(j, 1))) {
					
					Object rdata,rdata2;
					rdata=total.getDataVector().get(i);//현재줄
					rdata2=total.getDataVector().get(j);//다음줄
					
					//swap
					total.getDataVector().set(i, rdata2);
					total.getDataVector().set(j, rdata);
					
					
				}//if end
			}//for end			
		}//for end
		
		return total;
		
	}//pointAsc end
	
	
	//포지션 별 분배하는 메소드
	public DefaultTableModel posSelect(DefaultTableModel total) {
		Object tmp;
		int rnum=0;
		Vector totalr=total.getDataVector();
		
		//탱커 분배(점수 내림차순)
		while(rnum<=3) {//탱커 포지션 4명이 채워질 때 까지
			//0~1인덱스는 메인탱이나 올라운더
			for(int i=0 ; i <= total.getRowCount()-1 ; i++) {
				if(total.getValueAt(i, 2).equals("메인탱") || total.getValueAt(i, 2).equals("올라운더")
						&& rnum<=1) { //0~1인덱스를 메인탱이나 올라운더로
					
						tmp=totalr.get(rnum);
						totalr.set(rnum, totalr.get(i));
						totalr.set(i, tmp);
						rnum++;
						
				}//if end
			}//for end
			
			//나머지를 섭탱이나 올라운더로
			for(int i=2 ; i <= total.getRowCount()-1 ; i++) {
				if(total.getValueAt(i, 2).equals("서브탱") || total.getValueAt(i, 2).equals("올라운더")
						&& rnum<=3 && rnum>1) {
					try {
						tmp=totalr.get(rnum);
						totalr.set(rnum, totalr.get(i));
						totalr.set(i, tmp);
						rnum++;
						
					} catch (IndexOutOfBoundsException e) {
						System.out.println(e);
					}
							
					if(rnum>=12) {
						JOptionPane.showMessageDialog(this, "최소 두명 이상의 올라운더나 메인탱커가 있어야합니다!"
								,"ERROR",JOptionPane.ERROR_MESSAGE);
					}
				}//if end
			}//for end
		}//while end
		
		//딜러 분배(점수 내림차순)
		while(rnum<=7) {//딜러 포지션 4명이 채워질 때 까지
			for(int i=4 ; i <= total.getRowCount()-1 ; i++) {
				if(total.getValueAt(i, 3).equals("올라운더") && rnum<=5) { //4~5인덱스를 올라운더로 우선선발
					
						tmp=totalr.get(rnum);
						totalr.set(rnum, totalr.get(i));
						totalr.set(i, tmp);
						rnum++;
						
				}//if end
			}//for end
			
			for(int i=4 ; i <= total.getRowCount()-1 ; i++) {
				if(total.getValueAt(i, 3).equals("히트스캔") || total.getValueAt(i, 3).equals("투사체")
						|| total.getValueAt(i, 3).equals("올라운더")
						&& rnum<=7 && rnum>5) {//나머지 분배
					
						tmp=totalr.get(rnum);
						totalr.set(rnum, totalr.get(i));
						totalr.set(i, tmp);
						rnum++;
						
					if(rnum>=12) {
						JOptionPane.showMessageDialog(this, "최소 네명 이상의 딜러가 있어야합니다!"
								,"ERROR",JOptionPane.ERROR_MESSAGE);
					}
				}//if end
			}//for end
		}//while end
		
		
		//힐러 분배(점수 내림차순)
		while(rnum<=10) {//힐러 포지션 4명이 채워질 때 까지
			for(int i=8 ; i <= total.getRowCount()-1 ; i++) {
				if(total.getValueAt(i, 4).equals("메인힐") || total.getValueAt(i, 4).equals("올라운더")
						&& rnum<=9) { //8~9인덱스를 메인힐이나 올라운더로
					try {
						tmp=totalr.get(rnum);
						totalr.set(rnum, totalr.get(i));
						totalr.set(i, tmp);
						rnum++;
						
					} catch (IndexOutOfBoundsException e) {
						System.out.println(e);
					}
						
				}//if end
			}//for end 
			for(int i=10 ; i <= total.getRowCount()-1 ; i++) {
				if(total.getValueAt(i, 4).equals("서브힐") || total.getValueAt(i, 4).equals("올라운더")
						&& rnum<=11 && rnum>9) {//나머지를 서브힐이나 올라운더로
					
						tmp=totalr.get(rnum);
						totalr.set(rnum, totalr.get(i));
						totalr.set(i, tmp);
						rnum++;
						
				}//if end
			}//for end
		}//while end
		
		return total;
		
	}//posSelect end
		
	//팀별로 분배하는 메소드
	public void teamSelect(DefaultTableModel total) {
		//모델 모두 초기화
		model.setNumRows(0);
		model2.setNumRows(0);
		model3.setNumRows(0);
		
		//total을 팀1, 2, 관전자에 분배
		for(int i=0; i <= total.getRowCount()-1 ; i++) {
			//현재 로우
			Object rowdata=total.getDataVector().get(i);
			
			if(i%2!=0 && i<=11) {//홀수 줄은 팀2에 추가
				model2.addRow((Vector)rowdata);
			}else if(i>11){//12명 이상일때 관전자에 추가
				model3.addRow((Vector)rowdata);
			}else {//0번인덱스와 짝수 줄은 팀1에 추가
				model.addRow((Vector)rowdata);
			}//if end
		}//for end
		
//		total.setNumRows(0);//total 모델 초기화
	}//teamSelect() end
	
	
	
	
}//class MyFrame end













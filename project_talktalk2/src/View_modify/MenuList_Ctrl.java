package View_modify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class MenuList_Ctrl implements ActionListener {
	MenuList_View menuList_View; // 전역번수 선언 
	
	MenuList_Ctrl(MenuList_View menuList_View){
		this.menuList_View = menuList_View; //메뉴리스트  주소번지 동기화
	}
//	private void Remover() {
//		menuList_View.basementPanel.removeAll();
//		menuList_View.basementPanel.revalidate();
//		menuList_View.basementPanel.repaint();
//	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource(); //아무것도 없어 
		
	}
	

	
}

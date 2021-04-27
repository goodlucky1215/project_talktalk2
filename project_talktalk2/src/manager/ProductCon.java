package manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductCon implements ActionListener{
	ProductView pv   = null;
	ProductDao  pd   = new ProductDao();
 	public ProductCon(ProductView pv) {
		this.pv = pv;
	}
 	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		String command = ae.getActionCommand();
		if(obj == pv.jmi_selALL) {
			System.out.println("전체목록");
			pd.refresh(this);
		}
//		
//		else if(obj == pv.jmi_sel) {
//			System.out.println("승인목록");
//			pd.condition("jmi_sel",this);
//		}
//		else if(obj == pv.jmi_upd) {
//			System.out.println("수정");
//			pd.condition("jmi_upd",this);
//		}
//		else if(obj == pv.jmi_del) {
//			System.out.println("삭제");
//			pd.delete(this);
//		}
//		else if("처리".contentEquals(command)) {
//			pd.updateORnewproduct(this);
//		}
	}

}

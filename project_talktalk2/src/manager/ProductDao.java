package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.util.MyBatisCommonFactory;
class InMember{
	List<Map<String, Object>> p_temp;
}
public class ProductDao {
	SqlSessionFactory sqlSessionFactory = null;
	SqlSession sqlSession = null;
	//전체조회
	public void refresh(ProductCon pc) {
		//연결 베이스///////////////////////////////////////
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		InMember map = new InMember();
		try {
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.selectList("mybatis.ProcMapper1.refresh", map);
			System.out.println(map.p_temp.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			sqlSession.close();
		}
		for(Map<String,Object> map1 : map.p_temp) {
			System.out.println(map1.get("mem_no"));
			Vector<Object> v = new Vector<>();
			v.add(map1.get("mem_no"));
			pc.pv.dtm_dept.addRow(v);
		}
		
	}
	
}

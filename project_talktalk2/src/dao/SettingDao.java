package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.util.MyBatisCommonFactory;

/*************************************************************************
 * @author frien 설정창에 관련된 DAO입니다.
 *************************************************************************/

public class SettingDao {
	/* 선언부 */
	// 설정목록창에 관련된 VO 불러오기
	// SqlSessionFactory를 통하여 MyBatis와 연결할 예정
	SqlSessionFactory sqlSessionFactory = null;
	// sqlSession을 통하여 쿼리문을 받아올 예정
	SqlSession sqlSession = null;

	/* 5-1. 내 정보 불러오기 */
	public Map<String, Object> get_MyInfo(int my_Info) {
		// SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		// List<Map>>타입의 myInfomation 변수명 선언
		Map<String, Object> myInfomation = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			myInfomation = sqlSession.selectOne("mybatis.ProcMapper.myInfo", my_Info);
			// 단위테스트로 인한 주석처리
			System.out.println(myInfomation.get("p_mem_name"));
//			my_Info.put("mem_name", "");
//			my_Info.put("dept_name", "");
//			my_Info.put("mem_level", "");
			// 단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
			// 자원 반납하기
		} finally {
			sqlSession.close();
		}
		return myInfomation;
	}///////////////////////////////////// [[[ 5-1. 내 정보 불러오기 종료 ]]]
		///////////////////////////////////// /////////////////////////////////////

	/* 5-2. 사용자 상태 수정하기 */
	public void update_myState(String Mystate_update, int mem_no) {
		// SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		// 변경된 row를 updateMyState 변수명으로 선언
		Map<String,Object> map = new HashMap<>();
		map.put("p_state",Mystate_update);
		map.put("p_no",mem_no);
		try {
			// MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			// 쿼리문을 사용하여 나온 값을 updateMyState 값에 넣어주기
			sqlSession.update("mybatis.ProcMapper.update_MyState",map);
			// 단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//쿼리문 완료 시, 오라클에서도 변경하기
			sqlSession.commit();
			// 자원 반납하기
			sqlSession.close();
		}
	}///////////////////////////////////// [[[ 5-2. 사용자 상태 수정하기 종료 ]]]
		///////////////////////////////////// /////////////////////////////////////

	public static void main(String[] args) {
		/* 5-1 나의 정보 불러오기 단위테스트 */
		SettingDao sd = new SettingDao();
		Map<String, Object> my_Info = new HashMap<>();
		my_Info.put("p_no", 3);
		my_Info.put("mem_name", "");
		my_Info.put("dept_name", "");
		my_Info.put("mem_level", "");
		//List<Map<String, Object>> temp = sd.get_MyInfo(my_Info);
		//System.out.println(temp);
		
		System.out.println("=================내 정보 불러오기 종료=================");
		
		/* 5-2 사용자 상태 수정하기 단위테스트 */
		Map<String, Object> Update_mySt = new HashMap<>();
		//3번인 이지은님 선택
		Update_mySt.put("p_no", 3);
		//수정할 상태는 "근무중"
		Update_mySt.put("p_state", "근무중");
		// 3번인 이지은님의 현재 상태 "휴가중"
		Update_mySt.put("mem_state","휴가중");
		//int temp1 = sd.Update_myState(Update_mySt);
		
		System.out.println("=================내 상태 수정하기 종료=================");
	}
}

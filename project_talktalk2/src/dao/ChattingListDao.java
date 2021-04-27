package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.util.MyBatisCommonFactory;

/******************************************************************************
 * @author frien
 * 채팅목록창과 관련된 DAO입니다.
 ******************************************************************************/
class Result {
	int p_mem_no;    
    List<Map<String,Object>> p_temp;
}
public class ChattingListDao {
	/* 선언부 */
	//채팅목록창과 관련된 Table 선언하기
	//SqlSessionFactory를 통하여 MyBatis와 연결할 예정
	SqlSessionFactory sqlSessionFactory = null;
	// sqlSession을 통하여 쿼리문을 받아올 예정
	SqlSession 		  sqlSession 		= null; 
	
	/* 3.1 내 채팅방만 띄우기 */
	public List<Map<String, Object>> getMyChat(Map<String,Object> myChat_List){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//myChatList라는 변수명 선언해주기
		Result myChatList = new Result();
		myChatList.p_mem_no = (int) myChat_List.get("p_mem_no");
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			System.out.println("내 채팅방만 가져오는 다오: "+myChat_List.get("p_mem_no"));
			//#32번에서 선언한 변수(myChatList)에 com.util.sqlproc.xml파일의 3-1.내 채팅방만 띄우기 프로시저를 실행하여 myChat_List에서 꺼내온다.
			sqlSession.selectList("mybatis.ProcMapper.myChat_list",myChatList);
		//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
		// 자원 반납하기
		} finally {
			sqlSession.close();
		}
		for(Map<String,Object> map:myChatList.p_temp) {
			System.out.println(map.get("room_name")+" "+map.get("room_no")+" "+map.get("room_typeidentity"));
		}
		return myChatList.p_temp;
	}//////////////////////////////////////////// [[[ 3.1 내 채팅방만 띄우기 종료 ]]] ////////////////////////////////////////////
	
	/* 3.2 입장되지 않은 그룹 채팅방만 띄우기 */
	public List<Map<String, Object>> getGroupChat(Map<String,Object> groupChat_List){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//groupChatList라는 변수명을 Vector로 선언해주기
		Result myChatList = new Result();
		myChatList.p_mem_no = (int) groupChat_List.get("p_mem_no");
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#53번에서 선언한 변수(groupChatList)에 com.util.sqlproc.xml파일의 3-2.입장되지 않은 그룹 채팅방만 띄우기 프로시저를 실행하여 groupChat_List에서 꺼내온다.
			sqlSession.selectList("mybatis.ProcMapper.notEgroup_list",myChatList);
		//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
		//자원 반납하기
		} finally {
			sqlSession.close();
		}
		for(Map<String,Object> map:myChatList.p_temp) {
			System.out.println(map.get("room_name")+" "+map.get("room_no")+" "+map.get("room_typeidentity"));
		}
		return myChatList.p_temp;
	}//////////////////////////////////////////// [[[ 3.2 입장되지 않은 그룹 채팅방만 띄우기 종료 ]]] ////////////////////////////////////////////
}

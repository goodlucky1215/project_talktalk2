package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.util.MyBatisCommonFactory;

/*******************************************************
 * @author frien
 * 로그인 후 보이는 목록창에 대한 DAO 입니다.
 ******************************************************/
class InMember{
	int p_Room_no;
    int p_MemNo;
	List<Map<String, Object>> p_temp;
}
class PutMember{
	int p_no;
	List<Map<String, Object>> p_temp;
}
public class ListDao {
	/* 선언부 */
	//SqlSessionFactory를 통하여 MyBatis와 연결할 예정
	SqlSessionFactory sqlSessionFactory = null;
	// sqlSession을 통하여 쿼리문을 받아올 예정
	SqlSession sqlSession = null;
	
	/* 2-1. 메인 List창 불러오기(로그인 직후 나타나는 목록창) */
	public List<Map<String, Object>> getList(int p_no){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		// mainList 변수 안에 List와 Map을 넣어줄 예정이라 선언함
		PutMember putmember = new PutMember();
		putmember.p_no = p_no;
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#33번에서 선언한 변수에 com.util.sqlproc.xml파일의 2-1.메인 목록창 불러오기 프로시저를 실행하여 M_List에 넣어줌
			sqlSession.selectList("mybatis.ProcMapper.chat_mainList", putmember);
		//예외처리(단계별로 표시함)
		} catch (Exception e) {
			e.printStackTrace();
		//자원반납하기	
		} finally {
			sqlSession.close();
		}
		return putmember.p_temp;
	}//////////////////////////////////////////// [[[ 2-1.메인 List창 불러오기(로그인 직후 나타나는 목록창) 종료 ]]] ////////////////////////////////////////////
	
	/* 2-2. 사원 검색하기 */
	public List<Map<String,Object>> getSearch_Member(List<Map<String,Object>> Search_Member){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		// searchMember 변수 안에 List와 Map을 넣어줄 예정이라 선언함
		List<Map<String,Object>> searchMember = null;
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#54번에서 선언한 Vector변수에 com.util.sqlproc.xml파일 2-2. 사원 검색 프로시저를 실행하고 search_Member에 넣어줌
			searchMember = sqlSession.selectList("mybatis.ProcMapper.chat_searchMember", Search_Member);
		//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
		// 자원 반납하기
		} finally {
			sqlSession.close();
		}
		return searchMember;
	}//////////////////////////////////////////// [[[ 2-2. 사원 검색하기 종료 ]]] ////////////////////////////////////////////
		
	/* 2-3-1. 개인방이 이미 존재하는지 확인*/
	public Map<String,Object> getCreate_Room_indi(int friend,int me){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		// createRoom 변수 안에 List와 Map을 넣어줄 예정이라 선언함
		Map<String,Object> createRoom = new HashMap<>();
		createRoom.put("p_me", me);
		createRoom.put("p_you", friend);
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#75번에서 선언한 Vector변수(createRoom)에 com.util.sqlproc.xml파일 2-3.방 개설 프로시저를 실행하고 create_Room에 넣어줌
			sqlSession.selectOne("mybatis.ProcMapper.proc_CreateRoom_Indi", createRoom);
			//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
			//자원반납
		} finally {
			sqlSession.close();
		}
		return createRoom;
	} //////////////////////////////////////////// [[[ 2-3. 개인방 종료 ]]] //////////////////////////////////////////////
	
	/* 2-3-2. 방 개설하기 */
	public int getCreate_Room(Map<String,Object> create_Room){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		// createRoom 변수 안에 List와 Map을 넣어줄 예정이라 선언함
		Map<String,Object> createRoom = create_Room;
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#75번에서 선언한 Vector변수(createRoom)에 com.util.sqlproc.xml파일 2-3.방 개설 프로시저를 실행하고 create_Room에 넣어줌
			sqlSession.selectOne("mybatis.ProcMapper.create_Room", createRoom);
		//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
		//자원반납
		} finally {
			sqlSession.close();
		}
		System.out.println("만들어진 방 번호:"+(int) createRoom.get("p_room_num"));
		return (int) createRoom.get("p_room_num");
	} //////////////////////////////////////////// [[[ 2-3. 방 개설하기 종료 ]]] //////////////////////////////////////////////
	
	/* 2-4. 방 안에 사람 넣기 */
	public Map<String, Object> getInput_Member(int room_GI_num,int member_no){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		// inputMember 변수 안에 List와 Map을 넣어줄 예정이라 선언함
		InMember inputMember = new InMember();
		inputMember.p_Room_no=room_GI_num;
		inputMember.p_MemNo=member_no;
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#96번에서 선언한 Vector변수(inputMember)에 com.util.sqlproc.xml파일 2-4.방 안에 사람 넣기 프로시저를 실행하고 input_Member에 넣어줌
			sqlSession.selectList("mybatis.ProcMapper.inChat_Member", inputMember);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return inputMember.p_temp.get(0);
	} //////////////////////////////////////////// [[[ 2-4. 방 안에 사람 넣기 종료 ]]] ////////////////////////////////////////////
	
}

package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.util.MyBatisCommonFactory;
import com.vo.ConversationVO;
import com.vo.RoomPplVO;

public class RoomNConversationDao {
	/* 선언부 */
	// 방, 대화에 관련된 VO 가져오기
	ConversationVO 	cVO 	= new ConversationVO();
	RoomPplVO 		rpVO 	= new RoomPplVO();
	//SqlSessionFactory를 통하여 MyBatis와 연결할 예정
	SqlSessionFactory sqlSessionFactory = null;
	// sqlSession을 통하여 쿼리문을 받아올 예정
	SqlSession 		  sqlSession 		= null;
	
	/* 4-1. 방 이름 가져오기 */
	public List<Map<String, Object>> getRoom_people(int room_no){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//roomTitle이라는 변수명 선언해주기
		List<Map<String,Object>> roomTitle = new ArrayList<>();
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#29번에서 선언한 변수(roomTitle)에 com.util.sqlproc.xml파일의 4-1.방 이름 가져오기 프로시저를 실행 (Value값은 room_Title에서 꺼내온다.)
			roomTitle = sqlSession.selectList("mybatis.ProcMapper.getRoom_member", room_no);
		//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
		//자원 반납하기
		} finally {
			sqlSession.close();
		}
		return roomTitle;
	} //////////////////////////////////////////// [[[ 4-1. 방 이름 가져오기 종료 ]]] ////////////////////////////////////////////
	
	/* 4-2. 방 초대하기 */
	public List<Map<String, Object>> getInviteRoom(Map<String,Object> invite_Room){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//inviteRoom이라는 변수명 선언해주기
		List<Map<String,Object>> inviteRoom = new Vector<>();
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#50번에서 선언한 변수(roomTitle)에 com.util.sqlproc.xml파일의 4-2. 방 초대하기 프로시저를 실행 (Value값은 invite_Room에서 꺼내온다.)
			inviteRoom = sqlSession.selectList("mybatis.ProcMapper.invite_Room", invite_Room);
			//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
			//자원 반납하기
		} finally {
			sqlSession.close();
		}
		return inviteRoom;
	} //////////////////////////////////////////// [[[ 4-2. 방 이름 가져오기 종료 ]]] ////////////////////////////////////////////
	
	/* 4-3. 방 나가기 */
	public Map<String, Object> getExitRoom(int room_num,int room_mem_num){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//exitRoom이라는 변수명 선언해주기
		Map<String,Object> exitRoom = new HashMap<>();
		exitRoom.put("p_mem_no",room_mem_num);
		exitRoom.put("p_room_no",room_num);
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#71번에서 선언한 변수(roomTitle)에 com.util.sqlproc.xml파일의 4-3. 방 나가기 프로시저를 실행 (Value값은 exit_Room에서 꺼내온다.)
			sqlSession.selectList("mybatis.ProcMapper.exit_Room", exitRoom);
			//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
			//자원 반납하기
		} finally {
			sqlSession.close();
		}
		return exitRoom;
	}//////////////////////////////////////////// [[[ 4-3. 방 나가기 종료 ]]] ////////////////////////////////////////////
	
	/* 4-4.대화 내용 불러오기 */
	public List<Map<String, Object>> getConverRoom(int room_no){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//ConverRoom이라는 변수명 선언해주기
		List<Map<String, Object>> ConverRoom = new ArrayList<>();
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#92번에서 선언한 변수(ConverRoom)에 com.util.sqlproc.xml파일의 4-4. 대화 내용 불러오기 프로시저를 실행 (Value값은 get_Conversation에서 꺼내온다.)
			ConverRoom = sqlSession.selectList("mybatis.ProcMapper.getConver", room_no);
			//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
			//자원 반납하기
		} finally {
			sqlSession.close();
		}
		return ConverRoom;
	}//////////////////////////////////////////// [[[ 4-4.대화 내용 불러오기 종료 ]]] ////////////////////////////////////////////
	
	/* 4-5. 메시지 전송 */
	public void getSendMsg(int conver_room_num,int mem_num,String conversation){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//sendMsg이라는 변수명 선언해주기
		Map<String,Object> sendMsg = new HashMap<>();
		sendMsg.put("room_no",conver_room_num);
		sendMsg.put("mem_no",mem_num);
		sendMsg.put("conver_text",conversation);
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#113번에서 선언한 변수(sendMsg)에 com.util.sqlproc.xml파일의 4-5. 메시지 전송 프로시저를 실행 (Value값은 send_Msg에서 꺼내온다.)
			sqlSession.selectList("mybatis.ProcMapper.sendMessage", sendMsg);
			//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
			//자원 반납하기
		} finally {
			sqlSession.close();
		}
	}//////////////////////////////////////////// [[[ 4-5. 메시지 전송 종료 ]]] ////////////////////////////////////////////
	/* 4-6. 방들어가기 */
	public void getIngroup(int mem_no,int group_num){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			Map<String, Object> inclient = new HashMap<>();
			inclient.put("mem_no",mem_no);
			inclient.put("room_no",group_num);
			sqlSession.selectOne("mybatis.ProcMapper.getingroup", inclient);
			//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
			//자원 반납하기
		} finally {
			sqlSession.close();
		}
	}//////////////////////////////////////////// [[[ 4-5. 메시지 전송 종료 ]]] ////////////////////////////////////////////
	//1대1일때 상대가 나간상태라면 다시 불러오기
	public int putInyou(int mem_no, int room_num) {
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		Map<String, Object> inclient = null;
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			inclient = new HashMap<>();
			inclient.put("mem_no",mem_no);
			inclient.put("room_no",room_num);
			sqlSession.selectOne("mybatis.ProcMapper.putyou", inclient);
			//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
			//자원 반납하기
		} finally {
			sqlSession.close();
		}
		return Integer.parseInt(inclient.get("p_value").toString());
	}
	
}

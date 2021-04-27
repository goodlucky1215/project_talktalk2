package dao;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.util.MyBatisCommonFactory;
import com.vo.DeptVO;
import com.vo.MemberVO;
/******************************************************************************
 * @author frien
 * 관리자창에 관련된 DAO입니다.
 ******************************************************************************/
public class ManagerDao {
	/* 선언부 */
	//관리자창에 필요한 VO를 선언
	MemberVO 	mVO = new MemberVO();
	DeptVO 		dVO = new DeptVO();
	//SqlSessionFactory를 통하여 MyBatis와 연결할 예정
	SqlSessionFactory sqlSessionFactory = null;
	//sqlSession을 통하여 쿼리문을 받아올 예정
	SqlSession sqlSession = null;

	/* 6-1. 전체 사원 조회 */
	public List<Map<String,Object>> sel_AllMember(Map<String,Object> sel_allMember){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//select_AllMember라는 변수명 선언해주기
		List<Map<String, Object>> select_AllMember= null;
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#32번에서 선언한 변수(select_AllMember)에 com.util.sqlproc.xml파일의 6-1.전체 사원 조회 프로시저를 실행.(sel_allMember의 Value로 꺼내옴)
			select_AllMember = sqlSession.selectList("mybatis.ProcMapper.select_AllMember", sel_allMember);
		//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
		// 자원 반납하기
		} finally {
			sqlSession.close();
		}
		return select_AllMember;
	}//////////////////////////////////////////// [[[ 6-1. 전체 사원 조회 종료 ]]] ////////////////////////////////////////////

	/* 6-2. 회원가입 승인 목록 가져오기 */
	public List<Map<String,Object>> approval_List(Map<String,Object> A_List){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//myChatList라는 변수명 선언해주기
		List<Map<String, Object>> approvalList = new Vector<>();
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#53번에서 선언한 변수(approvalList)에 com.util.sqlproc.xml파일의 6-2.회원가입 승인 목록 가져오기 프로시저를 실행.(A_List의 Value로 꺼내옴)
			approvalList = sqlSession.selectList("mybatis.ProcMapper.approval_List", A_List);
		//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
		// 자원 반납하기
		} finally {
			sqlSession.close();
		}
		return approvalList;
	}//////////////////////////////////////////// [[[ 6-2. 회원가입 승인 목록 가져오기 종료 ]]] ////////////////////////////////////////////
	
	/* 6-3. 회원가입 승인 */
	public List<Map<String,Object>> get_ApprovalMember(Map<String,Object> Approval_M){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//M_Approval라는 변수명 선언해주기(Vector안에 넣어주기)
		List<Map<String, Object>> M_Approval = new Vector<>();
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#74번에서 선언한 변수(M_Approval)에 com.util.sqlproc.xml파일의 6-3. 회원가입 승인 프로시저를 실행.(Approval_M의 Value로 꺼내옴)
			M_Approval = sqlSession.selectList("mybatis.ProcMapper.approval_Member", Approval_M);
		//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
		// 자원 반납하기
		} finally {
			sqlSession.close();
		}
		return M_Approval;
	}//////////////////////////////////////////// [[[ 6-3. 회원가입 승인 종료 ]]] ////////////////////////////////////////////
	
	/* 6-4. 사원정보 수정 */
	public List<Map<String,Object>> update_memInfo(Map<String,Object> update_InfoMember){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//upDate_Member라는 변수명 선언해주기(Vector로)
		List<Map<String, Object>> upDate_Member = new Vector<>();
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#95번에서 선언한 변수(upDate_Member)에 com.util.sqlproc.xml파일의 6-4. 사원정보 수정 프로시저를 실행.(update_InfoMember의 Value로 꺼내옴)
			upDate_Member = sqlSession.selectList("mybatis.ProcMapper.update_MemberInfo", update_InfoMember);
		//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
		// 자원 반납하기
		} finally {
			sqlSession.close();
		}
		return upDate_Member;
	}//////////////////////////////////////////// [[[ 6-4. 사원정보 수정 종료 ]]] ////////////////////////////////////////////
	
	/* 6-5. 사원정보 삭제 */
	public List<Map<String,Object>> del_MemInfo(Map<String,Object> del_MemberInfo){
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//delete_MemInfo라는 변수명 선언해주기
		List<Map<String, Object>> delete_MemInfo = null;
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#116번에서 선언한 변수(delete_MemInfo)에 com.util.sqlproc.xml파일의 6-5. 사원정보 삭제 프로시저를 실행.(del_MemberInfo의 Value로 꺼내옴)
			delete_MemInfo = sqlSession.selectList("mybatis.ProcMapper.del_MemberInfo", del_MemberInfo);
		//단계별 예외처리
		} catch (Exception e) {
			e.printStackTrace();
		// 자원 반납하기
		} finally {
			sqlSession.close();
		}
		return delete_MemInfo;
	}//////////////////////////////////////////// [[[ 6-5. 사원정보 삭제 종료 ]]] ////////////////////////////////////////////
	
}

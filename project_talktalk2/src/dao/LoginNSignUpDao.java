package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.util.MyBatisCommonFactory;


/********************************************************************
 * @author frien
 * 로그인과 회원가입과 관련된 DAO 입니다.
 * 1-1. 로그인 구간은 sqlproc.xml파일과 동일하게 만들었기 때문에 xml파일에서 찾아보면 됩니다!
 ********************************************************************/

public class LoginNSignUpDao{
	/* 선언부 */
	//로그인 뷰쪽이랑 연결하기(단위테스트)
	//SqlSessionFactory를 통하여 MyBatis와 연결할 예정
	SqlSessionFactory sqlSessionFactory = null;
	// sqlSession을 통하여 쿼리문을 받아올 예정
	SqlSession sqlSession = null;
	
	/* 1.1 로그인 구간 */
	public Map<String,Object> getLogin(Map<String,Object> m_login) {
		Map<String, Object> m_login1 =m_login;
		//SqlSessionFactory를 통하여 MyBatis와 연결 (싱글톤)
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		try {
			//MyBatis를 통하여 쿼리문 또는 프로시저를 실행하려고 열었음
			sqlSession = sqlSessionFactory.openSession();
			//#36번에 선언한 변수(loginMap)에 com.util.sqlproc.xml파일의 1-1.로그인 프로시저를 실행하기(Value값은 M_login에서 찾아서 꺼내줄 것)
			sqlSession.selectOne("mybatis.ProcMapper.chat_login", m_login1);
			System.out.println(m_login1.get("msg"));
			System.out.println(m_login1.get("p_no"));
			System.out.println(m_login1.get("p_pw"));
			System.out.println(m_login1.get("p_name"));
		//예외처리
		} catch (Exception e) {
			e.printStackTrace();
		//자원반납
		} finally {
			sqlSession.close();
		}
		return m_login1;
	}//////////////////////////////////////////// [[[ 1-1. 로그인 구간 종료 ]]] ////////////////////////////////////////////
	
	/* 1-2. 회원 가입 구간 */
	public Map<String,Object> getSiguUp(Map<String,Object> m_signUp) {
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		//signUpMap이라는 변수명으로 Map을 선언해준다.
		Map<String, Object> m_signUp1 = m_signUp;
		try {
			//DB의 쿼리문을 돌리기 위하여 sqlSession을 열어준다.
			sqlSession = sqlSessionFactory.openSession();
			//#56번에서 선언한 변수(sinUpMap)에 com.util.sqlproc.xml파일의 1-2.회원가입 프로시저를 실행하기 (Value값은 M_signUp에서 꺼내줄 것)
			sqlSession.selectList("mybatis.ProcMapper.chat_signup", m_signUp1);
		} catch (Exception e) {
			//단계별 예외처리
			e.printStackTrace();
		} finally {
			//섹션 종료 시, 해당 회원 가입 sql을 종료시켜준다.
			sqlSession.close();
		}
		return m_signUp1;
	}//////////////////////////////////////////// [[[ 1-2. 회원가입 구간 종료 ]]] ////////////////////////////////////////////

}
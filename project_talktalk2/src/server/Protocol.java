package server;

public class Protocol {
	//관리자 부분
	public static final int  LOGIN_MANAGER         = 300; //(매니저 로그인)
	public static final int  MANAGER_APPROVELIST   = 310; //(승인해야하는 사원 목록)
	public static final int  MANAGER_APP_UP_DEL    = 320; //(승인해야하는 사원 목록/정보 수정/삭제 버튼 클릭)
	public static final int  MANAGER_APPROVE_DEL   = 320; //관리자 - 승인 목록에서 추가,삭제시 /// 사원 - 사원 목록 추가삭제시
	public static final int  MEMLIST_UP_DEL        = 330; //관리자 - 사원 목록에서 업데이트,삭제시
	//public static final int  MANAGER_DELETE        = 220; //(사원 삭제)(승인해야하는 사원 목록/정보 수정/삭제)
	//사원들 부분
	public static final int  ACCESS        = 100; //(채팅 프로그램 킴)
	public static final int  LOGIN         = 110; //(로그인시)
	public static final int  SIGNUP        = 120; //(회원가입시)
	public static final int  MEMLIST       = 130; //(사원 전부 불러오기)
	public static final int  ROOMLIST      = 140; //(내가 속한 방 전부 불러오기)
	public static final int  GROUPLIST     = 150; //(내가 속하지 않은 그룹 방 전부 불러오기)
	public static final int  MY_INFO       = 160; //(개인 정보 보기)
	public static final int  MY_STATE      = 170; //(나의 상태 설정)
	public static final int  MYROOM_IN     = 180; //(채팅방 생성 후에 입장&새로운 그룹 채팅방 입장)
	public static final int  GROUPROOM_IN  = 190; //(채팅방 생성 후에 입장&새로운 그룹 채팅방 입장)
	public static final int  ROOM_CREATE   = 200; //(채팅방 생성-1:1,N:N고려) - 방 올리거나 내리거나를 구현하기
	public static final int  GROUP_LIST    = 210; //(안들어간 그룹리스트 목록에서 제거 시켜주기) - 방 올리거나 내려가거나를 구현
	public static final int  CHAT_IN       = 220; //채팅방안으로 누가 들어오면 채팅방 안에서 사람 목록에 추가
	public static final int  MESSAGE       = 230; //(채팅방 생성 후에 입장&새로운 그룹 채팅방 입장)
	public static final int  ROOM_OUT      = 240; //(방 나간 사람 표시해주기)
	public static final int  ROOM_DEL      = 250; //(방 삭제)
	public static final int  LOGOUT        = 260; //(로그아웃)
	//SEPERATE - 토큰으로 썰어서 구분할 때 사용
	public static final String  seperator     = "#"; //(로그아웃)
}

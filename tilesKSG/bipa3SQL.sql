select * from simple_board;
select * from book_user;
select * from book_info;
select * from book_copy;

-- //////// 책관리 쿼리 ///////// 
-- 1. selectAllBook
select a.*, b.* from book_info a inner join book_copy b on a.book_isbn=b.book_isbn;

-- ////// 방명록 쿼리 ///////
-- 1. 전체 방명록 리스트
select * from simple_board;
-- 2. 특정 방명록
select * from simple_board where seq = 1;
-- 3. 방명록 등록
insert into simple_board(title, content, writer, read_count, create_date, attatch_password, attatch_data) values("방명록1","내용","작성자1",4,now(),"1234","12323.png");
-- 4. 방명록 삭제
delete from simple_board where seq= 1;
-- 5. 방명록 조회
SELECT * FROM simple_board WHERE title LIKE "%2%"  OR content LIKE "%%" OR writer LIKE "%%";
-- 6. 조회수 업데이트
update simple_board set read_count = (read_count + 1) where seq = 1;

-- ///// 회원관리 쿼리 ///////
-- 1. 모든 회원 리스트 조회
select * from book_user;
-- 2. 특정 회원 조회
select * from book_user where user_seq = 1;
-- 3. 회원가입
insert into book_user(user_id , user_pass, user_phone_number) values("user1" , "1234" , "010-1234-5678" );
insert into book_user(user_id , user_pass, user_phone_number) values("user2" , "1234" , "010-2345-6789" );
-- 4. 로그인 체크
select user_id, user_pass from book_user where user_id= "admin" and user_pass="1234";
-- 5. 아이디 체크
select user_id from book_user where user_id= "admin";
-- 6. 회원 등급 체크
select user_grade from book_user where user_id = "admin"; 
-- 7. 회원 삭제
delete from book_user where user_seq = "1";
-- 8. Id로 회원 찾기
select user_grade from book_user where user_id= "admin";
-- 9. 관리자 추가
insert into book_user(user_id , user_pass, user_phone_number,user_grade) values("admin" , "1234" , "010-7378-5742","2" ); 
          

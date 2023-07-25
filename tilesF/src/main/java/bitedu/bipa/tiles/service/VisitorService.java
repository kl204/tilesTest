package bitedu.bipa.tiles.service;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.tiles.dao.VisitorDao;
import bitedu.bipa.tiles.vo.BoardVO;
import bitedu.bipa.tiles.vo.BookCopy;
import bitedu.bipa.tiles.vo.MemberVO;

@Service("visitorService")
public class VisitorService implements IVisitorService{
	
	@Autowired
	private VisitorDao visiterDao;	
	
	//C : create
	@Override
	public int createText(BoardVO boardVo) {
		int flag = 0;
		
		visiterDao.registText(boardVo);
		
		return flag;
	}
	
	//R : read
	@Override
	public ArrayList<BoardVO> selectAllText(){
		ArrayList<BoardVO> allText = null;
		
		allText = visiterDao.selectAllText();
		
		return allText;
	}
	
	@Override
	public BoardVO selectOneText(int textNum){
		BoardVO OneText = null;
		
		OneText = visiterDao.selectOneText(textNum);
		
		return OneText;
	}
	
	@Override
	public ArrayList<BoardVO> searchText(String text){
		ArrayList<BoardVO> OneText = null;
		
		OneText = visiterDao.searchText(text);
		
		return OneText;
	}

	//U : no update
	@Override
	public int updateView(int text) {
		int flag = 0;
		
		flag = visiterDao.updateView(text);
		
		return flag;
	}
	
	//D : delete
	@Override
	public int removeText(int textNums) {
		int flag = 0;
		
		flag = visiterDao.deleteText(textNums);
		
		return flag;
	}

	@Override
	public int loginCheck(MemberVO memberVo) {
		int flag = 0;
		
		flag = visiterDao.loginCheck(memberVo);
		
		return flag;
	}

	@Override
	public int checkId(String memberId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public BoardVO upload(List<FileItem> items) throws Exception {
		// TODO Auto-generated method stub
		BoardVO copy = new BoardVO();
		
		for(FileItem item : items) {
			if(item!=null&item.isFormField()) {
				//일반적인 Form값 추출
				String fieldName = item.getFieldName();
				if(fieldName.equals("textNum")){
					copy.setTextNum(Integer.parseInt(item.getString("UTF-8")));
					
				} else if(fieldName.equals("title")) {
					copy.setTitle(item.getString("UTF-8"));
					
					
				} else if(fieldName.equals("content")) {
					copy.setContent(item.getString("UTF-8"));
					
					
				} else if(fieldName.equals("writer")) {
					copy.setWriter(item.getString("UTF-8"));
					
					
				} else if(fieldName.equals("createdAt")) {
					String date = item.getString("UTF-8");
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date now = df.parse(date);
						copy.setCreatedAt(new Timestamp(now.getTime()));
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				}

				
			} else {
				// uplode할 데이터에 대한 정보 추출
				String fieldName = item.getFieldName();
				if(fieldName.equals("dataImage")) {
					String temp = item.getName();
					System.out.println("dataImage "+temp);
					int index = temp.lastIndexOf("\\");
					String fileName = temp.substring(index+1);
					copy.setDataImage(fileName);
					
					File uploadFile = new File("d:\\dev\\upload_files\\images\\"+fileName);
					item.write(uploadFile);
				}

			}
		}
		return copy;
	}


}

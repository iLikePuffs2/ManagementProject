package com.yizhi.student.dao;

import com.yizhi.student.domain.StudentInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

/**
 * 生基础信息表
 * @author dunhf
 * @email 499345515@qq.com
 * @date 2019-08-01 09:45:46
 */
@Mapper
public interface StudentInfoDao {

	@Select("select * from s_student_info where id = #{id}")
	StudentInfoDO get(Integer id);

//	@Select("select * from s_student_info limit #{start},#{pageSize}")
	List<StudentInfoDO> list(Integer start, Integer pageSize, String name, String tocollegeId, String tomajorId, String classId);

	@Select("select count(*) from s_student_info")
	int count(Map<String,Object> map);

	@Insert("insert into s_student_info (student_id, exam_id, class_id, student_name, certify, mail_address, foreign_lanaguage, student_sex, nation, political, card_id, telephone, subject_type, tocollege, tocampus, tomajor, birthplace, grade, isstate, birthday, note, add_time, add_userid, edit_time, edit_userid) " +
			"VALUES (#{studentId}, #{examId}, #{classId}, #{studentName}, #{certify}, #{mailAddress}, #{foreignLanaguage}, #{studentSex}, #{nation}, #{political}, #{cardId}, #{telephone}, #{subjectType}, #{tocollege}, #{tocampus}, #{tomajor}, #{birthplace}, #{grade}, #{isstate}, #{birthday}, #{note}, #{addTime}, #{addUserid}, #{editTime}, #{editUserid})")
	int save(StudentInfoDO studentInfo);

	//修改表中的全部字段
	@Update("update s_student_info set student_id = #{studentId},exam_id = #{examId}, class_id = #{classId}, student_name =#{studentName}, certify = #{certify}, mail_address =#{mailAddress}, foreign_lanaguage =#{foreignLanaguage}, student_sex = #{studentSex}, nation = #{nation}, political = #{political}, card_id = #{cardId}, telephone = #{telephone}, subject_type = #{subjectType}, tocollege =#{tocollege}, tocampus =#{tocampus}, tomajor =#{tomajor}, birthplace =#{birthplace}, grade = #{grade}, isstate = #{isstate}, birthday =#{birthday}, note = #{note}, add_time =#{addTime}, add_userid = #{addUserid}, edit_time =#{editTime}, edit_userid =#{editUserid} where student_id = #{studentId}")
	int update(StudentInfoDO studentInfo);

	@Delete("delete from s_student_info where id = #{id}")
	int remove(Integer id);

	int batchRemove(List<Integer> ids);
}

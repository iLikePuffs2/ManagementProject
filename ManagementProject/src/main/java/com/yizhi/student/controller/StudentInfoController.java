package com.yizhi.student.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yizhi.common.annotation.Log;
import com.yizhi.common.controller.BaseController;
import com.yizhi.common.utils.*;
import com.yizhi.student.domain.ClassDO;
import com.yizhi.student.service.ClassService;
import com.yizhi.student.service.CollegeService;
import com.yizhi.student.service.MajorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.yizhi.student.domain.StudentInfoDO;
import com.yizhi.student.service.StudentInfoService;

/**
 * 生基础信息表
 */

@Controller
@RequestMapping("/student/studentInfo")
public class StudentInfoController {


	@Autowired
	private StudentInfoService studentInfoService;


	@Log("学生信息保存")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentInfo:add")
	public R save(@RequestParam("studentId") String studentId,
				  @RequestParam("examId") String examId,
				  @RequestParam("classId") Integer classId,
				  @RequestParam("studentName") String studentName,
				  @RequestParam("certify") String certify,
				  @RequestParam("mailAddress") String mailAddress,
				  @RequestParam("foreignLanaguage") String foreignLanaguage,
				  @RequestParam("studentSex") Integer studentSex,
				  @RequestParam("nation") String nation,
				  @RequestParam("political") String political,
				  @RequestParam("cardId") String cardId,
				  @RequestParam("telephone") String telephone,
				  @RequestParam("subjectType") Integer subjectType,
				  @RequestParam("tocollege") Integer tocollege,
				  @RequestParam("tomajor") Integer tomajor,
				  @RequestParam("birthplace") String birthplace,
				  @RequestParam("grade") String grade,
				  @RequestParam("isstate") Integer isstate,
				  @RequestParam("birthday") Date birthday,
				  @RequestParam("note") String note) {


		//验证学号studentId是不是纯数字
		if(!studentId.matches("[0-9]+")) return R.error("学号必须是纯数字");

		//验证考生号examId是不是纯数字
		if(!examId.matches("[0-9]+")) return R.error("考生号必须是纯数字");

		//验证学生姓名studentName是否规范
		if(!studentName.matches("[\u4e00-\u9fa5]{2,4}")) return R.error("学生姓名必须是2-4个汉字");

		//验证身份证号码certify是否规范
		if(!certify.matches("[0-9]{17}[0-9X]")) return R.error("身份证号码必须是18位数字");

		//验证手机号码telephone是否规范
		if(!telephone.matches("1[3|4|5|7|8][0-9]{9}")) return R.error("手机号码必须是11位数字");

		//验证科目类型subjectType是不是纯数字
		if(!subjectType.toString().matches("[0-9]+")) return R.error("科类类型必须是纯数字");

		//验证在校状态isstate是不是纯数字
		if(!isstate.toString().matches("[0-9]+")) return R.error("是否在校必须是纯数字");


		StudentInfoDO studentInfoDO = new StudentInfoDO();
		studentInfoDO.setStudentId(studentId);
		studentInfoDO.setExamId(examId);
		studentInfoDO.setClassId(classId);
		studentInfoDO.setStudentName(studentName);
		studentInfoDO.setCertify(certify);
		studentInfoDO.setMailAddress(mailAddress);
		studentInfoDO.setForeignLanaguage(foreignLanaguage);
		studentInfoDO.setStudentSex(String.valueOf(studentSex));
		studentInfoDO.setNation(nation);
		studentInfoDO.setPolitical(political);
		studentInfoDO.setCardId(cardId);
		studentInfoDO.setTelephone(telephone);
		studentInfoDO.setSubjectType(subjectType);
		studentInfoDO.setTocollege(tocollege);
		studentInfoDO.setTomajor(tomajor);
		studentInfoDO.setBirthplace(birthplace);
		studentInfoDO.setGrade(String.valueOf(grade));
		studentInfoDO.setIsstate(isstate);
		studentInfoDO.setBirthday(birthday);
		studentInfoDO.setNote(note);

		//增加四个字段的值
		studentInfoDO.setAddTime(new Date());
		studentInfoDO.setAddUserid(Integer.valueOf(ShiroUtils.getUserId().toString()));
		studentInfoDO.setEditTime(new Date());
		studentInfoDO.setEditUserid(Integer.valueOf(ShiroUtils.getUserId().toString()));

		if (studentInfoService.save(studentInfoDO)==1) {
			return R.ok("成功");
		}

		return R.error();
	}

	/**
	 * 可分页 查询
	 */
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentInfo:studentInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		List<StudentInfoDO> list = studentInfoService.list(params);
		int total = studentInfoService.count(params);

		int currPage = Integer.parseInt((String) params.get("currPage"));
		int pageSize = Integer.parseInt((String) params.get("pageSize"));

		return new PageUtils(list, total, currPage, pageSize);

	}


	/**
	 * 修改
	 */
	@Log("学生基础信息表修改")
	@ResponseBody
	@PostMapping("/update")
	@RequiresPermissions("student:studentInfo:edit")
	public R update(@RequestParam("studentId") String studentId,
				  @RequestParam("examId") String examId,
				  @RequestParam("classId") Integer classId,
				  @RequestParam("studentName") String studentName,
				  @RequestParam("certify") String certify,
				  @RequestParam("mailAddress") String mailAddress,
				  @RequestParam("foreignLanaguage") String foreignLanaguage,
				  @RequestParam("studentSex") Integer studentSex,
				  @RequestParam("nation") String nation,
				  @RequestParam("political") String political,
				  @RequestParam("cardId") String cardId,
				  @RequestParam("telephone") String telephone,
				  @RequestParam("subjectType") Integer subjectType,
				  @RequestParam("tocollege") Integer tocollege,
				  @RequestParam("tomajor") Integer tomajor,
				  @RequestParam("birthplace") String birthplace,
				  @RequestParam("grade") String grade,
				  @RequestParam("isstate") Integer isstate,
				  @RequestParam("birthday") Date birthday,
				  @RequestParam("note") String note) {


		//验证学号studentId是不是纯数字
		if(!studentId.matches("[0-9]+")) return R.error("学号必须是纯数字");

		//验证考生号examId是不是纯数字
		if(!examId.matches("[0-9]+")) return R.error("考生号必须是纯数字");

		//验证学生姓名studentName是否规范
		if(!studentName.matches("[\u4e00-\u9fa5]{2,4}")) return R.error("学生姓名必须是2-4个汉字");

		//验证身份证号码certify是否规范
		if(!certify.matches("[0-9]{17}[0-9X]")) return R.error("身份证号码必须是18位数字");

		//验证手机号码telephone是否规范
		if(!telephone.matches("1[3|4|5|7|8][0-9]{9}")) return R.error("手机号码必须是11位数字");

		//验证科目类型subjectType是不是纯数字
		if(!subjectType.toString().matches("[0-9]+")) return R.error("科类类型必须是纯数字");

		//验证在校状态isstate是不是纯数字
		if(!isstate.toString().matches("[0-9]+")) return R.error("是否在校必须是纯数字");


		StudentInfoDO studentInfoDO = new StudentInfoDO();
		studentInfoDO.setStudentId(studentId);
		studentInfoDO.setExamId(examId);
		studentInfoDO.setClassId(classId);
		studentInfoDO.setStudentName(studentName);
		studentInfoDO.setCertify(certify);
		studentInfoDO.setMailAddress(mailAddress);
		studentInfoDO.setForeignLanaguage(foreignLanaguage);
		studentInfoDO.setStudentSex(String.valueOf(studentSex));
		studentInfoDO.setNation(nation);
		studentInfoDO.setPolitical(political);
		studentInfoDO.setCardId(cardId);
		studentInfoDO.setTelephone(telephone);
		studentInfoDO.setSubjectType(subjectType);
		studentInfoDO.setTocollege(tocollege);
		studentInfoDO.setTomajor(tomajor);
		studentInfoDO.setBirthplace(birthplace);
		studentInfoDO.setGrade(String.valueOf(grade));
		studentInfoDO.setIsstate(isstate);
		studentInfoDO.setBirthday(birthday);
		studentInfoDO.setNote(note);

		//增加2个字段的值
		studentInfoDO.setEditTime(new Date());
		studentInfoDO.setEditUserid(Integer.valueOf(ShiroUtils.getUserId().toString()));

		if (studentInfoService.update(studentInfoDO)==1) {
			return R.ok("修改成功");
		}

		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("学生基础信息表删除")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentInfo:remove")
	public R remove( Integer id){

		if (studentInfoService.remove(id)==1) {
			return R.ok("删除成功");
		}

		return R.error();
	}

	/**
	 * 批量删除
	 */
	@Log("学生基础信息表批量删除")
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		if (studentInfoService.batchRemove(ids)>0) {
			return R.ok("删除成功");
		}

		return R.error();
	}


	//前后端不分离 客户端 -> 控制器-> 定位视图
	/**
	 * 学生管理 点击Tab标签 forward页面
	 */
	@GetMapping()
	@RequiresPermissions("student:studentInfo:studentInfo")
	String StudentInfo(){
		return "student/studentInfo/studentInfo";
	}

	/**
	 * 更新功能 弹出View定位
	 */
	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		StudentInfoDO studentInfo = studentInfoService.get(id);
		model.addAttribute("studentInfo", studentInfo);
		return "student/studentInfo/edit";
	}

	/**
	 * 学生管理 添加学生弹出 View
	 */
	@GetMapping("/add")
	@RequiresPermissions("student:studentInfo:add")
	String add(){
	    return "student/studentInfo/add";
	}

}//end class

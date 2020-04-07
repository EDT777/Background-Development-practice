package com.octopus.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.octopus.project.domain.SysRoleMenuDO;
import com.octopus.project.service.SysRoleMenuService;
import com.octopus.common.utils.PageUtils;
import com.octopus.common.utils.Query;
import com.octopus.common.utils.R;

/**
 * 角色与菜单对应关系
 * 
 * @author Jason
 * @email 1626883728@qq.com
 * @date 2020-02-23 22:23:26
 */
 
@Controller
@RequestMapping("/project/sysRoleMenu")
public class SysRoleMenuController {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	
	@RequestMapping("/list")
	@ResponseBody
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SysRoleMenuDO> sysRoleMenuList = sysRoleMenuService.list(query);
		int total = sysRoleMenuService.count(query);
		PageUtils pageUtils = new PageUtils(sysRoleMenuList, total);
		return pageUtils;
	}
	
	@RequestMapping("/add")
	public String add(){
	    return "project/sysRoleMenu/add";
	}
	
	@RequestMapping("/toList")
	public String toList(){
	    return "project/sysRoleMenu/sysRoleMenu";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model){
		SysRoleMenuDO sysRoleMenu = sysRoleMenuService.get(id);
		model.addAttribute("sysRoleMenu", sysRoleMenu);
	    return "project/sysRoleMenu/edit";
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@ResponseBody
	public R save( SysRoleMenuDO sysRoleMenu){
		if(sysRoleMenuService.save(sysRoleMenu)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public R update( SysRoleMenuDO sysRoleMenu){
		sysRoleMenuService.update(sysRoleMenu);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(sysRoleMenuService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 批量删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] ids){
		sysRoleMenuService.batchRemove(ids);
		return R.ok();
	}
	
}